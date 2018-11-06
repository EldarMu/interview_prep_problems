package com.eldar.commonDataStructs;

//right has to have start > this.end
//left has to have end < this.start
public class Date {
  public int start;
  public int end;
  public Date left;
  public Date right;
  public int height;

  public Date(int[] range, Date left, Date right){
    this.start = range[0];
    this.end = range[1];
    this.left = left;
    this.right = right;
  }
}
