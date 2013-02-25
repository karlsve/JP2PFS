/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.client;

import java.sql.SQLException;
import jp2pfs.global.DatabaseHandling;

/**
 *
 * @author karlinsv
 */
public class PTPClientDatabase extends DatabaseHandling {

    @Override
    public boolean connect() {
        return false;
    }

    @Override
    public void disconnect() {
       
    }
    
}
