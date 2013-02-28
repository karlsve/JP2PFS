/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.MainWindowComponents;

import java.io.File;

/**
 *
 * @author Foertsan
 */
public class FileBranch extends Branch {
    
    File file = null;
   
    public FileBranch(File file, Branch parent) {
        super(file.getName(), parent);
        this.file = file;
    }
    
    @Override
    public boolean getAllowsChildren() {
        return false;
    }
    
    public File getFile(File file) {
        return this.file;
    }
}

