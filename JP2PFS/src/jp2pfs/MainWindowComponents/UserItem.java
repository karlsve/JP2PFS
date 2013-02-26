/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.MainWindowComponents;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import jp2pfs.Chat.ChatMessage;
import jp2pfs.client.PTPClient;
import jp2pfs.client.PTPClientListener;
import jp2pfs.client.PTPClientMessage;

/**
 *
 * @author karlinsv
 */
public class UserItem {
    
    private String username = "";
    private InetAddress ip = null;
    private int port = 0;
    
    private List<ChatMessage> messages = new ArrayList<ChatMessage>();
    
    private PTPClientListener clientListener = new PTPClientListener() {

        @Override
        public void onMessage(PTPClientMessage message) {
            doOnClientMessage(message);
        }
    };

    public PTPClientListener getClientListener() {
        return clientListener;
    }

    public InetAddress getIp() {
        return ip;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    private void doOnClientMessage(PTPClientMessage message) {
        if(message.getSender() instanceof PTPClient) {
            PTPClient client = (PTPClient) message.getSender();
            if(message.getIp() != null) {
                switch(message.getMessageCode()) {
                    case CONNECTION_SUCCESS:

                        break;
                    case MESSAGE_SEND_SUCCESS:
                        messages.add(new ChatMessage(ip, message.getMessage()));
                        break;
                    case MESSAGE_SEND_ERROR:
                        System.out.println(message.getMessage());
                        break;
                    case MESSAGE_RECEIVE_SUCCESS:
                        System.out.println("Message received.");
                        messages.add(new ChatMessage(ip, message.getMessage()));
                        break;
                    case MESSAGE_RECEIVE_ERROR:
                        System.out.println(message.getMessage());
                        break;
                }
            }
        }
    }
    
    public UserItem(String username, InetAddress ip, int port) {
        this.username = username;
        this.ip = ip;
        this.port = port;
    }
    
    public String toString() {
        return username;
    }
    
}
