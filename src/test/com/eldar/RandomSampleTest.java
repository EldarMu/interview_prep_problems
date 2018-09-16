package com.eldar;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class RandomSampleTest {

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionForZero(){
    RandomSample tester = new RandomSample(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionForNegative(){
    RandomSample tester = new RandomSample(-3);
  }

  @Test
  public void testRandomSample() throws Exception{
    Set<Double> addedVals = new HashSet<>();
    RandomSample rndSmp = new RandomSample(5);
    double[] vals = new double[] {1.0, 2.5, 3.0, 4.4, 1.1, 2.9, -200.0, 2074.0, -2041.0, -3.0, -5.0};
    for(int i = 0; i < vals.length; i++){
      addedVals.add(vals[i]);
      rndSmp.next(vals[i]);
    }
    List<Double> results = rndSmp.getSample();
    for(Double result: results){
      Assert.assertTrue(addedVals.contains(result));
    }
  }

}
