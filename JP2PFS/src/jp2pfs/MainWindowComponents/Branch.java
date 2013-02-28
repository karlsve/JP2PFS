/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp2pfs.MainWindowComponents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;

/**
 *
 * @author karlinsv
 */
public class Branch implements TreeNode {
    
    private Branch parent = null;
    private List<Branch> children = new ArrayList<Branch>();
    private String name = "";
    
    public Branch(String name, Branch parent) {
        this.name = name;
        this.parent = parent;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public void add(Branch child) {
        children.add(child);
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return parent != null;
    }

    @Override
    public Enumeration children() {
        return Collections.enumeration(children);
    }
    
    public void removeChild(Branch child) {
        this.children.remove(child);
    }
    
    public List<Branch> getChildren() {
        return this.children;
    }
    
    
    
}
