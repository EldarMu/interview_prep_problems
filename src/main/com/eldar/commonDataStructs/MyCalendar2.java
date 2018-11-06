package com.eldar.commonDataStructs;

import java.util.TreeMap;

//alternative shorter sol'n to https://leetcode.com/problems/my-calendar-i/
public class MyCalendar2 {
  TreeMap<Integer, Integer> tm;
  public MyCalendar2(){tm=new TreeMap<>();}
  public void clear(){tm.clear();}
  public boolean book(int start, int end){
    Integer lower = tm.floorKey(end-1);
    if(lower==null||start>tm.get(lower)) tm.put(start, end-1);
    else return false;
    return true;
  }
}
