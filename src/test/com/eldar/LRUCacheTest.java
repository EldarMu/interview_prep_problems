package com.eldar;

import java.lang.IllegalArgumentException;
import org.junit.Assert;
import org.junit.Test;

//LRU Cache made far more sense as a separate class
//it was made one, and requires a separate test class
public class LRUCacheTest {

  @Test(expected = IllegalArgumentException.class)
  public void LRUCacheInvalidCapacity(){
    LRUCache test = new LRUCache(-1);
  }

  @Test
  public void LRUCache()throws Exception{
    LRUCache test;

    test = new LRUCache(3);
    test.put(1,1);
    test.put(2,2);
    test.put(3,3);
    test.put(4,4);
    Assert.assertEquals(4, test.get(4));
    Assert.assertEquals(3, test.get(3));
    Assert.assertEquals(2, test.get(2));
    Assert.assertEquals(-1, test.get(1));
    test.put(5,5);
    Assert.assertEquals(-1, test.get(1));
    Assert.assertEquals(2, test.get(2));
    Assert.assertEquals(3, test.get(3));
    Assert.assertEquals(-1, test.get(4));
    Assert.assertEquals(5, test.get(5));

    test = new LRUCache(2);
    test.put(1,1);
    test.put(2,2);
    Assert.assertEquals(1, test.get(1));
    test.put(3,3);
    Assert.assertEquals(-1, test.get(2));
    test.put(4,4);
    Assert.assertEquals(-1, test.get(1));
    Assert.assertEquals(3, test.get(3));
    Assert.assertEquals(4, test.get(4));
  }

}
