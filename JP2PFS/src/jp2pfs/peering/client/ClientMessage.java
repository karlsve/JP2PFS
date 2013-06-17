/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.peering.client;

import jp2pfs.peering.Message;

/**
 *
 * @author Foertsan
 */
public class ClientMessage extends Message {
    
    public enum ClientMessageCode {
        CONNECTION_SUCCESS, CONNECTION_ERROR, CLOSE_ERROR, MESSAGE_RECEIVE_ERROR, MESSAGE_RECEIVE_SUCCESS, MESSAGE_SEND_ERROR, MESSAGE_SEND_SUCCESS, FILE_LIST_SEND_SUCCESS, FILE_LIST_SEND_ERROR, FILE_LIST_RECEIVE_SUCCESS, FILE_LIST_RECEIVE_ERROR, FILE_LIST_REQUEST, FILE_SEND_SUCCESS, FILE_SEND_ERROR, FILE_RECEIVE_SUCCESS, USER_LIST_RECEIVE_SUCCESS, USER_LIST_RECEIVE_ERROR
    }

    public ClientMessage(Object sender, ClientMessageCode code, Object message) {
        super(sender, code, message);
    }
}
