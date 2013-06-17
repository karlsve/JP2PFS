/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.ui.main;

import java.io.File;

/**
 *
 * @author Foertsan
 */
public class FileBranch extends Branch {
    
    private File file = null;
   
    public FileBranch(File file, Branch parent) {
        super(file.getName(), parent);
        this.file = file;
    }
    
    @Override
    public boolean getAllowsChildren() {
        return false;
    }
    
    public File getFile() {
        return this.file;
    }
}

