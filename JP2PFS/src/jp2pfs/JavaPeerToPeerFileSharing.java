/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs;

import jp2pfs.MainWindowComponents.LoginWindow;
import jp2pfs.MainWindowComponents.MainWindow;

/**
 *
 * @author karlinsv
 */
public class JavaPeerToPeerFileSharing {
    
    String title = "PTP";
    MainWindow mainWindow = null;
    
    public static void main(String[] args) {
        new JavaPeerToPeerFileSharing();
    }
    
    public JavaPeerToPeerFileSharing() {
        initLoginWindow();
        //initMainWindow();
    }

    private void initMainWindow() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                mainWindow = new MainWindow();
                mainWindow.setTitle(title);
                mainWindow.setVisible(true);
            }
        });
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
