package com.eldar.commonDataStructs;

//class for comparing two binary trees made using the TreeNode class
public class BSTComparator {
  public boolean isSameTree(TreeNode root1, TreeNode root2){
    if(root1==null&&root2==null) return true;
    if(root1==null^root2==null) return false;
    if(root1.val!=root2.val) return false;
    if(!isSameTree(root1.left, root2.left)) return false;
    if(!isSameTree(root1.right, root2.right)) return false;
    return true;
  }

}
