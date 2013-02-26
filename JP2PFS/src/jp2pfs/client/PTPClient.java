/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import jp2pfs.client.PTPClientMessage.PTPClientMessageCode;
;

/**
 *
 * @author karlinsv
 */
public class PTPClient {
    
    private String name;
    private int port;
    private InetAddress ip;
    private String passwort;
    private int id;
    private Socket clientSocket = null;
    private boolean run = true;
    
    
    List<PTPClientListener> clientListener = new ArrayList<PTPClientListener>();
    
    
    public PTPClient(int port, InetAddress ip, String username, String passwort)
    {
        this.name = username;
        this.port = port;
        this.ip = ip;
        this.passwort = passwort;
    }
    
    public PTPClient(Socket connection) {
        clientSocket = connection;
    }
    
    public void setClientName(String name)
    {
        this.name = name;
    }
    
    public void setClientPort(int port)
    {
        this.port = port;
    }
    
    public void setClientIp(InetAddress ip)
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
    public InetAddress getClientIp()
    {
       return ip;
    }
    
    public int setClientPasswort()
    {
        return port;
    }
    
    private void connect()
    {
        if(clientSocket == null) {
            try {
                clientSocket = new Socket(this.ip.getHostAddress().toString(), this.port);
                this.sendMessage(this, PTPClientMessageCode.CONNECTION_SUCCESS, "Connection to server established.");
            } catch(Exception e) {
                this.sendMessage(this, PTPClientMessageCode.CONNECTION_ERROR, "Connection to server could not be established.");
            }
        }
    }
    
    public void receiveMessageClient() {
        if(clientSocket != null) {
            try {
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String message = inputStream.readLine();
                this.sendMessage(this, PTPClientMessageCode.MESSAGE_RECEIVE_SUCCESS, message);
            } catch (IOException ex) {
                this.sendMessage(this, PTPClientMessageCode.MESSAGE_RECEIVE_ERROR, "Could not send the message.");
            }
        }
    }
    
    public void sendMessageClient(String message) {
        
        if(clientSocket == null) {
            this.connect();
        }
        try {
            PrintStream outputStream = new PrintStream(clientSocket.getOutputStream());
            outputStream.println(message);
            this.sendMessage(this, PTPClientMessageCode.MESSAGE_SEND_SUCCESS, message);
        } catch (Exception ex) {
            this.sendMessage(this, PTPClientMessageCode.MESSAGE_SEND_ERROR, "Could not send the message.");
        }
    }
    
    public void stop() {
        run = false;
        try {
            clientSocket.close();
        } catch (Exception ex) {
            sendMessage(this, PTPClientMessageCode.CLOSE_ERROR, "Could not close connection to server.");
        }
    }
    
    public void addListener(PTPClientListener listener) {
        if(!clientListener.contains(listener)) {
           clientListener.add(listener);
        }
    }
    
    public void addListenerSet(List<PTPClientListener> listenerSet) {
        for(PTPClientListener listener : listenerSet) {
            addListener(listener);
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
