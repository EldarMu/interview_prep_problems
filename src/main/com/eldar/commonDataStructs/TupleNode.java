package com.eldar.commonDataStructs;

/**
 * Created by eldar on 9/15/2018.
 */
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
