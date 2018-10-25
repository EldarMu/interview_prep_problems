package com.eldar.commonDataStructs;

import java.util.List;

//helper class for
//https://leetcode.com/problems/employee-importance/
public class Employee {
  // unique id of this employee
  public int id;
  // the importance value of this employee
  public int importance;
  // the id of direct subordinates
  public List<Integer> subordinates;
};
