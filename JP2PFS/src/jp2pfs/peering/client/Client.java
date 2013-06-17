/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.peering.client;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import jp2pfs.ui.main.UserItem;
import jp2pfs.global.Configuration;
import jp2pfs.peering.client.ClientMessage.ClientMessageCode;
import net.jxta.document.AdvertisementFactory;
import net.jxta.id.IDFactory;
import net.jxta.pipe.PipeID;
import net.jxta.pipe.PipeService;
import net.jxta.protocol.PipeAdvertisement;
;

/**
 *
 * @author karlinsv
 */
public class Client {

    public static PipeAdvertisement getPipeAdvertisement(Configuration configuration) throws Exception {
        PipeID pipeID = null;
        pipeID = (PipeID) IDFactory.fromURI((URI)configuration.get("peergroup"));
        PipeAdvertisement advertisement = (PipeAdvertisement) AdvertisementFactory.newAdvertisement(PipeAdvertisement.getAdvertisementType());
        advertisement.setPipeID(pipeID);
        advertisement.setType(PipeService.UnicastSecureType);
        advertisement.setName((String)configuration.get("username"));
        return advertisement;
    }
    
    private Configuration configuration = null;
    private List<ClientListener> clientListener = new ArrayList<>();
    
    public Client(Configuration configuration) {
        this.configuration = configuration;
    }
    
    public void addListener(ClientListener listener) {
        if(!clientListener.contains(listener)) {
           clientListener.add(listener);
        }
    }
    
    public void addListenerSet(List<ClientListener> listenerSet) {
        for(ClientListener listener : listenerSet) {
            addListener(listener);
        }
    }
    
    public void removeListener(ClientListener listener) {
        if(clientListener.contains(listener)) {
            clientListener.remove(listener);
        }
    }

    private void sendDebugMessage(Object sender, ClientMessageCode code, Object content) {
        ClientMessage message = new ClientMessage(sender, code, content);
        for(ClientListener listener : clientListener) {
            if(listener != null) {
                listener.onMessage(message);
            }
        }
    }

    private void sendMessage(Object sender, ClientMessageCode code, Object content) {
        ClientMessage message = new ClientMessage(sender, code, content);
        for(ClientListener listener : clientListener) {
            if(listener != null) {
                listener.onMessage(message);
            }
        }
    }
    
}
