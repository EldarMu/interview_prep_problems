package com.eldar.commonDataStructs;


//for https://leetcode.com/problems/my-calendar-i/
//based on Mark Allen Weiss's implementation of AVL tree
//http://users.cis.fiu.edu/~weiss/dsaajava/code/DataStructures/AvlTree.java
//heavily stripped down since we only actually need the book and balance functionality
public class MyCalendar {
  Date root;

  public MyCalendar(){root=null;}

  public void clear(){root=null;}

  //book returns false if interval already overlaps with another interval
  public boolean book(int start, int end){
    boolean[] insertSuccess = new boolean[] {true};
    int[] range = new int[] {start, end-1};
    root = insertWithNode(range, root, insertSuccess);
    return insertSuccess[0];
  }

  private Date insertWithNode(int[] range, Date node, boolean[] insertSuccess){
    if(node == null) node = new Date( range, null, null );
    else if(range[1]<node.start) {
      node.left = insertWithNode(range, node.left, insertSuccess);
      if(getHeight(node.left)-getHeight(node.right) == 2){
        if(range[1]<node.left.start) node = rotateOneLeft( node );
        else node = rotateTwoLeft( node );
      }
    }
    else if(range[0]>node.end){
      node.right = insertWithNode(range, node.right, insertSuccess);
      if(getHeight(node.right)-getHeight(node.left) == 2){
        if(range[0]>node.right.end) node = rotateOneRight(node);
        else node = rotateTwoRight(node);
      }
    }
    else insertSuccess[0]=false;
    node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    return node;
  }

  private int getHeight(Date node){
    return node==null? -1 : node.height;
  }


  private Date rotateOneLeft(Date node){
    Date tmp = node.left;
    node.left = tmp.right;
    tmp.right = node;
    node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    tmp.height = Math.max(getHeight(tmp.left), node.height) + 1;
    return tmp;
  }

  private Date rotateOneRight(Date node){
    Date tmp = node.right;
    node.right = tmp.left;
    tmp.left = node;
    node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    tmp.height = Math.max(getHeight(tmp.right), node.height) + 1;
    return tmp;
  }
  private Date rotateTwoLeft(Date node){
    node.left = rotateOneRight(node.left);
    return rotateOneLeft(node);

  }
  private Date rotateTwoRight(Date node){
    node.right = rotateOneLeft(node.right);
    return rotateOneRight(node);
  }

}
