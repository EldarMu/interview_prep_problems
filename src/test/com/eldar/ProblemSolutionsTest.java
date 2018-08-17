package com.eldar;

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
}