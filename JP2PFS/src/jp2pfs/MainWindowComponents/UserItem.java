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
    private String password = "";
    private InetAddress ip = null;
    private int port = 0;
    private int tabIndex = 0;

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public String getPassword() {
        return password;
    }
    
    private List<ChatMessage> messages = new ArrayList<ChatMessage>();
    
    private PTPClientListener clientListener = null;

    public PTPClientListener getClientListener() {
        return clientListener;
    }

    public InetAddress getIp() {
        return ip;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }
    
    public void addMessage(ChatMessage message) {
        messages.add(message);
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }
    
    public UserItem(String username, String password, InetAddress ip, int port) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.port = port;
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
