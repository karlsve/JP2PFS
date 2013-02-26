/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs;

import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import jp2pfs.client.PTPClient;
import jp2pfs.client.PTPClientListener;
import jp2pfs.client.PTPClientMessage;
import jp2pfs.server.PTPServer;
import jp2pfs.server.PTPServerListener;
import jp2pfs.server.PTPServerMessage;

/**
 *
 * @author karlinsv
 */
public class JavaPeerToPeerFileSharing {
    
    JFrame frame = null;
    String title = "PTP";
    int width = 400;
    int height = 400;
    
    TextArea textarea = null;
    
    int serverport = 2100;
    PTPServer server = null;
    
    PTPServerListener serverListener = new PTPServerListener() {

        @Override
        public void onMessage(PTPServerMessage message) {
            doOnServerMessage(message);
        }
    };
    
    PTPClientListener clientListener = new PTPClientListener() {

        @Override
        public void onMessage(PTPClientMessage message) {
            doOnClientMessage(message);
        }
    };
    
    public static void main(String[] args) {
        new JavaPeerToPeerFileSharing();
    }
    
    public JavaPeerToPeerFileSharing() {
        initLayout();
        startServer();
        startClient();
    }

    private void initLayout() {
        frame = new JFrame(title);
        textarea = new TextArea(5, 40);
        textarea.setEditable(false);
        frame.setSize(width, height);
        frame.add(textarea);
        frame.addWindowListener(new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent e) {
		onWindowClose();
	    }
	});
        frame.setVisible(true);
    }

    private void onWindowClose() {
        if(frame != null) {
            frame.setVisible(false);
            frame.dispose();
        }
        stopServer();
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
    
    private void doOnServerMessage(PTPServerMessage message) {
        
    }

    private void startClient() {
        try {
            for(int i = 0; i<400; i++) {
                PTPClient client = new PTPClient("localhost", serverport, InetAddress.getLocalHost(), "");
                client.addListener(clientListener);
                client.sendMessageClient("QWERTYUIOP");
            }
        } catch (UnknownHostException ex) {
        }
    }

    private void doOnClientMessage(PTPClientMessage message) {
        if(message.getSender() instanceof PTPClient) {
            PTPClient client = (PTPClient) message.getSender();
            switch(message.getMessageCode()) {
                case CONNECTION_SUCCESS:
                    
                    break;
                case MESSAGE_SEND_SUCCESS:
                    System.out.println(message.getMessage());
                    break;
                case MESSAGE_RECEIVE_SUCCESS:
                    System.out.println("Message received.");
                    textarea.append(message.getMessage()+"\n");
                    break;
            }
        }
    }
}
