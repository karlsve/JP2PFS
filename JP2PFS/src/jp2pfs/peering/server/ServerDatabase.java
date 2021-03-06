/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.peering.server;

import java.sql.SQLException;
import jp2pfs.global.DatabaseHandling;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author karlinsv
 */
public class ServerDatabase extends DatabaseHandling {
    
    Connection connection=null;
    String driverName="ocacle.jdbc.driver.OracleDriver";
    
    private String user;
    private String password;
    private String serverName;
    private String portNummer;
    private String sid;
    public String url="jbc:oracle:thin:@"+serverName+":"+portNummer+":"+sid;
    
    public ServerDatabase(String servername, String user, String password,String portnummer,String sid)
    {
        this.serverName = servername;
        this.sid=sid;
        this.portNummer=portnummer;
        this.user=user;
        this.password=password;
    }
    @Override
    public boolean connect() {
        try
        {
            Class.forName(driverName);
            connection=DriverManager.getConnection(url,user,password);
        }catch(Exception e)
        {
            return false;
        }
        return true;
    }
    @Override
    public void disconnect() {
          connection=null;
    }
    
}
