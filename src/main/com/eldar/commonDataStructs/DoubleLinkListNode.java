package com.eldar.commonDataStructs;

//a listnode with a previous reference
//that in the implementation it was needed for was reached via key
//so it also stores the key
public class DoubleLinkListNode{
  public int val;
  public int key;
  public DoubleLinkListNode next;
  public DoubleLinkListNode prev;
  public DoubleLinkListNode(int key, int val){
    this.key=key;
    this.val=val;
  }
}
