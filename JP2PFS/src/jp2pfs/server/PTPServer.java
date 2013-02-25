/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import jp2pfs.client.PTPClient;

/**
 *
 * @author karlinsv
 */
public class PTPServer implements Runnable {
    
    private ServerSocket connection = null;
    private int port = 0;
    //private int timeout = 5000;
    
    private boolean run = true;
    
    List<PTPServerListener> serverListener = new ArrayList<PTPServerListener>();
    
    public PTPServer(int port) {
        this.port = port;
    }
    
    private void init() {
        try {
            this.connection = new ServerSocket(this.port);
            //this.connection.setSoTimeout(this.timeout);
            this.sendMessage(this, 100, "Server connection established.");
        } catch(Exception e) {
            this.sendError(this, 500, "Could not establish server connection.");
        }
        while(run) {
            try {
                Socket client = this.connection.accept();
                this.sendMessage(this, 100, "Connection to client established.");
            }catch(Exception e) {
                this.sendError(this, 404, "Could not establish connection to client.");
            }
        }
    }
    
    public void stop() {
        run = false;
        try {
            this.connection.close();
        } catch (Exception e) {
            this.sendError(this, 600, "Could not close the server connection.");
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

    private void sendMessage(Object sender, int code, String message) {
        for(PTPServerListener listener : serverListener) {
            if(listener != null)
                listener.onMessage(message);
        }
    }

    @Override
    public void run() {
        this.init();
    }
    
}
