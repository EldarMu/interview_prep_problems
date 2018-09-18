package com.eldar.commonDataStructs;

import java.util.List;


public class Contact {
  public String name;
  public List<String> emails;
  public Contact(String name, List<String> emails){
    this.name = name;
    this.emails=emails;
  }
}
