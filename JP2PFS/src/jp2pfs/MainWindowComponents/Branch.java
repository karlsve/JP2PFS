/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.MainWindowComponents;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;

/**
 *
 * @author karlinsv
 */
public class Branch implements TreeNode {
    
    int index = -1;
    List<Branch> children = new ArrayList<Branch>();
    
    public void add(Branch child) {
        children.add(child);
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TreeNode getParent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getIndex(TreeNode node) {
        return this.index;
    }

    @Override
    public boolean getAllowsChildren() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isLeaf() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Enumeration children() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
