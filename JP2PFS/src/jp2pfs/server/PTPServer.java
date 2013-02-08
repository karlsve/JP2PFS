/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.server;

import java.net.ServerSocket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import jp2pfs.client.PTPClient;

/**
 *
 * @author karlinsv
 */
public class PTPServer extends Thread {
    
    ServerSocket connection = null;
    int port = 0;
    
    List<PTPServerListener> serverListener = new ArrayList<PTPServerListener>();
    
    public PTPServer(int port) {
        this.port = port;
    }
    
    private void init() {
        try {
            this.connection = new ServerSocket(this.port);
        } catch(Exception e) {
            this.sendError(this, 500, "Could not connect to server.");
        }
    }

    public void setPort(int port) {
        this.port = port;
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
    
    public void addListener(PTPServerListener listener) {
        if(!serverListener.contains(listener)) {
            serverListener.add(listener);
        }
    }
    
    public void removeListener(PTPServerListener listener) {
        if(serverListener.contains(listener)) {
            serverListener.remove(listener);
        }
    }
    
    public void sendError(Object sender, int code, String message) {
        PTPServerError error = new PTPServerError(sender, code, message);
        for(PTPServerListener listener : serverListener) {
            if(listener != null)
                listener.onError(error);
        }
    }
    
}
