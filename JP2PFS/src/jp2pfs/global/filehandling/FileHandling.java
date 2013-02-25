/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.global.filehandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karlinsv
 */
public class FileHandling {
    
    List<FileHandlingListener> listener = new ArrayList<FileHandlingListener>();
    
    public void addListener(FileHandlingListener listener) {
        if(!this.listener.contains(listener)) {
            this.listener.add(listener);
        }
    }
    
    public void removeListener(FileHandlingListener listener) {
        if(this.listener.contains(listener)) {
            this.listener.remove(listener);
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
            
            return false;
        }
        return true;
    }
    
}
