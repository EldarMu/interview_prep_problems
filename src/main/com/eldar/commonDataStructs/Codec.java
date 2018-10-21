package com.eldar.commonDataStructs;

import java.util.LinkedList;
import java.util.Queue;

//serialize and deserialize BST
//https://leetcode.com/problems/serialize-and-deserialize-bst/
//beats 64% of java submissions, pretty standard solution
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

  //let's utilize the fact that it's a bst and is actually sorted values
  //and do a preorder traversal
  public String dfsSerialize(TreeNode root){
    if(root==null) return "";
    StringBuilder sb = new StringBuilder();
    recursSerialize(root, sb);
    sb.deleteCharAt(sb.length()-1);
    return sb.toString();
  }


  private void recursSerialize(TreeNode root, StringBuilder sb){
    sb.append(root.val);
    sb.append(" ");
    if(root.left!=null) recursSerialize(root.left, sb);
    if(root.right!=null) recursSerialize(root.right, sb);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if(data==null||data.length()==0)return null;
    String[] levels = data.split("\\.");
    Queue<TreeNode> level = new LinkedList<>();
    TreeNode head = new TreeNode(Integer.valueOf(levels[0]));
    level.offer(head);
    for(int i=1; i<levels.length; i++){
      String[] curLevel = levels[i].split("\\,");
      int levelSize = level.size();
      int curLevelChildNodeIndex = 0;
      for(int j=0; j<levelSize; j++){
        TreeNode curNode = level.poll();
        if(curLevel[curLevelChildNodeIndex].charAt(0)!='n'){
          curNode.left = new TreeNode(Integer.valueOf(curLevel[curLevelChildNodeIndex]));
          level.offer(curNode.left);
        }
        curLevelChildNodeIndex++;
        if(curLevel[curLevelChildNodeIndex].charAt(0)!='n'){
          curNode.right = new TreeNode(Integer.valueOf(curLevel[curLevelChildNodeIndex]));
          level.offer(curNode.right);
        }
        curLevelChildNodeIndex++;
      }
    }
    return head;
  }


}
