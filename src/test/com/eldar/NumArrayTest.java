package com.eldar;


import org.junit.Assert;
import org.junit.Test;

public class NumArrayTest {

  @Test(expected = IllegalArgumentException.class)
  public void numArrayInstantiateTest(){
    NumArray test = new NumArray(new int[] {});
  }

  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void numArrayBoundsTest(){
    NumArray test = new NumArray(new int[] {1,2,3});
    int result = test.sumRange(0, 5);
  }

  @Test
  public void numArrayNormalFunctioningTest(){
    NumArray test = new NumArray(new int[] {-2, 0, 3, -5, 2, -1});
    Assert.assertEquals(1, test.sumRange(0,2));
    Assert.assertEquals(-3, test.sumRange(0,5));
    Assert.assertEquals(-1, test.sumRange(2,5));
  }
}
