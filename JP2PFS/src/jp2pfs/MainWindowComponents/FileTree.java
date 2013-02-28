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

/**
 *
 * @author karlinsv
 */
public class FileTree extends JTree {
    
    private ActionListener treeActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "Datei hinzufügen") {
                addFileElement();
            } else if(e.getActionCommand() == "Verzeichnis hinzufügen") {
                addDirectoryElement();
            } else if(e.getActionCommand() == "Löschen") {
                deleteElement();
            }
        }
        
    };
    
    public FileTree() {
        super();
    }
    
    public void init() {
        FileTreeModel fileTreeModel = (FileTreeModel) this.getModel();
        fileTreeModel.addBranch(null, new Branch("Freigegebene Dokumente", null));
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    callPopupMenu(e.getX(), e.getY());
                }
            }
        });
        this.updateUI();
    }
    
    private void callPopupMenu(int x, int y) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Datei hinzufügen");
        menuItem.addActionListener(getTreeActionListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Verzeichnis hinzufügen");
        menuItem.addActionListener(getTreeActionListener());
        menu.add(menuItem);
        menuItem = new JMenuItem("Löschen");
        menuItem.addActionListener(getTreeActionListener());
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
        
    }

    /**
     * @return the treeActionListener
     */
    private ActionListener getTreeActionListener() {
        return treeActionListener;
    }

    /**
     * @param treeActionListener the treeActionListener to set
     */
    private void setTreeActionListener(ActionListener treeActionListener) {
        this.treeActionListener = treeActionListener;
    }
    
}
