/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.client;

import java.net.SocketAddress;

/**
 *
 * @author karlinsv
 */
public class PTPClient extends Thread {
    
    private String name;
    private int port;
    private SocketAddress ip;
    private String passwort;
    private int id;
    
    public PTPClient(String name,int port, SocketAddress ip,String passwort)
    {
        this.name = name;
        this.port = port;
        this.ip = ip;
        this.passwort = passwort;
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
    
    
    
    
}
