package com.eldar;

import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;


public class RandomPickIndexTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInputValidation(){
    RandomPickIndex rpi = new RandomPickIndex(null);
    RandomPickIndex rpj = new RandomPickIndex(new int[] {});
  }

  @Test
  public void pick() throws Exception{
    RandomPickIndex rpi;
    int[] input;
    int targetVal;
    Set<Integer> validResults = new HashSet<>();

    input = new int[] {1,2,3,3,3};
    rpi = new RandomPickIndex(input);

    targetVal = 1;
    validResults.add(0);
    Assert.assertTrue(validResults.contains(rpi.pick(targetVal)));

    targetVal = 3;
    validResults.clear();
    validResults.add(2);
    validResults.add(3);
    validResults.add(4);
    Assert.assertTrue(validResults.contains(rpi.pick(targetVal)));
    Assert.assertTrue(validResults.contains(rpi.pick(targetVal)));
    Assert.assertTrue(validResults.contains(rpi.pick(targetVal)));
  }

  @Test
  public void altPick() throws Exception{
    RandomPickIndex rpi;
    int[] input;
    int targetVal;
    Set<Integer> validResults = new HashSet<>();

    input = new int[] {1,2,3,3,3};
    rpi = new RandomPickIndex(input);

    targetVal = 1;
    validResults.add(0);
    Assert.assertTrue(validResults.contains(rpi.altPick(targetVal)));

    targetVal = 3;
    validResults.clear();
    validResults.add(2);
    validResults.add(3);
    validResults.add(4);
    Assert.assertTrue(validResults.contains(rpi.altPick(targetVal)));
    Assert.assertTrue(validResults.contains(rpi.altPick(targetVal)));
    Assert.assertTrue(validResults.contains(rpi.altPick(targetVal)));
  }

}
