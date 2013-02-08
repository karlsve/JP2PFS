/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.server;

import java.sql.SQLException;
import jp2pfs.global.DatabaseHandling;

/**
 *
 * @author karlinsv
 */
public class PTPServerDatabase extends DatabaseHandling {

    @Override
    public boolean connect(String filename, String user, String password) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void disconnect() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
