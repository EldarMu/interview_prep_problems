package com.eldar;

import org.junit.Assert;
import org.junit.Test;


//test class for getting average
public class AverageTest {

  @Test
  public void testAverage() throws Exception{
    Average tester = new Average();
    Assert.assertEquals(1.0, tester.Next(1.0), 0.009);
    Assert.assertEquals(2.0, tester.Next(3.0), 0.009);
    Assert.assertEquals(2.66,tester.Next(4.0), 0.009);
    Assert.assertEquals(1.0, tester.Next(-4.0), 0.009);
    Assert.assertEquals(-39.2, tester.Next(-200.0), 0.009);
  }

}
