/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.MainWindowComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;
import jp2pfs.client.PTPClient;

/**
 *
 * @author karlinsv
 */
public class FileTree extends JTree {
    
    UserItem to = null;
    UserItem from = null;
    
    private ActionListener treeUserActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if("Datei herunterladen".equals(e.getActionCommand())) {
                getFile();
            }
        }
        
    };
    
    private ActionListener treeHomeActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Datei hinzufügen":
                    addFileElement();
                    break;
                case "Verzeichnis hinzufügen":
                    addDirectoryElement();
                    break;
                case "Löschen":
                    deleteElement();
                    break;
            }
        }
        
    };
    
    public FileTree() {
        super();
    }
    
    public void initHome() {
        FileTreeModel fileTreeModel = (FileTreeModel) this.getModel();
        fileTreeModel.addBranch(null, new Branch("Freigegebene Dokumente", null));
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    callHomePopupMenu(e.getX(), e.getY());
                }
            }
        });
        this.updateUI();
    }
    
    public void initUser(UserItem from, UserItem to) {
        this.from = from;
        this.to = to;
        requestModel();
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    callUserPopupMenu(e.getX(), e.getY());
                }
            }
        });
        this.updateUI();
    }
    
    private void requestModel() {
        PTPClient client = new PTPClient(from, to);
        client.requestFileListClient();
    }
    
    private void callHomePopupMenu(int x, int y) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Datei hinzufügen");
        menuItem.addActionListener(treeHomeActionListener);
        menu.add(menuItem);
        menuItem = new JMenuItem("Verzeichnis hinzufügen");
        menuItem.addActionListener(treeHomeActionListener);
        menu.add(menuItem);
        menuItem = new JMenuItem("Löschen");
        menuItem.addActionListener(treeHomeActionListener);
        menu.add(menuItem);
        menu.show(this, x, y);
    }
    
    private void callUserPopupMenu(int x, int y) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Datei herunterladen");
        menuItem.addActionListener(treeUserActionListener);
        menu.add(menuItem);
        menu.show(this, x, y);
    }
    
    private void addFileElement() {
        if(this.getSelectionPath() != null) {
            FileTreeModel fileTreeModel = (FileTreeModel) this.getModel();
            Branch element = (Branch)this.getSelectionPath().getLastPathComponent();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(fileChooser.showDialog(this, "Datei freigeben") == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                fileTreeModel.addBranch(element, new FileBranch(file, element));
                this.updateUI();
            }
        }
    }
    
    private void addDirectoryElement() {
        if(this.getSelectionPath() != null) {
            FileTreeModel fileTreeModel = (FileTreeModel) this.getModel();
            Branch element = (Branch)this.getSelectionPath().getLastPathComponent();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(fileChooser.showDialog(this, "Verzeichnis freigeben") == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                fileTreeModel.addBranch(element, new DirectoryBranch(file, element));
                this.updateUI();
            }
        }
    }
    
    private void deleteElement() {
        if(this.getSelectionPath() != null) {
            FileTreeModel fileTreeModel = (FileTreeModel) this.getModel();
            Branch element = (Branch) this.getSelectionPath().getLastPathComponent();
            if(!element.equals(fileTreeModel.getRoot())) {
                ((Branch)element.getParent()).removeChild(element);
                this.updateUI();
            }
        }
    }

    private void getFile() {
        if(this.getSelectionPath() != null) {
            FileBranch element = (FileBranch)this.getSelectionPath().getLastPathComponent();
            
            PTPClient client = new PTPClient(from, to);
            client.requestFileMessageClient(element.getFile());
        }
    }
    
}
