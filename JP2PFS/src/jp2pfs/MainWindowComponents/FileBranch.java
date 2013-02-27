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
   
    public FileBranch(File file, String name, Branch parent) {
        super(name, parent);
    }
    
    File file = null;
    
    @Override
    public boolean getAllowsChildren() {
        return false;
    }
    
    private File getFile(File file)
    {   
        return this.file;
    }
}

