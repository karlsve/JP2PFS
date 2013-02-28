/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.MainWindowComponents;

import java.io.File;

/**
 *
 * @author Middelma
 */
public class DirectoryBranch extends Branch {

    File directory = null;
    
    public DirectoryBranch(File directory, Branch parent) {
        super(directory.getName(), parent);
        this.directory = directory;
    }
    
    public File getDirectory()
    {
        return directory;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
  
}
