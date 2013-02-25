/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.client;

/**
 *
 * @author Foertsan
 */
public interface PTPClientListener {
    public void onMessage(String message);
    public void onError(PTPClientMessage error);
}
