/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.global;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author karlinsv
 */
public class IPHandling {
    
    public static List<InetAddress> getLocalIpList() {
        List<InetAddress> ip = new ArrayList<InetAddress>();
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while(e.hasMoreElements())
            {
                NetworkInterface n=(NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while(ee.hasMoreElements())
                {
                    ip.add((InetAddress) ee.nextElement());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ip;
    }
}
