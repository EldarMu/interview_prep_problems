package com.eldar;

import com.eldar.commonDataStructs.MyCalendar2;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class MyCalendar2Test {
  @Test
  public void testBooking() throws Exception{
    MyCalendar2 test = new MyCalendar2();
    int[][] dates;
    boolean[] expected;
    List<Boolean> results;

    dates = new int[][] {{12,20},{21,29},{11,20},{12,17},{84,90},{60,68},{88,94},{23,32},
        {88,94},{15,20},{77,83},{34,42},{44,53},{35,40},{24,31},{48,55},{0,6},{6,13},
        {58,63},{15,23}};
    expected = new boolean[] {true, true, false, false, true, true, false, false, false,false,
        true, true, true, false, false, false, true, false, false, false};
    results = new ArrayList<>(dates.length);
    for(int i=0; i<dates.length; i++){
      if(i==10) System.out.println("debugging");
      results.add(test.book(dates[i][0], dates[i][1]));
    }
    for(int i=0; i<expected.length; i++){
      Assert.assertEquals(Integer.toString(i), expected[i], results.get(i));
    }

    dates = new int[][] {{10, 20}, {15,25}, {20, 30}};
    expected = new boolean[] {true, false, true};
    results = new ArrayList<>(dates.length);
    test.clear();
    for(int i=0; i<dates.length; i++){
      results.add(test.book(dates[i][0], dates[i][1]));
    }
    for(int i=0; i<expected.length; i++){
      Assert.assertEquals(Integer.toString(i), expected[i], results.get(i));
    }

  }

}

