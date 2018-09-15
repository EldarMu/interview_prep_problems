package com.eldar.commonDataStructs;

//a BST TreeNode that also has a reference
//to the element to its right in its own row
public class TreeLinkNode {
  public int val;
  public TreeLinkNode left;
  public TreeLinkNode right;
  public TreeLinkNode next;
  public TreeLinkNode(int x) { val = x; }
}
