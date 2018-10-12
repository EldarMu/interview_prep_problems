package com.eldar.commonDataStructs;

import java.util.List;

public class NaryTreeNode {
  public int val;
  public List<NaryTreeNode> children;
  public NaryTreeNode(int val, List<NaryTreeNode> children){
    this.val = val;
    this.children = children;
  }
}
