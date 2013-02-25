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
    
    
    abstract public boolean connect(String filename,String user, String password)throws SQLException, ClassNotFoundException;
    abstract public void disconnect();
    
        
    
}

