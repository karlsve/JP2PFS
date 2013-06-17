/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.peering;

/**
 *
 * @author karlsve
 */
public abstract interface Listener {
    public void getMessage(Message message);
}
