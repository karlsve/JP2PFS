/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.peering;

/**
 *
 * @author karlsve
 */
public abstract class Message {
    
    private Object sender = null;
    private Enum code = null;
    private Object message = "";
    
    public Message(Object sender, Enum code, Object message) {
        this.sender = sender;
        this.code = code;
        this.message = message;
    }
    
    public Object getSender() {
        return this.sender;
    }
    
    public Enum getCode() {
        return this.code;
    }
    
    public Object getMessage() {
        return this.message;
    }
    
}
