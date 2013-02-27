/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import jp2pfs.MainWindowComponents.UserItem;
import jp2pfs.client.PTPClient;
import jp2pfs.client.PTPClientListener;
import jp2pfs.server.PTPServerMessage.PTPServerMessageCode;

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
    List<PTPClientListener> clientListener = new ArrayList<PTPClientListener>();
    List<UserItem> userList = new ArrayList<UserItem>();
    
    public PTPServer(int port, List<UserItem> userList) {
        this.port = port;
        this.userList = userList;
    }
    
    private void init() {
        try {
            this.connection = new ServerSocket(this.port);
            //this.connection.setSoTimeout(this.timeout);
            this.sendMessage(this, PTPServerMessageCode.SUCCESS, "Server connection established.");
        } catch(Exception e) {
            this.sendMessage(this, PTPServerMessageCode.SERVER_CONNECTION_ERROR, "Could not establish server connection.");
        }
        while(run) {
            try {
                Socket clientConnection = this.connection.accept();
                UserItem from = null;
                UserItem to = null;
                for(UserItem user : userList) {
                    if(user.getIp().equals(clientConnection.getInetAddress())) {
                        from = user;
                        break;
                    }
                }
                for(UserItem user : userList) {
                    if(user.getIp().equals(clientConnection.getLocalAddress())) {
                        to = user;
                        break;
                    }
                }
                PTPClient client = new PTPClient(clientConnection, from, to);
                client.addListenerSet(clientListener);
                client.receiveMessageClient();
                this.sendMessage(this, PTPServerMessageCode.SUCCESS, "Connection to client established.");
            }catch(Exception e) {
                this.sendMessage(this, PTPServerMessageCode.CLIENT_CONNECTION_ERROR, e.getMessage());
            }
        }
    }
    
    public void stop() {
        run = false;
        try {
            this.connection.close();
        } catch (Exception e) {
            this.sendMessage(this, PTPServerMessageCode.SERVER_CONNECTION_CLOSE_ERROR, "Could not close the server connection.");
        }
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    public void addServerListener(PTPServerListener listener) {
        if(!serverListener.contains(listener)) {
            serverListener.add(listener);
        }
    }
    
    public void removeServerListener(PTPServerListener listener) {
        if(serverListener.contains(listener)) {
            serverListener.remove(listener);
        }
    }
    
    public void addClientListener(PTPClientListener listener) {
        if(!clientListener.contains(listener)) {
            clientListener.add(listener);
        }
    }
    
    public void removeClientListener(PTPClientListener listener) {
        if(clientListener.contains(listener)) {
            clientListener.remove(listener);
        }
    }

    private void sendMessage(Object sender, PTPServerMessageCode code, String content) {
        PTPServerMessage message = new PTPServerMessage(sender, code, content);
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
