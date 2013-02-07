/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.server;

/**
 *
 * @author karlinsv
 */
public class PTPServerError {

    private Object sender = null;
    private int code = 0;
    private String message = "No message.";

    public PTPServerError(Object sender, int code, String message) {
        this.sender = sender;
        this.code = code;
        this.message = message;
    }
    
    public Object getSender() {
        return sender;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
}
