/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import jp2pfs.MainWindowComponents.UserItem;
import jp2pfs.client.PTPClientMessage.PTPClientMessageCode;
;

/**
 *
 * @author karlinsv
 */
public class PTPClient {
    
    private UserItem from = null;
    private UserItem to = null;
    private Socket clientSocket = null;
    List<PTPClientListener> clientListener = new ArrayList<PTPClientListener>();
    
    
    public PTPClient(UserItem from, UserItem to)
    {
        this.from = from;
        this.to = to;
    }
    
    public PTPClient(Socket connection, UserItem from, UserItem to) {
        clientSocket = connection;
        this.from = from;
        this.to = to;
    }
    
    private void connect()
    {
        if(clientSocket == null) {
            try {
                clientSocket = new Socket(this.to.getIp().getHostAddress().toString(), this.to.getPort());
                this.sendDebugMessage(this, PTPClientMessageCode.CONNECTION_SUCCESS, "Connection to server established.");
            } catch(Exception e) {
                this.sendDebugMessage(this, PTPClientMessageCode.CONNECTION_ERROR, "Connection to server could not be established.");
            }
        }
    }
    
    public void receiveMessageClient() {
        if(clientSocket != null) {
            try {
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                byte type = inputStream.readByte();
                switch(type) {
                    case 1:
                        receiveTextMessageClient(inputStream);
                        break;
                }
            } catch (Exception ex) {
                this.sendDebugMessage(this, PTPClientMessageCode.MESSAGE_RECEIVE_ERROR, "Could not receive the message.");
            }
        }
    }

    private void receiveTextMessageClient(DataInputStream inputStream) throws IOException {
        String message = "";
        message = inputStream.readUTF();
        this.sendMessage(this, PTPClientMessageCode.MESSAGE_RECEIVE_SUCCESS, message);
    }
    
    public void sendTextMessageClient(String message) {
        
        if(clientSocket == null) {
            this.connect();
        }
        try {
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            outputStream.writeByte(1);
            outputStream.writeUTF(message);
            this.sendMessage(this, PTPClientMessageCode.MESSAGE_SEND_SUCCESS, message);
        } catch (Exception ex) {
            this.sendDebugMessage(this, PTPClientMessageCode.MESSAGE_SEND_ERROR, "Could not send the message.");
        }
    }
    
    public void stop() {
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

    private void sendDebugMessage(Object sender, PTPClientMessageCode code, String content) {
        PTPClientMessage message = new PTPClientMessage(sender, code, content);
        for(PTPClientListener listener : clientListener) {
            if(listener != null)
                listener.onMessage(message);
        }
    }

    private void sendMessage(Object sender, PTPClientMessageCode code, String content) {
        PTPClientMessage message = new PTPClientMessage(sender, code, content, from, to);
        for(PTPClientListener listener : clientListener) {
            if(listener != null)
                listener.onMessage(message);
        }
    }
    
}
