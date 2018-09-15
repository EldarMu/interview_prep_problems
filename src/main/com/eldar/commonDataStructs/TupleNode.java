package com.eldar.commonDataStructs;

//Int Tuple class for unique values, and the number of times they've occured
public class TupleNode {
  public int val;
  public int occurence;
  public TupleNode next;
  public TupleNode(int val, int occurrence){
    this.val = val;
    this.occurence = occurrence;
    next = null;
  }
}
