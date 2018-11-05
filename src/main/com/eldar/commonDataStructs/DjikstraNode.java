package com.eldar.commonDataStructs;

import java.util.ArrayList;
import java.util.List;

//helper class for https://leetcode.com/problems/network-delay-time/
public class DjikstraNode implements Comparable<DjikstraNode>{
  public List<int[]> links;
  public int bestWeight;
  public DjikstraNode(){
    links = new ArrayList<>();
    bestWeight=Integer.MAX_VALUE;
  }

  @Override
  public int compareTo(DjikstraNode o) {
    return this.bestWeight-o.bestWeight;
  }
}
