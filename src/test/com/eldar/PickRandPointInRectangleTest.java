package com.eldar;

import com.eldar.commonDataStructs.PickRandPointInRectangle;
import org.junit.Assert;
import org.junit.Test;

//test class for the non-overlapping rectangle picking problem
public class PickRandPointInRectangleTest {

  @Test
  public void pick() throws Exception{
    PickRandPointInRectangle test;
    int[][] input;
    int[] result;


    input = new int[][] {{82918473, -57180867, 82918476, -57180863}};
    test = new PickRandPointInRectangle(input);
    result = test.pick();
    Assert.assertTrue(result[0]>=input[0][0]&&result[0]<=input[0][2]);
    Assert.assertTrue(result[1]>=input[0][1]&&result[1]<=input[0][3]);

    input = new int[][] {{1,1,5,5}};
    test = new PickRandPointInRectangle(input);
    result = test.pick();
    Assert.assertTrue(result[0]>=input[0][0]&&result[0]<=input[0][2]);
    Assert.assertTrue(result[1]>=input[0][1]&&result[1]<=input[0][3]);

    input = new int[][] {{-2,-2,-1,-1}};
    test = new PickRandPointInRectangle(input);
    result = test.pick();
    Assert.assertTrue(result[0]>=input[0][0]&&result[0]<=input[0][2]);
    Assert.assertTrue(result[1]>=input[0][1]&&result[1]<=input[0][3]);

    input = new int[][] {{83793579, 18088559, 83793580, 18088560}};
    test = new PickRandPointInRectangle(input);
    result = test.pick();
    Assert.assertTrue(result[0]>=input[0][0]&&result[0]<=input[0][2]);
    Assert.assertTrue(result[1]>=input[0][1]&&result[1]<=input[0][3]);

    input = new int[][] {{66574245, 26243152, 66574246, 26243153}};
    test = new PickRandPointInRectangle(input);
    result = test.pick();
    Assert.assertTrue(result[0]>=input[0][0]&&result[0]<=input[0][2]);
    Assert.assertTrue(result[1]>=input[0][1]&&result[1]<=input[0][3]);

    input = new int[][] {{72983930, 11921716, 72983934, 11921720}};
    test = new PickRandPointInRectangle(input);
    result = test.pick();
    Assert.assertTrue(result[0]>=input[0][0]&&result[0]<=input[0][2]);
    Assert.assertTrue(result[1]>=input[0][1]&&result[1]<=input[0][3]);
  }

}
