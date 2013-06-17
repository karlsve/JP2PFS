/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.peering.server;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import jp2pfs.peering.client.Client;
import jp2pfs.global.Configuration;
import jp2pfs.peering.server.ServerMessage.ServerMessageCode;
import net.jxta.peergroup.PeerGroup;
import net.jxta.pipe.InputPipe;
import net.jxta.pipe.PipeMsgEvent;
import net.jxta.pipe.PipeMsgListener;
import net.jxta.pipe.PipeService;
import net.jxta.platform.NetworkManager;
import net.jxta.protocol.PipeAdvertisement;

/**
 *
 * @author karlinsv
 */
public class Server implements PipeMsgListener {
    
    private Configuration configuration = null;
    
    transient NetworkManager networkManager = null;
    private PeerGroup peerGroup = null;
    private PipeService pipeService = null;
    private PipeAdvertisement pipeAdvertisement = null;
    private InputPipe inputPipe = null;
    
    List<ServerListener> serverListener = new ArrayList<>();
    
    public Server(Configuration configuration) throws Exception {
        this.configuration = configuration;
        this.init();
        this.start();
    }

    private void init() throws Exception {
        this.networkManager = new NetworkManager(NetworkManager.ConfigMode.RENDEZVOUS, (String)this.configuration.get("username"), (URI)this.configuration.get("server"));
        this.networkManager.startNetwork();
        this.peerGroup = this.networkManager.getNetPeerGroup();
        this.pipeService = this.peerGroup.getPipeService();
        this.pipeAdvertisement = Client.getPipeAdvertisement(this.configuration);
    }
    
    private void start() throws Exception {
        inputPipe = pipeService.createInputPipe(pipeAdvertisement, this);
    }
    
    public void stop() {
        inputPipe.close();
        networkManager.stopNetwork();
    }
    
    public void addServerListener(ServerListener listener) {
        if(!serverListener.contains(listener)) {
            serverListener.add(listener);
        }
    }
    
    public void removeServerListener(ServerListener listener) {
        if(serverListener.contains(listener)) {
            serverListener.remove(listener);
        }
    }

    private void sendMessage(Object sender, ServerMessageCode code, String content) {
        ServerMessage message = new ServerMessage(sender, code, content);
        for(ServerListener listener : serverListener) {
            if(listener != null) {
                listener.onMessage(message);
            }
        }
    }

    @Override
    public void pipeMsgEvent(PipeMsgEvent pme) {
        for(ServerListener listener : serverListener) {
            
        }
    }
}
