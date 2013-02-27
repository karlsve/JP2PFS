/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.MainWindowComponents;

import java.util.List;
import jp2pfs.Chat.ChatMessage;
import jp2pfs.client.PTPClient;
import sun.reflect.generics.tree.Tree;

/**
 *
 * @author karlinsv
 */
public class UserPanel extends javax.swing.JPanel {

    private UserItem to = null;
    private UserItem from = null;
    
    
    public UserItem getTo() {
        return to;
    }
    
    public UserItem getFrom() {
        return from;
    }
    
    /**
     * Creates new form UserPanel
     */
    public UserPanel(UserItem from, UserItem to) {
        this.to = to;
        this.from = from;
        
        initComponents();
        initFileView();
    }
    
    public void initFileView() {
        
    }
    
    public void setMessages(List<ChatMessage> messageList) {
        for(ChatMessage chatMessage : messageList) {
            this.addMessage(chatMessage);
        }
    }
    
    public void addMessage(ChatMessage message) {
        UserChatTextArea.append(message.getFrom().getUsername()+": "+message.getContent()+"\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        UserTreeView = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        UserChatTextArea = new javax.swing.JTextArea();
        UserChatInput = new javax.swing.JTextField();
        UserChatSend = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        setPreferredSize(new java.awt.Dimension(527, 350));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        UserTreeView.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(UserTreeView);

        UserChatTextArea.setColumns(20);
        UserChatTextArea.setRows(5);
        jScrollPane2.setViewportView(UserChatTextArea);

        UserChatSend.setText("Senden");
        UserChatSend.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        UserChatSend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UserChatSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserChatSendMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(UserChatInput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UserChatSend, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UserChatInput, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(UserChatSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void UserChatSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserChatSendMouseClicked
        PTPClient client = new PTPClient(from, to);
        client.addListener(from.getClientListener());
        client.sendMessageClient(UserChatInput.getText());
        UserChatInput.setText("");
    }//GEN-LAST:event_UserChatSendMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField UserChatInput;
    private javax.swing.JButton UserChatSend;
    private javax.swing.JTextArea UserChatTextArea;
    private javax.swing.JTree UserTreeView;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
