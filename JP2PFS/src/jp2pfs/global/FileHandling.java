/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.global;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author karlinsv
 */
public class FileHandling {
    
    public boolean sendFile(Socket connection, File file) {
        ObjectOutputStream outputStream;
        int bufferSize = 150000;
        
        try {
            outputStream = new ObjectOutputStream(connection.getOutputStream());
            outputStream.flush();
        
            FileInputStream fileStream = new FileInputStream(file);
            outputStream.writeObject("SENDING_FILE|"+file.getName()+"|"+file.length());
            
            byte[] buffer = new byte[bufferSize];
            for(int i = 0; i<file.length(); i++) {
                
            }
            
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    
}
