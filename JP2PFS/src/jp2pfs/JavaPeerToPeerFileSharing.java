/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs;

import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import jp2pfs.server.PTPServer;
import jp2pfs.server.PTPServerError;
import jp2pfs.server.PTPServerListener;

/**
 *
 * @author karlinsv
 */
public class JavaPeerToPeerFileSharing implements PTPServerListener {
    
    JFrame frame = null;
    String title = "PTP";
    int width = 400;
    int height = 400;
    
    TextArea textarea = null;
    
    int serverport = 2100;
    PTPServer server = null;
    
    public static void main(String[] args) {
        new JavaPeerToPeerFileSharing();
    }
    
    public JavaPeerToPeerFileSharing() {
        initLayout();
        startServer();
    }

    private void initLayout() {
        frame = new JFrame(title);
        textarea = new TextArea(5, 40);
        textarea.setEditable(false);
        frame.setSize(width, height);
        frame.add(textarea);
        frame.addWindowListener(new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent e) {
		onWindowClose();
	    }
	});
        frame.setVisible(true);
    }

    private void onWindowClose() {
        if(frame != null) {
            frame.setVisible(false);
            frame.dispose();
        }
        stopServer();
    }
    
    private void startServer() {
        if(server == null) {
            server = new PTPServer(serverport);
            server.addListener(this);
        }
        new Thread(server).start();
    }

    private void stopServer() {
        if(server != null) {
            server.stop();
        }
    }

    @Override
    public void onError(PTPServerError error) {
        if(frame.isVisible()) {
            textarea.append(error.getMessage() + "\r\n");
        } else {
            System.out.println(error.getMessage());
        }
    }

    @Override
    public void onMessage(String message) {
        if(frame.isVisible()) {
            textarea.append(message);
        }
    }
}
