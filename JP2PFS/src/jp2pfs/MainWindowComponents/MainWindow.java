/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.MainWindowComponents;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import jp2pfs.Chat.ChatMessage;
import jp2pfs.client.PTPClient;
import jp2pfs.client.PTPClientListener;
import jp2pfs.client.PTPClientMessage;
import jp2pfs.global.IPHandling;
import jp2pfs.server.PTPServer;
import jp2pfs.server.PTPServerListener;
import jp2pfs.server.PTPServerMessage;

/**
 *
 * @author karlinsv
 */
public class MainWindow extends javax.swing.JFrame {

    
    private PTPServer server = null;
    int serverport = 2100;
    
    UserItem self = null;
    
    PTPServerListener serverListener = new PTPServerListener() {

        @Override
        public void onMessage(PTPServerMessage message) {
            
        }
    };
    
    PTPClientListener clientListener = new PTPClientListener() {

        @Override
        public void onMessage(PTPClientMessage message) {
            onClientMessage(message);
        }
        
    };
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        userListScrollPane = new javax.swing.JScrollPane();
        userList = new javax.swing.JList();
        mainWindowTabPane = new javax.swing.JTabbedPane();
        mainWindowHomePanel = new javax.swing.JPanel();
        mainWindowHomeScrollPane = new javax.swing.JScrollPane();
        MainWindowHomeTreeView = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("MainWindow");

        jSplitPane1.setDividerLocation(110);

        userListScrollPane.setName("Userlist");

        userList.setModel(new DefaultListModel<UserItem>());
        userListScrollPane.setViewportView(userList);

        jSplitPane1.setLeftComponent(userListScrollPane);

        mainWindowHomeScrollPane.setViewportView(MainWindowHomeTreeView);

        javax.swing.GroupLayout mainWindowHomePanelLayout = new javax.swing.GroupLayout(mainWindowHomePanel);
        mainWindowHomePanel.setLayout(mainWindowHomePanelLayout);
        mainWindowHomePanelLayout.setHorizontalGroup(
            mainWindowHomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainWindowHomePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainWindowHomeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainWindowHomePanelLayout.setVerticalGroup(
            mainWindowHomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainWindowHomePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainWindowHomeScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainWindowTabPane.addTab("Home", mainWindowHomePanel);

        jSplitPane1.setRightComponent(mainWindowTabPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree MainWindowHomeTreeView;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel mainWindowHomePanel;
    private javax.swing.JScrollPane mainWindowHomeScrollPane;
    private javax.swing.JTabbedPane mainWindowTabPane;
    private javax.swing.JList userList;
    private javax.swing.JScrollPane userListScrollPane;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initTabPane();
        startServer();
        initUserList();
        initUser();
    }
    
    private void initTabPane() {
        mainWindowTabPane.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    int index = mainWindowTabPane.getSelectedIndex();
                    if(mainWindowTabPane.getTitleAt(index) != "Home") {
                        mainWindowTabPane.removeTabAt(index);
                    }
                }
            }
        });
    }

    private void initUser() {
        List<InetAddress> localip = IPHandling.getLocalIpList();
        for(int i = 0; i<userList.getModel().getSize(); i++) {
            UserItem current = (UserItem)userList.getModel().getElementAt(i);
            for(InetAddress ip : localip) {
                if(current.getIp().equals(ip)) {
                    self = current;
                    System.out.println(ip);
                }
            }
        }
    }

    private void initUserList() {
        userList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    createTabUser(index);
                } else if (evt.getClickCount() == 3) {   // Triple-click
                    int index = list.locationToIndex(evt.getPoint());

                }
            }
        });
        DefaultListModel<UserItem> list = (DefaultListModel<UserItem>)userList.getModel();
        try {
            UserItem user = new UserItem("Marvin Middel", InetAddress.getByName("172.30.64.19"), 2100, clientListener);
            list.addElement(user);
            user = new UserItem("Andreas Förtsch", InetAddress.getByName("172.30.64.150"), 2100, clientListener);
            list.addElement(user);
            user = new UserItem("Sven Karliner", InetAddress.getByName("172.30.64.20"), 2100, clientListener);
            list.addElement(user);
        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void createTabUser(int userindex) {
        UserItem item = (UserItem)userList.getModel().getElementAt(userindex);
        UserPanel panel = new UserPanel(self, item);
        if(mainWindowTabPane.indexOfComponent(panel) < 0) {
            mainWindowTabPane.addTab(item.getUsername(), panel);
        }
        item.setTabIndex(mainWindowTabPane.indexOfComponent(panel));
        mainWindowTabPane.setSelectedIndex(item.getTabIndex());
    }
    
    private void startServer() {
        if(server == null) {
            server = new PTPServer(serverport);
            server.addServerListener(serverListener);
            server.addClientListener(clientListener);
        }
        new Thread(server).start();
    }

    private void stopServer() {
        if(server != null) {
            server.stop();
        }
    }
    
    private void onClientMessage(PTPClientMessage message) {
        if(message.getSender() instanceof PTPClient) {
            PTPClient client = (PTPClient) message.getSender();
            DefaultListModel<UserItem> list = (DefaultListModel<UserItem>) userList.getModel();
            switch(message.getMessageCode()) {
                case MESSAGE_SEND_SUCCESS:
                    for(int i = 0; i<list.getSize(); i++) {
                        UserItem user = list.getElementAt(i);
                        if(message.getTo().equals(user.getIp())) {
                            user.addMessage(new ChatMessage(message.getFrom(), message.getTo(), message.getMessage()));
                        }
                    }
                    break;
                case MESSAGE_RECEIVE_SUCCESS:
                    for(int i = 0; i<list.getSize(); i++) {
                        UserItem user = list.getElementAt(i);
                        if(message.getFrom().equals(user.getIp())) {
                            ChatMessage chatMessage = new ChatMessage(message.getFrom(), message.getTo(), message.getMessage());
                            user.addMessage(chatMessage);
                            if(user.getTabIndex() != 0) {
                                ((UserPanel)mainWindowTabPane.getTabComponentAt(user.getTabIndex())).addMessage(chatMessage);
                            }
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println(message.getMessage());
                    break;
                    
            }
        }
    }
}
