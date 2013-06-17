/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.peering.server;

import jp2pfs.peering.Message;

/**
 *
 * @author karlinsv
 */
public class ServerMessage extends Message {
    
    public enum ServerMessageCode {
        SUCCESS, SERVER_CONNECTION_ERROR, CLIENT_CONNECTION_ERROR, SERVER_CONNECTION_CLOSE_ERROR
    }

    public ServerMessage(Object sender, ServerMessageCode code, String message) {
        super(sender, code, message);
    }
}
