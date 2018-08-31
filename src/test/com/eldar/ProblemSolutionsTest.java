package com.eldar;

import com.eldar.ProblemSolutions.ListNode;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


public class ProblemSolutionsTest {

    @Test
    public void naiveSumInArray() throws Exception {
        List<int[]> testList = new LinkedList<>();
        List<Integer> testedSumList = new LinkedList<>();
        List<int[][]> resultList = new LinkedList<>();

        testList.add(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        testedSumList.add(10);
        resultList.add(new int[][] {{1,9},{2,8},{3,7},{4,6}});

        testList.add(new int[] {-2,-5,0,3,5,6,-1,-4});
        testedSumList.add(1);
        resultList.add(new int[][] {{0,3},{1,5},{4,7}});


        ProblemSolutions tester = new ProblemSolutions();

        assertTrue("Naive array with no valid result did not return empty list",
                tester.naiveSumInArray(new int[] {0,0}, 1).isEmpty());

        for(int i=0; i<testList.size(); i++){
            List<int[]> naiveResult = tester.naiveSumInArray(testList.get(i), testedSumList.get(i));

            assertTrue("Naive sum search returned unexpected number of results",
                    naiveResult.size() == resultList.get(i).length);

            for(int j = 0; j < resultList.get(i).length; j++)
            {
                assertArrayEquals("naive result does not match expected result",
                        naiveResult.get(j), resultList.get(i)[j]);
            }
        }
    }

    @Test
    public void hashSumInArray() throws Exception {
        List<int[]> testList = new LinkedList<>();
        List<Integer> testedSumList = new LinkedList<>();
        List<int[][]> resultList = new LinkedList<>();

        testList.add(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        testedSumList.add(10);
        resultList.add(new int[][] {{1,9},{2,8},{3,7},{4,6}});

        testList.add(new int[] {-2,-5,0,3,5,6,-1,-4});
        testedSumList.add(1);
        resultList.add(new int[][] {{0,3},{1,5},{4,7}});


        ProblemSolutions tester = new ProblemSolutions();

        assertTrue("Hash array with no valid result did not return empty list",
                tester.hashSumInArray(new int[] {-1,0}, 1).isEmpty());

        for(int i=0; i<testList.size(); i++) {
            List<int[]> hashResult = tester.hashSumInArray(testList.get(i), testedSumList.get(i));

            assertTrue("Hash sum search returned unexpected number of results",
                    hashResult.size() == resultList.get(i).length);

            for (int j = 0; j < resultList.get(i).length; j++) {
                assertArrayEquals("hash result does not match expected result",
                        hashResult.get(j), resultList.get(i)[j]);
            }
        }
    }
    @Test
    public void hashCountJewels() throws Exception {
        ProblemSolutions tester = new ProblemSolutions();
        Assert.assertEquals(5, tester.hashCountJewels("aAbBZ", "afuinefdsnBdasZA"));
        Assert.assertEquals(0, tester.hashCountJewels("", ""));

    }

    @Test
    public void asciiCountJewels() throws Exception {
        ProblemSolutions tester = new ProblemSolutions();
        Assert.assertEquals(5, tester.asciiCountJewels("aAbBZ", "afuinefdsnBdasZA"));
        Assert.assertEquals(0, tester.asciiCountJewels("", ""));

    }
    @Test
    public void maxIncreaseInSkyline() throws Exception {
        ProblemSolutions tester = new ProblemSolutions();
        Assert
            .assertEquals(35, tester.maxIncreaseInSkyline(new int[][] {{3,0,8,4},{2,4,5,7},{9,2,6,3},{0,3,1,0}}));
    }
    @Test
    public void uniqueMorseCodeRepresentations() throws Exception {
        ProblemSolutions tester = new ProblemSolutions();
        Assert
            .assertEquals(2, tester.uniqueMorseCodeRepresentations(new String[] {"gin", "zen", "gig", "msg"}));

    }
    @Test
    public void sumWithoutOperators() throws Exception {
        ProblemSolutions tester = new ProblemSolutions();
        Assert.assertEquals(5, tester.sumWithoutOperators(4,1));
        Assert.assertEquals(0, tester.sumWithoutOperators(0,0));
    }
    @Test
    public void returnChange() throws Exception {
        ProblemSolutions tester = new ProblemSolutions();
        Assert.assertEquals(true, tester.returnChange(new int[] {5,5,5,10,20}));
        Assert.assertEquals(true, tester.returnChange(new int[] {5,5,10}));
        Assert.assertEquals(false, tester.returnChange(new int[] {10, 20}));
    }
    @Test
    public void topKFrequent() throws Exception {
        ProblemSolutions tester = new ProblemSolutions();
        List<Integer> results = tester.topKFrequent(new int[] {3,2,291,3,5,54,2,2,1,1,6,6,7,1,1}, 3);
        int[] expectedResults = new int[] {1,2,3};
        assertTrue(expectedResults.length == results.size());
        for(int i = 0; i < expectedResults.length; i++){
            assertEquals(expectedResults[i],(int) results.get(i));
        }
    }
    @Test
    public void constructMaximumBinaryTree() throws Exception {
        ProblemSolutions tester = new ProblemSolutions();
        ProblemSolutions.TreeNode tn = tester.constructMaximumBinaryTree(new int[] {3,2,1,6,0,5});
        assertTrue(tn.val == 6);
        ProblemSolutions.TreeNode left = tn.left;
        ProblemSolutions.TreeNode right = tn.right;
        assertTrue(left.val == 3);
        assertTrue(right.val == 5);
        assertTrue(left.right.val == 2);
        assertTrue(right.left.val == 0);
        assertTrue(left.right.right.val == 1);
    }
    @Test
    public void flipAndInvertImage() throws Exception {
        ProblemSolutions tester = new ProblemSolutions();
        Assert.assertArrayEquals(new int[][] {{1,0,0}, {0,1,0}, {1,1,1}},
                tester.flipAndInvertImage(new int[][] {{1,1,0},{1,0,1},{0,0,0}}));
        Assert.assertArrayEquals(new int[][] {{0}, {1}, {1}},
                tester.flipAndInvertImage(new int[][] {{1}, {0}, {0}}));
    }
    @Test
    public void twoSum() throws Exception {
        ProblemSolutions tester = new ProblemSolutions();
        Assert.assertArrayEquals(new int[] {0,1}, tester.twoSum(new int[] {2, 7, 11, 15}, 9));
    }
    @Test
    public void addTwoNumbers() throws Exception {
        ProblemSolutions tester = new ProblemSolutions();
        ProblemSolutions.ListNode l1 = tester.new ListNode(2);
        l1.next = tester.new ListNode(4);
        l1.next.next = tester.new ListNode(3);
        ProblemSolutions.ListNode l2 = tester.new ListNode(5);
        l2.next = tester.new ListNode(6);
        l2.next.next = tester.new ListNode(4);
        ProblemSolutions.ListNode result = tester.addTwoNumbers(l1,l2);
        assertTrue(result.val == 7);
        assertTrue(result.next.val == 0);
        assertTrue(result.next.next.val == 8);
    }
    @Test
  public void lengthOfLongestSubstring() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertEquals(tester.lengthOfLongestSubstring("abcabcbb"), 3);
      Assert.assertEquals(tester.lengthOfLongestSubstring(" "), 1);
      Assert.assertEquals(tester.lengthOfLongestSubstring(""), 0);
    }

    @Test
  public void longestPalindrome() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertTrue("aaaa".equals(tester.longestPalindrome("aaaa")));
      Assert.assertTrue("".equals(tester.longestPalindrome("")));
      Assert.assertTrue("a".equals(tester.longestPalindrome("a")));
      Assert.assertTrue("a".equals(tester.longestPalindrome("abcd")));
    }

    @Test
  public void zigZagConvert() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertTrue("PAHNAPLSIIGYIR".equals(tester.zigZagConvert("PAYPALISHIRING", 3)));
      Assert.assertTrue("".equals(tester.zigZagConvert("", 5)));
      Assert.assertTrue("abcd".equals(tester.zigZagConvert("abcd", 1)));
    }

    @Test
  public void integerReverse() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertEquals(123, tester.integerReverse(321));
      Assert.assertEquals(23445, tester.integerReverse(544320000));
      Assert.assertEquals(0, tester.integerReverse(-2147483648));
    }

    @Test
  public void myAtoi() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertEquals(-42, tester.myAtoi("   -42"));
      Assert.assertEquals(Integer.MAX_VALUE, tester.myAtoi("20000000000000000000"));
      Assert.assertEquals(Integer.MIN_VALUE, tester.myAtoi("-20000000000000000000"));
      Assert.assertEquals(0, tester.myAtoi("  nope"));
    }

    @Test
  public void isNumAPalindrome() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertTrue(tester.isNumAPalindrome(121));
      Assert.assertFalse(tester.isNumAPalindrome(-121));
      Assert.assertTrue(tester.isNumAPalindrome(0));
    }

    @Test
  public void maxArea() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertEquals(49, tester.maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
      Assert.assertEquals(0, tester.maxArea(new int[] {0,0}));
      Assert.assertEquals(2000, tester.maxArea(new int[] {500, 10, 0, 50000, 501}));
    }

    @Test
  public void longestCommonPrefix() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertTrue("".equals(tester.longestCommonPrefix(new String[] {"cba", "abc"})));
      Assert.assertTrue("co".equals(tester.longestCommonPrefix((new String[] {"cows", "company", "cobweb"}))));
      Assert.assertTrue("".equals(tester.longestCommonPrefix(new String[] {""})));
    }

    @Test
  public void threeSum() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      List<List<Integer>> results = tester.threeSum(new int[] {1,-1,-1,0});
      int[] resultToArr = new int[] {results.get(0).get(0), results.get(0).get(1), results.get(0).get(2)};
      Assert.assertArrayEquals(new int[] {-1, 0, 1}, resultToArr);
      results = tester.threeSum(new int[] {0,-4,-1,-4,-2,-3,2});
      resultToArr = new int[] {results.get(0).get(0), results.get(0).get(1), results.get(0).get(2)};
      Assert.assertArrayEquals(new int[] {-2, 0, 2}, resultToArr);
    }

    @Test
  public void medianOfIntStream() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertEquals(5.0, tester.medianOfIntStream(Arrays.stream(new Integer[] {0, 6, 5, 7, 2})), 0.00001);
      Assert.assertEquals(5.5, tester.medianOfIntStream(Arrays.stream(new Integer[] {6, 0, 6, 5, 7, 2})), 0.00001);
    }

    @Test
  public void letterCombinations() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      List<String> results = tester.letterCombinations("2");
      Assert.assertTrue(results.remove(0).equals("a"));
      Assert.assertTrue(results.remove(0).equals("b"));
      Assert.assertTrue(results.remove(0).equals("c"));
    }

    @Test
  public void removeNthFromEnd() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      ProblemSolutions.ListNode head = tester.new ListNode(1);

      Assert.assertNull(tester.removeNthFromEnd(head, 1));

      ProblemSolutions.ListNode tail = tester.new ListNode(2);
      head.next = tail;
      ProblemSolutions.ListNode tmp = tester.new ListNode(3);
      tail.next = tmp;
      tail = tmp;
      tmp = tester.new ListNode(4);
      tail.next = tmp;
      tail = tmp;
      tmp = tester.new ListNode(5);
      tail.next = tmp;
      tmp = tester.removeNthFromEnd(head, 2);
      int[] expectedNodeVals = new int[] {1,2,3,5};
      for(int i = 0; i < expectedNodeVals.length; i++){
        Assert.assertEquals(expectedNodeVals[i], tmp.val);
        tmp = tmp.next;
      }
    }

    @Test
  public void isValidParentheses() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertTrue(tester.isValidParentheses("(){}[]"));
      Assert.assertTrue(tester.isValidParentheses("(([{{}}]()[]))"));
    }

    @Test
    //[[1,4,5],[1,3,4],[2,6]]
  public void mergeKLists() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertNull(tester.mergeKLists(new ListNode[1]));
      ProblemSolutions.ListNode head1= tester.new ListNode(1);
      head1.next = tester.new ListNode(4);
      head1.next.next = tester.new ListNode(5);
      ProblemSolutions.ListNode head2 = tester.new ListNode(1);
      head2.next = tester.new ListNode(3);
      head2.next.next = tester.new ListNode(4);
      ProblemSolutions.ListNode head3 = tester.new ListNode(2);
      head3.next = tester.new ListNode(6);
      ProblemSolutions.ListNode[] listOfNodes = new ListNode[3];
      listOfNodes[0] = head1;
      listOfNodes[1] = head2;
      listOfNodes[2] = head3;
      ProblemSolutions.ListNode result =  tester.mergeKLists(listOfNodes);
      int[] expectedResults = new int[] {1,1,2,3,4,4,5,6};
      for(int i = 0; i < expectedResults.length; i++){
        Assert.assertEquals(expectedResults[i], result.val);
        result = result.next;
      }
    }

    @Test
    //1,2,3,4,5
  public void swapPairs() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      ProblemSolutions.ListNode head = tester.new ListNode(1);
      head.next = tester.new ListNode(2);
      head.next.next = tester.new ListNode(3);
      head.next.next.next = tester.new ListNode(4);
      head.next.next.next.next = tester.new ListNode(5);
      ListNode results = tester.swapPairs(head);
      int[] expectedResults = new int[] {2,1,4,3,5};
      for(int i = 0; i < expectedResults.length; i++){
        Assert.assertEquals(expectedResults[i], results.val);
        results = results.next;
      }
    }
    @Test
  public void removeDuplicates() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertEquals(3, tester.removeDuplicates(new int[] {1,1,1,3,3,3,4,4}));
      Assert.assertEquals(1, tester.removeDuplicates(new int[] {1}));
      Assert.assertEquals(0, tester.removeDuplicates(new int[0]));
    }

    @Test
  public void combinationSum() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      List<List<Integer>> results = tester.combinationSum(new int[] {2,3,5}, 8);
      int[][] expectedResults = new int[][] {{2,2,2,2},{2,3,3},{3,5}};
      Assert.assertTrue(results.size()==expectedResults.length);
      for(int i = 0; i < results.size(); i++){
        Assert.assertTrue(results.get(i).size()==expectedResults[i].length);
        for(int j = 0; j < results.get(i).size(); j++){
          Assert.assertTrue(results.get(i).get(j)==expectedResults[i][j]);
        }
      }
      results = tester.combinationSum(new int[] {2,3,6,7}, 7);
      expectedResults = new int[][] {{2,2,3}, {7}};
      Assert.assertTrue(results.size()==expectedResults.length);
      for(int i = 0; i < results.size(); i++){
        Assert.assertTrue(results.get(i).size()==expectedResults[i].length);
        for(int j = 0; j < results.get(i).size(); j++){
          Assert.assertTrue(results.get(i).get(j)==expectedResults[i][j]);
        }
      }
    }

    @Test
  public void myPow() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertTrue(1024.0==tester.myPow(2.0, 10));
      Assert.assertTrue(0.25==tester.myPow(2.0, -2));
      Assert.assertTrue(Math.abs(9.261-tester.myPow(2.1, 3))<0.0000001);
      Assert.assertTrue(1.0==tester.myPow(1.0, -2147483648));
    }

    @Test
  public void removeElement() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertEquals(5, tester.removeElement(new int[] {5,13,9,8,7,13,13,2,13}, 13));
      Assert.assertEquals(0, tester.removeElement(new int[] {}, 0));
      Assert.assertEquals(0, tester.removeElement(new int[] {5,5,5,5,5}, 5));
    }

    @Test
  public void reverseString() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      String initial = "cat math";
      String reverse = "math cat";
      Assert.assertTrue(reverse.equals(tester.reverseString(initial)));
      initial = "The rain in Spain stays mainly in the plain";
      reverse = "plain the in mainly stays Spain in rain The";
      Assert.assertTrue(reverse.equals(tester.reverseString(initial)));
      Assert.assertTrue("travel".equals(tester.reverseString("travel")));
      Assert.assertTrue("".equals(tester.reverseString("")));
    }
}