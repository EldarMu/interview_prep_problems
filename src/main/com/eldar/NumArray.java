package com.eldar;

//given an array, return the sum of values within range of that array (range edges inclusive)
//function sumRange gets called frequently
//https://leetcode.com/problems/range-sum-query-immutable/description/
//standard issue java solution, beats 52% of java submissions
class NumArray {
  int[] nums;

  public NumArray(int[] nums) {
    if(nums==null||nums.length==0) throw new IllegalArgumentException("can't instantiate NumArray with empty array");
    this.nums = nums;
    for(int i = 1; i < nums.length; i++){
      nums[i]+=nums[i-1];
    }
  }

  public int sumRange(int i, int j) {
    if(i<0||j>nums.length-1) throw new ArrayIndexOutOfBoundsException("parameters must be valid array indeces");
    if(i==0) return nums[j];
    else return nums[j]-nums[i-1];
  }
}
