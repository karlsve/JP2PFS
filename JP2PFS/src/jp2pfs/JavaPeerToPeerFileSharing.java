/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs;

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
        initMainWindow();
    }

    private void initMainWindow() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                mainWindow = new MainWindow();
                mainWindow.setTitle(title);
                mainWindow.setVisible(true);
            }
        });
    }
}
