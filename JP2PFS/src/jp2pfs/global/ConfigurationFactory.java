/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.global;

import java.net.URI;

/**
 *
 * @author karlsve
 */
class ConfigurationFactory {
    
    public static Object deSerialize(String key, String content) throws Exception {
        switch(key) {
            case "server":
                return new URI(content);
            case "peergroup":
                return new URI(content);
            default:
                return content;
        }
    }
    
    public static String serialize(Object content) {
        if(content instanceof URI) {
            return ((URI)content).toString();
        } else {
            return content.toString();
        }
    }
    
}
