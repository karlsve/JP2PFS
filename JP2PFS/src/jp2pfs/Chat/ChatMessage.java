/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.Chat;

import jp2pfs.MainWindowComponents.UserItem;

/**
 *
 * @author karlinsv
 */
public class ChatMessage {
    
    private UserItem from;
    private UserItem to;
    private String content;

    public String getContent() {
        return content;
    }
    
    public UserItem getTo() {
        return to;
    }

    public UserItem getFrom() {
        return from;
    }
    
    public ChatMessage(UserItem from, UserItem to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }
            
}
