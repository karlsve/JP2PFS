/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.MainWindowComponents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author karlinsv
 */
public class FileTreeModel implements TreeModel {
    
    Branch root = null;
    
    List<TreeModelListener> listener = new ArrayList<TreeModelListener>();
    
    public void addBranch(Branch parent, Branch branch) {
        if(parent != null) {
            parent.add(branch);
        } else {
            root = branch;
        }
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        if(parent instanceof Branch) {
            Branch parentBranch = (Branch) parent;
            return parentBranch.getChildAt(index);
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if(parent instanceof Branch) {
            Branch parentBranch = (Branch) parent;
            return parentBranch.getChildCount();
        }
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        if(node instanceof Branch) {
            Branch nodeBranch = (Branch) node;
            return nodeBranch.isLeaf();
        }
        return false;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        Branch object = (Branch)path.getLastPathComponent();
        object = (Branch)newValue;
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if(parent instanceof Branch) {
            Branch parentBranch = (Branch) parent;
            return parentBranch.getIndex((Branch)child);
        }
        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        if(!this.listener.contains(l))
            this.listener.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        if(this.listener.contains(l))
            this.listener.remove(l);
    }
    
    public void fireTreeNodesInserted(TreeModelEvent e) {
        for(TreeModelListener current : listener) {
            current.treeNodesInserted(e);
        }
    }
    
    public void fireTreeNodesRemoved(TreeModelEvent e) {
        for(TreeModelListener current : listener) {
            current.treeNodesInserted(e);
        }
    }
    
    public void fireTreeNodesChanged(TreeModelEvent e) {
        for(TreeModelListener current : listener) {
            current.treeNodesInserted(e);
        }
    }
    
    public void fireTreeStructureChanged(TreeModelEvent e) {
        for(TreeModelListener current : listener) {
            current.treeNodesInserted(e);
        }
    }

    
}
