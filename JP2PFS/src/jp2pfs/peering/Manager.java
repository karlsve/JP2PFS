/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.peering;

import jp2pfs.global.Configuration;
import jp2pfs.peering.server.Server;

/**
 *
 * @author karlsve
 */
public class Manager implements Listener {
    
    private Configuration configuration = null;
    private Server server = null;
    
    public Manager(Configuration configuration) throws Exception {
        this.configuration = configuration;
        this.startServer();
    }

    private void startServer() throws Exception {
        this.server = new Server(this.configuration);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Server getServer() {
        return server;
    }

    @Override
    public void getMessage(Message message) {
    }
    
}
