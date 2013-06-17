/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.peering.client;

/**
 *
 * @author Foertsan
 */
public interface ClientListener {
    public void onMessage(ClientMessage message);
}
