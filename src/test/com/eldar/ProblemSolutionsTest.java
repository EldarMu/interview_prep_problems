package com.eldar;

import com.eldar.commonDataStructs.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
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
        TreeNode tn = tester.constructMaximumBinaryTree(new int[] {3,2,1,6,0,5});
        assertTrue(tn.val == 6);
        TreeNode left = tn.left;
        TreeNode right = tn.right;
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
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode result = tester.addTwoNumbers(l1,l2);
        assertTrue(result.val == 7);
        assertTrue(result.next.val == 0);
        assertTrue(result.next.next.val == 8);
    }
    @Test
  public void lengthOfLongestSubstring() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertEquals(3, tester.lengthOfLongestSubstring("abcabcbb"));
      Assert.assertEquals(1, tester.lengthOfLongestSubstring(" "));
      Assert.assertEquals(0, tester.lengthOfLongestSubstring(""));
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
      ListNode head = new ListNode(1);

      Assert.assertNull(tester.removeNthFromEnd(head, 1));

      ListNode tail = new ListNode(2);
      head.next = tail;
      ListNode tmp = new ListNode(3);
      tail.next = tmp;
      tail = tmp;
      tmp = new ListNode(4);
      tail.next = tmp;
      tail = tmp;
      tmp = new ListNode(5);
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
      ListNode head1= new ListNode(1);
      head1.next = new ListNode(4);
      head1.next.next = new ListNode(5);
      ListNode head2 = new ListNode(1);
      head2.next = new ListNode(3);
      head2.next.next = new ListNode(4);
      ListNode head3 = new ListNode(2);
      head3.next = new ListNode(6);
      ListNode[] listOfNodes = new ListNode[3];
      listOfNodes[0] = head1;
      listOfNodes[1] = head2;
      listOfNodes[2] = head3;
      ListNode result =  tester.mergeKLists(listOfNodes);
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
      ListNode head = new ListNode(1);
      head.next = new ListNode(2);
      head.next.next = new ListNode(3);
      head.next.next.next = new ListNode(4);
      head.next.next.next.next = new ListNode(5);
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
    @Test
  public void strStr() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      String haystack = "a";
      String needle = "a";
      Assert.assertEquals(0, tester.strStr(haystack, needle));
      haystack = "hallowed";
      needle = "ll";
      Assert.assertEquals(2, tester.strStr(haystack, needle));
      Assert.assertEquals(-1, tester.strStr("candle", "row"));
    }

    @Test
  public void searchRange()throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertArrayEquals(new int[] {-1,-1}, tester.searchRange(new int[] {2,2}, 1));
      Assert.assertArrayEquals(new int[] {-1,-1}, tester.searchRange(new int[]{5,7,7,8,8,10}, 6));
      Assert.assertArrayEquals(new int[] {3,4}, tester.searchRange(new int[]{5,7,7,8,8,10}, 8));
      Assert.assertArrayEquals(new int[] {-1,-1}, tester.searchRange(new int[] {}, 0));
    }

    @Test
  public void searchInsert() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertEquals(3, tester.searchInsert(new int[] {3,5,7,9,10}, 8));
      Assert.assertEquals(2, tester.searchInsert(new int[] {1,3,5,6}, 5));
      Assert.assertEquals(1, tester.searchInsert(new int[] {1,3,5,6}, 2));
      Assert.assertEquals(4, tester.searchInsert(new int[] {1,3,5,6}, 7));
      Assert.assertEquals(0, tester.searchInsert(new int[] {1,3,5,6}, 0));
    }

    @Test
  public void mergeArrs() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      int[] result = new int[] {1,2,3,0,0,0};
      tester.mergeArrs(result, 3, new int[] {2,5,6}, 3);
      Assert.assertArrayEquals(new int[] {1,2,2,3,5,6}, result);
      result = new int[] {0};
      tester.mergeArrs(result, 0, new int[] {1}, 1);
      Assert.assertArrayEquals(new int[] {1}, result);
    }

    @Test
  public void inorderTraversal() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      TreeNode head = new TreeNode(1);
      head.left = null;
      head.right = new TreeNode(2);
      head.right.right = null;
      head.right.left = new TreeNode(3);
      List<Integer> results = tester.inorderTraversal(head);
      int[] expectedResults = new int[] {1,3,2};
      for(int i = 0; i < expectedResults.length; i++){
        Assert.assertEquals(expectedResults[i], (int)results.get(i));
      }
      Assert.assertEquals(0, (int)tester.inorderTraversal(new TreeNode(0)).get(0));
    }

    @Test
  public void climbStairs() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertEquals(3, tester.climbStairs(3));
      Assert.assertEquals(2, tester.climbStairs(2));
      Assert.assertEquals(13, tester.climbStairs(6));
      Assert.assertEquals(0, tester.climbStairs(0));
      Assert.assertEquals(0, tester.climbStairs(-3));
    }

    @Test
  public void insertInterval() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      List<Interval> initIntervals = new ArrayList<>();
      initIntervals.add(new Interval(1,2));
      initIntervals.add(new Interval(3,5));
      initIntervals.add(new Interval(6,7));
      initIntervals.add(new Interval(8,10));
      initIntervals.add(new Interval(12, 16));
      List<Interval> result = tester.insertInterval(initIntervals, new Interval(4,8));
      int[][] expectedResults = new int[][] {{1,2},{3,10},{12,16}};
      Assert.assertEquals(expectedResults.length, result.size());
      for(int i = 0; i < result.size(); i++){
        Assert.assertEquals(expectedResults[i][0], result.get(i).start);
        Assert.assertEquals(expectedResults[i][1], result.get(i).end);
      }

      initIntervals = new ArrayList<>();
      initIntervals.add(new Interval(1,3));
      initIntervals.add(new Interval(6,9));
      result = tester.insertInterval(initIntervals, new Interval(2,5));
      expectedResults = new int[][] {{1,5},{6,9}};
      Assert.assertEquals(expectedResults.length, result.size());
      for(int i = 0; i < result.size(); i++){
        Assert.assertEquals(expectedResults[i][0], result.get(i).start);
        Assert.assertEquals(expectedResults[i][1], result.get(i).end);
      }
    }

    @Test
  public void wordExists() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      char[][] board = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
      Assert.assertFalse(tester.wordExists(board, "ABCB"));
      Assert.assertTrue(tester.wordExists(board, "ABCCED"));
      Assert.assertTrue(tester.wordExists(board, "SEE"));
    }

    @Test
  public void removeTriplicates() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertEquals(5, tester.removeTriplicates(new int[] {1,1,1,2,2,3}));
      Assert.assertEquals(0, tester.removeTriplicates(new int[] {}));
      Assert.assertEquals(7, tester.removeTriplicates(new int[] {0,0,1,1,1,1,2,3,3}));
      Assert.assertEquals(3, tester.removeTriplicates(new int[] {1,2,2}));
    }

    @Test
  public void minWindow() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      Assert.assertTrue("BANC".equals(tester.minWindow("ADOBECODEBANC", "ABC")));
    }

    @Test
  public void isValidBST() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      TreeNode head = new TreeNode(2);
      head.left = new TreeNode(1);
      head.right = new TreeNode(3);
      Assert.assertTrue(tester.isValidBST(head));
      head = new TreeNode(1);
      head.left = new TreeNode(1);
      Assert.assertFalse(tester.isValidBST(head));
      head = new TreeNode(-2147483648);
      Assert.assertTrue(tester.isValidBST(head));
      head = new TreeNode(5);
      head.left = new TreeNode(1);
      head.right = new TreeNode(4);
      head.right.left = new TreeNode(3);
      head.right.right = new TreeNode(6);
      Assert.assertFalse(tester.isValidBST(head));
    }

    @Test
  public void setZeroes() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      int[][] testMatrix = new int[][] {{1,1,1},{1,0,1},{1,1,1}};
      int[][] expectedResult = new int[][] {{1,0,1},{0,0,0},{1,0,1}};
      tester.setZeroes(testMatrix);
      for(int i = 0; i < expectedResult.length; i++){
        Assert.assertArrayEquals(expectedResult[i], testMatrix[i]);
      }
      testMatrix = new int[][] {{}};
      tester.setZeroes(testMatrix);
      Assert.assertArrayEquals(new int[][] {{}}, testMatrix);
    }

    @Test
  public void plusOne() throws Exception{
      ProblemSolutions tester = new ProblemSolutions();
      int[] testArr = new int[] {1,2,3,4,5};
      Assert.assertArrayEquals(new int[] {1,2,3,4,6}, tester.plusOne(testArr));
      testArr = new int[] {9,9};
      Assert.assertArrayEquals(new int[] {1,0,0}, tester.plusOne(testArr));
      Assert.assertArrayEquals(new int[] {}, tester.plusOne(new int[] {}));
    }

    @Test
  public void minPathSum() throws Exception {
      ProblemSolutions tester = new ProblemSolutions();
      int[][] testMatrix = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
      Assert.assertEquals(7, tester.minPathSum(testMatrix));
      testMatrix = new int[][] {{0}};
      Assert.assertEquals(0, tester.minPathSum(testMatrix));

    }

  @Test
  public void downRightMinPathSum() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[][] testMatrix = new int[][] {{1,3,1}, {1,5,1}, {4,2,1}};
    Assert.assertEquals(7, tester.downRightMinPathSum(testMatrix));
    testMatrix = new int[][] {{0}};
    Assert.assertEquals(0, tester.downRightMinPathSum(testMatrix));

  }

  @Test
  public void rotateListRight() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    ListNode head = new ListNode(1);
    ListNode tmp = new ListNode(2);
    head.next = tmp;
    tmp.next = new ListNode(3);
    tmp = tmp.next;
    tmp.next = new ListNode(4);
    tmp = tmp.next;
    tmp.next = new ListNode(5);
    int[] expectedResult = new int[] {4,5,1,2,3};
    tmp = tester.rotateListRight(head, 2);
    for(int i = 0; i < expectedResult.length; i++){
      Assert.assertEquals(expectedResult[i],tmp.val);
      tmp = tmp.next;
    }
    head = new ListNode(0);
    tmp = new ListNode(1);
    head.next = tmp;
    tmp.next = new ListNode(2);
    tmp = tester.rotateListRight(head, 4);
    expectedResult = new int[] {2,0,1};
    for(int i = 0; i < expectedResult.length; i++){
      Assert.assertEquals(expectedResult[i], tmp.val);
      tmp = tmp.next;
    }
  }

  @Test
  public void altRotateListRight() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    ListNode head = new ListNode(1);
    ListNode tmp = new ListNode(2);
    head.next = tmp;
    tmp.next = new ListNode(3);
    tmp = tmp.next;
    tmp.next = new ListNode(4);
    tmp = tmp.next;
    tmp.next = new ListNode(5);
    int[] expectedResult = new int[] {4,5,1,2,3};
    tmp = tester.altRotateListRight(head, 2);
    for(int i = 0; i < expectedResult.length; i++){
      Assert.assertEquals(expectedResult[i],tmp.val);
      tmp = tmp.next;
    }
    head = new ListNode(0);
    tmp = new ListNode(1);
    head.next = tmp;
    tmp.next = new ListNode(2);
    tmp = tester.altRotateListRight(head, 4);
    expectedResult = new int[] {2,0,1};
    for(int i = 0; i < expectedResult.length; i++){
      Assert.assertEquals(expectedResult[i], tmp.val);
      tmp = tmp.next;
    }
  }

  @Test
  public void largestRectangleArea() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] testArr = new int[] {2,1,5,6,2,3};
    Assert.assertEquals(10, tester.largestRectangleArea(testArr));
    Assert.assertEquals(2, tester.largestRectangleArea(new int[] {1,1}));
  }

  @Test
  public void reverseListBetween() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    ListNode head = new ListNode(1);
    ListNode tmp = new ListNode(2);
    head.next = tmp;
    tmp.next = new ListNode(3);
    tmp = tmp.next;
    tmp.next = new ListNode(4);
    tmp = tmp.next;
    tmp.next = new ListNode(5);
    int[] expected = new int[] {1,4,3,2,5};
    head = tester.reverseListBetween(head, 2,4);
    for(int i = 0; i < expected.length; i++){
      Assert.assertNotNull(head);
      Assert.assertEquals(expected[i], head.val);
      head = head.next;
    }
    head = new ListNode(3);
    head.next = new ListNode(5);
    head = tester.reverseListBetween(head,1,2);
    Assert.assertEquals(5, head.val);
    Assert.assertEquals(3, head.next.val);
    Assert.assertNull(tester.reverseListBetween(null, 1,2));
    Assert.assertEquals(1, tester.reverseListBetween(new ListNode(1),1,2).val);
  }

  @Test
  public void mySqrt() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(2, tester.mySqrt(4));
    Assert.assertEquals(7, tester.mySqrt(49));
    Assert.assertEquals(2, tester.mySqrt(8));
    Assert.assertEquals(0, tester.mySqrt(0));
  }

  @Test
  public void spiralOrder() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[][] testMatrix;
    int[] expectedResult;
    List<Integer> actualResult;

    testMatrix = new int[][] {{6,9,7}};
    expectedResult = new int[] {6,9,7};
    actualResult = tester.spiralOrder(testMatrix);
    Assert.assertEquals(expectedResult.length, actualResult.size());
    for(int i = 0; i < expectedResult.length; i ++){
      Assert.assertEquals(expectedResult[i], (int)actualResult.get(i));
    }

    testMatrix = new int[][] {{6},{9},{7}};
    actualResult = tester.spiralOrder(testMatrix);
    Assert.assertEquals(expectedResult.length, actualResult.size());
    for(int i = 0; i < expectedResult.length; i ++){
      Assert.assertEquals(expectedResult[i], (int)actualResult.get(i));
    }

    testMatrix = new int[][] {{1,2,3,4},
        {5,6,7,8},{9,10,11,12},{13,14,15,16}};
    expectedResult = new int[] {1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10};
    actualResult = tester.spiralOrder(testMatrix);
    Assert.assertEquals(expectedResult.length, actualResult.size());
    for(int i = 0; i < expectedResult.length; i ++){
      Assert.assertEquals(expectedResult[i], (int)actualResult.get(i));
    }

    testMatrix = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
    expectedResult = new int[] {1,2,3,4,8,12,11,10,9,5,6,7};
    actualResult = tester.spiralOrder(testMatrix);
    Assert.assertEquals(expectedResult.length, actualResult.size());
    for(int i = 0; i < expectedResult.length; i ++){
      Assert.assertEquals(expectedResult[i], (int)actualResult.get(i));
    }

    testMatrix = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
    expectedResult = new int[] {1,2,3,6,9,8,7,4,5};
    actualResult = tester.spiralOrder(testMatrix);
    Assert.assertEquals(expectedResult.length, actualResult.size());
    for(int i = 0; i < expectedResult.length; i ++){
      Assert.assertEquals(expectedResult[i], (int)actualResult.get(i));
    }

    Assert.assertTrue(tester.spiralOrder(new int[][] {{}}).isEmpty());
  }

  @Test
  public void singleNumber() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(1, tester.singleNumber(new int[] {2,2,1}));
    Assert.assertEquals(4, tester.singleNumber(new int[] {4,2,1,3,3,2,1}));
  }

  @Test
  public void altSingleNumber() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(1, tester.altSingleNumber(new int[] {2,2,1}));
    Assert.assertEquals(4, tester.altSingleNumber(new int[] {4,2,1,3,3,2,1}));
  }

  @Test
  public void singleNumberTwo() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(1, tester.singleNumberTwo(new int[] {2,2,2,1}));
    Assert.assertEquals(4, tester.singleNumberTwo(new int[] {4,2,1,2,3,3,2,1,3,1}));
    Assert.assertEquals(0, tester.singleNumberTwo(new int[] {0}));
  }

  @Test
  public void sumNumbers() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    TreeNode head;

    head = new TreeNode(4);
    head.left = new TreeNode(9);
    head.left.left = new TreeNode(5);
    head.left.right = new TreeNode(1);
    head.right = new TreeNode(0);
    Assert.assertEquals(1026, tester.sumNumbers(head));

    head = new TreeNode(1);
    head.left = new TreeNode(2);
    head.right = new TreeNode(3);
    Assert.assertEquals(25, tester.sumNumbers(head));

    Assert.assertEquals(0, tester.sumNumbers(null));

    Assert.assertEquals(5, tester.sumNumbers(new TreeNode(5)));
  }

  @Test
  public void zigzagLevelOrder() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    TreeNode head;
    List<List<Integer>> results;
    List<int[]> expectedResults;

    head = new TreeNode(3);
    head.left = new TreeNode(9);
    head.right = new TreeNode(20);
    head.right.left = new TreeNode(15);
    head.right.right = new TreeNode(7);
    results = tester.zigzagLevelOrder(head);
    expectedResults = new ArrayList<>();
    expectedResults.add(new int[] {3});
    expectedResults.add(new int[] {20,9});
    expectedResults.add(new int[] {15,7});
    Assert.assertEquals(expectedResults.size(), results.size());
    for(int i = 0; i<expectedResults.size(); i++){
      Assert.assertEquals(expectedResults.get(i).length, results.get(i).size());
      for(int j = 0; j < expectedResults.get(i).length; j++){
        Assert.assertEquals(expectedResults.get(i)[j], (int)results.get(i).get(j));
      }
    }

    Assert.assertTrue(tester.zigzagLevelOrder(null).isEmpty());
  }

  @Test
  public void captureOVals() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    char[][] testMatrix;
    char[][] expectedResult;

    testMatrix = new char[][]
        {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
    expectedResult = new char[][]
        {{'X','X','X','X'},{'X','X','X','X'},{'X','X','X','X'},{'X','O','X','X'}};
    tester.captureOVals(testMatrix);
    for(int i = 0; i < testMatrix.length; i++){
      for(int j = 0; j < testMatrix[0].length; j++){
        Assert.assertEquals(expectedResult[i][j], testMatrix[i][j]);
      }
    }

    testMatrix = new char[][] {{'X','O','X'}};
    expectedResult = new char[][] {{'X','O','X'}};
    tester.captureOVals(testMatrix);
    for(int i = 0; i < testMatrix.length; i++){
      for(int j = 0; j < testMatrix[0].length; j++){
        Assert.assertEquals(expectedResult[i][j], testMatrix[i][j]);
      }
    }
    testMatrix = new char[][] {{'X'},{'O'},{'X'}};
    expectedResult = new char[][] {{'X'},{'O'},{'X'}};
    tester.captureOVals(testMatrix);
    for(int i = 0; i < testMatrix.length; i++){
      for(int j = 0; j < testMatrix[0].length; j++){
        Assert.assertEquals(expectedResult[i][j], testMatrix[i][j]);
      }
    }

    testMatrix = new char[][] {{}};
    expectedResult = new char[][] {{}};
    tester.captureOVals(testMatrix);
    for(int i = 0; i < testMatrix.length; i++){
      for(int j = 0; j < testMatrix[0].length; j++){
        Assert.assertEquals(expectedResult[i][j], testMatrix[i][j]);
      }
    }
  }

  @Test
  public void wordBreak() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    List<String> wordDict = new ArrayList<>();

    wordDict.clear();
    wordDict.add("a");
    wordDict.add("aa");
    wordDict.add("aaa");
    wordDict.add("aaaa");
    wordDict.add("aaaaa");
    wordDict.add("aaaaaa");
    wordDict.add("aaaaaaa");
    wordDict.add("aaaaaaaa");
    wordDict.add("aaaaaaaaa");
    wordDict.add("aaaaaaaaaa");
    String testStr = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
    Assert.assertFalse(tester.wordBreak(testStr, wordDict));

    wordDict.clear();
    wordDict.add("cat");
    wordDict.add("sand");
    wordDict.add("cats");
    wordDict.add("dog");
    wordDict.add("and");
    Assert.assertFalse(tester.wordBreak("catsandog", wordDict));

    wordDict.add("leet");
    wordDict.add("code");
    Assert.assertTrue(tester.wordBreak("leetcode", wordDict));

    wordDict.clear();

    wordDict.add("apple");
    wordDict.add("pen");
    Assert.assertTrue(tester.wordBreak("applepenapple", wordDict));
  }

  @Test
  public void findMode() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(5, tester.findMode(4,2,3,5,5));
    Assert.assertEquals(5, tester.findMode(4,5,4,5,5));
  }

  @Test
  public void sortedArrayToBST() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Queue<TreeNode> treeLevel = new LinkedList<>();
    treeLevel.add(tester.sortedArrayToBST(new int[] {-10,-3,0,5,9}));
    int[] expectedResult = new int[] {0,-10,5,-3,9};
    int counter = 0;
    while(!treeLevel.isEmpty()){
      Queue<TreeNode> tmpQueue = new LinkedList<>();
      while(treeLevel.peek()!=null){
        TreeNode tmp = treeLevel.poll();
        Assert.assertEquals(expectedResult[counter], tmp.val);
        counter++;
        if(tmp.left!=null){tmpQueue.offer(tmp.left);}
        if(tmp.right!=null){tmpQueue.offer(tmp.right);}
      }
      treeLevel = tmpQueue;
    }

    Assert.assertNull(tester.sortedArrayToBST(new int[] {}));
  }

  @Test
  public void maxProfit() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(0, tester.maxProfit(new int[] {}));
    Assert.assertEquals(0, tester.maxProfit(new int[] {7,6,4,3,1}));
    Assert.assertEquals(4, tester.maxProfit(new int[] {3,2,6,5,0,3}));
    Assert.assertEquals(5, tester.maxProfit(new int[] {7,1,5,3,6,4}));
  }

  @Test
  public void connectTreeLevels() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    TreeLinkNode head = new TreeLinkNode(1);
    head.left = new TreeLinkNode(2);
    head.right = new TreeLinkNode(3);
    head.left.left = new TreeLinkNode(4);
    head.left.right = new TreeLinkNode(5);
    head.right.left = new TreeLinkNode(6);
    head.right.right = new TreeLinkNode(7);
    tester.connectTreeLevels(head);

    List<int[]> expectedResult = new ArrayList<>();
    expectedResult.add(new int[] {1});
    expectedResult.add(new int[] {2,3});
    expectedResult.add(new int[] {4,5,6,7});

    TreeLinkNode tmp = head;
    for(int[] curLevel: expectedResult){
      TreeLinkNode nextTmp = tmp.left;
      for(int i = 0; i < curLevel.length; i++){
        Assert.assertEquals(curLevel[i], tmp.val);
        tmp = tmp.next;
      }
      tmp = nextTmp;
    }
  }

  @Test
  public void recursConnectTreeLevels() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    TreeLinkNode head = new TreeLinkNode(1);
    head.left = new TreeLinkNode(2);
    head.right = new TreeLinkNode(3);
    head.left.left = new TreeLinkNode(4);
    head.left.right = new TreeLinkNode(5);
    head.right.left = new TreeLinkNode(6);
    head.right.right = new TreeLinkNode(7);
    tester.recursConnectTreeLevels(head);

    List<int[]> expectedResult = new ArrayList<>();
    expectedResult.add(new int[] {1});
    expectedResult.add(new int[] {2,3});
    expectedResult.add(new int[] {4,5,6,7});

    TreeLinkNode tmp = head;
    for(int[] curLevel: expectedResult){
      TreeLinkNode nextTmp = tmp.left;
      for(int i = 0; i < curLevel.length; i++){
        Assert.assertEquals(curLevel[i], tmp.val);
        tmp = tmp.next;
      }
      tmp = nextTmp;
    }
  }

  @Test
  public void longestConsecutive() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(4, tester.longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
    Assert.assertEquals(0, tester.longestConsecutive(new int[] {}));
    Assert.assertEquals(1, tester.longestConsecutive(new int[] {1}));
    Assert.assertEquals(1, tester.longestConsecutive(new int[] {1,11,21,31,41,51,61,71,81,91,101}));
  }

  @Test
  public void generatePascalTriangle() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    List<List<Integer>> actualResult;
    List<int[]> expectedResult = new ArrayList<>();

    Assert.assertTrue(tester.generatePascalTriangle(0).isEmpty());
    Assert.assertTrue(tester.generatePascalTriangle(-5).isEmpty());

    actualResult = tester.generatePascalTriangle(5);
    expectedResult.add(new int[] {1});
    expectedResult.add(new int[] {1,1});
    expectedResult.add(new int[] {1,2,1});
    expectedResult.add(new int[] {1,3,3,1});
    expectedResult.add(new int[] {1,4,6,4,1});
    Assert.assertEquals(expectedResult.size(), actualResult.size());
    for(int i = 0; i < expectedResult.size(); i++){
      Assert.assertArrayEquals(expectedResult.get(i), actualResult.get(i).stream().mapToInt(j->j).toArray());
    }
  }

  @Test
  public void getPascalRow() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] expectedResult;
    int[] result;

    expectedResult = new int[] {1,4,6,4,1};
    result = tester.getPascalRow(5).stream().mapToInt(i->i).toArray();
    Assert.assertArrayEquals(expectedResult,result);

    Assert.assertArrayEquals(new int[] {1}, tester.getPascalRow(1).stream().mapToInt(i->i).toArray());
    Assert.assertArrayEquals(new int[] {1,1}, tester.getPascalRow(2).stream().mapToInt(i->i).toArray());
    Assert.assertTrue(tester.getPascalRow(0).isEmpty());
    Assert.assertTrue(tester.getPascalRow(-2).isEmpty());
  }

  @Test
  public void listHasCycle() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    ListNode head = new ListNode(3);
    head.next = new ListNode(4);
    head.next.next = new ListNode(5);
    ListNode tmp = head.next.next;
    tmp.next = new ListNode(1);
    tmp.next.next = new ListNode(2);
    tmp.next.next.next = tmp;

    Assert.assertTrue(tester.listHasCycle(head));
    Assert.assertFalse(tester.listHasCycle(new ListNode(0)));
  }

  @Test
  public void flatten() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    TreeNode root;

    root = new TreeNode(2);
    tester.flatten(root);
    Assert.assertTrue(root.left==null&&root.right==null);

    root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.right = new TreeNode(5);
    root.right.right = new TreeNode(6);
    tester.flatten(root);

    TreeNode tmp = root;
    for(int i = 1; i <= 6; i++){
      Assert.assertNotNull(tmp);
      Assert.assertEquals(i, tmp.val);
      tmp = tmp.right;
    }
  }

  @Test
  public void altFlatten() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    TreeNode root;

    root = new TreeNode(2);
    tester.altFlatten(root);
    Assert.assertTrue(root.left==null&&root.right==null);

    root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.right = new TreeNode(5);
    root.right.right = new TreeNode(6);
    tester.altFlatten(root);

    TreeNode tmp = root;
    for(int i = 1; i <= 6; i++){
      Assert.assertNotNull(tmp);
      Assert.assertEquals(i, tmp.val);
      tmp = tmp.right;
    }
  }

  @Test
  public void detectCycle() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertNull(tester.detectCycle(new ListNode(1)));

    ListNode root = new ListNode(1);
    root.next = new ListNode(2);
    root.next.next = new ListNode(3);
    ListNode tmp = root.next.next;
    tmp.next = new ListNode(6);
    tmp = tmp.next;
    tmp.next = new ListNode(5);
    tmp = tmp.next;
    tmp.next = new ListNode(4);
    tmp = tmp.next;
    tmp.next = root.next.next;

    Assert.assertEquals(3, tester.detectCycle(root).val);
  }

  @Test
  public void findRepeatedDNASequences() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    List<String> results;
    List<String> expectedResults;

    results = tester.findRepeatedDnaSequences("");
    Assert.assertTrue(results.isEmpty());

    results = tester.findRepeatedDnaSequences("AAAAAAAAAAAA");
    expectedResults = new ArrayList<>();
    expectedResults.add("AAAAAAAAAA");
    Assert.assertEquals(expectedResults.size(), results.size());
    Assert.assertTrue(expectedResults.get(0).equals(results.get(0)));

    results = tester.findRepeatedDnaSequences("AAAAAAAAAAA");
    expectedResults = new ArrayList<>();
    expectedResults.add("AAAAAAAAAA");
    Assert.assertEquals(expectedResults.size(), results.size());
    Assert.assertTrue(expectedResults.get(0).equals(results.get(0)));

    results = tester.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
    expectedResults = new ArrayList<>();
    expectedResults.add("AAAAACCCCC");
    expectedResults.add("CCCCCAAAAA");

    Assert.assertEquals(expectedResults.size(), results.size());
    for(int i = 0; i < expectedResults.size(); i++){
      Assert.assertTrue(expectedResults.get(i).equals(results.get(i)));
    }
  }

  @Test
  public void majorityElement() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(-1, tester.majorityElement(new int[] {}));
    Assert.assertEquals(3, tester.majorityElement(new int[] {3,2,3}));
    Assert.assertEquals(2, tester.majorityElement(new int[] {2,2,1,1,1,2,2}));
    Assert.assertEquals(3, tester.majorityElement(new int[] {3,3,4}));
    Assert.assertEquals(7, tester.majorityElement(new int[] {8,8,7,7,7}));
  }

  @Test
  public void altMajorityElement() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(7, tester.altMajorityElement(new int[] {8,8,7,7,7}));
    Assert.assertEquals(3, tester.altMajorityElement(new int[] {3,3,4}));
    Assert.assertEquals(-1, tester.altMajorityElement(new int[] {}));
    Assert.assertEquals(3, tester.altMajorityElement(new int[] {3,2,3}));
    Assert.assertEquals(2, tester.altMajorityElement(new int[] {2,2,1,1,1,2,2}));
  }

  @Test
  public void twoSumSorted() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertArrayEquals(new int[] {1,2}, tester.twoSumSorted(new int[] {2,7,11,15}, 9));
    Assert.assertArrayEquals(new int[] {5,8}, tester.twoSumSorted(new int[] {-5,-3,-1,0,3,4,6,8}, 11));
    Assert.assertArrayEquals(new int[] {2,7}, tester.twoSumSorted(new int[] {-9,-4,-2,-1,0,3,4,7,8,11}, 0));
  }

  @Test
  public void altTwoSumSorted() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertArrayEquals(new int[] {1,2}, tester.altTwoSumSorted(new int[] {2,7,11,15}, 9));
    Assert.assertArrayEquals(new int[] {5,8}, tester.altTwoSumSorted(new int[] {-5,-3,-1,0,3,4,6,8}, 11));
    Assert.assertArrayEquals(new int[] {2,7}, tester.altTwoSumSorted(new int[] {-9,-4,-2,-1,0,3,4,7,8,11}, 0));
  }

  @Test
  public void addDigits() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(5, tester.addDigits(5));
    Assert.assertEquals(2, tester.addDigits(38));
    Assert.assertEquals(6, tester.addDigits(12345));
    Assert.assertEquals(9, tester.addDigits(999999));
  }

  @Test
  public void mergeDuplicateContacts() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    List<Contact> contactData;
    List<List<String>> emails;
    List<String> names;


    names = new ArrayList<>();
    emails = new ArrayList<>();
    String[] nameStarts = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};

    for(int i = 0; i < nameStarts.length; i++) {
      names.add(nameStarts[i]);
      List<String> curEmails = new ArrayList<>();
      for (int j = 0; j < nameStarts.length; j++) {
        curEmails.add(nameStarts[i] + nameStarts[j]);
      }
      emails.add(curEmails);
    }

    contactData = new ArrayList<>();
    Random rnd = new Random();
    for(int i = 0; i < nameStarts.length; i++){
      for(int j = 0; j < 5; j++){
        Contact curContact = new Contact(nameStarts[i], new ArrayList<>());
        curContact.emails.add(emails.get(i).get(rnd.nextInt(emails.get(i).size())));
        curContact.emails.add(emails.get(i).get(rnd.nextInt(emails.get(i).size())));
        curContact.emails.add(emails.get(i).get(rnd.nextInt(emails.get(i).size())));
        curContact.emails.add(emails.get(i).get(rnd.nextInt(emails.get(i).size())));
        contactData.add(curContact);
      }
    }
    Collections.shuffle(contactData);
    List<Contact> results = tester.mergeDuplicateContacts(contactData);
    Map<String, Set<String>> nameToEmails = new HashMap<>();
    for(int i = 0; i < nameStarts.length; i++){
      Set<String> emailSet = new HashSet<>();
      for(String email: emails.get(i)){
        emailSet.add(email);
      }
      nameToEmails.put(nameStarts[i], emailSet);
    }

    for(int i = 0; i < results.size(); i++){
      String curName = results.get(i).name;
      List<String> curEmailList = results.get(i).emails;
      for(int j = 0; j < curEmailList.size(); j++){
        Assert.assertTrue(nameToEmails.get(curName).contains(curEmailList.get(j)));
      }
    }
  }

  @Test
  public void canVisitAllRooms() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    List<List<Integer>> rooms;
    List<Integer> tmpRoom;
    int[][] roomsAsMatrix;

    Assert.assertTrue(tester.canVisitAllRooms(new ArrayList<>()));

    roomsAsMatrix = new int[][] {{1},{2},{3},{-1}};
    rooms = new ArrayList<>();

    for(int i = 0; i < roomsAsMatrix.length; i++){
      tmpRoom = new ArrayList<>();
      for(int j = 0; j < roomsAsMatrix[0].length; j++){
        if(roomsAsMatrix[i][j]!=-1){
          tmpRoom.add(roomsAsMatrix[i][j]);
        }
      }
      rooms.add(tmpRoom);
    }

    Assert.assertTrue(tester.canVisitAllRooms(rooms));

    roomsAsMatrix = new int[][] {{2}, {-1}, {1}};
    rooms = new ArrayList<>();

    for(int i = 0; i < roomsAsMatrix.length; i++){
      tmpRoom = new ArrayList<>();
      for(int j = 0; j < roomsAsMatrix[0].length; j++){
        if(roomsAsMatrix[i][j]!=-1){
          tmpRoom.add(roomsAsMatrix[i][j]);
        }
      }
      rooms.add(tmpRoom);
    }

    Assert.assertTrue(tester.canVisitAllRooms(rooms));


    roomsAsMatrix = new int[][] {{1,3,-1}, {3,0,1}, {2,-1,-1}, {0,-1,-1}};
    rooms = new ArrayList<>();

    for(int i = 0; i < roomsAsMatrix.length; i++){
      tmpRoom = new ArrayList<>();
      for(int j = 0; j < roomsAsMatrix[0].length; j++){
        if(roomsAsMatrix[i][j]!=-1){
          tmpRoom.add(roomsAsMatrix[i][j]);
        }
      }
      rooms.add(tmpRoom);
    }

    Assert.assertFalse(tester.canVisitAllRooms(rooms));
  }

  @Test
  public void moveZeroes() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] result;
    int[] expectedResult;

    result = new int[] {0,1,0,3,12};
    expectedResult = new int[] {1,3,12,0,0};
    tester.moveZeroes(result);
    Assert.assertArrayEquals(expectedResult, result);

    result = new int[] {};
    tester.moveZeroes(result);
    Assert.assertTrue(result.length==0);

    result = new int[] {1};
    expectedResult = new int[] {1};
    tester.moveZeroes(result);
    Assert.assertArrayEquals(expectedResult, result);

    result = new int[] {0};
    expectedResult = new int[] {0};
    tester.moveZeroes(result);
    Assert.assertArrayEquals(expectedResult, result);

    result = new int[] {0,1};
    expectedResult = new int[] {1,0};
    tester.moveZeroes(result);
    Assert.assertArrayEquals(expectedResult, result);
  }

  @Test
  public void intersectionArgTest() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertArrayEquals(new int [] {}, tester.intersection(new int[] {1,2}, new int[] {}));
    int[] result = tester.intersection(new int[] {1,2,0,3,4,5,9,34,62,63,12}, new int[] {2,2,2,2,1,2,2,2,2,2});
    Assert.assertEquals(2, result.length);
    Assert.assertTrue((result[0]==2&&result[1]==1)||(result[0]==1&&result[1]==2));
  }

  @Test
  public void calcEquation() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    String[][] equations;
    double[] values;
    String[][] queries;
    double results[];
    double[] expectedResults;

    equations = new String[][] {{"a", "e"}, {"b", "e"}};
    values = new double[] {4.0, 3.0};
    queries = new String[][] {{"a", "b"}, {"e", "e"}, {"x", "x"}};
    expectedResults = new double[] {1.333, 1.0, -1.0};
    results = tester.calcEquation(equations, values, queries);
    Assert.assertEquals(expectedResults.length, results.length);
    for(int i = 0; i < results.length; i++){
      Assert.assertEquals(expectedResults[i], results[i], 0.009);
    }

    equations = new String[][] {{"a", "b"}, {"b", "c"}};
    values = new double[] {2.0, 3.0};
    queries = new String[][] {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
    expectedResults = new double[] {6.0, .5, -1.0, 1.0, -1.0};
    results = tester.calcEquation(equations, values, queries);

    Assert.assertEquals(expectedResults.length, results.length);
    for(int i = 0; i < results.length; i++){
      Assert.assertEquals(expectedResults[i], results[i], 0.009);
    }

    equations = new String[][] {{"a", "b"}, {"b", "c"}, {"bc", "cd"}};
    values = new double[] {1.5, 2.5, 5.0};
    queries = new String[][] {{"a", "c"}, {"c", "b"}, {"bc", "cd"}, {"cd", "bc"}};
    expectedResults = new double[] {3.75, 0.4, 5.0, 0.2};
    results = tester.calcEquation(equations, values, queries);
    Assert.assertEquals(expectedResults.length, results.length);
    for(int i = 0; i < results.length; i++){
      Assert.assertEquals(expectedResults[i], results[i], 0.009);
    }
  }

  @Test
  public void unconnectedCalcEquation() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    String[][] equations;
    double[] values;
    String[][] queries;
    double results[];
    double[] expectedResults;

    equations = new String[][] {{"a", "e"}, {"b", "e"}};
    values = new double[] {4.0, 3.0};
    queries = new String[][] {{"a", "b"}, {"e", "e"}, {"x", "x"}};
    expectedResults = new double[] {1.333, 1.0, -1.0};
    results = tester.unconnectedCalcEquation(equations, values, queries);
    Assert.assertEquals(expectedResults.length, results.length);
    for(int i = 0; i < results.length; i++){
      Assert.assertEquals(expectedResults[i], results[i], 0.009);
    }

    equations = new String[][] {{"a", "b"}, {"b", "c"}};
    values = new double[] {2.0, 3.0};
    queries = new String[][] {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
    expectedResults = new double[] {6.0, .5, -1.0, 1.0, -1.0};
    results = tester.unconnectedCalcEquation(equations, values, queries);

    Assert.assertEquals(expectedResults.length, results.length);
    for(int i = 0; i < results.length; i++){
      Assert.assertEquals(expectedResults[i], results[i], 0.009);
    }

    equations = new String[][] {{"a", "b"}, {"b", "c"}, {"bc", "cd"}};
    values = new double[] {1.5, 2.5, 5.0};
    queries = new String[][] {{"a", "c"}, {"c", "b"}, {"bc", "cd"}, {"cd", "bc"}};
    expectedResults = new double[] {3.75, 0.4, 5.0, 0.2};
    results = tester.unconnectedCalcEquation(equations, values, queries);
    Assert.assertEquals(expectedResults.length, results.length);
    for(int i = 0; i < results.length; i++){
      Assert.assertEquals(expectedResults[i], results[i], 0.009);
    }
  }

  @Test
  public void isPerfectSquare() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertTrue(tester.isPerfectSquare(64516));
    Assert.assertTrue(tester.isPerfectSquare(1));
    Assert.assertTrue(tester.isPerfectSquare(16));
    Assert.assertFalse(tester.isPerfectSquare(14));
  }

  @Test
  public void bulbSwitch() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(1, tester.bulbSwitch(3));
    Assert.assertEquals(316, tester.bulbSwitch(99999));
    Assert.assertEquals(0, tester.bulbSwitch(-3));
    Assert.assertEquals(0, tester.bulbSwitch(0));
  }

  @Test
  public void mathBulbSwitch() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(1, tester.mathBulbSwitch(3));
    Assert.assertEquals(316, tester.mathBulbSwitch(99999));
    Assert.assertEquals(0, tester.mathBulbSwitch(-3));
    Assert.assertEquals(0, tester.mathBulbSwitch(0));
  }

  @Test
  public void isPowerOfThree() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertTrue(tester.isPowerOfThree(9));
    Assert.assertFalse(tester.isPowerOfThree(0));
    Assert.assertFalse(tester.isPowerOfThree(199));
  }

  @Test
  public void reverseVowels() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertTrue("holle".equals(tester.reverseVowels("hello")));
    Assert.assertTrue("leotcede".equals(tester.reverseVowels("leetcode")));
    Assert.assertTrue("cross".equals(tester.reverseVowels("cross")));
  }

  @Test
  public void sumOfLeftLeaves() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    Assert.assertEquals(24, tester.sumOfLeftLeaves(root));
    Assert.assertEquals(0, tester.sumOfLeftLeaves(null));
  }

  @Test
  public void altSumOfLeftLeaves() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    Assert.assertEquals(24, tester.altSumOfLeftLeaves(root));
    Assert.assertEquals(0, tester.altSumOfLeftLeaves(null));
  }

  @Test
  public void kthSmallest() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[][] testedMatrix;

    testedMatrix = new int[][] {{1,5,9}, {10,11,13}, {12, 13, 15}};
    Assert.assertEquals(13, tester.kthSmallest(testedMatrix, 8));
    Assert.assertEquals(0, tester.kthSmallest(testedMatrix, 0));
    Assert.assertEquals(0, tester.kthSmallest(testedMatrix, -3));

    testedMatrix = new int[][] {{}};
    Assert.assertEquals(0, tester.kthSmallest(testedMatrix, 3));

    testedMatrix = new int[][] {{-5}};
    Assert.assertEquals(-5, tester.kthSmallest(testedMatrix, 1));
  }

  @Test
  public void altKthSmallest() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[][] testedMatrix;

    testedMatrix = new int[][] {{1,5,9}, {10,11,13}, {12, 13, 15}};
    Assert.assertEquals(13, tester.altKthSmallest(testedMatrix, 8));
    Assert.assertEquals(0, tester.altKthSmallest(testedMatrix, 0));
    Assert.assertEquals(0, tester.altKthSmallest(testedMatrix, -3));

    testedMatrix = new int[][] {{}};
    Assert.assertEquals(0, tester.altKthSmallest(testedMatrix, 3));

    testedMatrix = new int[][] {{-5}};
    Assert.assertEquals(-5, tester.altKthSmallest(testedMatrix, 1));
  }

  @Test
  public void phoneNumsToStrings() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    tester.phoneNumsToStrings("15230");
    tester.phoneNumsToStrings("1");
  }

  @Test
  public void altPhoneNumsToStrings() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    tester.altPhoneNumsToStrings("15230");
    tester.altPhoneNumsToStrings("1");
  }

  @Test
  public void sumArrays() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] firstArr;
    int[] secondArr;
    int[] expectedResult;

    firstArr = new int[] {5,5,5,5,5,5};
    secondArr = new int[] {5,5,5,5,5,5};
    expectedResult = new int[] {1,1,1,1,1,1,0};
    Assert.assertArrayEquals(expectedResult, tester.sumArrays(firstArr, secondArr));

    firstArr = new int[] {1};
    secondArr = new int[] {9,9,9};
    expectedResult = new int[] {1,0,0,0};
    Assert.assertArrayEquals(expectedResult, tester.sumArrays(firstArr, secondArr));

    firstArr = new int[] {};
    secondArr = new int[] {9,9,9};
    expectedResult = new int[] {9,9,9};
    Assert.assertArrayEquals(expectedResult, tester.sumArrays(firstArr, secondArr));
  }

  @Test
  public void findTheDifference() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals('e', tester.findTheDifference("abcde", "abcd"));
    Assert.assertEquals('t', tester.findTheDifference("ymbgaraibkfmvocpizdydugvalagaivdbfsfbepeyccq"
        + "fepzvtpyxtbadkhmwmoswrcxnargtlswqemafandgkmydtimuzvjwxvlfwlhvkrgcsithaqlcvrihrwqkpj"
        + "dhgfgreqoxzfvhjzojhghfwbvpfzectwwhexthbsndovxejsntmjihchaotbgcysfdaojkjldprwyrnischr"
        + "gmtvjcorypvopfmegizfkvudubnejzfqffvgdoxohuinkyygbdzmshvyqyhsozwvlhevfepdvafgkqpkmcsik"
        + "fyxczcovrmwqxxbnhfzcjjcpgzjjfateajnnvlbwhyppdleahgaypxidkpwmfqwqyofwdqgxhjaxvyrzupfwes"
        + "mxbjszolgwqvfiozofncbohduqgiswuiyddmwlwubetyaummenkdfptjczxemryuotrrymrfdxtrebpbjtpnuhs"
        + "bnovhectpjhfhahbqrfbyxggobsweefcwxpqsspyssrmdhuelkkvyjxswjwofngpwfxvknkjviiavorwyfzlnk"
        + "tmfwxkvwkrwdcxjfzikdyswsuxegmhtnxjraqrdchaauazfhtklxsksbhwgjphgbasfnlwqwukprgvihntsyym"
        + "drfovaszjywuqygpvjtvlsvvqbvzsmgweiayhlubnbsitvfxawhfmfiatxvqrcwjshvovxknnxnyyfexqycrly"
        + "ksderlqarqhkxyaqwlwoqcribumrqjtelhwdvaiysgjlvksrfvjlcaiwrirtkkxbwgicyhvakxgdjwnwmubkia"
        + "zdjkfmotglclqndqjxethoutvjchjbkoasnnfbgrnycucfpeovruguzumgmgddqwjgdvaujhyqsqtoexmnfulu"
        + "aqbxoofvotvfoiexbnprrxptchmlctzgqtkivsilwgwgvpidpvasurraqfkcmxhdapjrlrnkbklwkrvoaziznl"
        + "por", "qhxepbshlrhoecdaodgpousbzfcqjxulatciapuftffahhlmxbufgjuxstfjvljybfxnenlacmjqoym"
        + "vamphpxnolwijwcecgwbcjhgdybfffwoygikvoecdggplfohemfypxfsvdrseyhmvkoovxhdvoavsqqbrsqrkq"
        + "hbtmgwaurgisloqjixfwfvwtszcxwktkwesaxsmhsvlitegrlzkvfqoiiwxbzskzoewbkxtphapavbyvhzvgrr"
        + "friddnsrftfowhdanvhjvurhljmpxvpddxmzfgwwpkjrfgqptrmumoemhfpojnxzwlrxkcafvbhlwrapubhvea"
        + "ttfifsmiounhqusvhywnxhwrgamgnesxmzliyzisqrwvkiyderyotxhwspqrrkeczjysfujvovsfcfouykcqyj"
        + "oobfdgnlswfzjmyucaxuaslzwfnetekymrwbvponiaojdqnbmboldvvitamntwnyaeppjaohwkrisrlrgwcjqq"
        + "gxeqerjrbapfzurcwxhcwzugcgnirkkrxdthtbmdqgvqxilllrsbwjhwqszrjtzyetwubdrlyakzxcveufvhqu"
        + "gyawvkivwonvmrgnchkzdysngqdibhkyboyftxcvvjoggecjsajbuqkjjxfvynrjsnvtfvgpgveycxidhhfauv"
        + "jovmnbqgoxsafknluyimkczykwdgvqwlvvgdmufxdypwnajkncoynqticfetcdafvtqszuwfmrdggifokwmkgz"
        + "uxnhncmnsstffqpqbplypapctctfhqpihavligbrutxmmygiyaklqtakdidvnvrjfteazeqmbgklrgrorudayo"
        + "kxptswwkcircwuhcavhdparjfkjypkyxhbgwxbkvpvrtzjaetahmxevmkhdfyidhrdeejapfbafwmdqjqszwnw"
        + "zgclitdhlnkaiyldwkwwzvhyorgbysyjbxsspnjdewjxbhpsvj"));
  }

  @Test
  public void isSubsequence() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertTrue(tester.isSubsequence("abc", "aasdadsbadjnkc"));
    Assert.assertTrue(tester.isSubsequence("", "literallyanything"));
    Assert.assertFalse(tester.isSubsequence("line", ""));
  }

  @Test
  public void altIsSubsequence() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertTrue(tester.altIsSubsequence("abc", "aasdadsbadjnkc"));
    Assert.assertTrue(tester.altIsSubsequence("", "literallyanything"));
    Assert.assertFalse(tester.altIsSubsequence("line", ""));
  }

  @Test
  public void romanToInt() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(3, tester.romanToInt("III"));
    Assert.assertEquals(8, tester.romanToInt("VIII"));
    Assert.assertEquals(58, tester.romanToInt("LVIII"));
    Assert.assertEquals(27, tester.romanToInt("XXVII"));
    Assert.assertEquals(1994, tester.romanToInt("MCMXCIV"));
  }

  @Test
  public void altRomanToInt() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(3, tester.altRomanToInt("III"));
    Assert.assertEquals(8, tester.altRomanToInt("VIII"));
    Assert.assertEquals(58, tester.altRomanToInt("LVIII"));
    Assert.assertEquals(27, tester.altRomanToInt("XXVII"));
    Assert.assertEquals(1994, tester.altRomanToInt("MCMXCIV"));
  }

  @Test
  public void addStrings() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    String firstStr;
    String secondStr;
    String expectedResult;

    firstStr = "1";
    secondStr = "999";
    expectedResult = "1000";
    Assert.assertTrue(expectedResult.equals(tester.addStrings(firstStr, secondStr)));

    firstStr = "555555";
    secondStr = "555555";
    expectedResult = "1111110";
    Assert.assertTrue(expectedResult.equals(tester.addStrings(firstStr, secondStr)));

    firstStr = "";
    secondStr = "999";
    expectedResult = "999";
    Assert.assertTrue(expectedResult.equals(tester.addStrings(firstStr, secondStr)));
  }

  @Test
  public void thirdMax() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(Integer.MIN_VALUE, tester.thirdMax(new int[] {1, 2, Integer.MIN_VALUE}));
    Assert.assertEquals(-1, tester.thirdMax(new int[] {1,2,-1}));
    Assert.assertEquals(1, tester.thirdMax(new int[] {1,2,3}));
    Assert.assertEquals(3, tester.thirdMax(new int[] {3,3,2}));
  }

  @Test
  public void guessNumber() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    GuessNumber gn;

    gn = new GuessNumber(123144);
    Assert.assertEquals(123144, tester.guessNumber(999999, gn));
    gn = new GuessNumber(1);
    Assert.assertEquals(1, tester.guessNumber(1, gn));
    gn = new GuessNumber(Integer.MAX_VALUE);
    Assert.assertEquals(Integer.MAX_VALUE, tester.guessNumber(Integer.MAX_VALUE, gn));
  }

  @Test
  public void reconstructQueue() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[][] randomizedQueue;
    int[][] organizedQueue;

    randomizedQueue = new int[][] {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
    organizedQueue = new int[][] {{5,0}, {7,0}, {5,2},{6,1},{4,4},{7,1}};
    randomizedQueue = tester.reconstructQueue(randomizedQueue);
    for(int i=0; i<randomizedQueue.length; i++){
      for(int j=0; j<randomizedQueue[0].length; j++){
        Assert.assertEquals(organizedQueue[i][j], randomizedQueue[i][j]);
      }
    }
    randomizedQueue = new int[0][0];
    organizedQueue = new int[0][0];
    randomizedQueue = tester.reconstructQueue(randomizedQueue);
    Assert.assertEquals(organizedQueue.length,randomizedQueue.length);
  }

  @Test
  public void reconstructQueueTakeTwo() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[][] randomizedQueue;
    int[][] organizedQueue;

    randomizedQueue = new int[][] {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
    organizedQueue = new int[][] {{5,0}, {7,0}, {5,2},{6,1},{4,4},{7,1}};
    randomizedQueue = tester.reconstructQueueTakeTwo(randomizedQueue);
    for(int i=0; i<randomizedQueue.length; i++){
      for(int j=0; j<randomizedQueue[0].length; j++){
        Assert.assertEquals(organizedQueue[i][j], randomizedQueue[i][j]);
      }
    }
    randomizedQueue = new int[0][0];
    organizedQueue = new int[0][0];
    randomizedQueue = tester.reconstructQueueTakeTwo(randomizedQueue);
    Assert.assertEquals(organizedQueue.length,randomizedQueue.length);
  }

  @Test
  public void reconstructQueueTakeThree() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[][] randomizedQueue;
    int[][] organizedQueue;

    randomizedQueue = new int[][] {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
    organizedQueue = new int[][] {{5,0}, {7,0}, {5,2},{6,1},{4,4},{7,1}};
    randomizedQueue = tester.reconstructQueueTakeThree(randomizedQueue);
    for(int i=0; i<randomizedQueue.length; i++){
      for(int j=0; j<randomizedQueue[0].length; j++){
        Assert.assertEquals(organizedQueue[i][j], randomizedQueue[i][j]);
      }
    }
    randomizedQueue = new int[0][0];
    organizedQueue = new int[0][0];
    randomizedQueue = tester.reconstructQueueTakeThree(randomizedQueue);
    Assert.assertEquals(organizedQueue.length,randomizedQueue.length);
  }

  @Test
  public void findDisappearedNumbers() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] testArr;
    List<Integer> actualResult;
    int[] expectedResult;

    testArr = new int[] {4,3,2,7,8,2,3,1};
    expectedResult = new int[] {5,6};
    actualResult = tester.findDisappearedNumbers(testArr);
    Assert.assertEquals(expectedResult.length, actualResult.size());
    for(int i=0; i<expectedResult.length; i++){
      Assert.assertEquals(expectedResult[i], (int)actualResult.get(i));
    }

    Assert.assertTrue(tester.findDisappearedNumbers(new int[] {}).isEmpty());
    Assert.assertTrue(tester.findDisappearedNumbers(null).isEmpty());
  }

  @Test
  public void levelOrder(){
    ProblemSolutions tester = new ProblemSolutions();
    List<List<Integer>> expectedResults;
    List<List<Integer>> actualResults;

    NaryTreeNode lastLevel1 = new NaryTreeNode(5, new ArrayList<>());
    NaryTreeNode lastLevel2 = new NaryTreeNode(6, new ArrayList<>());
    NaryTreeNode secondLevel1 = new NaryTreeNode(3, new ArrayList<>());
    secondLevel1.children.add(lastLevel1);
    secondLevel1.children.add(lastLevel2);
    NaryTreeNode root = new NaryTreeNode(1, new ArrayList<>());
    root.children.add(secondLevel1);
    root.children.add(new NaryTreeNode(2, new ArrayList<>()));
    root.children.add(new NaryTreeNode(4, new ArrayList<>()));

    expectedResults = new ArrayList<>();
    List<Integer> tmpList = new ArrayList<>();
    tmpList.add(1);
    expectedResults.add(tmpList);
    tmpList = new ArrayList<>();
    tmpList.add(3);
    tmpList.add(2);
    tmpList.add(4);
    expectedResults.add(tmpList);
    tmpList = new ArrayList<>();
    tmpList.add(5);
    tmpList.add(6);
    expectedResults.add(tmpList);

    actualResults = tester.levelOrder(root);
    Assert.assertEquals(expectedResults.size(), actualResults.size());
    for(int i=0; i<expectedResults.size(); i++){
      Assert.assertEquals(expectedResults.get(i).size(), actualResults.get(i).size());
      for(int j=0; j<expectedResults.get(i).size(); j++){
        Assert.assertEquals(expectedResults.get(i).get(j), actualResults.get(i).get(j));
      }
    }
    actualResults = tester.levelOrder(null);
    Assert.assertTrue(actualResults.isEmpty());
  }

  @Test
  public void numberOfArithmeticSlices() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(2, tester.numberOfArithmeticSlices(new int[] {1,2,3,8,9,10}));
    Assert.assertEquals(1, tester.numberOfArithmeticSlices(new int[] {1,2,3}));
    Assert.assertEquals(3, tester.numberOfArithmeticSlices(new int[] {1,2,3,4}));
    Assert.assertEquals(6, tester.numberOfArithmeticSlices(new int[] {1,2,3,4,5}));
    Assert.assertEquals(10, tester.numberOfArithmeticSlices(new int[] {1,2,3,4,5,6}));
  }

  @Test
  public void deleteNode() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    TreeNode root;
    int[] expectedValuesOpt1;
    int[] expectedValuesOpt2;
    Queue<TreeNode> levels;
    int index;

    root = new TreeNode(2);
    root.left = new TreeNode(1);
    levels = new LinkedList<>();
    levels.offer(tester.deleteNode(root, 2));
    Assert.assertTrue(levels.peek().left==null&&levels.peek().right==null);
    Assert.assertEquals(1, levels.peek().val);

    root = new TreeNode(2);
    root.left = new TreeNode(1);
    root.right = new TreeNode(3);
    levels = new LinkedList();
    expectedValuesOpt1 = new int[] {1,3};
    expectedValuesOpt2 = new int[] {3,1};
    index = 0;

    levels.offer(tester.deleteNode(root, 2));
    while(!levels.isEmpty()){
      int lvlSize = levels.size();
      for(int i=0; i<lvlSize; i++){
        TreeNode tmp = levels.poll();
        if(tmp.left!=null) levels.offer(tmp.left);
        if(tmp.right!=null) levels.offer(tmp.right);
        Assert.assertTrue(tmp.val==expectedValuesOpt1[index]||tmp.val==expectedValuesOpt2[index]);
        index++;
      }
    }

    root = new TreeNode(5);
    root.left = new TreeNode(3);
    root.left.left = new TreeNode(2);
    root.left.right = new TreeNode(4);
    root.right = new TreeNode(6);
    root.right.right = new TreeNode(7);
    levels = new LinkedList<>();
    expectedValuesOpt1 = new int[] {5,2,6,4,7};
    expectedValuesOpt2 = new int[] {5,4,6,2,7};
    levels.add(tester.deleteNode(root, 3));
    index = 0;
    while(!levels.isEmpty()){
      int lvlSize = levels.size();
      for(int i=0; i<lvlSize; i++){
        TreeNode tmp = levels.poll();
        if(tmp.left!=null) levels.offer(tmp.left);
        if(tmp.right!=null) levels.offer(tmp.right);
        Assert.assertTrue(tmp.val==expectedValuesOpt1[index]||tmp.val==expectedValuesOpt2[index]);
        index++;
      }
    }

    root = new TreeNode(0);
    Assert.assertNull(tester.deleteNode(root, 0));
  }

  @Test
  public void originalDigits() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertNull(tester.originalDigits(null));
    Assert.assertTrue(tester.originalDigits("").length()==0);
    Assert.assertTrue("012".equals(tester.originalDigits("owoztneoer")));
    Assert.assertTrue("45".equals(tester.originalDigits("fviefuro")));
  }

  @Test
  public void findRightInterval() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Interval[] inputData;
    int[] expectedResult;

    inputData = new Interval[3];
    inputData[0] = new Interval(3,4);
    inputData[1] = new Interval(2,3);
    inputData[2] = new Interval(1,2);
    expectedResult = new int[] {-1,0,1};
    Assert.assertArrayEquals(expectedResult, tester.findRightInterval(inputData));
    Assert.assertArrayEquals(new int[] {-1}, tester.findRightInterval(new Interval[] {new Interval(1,2)}));
    Assert.assertTrue(tester.findRightInterval(new Interval[0]).length==0);
  }

  @Test
  public void canFinish() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[][] courses;

    Assert.assertTrue(tester.canFinish(3, new int[][] {}));

    courses = new int[][] {{1,0}};
    Assert.assertTrue(tester.canFinish(2, courses));

    courses = new int[][] {{1,0}, {0,1}};
    Assert.assertFalse(tester.canFinish(2, courses));

    courses = new int[][] {{4,3}, {3,2}, {3,1}, {2,0}, {1,2}};
    Assert.assertTrue(tester.canFinish(5, courses));
  }

  @Test public void nextGreaterElement() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] input1;
    int[] input2;
    int[] expectedResult;

    input1 = new int[] {4,1,2};
    input2 = new int[] {1,3,4,2};
    expectedResult = new int[] {-1,3,-1};
    Assert.assertArrayEquals(expectedResult, tester.nextGreaterElement(input1, input2));

    input1 = new int[] {2,4};
    input2 = new int[] {1,2,3,4};
    expectedResult = new int[] {3,-1};
    Assert.assertArrayEquals(expectedResult, tester.nextGreaterElement(input1, input2));
  }

  @Test
  public void frequencySort() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    String result;

    result = tester.frequencySort("tree");
    Assert.assertTrue("eert".equals(result)||"eetr".equals(result));

    result = tester.frequencySort("cccaa");
    Assert.assertTrue("cccaa".equals(result));
  }

  @Test
  public void hammingDistance() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(2, tester.hammingDistance(4, 1));
    Assert.assertEquals(2, tester.hammingDistance(14,4));
  }

  @Test
  public void arrayNesting() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] input;

    input = new int[] {5,4,0,3,1,6,2};
    Assert.assertEquals(4, tester.arrayNesting(input));
    input = new int[] {1,0};
    Assert.assertEquals(2, tester.arrayNesting(input));
    input = new int[] {};
    Assert.assertEquals(0, tester.arrayNesting(input));
    Assert.assertEquals(0, tester.arrayNesting(null));
  }

  @Test
  public void maxDepth() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();

    Assert.assertEquals(0, tester.maxDepth(null));

    NaryTreeNode root = new NaryTreeNode(1, new ArrayList<>());
    Assert.assertEquals(1, tester.maxDepth(root));

    root.children.add(new NaryTreeNode(2, new ArrayList<>()));
    root.children.add(new NaryTreeNode(4, new ArrayList<>()));
    Assert.assertEquals(2, tester.maxDepth(root));

    NaryTreeNode tmp = new NaryTreeNode(3, new ArrayList<>());
    tmp.children.add(new NaryTreeNode(5, new ArrayList<>()));
    tmp.children.add(new NaryTreeNode(6, new ArrayList<>()));
    root.children.add(tmp);
    Assert.assertEquals(3, tester.maxDepth(root));
  }

  @Test
  public void dfsMaxDepth() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();

    Assert.assertEquals(0, tester.dfsMaxDepth(null));

    NaryTreeNode root = new NaryTreeNode(1, new ArrayList<>());
    Assert.assertEquals(1, tester.dfsMaxDepth(root));

    root.children.add(new NaryTreeNode(2, new ArrayList<>()));
    root.children.add(new NaryTreeNode(4, new ArrayList<>()));
    Assert.assertEquals(2, tester.dfsMaxDepth(root));

    NaryTreeNode tmp = new NaryTreeNode(3, new ArrayList<>());
    tmp.children.add(new NaryTreeNode(5, new ArrayList<>()));
    tmp.children.add(new NaryTreeNode(6, new ArrayList<>()));
    root.children.add(tmp);
    Assert.assertEquals(3, tester.dfsMaxDepth(root));
  }

  @Test
  public void isSubtree() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(1);
    Assert.assertTrue(tester.isSubtree(root, root.left));

    TreeNode head = new TreeNode(1);
    head.left = null;
    head.right = new TreeNode(2);
    head.right.right = null;
    head.right.left = new TreeNode(3);
    Assert.assertTrue(tester.isSubtree(head, head.right.left));
    Assert.assertTrue(tester.isSubtree(head, head));
    Assert.assertFalse(tester.isSubtree(head, root));
    Assert.assertFalse(tester.isSubtree(null, head));
  }

  @Test
  public void findRestaurant() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    String[] list1;
    String[] list2;
    String[] result;
    Set<String> expected;

    list1 = new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"};
    list2 = new String[] {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
    expected = new HashSet<>();
    expected.add("Shogun");
    result = tester.findRestaurant(list1, list2);
    for(int i=0; i<result.length; i++){
      Assert.assertTrue(expected.contains(result[i]));
    }

    list1 = new String[] {"Shogun", "Tapioca Express", "Burger King", "Piatti", "KFC"};
    list2 = new String[] {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
    result = tester.findRestaurant(list1, list2);
    expected.add("Piatti");
    for(int i=0; i<result.length; i++){
      Assert.assertTrue(expected.contains(result[i]));
    }
  }

  @Test
  public void addOneRow() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    BSTComparator comparer = new BSTComparator();
    TreeNode input;
    TreeNode expected;

    input = new TreeNode(4);
    expected = new TreeNode(1);
    expected.left = new TreeNode(4);
    Assert.assertTrue(comparer.isSameTree(expected, tester.addOneRow(input,1,1)));

    input = new TreeNode(4);
    expected = expected.left;
    expected.left = new TreeNode(1);
    expected.right = new TreeNode(1);
    Assert.assertTrue(comparer.isSameTree(expected, tester.addOneRow(input, 1, 2)));

    input = new TreeNode(4);
    input.left = new TreeNode(3);
    input.right = new TreeNode(6);
    input.right.right = new TreeNode(8);
    input.right.left = new TreeNode(5);

    expected =new TreeNode(4);
    expected.left = new TreeNode(3);
    expected.left.left = new TreeNode(1);
    expected.left.right = new TreeNode(1);
    expected.right = new TreeNode(6);
    expected.right.left = new TreeNode(1);
    expected.right.right = new TreeNode(1);
    expected.right.right.right = new TreeNode(8);
    expected.right.left.left = new TreeNode(5);
    Assert.assertTrue(comparer.isSameTree(expected, tester.addOneRow(input, 1, 3)));
  }

  @Test
  public void minSteps() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(6, tester.minSteps(9));
    Assert.assertEquals(5, tester.minSteps(6));
    Assert.assertEquals(4, tester.minSteps(4));
    Assert.assertEquals(3, tester.minSteps(3));
  }

  @Test
  public void predictPartyVictory() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();


    Assert.assertTrue("Radiant".equals(tester.predictPartyVictory("DRRDRDRDRDDRDRDR")));
    Assert.assertTrue("Dire".equals(tester.predictPartyVictory("DR")));
    Assert.assertTrue("Dire".equals(tester.predictPartyVictory("RDD")));
    Assert.assertTrue("Dire".equals(tester.predictPartyVictory("DDDDRRRRR")));
    Assert.assertTrue("Radiant".equals(tester.predictPartyVictory("RDDDRRDRRR")));
  }

  @Test
  public void altPredictPartyVictory() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();

    Assert.assertTrue("Radiant".equals(tester.altPredictPartyVictory("DRRDRDRDRDDRDRDR")));
    Assert.assertTrue("Dire".equals(tester.altPredictPartyVictory("DR")));
    Assert.assertTrue("Dire".equals(tester.altPredictPartyVictory("RDD")));
    Assert.assertTrue("Dire".equals(tester.altPredictPartyVictory("DDDDRRRRR")));
    Assert.assertTrue("Radiant".equals(tester.altPredictPartyVictory("RDDDRRDRRR")));
  }

  @Test
  public void naryPreorder() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] expected;
    List<Integer> result;

    Assert.assertTrue(tester.naryPreorder(null).isEmpty());
    NaryTreeNode root = new NaryTreeNode(1, new ArrayList<>());
    NaryTreeNode tmp = new NaryTreeNode(3, new ArrayList<>());
    root.children.add(tmp);
    expected = new int[] {1,3};
    result = tester.naryPreorder(root);
    Assert.assertEquals(expected.length, result.size());
    int index = 0;
    for(Integer integer: result){
      Assert.assertEquals(expected[index], (int)integer);
      index++;
    }

    tmp.children.add(new NaryTreeNode(5, new ArrayList<>()));
    tmp.children.add(new NaryTreeNode(6, new ArrayList<>()));
    expected = new int[] {1,3,5,6};
    result = tester.naryPreorder(root);
    Assert.assertEquals(expected.length, result.size());
    index = 0;
    for(Integer integer: result){
      Assert.assertEquals(expected[index], (int)integer);
      index++;
    }


    root.children.add(new NaryTreeNode(2, new ArrayList<>()));
    root.children.add(new NaryTreeNode(4, new ArrayList<>()));
    expected = new int[] {1,3,5,6,2,4};
    result = tester.naryPreorder(root);
    Assert.assertEquals(expected.length, result.size());
    index = 0;
    for(Integer integer: result){
      Assert.assertEquals(expected[index], (int)integer);
      index++;
    }
  }

  @Test
  public void findErrorNums() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertArrayEquals(new int[] {2,3}, tester.findErrorNums(new int[] {2,4,5,6,7,1,2}));
    Assert.assertArrayEquals(new int[] {1,2}, tester.findErrorNums(new int[] {1,1}));
    Assert.assertArrayEquals(new int[] {-1,-1}, tester.findErrorNums(new int[] {1}));
  }

  @Test
  public void findMaxAverage() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(12.75, tester.findMaxAverage(new int[] {3,1,12,-5,-6,50}, 4), 0.0001);
    Assert.assertEquals(0.5, tester.findMaxAverage(new int[] {1,12,-5,-6,-50,50,3}, 4), 0.0001);
    Assert.assertEquals(26.5, tester.findMaxAverage(new int[] {1,12,-5,-6,-50,50,3}, 2), 0.0001);
  }

  @Test
  public void topKFrequentWords() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    List<String> result;
    String[] expected;
    String[] input;

    input = new String[] {"i", "love", "leetcode", "i", "love", "coding"};
    expected = new String[] {"i", "love"};
    result = tester.topKFrequentWords(input, 2);
    Assert.assertEquals(expected.length, result.size());
    for(int i=0; i<expected.length; i++){
      Assert.assertTrue(expected[i].equals(result.get(i)));
    }

    input = new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
    expected = new String[] {"the", "is", "sunny", "day"};
    result = tester.topKFrequentWords(input, 4);
    Assert.assertEquals(expected.length, result.size());
    for(int i=0; i<expected.length; i++){
      Assert.assertTrue(expected[i].equals(result.get(i)));
    }
  }

  @Test
  public void getImportance() throws Exception {
    ProblemSolutions tester = new ProblemSolutions();
    int[] ids;
    int[] vals;
    int[][] subordinates;
    List<Employee> input;


    ids = new int[] {1,2,3};
    vals = new int[] {5,3,3};
    subordinates = new int[][] {{2,3}, {}, {}};
    input = new ArrayList<>(ids.length);
    for(int i=0; i<ids.length; i++){
      Employee tmp = new Employee();
      tmp.id = ids[i];
      tmp.importance = vals[i];
      tmp.subordinates = new ArrayList<>();
      for(int j=0; j<subordinates[i].length; j++){
        tmp.subordinates.add(subordinates[i][j]);
      }
      input.add(tmp);
    }
    Assert.assertEquals(11, tester.getImportance(input, 1));
    Assert.assertEquals(3, tester.getImportance(input, 2));

    ids = new int[] {2};
    vals = new int[] {5};
    subordinates = new int[][] {{}};
    input.clear();
    for(int i=0; i<ids.length; i++){
      Employee tmp = new Employee();
      tmp.id = ids[i];
      tmp.importance = vals[i];
      tmp.subordinates = new ArrayList<>(subordinates[i].length);
      for(int j=0; j<subordinates[i].length; i++){
        tmp.subordinates.add(subordinates[i][j]);
      }
      input.add(tmp);
    }
    Assert.assertEquals(5, tester.getImportance(input, 2));
    Assert.assertEquals(0, tester.getImportance(input, 1));
  }

  @Test
  public void altGetImportance() throws Exception {
    ProblemSolutions tester = new ProblemSolutions();
    int[] ids;
    int[] vals;
    int[][] subordinates;
    List<Employee> input;


    ids = new int[] {1,2,3};
    vals = new int[] {5,3,3};
    subordinates = new int[][] {{2,3}, {}, {}};
    input = new ArrayList<>(ids.length);
    for(int i=0; i<ids.length; i++){
      Employee tmp = new Employee();
      tmp.id = ids[i];
      tmp.importance = vals[i];
      tmp.subordinates = new ArrayList<>();
      for(int j=0; j<subordinates[i].length; j++){
        tmp.subordinates.add(subordinates[i][j]);
      }
      input.add(tmp);
    }
    Assert.assertEquals(11, tester.altGetImportance(input, 1));
    Assert.assertEquals(3, tester.altGetImportance(input, 2));

    ids = new int[] {2};
    vals = new int[] {5};
    subordinates = new int[][] {{}};
    input.clear();
    for(int i=0; i<ids.length; i++){
      Employee tmp = new Employee();
      tmp.id = ids[i];
      tmp.importance = vals[i];
      tmp.subordinates = new ArrayList<>(subordinates[i].length);
      for(int j=0; j<subordinates[i].length; i++){
        tmp.subordinates.add(subordinates[i][j]);
      }
      input.add(tmp);
    }
    Assert.assertEquals(5, tester.altGetImportance(input, 2));
    Assert.assertEquals(0, tester.altGetImportance(input, 1));
  }

  @Test
  public void alt2GetImportance() throws Exception {
    ProblemSolutions tester = new ProblemSolutions();
    int[] ids;
    int[] vals;
    int[][] subordinates;
    List<Employee> input;


    ids = new int[] {1,2,3};
    vals = new int[] {5,3,3};
    subordinates = new int[][] {{2,3}, {}, {}};
    input = new ArrayList<>(ids.length);
    for(int i=0; i<ids.length; i++){
      Employee tmp = new Employee();
      tmp.id = ids[i];
      tmp.importance = vals[i];
      tmp.subordinates = new ArrayList<>();
      for(int j=0; j<subordinates[i].length; j++){
        tmp.subordinates.add(subordinates[i][j]);
      }
      input.add(tmp);
    }
    Assert.assertEquals(11, tester.alt2GetImportance(input, 1));
    Assert.assertEquals(3, tester.alt2GetImportance(input, 2));

    ids = new int[] {2};
    vals = new int[] {5};
    subordinates = new int[][] {{}};
    input.clear();
    for(int i=0; i<ids.length; i++){
      Employee tmp = new Employee();
      tmp.id = ids[i];
      tmp.importance = vals[i];
      tmp.subordinates = new ArrayList<>(subordinates[i].length);
      for(int j=0; j<subordinates[i].length; i++){
        tmp.subordinates.add(subordinates[i][j]);
      }
      input.add(tmp);
    }
    Assert.assertEquals(5, tester.alt2GetImportance(input, 2));
    Assert.assertEquals(0, tester.alt2GetImportance(input, 1));
  }

  @Test
  public void judgeCircle() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertTrue(tester.judgeCircle("LDRLLDDUUURR"));
    Assert.assertTrue(tester.judgeCircle(""));
    Assert.assertFalse(tester.judgeCircle("LDU"));
  }

  @Test
  public void selfDividingNumbers() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] expected;
    List<Integer> result;

    expected = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22};
    result = tester.selfDividingNumbers(1,22);
    Assert.assertEquals(expected.length, result.size());
    for(int i=0; i<expected.length; i++){
      Assert.assertEquals(expected[i], (int)result.get(i));
    }
  }

  @Test
  public void miniMaxSum() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] input = new int[] {5,5,5,5,5};
    Assert.assertArrayEquals(new long[] {20,20}, tester.miniMaxSum(input));

    input = new int[] {1,2,3,4,5};
    Assert.assertArrayEquals(new long[] {10,14}, tester.miniMaxSum(input));
  }

  @Test
  public void timeConversion() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertTrue("19:05:45".equals(tester.timeConversion("07:05:45PM")));
    Assert.assertTrue("00:05:45".equals(tester.timeConversion("12:05:45AM")));
    Assert.assertTrue("12:05:45".equals(tester.timeConversion("12:05:45PM")));
    Assert.assertTrue("07:05:45".equals(tester.timeConversion("07:05:45AM")));
  }

  @Test
  public void utopianTree() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(1, tester.utopianTree(0));
    Assert.assertEquals(2, tester.utopianTree(1));
    Assert.assertEquals(3, tester.utopianTree(2));
    Assert.assertEquals(6, tester.utopianTree(3));
    Assert.assertEquals(7, tester.utopianTree(4));
  }

  @Test
  public void viralAdvertising() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    Assert.assertEquals(2, tester.viralAdvertising(1));
    Assert.assertEquals(5, tester.viralAdvertising(2));
    Assert.assertEquals(9, tester.viralAdvertising(3));
    Assert.assertEquals(15, tester.viralAdvertising(4));
    Assert.assertEquals(24, tester.viralAdvertising(5));
  }

  @Test
  public void freqQuery() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[][] rawInput;
    List<List<Integer>> boxedInput;
    List<Integer> result;
    int[] expected;

    rawInput = new int[][] {{1,1}, {2,2}, {3,2}, {1,1}, {1,1}, {2,1}, {3,2}};
    expected = new int[] {0,1};
    boxedInput = new ArrayList<>(rawInput.length);
    for(int i=0; i<rawInput.length; i++){
      List<Integer> tmp = new ArrayList<>(2);
      for(int j=0; j<rawInput[0].length; j++){
        tmp.add(rawInput[i][j]);
      }
      boxedInput.add(tmp);
    }
    result = tester.freqQuery(boxedInput);
    Assert.assertEquals(expected.length, result.size());
    for(int i=0; i<expected.length; i++){
      Assert.assertEquals(expected[i], (int)result.get(i));
    }

    rawInput = new int[][] {{1,5}, {1,6}, {3,2}, {1,10}, {1,10}, {1,6}, {2,5}, {3,2}};
    expected = new int[] {0,1};
    boxedInput = new ArrayList<>(rawInput.length);
    for(int i=0; i<rawInput.length; i++){
      List<Integer> tmp = new ArrayList<>(2);
      for(int j=0; j<rawInput[0].length; j++){
        tmp.add(rawInput[i][j]);
      }
      boxedInput.add(tmp);
    }
    result = tester.freqQuery(boxedInput);
    Assert.assertEquals(expected.length, result.size());
    for(int i=0; i<expected.length; i++){
      Assert.assertEquals(expected[i], (int)result.get(i));
    }
  }

  @Test
  public void hourglassSum() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[][] input;

    input = new int[][] {{1,1,1,0,0,0}, {0,1,0,0,0,0}, {1,1,1,0,0,0},
        {0,0,0,0,0,0}, {0,0,0,0,0,0}, {0,0,0,0,0,0}};
    Assert.assertEquals(7, tester.hourglassSum(input));

    input = new int[][] {{1,1,1,0,0,0}, {0,1,0,0,0,0}, {1,1,1,0,0,0},
        {0,0,2,4,4,0}, {0,0,0,2,0,0}, {0,0,1,2,4,0}};
    Assert.assertEquals(19, tester.hourglassSum(input));
  }

  @Test
  public void longestWord() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    String[] input;

    input = new String[] {"w","wo","wor","worl", "world"};
    Assert.assertTrue("world".equals(tester.longestWord(input)));

    input = new String[] {"a", "banana", "app", "appl", "ap", "apply", "apple"};
    Assert.assertTrue("apple".equals(tester.longestWord(input)));
  }

  @Test
  public void dailyTemperatures() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] input;
    int[] expected;
    int[] actual;

    input = new int[] {73, 74, 75, 71, 69, 72, 76, 73};
    expected = new int[] {1, 1, 4, 2, 1, 1, 0, 0};
    actual = tester.dailyTemperatures(input);
    Assert.assertArrayEquals(expected, actual);

    input = new int[] {0,0,0};
    expected = new int[] {0,0,0};
    actual = tester.dailyTemperatures(input);
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void rotLeft() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] input;
    int[] expected;

    input = new int[] {1,2,3,4,5};
    expected = new int[] {1,2,3,4,5};
    Assert.assertArrayEquals(expected, tester.rotLeft(input, 0));
    Assert.assertArrayEquals(expected, tester.rotLeft(input, input.length*3));

    expected = new int[] {4,5,1,2,3};
    Assert.assertArrayEquals(expected, tester.rotLeft(input, 3));
  }

  @Test
  public void networkDelayTime() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int input[][];

    input = new int[][] {{1,2,1},{2,3,7},{1,3,4},{2,1,2}};
    Assert.assertEquals(-1, tester.networkDelayTime(input, 4,1));

    input = new int[][] {{2,1,1},{2,3,1},{3,4,1}};
    Assert.assertEquals(2, tester.networkDelayTime(input, 4, 2));

    input = new int[][] {{1,2,1},{2,3,2},{1,3,2}};
    Assert.assertEquals(2, tester.networkDelayTime(input, 3, 1));
  }

  @Test
  public void asteroidCollision() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] input;
    int[] expected;

    input = new int[] {3, 2, 3, 2, 1, -3, -1, -5};
    expected = new int[] {-5};
    Assert.assertArrayEquals(expected, tester.asteroidCollision(input));

    input = new int[] {5, 10, -5};
    expected = new int[] {5, 10};
    Assert.assertArrayEquals(expected, tester.asteroidCollision(input));
  }

  @Test
  public void accountsMerge() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    String[][] accounts;
    List<List<String>> input;
    Map<String, String[]> expected;
    List<List<String>> results;

    accounts = new String[][] {{"Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"}};
    input = new ArrayList<>(accounts.length);
    for(int i=0; i<accounts.length; i++){
      List<String> tmp = new ArrayList<>(accounts[i].length);
      for(int j=0; j<accounts[i].length; j++){
        tmp.add(accounts[i][j]);
      }
      input.add(tmp);
    }
    results = tester.accountsMerge(input);
    expected = new HashMap<>();
    expected.put("Ethan", new String[] {"Ethan","Ethan0@m.co","Ethan3@m.co"});
    for(int i=0; i<results.size(); i++){
      Assert.assertTrue(expected.containsKey(results.get(i).get(0)));
      List<String> curResult = results.get(i);
      String[] curExpected = expected.get(curResult.get(0));
      for(int j=0; j<curExpected.length; j++){
        Assert.assertTrue(curExpected[i].equals(curResult.get(i)));
      }
    }


    accounts = new String[][] {{"Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"},
        {"Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"},
        {"Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"},
        {"Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"},
        {"Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"}};
    input = new ArrayList<>(accounts.length);
    for(int i=0; i<accounts.length; i++){
      List<String> tmp = new ArrayList<>(accounts[i].length);
      for(int j=0; j<accounts[i].length; j++){
        tmp.add(accounts[i][j]);
      }
      input.add(tmp);
    }
    results = tester.accountsMerge(input);
    expected = new HashMap<>();
    expected.put("Ethan", new String[] {"Ethan","Ethan0@m.co","Ethan3@m.co"});
    expected.put("Gabe", new String[] {"Gabe","Gabe0@m.co","Gabe2@m.co","Gabe3@m.co", "Gabe4@m.co"});
    expected.put("Kevin", new String[] {"Kevin","Kevin2@m.co","Kevin4@m.co"});
    expected.put("Alex", new String[] {"Alex","Alex0@m.co","Alex4@m.co","Alex5@m.co"});

    for(int i=0; i<results.size(); i++){
      Assert.assertTrue(expected.containsKey(results.get(i).get(0)));
      List<String> curResult = results.get(i);
      String[] curExpected = expected.get(curResult.get(0));
      for(int j=0; j<curExpected.length; j++){
        Assert.assertTrue(curExpected[i].equals(curResult.get(i)));
      }
    }
  }

  @Test
  public void accountsMerge2() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    String[][] accounts;
    List<List<String>> input;
    Map<String, String[]> expected;
    List<List<String>> results;

    accounts = new String[][]
        {{"Gabe","Gabe5@m.co","Gabe45@m.co","Gabe46@m.co","Gabe47@m.co","Gabe48@m.co","Gabe49@m.co",
            "Gabe50@m.co","Gabe51@m.co","Gabe52@m.co"}, {"Gabe","Gabe3@m.co","Gabe27@m.co",
            "Gabe28@m.co","Gabe29@m.co","Gabe30@m.co","Gabe31@m.co","Gabe32@m.co","Gabe33@m.co",
            "Gabe34@m.co"},{"Gabe","Gabe6@m.co","Gabe54@m.co","Gabe55@m.co","Gabe56@m.co",
            "Gabe57@m.co","Gabe58@m.co","Gabe59@m.co","Gabe60@m.co","Gabe61@m.co"},{"Gabe",
            "Gabe0@m.co","Gabe0@m.co","Gabe1@m.co","Gabe2@m.co","Gabe3@m.co","Gabe4@m.co",
            "Gabe5@m.co","Gabe6@m.co","Gabe7@m.co"},{"Gabe","Gabe1@m.co","Gabe9@m.co","Gabe10@m.co",
            "Gabe11@m.co","Gabe12@m.co","Gabe13@m.co","Gabe14@m.co","Gabe15@m.co","Gabe16@m.co"},
            {"Gabe","Gabe2@m.co","Gabe18@m.co","Gabe19@m.co","Gabe20@m.co","Gabe21@m.co",
                "Gabe22@m.co","Gabe23@m.co","Gabe24@m.co","Gabe25@m.co"},{"Gabe","Gabe4@m.co",
            "Gabe36@m.co","Gabe37@m.co","Gabe38@m.co","Gabe39@m.co","Gabe40@m.co","Gabe41@m.co",
            "Gabe42@m.co","Gabe43@m.co"},{"Gabe","Gabe0@m.co","Gabe0@m.co","Gabe1@m.co",
            "Gabe2@m.co","Gabe3@m.co","Gabe4@m.co","Gabe5@m.co","Gabe6@m.co","Gabe7@m.co"}};
    input = new ArrayList<>(accounts.length);
    for(int i=0; i<accounts.length; i++){
      List<String> tmp = new ArrayList<>(accounts[i].length);
      for(int j=0; j<accounts[i].length; j++){
        tmp.add(accounts[i][j]);
      }
      input.add(tmp);
    }
    results = tester.accountsMerge2(input);
    expected = new HashMap<>();
    expected.put("Gabe", new String[] {"Gabe","Gabe0@m.co","Gabe10@m.co","Gabe11@m.co",
        "Gabe12@m.co","Gabe13@m.co","Gabe14@m.co","Gabe15@m.co","Gabe16@m.co",
        "Gabe18@m.co","Gabe19@m.co","Gabe1@m.co","Gabe20@m.co","Gabe21@m.co",
        "Gabe22@m.co","Gabe23@m.co","Gabe24@m.co","Gabe25@m.co","Gabe27@m.co",
        "Gabe28@m.co","Gabe29@m.co","Gabe2@m.co","Gabe30@m.co","Gabe31@m.co","Gabe32@m.co",
        "Gabe33@m.co","Gabe34@m.co","Gabe36@m.co","Gabe37@m.co","Gabe38@m.co","Gabe39@m.co",
        "Gabe3@m.co","Gabe40@m.co","Gabe41@m.co","Gabe42@m.co","Gabe43@m.co","Gabe45@m.co",
        "Gabe46@m.co","Gabe47@m.co","Gabe48@m.co","Gabe49@m.co","Gabe4@m.co","Gabe50@m.co",
        "Gabe51@m.co","Gabe52@m.co","Gabe54@m.co","Gabe55@m.co","Gabe56@m.co","Gabe57@m.co",
        "Gabe58@m.co","Gabe59@m.co","Gabe5@m.co","Gabe60@m.co","Gabe61@m.co","Gabe6@m.co",
        "Gabe7@m.co","Gabe9@m.co"});

    for(int i=0; i<results.size(); i++){
      Assert.assertTrue(expected.containsKey(results.get(i).get(0)));
      List<String> curResult = results.get(i);
      String[] curExpected = expected.get(curResult.get(0));
      for(int j=0; j<curExpected.length; j++){
        Assert.assertTrue(curExpected[j].equals(curResult.get(j)));
      }
    }


    accounts = new String[][] {{"Gabe","Gabe1@m.co","Gabe2@m.co"},
        {"Gabe","Gabe3@m.co","Gabe4@m.co"},
        {"Gabe","Gabe5@m.co","Gabe6@m.co"},
        {"Gabe","Gabe2@m.co","Gabe4@m.co"},
        {"Gabe","Gabe3@m.co","Gabe5@m.co"}};
    input = new ArrayList<>(accounts.length);
    for(int i=0; i<accounts.length; i++){
      List<String> tmp = new ArrayList<>(accounts[i].length);
      for(int j=0; j<accounts[i].length; j++){
        tmp.add(accounts[i][j]);
      }
      input.add(tmp);
    }
    results = tester.accountsMerge2(input);
    expected = new HashMap<>();
    expected.put("Gabe", new String[] {"Gabe", "Gabe1@m.co","Gabe2@m.co","Gabe3@m.co",
        "Gabe4@m.co", "Gabe5@m.co", "Gabe6@m.co"});

    for(int i=0; i<results.size(); i++){
      Assert.assertTrue(expected.containsKey(results.get(i).get(0)));
      List<String> curResult = results.get(i);
      String[] curExpected = expected.get(curResult.get(0));
      for(int j=0; j<curExpected.length; j++){
        Assert.assertTrue(curExpected[j].equals(curResult.get(j)));
      }
    }


    accounts = new String[][] {{"Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"},
        {"Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"},
        {"Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"},
        {"Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"},
        {"Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"}};
    input = new ArrayList<>(accounts.length);
    for(int i=0; i<accounts.length; i++){
      List<String> tmp = new ArrayList<>(accounts[i].length);
      for(int j=0; j<accounts[i].length; j++){
        tmp.add(accounts[i][j]);
      }
      input.add(tmp);
    }
    results = tester.accountsMerge2(input);
    expected = new HashMap<>();
    expected.put("Ethan", new String[] {"Ethan","Ethan0@m.co","Ethan3@m.co"});
    expected.put("Gabe", new String[] {"Gabe","Gabe0@m.co","Gabe2@m.co","Gabe3@m.co", "Gabe4@m.co"});
    expected.put("Kevin", new String[] {"Kevin","Kevin2@m.co","Kevin4@m.co"});
    expected.put("Alex", new String[] {"Alex","Alex0@m.co","Alex4@m.co","Alex5@m.co"});

    for(int i=0; i<results.size(); i++){
      Assert.assertTrue(expected.containsKey(results.get(i).get(0)));
      List<String> curResult = results.get(i);
      String[] curExpected = expected.get(curResult.get(0));
      for(int j=0; j<curExpected.length; j++){
        Assert.assertTrue(curExpected[j].equals(curResult.get(j)));
      }
    }

    accounts = new String[][] {{"David","David0@m.co","David4@m.co","David3@m.co"},
        {"David","David5@m.co","David5@m.co","David0@m.co"},
        {"David","David1@m.co","David4@m.co","David0@m.co"},
        {"David","David0@m.co","David1@m.co","David3@m.co"},
        {"David","David4@m.co","David1@m.co","David3@m.co"}};
    input = new ArrayList<>(accounts.length);
    for(int i=0; i<accounts.length; i++){
      List<String> tmp = new ArrayList<>(accounts[i].length);
      for(int j=0; j<accounts[i].length; j++){
        tmp.add(accounts[i][j]);
      }
      input.add(tmp);
    }
    results = tester.accountsMerge2(input);
    expected = new HashMap<>();
    expected.put("David", new String[]
        {"David","David0@m.co","David1@m.co","David3@m.co","David4@m.co","David5@m.co"});
    for(int i=0; i<results.size(); i++){
      Assert.assertTrue(expected.containsKey(results.get(i).get(0)));
      List<String> curResult = results.get(i);
      String[] curExpected = expected.get(curResult.get(0));
      for(int j=0; j<curExpected.length; j++){
        Assert.assertTrue(curExpected[j].equals(curResult.get(j)));
      }
    }


    accounts = new String[][] {{"Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"}};
    input = new ArrayList<>(accounts.length);
    for(int i=0; i<accounts.length; i++){
      List<String> tmp = new ArrayList<>(accounts[i].length);
      for(int j=0; j<accounts[i].length; j++){
        tmp.add(accounts[i][j]);
      }
      input.add(tmp);
    }
    results = tester.accountsMerge2(input);
    expected = new HashMap<>();
    expected.put("Ethan", new String[] {"Ethan","Ethan0@m.co","Ethan3@m.co"});
    for(int i=0; i<results.size(); i++){
      Assert.assertTrue(expected.containsKey(results.get(i).get(0)));
      List<String> curResult = results.get(i);
      String[] curExpected = expected.get(curResult.get(0));
      for(int j=0; j<curExpected.length; j++){
        Assert.assertTrue(curExpected[j].equals(curResult.get(j)));
      }
    }
  }

  @Test
  public void dominantIndex() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[] input;

    input = new int[] {0,0,0,3};
    Assert.assertEquals(3, tester.dominantIndex(input));

    input = new int[] {0,0,3,2};
    Assert.assertEquals(-1, tester.dominantIndex(input));
  }

  @Test
  public void floodFill() throws Exception{
    ProblemSolutions tester = new ProblemSolutions();
    int[][] image;
    int[][] expected;
    int[][] result;

    image = new int[][] {{1,1,1}, {1,1,0}, {1,0,1}};
    expected = new int[][] {{2,2,2}, {2,2,0}, {2,0,1}};

    result = tester.floodFill(image, 1,1,2);
    Assert.assertEquals(expected.length, result.length);
    for(int i=0; i<expected.length; i++){
      Assert.assertArrayEquals(expected[i], result[i]);
    }
  }

  @Test
  public void oddEvenList() throws Exception{
    ProblemSolutions t = new ProblemSolutions();
    int[] input;
    int[] expected;
    input = new int[] {1,2,3,4,5};
    expected = new int[] {1,3,5,2,4};
    ListNode head = populateLinkedList(input);
    head = t.oddEvenList(head);
    ListNode tmp = head;
    for(int i = 0; i < expected.length; i++){
      if(tmp == null) {
        Assert.assertNotNull(tmp);
      } else {
        Assert.assertEquals(expected[i], tmp.val);
      }
      tmp = tmp.next;
    }

    Assert.assertNull(t.oddEvenList(null));
    Assert.assertEquals(3, t.oddEvenList(new ListNode(3)).val);
  }

  private ListNode populateLinkedList(int[] data){
    ListNode head = new ListNode(data[0]);
    ListNode tail = head;
    ListNode tmp;
    for(int i = 1; i < data.length; i++){
      tmp = new ListNode(data[i]);
      tail.next = tmp;
      tail = tmp;
    }
    return head;
  }

  @Test
  public void maxProduct() throws Exception{
    ProblemSolutions t = new ProblemSolutions();
    Assert.assertEquals(16, t.maxProduct(new String[] {"abcw","baz","foo","bar","xtfn","abcdef"}));
    Assert.assertEquals(4, t.maxProduct(new String[] {"a","ab","abc","d","cd","bcd","abcd"}));
    Assert.assertEquals(0, t.maxProduct(new String[] {"a","aa","aaa","aaaa"}));
  }

  @Test
  public void altTopKFrequent() throws Exception {
    ProblemSolutions t = new ProblemSolutions();
    List<Integer> results = t.altTopKFrequent(new int[] {3,2,291,3,5,54,2,2,1,1,6,7,1,1}, 3);
    int[] expectedResults = new int[] {1,2,3};
    assertTrue(expectedResults.length == results.size());
    for(int i = 0; i < expectedResults.length; i++){
      assertEquals(expectedResults[i],(int) results.get(i));
    }
  }

  @Test
  public void findMaxConsecutiveOnes() throws Exception {
    ProblemSolutions t = new ProblemSolutions();
    Assert.assertEquals(3, t.findMaxConsecutiveOnes(new int[] {0,0,1,0,0,1,1,1}));
    Assert.assertEquals(3, t.findMaxConsecutiveOnes(new int[] {0,1,1,1,0,1,1,1,0,0,1,1}));
    Assert.assertEquals(0, t.findMaxConsecutiveOnes(new int[] {0,0,0,0,0,0}));
    Assert.assertEquals(1, t.findMaxConsecutiveOnes(new int[] {1}));
  }

  @Test
  public void findComplement() throws Exception {
    ProblemSolutions t = new ProblemSolutions();
    Assert.assertEquals(2, t.findComplement(5));
    Assert.assertEquals(0, t.findComplement(1));
    Assert.assertEquals(42479185, t.findComplement(91738542));
  }

  @Test
  public void altFindComplement() throws Exception {
    ProblemSolutions t = new ProblemSolutions();
    Assert.assertEquals(2, t.altFindComplement(5));
    Assert.assertEquals(0, t.altFindComplement(1));
    Assert.assertEquals(42479185, t.altFindComplement(91738542));
  }

  @Test
  public void decodeString() throws Exception{
    ProblemSolutions t = new ProblemSolutions();
    Assert.assertTrue("aaabcbc".equals(t.decodeString("3[a]2[bc]")));
    Assert.assertTrue("accaccacc".equals(t.decodeString("3[a2[c]]")));
    Assert.assertTrue("abcabccdcdcdef".equals(t.decodeString("2[abc]3[cd]ef")));

  }

  @Test
  public void findModeInTree() throws Exception {
    ProblemSolutions t = new ProblemSolutions();
    TreeNode head = new TreeNode(1);
    head.right = new TreeNode(2);
    head.right.left = new TreeNode(2);

    Assert.assertArrayEquals(new int[] {Integer.MAX_VALUE},
        t.findModeInTree(new TreeNode(Integer.MAX_VALUE)));
    Assert.assertArrayEquals(new int[] {2}, t.findModeInTree(head));
  }

  @Test
  public void altFindMode() throws Exception {
      ProblemSolutions t = new ProblemSolutions();
      TreeNode head = new TreeNode(1);
      head.right = new TreeNode(2);
      head.right.left = new TreeNode(2);

      Assert.assertArrayEquals(new int[] {Integer.MAX_VALUE},
              t.altFindMode(new TreeNode(Integer.MAX_VALUE)));
      Assert.assertArrayEquals(new int[] {2}, t.altFindMode(head));
  }

  @Test
  public void trap() {
      ProblemSolutions t = new ProblemSolutions();
      Assert.assertEquals(9, t.trap(new int[] {4,2,0,3,2,5}));
      Assert.assertEquals(6, t.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
      Assert.assertEquals(0, t.trap(new int[] {1,1,1,1,1,1,1,1,1}));
  }

  @Test
  public void hammingWeight() {
      ProblemSolutions t = new ProblemSolutions();
      Assert.assertEquals(3, t.hammingWeight(11));
      Assert.assertEquals(1, t.hammingWeight(128));
      Assert.assertEquals(1, t.hammingWeight(Integer.MIN_VALUE));
      Assert.assertEquals(31, t.hammingWeight(Integer.MAX_VALUE));
  }

  @Test
  public void computeArea() {
    ProblemSolutions t = new ProblemSolutions();
    Assert.assertEquals(24, t.computeArea(-2, -2, 2, 2, -3, -3, 3, -1));
    Assert.assertEquals(45, t.computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    Assert.assertEquals(16, t.computeArea(-2, -2, 2, 2, -2, -2, 2, 2));
  }

  @Test
  public void minSubArrayLen() {
      ProblemSolutions t = new ProblemSolutions();
      Assert.assertEquals(0, t.minSubArrayLen(0, new int[] {}));
      Assert.assertEquals(2, t.minSubArrayLen(7, new int[] {2,3,1,2,4,3}));
  }

  @Test
  public void majorityElements() {
      ProblemSolutions t = new ProblemSolutions();
      int[] expected;
      int[] input;
      List<Integer> result;

      input = new int[] {1,1,1,3,3,2,2,2};
      expected = new int[] {1,2};
      result = t.majorityElements(input);
      checkListAgainstArray(result, expected);

      input = new int[] {1,2};
      expected = new int[] {1,2};
      result = t.majorityElements(input);
      checkListAgainstArray(result, expected);

      input = new int[] {1,1,2};
      expected = new int[] {1};
      result = t.majorityElements(input);
      checkListAgainstArray(result, expected);


  }

  private void checkListAgainstArray(List<Integer> result, int[] expected){
    Assert.assertEquals(expected.length, result.size());
    for(int i = 0; i < expected.length; i++){
      Assert.assertEquals(expected[i], (int) result.get(i));
    }
  }

  @Test
  public void integerReplacement() {
      ProblemSolutions t = new ProblemSolutions();
      Assert.assertEquals(32, t.integerReplacement(Integer.MAX_VALUE));
      Assert.assertEquals(3, t.integerReplacement(8));
      Assert.assertEquals(6, t.integerReplacement(23));
  }

  @Test
  public void longestPalindromeFromSet() {
      ProblemSolutions t = new ProblemSolutions();
      Assert.assertEquals(7, t.longestPalindromeFromSet("abccccdd"));
  }

  @Test
  public void pacificAtlantic() {
      ProblemSolutions t = new ProblemSolutions();
      int[][] input;
      List<int[]> result;
      boolean[][] expected;
      int[][] expectedLoader;
      input = new int[][] {{1,2,2,3,5}, {3,2,3,4,4},
          {2,4,5,3,1}, {6,7,1,4,5}, {5,1,1,2,4}};
      expected = new boolean[input.length][input[0].length];
      expectedLoader = new int[][] {{0,4}, {1,3}, {1,4}, {2,2}, {3, 0}, {3,1}, {4, 0}};
      for(int i = 0; i < expectedLoader.length; i++){
        expected[expectedLoader[i][0]][expectedLoader[i][1]] = true;
      }
      result = t.pacificAtlantic(input);

      for(int i = 0; i < result.size(); i++){
        int[] curResult = result.get(i);
        Assert.assertTrue(expected[curResult[0]][curResult[1]]);
      }

      Assert.assertTrue(t.pacificAtlantic(new int[][] {{}}).isEmpty());
      Assert.assertTrue(t.pacificAtlantic(null).isEmpty());
  }

  @Test
  public void addTwoNumbersList() {
      ProblemSolutions t = new ProblemSolutions();
      ListNode l1;
      ListNode l2;
      ListNode result;
      int[] expected;
      int[] l1AsArr;
      int[] l2AsArr;

      l1AsArr = new int[] {7,2,4,3};
      l2AsArr = new int[] {5,6,4};
      l1 = new ListNode(7);
      ListNode tmp1 = l1;
      for(int i = 1; i < l1AsArr.length; i++) {
        tmp1.next = new ListNode(l1AsArr[i]);
        tmp1 = tmp1.next;
      }

      l2 = new ListNode(5);
      ListNode tmp2 = l2;
      for(int i = 1; i < l2AsArr.length; i++) {
        tmp2.next = new ListNode(l2AsArr[i]);
        tmp2 = tmp2.next;
      }

      result = t.addTwoNumbersList(l1, l2);
      expected = new int[] {7,8,0,7};
      for(int i = 0; i < expected.length; i++){
        Assert.assertNotNull(result);
        Assert.assertEquals(expected[i], result.val);
        result = result.next;
      }
  }

  @Test
  public void findDuplicates() {
      ProblemSolutions t = new ProblemSolutions();
      int[] input;
      int[] expected;
      List<Integer> result;

      input = new int[] {3,4,1,5,2,6,3,6};
      expected = new int[] {3,6};
      result = t.findDuplicates(input);
      checkListAgainstArray(result, expected);

      input = new int[] {4,3,2,7,8,2,3,1};
      expected = new int[] {3,2};
      result = t.findDuplicates(input);
      checkListAgainstArray(result, expected);
  }

  @Test
  public void countBattleships() {
      ProblemSolutions t = new ProblemSolutions();
      char[][] input;

      input = new char[][] {{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
      Assert.assertEquals(2, t.countBattleships(input));
      Assert.assertEquals(0, t.countBattleships(new char[][] {{}}));
  }

  @Test
  public void altCountBattleships() {
    ProblemSolutions t = new ProblemSolutions();
    char[][] input;

    input = new char[][] {{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
    Assert.assertEquals(2, t.countBattleships(input));
    Assert.assertEquals(0, t.countBattleships(new char[][] {{}}));
  }

  @Test
  public void countSegments() {
      ProblemSolutions t = new ProblemSolutions();
      Assert.assertEquals(5, t.countSegments("hello from the inter nets"));
      Assert.assertEquals(6, t.countSegments(".   ,  ,,,. ' 0ad an.,,"));
  }

  @Test
  public void drawFigureTest(){
      ProblemSolutions t = new ProblemSolutions();

      String[] expected;
      String[] result;

      expected = new String[]{
          "###########",
          "##       ##",
          "# #     # #",
          "#  #   #  #",
          "#   # #   #",
          "#    #    #",
          "#   # #   #",
          "#  #   #  #",
          "# #     # #",
          "##       ##",
          "###########"
      };

      result = t.drawFigure(11, ' ', '#');

      Assert.assertEquals(expected.length, result.length);
      for(int i=0; i<expected.length; i++){
        Assert.assertTrue(expected[i].equals(result[i]));
      }

      expected = new String[]{
          "HHHHHH",
          "HH  HH",
          "H HH H",
          "H HH H",
          "HH  HH",
          "HHHHHH"
      };

      result = t.drawFigure(6, ' ', 'H');
      Assert.assertEquals(expected.length, result.length);
      for(int i=0; i<expected.length; i++){
        Assert.assertTrue(expected[i].equals(result[i]));
      }
  }
}