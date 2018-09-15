package com.eldar;

import com.eldar.commonDataStructs.DoubleLinkListNode;
import java.util.HashMap;
import java.util.Map;

//implement an LRU cache class;
//get and put should be constant time operations
//note that according to the problem, .get() does not refresh the item's position
//https://leetcode.com/problems/lru-cache/description/
//standard solution, beats 57% of java submissions, nothing special but it's mine
public class LRUCache {
  private int capacity;
  private Map<Integer, DoubleLinkListNode> internalMap;
  DoubleLinkListNode head;
  DoubleLinkListNode tail;

  public LRUCache(int capacity) {
    if(capacity<=0){throw new IllegalArgumentException("cannot set LRU capacity below 1");}
    this.capacity = capacity;
    internalMap = new HashMap<>();
  }

  public int get(int key) {
    if(internalMap.containsKey(key)){
      DoubleLinkListNode tmp = internalMap.get(key);
      if(tmp.next!=null&&tmp.prev!=null){
        tmp.next.prev=tmp.prev;
        tmp.prev.next=tmp.next;
        tmp.prev = tail;
        tmp.next = null;
        tail.next = tmp;
        tail = tmp;
      }
      else if(tmp.next!=null){
        tmp.next.prev=null;
        head = tmp.next;
        tmp.next = null;
        tmp.prev = tail;
        tail.next = tmp;
        tail = tmp;
      }
      return tmp.val;
    }
    else{
      return -1;
    }
  }

  public void put(int key, int value) {
    if(internalMap.containsKey(key)){
      int callGet = get(key);
      internalMap.get(key).val=value;
    }
    else{
      if(head==null){
        head = new DoubleLinkListNode(key, value);
        internalMap.put(key, head);
        tail = head;
      }
      else{
        DoubleLinkListNode tmp = new DoubleLinkListNode(key, value);
        tmp.prev=tail;
        tail.next = tmp;
        internalMap.put(key, tmp);
        tail = tmp;
        if(internalMap.size()==capacity+1){
          internalMap.remove(head.key);
          head.next.prev = null;
          head=head.next;
        }
      }
    }
  }
}
