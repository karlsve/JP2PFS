/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.MainWindowComponents;

import java.net.InetAddress;

/**
 *
 * @author karlinsv
 */
public class UserItem {
    
    String username = "";
    InetAddress ip = null;
    int port = 0;
    
    public UserItem(String username, InetAddress ip, int port) {
        this.username = username;
        this.ip = ip;
        this.port = port;
    }
    
    public String toString() {
        return username;
    }
    
}
