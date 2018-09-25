package com.eldar.commonDataStructs;

import java.util.HashMap;
import java.util.Map;

//class needed for the "solve equations as graphs" problem
public class GraphNode{
  public String name;
  public Map<String, Double> connections;
  public GraphNode(String name){
    this.name=name;
    connections = new HashMap<>();
  }
}
