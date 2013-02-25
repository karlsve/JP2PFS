/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.global;
import java.sql.*;

/**
 *
 * @author karlinsv
 */
public abstract class DatabaseHandling {
    protected Connection con;
    
    
    abstract public boolean connect();
    abstract public void disconnect();
    
        
    
}

