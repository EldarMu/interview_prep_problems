package com.eldar;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//create a set that can add, remove, and return a random element in O(1) each
//https://leetcode.com/submissions/detail/179768574/
//fairly standard solution, beats 55% of java submissions
//worked on first compilation
class RandomizedSet {
  private Map<Integer, Integer> valToIncrement;
  private Map<Integer, Integer> incrementToVal;
  private Random rand;
  private int increment;

  public RandomizedSet() {
    valToIncrement = new HashMap<>();
    incrementToVal = new HashMap<>();
    rand = new Random();
    increment = 0;
  }

  public boolean insert(int val) {
    if(valToIncrement.containsKey(val)) return false;
    valToIncrement.put(val, increment);
    incrementToVal.put(increment, val);
    increment++;
    return true;
  }

  public boolean remove(int val) {
    if(!valToIncrement.containsKey(val)) return false;

    increment--;
    int lastVal = incrementToVal.get(increment);

    if(val==lastVal){
      incrementToVal.remove(increment);
      valToIncrement.remove(lastVal);
      return true;
    }
    else{
      int curIncrement = valToIncrement.get(val);
      valToIncrement.remove(val);
      incrementToVal.remove(increment);
      valToIncrement.put(lastVal, curIncrement);
      incrementToVal.put(curIncrement, lastVal);
      return true;
    }
  }

  public int getRandom() {
    if(increment==0) return -1;
    return incrementToVal.get(rand.nextInt(increment));
  }
}
