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
    
    public DirectoryBranch(File directory, String name, Branch parent) {
        super(name, parent);
        this.directory = directory;
    }
    
    public File getdirectory()
    {
        return directory;
    }
  
}
