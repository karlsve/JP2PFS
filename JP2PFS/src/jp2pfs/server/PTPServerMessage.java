/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.server;

/**
 *
 * @author karlinsv
 */
public class PTPServerMessage {
    
    public enum PTPServerMessageCode {
        SUCCESS, SERVER_CONNECTION_ERROR, CLIENT_CONNECTION_ERROR, SERVER_CONNECTION_CLOSE_ERROR
    }

    private Object sender = null;
    private PTPServerMessageCode messageCode;
    private String message = "No message.";

    public PTPServerMessage(Object sender, PTPServerMessageCode messageCode, String message) {
        this.sender = sender;
        this.messageCode = messageCode;
        this.message = message;
    }
    
    public Object getSender() {
        return sender;
    }
    
    public PTPServerMessageCode getMessageCode() {
        return messageCode;
    }
    
    public String getMessage() {
        return message;
    }
}
