/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.MainWindowComponents;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import jp2pfs.Chat.ChatMessage;
import jp2pfs.client.PTPClientListener;

/**
 *
 * @author karlinsv
 */
public class UserItem {
    
    private String username = "";
    private String password = "";
    private InetSocketAddress ip = null;
    private int tabIndex = 0;
    
    FileTreeModel treeModel = null;
    
    private List<ChatMessage> messages = new ArrayList<ChatMessage>();
    
    private PTPClientListener clientListener = null;

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public String getPassword() {
        return password;
    }

    public PTPClientListener getClientListener() {
        return clientListener;
    }

    public InetSocketAddress getIp() {
        return ip;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }
    
    public void addMessage(ChatMessage message) {
        messages.add(message);
    }

    public String getUsername() {
        return username;
    }

    public FileTreeModel getTreeModel() {
        return treeModel;
    }

    public void setTreeModel(FileTreeModel treeModel) {
        this.treeModel = treeModel;
    }
    
    public UserItem(String username, String password, InetSocketAddress ip) {
        this.username = username;
        this.password = password;
        this.ip = ip;
    }
    
    public UserItem(String username, InetSocketAddress ip, PTPClientListener listener) {
        this.username = username;
        this.ip = ip;
        this.clientListener = listener;
    }
    
    public UserItem(String username, InetSocketAddress ip) {
        this.username = username;
        this.ip = ip;
    }
    
    @Override
    public String toString() {
        return username;
    }
    
}
