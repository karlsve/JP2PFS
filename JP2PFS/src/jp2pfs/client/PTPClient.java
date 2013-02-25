/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.client;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import jp2pfs.client.PTPClientMessage.PTPClientMessageCode;
;

/**
 *
 * @author karlinsv
 */
public class PTPClient implements Runnable{
    
    private String name;
    private int port;
    private SocketAddress ip;
    private String passwort;
    private int id;
    private Socket clientSocket = null;
    private boolean run = true;
    
    
    List<PTPClientListener> clientListener = new ArrayList<PTPClientListener>();
    
    
    public PTPClient(String name,int port, SocketAddress ip,String passwort)
    {
        this.name = name;
        this.port = port;
        this.ip = ip;
        this.passwort = passwort;
    }
    
    public void setClientName(String name)
    {
        this.name = name;
    }
    
    public void setClientPort(int port)
    {
        this.port = port;
    }
    
    public void setClientIp(SocketAddress ip)
    {
        this.ip = ip;
    }
    
    public void setClientPasswort(String passwort)
    {
        this.passwort = passwort;
    }
    
    public String getClientPasswort()
    {
       return passwort;
    }
    public String getClientName()
    {
        return name;
    }
    public SocketAddress getClientIp()
    {
       return ip;
    }
    
    public int setClientPasswort()
    {
        return port;
    }

    
    
    private void init()
    {
        try {
            clientSocket = new Socket(this.ip.toString(), this.port);
            this.sendMessage(this, PTPClientMessageCode.SUCCESS, "Connection to server established.");
        } catch(Exception e) {
            this.sendMessage(this, PTPClientMessageCode.SERVER_CONNECTION_ERROR, "Connection to server could not be established.");
        }   
            
    }
    
    @Override
    public void run() {
        this.init();
    }
    
    private void connect(SocketAddress addr)
    {   
        connect(getClientIp());
    }
    
    public void addListener(PTPClientListener listener) {
        if(!clientListener.contains(listener)) {
           clientListener.add(listener);
        }
    }
    
    public void removeListener(PTPClientListener listener) {
        if(clientListener.contains(listener)) {
            clientListener.remove(listener);
        }
    }
    
    

    private void sendMessage(Object sender, PTPClientMessageCode code, String content) {
        PTPClientMessage message = new PTPClientMessage(sender, code, content);
        for(PTPClientListener listener : clientListener) {
            if(listener != null)
                listener.onMessage(message);
        }
    }
    
}
