/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs;

import jp2pfs.ui.login.LoginWindow;

/**
 *
 * @author karlinsv
 */
public class JavaPeerToPeerFileSharing {
    
    public static void main(String[] args) {
        new JavaPeerToPeerFileSharing();
    }
    
    public JavaPeerToPeerFileSharing() {
        initLoginWindow();
    }

    private void initLoginWindow() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new LoginWindow();
            }
        });
    }
}
