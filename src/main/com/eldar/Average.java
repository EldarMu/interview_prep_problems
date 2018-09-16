package com.eldar;

//implement a class that takes in double values and returns the average
//double values are input sequentially by user
public class Average {
  private int count = 0;
  private double curTotal = 0;

  public double Next(double value) {
    curTotal +=value;
    count++;
    return curTotal/count;
  }
}

