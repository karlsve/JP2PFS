/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.client;

import java.io.*;
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
    private String downloadPath = "C:\\Documents and Settings\\All Users\\Downloads\\";
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
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                byte type = inputStream.readByte();
                switch(type) {
                    case 1:
                        receiveTextMessageClient(inputStream);
                        break;
                    case 2:
                        receiveFileMessageClient(inputStream);
                        break;
                    case 3:
                        receiveFileMessageRequestClient(inputStream);
                    case 4:
                        receiveFileListMessageClient(inputStream);
                }
            } catch (Exception ex) {
                this.sendDebugMessage(this, PTPClientMessageCode.MESSAGE_RECEIVE_ERROR, "Could not receive the message.");
            }
        }
    }

    private void receiveTextMessageClient(ObjectInputStream inputStream) throws IOException {
        String message = "";
        message = inputStream.readUTF();
        this.sendMessage(this, PTPClientMessageCode.MESSAGE_RECEIVE_SUCCESS, message);
    }

    private void receiveFileMessageClient(ObjectInputStream inputStream) throws IOException {
        int bufferSize = 150000;
        int bytesRead = 0;
        
        FileOutputStream outputStream = new FileOutputStream(downloadPath+inputStream.readUTF());

        byte[] buffer = new byte[bufferSize];

        while((bytesRead = inputStream.read(buffer)) >= 0) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
            
        this.sendMessage(this, PTPClientMessageCode.FILE_RECEIVE_SUCCESS, "File received.");
    }
    
    private void receiveFileListMessageClient(ObjectInputStream inputStream) throws Exception {
        File file = (File)inputStream.readObject();
    }
    
    private void receiveFileMessageRequestClient(ObjectInputStream inputStream) throws Exception {
        File file = (File)inputStream.readObject();
        PTPClient client = new PTPClient(to, from);
        client.sendFileMessageClient(file);
    }
    
    public void sendFileMessageClient(File file) {
        if(clientSocket == null) {
            this.connect();
        }
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeByte(2);
            outputStream.writeUTF(file.getName());
            int bufferSize = 150000;
        
            FileInputStream fileStream = new FileInputStream(file);
            
            byte[] buffer = new byte[bufferSize];
            for(int i = 0; i<file.length(); i+=bufferSize) {
                fileStream.read(buffer);
                outputStream.write(buffer);
            }
            fileStream.close();
            outputStream.flush();
            this.sendMessage(this, PTPClientMessageCode.FILE_SEND_SUCCESS, "File sent.");
        } catch (Exception ex) {
            this.sendDebugMessage(this, PTPClientMessageCode.FILE_SEND_ERROR, "Could not send the message.");
        }
    }
    
    public void requestFileMessageClient(File file) {
        if(clientSocket == null) {
            this.connect();
        }
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeByte(3);
            outputStream.writeObject(file);
            outputStream.flush();
            this.sendMessage(this, PTPClientMessageCode.FILE_SEND_SUCCESS, "File sent.");
        } catch (Exception ex) {
            this.sendDebugMessage(this, PTPClientMessageCode.FILE_SEND_ERROR, "Could not send the message.");
        }
    }
    
    public void requestFileListClient() {
        if(clientSocket == null) {
            this.connect();
        }
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeByte(5);
            outputStream.flush();
        } catch(Exception e) {
            System.out.println("Could not request fileList.");
        }
    }
    
    public void sendFileListMessageClient(Object list) {
        
        if(clientSocket == null) {
            this.connect();
        }
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeByte(4);
            outputStream.writeObject(list);
            outputStream.flush();
            this.sendMessage(this, PTPClientMessageCode.FILE_SEND_SUCCESS, "File sent.");
        } catch (Exception ex) {
            this.sendDebugMessage(this, PTPClientMessageCode.FILE_SEND_ERROR, "Could not send the message.");
        }
    }
    
    public void sendTextMessageClient(String message) {
        if(clientSocket == null) {
            this.connect();
        }
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeByte(1);
            outputStream.writeUTF(message);
            outputStream.flush();
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
