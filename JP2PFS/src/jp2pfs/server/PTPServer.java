/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.server;

import java.net.SocketAddress;
import java.util.List;
import jp2pfs.client.PTPClient;

/**
 *
 * @author karlinsv
 */
public class PTPServer extends Thread {
    
    public PTPServer() {
        
    }
    
    public PTPClient addClient(String name, SocketAddress ip, int port) {
        return null;
    }
    
    public void updateClient(PTPClient client) {
        
    }
    
    public boolean removeClient(PTPClient client) {
        return false;
    }
    
    public PTPClient getClient(int id) {
        return null;
    }
    
    public List<PTPClient> getClients() {
        return null;
    }
    
    public List<PTPClient> getClientsOnline() {
        return null;
    }
    
}
