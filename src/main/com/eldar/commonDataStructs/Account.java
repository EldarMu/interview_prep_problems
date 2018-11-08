package com.eldar.commonDataStructs;

import java.util.TreeSet;

//helper class for https://leetcode.com/problems/accounts-merge/
public class Account{
  public String name;
  public TreeSet<String> emailSet;
  public Account(String name){
    this.name=name;
    emailSet = new TreeSet<>();
  }
}
