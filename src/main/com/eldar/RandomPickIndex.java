package com.eldar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//given an array of integers with possible duplicates
//the class needs to, when the get(int n) method is called,
//return a random instance of that integer out of its duplicates
//needs to take up less than O(N) space as the input array may be very large
//https://leetcode.com/problems/random-pick-index/description/
//sorting the array is not allowed, indices must remain as provided
//solution beats 62% of java submissions
public class RandomPickIndex {
  private int[] nums;
  private Random rand;

  public RandomPickIndex(int[] nums){
    if(nums==null||nums.length==0) throw new IllegalArgumentException("must input a valid array");
    this.nums=nums;
    rand = new Random();
  }
  public int pick(int target) {
    List<Integer> instancesOfTarget = new ArrayList<>();
    for(int i=0; i<nums.length; i++){
      if(nums[i]==target) instancesOfTarget.add(i);
    }
    if(instancesOfTarget.isEmpty()) return -1;
    return instancesOfTarget.get(rand.nextInt(instancesOfTarget.size()));
  }

  //alternative approach
  //beats 63% of java submissions
  public int altPick(int target){
    double occurences = 0.0;
    int chosenIndex = 0;
    for(int i=0; i<nums.length; i++){
      if(nums[i]!=target) continue;
      occurences+=1.0;
      chosenIndex = rand.nextDouble()<=1.0/occurences ? i : chosenIndex;
    }
    return chosenIndex;
  }

}
