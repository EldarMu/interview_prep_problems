package com.eldar.commonDataStructs;

import java.util.LinkedList;
import java.util.Queue;

//serialize and deserialize BST
//https://leetcode.com/problems/serialize-and-deserialize-bst/
public class Codec {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if(root==null) return "";
    StringBuilder sb = new StringBuilder();
    Queue<TreeNode> level = new LinkedList<>();
    sb.append(Integer.toString(root.val));
    sb.append('.');
    level.offer(root);
    boolean atLeastOneNonNull = true;
    while(!level.isEmpty()&&atLeastOneNonNull){
      int curLevelSize = level.size();
      for(int i=0; i<curLevelSize; i++){
        TreeNode curNode = level.poll();
        if(curNode.left==null) sb.append('n');
        else{
          sb.append(Integer.toString(curNode.left.val));
          level.offer(curNode.left);
        }
        sb.append(',');
        if(curNode.right==null) sb.append('n');
        else{
          sb.append(Integer.toString(curNode.right.val));
          level.offer(curNode.right);
        }
        if(i!=curLevelSize-1) sb.append(',');
      }
      sb.append('.');
    }
    sb.deleteCharAt(sb.length()-1);
    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    return null;
  }
}
