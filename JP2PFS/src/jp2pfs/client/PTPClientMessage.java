/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.client;

/**
 *
 * @author Foertsan
 */
public class PTPClientMessage {
    
    public enum PTPClientMessageCode {
        SUCCESS, SERVER_CONNECTION_ERROR, CLIENT_CONNECTION_ERROR, SERVER_CONNECTION_CLOSE_ERROR
    }
    
    private Object sender = null;
    private PTPClientMessageCode messageCode;
    private String message = "No message.";

    public PTPClientMessage(Object sender, PTPClientMessageCode messageCode, String message) {
        this.sender = sender;
        this.messageCode = messageCode;
        this.message = message;
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
