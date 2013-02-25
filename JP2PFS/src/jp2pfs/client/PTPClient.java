/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public PTPClient(Socket connection) {
        
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
            this.sendMessage(this, PTPClientMessageCode.CONNECTION_ERROR, "Connection to server could not be established.");
        }
    }
    
    private void receiveMessageClient() {
        try {
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message = inputStream.readLine();
            
            this.sendMessage(this, PTPClientMessageCode.MESSAGE_RECEIVE_SUCCESS, message);
            
        } catch (IOException ex) {
            this.sendMessage(this, PTPClientMessageCode.MESSAGE_RECEIVE_ERROR, "Could not send the message.");
        }
    }
    
    public void sendMessageClient(String message) {
        try {
            PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());
            outputStream.println(message);
        } catch (IOException ex) {
            this.sendMessage(this, PTPClientMessageCode.MESSAGE_SEND_ERROR, "Could not send the message.");
        }
    }
    
    @Override
    public void run() {
        this.init();
    }
    
    private void connect(SocketAddress addr)
    {   
        try {
            clientSocket.connect(this.ip);
        } catch (Exception ex) {
            sendMessage(this, PTPClientMessageCode.CONNECTION_ERROR, "Could not establish connection to client.");
        }
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
