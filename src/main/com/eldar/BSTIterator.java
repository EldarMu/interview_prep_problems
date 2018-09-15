package com.eldar;

import com.eldar.ProblemSolutions.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

//create an iterator for smallest to largest values in a BST
//https://leetcode.com/problems/binary-search-tree-iterator/
//beats 100% of java submissions
public class BSTIterator {
  private Queue<Integer> internalIterator;
  public BSTIterator(TreeNode root) {
    internalIterator = new LinkedList<>();
    if(root!=null){recursBuildIterator(root);}
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return !internalIterator.isEmpty();
  }

  /** @return the next smallest number */
  public int next() {
   return internalIterator.poll();
  }

  private void recursBuildIterator(TreeNode root){
    if(root.left!=null){recursBuildIterator(root.left);}
    internalIterator.offer(root.val);
    if(root.right!=null){recursBuildIterator(root.right);}
  }
}
