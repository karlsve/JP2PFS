/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.global.filehandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import jp2pfs.global.filehandling.FileHandlingMessage.FileHandlingMessageCode;

/**
 *
 * @author karlinsv
 */
public class FileHandling {
    
    List<FileHandlingListener> fileHandlingListener = new ArrayList<FileHandlingListener>();
    
    public void addListener(FileHandlingListener listener) {
        if(!this.fileHandlingListener.contains(listener)) {
            this.fileHandlingListener.add(listener);
        }
    }
    
    public void removeListener(FileHandlingListener listener) {
        if(this.fileHandlingListener.contains(listener)) {
            this.fileHandlingListener.remove(listener);
        }
    }
    
    public boolean sendFile(Socket connection, File file) {
        ObjectOutputStream outputStream;
        int bufferSize = 150000;
        
        try {
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            outputStream.flush();
        
            FileInputStream fileStream = new FileInputStream(file);
            outputStream.writeObject("FILE_SENDING_STARTING|"+file.getName()+"|"+file.length());
            
            byte[] buffer = new byte[bufferSize];
            for(int i = 0; i<file.length(); i+=bufferSize) {
                fileStream.read(buffer);
                outputStream.write(buffer);
            }
            outputStream.writeObject("FILE_SENDING_DONE");
            fileStream.close();
            
        } catch(Exception e) {
            sendMessage(this, FileHandlingMessageCode.FILE_SEND_ERROR, "Could not send file.");
            return false;
        }
        sendMessage(this, FileHandlingMessageCode.SUCCESS, "File sent.");
        return true;
    }
    
    public boolean receiveFile(Socket connection, String filename) {
        int bufferSize = 150000;
        int bytesRead = 0;
        int counter = 0;
        
        try {
            FileOutputStream outputStream = new FileOutputStream(filename);
            InputStream inputStream = connection.getInputStream();
            
            byte[] buffer = new byte[bufferSize];
            
            for(int i = 0; i<0;) {
                bytesRead = inputStream.read(buffer);
                
                if(bytesRead >= 0) {
                    outputStream.write(buffer, 0, bytesRead);
                    counter += bytesRead;
                }
                
                if(bytesRead < 1024) {
                    outputStream.flush();
                    break;
                }
            }
            
            outputStream.close();
            sendMessage(this, FileHandlingMessageCode.SUCCESS, "File received.");
            
        } catch(Exception e) {
            sendMessage(this, FileHandlingMessageCode.FILE_RECEIVE_ERROR, "Could not receive file.");
            return false;
        }
        sendMessage(this, FileHandlingMessageCode.SUCCESS, "File received.");
        return true;
    }

    private void sendMessage(FileHandling sender, FileHandlingMessageCode code, String content) {
        FileHandlingMessage message = new FileHandlingMessage(sender, code, content);
        for(FileHandlingListener listener : fileHandlingListener) {
            if(listener != null)
                listener.onMessage(message);
        }
    }
    
}
