/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.client;

import java.net.InetAddress;
import jp2pfs.MainWindowComponents.UserItem;

/**
 *
 * @author Foertsan
 */
public class PTPClientMessage {
    
    public enum PTPClientMessageCode {
        CONNECTION_SUCCESS, CONNECTION_ERROR, CLOSE_ERROR, MESSAGE_RECEIVE_ERROR, MESSAGE_RECEIVE_SUCCESS, MESSAGE_SEND_ERROR, MESSAGE_SEND_SUCCESS
    }
    
    private Object sender = null;
    private PTPClientMessageCode messageCode;
    private String message = "No message.";
    private UserItem from = null;
    private UserItem to = null;

    public UserItem getFrom() {
        return from;
    }

    public UserItem getTo() {
        return to;
    }

    public PTPClientMessage(Object sender, PTPClientMessageCode messageCode, String message) {
        this.sender = sender;
        this.messageCode = messageCode;
        this.message = message;
    }
    
    public PTPClientMessage(Object sender, PTPClientMessageCode messageCode, String message, UserItem from, UserItem to) {
        this.sender = sender;
        this.messageCode = messageCode;
        this.message = message;
        this.from = from;
        this.to = to;
    }
    public Object getSender() {
        return sender;
    }
    
    public PTPClientMessageCode getMessageCode() {
        return messageCode;
    }
    
    public String getMessage() {
        return message;
    }
}
