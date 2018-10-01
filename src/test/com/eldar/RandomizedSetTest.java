package com.eldar;

import org.junit.Assert;
import org.junit.Test;

public class RandomizedSetTest {
  @Test
  public void testRandomizedSet(){
    RandomizedSet test = new RandomizedSet();
    Assert.assertTrue(test.insert(1));
    Assert.assertFalse(test.insert(1));
    Assert.assertFalse(test.remove(2));
    Assert.assertTrue(test.insert(2));
    Assert.assertTrue(test.remove(2));
    Assert.assertEquals(1, test.getRandom());
    Assert.assertTrue(test.insert(2));
    int randVal = test.getRandom();
    Assert.assertTrue(randVal==1||randVal==2);
  }
}
