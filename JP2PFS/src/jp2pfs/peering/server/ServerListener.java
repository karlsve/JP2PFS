/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.peering.server;

/**
 *
 * @author karlinsv
 */
public interface ServerListener {
    public void onMessage(ServerMessage message);
}
