/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.server;

/**
 *
 * @author karlinsv
 */
public interface PTPServerListener {
    public void onMessage(PTPServerMessage message);
}
