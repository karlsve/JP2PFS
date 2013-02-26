/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.Chat;

import java.net.InetAddress;

/**
 *
 * @author karlinsv
 */
public class ChatMessage {
    
    private InetAddress from;
    private String content;

    public String getContent() {
        return content;
    }

    public InetAddress getFrom() {
        return from;
    }
    
    public ChatMessage(InetAddress from, String content) {
        this.from = from;
        this.content = content;
    }
            
}
