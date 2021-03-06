package com.eldar;

import com.eldar.commonDataStructs.*;
import java.util.*;
import java.util.stream.Stream;

/*
//Class to store the various methods implemented over the course of interview practice
//interview problem urls listed
//occasionally, numerous solutions (generally a naive one to structure thoughts and an actually good one) are listed
//each problem prefaced with a commented description
*/

public class ProblemSolutions {

  //Given an array of ints, find whether the provided value exists as a sum of two values in the array
  //http://www.techiedelight.com/find-pair-with-given-sum-array/
  //will return list of int pairs, if no values found, list will be empty
  public List<int[]> naiveSumInArray(int[] array, int sum) {
    List<int[]> results = new LinkedList<>();
    for (int i = 0; i < array.length; i++) {
      for (int j = i + 1; j < array.length; j++) {
        if (array[i] + array[j] == sum) {
          int[] indices = {i, j};
          results.add(indices);
        }
      }
    }
    return results;
  }

  public List<int[]> hashSumInArray(int[] array, int sum) {
    List<int[]> results = new LinkedList<>();
    Map<Integer, Integer> indecesOfValues = new HashMap<>();
    for (int i = 0; i < array.length; i++) {
      indecesOfValues.put(array[i], i);
    }

    Map<Integer, Integer> alreadyAddedPairs = new HashMap<>();
    for (int i = 0; i < array.length; i++) {
      int soughtValue = sum - array[i];
      if (indecesOfValues.containsKey(soughtValue) && i != indecesOfValues.get(soughtValue)) {
        int[] indices = {i, indecesOfValues.get(soughtValue)};

        if (!alreadyAddedPairs.containsKey(indices[0])
            || alreadyAddedPairs.get(indices[0]) != indices[1]) {
          alreadyAddedPairs.put(indices[1], indices[0]);
          results.add(indices);
        }
      }
    }
    return results;
  }

  //Given a string of distinct characters J, return a count of those characters appearing in string S
  //https://leetcode.com/problems/jewels-and-stones/description/
  public int hashCountJewels(String distChars, String str) {
    Map<Character, Boolean> jewelTypes = new HashMap<>();

    for (char ch : distChars.toCharArray()) {
      jewelTypes.put(ch, true);
    }
    int count = 0;
    for (char ch : str.toCharArray()) {
      count = jewelTypes.containsKey(ch) ? count + 1 : count;
    }
    return count;
  }

  //all characters are guaranteed to be letters, so let's try using this to our advantage
  //lowercase z is number 122, uppercase A is 65, difference is 57, 58 elements though since it starts at 0
  //solution runtime faster than 93.32% other Java solutions, alright
  public int asciiCountJewels(String distChars, String str) {
    boolean[] asciiCounts = new boolean[58];
    for (int i = 0; i < distChars.length(); i++) {
      asciiCounts[(int) distChars.charAt(i) - 65] = true;
    }
    int count = 0;
    for (int j = 0; j < str.length(); j++) {
      count = asciiCounts[(int) str.charAt(j) - 65] ? count + 1 : count;

    }
    return count;
  }

  //given a 2d array of positive ints, find by how much all the elements in the array can be increased
  //while preserving the maximum value of each row and column
  //https://leetcode.com/problems/max-increase-to-keep-city-skyline/description/
  public int maxIncreaseInSkyline(int[][] grid) {
    int vertArrHeight = grid.length;
    int horizArrHeight = grid[0].length;
    int[] maxVertHeights = new int[vertArrHeight];
    int[] maxHorizHeights = new int[horizArrHeight];

    for (int i = 0; i < vertArrHeight; i++) {
      for (int j = 0; j < vertArrHeight; j++) {
        int heightInCell = grid[i][j];
        maxVertHeights[i] = maxVertHeights[i] < heightInCell ? heightInCell : maxVertHeights[i];
        maxHorizHeights[j] = maxHorizHeights[j] < heightInCell ? heightInCell : maxHorizHeights[j];
      }
    }
    int sumOfExtraHeights = 0;
    for (int i = 0; i < vertArrHeight; i++) {
      for (int j = 0; j < vertArrHeight; j++) {
        int heightInCell = grid[i][j];
        if (maxVertHeights[i] > maxHorizHeights[j]) {
          sumOfExtraHeights += maxHorizHeights[j] - heightInCell;
        } else {
          sumOfExtraHeights += maxVertHeights[i] - heightInCell;
        }
      }
    }
    return sumOfExtraHeights;
  }

  //unique morse code words
  //given a list of words and the morse code for the letters, many words will have the same representation
  //find all unique representations in the list
  //ascii lowercase a is 97
  public int uniqueMorseCodeRepresentations(String[] words) {
    int count = 0;
    String[] morseCode = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
        "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--",
        "-..-", "-.--", "--.."};
    Map<String, Boolean> wordAlreadyOccured = new HashMap<>();
    for (int i = 0; i < words.length; i++) {
      StringBuilder morseString = new StringBuilder();
      for (int j = 0; j < words[i].length(); j++) {
        morseString.append(morseCode[(int) words[i].charAt(j) - 97]);
      }
      if (!wordAlreadyOccured.containsKey(morseString.toString())) {
        count++;
        wordAlreadyOccured.put(morseString.toString(), true);
      }
    }
    return count;
  }

  //calculate sum of a and b without using + or -
  //using bit operations is pretty straightforward here
  //https://leetcode.com/problems/sum-of-two-integers/
  public int sumWithoutOperators(int a, int b) {
    int intermed;
    while (b != 0) {
      intermed = a & b;
      a = a ^ b;
      b = intermed << 1;
    }
    return a;
  }

  //given a product that costs 5 and no initial change in the drawer,
  //return true if it is possible to give every customer exact change
  //https://leetcode.com/problems/lemonade-change
  // a customer that has 5 needs no change
  // a customer that has 10 needs a 5
  // a customer that has 20 needs either three 5s or a 10 and a 5
  // preference given to handing out 10s to customers with 20s.
  public boolean returnChange(int[] bills) {
    boolean possibleToReturnChange = true;
    int tens = 0;
    int fives = 0;
    int iterator = 0;
    while (iterator < bills.length && possibleToReturnChange) {
      if (bills[iterator] == 5) {
        fives++;
      } else if (bills[iterator] == 10) {
        if (fives == 0) {
          possibleToReturnChange = false;
        } else {
          tens++;
          fives--;
        }
      } else {
        if (tens > 0 && fives > 0) {
          tens--;
          fives--;
        } else if (fives > 2) {
          fives -= 3;
        } else {
          possibleToReturnChange = false;
        }
      }
      iterator++;
    }
    return possibleToReturnChange;
  }

  //given an array of n integers and an int k, return the k most frequent elements
  //https://leetcode.com/problems/top-k-frequent-elements/description/
  //better than O(NlogN) complexity
  //going to try doing it without implementing a max-heap
  //beats 46% of solution runtimes, so really average.
  public List<Integer> topKFrequent(int[] nums, int k) {
    NumCount[] mostFrequentArr = new NumCount[nums.length];
    int lastUnfilledIndex = 0;
    Map<Integer, NumCount> occuringNumsMap = new HashMap<>();
    int maxCount = 0;
    for (int i = 0; i < nums.length; i++) {
      if (!occuringNumsMap.containsKey(nums[i])) {
        NumCount newVal = new NumCount(nums[i], 1, lastUnfilledIndex);
        occuringNumsMap.put(nums[i], newVal);
        mostFrequentArr[lastUnfilledIndex] = newVal;
        lastUnfilledIndex++;
      } else {
        NumCount curVal = occuringNumsMap.get(nums[i]);
        curVal.count++;
        while (curVal.index != 0 && curVal.count > mostFrequentArr[curVal.index - 1].count) {
          int curIndex = curVal.index;
          int newIndex = curVal.index - 1;
          mostFrequentArr[curIndex] = mostFrequentArr[newIndex];
          mostFrequentArr[curIndex].index = curIndex;
          mostFrequentArr[newIndex] = curVal;
          curVal.index = newIndex;
        }
      }
    }
    List<Integer> results = new LinkedList<>();
    for (int i = 0; i < k; i++) {
      results.add(mostFrequentArr[i].value);
    }
    return results;
  }

  class NumCount {

    int index;
    int count;
    int value;

    public NumCount(int value, int count, int index) {
      this.index = index;
      this.count = count;
      this.value = value;
    }
  }

  //given an array of numbers, build a maximum binary tree in the following way:
  //the max value becomes the root, the max value of the array slice to its left becomes
  //the left child, and so forth..
  //https://leetcode.com/problems/maximum-binary-tree/
  //Beat 100% of solution runtimes
  public TreeNode constructMaximumBinaryTree(int[] nums) {
    return recursBuildTree(0, nums.length - 1, nums);
  }

  private TreeNode recursBuildTree(int leftIndex, int rightIndex, int[] nums) {
    int maxVal = nums[leftIndex];
    int maxValIndex = leftIndex;
    if (leftIndex == rightIndex) {
      return new TreeNode(nums[leftIndex]);
    } else {
      for (int i = leftIndex; i <= rightIndex; i++) {
        if (maxVal < nums[i]) {
          maxVal = nums[i];
          maxValIndex = i;
        }
      }
    }

    TreeNode curNode = new TreeNode(maxVal);
    curNode.left =
        maxValIndex == leftIndex ? null : recursBuildTree(leftIndex, maxValIndex - 1, nums);
    curNode.right =
        maxValIndex == rightIndex ? null : recursBuildTree(maxValIndex + 1, rightIndex, nums);
    return curNode;
  }

  //given a two dimensional matrix of ones and zeros, flip it horizontally, then invert the values
  //https://leetcode.com/problems/flipping-an-image
  //it's just a test of understanding java 2d matrices
  //runtime better than 100% of submissions
  public int[][] flipAndInvertImage(int[][] matrix) {
    int columnLen = matrix[0].length;
    boolean oddColumnNum = columnLen % 2 == 1;
    int tmp = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < (columnLen / 2); j++) {
        tmp = matrix[i][j];
        matrix[i][j] = 1 ^ matrix[i][columnLen - 1 - j];
        matrix[i][columnLen - 1 - j] = 1 ^ tmp;
      }
      if (oddColumnNum) {
        matrix[i][(columnLen / 2)] = 1 ^ matrix[i][(columnLen / 2)];
      }
    }
    return matrix;
  }

  //given an array of ints and a sum,
  //return indices of ints that together produce the sum
  //https://leetcode.com/problems/two-sum
  //beats runtime of 98.38% of submissions
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> valsAndIndeces = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (valsAndIndeces.containsKey(target - nums[i])) {
        return new int[]{valsAndIndeces.get(target - nums[i]), i};
      }
      if (!valsAndIndeces.containsKey(nums[i])) {
        valsAndIndeces.put(nums[i], i);
      }
    }
    for (int j = 0; j < nums.length; j++) {
      if (valsAndIndeces.containsKey(target - nums[j])) {
        return new int[]{j, valsAndIndeces.get(target - nums[j])};
      }
    }
    return new int[]{0, 0};
  }

  //given two non-empty linked-lists representing two numbers
  //with each digit as a separate node, in reverse order
  //return the sum of the numbers
  //https://leetcode.com/problems/add-two-numbers
  //beat 99.74% of solution runtimes (for Java)
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    List<ListNode> listNodeLinkedList = new LinkedList<>();
    boolean carryone = false;
    ListNode curL1 = l1;
    ListNode curL2 = l2;
    while (curL1 != null || curL2 != null) {
      boolean firstListDone = curL1 == null;
      boolean secondListDone = curL2 == null;
      int initVal = 0;
      if (!firstListDone && !secondListDone) {
        initVal = curL1.val + curL2.val;
      } else if (firstListDone && !secondListDone) {
        initVal = curL2.val;
      } else if (!firstListDone && secondListDone) {
        initVal = curL1.val;
      }
      initVal = carryone ? initVal + 1 : initVal;
      carryone = initVal > 9;
      initVal = initVal % 10;
      listNodeLinkedList.add(new ListNode(initVal));
      curL1 = curL1 == null ? null : curL1.next;
      curL2 = curL2 == null ? null : curL2.next;
    }
    if (carryone) {
      listNodeLinkedList.add(new ListNode(1));
    }
    for (int i = 0; i < listNodeLinkedList.size() - 1; i++) {
      listNodeLinkedList.get(i).next = listNodeLinkedList.get(i + 1);
    }
    return listNodeLinkedList.get(0);
  }

  //Given a string, find the longest substring consisting of non-repeating elements
  //
  public int lengthOfLongestSubstring(String s) {
    int result = 0;
    int[] asciiVals = new int[127];
    Arrays.fill(asciiVals, -1);
    int startIndex = 0;
    int substrEndIndex = 0;
    int substrLen = 0;
    while (substrEndIndex < s.length()) {
      char curChar = s.charAt(substrEndIndex);
      if (asciiVals[curChar] == -1) {
        asciiVals[curChar] = substrEndIndex;
        substrLen++;
        result = substrLen > result ? substrLen : result;
        substrEndIndex++;
      } else {
        int repeatIndex = asciiVals[curChar];
        for (int i = startIndex; i <= repeatIndex; i++) {
          asciiVals[s.charAt(i)] = -1;
          substrLen--;
        }
        startIndex = repeatIndex + 1;
      }
    }
    return result;
  }

  //find the longest palindrome in a string, return the palindrome
  //approach seeks to find the potential centers, and work away from them to palindrome max
  public String longestPalindrome(String s) {
    if (s.length() < 2) {
      return s;
    }
    int leftPalinIndex = 0;
    int rightPalinIndex = 0;
    int longestPalinLen = 0;
    char[] strAsCharArr = s.toCharArray();
    for (int i = 0; i < strAsCharArr.length; i++) {
      if (i > 0 && i < strAsCharArr.length - 1) {
        if (strAsCharArr[i - 1] == strAsCharArr[i + 1]) {
          int tempPalinLen = 3;
          int left = i - 1;
          int right = i + 1;
          while (left > 0 && right < strAsCharArr.length - 1) {
            if (strAsCharArr[left - 1] == strAsCharArr[right + 1]) {
              tempPalinLen += 2;
              left--;
              right++;
            } else {
              break;
            }
          }
          if (longestPalinLen < tempPalinLen) {
            longestPalinLen = tempPalinLen;
            leftPalinIndex = left;
            rightPalinIndex = right;
          }
        }
      }
      if (i < strAsCharArr.length - 1) {
        if (strAsCharArr[i] == strAsCharArr[i + 1]) {
          int tempPalinLen = 2;
          int left = i;
          int right = i + 1;
          while (left > 0 && right < strAsCharArr.length - 1) {
            if (strAsCharArr[left - 1] == strAsCharArr[right + 1]) {
              tempPalinLen += 2;
              left--;
              right++;
            } else {
              break;
            }
          }
          if (longestPalinLen < tempPalinLen) {
            longestPalinLen = tempPalinLen;
            leftPalinIndex = left;
            rightPalinIndex = right;
          }
        }
      }
    }
    return s.substring(leftPalinIndex, rightPalinIndex + 1);
  }

  //convert zig-zag text (string, with number of rows it's spread on)
  //into the resulting flat string with rows read top to bottom
  //https://leetcode.com/problems/zigzag-conversion/description/
  //text first goes down, with 1 per row, then up diagonally, and repeat
  public String zigZagConvert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }
    List<StringBuilder> rowStrings = new ArrayList<>(numRows);
    char[] charVerOfStr = s.toCharArray();
    for (int i = 0; i < numRows; i++) {
      rowStrings.add(i, new StringBuilder(""));
    }
    boolean goingDown = false;
    boolean goingUpDiagonally = false;
    int rowPtr = 0;
    for (int j = 0; j < charVerOfStr.length; j++) {
      if (rowPtr == 0) {
        goingDown = true;
        goingUpDiagonally = false;
      } else if (rowPtr == numRows - 1) {
        goingDown = false;
        goingUpDiagonally = true;
      }
      if (goingDown) {
        rowStrings.get(rowPtr).append(charVerOfStr[j]);
        rowPtr++;
      } else if (goingUpDiagonally) {
        rowStrings.get(rowPtr).append(charVerOfStr[j]);
        rowPtr--;
      }
    }
    StringBuilder result = new StringBuilder("");
    for (int k = 0; k < rowStrings.size(); k++) {
      result.append(rowStrings.get(k));
    }
    return result.toString();
  }

  //given a signed integer, return it with the digits reversed (leading zeros removed, sign kept)
  //if it overflows return 0
  //https://leetcode.com/problems/reverse-integer/description/
  //like a regex problem, this one is defined by the edge cases of what technically counts as
  //a valid way to write a number.
  public int integerReverse(int x) {
    String intAsChArr = Integer.toString(x);
    boolean negative = intAsChArr.charAt(0) == '-';
    int endVal = negative ? 1 : 0;
    int startVal = intAsChArr.length() - 1;
    char[] reverseChArr = new char[startVal - endVal + 1];
    int secondaryIndex = 0;
    for (int j = startVal; j >= endVal; j--) {
      reverseChArr[secondaryIndex] = intAsChArr.charAt(j);
      secondaryIndex++;
    }
    long absResult = Long.parseLong(new String(reverseChArr));
    if (absResult > Integer.MAX_VALUE) {
      return 0;
    }
    return negative ? (int) -absResult : (int) absResult;
  }

  //parse an int from a string that may contain words or other noise;
  public int myAtoi(String str) {
    if (str.length() == 0) {
      return 0;
    }
    int startOfNum = 0;
    int endOfNum = 0;
    boolean startFound = false;
    boolean endFound = false;
    int indexPtr = 0;
    boolean initialSignFound = false;
    boolean initialMinus = false;
    while (!startFound && indexPtr < str.length()) {
      char ch = str.charAt(indexPtr);
      if ((ch == '-' || ch == '+') && indexPtr + 1 < str.length() && Character
          .isDigit(str.charAt(indexPtr + 1))) {
        if (ch == '-') {
          initialMinus = true;
        }
        startFound = true;
        startOfNum = indexPtr;
        initialSignFound = true;
      } else if (!Character.isDigit(ch) && ch != ' ') {
        return 0;
      } else if (Character.isDigit(str.charAt(indexPtr))) {
        startFound = true;
        startOfNum = indexPtr;
      } else {
        indexPtr++;
        startOfNum++;
      }
    }
    endOfNum = initialSignFound ? ++indexPtr : indexPtr;
    while (!endFound && indexPtr < str.length()) {
      char ch = str.charAt(indexPtr);
      if (Character.isDigit(ch)) {
        indexPtr++;
        endOfNum++;
      } else {
        endFound = true;
      }
    }
    if (startOfNum >= endOfNum) {
      return 0;
    }
    long result = Long.MAX_VALUE;
    try {
      result = Long.parseLong(str.substring(startOfNum, endOfNum));
    } catch (Exception e) {
      return initialMinus ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    }
    if (result > Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    }
    if (result < Integer.MIN_VALUE) {
      return Integer.MIN_VALUE;
    }
    return (int) result;
  }

  //determine if a number is a palindrome. Signs matter
  //https://leetcode.com/problems/palindrome-number/
  public boolean isNumAPalindrome(int x) {
    String str = Integer.toString(x);
    for (int i = 0; i < str.length() / 2; i++) {
      if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
        return false;
      }
    }
    return true;
  }

  //given an array of integers, representing heights of lines, with their distance from each other
  //in the array representing length of a side of a hypothetical rectangle they form
  //return the maximum area possible between any two heights in the array.
  //the shorter height defines the height of the rectangle
  //https://leetcode.com/problems/container-with-most-water/description/
  //solution faster than 92% of submissions
  public int maxArea(int[] height) {
    int curMaxArea = 0;
    int leftPtr = 0;
    int rightPtr = height.length - 1;
    while (leftPtr != rightPtr) {
      boolean leftHeightSmaller = height[leftPtr] < height[rightPtr];
      int tmpArea = leftHeightSmaller ? height[leftPtr] * (rightPtr - leftPtr)
          : height[rightPtr] * (rightPtr - leftPtr);
      if (tmpArea > curMaxArea) {
        curMaxArea = tmpArea;
      }
      if (leftHeightSmaller) {
        leftPtr++;
      } else {
        rightPtr--;
      }
    }
    return curMaxArea;
  }

  //find longest common prefix in a string array
  //return empty string if no common prefix
  //https://leetcode.com/problems/longest-common-prefix/description/
  public String longestCommonPrefix(String[] strs) {
    if (strs.length < 1) {
      return "";
    } //in reality ought to throw an IllegalArgumentException for empty array
    int maxPotentialPrefixLen = strs[0].length();
    for (int i = 1; i < strs.length; i++) {
      maxPotentialPrefixLen =
          maxPotentialPrefixLen > strs[i].length() ? strs[i].length() : maxPotentialPrefixLen;
      int counter = maxPotentialPrefixLen - 1;
      while (counter >= 0) {
        if (strs[0].charAt(counter) != strs[i].charAt(counter)) {
          maxPotentialPrefixLen = counter;
        }
        counter--;
      }
      if (maxPotentialPrefixLen == 0) {
        return "";
      }
    }
    return strs[0].substring(0, maxPotentialPrefixLen);
  }

  //3sum problem
  //given an array of ints and a target value, find all unique combinations of values
  //such that their sum is zero
  //no recombinations of triplets already entered into the result list
  //https://leetcode.com/problems/3sum/description/
  //just a bog standard two pointer two-sum solution.
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> results = new LinkedList<>();
    for (int i = 0; i < nums.length - 2; i++) {
      if (i == 0 || nums[i] != nums[i - 1]) {
        int leftPtr = i + 1;
        int rightPtr = nums.length - 1;
        int soughtVal = 0 - nums[i];
        while (leftPtr < rightPtr) {
          if (nums[leftPtr] + nums[rightPtr] == soughtVal) {
            List<Integer> tmp = new LinkedList<>();
            tmp.add(nums[i]);
            tmp.add(nums[leftPtr]);
            tmp.add(nums[rightPtr]);
            results.add(tmp);
            while (leftPtr < rightPtr && nums[leftPtr] == nums[leftPtr + 1]) {
              leftPtr++;
            }
            while (leftPtr < rightPtr && nums[rightPtr] == nums[rightPtr - 1]) {
              rightPtr--;
            }
            leftPtr++;
            rightPtr--;
          } else if (nums[leftPtr] + nums[rightPtr] < soughtVal) {
            leftPtr++;
          } else {
            rightPtr--;
          }
        }
      }
    }
    return results;
  }

  //given a stream of integers, calculate the median value
  public double medianOfIntStream(Stream<Integer> intStream) {
    Queue<Integer> minHeap = new PriorityQueue<>();
    Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    intStream.forEach(x -> updateMedian(x, minHeap, maxHeap));
    if (minHeap.size() == maxHeap.size()) {
      return (double) (minHeap.peek() + maxHeap.peek()) / 2.0;
    } else if (minHeap.size() > maxHeap.size()) {
      return (double) minHeap.peek();
    } else {
      return (double) maxHeap.peek();
    }
  }

  private void updateMedian(int x, Queue<Integer> minHeap, Queue<Integer> maxHeap) {
    minHeap.offer(x);
    maxHeap.offer(minHeap.poll());
    if (maxHeap.size() != minHeap.size()) {
      minHeap.offer(maxHeap.poll());
    }
  }

  //given a string consisting of digits 2-9, with each digit corresponding to up to 4 letters
  //(as one might see on a home phone), return an array of strings consisting of all possible
  //letter combinations resulting from the digits
  //https://leetcode.com/problems/letter-combinations-of-a-phone-number/
  public List<String> letterCombinations(String digits) {
    if (digits.length() == 0) {
      return new LinkedList<>();
    }
    Map<Character, String> combinations = new HashMap<>();
    combinations.put('2', "abc");
    combinations.put('3', "def");
    combinations.put('4', "ghi");
    combinations.put('5', "jkl");
    combinations.put('6', "mno");
    combinations.put('7', "pqrs");
    combinations.put('8', "tuv");
    combinations.put('9', "wxyz");

    int totalStringNum = 1;
    for (int i = 0; i < digits.length(); i++) {
      totalStringNum *= combinations.get(digits.charAt(i)).length();
    }

    char[][] resultsAsChArr = new char[totalStringNum][digits.length()];
    int digitsIndex = 0;
    while (digitsIndex < digits.length()) {
      char tmpChar = digits.charAt(digitsIndex);
      String tmpStr = combinations.get(tmpChar);
      int repeatsPerChar = totalStringNum / tmpStr.length();
      int letterIndex = 0;
      int letterRepeatCount = 0;
      for (int i = 0; i < resultsAsChArr.length; i++) {
        resultsAsChArr[i][digitsIndex] = tmpStr.charAt(letterIndex);
        letterRepeatCount++;
        if (letterRepeatCount == repeatsPerChar) {
          letterRepeatCount = 0;
          letterIndex = letterIndex + 1 == tmpStr.length() ? 0 : letterIndex + 1;
        }
      }
      totalStringNum /= tmpStr.length();
      digitsIndex++;
    }
    List<String> results = new LinkedList<>();
    for (int k = 0; k < resultsAsChArr.length; k++) {
      results.add(new String(resultsAsChArr[k]));
    }
    return results;
  }


  //remove nth from the end list node in linear time
  //given a singly-linked list (e.g. the head of a linked list)
  //https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
  //beats 99.9% of java submissions.
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head.next == null) {
      return null;
    }
    ListNode pointerToNth = head;
    ListNode curNode = head;
    int iterator = 0;
    while (curNode.next != null) {
      curNode = curNode.next;
      iterator++;
      if (iterator > n) {
        pointerToNth = pointerToNth.next;
      }
    }
    //all of that works with the premise that the curNode has a chance to move at least once
    //if the ith element is the head, just return head.next
    if (iterator + 1 == n) {
      return head.next;
    }
    pointerToNth.next = pointerToNth.next.next;
    return head;
  }

  //given a string of parentheses (){}[], check if the string is valid
  //meaning parentheses of the same type are closed before closing parentheses of another
  //type are opened and every opened parenthesis is closed.
  //initial solution used stack data structure, found a much more elegant idea using
  //an array as a stack and case switch. kudos to mario.macias.upc on leetcode
  //beats 96% of solutions
  public boolean isValidParentheses(String s) {
    char[] stack = new char[s.length()];
    int ptr = 0;
    boolean pointerAtZero;
    for (char c : s.toCharArray()) {
      pointerAtZero = ptr == 0;
      switch (c) {
        case '(':
        case '{':
        case '[':
          stack[ptr++] = c;
          break;
        case ')':
          if (pointerAtZero || stack[--ptr] != '(') {
            return false;
          }
          break;
        case '}':
          if (pointerAtZero || stack[--ptr] != '{') {
            return false;
          }
          break;
        case ']':
          if (pointerAtZero || stack[--ptr] != '[') {
            return false;
          }
          break;
        default:
          throw new IllegalArgumentException("input contains a character not in '()[]{}'");
      }
    }
    return ptr == 0;
  }

  //merge k sorted lists of listnodes (given head nodes in an array)
  //https://leetcode.com/problems/merge-k-sorted-lists/description/
  //took three revisions, but it beats 70% of java submissions
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) {
      return null;
    } //really should thrown InvalidArgumentException
    int listArrMax = lists.length;
    int listIndex = 0;
    int firstListFoundIndex = 0;
    boolean firstListFound = false;
    while (listArrMax > 1) {
      for (int i = 0; i < listArrMax; i++) {
        if (lists[i] == null) {
          continue;
        } else if (firstListFound) {
          lists[listIndex++] = merge2Lists(lists[firstListFoundIndex], lists[i]);
          firstListFound = false;
        } else {
          firstListFoundIndex = i;
          firstListFound = true;
        }
      }
      if (firstListFound) {
        lists[listIndex++] = lists[firstListFoundIndex];
      }
      firstListFound = false;
      firstListFoundIndex = 0;
      listArrMax = listIndex;
      listIndex = 0;
    }
    return lists[0];
  }

  private ListNode merge2Lists(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) {
      return null;
    } else if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    }
    ListNode head;
    ListNode cur;
    if (l1.val < l2.val) {
      head = l1;
      cur = head;
      l1 = l1.next;
    } else {
      head = l2;
      cur = head;
      l2 = l2.next;
    }
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        cur.next = l1;
        cur = l1;
        l1 = l1.next;
      } else {
        cur.next = l2;
        cur = l2;
        l2 = l2.next;
      }
    }
    if (l1 == null) {
      cur.next = l2;
    }
    if (l2 == null) {
      cur.next = l1;
    }
    return head;
  }

  //given a linked list, for every two nodes, swap their positions
  //https://leetcode.com/problems/swap-nodes-in-pairs/description/
  //implemented solution as per approach taken by tusizi on leetcode
  //beats 100% of submissions.
  public ListNode swapPairs(ListNode head) {
    ListNode tmpHead = new ListNode(0);
    tmpHead.next = head;
    ListNode cur = tmpHead;
    while (cur.next != null && cur.next.next != null) {
      ListNode first = cur.next;
      ListNode second = first.next;
      cur.next = second;
      first.next = second.next;
      second.next = first;
      cur = cur.next.next;
    }
    return tmpHead.next;
  }

  //given an array potentially containing duplicates
  //modify the array such that, if it has n unique elements,
  //the first n positions of the array are occupied by them, in order of appearance
  //then return n
  //beats 100% of java submissions
  public int removeDuplicates(int[] nums) {
    if (nums.length < 2) {
      return nums.length;
    }
    int duplWriteIndex = 1;
    //given that we know nothing of the range of values
    //and thus can't simply use a new array's index positions
    //we choose to use a hashmap
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != nums[i - 1]) {
        nums[duplWriteIndex] = nums[i];
        duplWriteIndex++;
      }
    }
    return duplWriteIndex;
  }

  //return all combinations of provided integers that sum to target number
  //https://leetcode.com/problems/combination-sum/
  //bog-standard recursive solution
  //beats 98% of solutions, seemingly because there's no sorting and ArrayList instead of LinkedList
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> results = new LinkedList<>();
    combineInts(candidates, new ArrayList<>(), results, target, 0);
    return results;
  }

  private void combineInts(int[] candidates, List<Integer> curList, List<List<Integer>> fullList,
      int target, int startPos) {
    if (target == 0) {
      fullList.add(new ArrayList<>(curList));
    } else if (target < 0) {
      return;
    } else {
      for (int i = startPos; i < candidates.length; i++) {
        curList.add(candidates[i]);
        combineInts(candidates, curList, fullList, target - candidates[i], i);
        curList.remove(curList.size() - 1);
      }
    }
  }

  //given a double x and int n, return x to the nth power
  //x is between 100 and -100
  //https://leetcode.com/problems/powx-n/description/
  //ultimately the fastest solution was to use the "egyptian/russian/peasant" method
  //with bitwise operators
  //inspiration: FlorenceMachine on leetcode
  //beats 100% of results
  public double myPow(double x, int n) {
    long m = n > 0 ? n : -(long) n;
    double result = 1.0;
    while (m != 0) {
      if ((m & 1) == 1) {
        result *= x;
      }
      x *= x;
      m >>= 1;
    }
    return n > 0 ? result : 1 / result;
  }

  //given an array and a value requiring removal
  //return the same array and an int indicating new end of array
  //such that the selected value has been removed from that part
  //and all other values shifted left
  //https://leetcode.com/problems/remove-element/description/
  //faster than 99.98% of solutions
  public int removeElement(int[] nums, int val) {
    if (nums.length == 0) {
      return 0;
    }
    int writeIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        if (i == writeIndex) {
          writeIndex++;
        } else {
          nums[writeIndex] = nums[i];
          writeIndex++;
        }
      }
    }
    return writeIndex;
  }

  //given a string of words with spaces between them
  //perform an in-place reversal using constant extra memory
  //such that a string with the words in the opposite order is returned
  //note-since java strings are immutable, an in-place reversal on a char array is done instead
  public String reverseString(String words) {
    char[] wordsArr = words.toCharArray();
    int runningPtr = 0;
    int wordLen = 0;
    int wordStart = 0;
    while (runningPtr < wordsArr.length) {
      while (runningPtr < wordsArr.length && wordsArr[runningPtr] != ' ') {
        runningPtr++;
        wordLen++;
      }
      for (int i = 0; i < wordLen / 2; i++) {
        char tmp = wordsArr[wordStart + i];
        wordsArr[wordStart + i] = wordsArr[runningPtr - 1 - i];
        wordsArr[runningPtr - 1 - i] = tmp;
      }
      runningPtr++;
      wordStart = runningPtr;
      wordLen = 0;
    }
    for (int j = 0; j < wordsArr.length / 2; j++) {
      char tmp = wordsArr[j];
      wordsArr[j] = wordsArr[wordsArr.length - 1 - j];
      wordsArr[wordsArr.length - 1 - j] = tmp;
    }
    return new String(wordsArr);
  }

  //find first occurence of a substring in a string
  //https://leetcode.com/problems/implement-strstr/description/
  //bog standard solution without any substring algorithms used, just a hashmap.
  //technically linear time, though having to get all those substrings is a hidden cost
  public int strStr(String haystack, String needle) {
    if (haystack.length() == 0) {
      return 0;
    }
    if (needle.length() > haystack.length()) {
      return -1;
    }
    Map<String, Boolean> needleStorage = new HashMap<>(1);
    needleStorage.put(needle, true);
    for (int i = 0; i <= haystack.length() - needle.length(); i++) {
      if (needleStorage.containsKey(haystack.substring(i, i + needle.length()))) {
        return i;
      }
    }
    return -1;
  }

  //given a low->high sorted int array, find the starting and ending position of a value
  //return [-1,-1] if it is not present
  //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
  public int[] searchRange(int[] nums, int target) {
    if (nums.length == 0) {
      return new int[]{-1, -1};
    }
    return recursSearchRange(nums, 0, nums.length - 1, target);
  }

  private int[] recursSearchRange(int[] nums, int leftIndex, int rightIndex, int target) {
    if (leftIndex > rightIndex) {
      return new int[]{-1, -1};
    }
    if (rightIndex == leftIndex) {
      if (nums[leftIndex] == target) {
        return new int[]{leftIndex, leftIndex};
      } else {
        return new int[]{-1, -1};
      }
    }
    int centerVal = leftIndex + (rightIndex - leftIndex) / 2;
    if (nums[centerVal] == target) {
      int leftEnd = centerVal;
      int rightEnd = centerVal;
      while (leftEnd > 0 && nums[leftEnd - 1] == target) {
        leftEnd--;
      }
      while (rightEnd < nums.length - 1 && nums[rightEnd + 1] == target) {
        rightEnd++;
      }
      return new int[]{leftEnd, rightEnd};
    } else if (nums[centerVal] > target) {
      return recursSearchRange(nums, leftIndex, centerVal - 1, target);
    } else {
      return recursSearchRange(nums, centerVal + 1, rightIndex, target);
    }
  }

  //given a sorted int array and a target value
  //return either the index of the target value
  //or where it would be if it were in the array
  //https://leetcode.com/problems/search-insert-position/description/
  //beats 100% of java solutions, but the edge cases sure make it look ugly.
  public int searchInsert(int[] nums, int target) {
    //first a binary search that keeps track of the mid pointer externally
    int left = 0;
    int right = nums.length - 1;
    int curMid = 0;
    while (left < right) {
      curMid = left + (right - left) / 2;
      if (nums[curMid] > target) {
        right = curMid;
      } else if (nums[curMid] < target) {
        left = curMid + 1;
      } else {
        return curMid;
      }
    }
    //edge case - left==right, at target value
    //after this, we know for sure the value was not found
    if (nums[curMid] == target) {
      return curMid;
    }
    //only exception to rule - if target < nums[0] return 0;
    if (curMid == 0 && nums[curMid] > target) {
      return 0;
    }
    //decrement curMid if possible in case curMid is currently stuck
    //on the first value larger than it
    if (curMid != 0 && nums[curMid] > target) {
      curMid--;
    }
    //find index of largest value smaller than target, then add 1 to it.
    boolean rightAdjustDone = false;
    while (curMid < nums.length - 1 && !rightAdjustDone) {
      if (nums[curMid + 1] < target) {
        curMid++;
      } else {
        rightAdjustDone = true;
      }
    }
    return ++curMid;
  }

  //merge one array into another one (with sufficient extra space)
  //https://leetcode.com/problems/merge-sorted-array/description/
  //nums1-array to merge into, nums2-array to merge
  //nums1 has m initialized elements, nums2 has n;
  //beats 100% of java solutions
  public void mergeArrs(int[] nums1, int m, int[] nums2, int n) {
    int mPtr = 1;
    int nPtr = 1;
    int j = 0;
    while (m >= mPtr || n >= nPtr) {
      if (m >= mPtr && n >= nPtr) {
        if (nums1[m - mPtr] <= nums2[n - nPtr]) {
          nums1[nums1.length - 1 - j] = nums2[n - nPtr];
          nPtr++;
          j++;
        } else {
          nums1[nums1.length - 1 - j] = nums1[m - mPtr];
          mPtr++;
          j++;
        }
      } else if (mPtr > m) {
        nums1[nums1.length - 1 - j] = nums2[n - nPtr];
        nPtr++;
        j++;
      } else {
        nums1[nums1.length - 1 - j] = nums1[m - mPtr];
        mPtr++;
        j++;
      }
    }
  }

  //non-recursive in-order traversal of a binary tree
  //https://leetcode.com/problems/binary-tree-inorder-traversal/description/
  //we'll use a stack and a while loop
  //ultimately ended up using a hashmap to keep from double-writing values
  //standard solution
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> results = new ArrayList<>();
    if (root == null) {
      return results;
    }
    Deque<TreeNode> recursStack = new ArrayDeque<>();
    Map<TreeNode, Boolean> alreadyWritten = new HashMap<>();
    recursStack.push(root);
    while (!recursStack.isEmpty()) {
      TreeNode tmp = recursStack.peek();
      if (tmp.left != null && !alreadyWritten.containsKey(tmp.left)) {
        recursStack.push(tmp.left);
      } else {
        results.add(tmp.val);
        tmp = recursStack.pop();
        alreadyWritten.put(tmp, true);
        if (tmp.right != null && !alreadyWritten.containsKey(tmp.right)) {
          recursStack.push(tmp.right);
        }
      }
    }
    return results;
  }

  //count different number of ways to climb stairs
  //at any point with two or more stairsteps, an individual may take a normal or double step (1/2)
  //had to write it on paper, but it's just a fibonacci puzzle
  //probably best to avoid recursion here.
  //beats 100% of java solutions
  public int climbStairs(int n) {
    if (n < 1) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    int firstVal = 1;
    int secondVal = 2;
    int tmp;
    for (int i = 3; i <= n; i++) {
      tmp = firstVal + secondVal;
      firstVal = secondVal;
      secondVal = tmp;
    }
    return secondVal;
  }

  //given a list of non-overlapping intervals sorted by start times
  //and an interval to be inserted, book and merge as needed
  //then return the resulting list
  //https://leetcode.com/problems/insert-interval/description/
  //a very verbose solution that worked on the first try
  //and works faster than 96% of solutions
  public List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {
    Interval[] intervArr = intervals.toArray(new Interval[0]);
    List<Interval> resultList = new ArrayList<>();
    int smallerStartValIndex = -1;
    int biggerEndValIndex = intervArr.length;
    boolean startInInterval = false;
    boolean endInInterval = false;
    for (int i = 0; i < intervArr.length; i++) {
      if (intervArr[i].start <= newInterval.start) {
        smallerStartValIndex++;
      }
      if (intervArr[intervArr.length - 1 - i].end >= newInterval.end) {
        biggerEndValIndex--;
      }
    }
    if (smallerStartValIndex > -1 && intervArr[smallerStartValIndex].end >= newInterval.start) {
      startInInterval = true;
    }
    if (biggerEndValIndex < intervArr.length
        && intervArr[biggerEndValIndex].start <= newInterval.end) {
      endInInterval = true;
    }
    if (smallerStartValIndex == -1 && biggerEndValIndex == intervArr.length) {
      resultList.add(newInterval);
      return resultList;
    } else if (smallerStartValIndex == -1) {
      if (endInInterval) {
        newInterval.end = intervArr[biggerEndValIndex].end;
        biggerEndValIndex++;
      }
      resultList.add(newInterval);
      for (int j = biggerEndValIndex; j < intervArr.length; j++) {
        resultList.add(intervArr[j]);
      }
    } else if (biggerEndValIndex == intervArr.length) {
      if (startInInterval) {
        newInterval.start = intervArr[smallerStartValIndex].start;
        smallerStartValIndex--;
      }
      for (int j = 0; j <= smallerStartValIndex; j++) {
        resultList.add(intervArr[j]);
      }
      resultList.add(newInterval);
    } else {
      if (startInInterval) {
        newInterval.start = intervArr[smallerStartValIndex].start;
        smallerStartValIndex--;
      }
      if (endInInterval) {
        newInterval.end = intervArr[biggerEndValIndex].end;
        biggerEndValIndex++;
      }
      for (int j = 0; j <= smallerStartValIndex; j++) {
        resultList.add(intervArr[j]);
      }
      resultList.add(newInterval);
      for (int j = biggerEndValIndex; j < intervArr.length; j++) {
        resultList.add(intervArr[j]);
      }
    }
    return resultList;
  }

  //given a two-dimensional array (a 'board') of letters
  //find if a word can be constructed by connecting adjacent letters on the board
  //https://leetcode.com/problems/word-search/description/
  //beats 82% of java solutions
  public boolean wordExists(char[][] board, String word) {
    char[] wordArr = word.toCharArray();
    boolean[][] visited = new boolean[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == wordArr[0]) {
          visited[i][j] = true;
          if (recursWordExists(board, visited, i, j, wordArr, 1)) {
            return true;
          }
          visited[i][j] = false;
        }
      }
    }
    return false;
  }

  private boolean recursWordExists(char[][] board, boolean[][] visited, int row, int col,
      char[] word, int wordIndex) {
    if (wordIndex == word.length) {
      return true;
    }
    int tmpRow;
    int tmpCol;
    if (row > 0 && board[row - 1][col] == word[wordIndex]) {
      tmpRow = row - 1;
      tmpCol = col;
      if (!visited[tmpRow][tmpCol]) {
        visited[tmpRow][tmpCol] = true;
        if (recursWordExists(board, visited, tmpRow, tmpCol, word, wordIndex + 1)) {
          return true;
        }
        visited[tmpRow][tmpCol] = false;
      }
    }
    if (row < board.length - 1 && board[row + 1][col] == word[wordIndex]) {
      tmpRow = row + 1;
      tmpCol = col;
      if (!visited[tmpRow][tmpCol]) {
        visited[tmpRow][tmpCol] = true;
        if (recursWordExists(board, visited, tmpRow, tmpCol, word, wordIndex + 1)) {
          return true;
        }
        visited[tmpRow][tmpCol] = false;
      }
    }
    if (col > 0 && board[row][col - 1] == word[wordIndex]) {
      tmpRow = row;
      tmpCol = col - 1;
      if (!visited[tmpRow][tmpCol]) {
        visited[tmpRow][tmpCol] = true;
        if (recursWordExists(board, visited, tmpRow, tmpCol, word, wordIndex + 1)) {
          return true;
        }
        visited[tmpRow][tmpCol] = false;
      }
    }
    if (col < board[0].length - 1 && board[row][col + 1] == word[wordIndex]) {
      tmpRow = row;
      tmpCol = col + 1;
      if (!visited[tmpRow][tmpCol]) {
        visited[tmpRow][tmpCol] = true;
        if (recursWordExists(board, visited, tmpRow, tmpCol, word, wordIndex + 1)) {
          return true;
        }
        visited[tmpRow][tmpCol] = false;
      }
    }
    return false;
  }

  //given a sorted int array, remove duplicates (>2) by shifting array vals left
  //https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
  //a pretty standard solution
  public int removeTriplicates(int[] nums) {
    if (nums.length < 3) {
      return nums.length;
    }
    boolean foundWriteIndex = false;
    int curInt = nums[0];
    int curIntCount = 1;
    int writeIndex = 0;
    for (int i = 1; i < nums.length; i++) {
      if (curInt == nums[i]) {
        curIntCount++;
      } else {
        curInt = nums[i];
        curIntCount = 1;
      }
      if (!foundWriteIndex) {
        if (curIntCount == 3) {
          writeIndex = i;
          foundWriteIndex = true;
        }
      } else {
        if (curIntCount < 3) {
          nums[writeIndex] = nums[i];
          writeIndex++;
        }
      }
    }
    return foundWriteIndex ? writeIndex : nums.length;
  }

  //given a string S and a set of characters T
  //find the smallest substring of S containing all characters in T
  //https://leetcode.com/problems/minimum-window-substring/description/
  public String minWindow(String s, String t) {
    if (t == null || s == null || t.length() == 0 || s.length() == 0 || t.length() > s.length()) {
      return "";
    }
    Map<Character, Integer> subStrChars = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      subStrChars.put(t.charAt(i), 0);
    }
    Map<Character, List<Integer>> instOfChars = new HashMap<>();
    for (int j = 0; j < s.length(); j++) {
      if (subStrChars.get(s.charAt(j)) != null) {
        if (instOfChars.get(s.charAt(j)) == null) {
          List<Integer> charIndeces = new ArrayList<>();
          charIndeces.add(j);
          instOfChars.put(s.charAt(j), charIndeces);
        } else {
          instOfChars.get(s.charAt(j)).add(j);
        }
      }
    }
    if (instOfChars.size() != t.length()) {
      return "";
    }
    int minDist = s.length();
    int minDistLeft = 0;
    int minDistRight = 0;
    boolean minimalSolFound = false;
    while (!minimalSolFound) {
      int tmpDist;
      int tmpLeft = s.length();
      int tmpRight = -1;
      char minChar = t.charAt(0);
      for (int l = 0; l < t.length(); l++) {
        char tmpChar = t.charAt(l);
        int tmpListIndex = subStrChars.get(tmpChar);
        int tmpCharIndex = instOfChars.get(tmpChar).get(tmpListIndex);
        if (tmpCharIndex < tmpLeft) {
          tmpLeft = tmpCharIndex;
          minChar = tmpChar;
        }
        if (tmpCharIndex > tmpRight) {
          tmpRight = tmpCharIndex;
        }
      }
      tmpDist = tmpRight - tmpLeft;
      if (tmpDist < minDist) {
        minDist = tmpDist;
        minDistLeft = tmpLeft;
        minDistRight = tmpRight;
      }
      int curMinListIndex = subStrChars.get(minChar);
      if (curMinListIndex == instOfChars.get(minChar).size() - 1) {
        minimalSolFound = true;
      } else {
        subStrChars.put(minChar, subStrChars.get(minChar) + 1);
      }

    }
    return s.substring(minDistLeft, minDistRight + 1);
  }

  //validate a BST
  //https://leetcode.com/submissions/detail/173807875/
  //borrowed idea from jeantimex of leetcode for a more performant solution
  public boolean isValidBST(TreeNode root) {
    return recursValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean recursValidBST(TreeNode node, long min, long max) {
    if (node == null) {
      return true;
    }
    if (node.val <= min || node.val >= max) {
      return false;
    }
    if (node.left != null) {
      if (!recursValidBST(node.left, min, node.val)) {
        return false;
      }
    }
    if (node.right != null) {
      if (!recursValidBST(node.right, node.val, max)) {
        return false;
      }
    }
    return true;
  }

  //given a matrix of int values
  //for any zero, set that row and column to all zeroes
  //https://leetcode.com/problems/set-matrix-zeroes/description/
  //find a constant space solution
  public void setZeroes(int[][] matrix) {
    Map<Integer, Boolean> rows = new HashMap<>();
    Map<Integer, Boolean> cols = new HashMap<>();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          rows.put(j, true);
          cols.put(i, true);
        }
      }
    }
    for (Integer j : rows.keySet()) {
      for (int i = 0; i < matrix.length; i++) {
        if (matrix[i][j] != 0) {
          matrix[i][j] = 0;
        }
      }
    }
    for (Integer i : cols.keySet()) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] != 0) {
          matrix[i][j] = 0;
        }
      }
    }
  }

  //given a number represented as an array of digits
  //add one to the value, and return as resulting array
  //https://leetcode.com/problems/plus-one/description/
  //straightforward, beats 100% of solutions
  public int[] plusOne(int[] digits) {
    boolean carryOne = false;
    for (int i = digits.length - 1; i >= 0; i--) {
      if (digits[i] < 9) {
        digits[i] = digits[i] + 1;
        break;
      } else {
        digits[i] = 0;
        if (i == 0) {
          carryOne = true;
        }
      }
    }
    int[] newResult = new int[]{};
    if (carryOne) {
      newResult = new int[digits.length + 1];
      newResult[0] = 1;
      for (int j = 1; j < newResult.length; j++) {
        newResult[j] = digits[j - 1];
      }
    }
    return carryOne ? newResult : digits;
  }

  //given a matrix of positive ints
  //find a path from top left to bottom right that minimizes the journey cost
  //https://leetcode.com/problems/minimum-path-sum/description/
  //oh look, a dynamic programming problem
  public int minPathSum(int[][] grid) {
    boolean[][] visited = new boolean[grid.length][grid[0].length];
    int[] minPath = new int[]{Integer.MAX_VALUE};
    visited[0][0] = true;
    recursMinPathSum(grid, minPath, visited, 0, 0, 0);
    return minPath[0];
  }

  private void recursMinPathSum(int[][] grid, int[] minPath, boolean[][] visited, int row, int col,
      int curVal) {
    curVal += grid[row][col];
    if (curVal >= minPath[0]) {
      return;
    }
    if (row == grid.length - 1 && col == grid[0].length - 1) {
      if (curVal < minPath[0]) {
        minPath[0] = curVal;
      }
      return;
    }
    if (row + 1 < grid.length && !visited[row + 1][col]
        && curVal + grid[row + 1][col] < minPath[0]) {
      visited[row + 1][col] = true;
      recursMinPathSum(grid, minPath, visited, row + 1, col, curVal);
      visited[row + 1][col] = false;
    }
    if (col + 1 < grid[0].length && !visited[row][col + 1]
        && curVal + grid[row][col + 1] < minPath[0]) {
      visited[row][col + 1] = true;
      recursMinPathSum(grid, minPath, visited, row, col + 1, curVal);
      visited[row][col + 1] = false;
    }
  }

  //let's try again
  //key words from description - You can only move either down or right at any point in time.
  public int downRightMinPathSum(int[][] grid) {
    if (grid.length == 0) {
      return 0;
    } else if (grid.length == 1) {
      int result = 0;
      for (int i = 0; i < grid[0].length; i++) {
        result += grid[0][i];
      }
      return result;
    }
    for (int i = 1; i < grid[0].length; i++) {
      grid[0][i] += grid[0][i - 1];
    }
    for (int j = 1; j < grid.length; j++) {
      for (int k = 0; k < grid[0].length; k++) {
        if (k == 0) {
          grid[j][k] += grid[j - 1][k];
        } else if (j == grid.length - 1 && k == grid[0].length - 1) {
          break;
        } else {
          int smallerVal = grid[j - 1][k] < grid[j][k - 1] ? grid[j - 1][k] : grid[j][k - 1];
          grid[j][k] += smallerVal;
        }
      }
    }
    int leftVal =
        grid[grid.length - 1][grid[0].length - 2] + grid[grid.length - 1][grid[0].length - 1];
    int upVal =
        grid[grid.length - 2][grid[0].length - 1] + grid[grid.length - 1][grid[0].length - 1];
    return leftVal < upVal ? leftVal : upVal;
  }


  //rotate singly-linked list to the right by k
  //https://leetcode.com/problems/rotate-list/description/
  public ListNode rotateListRight(ListNode head, int k) {
    if (head == null || head.next == null) {
      return head;
    }
    List<ListNode> tmpList = new ArrayList<>();
    tmpList.add(head);
    ListNode tmpNode = head;
    while (tmpNode.next != null) {
      tmpList.add(tmpNode.next);
      tmpNode = tmpNode.next;
    }
    k = k % tmpList.size();
    if (k == 0) {
      return head;
    } else {
      tmpNode = tmpList.get(tmpList.size() - k - 1);
      tmpNode.next = null;
      tmpList.get(tmpList.size() - 1).next = tmpList.get(0);
    }
    return tmpList.get(tmpList.size() - k);
  }

  //solution that doesn't require an additional data structure
  //beats 99% of java solutions
  public ListNode altRotateListRight(ListNode head, int k) {
    if (head == null || head.next == null) {
      return head;
    }
    int listSize = 1;
    ListNode tmp = head;
    while (tmp.next != null) {
      tmp = tmp.next;
      listSize++;
    }
    k = k % listSize;
    if (k == 0) {
      return head;
    }
    tmp.next = head;
    tmp = head;
    for (int i = 0; i < listSize - 1 - k; i++) {
      tmp = tmp.next;
    }
    head = tmp.next;
    tmp.next = null;
    return head;
  }

  //given an array of int (as if a histogram), find the max rectangle area
  //that can be created from the filled parts of the histogram
  //https://leetcode.com/problems/largest-rectangle-in-histogram/description/
  //find largest value in histogram, find all values that are above thisValue -1
  //find largest resulting rectangle
  //keep moving target height down until such a value is attained
  //that no rectangle below it could have more unless due to width limitations
  //beats 91% of java solutions
  public int largestRectangleArea(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }
    int[] left = new int[heights.length];
    int[] right = new int[heights.length];
    left[0] = -1;
    right[heights.length - 1] = heights.length;
    int curLeftPtr;
    int curRightPtr;
    for (int i = 1; i < heights.length; i++) {
      curLeftPtr = i - 1;
      while (curLeftPtr >= 0 && heights[curLeftPtr] >= heights[i]) {
        curLeftPtr = left[curLeftPtr];
        if (curLeftPtr == -1) {
          break;
        }
      }
      left[i] = curLeftPtr;
    }
    for (int j = heights.length - 2; j >= 0; j--) {
      curRightPtr = j + 1;
      while (curRightPtr < heights.length && heights[curRightPtr] >= heights[j]) {
        curRightPtr = right[curRightPtr];
        if (curRightPtr == heights.length) {
          break;
        }
      }
      right[j] = curRightPtr;
    }
    int maxRect = 0;
    for (int k = 0; k < heights.length; k++) {
      int tmpRect = heights[k] * (right[k] - left[k] - 1);
      maxRect = tmpRect > maxRect ? tmpRect : maxRect;
    }
    return maxRect;
  }

  //reverse linked list from position l to position m in one pass
  //I'll use ArrayList
  //https://leetcode.com/problems/reverse-linked-list-ii/description/
  public ListNode reverseListBetween(ListNode head, int m, int n) {
    if (head == null || head.next == null) {
      return head;
    }
    List<ListNode> listStorage = new ArrayList<>();
    while (head != null) {
      listStorage.add(head);
      head = head.next;
    }
    for (int i = 0; i <= (n - m) / 2; i++) {
      int tmp = listStorage.get(m - 1 + i).val;
      listStorage.get(m - 1 + i).val = listStorage.get(n - 1 - i).val;
      listStorage.get(n - 1 - i).val = tmp;
    }
    return listStorage.get(0);
  }

  // compute sqrt(x) - x guaranteed to be non-negative integer;
  // https://leetcode.com/problems/sqrtx/
  // standard speed sol'n with essentially a binary search;
  // beats 60% of java solutions
  // to get faster, have to change to bit operations;
  public int mySqrt(int x) {
    double left = 0;
    double right = x;
    double result = 1;
    while (Math.abs(result * result - (double) x) > .000001) {
      result = left + (right - left) / 2.0;
      if (result * result > x) {
        right = result;
      } else {
        left = result;
      }
    }
    if ((int) result < (int) (result + .000001)) {
      result += .000001;
    }
    return (int) result;
  }

  //given an nxm matrix, return the elements in spiral order
  //left->down->right->up
  //https://leetcode.com/problems/spiral-matrix/description/
  //a verbose but functional solution
  //made worse by edge cases
  public List<Integer> spiralOrder(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return new ArrayList<>();
    } else if (matrix.length == 1) {
      List<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
      for (int i = 0; i < matrix[0].length; i++) {
        result.add(matrix[0][i]);
      }
      return result;
    } else if (matrix[0].length == 1) {
      List<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
      for (int i = 0; i < matrix.length; i++) {
        result.add(matrix[i][0]);
      }
      return result;
    }
    int upBound = 0;
    int leftBound = 0;
    int rightBound = matrix[0].length - 1;
    int downBound = matrix.length - 1;
    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
    List<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
    while (leftBound <= rightBound && upBound <= downBound) {
      for (int i = leftBound; i <= rightBound; i++) {
        if (!visited[upBound][i]) {
          result.add(matrix[upBound][i]);
          visited[upBound][i] = true;
        }
      }
      upBound++;
      for (int i = upBound; i <= downBound; i++) {
        if (!visited[i][rightBound]) {
          result.add(matrix[i][rightBound]);
          visited[i][rightBound] = true;
        }
      }
      rightBound--;
      for (int i = rightBound; i >= leftBound; i--) {
        if (!visited[downBound][i]) {
          result.add(matrix[downBound][i]);
          visited[downBound][i] = true;
        }
      }
      downBound--;
      for (int i = downBound; i >= upBound; i--) {
        if (!visited[i][leftBound]) {
          result.add(matrix[i][leftBound]);
          visited[i][leftBound] = true;
        }
      }
      leftBound++;
    }
    return result;
  }

  //given an array where each int that appears in it, appears in it twice, except one
  //find that one that has no duplicate
  //https://leetcode.com/problems/single-number/description/
  //first let's try the obvious hashmap solution
  public int singleNumber(int[] nums) {
    Map<Integer, Boolean> unpairedVals = new HashMap<>(nums.length / 2 + 1);
    for (int i = 0; i < nums.length; i++) {
      if (!unpairedVals.containsKey(nums[i])) {
        unpairedVals.put(nums[i], true);
      } else {
        unpairedVals.remove(nums[i]);
      }
    }
    return unpairedVals.keySet().toArray(new Integer[1])[0];
  }

  //and here's the super-clever bit operation solution
  //beats 62% of java solutions
  public int altSingleNumber(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      nums[0] ^= nums[i];
    }
    return nums[0];
  }

  //same problem as above, but every value except one appears thrice
  //https://leetcode.com/problems/single-number-ii/description/
  public int singleNumberTwo(int[] nums) {
    Map<Integer, Integer> unpairedVals = new HashMap<>(nums.length / 3 + 1);
    for (int i = 0; i < nums.length; i++) {
      if (!unpairedVals.containsKey(nums[i])) {
        unpairedVals.put(nums[i], 1);
      } else if (unpairedVals.get(nums[i]) == 1) {
        unpairedVals.put(nums[i], 2);
      } else if (unpairedVals.get(nums[i]) == 2) {
        unpairedVals.remove(nums[i]);
      }
    }
    return unpairedVals.keySet().toArray(new Integer[1])[0];
  }

  //given a binary tree of digits (0-9)
  //with each root->lead path representing a value
  //return the sum of these values
  //https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
  //beats 100% of java submissions
  public int sumNumbers(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int pathVal = 0;
    int[] sum = new int[]{0};
    recursSumNumbers(root, pathVal, sum);
    return sum[0];
  }

  private void recursSumNumbers(TreeNode node, int pathVal, int[] sum) {
    pathVal *= 10;
    pathVal += node.val;
    if (node.left == null && node.right == null) {
      sum[0] += pathVal;
      return;
    }
    if (node.left != null) {
      recursSumNumbers(node.left, pathVal, sum);
    }
    if (node.right != null) {
      recursSumNumbers(node.right, pathVal, sum);
    }
  }

  //breadth-first traversal, with every second level reversed
  //https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
  //beats 96% of java submissions
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<List<Integer>> results = new ArrayList<>();
    boolean leftToRight = true;
    List<TreeNode> curLevel = new ArrayList<>();
    curLevel.add(root);
    while (!curLevel.isEmpty()) {
      List<TreeNode> newLevel = new ArrayList<>();
      for (int i = 0; i < curLevel.size(); i++) {
        if (curLevel.get(i).left != null) {
          newLevel.add(curLevel.get(i).left);
        }
        if (curLevel.get(i).right != null) {
          newLevel.add(curLevel.get(i).right);
        }
      }
      List<Integer> tmp = new ArrayList<>();
      if (leftToRight) {
        for (int j = 0; j < curLevel.size(); j++) {
          tmp.add(curLevel.get(j).val);
        }
      } else {
        for (int j = curLevel.size() - 1; j >= 0; j--) {
          tmp.add(curLevel.get(j).val);
        }
      }
      results.add(tmp);
      leftToRight = !leftToRight;
      curLevel = newLevel;
    }
    return results;
  }

  //given a matrix consisting of the chars 'X' and 'O'
  //flip all 'O' chars if they are surrounded by 'X'
  //i.e. they don't have an exit path of 'O' chars to matrix edge
  //https://leetcode.com/problems/surrounded-regions/description/
  //standard-issue solution, beats 75% of java submissions
  public void captureOVals(char[][] board) {
    if (board == null || board.length == 0 || board[0].length == 0
        || board.length == 1 || board[0].length == 1) {
      return;
    }
    boolean[][] safeOChars = new boolean[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
      if (board[i][0] == 'O') {
        safeOChars[i][0] = true;
      }
      if (board[i][board[0].length - 1] == 'O') {
        safeOChars[i][board[0].length - 1] = true;
      }
    }
    for (int i = 0; i < board[0].length; i++) {
      if (board[0][i] == 'O') {
        safeOChars[0][i] = true;
      }
      if (board[board.length - 1][i] == 'O') {
        safeOChars[board.length - 1][i] = true;
      }
    }

    for (int i = 0; i < board.length; i++) {
      if (safeOChars[i][0]) {
        recursMarkSafe(board, safeOChars, i, 0);
      }
      if (safeOChars[i][board[0].length - 1]) {
        recursMarkSafe(board, safeOChars, i, board[0].length - 1);
      }
    }
    for (int i = 0; i < board[0].length; i++) {
      if (safeOChars[0][i]) {
        recursMarkSafe(board, safeOChars, 0, i);
      }
      if (safeOChars[board.length - 1][i]) {
        recursMarkSafe(board, safeOChars, board.length - 1, i);
      }
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == 'O' && !safeOChars[i][j]) {
          board[i][j] = 'X';
        }
      }
    }
  }

  private void recursMarkSafe(char[][] board, boolean[][] safeOChars, int row, int col) {
    if (row > 0 && board[row - 1][col] == 'O' && !safeOChars[row - 1][col]) {
      safeOChars[row - 1][col] = true;
      recursMarkSafe(board, safeOChars, row - 1, col);
    }
    if (col > 0 && board[row][col - 1] == 'O' && !safeOChars[row][col - 1]) {
      safeOChars[row][col - 1] = true;
      recursMarkSafe(board, safeOChars, row, col - 1);
    }
    if (row < board.length - 1 && board[row + 1][col] == 'O' && !safeOChars[row + 1][col]) {
      safeOChars[row + 1][col] = true;
      recursMarkSafe(board, safeOChars, row + 1, col);
    }
    if (col < board[0].length - 1 && board[row][col + 1] == 'O' && !safeOChars[row][col + 1]) {
      safeOChars[row][col + 1] = true;
      recursMarkSafe(board, safeOChars, row, col + 1);
    }
  }


  //given a string and a series of potential substrings
  //see if the string can be created from those substrings
  //a substring may be reused
  //https://leetcode.com/problems/word-break/description/
  //reworked to skip looking for a solution to an already solved substring
  //beats 88% of java submissions
  public boolean wordBreak(String s, List<String> wordDict) {
    Map<String, Boolean> alreadyDone = new HashMap<>();
    return recursWordBreak(wordDict, s, alreadyDone);
  }

  private boolean recursWordBreak(List<String> wordDict, String s,
      Map<String, Boolean> alreadyDone) {
    if (s.length() == 0) {
      return true;
    }
    if (alreadyDone.containsKey(s)) {
      return false;
    }
    alreadyDone.put(s, true);
    for (String word : wordDict) {
      if (s.startsWith(word) && recursWordBreak(wordDict, s.substring(word.length()),
          alreadyDone)) {
        return true;
      }
    }
    return false;
  }


  //given five values separately,
  //with a guarantee that there is a value that occurs more than others
  //find that value (the mode) without using any structures (not even arrays)
  public int findMode(int a, int b, int c, int d, int e) {
    TupleNode head = new TupleNode(a, 1);
    buildModeList(b, head);
    buildModeList(c, head);
    buildModeList(d, head);
    buildModeList(e, head);
    int mode = 0;
    int modeOccurrence = 0;
    TupleNode tmp = head;
    while (tmp != null) {
      if (tmp.occurence > modeOccurrence) {
        mode = tmp.val;
        modeOccurrence = tmp.occurence;
      }
      tmp = tmp.next;
    }
    return mode;
  }

  public void buildModeList(int val, TupleNode head) {
    TupleNode tmp = head;
    boolean valOccurred = false;
    boolean lastVal = false;
    while (!lastVal) {
      if (tmp.val == val) {
        tmp.occurence += 1;
        valOccurred = true;
      }
      if (tmp.next != null) {
        tmp = tmp.next;
      } else {
        lastVal = true;
      }
    }
    if (!valOccurred) {
      tmp.next = new TupleNode(val, 1);
    }
  }

  //convert sorted array of unique integers into a balanced binary tree
  //https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
  //bog-standard recursive solution
  public TreeNode sortedArrayToBST(int[] nums) {
    return recursSortArrayToBST(nums, 0, nums.length - 1);
  }

  private TreeNode recursSortArrayToBST(int[] nums, int leftIncl, int rightIncl) {
    if (leftIncl > rightIncl) {
      return null;
    } else if (leftIncl == rightIncl) {
      return new TreeNode(nums[leftIncl]);
    }
    int mid = leftIncl + (rightIncl - leftIncl) / 2;
    TreeNode result = new TreeNode(nums[mid]);
    result.left = recursSortArrayToBST(nums, leftIncl, mid - 1);
    result.right = recursSortArrayToBST(nums, mid + 1, rightIncl);
    return result;
  }

  //a wizard gives you the price of a certain stock for future x days
  //figure out the optimal price to buy that stock, and then sell it
  //and return the difference per stock
  //if no optimal time to buy and sell exists, return 0
  //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
  public int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }
    int[] minThusFar = new int[prices.length];
    int[] maxThusFar = new int[prices.length];
    int curMin = Integer.MAX_VALUE;
    int curMax = Integer.MIN_VALUE;
    for (int i = 0; i < prices.length; i++) {
      if (prices[i] < curMin) {
        curMin = prices[i];
      }
      minThusFar[i] = curMin;
      if (prices[prices.length - 1 - i] > curMax) {
        curMax = prices[prices.length - 1 - i];
      }
      maxThusFar[prices.length - 1 - i] = curMax;
    }
    int maxDiff = 0;
    for (int i = 0; i < prices.length; i++) {
      int curDiff = maxThusFar[i] - minThusFar[i];
      maxDiff = curDiff > maxDiff ? curDiff : maxDiff;
    }
    return maxDiff;
  }

  //given a perfect binary tree
  //fill in another pointer, next, that points along the level rather than down
  //https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
  //standard BFS solution
  public void connectTreeLevels(TreeLinkNode root) {
    if (root == null) {
      return;
    }
    Queue<TreeLinkNode> level = new LinkedList<>();
    level.add(root);
    while (!level.isEmpty()) {
      TreeLinkNode tmpTLN;
      Queue<TreeLinkNode> nextLevel = new LinkedList<>();
      while (level.peek() != null) {
        tmpTLN = level.poll();
        if (tmpTLN.left != null) {
          nextLevel.offer(tmpTLN.left);
        }
        if (tmpTLN.right != null) {
          nextLevel.offer(tmpTLN.right);
        }
        tmpTLN.next = level.peek();
      }
      level = nextLevel;
    }
  }

  //same problem with recursion
  //beats 100% of java solutions
  public void recursConnectTreeLevels(TreeLinkNode root) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      root.left.next = root.right;
    }
    if (root.next != null && root.right != null) {
      root.right.next = root.next.left;
    }
    recursConnectTreeLevels(root.left);
    recursConnectTreeLevels(root.right);
  }

  //given an unsorted array
  //find the longest sequence (n, n+1, n+2...) and return its length
  //in linear time (so no sorting)
  //https://leetcode.com/problems/longest-consecutive-sequence/description/
  //average solution using set rather than map
  public int longestConsecutive(int[] nums) {
    if (nums.length == 0) {
      return 0;
    } else if (nums.length == 1) {
      return 1;
    }
    Set<Integer> values = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      values.add(nums[i]);
    }
    int maxRun = 0;
    for (Integer s : values) {
      if (!values.contains(s - 1)) {
        int tmpRun = 1;
        int incrementer = 1;
        while (values.contains(s + incrementer)) {
          tmpRun++;
          incrementer++;
        }
        maxRun = tmpRun > maxRun ? tmpRun : maxRun;
      }
    }
    return maxRun;
  }

  //generate pascal's triangle of height numRows
  //https://leetcode.com/problems/pascals-triangle/
  //standard solution, beats 100% of java submissions
  public List<List<Integer>> generatePascalTriangle(int numRows) {
    if (numRows <= 0) {
      return new ArrayList<>();
    }
    List<List<Integer>> results = new ArrayList<>(numRows);
    List<Integer> curRow = new ArrayList<>(1);
    curRow.add(1);
    results.add(curRow);
    while (results.size() != numRows) {
      List<Integer> tmpRow = new ArrayList<>(curRow.size() + 1);
      tmpRow.add(1);
      for (int i = 0; i < curRow.size() - 1; i++) {
        tmpRow.add(curRow.get(i) + curRow.get(i + 1));
      }
      tmpRow.add(1);
      results.add(tmpRow);
      curRow = tmpRow;
    }
    return results;
  }

  //return the kth row of pascal's triangle with k>0 and k<=33
  //using only k extra space
  //https://leetcode.com/problems/pascals-triangle-ii/description/
  //pretty standard solution, beats 90% of java submissions
  public List<Integer> getPascalRow(int rowIndex) {
    if (rowIndex <= 0) {
      return new ArrayList<>();
    }
    List<Integer> row = new ArrayList<>(rowIndex);
    row.add(0, 1);
    int tmp;
    int tmpSum;
    for (int i = 1; i < rowIndex; i++) {
      tmp = 1;
      for (int j = 1; j < i; j++) {
        tmpSum = tmp + row.get(j);
        tmp = row.get(j);
        row.remove(j);
        row.add(j, tmpSum);
      }
      row.add(1);
    }
    return row;
  }

  //given a linked list, determine if it contains a cycle
  //without extra space
  //https://leetcode.com/problems/linked-list-cycle/description/
  //constant space, but only beats 25% of solutions
  public boolean listHasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    boolean stepDelay = true;
    ListNode tmpFast = head;
    ListNode tmpSlow = head;
    while (tmpFast.next != null) {
      if (stepDelay) {
        tmpFast = tmpFast.next;
        stepDelay = !stepDelay;
      } else {
        tmpFast = tmpFast.next;
        tmpSlow = tmpSlow.next;
        stepDelay = !stepDelay;
      }
      if (tmpFast == tmpSlow) {
        return true;
      }
    }
    return false;
  }

  //given a binary tree, turn it into a linked list using TreeNode.right insead of LinkNode.next;
  //note that the tree should be read as a preorder traversal
  //https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
  //first a less-than-perfect solution using an arraylist
  public void flatten(TreeNode root) {
    if (root == null) {
      return;
    }
    List<TreeNode> sorted = new ArrayList<>();
    recursFlatten(sorted, root);
    TreeNode tmp;
    for (int i = 0; i < sorted.size() - 1; i++) {
      tmp = sorted.get(i);
      tmp.left = null;
      tmp.right = sorted.get(i + 1);
    }
  }

  private void recursFlatten(List<TreeNode> sorted, TreeNode root) {
    sorted.add(root);
    if (root.left != null) {
      recursFlatten(sorted, root.left);
    }
    if (root.right != null) {
      recursFlatten(sorted, root.right);
    }
  }

  //slightly better DFS solution
  public void altFlatten(TreeNode root) {
    if (root == null) {
      return;
    }
    recursAltFlatten(root);
  }

  private TreeNode[] recursAltFlatten(TreeNode root) {
    TreeNode[] firstAndLast = new TreeNode[2];

    TreeNode tmpRight = root.right;
    TreeNode tmpLeft = root.left;
    root.right = null;
    root.left = null;

    firstAndLast[0] = root;
    firstAndLast[1] = root;

    if (tmpLeft != null) {
      TreeNode[] leftFirstAndLast = recursAltFlatten(tmpLeft);
      firstAndLast[1].right = leftFirstAndLast[0];
      firstAndLast[1] = leftFirstAndLast[1];
    }
    if (tmpRight != null) {
      TreeNode[] rightFirstAndLast = recursAltFlatten(tmpRight);
      firstAndLast[1].right = rightFirstAndLast[0];
      firstAndLast[1] = rightFirstAndLast[1];
    }
    return firstAndLast;
  }

  //given a linked list, return the element at which a cycle begins
  //if no cycle, return null
  //https://leetcode.com/problems/linked-list-cycle-ii/description/
  //Technically this is an O(N) solution, but it has some overhead
  //and takes up linear space
  public ListNode detectCycle(ListNode head) {
    Set<ListNode> nodes = new HashSet<>();
    while (head != null) {
      if (!nodes.contains(head)) {
        nodes.add(head);
      } else {
        return head;
      }
      head = head.next;
    }
    return null;
  }

  //given dna in string format consisting of chars g,a,t, and c
  //return all repeating 10-char substrings
  //https://leetcode.com/problems/repeated-dna-sequences/description/
  //standard hashmap sol'n
  public List<String> findRepeatedDnaSequences(String fullSeq) {
    if (fullSeq.length() < 10) {
      return new ArrayList<>();
    }
    Map<String, Integer> sequences = new HashMap<>();
    List<String> repeats = new ArrayList<>();
    for (int i = 0; i < fullSeq.length() - 9; i++) {
      String curSubStr = fullSeq.substring(i, i + 10);
      if (sequences.containsKey(curSubStr)) {
        if (sequences.get(curSubStr) == 0) {
          repeats.add(curSubStr);
          sequences.put(curSubStr, 1);
        }
      } else {
        sequences.put(curSubStr, 0);
      }
    }
    return repeats;
  }


  //given an array wherein some value constitutes more than half the elements
  //find this element. Note that we're guaranteed the presence of a majority element
  //first the naive linear solution
  public int majorityElement(int[] nums) {
    if (nums.length == 0) {
      return -1;
    } else if (nums.length == 1) {
      return nums[0];
    }
    Map<Integer, Integer> occurenceCounts = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (occurenceCounts.containsKey(nums[i])) {
        int curCount = occurenceCounts.get(nums[i]);
        curCount++;
        if (curCount > nums.length / 2) {
          return nums[i];
        }
        occurenceCounts.put(nums[i], curCount);
      } else {
        occurenceCounts.put(nums[i], 1);
      }
    }
    return -1;
  }

  //using the fact that only one of the elements can constitute more than
  //n.length/2+1 elements of the array
  //beats 67% of java submissions
  public int altMajorityElement(int[] nums) {
    if (nums.length == 0) {
      return -1;
    } else if (nums.length < 3) {
      return nums[0];
    }
    int curTopVal = nums[0];
    int topCount = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == curTopVal) {
        topCount++;
      } else {
        if (topCount == 0) {
          curTopVal = nums[i];
          topCount = 1;
        } else {
          topCount--;
        }
      }
    }
    return curTopVal;
  }

  //two-sum with sorted array
  //guaranteed presence of solution, can't reuse same index
  //https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
  //standard O(Nlog(N)) solution
  public int[] twoSumSorted(int[] numbers, int target) {
    if (numbers.length == 2) {
      return new int[]{1, 2};
    }
    for (int i = 0; i < numbers.length; i++) {
      int tryFind = binSearchForTwoSum(numbers, target - numbers[i], i + 1, numbers.length - 1);
      if (tryFind != -1) {
        return new int[]{i + 1, tryFind + 1};
      }
    }
    return new int[]{-1, -1};
  }

  private int binSearchForTwoSum(int[] numbers, int target, int left, int right) {
    if (left == right) {
      if (numbers[left] == target) {
        return left;
      } else {
        return -1;
      }
    } else if (left > right) {
      return -1;
    }

    if (left > right) {
      return -1;
    }
    int mid = left + (right - left) / 2;
    if (numbers[mid] == target) {
      return mid;
    } else if (numbers[mid] > target) {
      return binSearchForTwoSum(numbers, target, left, mid - 1);
    } else {
      return binSearchForTwoSum(numbers, target, mid + 1, right);
    }
  }

  //let's try the same thing with two pointers and no binary search
  //note that we are guaranteed a unique solution
  //standard linear time solution
  //beats 67% of java submissions
  public int[] altTwoSumSorted(int[] numbers, int target) {
    int leftPtr = 0;
    int rightPtr = numbers.length - 1;
    while (leftPtr != rightPtr) {
      int curSum = numbers[leftPtr] + numbers[rightPtr];
      if (curSum == target) {
        return new int[]{leftPtr + 1, rightPtr + 1};
      } else if (curSum > target) {
        rightPtr--;
      } else {
        leftPtr++;
      }
    }
    return new int[]{-1, -1};
  }

  //given a positive number, add the digits of that number
  //then if the given numbers is more than 1 digit
  //repeat
  //until the result is a 1 digit value
  //basic solution
  public int addDigits(int num) {
    if (num < 10) {
      return num;
    }
    while (num > 9) {
      int tmp = 0;
      while (num > 0) {
        tmp += num % 10;
        num /= 10;
      }
      num = tmp;
    }
    return num;
  }

  //given a binary search tree and two nodes known to be present in it
  //find the lowest common ancestor
  //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
  //bit of a slow solution, but it works
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }
    if (root.val == p.val || root.val == q.val) {
      return root;
    }
    if (root.right == null) {
      return lowestCommonAncestor(root.left, p, q);
    } else if (root.left == null) {
      return lowestCommonAncestor(root.right, p, q);
    } else {
      boolean pRight = findValInBST(root.right, p);
      boolean qLeft = findValInBST(root.left, q);
      if (pRight && qLeft || !pRight && !qLeft) {
        return root;
      } else if (pRight && !qLeft) {
        return lowestCommonAncestor(root.right, p, q);
      } else {
        return lowestCommonAncestor(root.left, p, q);
      }
    }
  }

  private boolean findValInBST(TreeNode root, TreeNode tn) {
    if (root.val == tn.val) {
      return true;
    } else if (root.val > tn.val) {
      if (root.left == null) {
        return false;
      } else {
        return findValInBST(root.left, tn);
      }
    } else {
      if (root.right == null) {
        return false;
      } else {
        return findValInBST(root.right, tn);
      }
    }
  }

  List<Contact> mergeDuplicateContacts(List<Contact> contacts) {
    if (contacts == null) {
      return new ArrayList<>();
    }
    if (contacts.size() < 2) {
      return contacts;
    }

    Map<Integer, Set<String>> emailSets = new HashMap<>();
    for (int i = 0; i < contacts.size(); i++) {
      Set<String> emails = new HashSet<>(contacts.get(i).emails);
      emailSets.put(i, emails);
    }
    List<Integer> mergeIndeces = new ArrayList<>();
    Map<Integer, Set<String>> setsToMerge = new HashMap<>();
    int curMergeStartKey = 0;

    List<Contact> results = new ArrayList<>();
    while (!emailSets.isEmpty()) {
      Set<String> curStartSet = emailSets.get(curMergeStartKey);
      emailSets.remove(curMergeStartKey);
      for (String email : curStartSet) {
        for (Integer key : emailSets.keySet()) {
          if (emailSets.get(key).contains(email)) {
            mergeIndeces.add(key);
          }
        }
        for (Integer key : mergeIndeces) {
          setsToMerge.put(key, emailSets.get(key));
          emailSets.remove(key);
        }
        mergeIndeces.clear();
      }
      for (Integer key : setsToMerge.keySet()) {
        for (String s : setsToMerge.get(key)) {
          if (!curStartSet.contains(s)) {
            curStartSet.add(s);
          }
        }
      }
      results.add(new Contact(contacts.get(curMergeStartKey).name, new ArrayList<>(curStartSet)));
      setsToMerge.clear();
      for (int i = 1; i < contacts.size(); i++) {
        if (emailSets.containsKey(i)) {
          curMergeStartKey = i;
          break;
        }
      }
    }
    return results;
  }

  //given a list of rooms, each potentially containing keys to other rooms
  //with room 0 being unlocked
  //check if it's possible to visit all rooms
  //https://leetcode.com/problems/keys-and-rooms/
  //standard issue solution, beats roughly 50% of java submissions
  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    if (rooms == null || rooms.size() < 2) {
      return true;
    }
    boolean[] unlockedRooms = new boolean[rooms.size()];
    boolean[] visitedRooms = new boolean[rooms.size()];
    unlockedRooms[0] = true;
    int curUnlockedRooms = 1;
    int curUnvisitedRooms = rooms.size();
    List<Integer> roomsToVisit = new ArrayList<>();
    while (curUnvisitedRooms != 0 && curUnlockedRooms != rooms.size() - curUnvisitedRooms) {
      for (int i = 0; i < unlockedRooms.length; i++) {
        if (unlockedRooms[i] && !visitedRooms[i]) {
          roomsToVisit.add(i);
          visitedRooms[i] = true;
          curUnvisitedRooms--;
        }
      }
      for (int i = 0; i < roomsToVisit.size(); i++) {
        List<Integer> curRoom = rooms.get(roomsToVisit.get(i));
        for (int j = 0; j < curRoom.size(); j++) {
          if (!visitedRooms[curRoom.get(j)]) {
            unlockedRooms[curRoom.get(j)] = true;
            curUnlockedRooms++;
          }
        }
      }
      roomsToVisit.clear();
    }
    return curUnvisitedRooms == 0;
  }

  //given an array with a certain number of zeroes
  //move the zeroes to the end without disrupting the order of other elements in-place
  //https://leetcode.com/problems/move-zeroes/
  //beats 100% of java submissions
  //admittedly a trivial problem
  public void moveZeroes(int[] nums) {
    if (nums.length < 2) {
      return;
    }
    int replaceIndex = -1;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0 && replaceIndex == -1) {
        replaceIndex = i;
        continue;
      } else if (nums[i] != 0 && replaceIndex != -1) {
        nums[replaceIndex] = nums[i];
        nums[i] = 0;
        replaceIndex++;
      }
    }
  }

  //given two arrays, find all values present in both
  //https://leetcode.com/problems/intersection-of-two-arrays/
  //simple solution using a map, beats 91% of java submissions
  public int[] intersection(int[] nums1, int[] nums2) {
    Map<Integer, Boolean> inBoth = new HashMap<>();
    for (int i = 0; i < nums1.length; i++) {
      if (!inBoth.containsKey(nums1[i]))
        inBoth.put(nums1[i], false);
    }
    int numOfIntersections = 0;
    for (int j = 0; j < nums2.length; j++) {
      if (inBoth.containsKey(nums2[j]) && !inBoth.get(nums2[j])) {
        inBoth.put(nums2[j], true);
        numOfIntersections++;
      }
    }
    int[] result = new int[numOfIntersections];
    int index = 0;
    for (Integer key : inBoth.keySet()) {
      if (inBoth.get(key)) {
        result[index] = key;
        index++;
      }
    }
    return result;
  }


  //given a series of equations and result values (a/b=2.0) as {{"a", "b"}}, {2.0}
  //return the results of new division queries if possible
  //approach tolerates unconnected graphs
  //beats 80% of java submissions
  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    Map<String, GraphNode> allVars = new HashMap<>();
    for (int i = 0; i < equations.length; i++) {
      if (!allVars.containsKey(equations[i][0])) {
        GraphNode curNode = new GraphNode(equations[i][0]);
        curNode.connections.put(equations[i][1], values[i]);
        allVars.put(equations[i][0], curNode);
      } else {
        allVars.get(equations[i][0]).connections.put(equations[i][1], values[i]);
      }
      if (!allVars.containsKey(equations[i][1])) {
        GraphNode curNode = new GraphNode(equations[i][1]);
        curNode.connections.put(equations[i][0], 1.0 / values[i]);
        allVars.put(equations[i][1], curNode);
      } else {
        allVars.get(equations[i][1]).connections.put(equations[i][0], 1.0 / values[i]);
      }
    }

    double[] results = new double[queries.length];
    for (int i = 0; i < queries.length; i++) {
      if (!allVars.containsKey(queries[i][0]) || !allVars.containsKey(queries[i][1])) {
        results[i] = -1;
      } else {
        String startNode = queries[i][0];
        String endNode = queries[i][1];
        if (startNode.equals(endNode)) {
          results[i] = 1.0;
        } else {
          Set<String> checkedGraphs = new HashSet<>();
          checkedGraphs.add(startNode);
          results[i] = recursGraphSearch(allVars, allVars.get(startNode), 1.0, endNode,
              checkedGraphs);
        }
      }
    }
    return results;
  }

  private double recursGraphSearch(Map<String, GraphNode> nodeMap, GraphNode curNode, double val,
      String target, Set<String> checkedGraphs) {
    double result = -1.0;
    if (curNode.connections.containsKey(target))
      return val * curNode.connections.get(target);
    else {
      for (String s : curNode.connections.keySet()) {
        if (!checkedGraphs.contains(s)) {
          checkedGraphs.add(s);
          result = recursGraphSearch(nodeMap, nodeMap.get(s), val * curNode.connections.get(s),
              target, checkedGraphs);
          if (result != -1.0)
            return result;
        }
      }
    }
    return result;
  }

  //same problem as above, but using maps
  //significantly slower, but the test cases are a bit limited
  //this is a valid way to do it, and for larger data sets seems like it'd be better than DFS
  public double[] unconnectedCalcEquation(String[][] equations, double[] values,
      String[][] queries) {
    Map<String, Map<String, Double>> dividendMap = new HashMap<>();
    Set<String> uncheckedDividends = new HashSet<>();
    for (int i = 0; i < equations.length; i++) {
      if (dividendMap.containsKey(equations[i][0])) {
        dividendMap.get(equations[i][0]).put(equations[i][1], values[i]);
      } else {
        uncheckedDividends.add(equations[i][0]);
        Map<String, Double> curDivisor = new HashMap<>();
        curDivisor.put(equations[i][1], values[i]);
        dividendMap.put(equations[i][0], curDivisor);
      }
      if (dividendMap.containsKey(equations[i][1])) {
        dividendMap.get(equations[i][1]).put(equations[i][0], 1.0 / values[i]);
      } else {
        uncheckedDividends.add(equations[i][1]);
        Map<String, Double> curDivisor = new HashMap<>();
        curDivisor.put(equations[i][0], 1.0 / values[i]);
        dividendMap.put(equations[i][1], curDivisor);
      }

    }

    List<Map<String, Double>> quotientMapList = new ArrayList<>();
    List<Set> valueGroupings = new ArrayList<>();

    while (!uncheckedDividends.isEmpty()) {
      String initialDividend = "";
      for (String s : uncheckedDividends) {
        initialDividend = s;
        break;
      }
      uncheckedDividends.remove(initialDividend);

      Map<String, Double> quotientMap = new HashMap<>();
      Set<String> curVals = new HashSet<>();
      Set<String> checkInNextLoop = new HashSet<>();
      curVals.add(initialDividend);
      checkInNextLoop.add(initialDividend);

      while (!checkInNextLoop.isEmpty()) {
        List<String> nextCheckInNextLoop = new LinkedList<>();
        for (String curDividend : checkInNextLoop) {
          if (!quotientMap.containsKey(curDividend)) {
            quotientMap.put(curDividend, 1.0);

          }
          double quotientFromDividend = quotientMap.get(curDividend);
          if (dividendMap.containsKey(curDividend)) {
            for (String divisor : dividendMap.get(curDividend).keySet()) {
              if (!quotientMap.containsKey(divisor)) {
                nextCheckInNextLoop.add(divisor);
                quotientMap
                    .put(divisor, quotientFromDividend * dividendMap.get(curDividend).get(divisor));
                uncheckedDividends.remove(divisor);
                curVals.add(divisor);
              }
            }
          }
        }
        checkInNextLoop = new HashSet<>(nextCheckInNextLoop);
      }

      valueGroupings.add(curVals);
      quotientMapList.add(quotientMap);
    }

    double[] results = new double[queries.length];
    for (int i = 0; i < results.length; i++) {
      int soughtSet = -1;
      for (int j = 0; j < valueGroupings.size(); j++) {
        if (valueGroupings.get(j).contains(queries[i][0]) && valueGroupings.get(j)
            .contains(queries[i][1])) {
          soughtSet = j;
        }
      }
      if (soughtSet == -1)
        results[i] = -1;
      else {
        Map<String, Double> quotientMap = quotientMapList.get(soughtSet);
        results[i] = 1.0 / quotientMap.get(queries[i][0]) * quotientMap.get(queries[i][1]);
      }
    }
    return results;
  }

  //given a number, return true if it's square root is a whole number
  //https://leetcode.com/problems/valid-perfect-square/description/
  //trick is the same as get square root of num
  //beats 100% of java submissions
  public boolean isPerfectSquare(int num) {
    double tolerableDoubleError = 0.00001;
    if (num == 1 || num == 0)
      return true;
    double x = num / 2.0;
    double range = num - x;
    while (Math.abs(num - x * x) > tolerableDoubleError) {
      if (x * x > num)
        x = x - range / 2;
      else
        x = x + range / 2;
      range /= 2;
    }
    int intVersOfX = (int) (x + tolerableDoubleError);
    return Math.abs(x - intVersOfX) < tolerableDoubleError;
  }

  //given a list of N lightbulbs, turn all on, then every 2nd on,
  //then toggle every third one... then toggle every nth
  //then return the number of bulbs turned on
  //curious if there's a super-clever binary operation solution
  //https://leetcode.com/problems/bulb-switcher/description/
  //let's try the obvious solution first
  public int bulbSwitch(int n) {
    if (n < 0)
      return 0;
    boolean[] bulbs = new boolean[n];
    for (int i = 1; i <= n; i++) {
      for (int j = i - 1; j < bulbs.length; j += i) {
        bulbs[j] = !bulbs[j];
      }
    }
    int result = 0;
    for (int k = 0; k < bulbs.length; k++) {
      if (bulbs[k])
        result++;
    }
    return result;
  }

  //it's a math problem, apparently
  //source - every single suggestion on leetcode
  public int mathBulbSwitch(int n) {
    return (int) Math.sqrt(n);
  }


  //given a value, return if it's a power of three (x*3)
  //no recursion, no loops
  //trivial with loops
  //brilliant solution from ElementNotFound of leetcode
  public boolean isPowerOfThree(int n) {
    return Integer.toString(n, 3).matches("10*");
  }

  //reverse the vowels of a string
  //https://leetcode.com/problems/reverse-vowels-of-a-string/description/
  //getting rid of set means it now beats 95% of java solutions
  public String reverseVowels(String s) {
    boolean[] vowels = new boolean[256];
    int[] vowelIndexes = new int[]{65, 69, 73, 79, 85, 97, 101, 105, 111, 117};
    for (int i = 0; i < vowelIndexes.length; i++) {
      vowels[vowelIndexes[i]] = true;
    }
    char[] strAsArr = s.toCharArray();
    int startPtr = 0;
    int endPtr = strAsArr.length - 1;
    while (startPtr < endPtr) {
      if (vowels[(strAsArr[startPtr])] && vowels[(strAsArr[endPtr])]) {
        char tmp = strAsArr[startPtr];
        strAsArr[startPtr] = strAsArr[endPtr];
        strAsArr[endPtr] = tmp;
        startPtr++;
        endPtr--;
      } else if (vowels[(strAsArr[startPtr])]) {
        endPtr--;
      } else if (vowels[(strAsArr[endPtr])]) {
        startPtr++;
      } else {
        startPtr++;
        endPtr--;
      }
    }
    return new String(strAsArr);
  }

  //return the sum of left leaves in a tree
  //This is pretty trivial, admittedly
  //https://leetcode.com/problems/sum-of-left-leaves/description/
  //bfs solution, beats 78% of java submissions
  public int sumOfLeftLeaves(TreeNode root) {
    if (root == null)
      return 0;
    Queue<TreeNode> treeLevels = new LinkedList<>();
    treeLevels.offer(root);
    int sum = 0;
    while (!treeLevels.isEmpty()) {
      int curLevelLen = treeLevels.size();
      for (int i = 0; i < curLevelLen; i++) {
        TreeNode tmp = treeLevels.poll();
        if (tmp.left != null) {
          if (tmp.left.left == null && tmp.left.right == null)
            sum += tmp.left.val;
          else
            treeLevels.offer(tmp.left);
        }
        if (tmp.right != null)
          treeLevels.offer(tmp.right);
      }
    }
    return sum;
  }

  //same deal, beats same 78% of java submissions
  public int altSumOfLeftLeaves(TreeNode root) {
    if (root == null)
      return 0;
    int[] sum = new int[]{0};
    recurSumOfLeftLeaves(root, sum, false);
    return sum[0];
  }

  private void recurSumOfLeftLeaves(TreeNode curNode, int[] sum, boolean left) {
    if (left && curNode.left == null && curNode.right == null)
      sum[0] += curNode.val;
    if (curNode.left != null)
      recurSumOfLeftLeaves(curNode.left, sum, true);
    if (curNode.right != null)
      recurSumOfLeftLeaves(curNode.right, sum, false);
  }

  //given a matrix of k sorted arrays, find the kth smallest elemet
  //https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
  //basic solution requiring k*matrix row number operations on average
  //only beats around 27% of java submissions
  public int kthSmallest(int[][] matrix, int k) {
    if (matrix == null || k < 1 || matrix.length * matrix[0].length < k)
      return 0;
    int[] rowPtrs = new int[matrix.length];
    int lowVal = 0;
    int lowValIndex = 0;
    for (int i = 0; i < k; i++) {
      lowVal = Integer.MAX_VALUE;
      for (int j = 0; j < rowPtrs.length; j++) {
        if (rowPtrs[j] < matrix.length && matrix[j][rowPtrs[j]] < lowVal) {
          lowVal = matrix[j][rowPtrs[j]];
          lowValIndex = j;
        }
      }
      rowPtrs[lowValIndex]++;
    }
    return lowVal;
  }

  //alternative solution using a minHeap
  //also somehow only beats 27% of java submissions
  //despite having klog(k) complexity
  public int altKthSmallest(int[][] matrix, int k) {
    if (matrix == null || k < 1 || matrix.length * matrix[0].length < k)
      return 0;
    Queue<TupleWithIndex> minHeap = new PriorityQueue<>(new Comparator<TupleWithIndex>() {
      @Override
      public int compare(TupleWithIndex o1, TupleWithIndex o2) {
        return o1.value - o2.value;
      }
    });
    int[] rowPtrs = new int[matrix.length];

    for (int i = 0; i < rowPtrs.length; i++) {
      minHeap.offer(new TupleWithIndex(matrix[i][0], i));
    }
    int curVal = 0;
    for (int j = 0; j < k; j++) {
      TupleWithIndex curTuple = minHeap.poll();
      curVal = curTuple.value;
      if (rowPtrs[curTuple.index] < matrix.length - 1) {
        curTuple.value = matrix[curTuple.index][++rowPtrs[curTuple.index]];
        minHeap.offer(curTuple);
      }
    }
    return curVal;
  }

  //given a phone number, print all possible string values
  //of the corresponding keyboard
  public void phoneNumsToStrings(String phNum) {
    Map<Character, char[]> numsToChars = new HashMap<>();
    numsToChars.put('1', new char[]{' '});
    numsToChars.put('0', new char[]{'0'});
    numsToChars.put('2', new char[]{'a', 'b', 'c'});
    numsToChars.put('3', new char[]{'d', 'e', 'f'});
    numsToChars.put('4', new char[]{'g', 'h', 'i'});
    numsToChars.put('5', new char[]{'j', 'k', 'l'});
    numsToChars.put('6', new char[]{'m', 'n', 'o'});
    numsToChars.put('7', new char[]{'p', 'q', 'r', 's'});
    numsToChars.put('8', new char[]{'t', 'u', 'v'});
    numsToChars.put('9', new char[]{'w', 'x', 'y', 'z'});

    int[] curIndeces = new int[phNum.length()];
    int[] maxVals = new int[phNum.length()];
    for (int i = 0; i < maxVals.length; i++) {
      maxVals[i] = numsToChars.get(phNum.charAt(i)).length - 1;
    }
    boolean finishedPrinting = false;
    while (!finishedPrinting) {
      char[] curStr = new char[phNum.length()];
      for (int j = 0; j < curIndeces.length; j++) {
        curStr[j] = numsToChars.get(phNum.charAt(j))[curIndeces[j]];
      }
      System.out.println(new String(curStr));

      curIndeces[curIndeces.length - 1]++;
      if (curIndeces[curIndeces.length - 1] > maxVals[curIndeces.length - 1]) {
        boolean finishedCarryingOne = false;
        int pos = curIndeces.length - 1;
        while (!finishedCarryingOne&&pos>=0) {
          if(curIndeces[pos]>=maxVals[pos]){
            curIndeces[pos]=0;
            pos--;
          }
          else{
            curIndeces[pos]++;
            finishedCarryingOne=true;
          }
        }
        if(pos==-1) finishedPrinting = true;
      }
    }
  }

  //recursive solution to same problem
  public void altPhoneNumsToStrings(String phNum){
    Map<Character, char[]> numsToChars = new HashMap<>();
    numsToChars.put('1', new char[]{' '});
    numsToChars.put('0', new char[]{'0'});
    numsToChars.put('2', new char[]{'a', 'b', 'c'});
    numsToChars.put('3', new char[]{'d', 'e', 'f'});
    numsToChars.put('4', new char[]{'g', 'h', 'i'});
    numsToChars.put('5', new char[]{'j', 'k', 'l'});
    numsToChars.put('6', new char[]{'m', 'n', 'o'});
    numsToChars.put('7', new char[]{'p', 'q', 'r', 's'});
    numsToChars.put('8', new char[]{'t', 'u', 'v'});
    numsToChars.put('9', new char[]{'w', 'x', 'y', 'z'});

    char[] str = new char[phNum.length()];

    recursPhoneNum(numsToChars, phNum, str, 0);
  }

  private void recursPhoneNum(Map<Character, char[]> numToChars, String phNum, char[] str, int curIndex){
    if(curIndex<phNum.length()-1){
      for(char ch: numToChars.get(phNum.charAt(curIndex))){
        str[curIndex] = ch;
        recursPhoneNum(numToChars, phNum, str, curIndex+1);
      }
    }
    else{
      for(char ch: numToChars.get(phNum.charAt(curIndex))){
        str[curIndex] = ch;
        System.out.println(new String(str));
      }
    }
  }

  //given two large numbers represented by int arays (with each cell having value 0-9)
  //return an int array that is the sum of those values
  public int[] sumArrays(int[] a, int[] b){
    if(b==null||b.length==0) return a;
    if(a==null||a.length==0) return b;

    boolean bArrIsLonger = b.length>a.length;
    boolean carryOne = false;
    int aIndex = a.length-1;
    int bIndex = b.length-1;
    int aVal = 0;
    int bVal = 0;
    while(aIndex>=0||bIndex>=0){
      aVal = aIndex>=0? a[aIndex]:0;
      bVal = bIndex>=0? b[bIndex]:0;
      int sum = aVal+bVal;
      sum = carryOne? sum+1:sum;
      if(sum>9) {carryOne = true; sum = sum%10;}
      if(bArrIsLonger) b[bIndex] = sum;
      else a[aIndex] = sum;
      aIndex--;
      bIndex--;
    }
    if(!carryOne) return bArrIsLonger? b:a;
    else{
      int[] biggerArr = bArrIsLonger? b: a;
      int[] extendedArr = new int[biggerArr.length+1];
      extendedArr[0] = 1;
      for(int i = 1; i<extendedArr.length; i++){
        extendedArr[i] = biggerArr[i-1];
      }
      return extendedArr;
    }
  }

  //given a string and the result of randomly shuffling the string and adding a letter
  //return the letter
  //https://leetcode.com/problems/find-the-difference/
  //beats 68% of java submissions
  public char findTheDifference(String s, String t) {
    int[] asciiCounts = new int[26];
    for (int i = 0; i < t.length(); i++) {
      asciiCounts[t.charAt(i) - 97]++;
    }
    for (int j = 0; j < s.length(); j++) {
      asciiCounts[s.charAt(j) - 97]--;
    }
    for (int k = 0; k < asciiCounts.length; k++) {
      if (asciiCounts[k] != 0)
        return (char) (k + 97);
    }
    return ' ';
  }

  //given two strings, check if one string contains the other's characters in the same order
  //but not necessarily next to one another
  //https://leetcode.com/problems/is-subsequence/description/
  //beats 28% of java submissions
  public boolean isSubsequence(String s, String t) {
    if(s==null||s.length()==0) return true;
    if(t==null||t.length()==0) return false;
    int sLeftCharPtr = 0;
    int sRightCharPtr = s.length()-1;
    int tPtr = 0;
    while(tPtr<=t.length()/2){
      if(t.charAt(tPtr)==s.charAt(sLeftCharPtr)){
        if(sLeftCharPtr==sRightCharPtr) return true;
        sLeftCharPtr++;
      }
      if(t.charAt(t.length()-1-tPtr)==s.charAt(sRightCharPtr)){
        if(sLeftCharPtr==sRightCharPtr) return true;
        sRightCharPtr--;
      }
      tPtr++;
    }
    return false;
  }

  //for some bizarre reason using indexOf is faster
  //beats 100% of java submissions
  public boolean altIsSubsequence(String s, String t){
    if(s.length()>t.length()) return false;
    int prevCharIndex = -1;
    for(int i = 0; i < s.length(); i++){
      int curCharIndex = t.indexOf(s.charAt(i), prevCharIndex+1);
      if(curCharIndex<prevCharIndex) return false;
      else prevCharIndex = curCharIndex;
    }
    return true;
  }

  //translate roman number to int
  //https://leetcode.com/problems/roman-to-integer/
  //basic sol'n beats 28% of java submissions (I'm starting to think there's a bug in leetcode)
  public int romanToInt(String s) {
    Map<String, Integer> romanToVal = new HashMap<>();
    romanToVal.put("I",1);
    romanToVal.put("V",5);
    romanToVal.put("X",10);
    romanToVal.put("L", 50);
    romanToVal.put("C", 100);
    romanToVal.put("D", 500);
    romanToVal.put("M",1000);
    romanToVal.put("IV",4);
    romanToVal.put("IX", 9);
    romanToVal.put("XL",40);
    romanToVal.put("XC",90);
    romanToVal.put("CD",400);
    romanToVal.put("CM",900);

    int sum = 0;
    for(int i=0; i<s.length();i++){
      if ( i != s.length()-1 && romanToVal.containsKey(s.substring(i, i+2))) {
        sum+=romanToVal.get(s.substring(i, i+2));
        i++;
      }
      else sum+=romanToVal.get(s.substring(i,i+1));
    }
    return sum;
  }

  //alternative solution, beats 99.5% of java submissions
  public int altRomanToInt(String s) {
    int sum = 0;
    for(int i=0; i<s.length()-1; i++){
      int curVal = singleRomanToInt(s.charAt(i));
      if(curVal<singleRomanToInt(s.charAt(i+1))) sum-=curVal;
      else sum+=curVal;
    }
    return sum+singleRomanToInt(s.charAt(s.length()-1));
  }

  private int singleRomanToInt(char ch){
    switch(ch){
      case 'I': return 1;
      case 'V': return 5;
      case 'X': return 10;
      case 'L': return 50;
      case 'C': return 100;
      case 'D': return 500;
      case 'M': return 1000;
      default: return 0;
    }
  }


  //given two numbers as strings, return their sum as a string
  //https://leetcode.com/problems/add-strings/description/
  //standard solution beats 56% of java submissions
  public String addStrings(String num1, String num2) {
    if(num1==null||num1.length()==0) return num2;
    if(num2==null||num2.length()==0) return num1;

    char[] sumArr = new char[num2.length()>num1.length()?num2.length()+1:num1.length()+1];
    boolean carryOne = false;
    int num2Index = num2.length()-1;
    int num1Index = num1.length()-1;
    int num1Val;
    int num2Val;
    for(int i=sumArr.length-1; i>0; i--){
      if(num1Index>=0) num1Val = Character.getNumericValue(num1.charAt(num1Index));
      else num1Val=0;
      if(num2Index>=0) num2Val = Character.getNumericValue(num2.charAt(num2Index));
      else num2Val=0;
      int curSum = num1Val+num2Val;
      curSum = carryOne?curSum+1:curSum;
      carryOne = curSum>9;
      curSum %=10;
      curSum += '0';
      sumArr[i] = (char)curSum;
      num2Index--;
      num1Index--;
    }
    if(carryOne){
      sumArr[0] = '1';
      return new String(sumArr);
    }
    else{
      return new String(sumArr, 1, sumArr.length-1);
    }
  }

  //given a non-empty array of integers, return the third biggest distinct integer
  //seems trivial since it's 3rd rather than kth
  //https://leetcode.com/problems/third-maximum-number/description/
  //beats 100% of java submissions
  public int thirdMax(int[] nums) {
    long firstMax = Long.MIN_VALUE;
    long secondMax = Long.MIN_VALUE;
    long thirdMax = Long.MIN_VALUE;
    boolean thirdExists = false;
    for(int i=0; i<nums.length; i++){
      if(nums[i]!=firstMax&&nums[i]!=secondMax&&nums[i]!=thirdMax){
        if(nums[i]>firstMax){
          thirdMax = secondMax;
          secondMax = firstMax;
          firstMax = nums[i];
        }
        else if(nums[i]>secondMax){
          thirdMax = secondMax;
          secondMax = nums[i];
        }
        else if(nums[i]>thirdMax){
          thirdMax = nums[i];
        }
      }
    }
    return thirdMax==Long.MIN_VALUE?(int)firstMax:(int)thirdMax;
  }

  //guessing game as a method you call guess(int num), number from 1 to n
  //-1 if lower, 1 if higher, 0 if got it
  //https://leetcode.com/problems/guess-number-higher-or-lower/description/
  //this is just binary search
  public int guessNumber(int n, GuessNumber gn) {
    int low = 1;
    int high = n;
    int med = low+ (high-low)/2;
    int prevGuess;
    while(low<=high){
      prevGuess = gn.guess(med);
      if(prevGuess==0) break;
      else if(prevGuess==-1) high = med-1;
      else low = med+1;
      med = low+(high-low)/2;
    }
    return med;
  }

  //given an array of int h, int k vals with int h = height of person
  //and int k = num of people of height equal or greater than them in front of them
  //reconstruct the order
  //"in front" = to the left
  //https://leetcode.com/problems/queue-reconstruction-by-height/description/
  //let's toss the values in a map of int->list<int>
  //then start with the smallest value and fill in
  //slow but functioning solution
  public int[][] reconstructQueue(int[][] people) {
    if(people==null||people.length==0) return new int[0][0];
    List<Integer> uniqueVals = new ArrayList<>();
    Map<Integer, List<Integer>> valsToIndices = new HashMap<>();
    int[][] results = new int[people.length][people[0].length];
    for(int i=0; i<results.length; i++){
      results[i][0]=Integer.MAX_VALUE;
    }
    for(int j=0; j<people.length; j++){
      if(!valsToIndices.containsKey(people[j][0])){
        List<Integer> peopleInFront = new ArrayList<>();
        peopleInFront.add(people[j][1]);
        valsToIndices.put(people[j][0], peopleInFront);
        uniqueVals.add(people[j][0]);
      }
      else{
        valsToIndices.get(people[j][0]).add(people[j][1]);
      }
    }
    uniqueVals.sort(Comparator.naturalOrder());
    for(int k=0; k<uniqueVals.size(); k++){
      List<Integer> currentPositions = valsToIndices.get(uniqueVals.get(k));
      currentPositions.sort(Comparator.naturalOrder());
      for(int l=0; l<currentPositions.size(); l++){
        int count = 0;
        int index = 0;
        while(index<results.length&&count!=currentPositions.get(l)){

          if(results[index][0]>=uniqueVals.get(k)){
            count++;
          }
          index++;
        }
        while(results[index][0]!=Integer.MAX_VALUE){
          index++;
        }
        results[index][0]=uniqueVals.get(k);
        results[index][1]=currentPositions.get(l);
      }
    }
    return results;
  }

  //a better, but still slow solution
  //beats 49% of java submissions
  public int[][] reconstructQueueTakeTwo(int[][] people) {
    if(people==null||people.length==0) return people;
    int[][] results = new int[people.length][people[0].length];
    for(int i=0; i<results.length; i++){
      results[i][0]=Integer.MAX_VALUE;
    }
    Arrays.sort(people, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return Integer.compare(o1[0], o2[0]);
      }
    });
    for(int i=0; i<people.length; i++){
      int count = 0;
      int index = 0;
      while(index<results.length&&count!=people[i][1]){
        if(results[index][0]>=people[i][0]) count++;
        index++;
      }
      while(results[index][0]!=Integer.MAX_VALUE) index++;
      results[index][0] = people[i][0];
      results[index][1] = people[i][1];
    }
    return results;
  }

  //improved solution based on solution from YJL1228 on leetcode
  //beats 67% of java submissions
  public int[][] reconstructQueueTakeThree(int[][] people){
    if(people==null||people.length==0) return people;
    Arrays.sort(people, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[0]==o2[0]? o1[1]-o2[1] : o2[0]-o1[0];
      }
    });
    List<int[]> orderedQueue = new LinkedList<>();
    for(int i=0; i<people.length; i++){
      orderedQueue.add(people[i][1], people[i]);
    }
    return orderedQueue.toArray(people);
  }

  //given an array of length n
  //holding values between 1 and n
  //some values appear twice, the rest appear once
  //return all values between 1 and n that are missing
  //at O(N) time with O(1) extra space
  //https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
  public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> results = new ArrayList<>();
    if(nums==null||nums.length==0) return results;
    for(int i=0; i<nums.length; i++){
      if(nums[i]-1!=i&&nums[i]!=0){
        int val = nums[i];
        nums[i]=0;
        while(val!=0&&nums[val-1]!=val){
          int tmp = nums[val-1];
          nums[val-1] = val;
          val = tmp;
        }
      }
    }
    for(int j=0; j<nums.length; j++){
      if(nums[j]==0) results.add(j+1);
    }
    return results;
  }

  //level order traversal for N-ary tree
  //https://leetcode.com/problems/n-ary-tree-level-order-traversal/description/
  //beats 75% of java submissions
  public List<List<Integer>> levelOrder(NaryTreeNode root) {
    if(root==null) return new ArrayList<>();
    Queue<NaryTreeNode> level = new LinkedList<>();
    level.offer(root);
    List<List<Integer>> results = new ArrayList<>();
    while(!level.isEmpty()){
      int curLevelSize = level.size();
      List<Integer> levelVals = new ArrayList<>(curLevelSize);
      for(int i=0; i<curLevelSize; i++){
        NaryTreeNode curNode = level.poll();
        levelVals.add(curNode.val);
        for(int j=0; j<curNode.children.size(); j++){
          level.add(curNode.children.get(j));
        }
      }
      results.add(levelVals);
    }
    return results;
  }

  //find arithmetic slices in array
  //https://leetcode.com/problems/arithmetic-slices/description/
  //beats 100% of java submissions
  public int numberOfArithmeticSlices(int[] A) {
    if(A==null||A.length<3) return 0;
    int numOfSlices = 0;
    for(int i=0; i<A.length-2; i++){
      int distBetweenVals = A[i+1]-A[i];
      int sliceIndex = i+1;
      while(sliceIndex+1<A.length&&A[sliceIndex+1]-A[sliceIndex]==distBetweenVals) sliceIndex++;
      if(sliceIndex+1-i>=3){
        int multiplier = 1;
        for(int j=sliceIndex-i+1; j>=3; j--){
          numOfSlices += multiplier;
          multiplier++;
        }
      }
      i = sliceIndex-1;
    }
    return numOfSlices;
  }

  //delete node in BST
  //https://leetcode.com/problems/delete-node-in-a-bst/description/
  //beats 45% of java submissions
  public TreeNode deleteNode(TreeNode root, int key) {
    if(root==null) return null;
    TreeNode parent = new TreeNode(-1);
    parent.left=root;
    recursFindNode(parent, root, key);
    return parent.left;
  }

  private void recursFindNode(TreeNode parent, TreeNode root, int key){
    if(root.val==key){
      if(root.left!=null&&root.right!=null) root.val = findReturnDelete(root, root.right);
      else if(root.left!=null) root.val = findReturnDelete(root, root.left);
      else if(root.right!=null) root.val = findReturnDelete(root, root.right);
      else{
        if(root==parent.left) parent.left=null;
        else parent.right=null;
      }
    }
    else if(root.val>key){
      if(root.left==null) return;
      else recursFindNode(root, root.left, key);
    }
    else{
      if(root.right==null) return;
      else recursFindNode(root, root.right, key);
    }
  }

  private int findReturnDelete(TreeNode parent, TreeNode root){
    int val = -1;
    if(parent.left==root){
      if(root.right==null){
        val = root.val;
        parent.left=root.left;
        return val;
      }
      while(root.right!=null){
        parent = root;
        root = root.right;
      }
      val = root.val;
      parent.right=root.left;
      return val;
    }
    else{
      if(root.left==null){
        val = root.val;
        parent.right = root.right;
        return val;
      }
      while(root.left!=null){
        parent = root;
        root = root.left;
      }
      val = root.val;
      parent.left = root.right;
      return val;
    }
  }

  //given a string consisting of digits as words that has been shuffled
  //return the digits it consists of in sorted order
  //https://leetcode.com/problems/reconstruct-original-digits-from-english/
  //beats 77% of java submissions
  //key idea - as we remove words, more and more words left as options have a unique letter
  public String originalDigits(String s) {
    if(s==null||s.length()==0) return s;
    int[] charCounts = new int[26];
    for(int i=0; i<s.length(); i++){
      charCounts[s.charAt(i)-97]++;
    }

    List<char[]> digitsToChars = new ArrayList<>(10);
    digitsToChars.add(0, new char[] {'z', 'e', 'r', 'o'});
    digitsToChars.add(1, new char[] {'o', 'n', 'e'});
    digitsToChars.add(2, new char[] {'t', 'w', 'o'});
    digitsToChars.add(3, new char[] {'t', 'h', 'r', 'e', 'e'});
    digitsToChars.add(4, new char[] {'f', 'o', 'u', 'r'});
    digitsToChars.add(5, new char[] {'f', 'i', 'v', 'e'});
    digitsToChars.add(6, new char[] {'s', 'i', 'x'});
    digitsToChars.add(7, new char[] {'s', 'e', 'v', 'e', 'n'});
    digitsToChars.add(8, new char[] {'e', 'i', 'g', 'h', 't'});
    digitsToChars.add(9, new char[] {'n', 'i', 'n', 'e'});
    int[] orderToReadIn = new int[] {0,2,4,6,8,1,3,5,7,9};
    char[] keyChar = new char[] {'z', 'w', 'u', 'x', 'g', 'o', 'r', 'f', 's', 'i'};
    List<Integer> resultAsList = new ArrayList<>();

    int readIndex = 0;
    while(readIndex<10){
      if(charCounts[keyChar[readIndex]-97]<1) readIndex++;
      else{
        resultAsList.add(orderToReadIn[readIndex]);
        char[] curWord = digitsToChars.get(orderToReadIn[readIndex]);
        for(int i=0; i<curWord.length; i++){
          charCounts[curWord[i]-97]--;
        }
      }
    }
    resultAsList.sort(Comparator.naturalOrder());
    char[] result = new char[resultAsList.size()];
    for(int j=0; j<result.length; j++){
      int asciiDigitVal = resultAsList.get(j)+48;
      result[j] = (char) asciiDigitVal;
    }
    return new String(result);
  }

  //given tuples representing intervals as a matrix (an array of tuples)
  //create a new array such that each index points to the index of the tuple
  //with a start value bigger than or equal to its end value
  //https://leetcode.com/problems/find-right-interval/
  //beats 67% of java submissions
  public int[] findRightInterval(Interval[] intervals) {
    if(intervals==null||intervals.length==0) return new int[] {};
    Queue<int[]> nextSmallestStart = new PriorityQueue<>(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[0]-o2[0];
      }
    });
    for(int i=0; i<intervals.length; i++){
      nextSmallestStart.offer(new int[] {intervals[i].start, i});
      intervals[i].start = i;
    }


    Arrays.sort(intervals, new Comparator<Interval>() {
      @Override
      public int compare(Interval o1, Interval o2) {
        return o1.end-o2.end;
      }
    });

    int[] results = new int[intervals.length];
    for(int i=0; i<intervals.length; i++){
      while(!nextSmallestStart.isEmpty()&&intervals[i].end>nextSmallestStart.peek()[0]) nextSmallestStart.poll();
      results[intervals[i].start] = nextSmallestStart.isEmpty()? -1:nextSmallestStart.peek()[1];
    }
    return results;
  }

  //given a list of courses and prerequisites as a matrix (array of tuples)
  //and the number of courses that need to be taken
  //find if there is a way to take all courses (e.g. no circular dependencies)
  //https://leetcode.com/problems/course-schedule/
  //beats 87% of java submissions
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if(numCourses<1||prerequisites==null||prerequisites.length==0) return true;
    List<List<Integer>> sortedPrereqs = new ArrayList<>(numCourses);
    for(int i=0; i<numCourses; i++){
      sortedPrereqs.add(new ArrayList<>());
    }
    for(int i=0; i<prerequisites.length; i++){
      sortedPrereqs.get(prerequisites[i][0]).add(prerequisites[i][1]);
    }
    boolean[] nodeKnownAcyclical = new boolean[numCourses];
    boolean[] visited = new boolean[numCourses];
    for(int j=numCourses-1; j>=0; j--){
      if(sortedPrereqs.get(j)==null) nodeKnownAcyclical[j]=true;
      if(!nodeKnownAcyclical[j]){
        visited[j]=true;
        if(!dfsCourseTraversal(nodeKnownAcyclical, sortedPrereqs, visited, j)) return false;
        nodeKnownAcyclical[j]=true;
      }
    }
    return true;
  }

  private boolean dfsCourseTraversal(boolean[] nodeKnownAcyclical,
      List<List<Integer>> sortedPrereqs,
      boolean[] visited,
      int course){
    List<Integer> curPrereqs = sortedPrereqs.get(course);
    for(int i=0; i<curPrereqs.size(); i++){
      int curPrereq = curPrereqs.get(i);
      if(sortedPrereqs.get(curPrereq)==null) nodeKnownAcyclical[curPrereq]=true;
      else if(!nodeKnownAcyclical[curPrereq]){
        if(visited[curPrereq]) return false;
        else{
          visited[curPrereq] = true;
          if(!dfsCourseTraversal(nodeKnownAcyclical, sortedPrereqs, visited, curPrereq)) return false;
          nodeKnownAcyclical[curPrereq] = true;
        }
      }
    }
    return true;
  }

  //given an array of integers as nums1, which are all contained in nums2 in some order
  //find the next value greater than each value in nums1 in nums2
  //https://leetcode.com/problems/next-greater-element-i/description/
  //bog-standard solution, beats 52% of submissions
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    if(nums2==null||nums1==null||nums1.length==0||nums2.length==0) return new int[] {};
    int curGreaterVal = Integer.MIN_VALUE;
    Map<Integer, Integer> intToGreaterVal = new HashMap<>(nums2.length);
    for(int i=nums2.length-1; i>=0; i--){
      if(curGreaterVal<=nums2[i]){
        while(curGreaterVal!=Integer.MIN_VALUE&&curGreaterVal<=nums2[i]){
          curGreaterVal = intToGreaterVal.get(curGreaterVal);
        }
      }
      intToGreaterVal.put(nums2[i],curGreaterVal);
      curGreaterVal = nums2[i];
    }
    int[] results = new int[nums1.length];
    for(int j=0; j<nums1.length; j++){
      int curVal = intToGreaterVal.get(nums1[j]);
      curVal = curVal==Integer.MIN_VALUE? -1:curVal;
      results[j] = curVal;
    }
    return results;
  }

  //sort characters in string by frequency
  //most frequent chars first
  //https://leetcode.com/problems/sort-characters-by-frequency/submissions/
  //beats 98.65% of java submissions
  public String frequencySort(String s) {
    if(s==null||s.length()<3) return s;
    int[] counts = new int[256];
    for(int i=0; i<s.length(); i++){
      counts[s.charAt(i)]++;
    }
    Queue<CharWithCount> maxHeap = new PriorityQueue<>();
    for(int j=0; j<counts.length; j++){
      if(counts[j]!=0){
        CharWithCount ch = new CharWithCount(counts[j], (char)j);
        maxHeap.offer(ch);
      }
    }
    char[] resultAsChar = new char[s.length()];
    int nextCharToWrite = 0;
    while(!maxHeap.isEmpty()){
      CharWithCount ch = maxHeap.poll();
      for(int k=0; k<ch.count; k++){
        resultAsChar[nextCharToWrite]=ch.ch;
        nextCharToWrite++;
      }
    }
    return new String(resultAsChar);
  }

  private class CharWithCount implements Comparable<CharWithCount>{
    int count;
    char ch;
    CharWithCount(int count, char ch) {
      this.count = count;
      this.ch = ch;
    }

    public int compareTo(CharWithCount charWithCount){
      return charWithCount.count-this.count;
    }
  }

  //find Hamming distance between two values
  //This is not optimal, but I know how to do it without looking anything up
  //https://leetcode.com/problems/hamming-distance/
  //beats 61% of java submissions
  public int hammingDistance(int x, int y) {
    String xStr = Integer.toString(x,2);
    String yStr = Integer.toString(y,2);
    String longerStr = xStr.length()>yStr.length()? xStr:yStr;
    int diff = 0;
    int index = 0;
    while(index<xStr.length()&&index<yStr.length()){
      if(xStr.charAt(xStr.length()-1-index)!=yStr.charAt(yStr.length()-1-index)) diff++;
      index++;
    }
    while(index<longerStr.length()){
      if(longerStr.charAt(longerStr.length()-1-index)!='0') diff++;
      index++;
    }
    return diff;
  }

  //given a zero-indexed array of size N holding values 0 - N-1
  //find such an index that it makes the longest chain of jumps from one value to another
  //and return the number of jumps
  //https://leetcode.com/problems/array-nesting/
  //beats 88% of java submissions
  public int arrayNesting(int[] nums) {
    if(nums==null||nums.length==0) return 0;
    boolean[] valCalculated = new boolean[nums.length];
    boolean[] visited = new boolean[nums.length];
    for(int i=0; i<nums.length; i++){
      if(!visited[i]){
        nums[i] = recursArrayNesting(nums, visited, valCalculated, i);
      }
    }
    int biggest = Integer.MIN_VALUE;
    for(int j=0; j<nums.length; j++){
      biggest = nums[j]>biggest? nums[j]:biggest;
    }
    return biggest;
  }

  private int recursArrayNesting(int[] nums, boolean[] visited, boolean[] valCalculated, int index){
    if(valCalculated[index]) return nums[index]+1;
    if(visited[index]) return 0;
    else{
      visited[index]=true;
      nums[index] = recursArrayNesting(nums, visited, valCalculated, nums[index]);
      valCalculated[index]=true;
      return nums[index]+1;
    }
  }

  //find depth of N-ary tree
  //beats 54% of java submissions
  //as standard as bfs gets
  //https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
  public int maxDepth(NaryTreeNode root) {
    if(root==null) return 0;
    int levels = 0;
    Queue<NaryTreeNode> curLevel = new LinkedList<>();
    curLevel.offer(root);
    while(!curLevel.isEmpty()){
      int curLevelSize = curLevel.size();
      for(int i=0; i<curLevelSize; i++){
        NaryTreeNode curNode = curLevel.poll();
        for(int j=0; j<curNode.children.size(); j++){
          curLevel.offer(curNode.children.get(j));
        }
      }
      levels++;
    }
    return levels;
  }


  //alt solution with dfs
  //beats 90% of java submissions
  public int dfsMaxDepth(NaryTreeNode root){
    if(root==null) return 0;
    if(root.children.isEmpty()) return 1;
    return recursMaxDepth(root.children)+1;
  }

  private int recursMaxDepth(List<NaryTreeNode> children){
    int maxDepth = 0;
    for(int i=0; i<children.size(); i++){
      int tmp = 0;
      if(!children.get(i).children.isEmpty()){
        tmp = recursMaxDepth(children.get(i).children);
        if(tmp>maxDepth) maxDepth=tmp;
      }
    }
    return maxDepth+1;
  }

  //check if tree t is a subtree of tree s
  //a tree technically counts as its own subtree
  //https://leetcode.com/problems/subtree-of-another-tree/
  //beats 95% of java submissions
  public boolean isSubtree(TreeNode s, TreeNode t) {
    if(s==null&&t==null) return true;
    if(s!=null^t!=null) return false;
    List<TreeNode> matches = new ArrayList<>();
    findMatchVals(s, t, matches);
    if(matches.isEmpty()) return false;
    for(int i=0; i<matches.size(); i++){
      if(isSameTree(matches.get(i), t)) return true;
    }
    return false;
  }

  private void findMatchVals(TreeNode root, TreeNode t, List<TreeNode> matches){
    if(root.val==t.val) matches.add(root);
    if(root.left!=null) findMatchVals(root.left, t, matches);
    if(root.right!=null) findMatchVals(root.right, t, matches);
  }

  private boolean isSameTree(TreeNode root1, TreeNode root2){
    if(root1==null&&root2==null) return true;
    if(root1==null^root2==null) return false;
    if(root1.val!=root2.val) return false;
    if(!isSameTree(root1.left, root2.left)) return false;
    if(!isSameTree(root1.right, root2.right)) return false;
    return true;
  }

  //given two arrays of strings, find the string with the smallest index in either array
  //if there are two common strings with the same early index, output both
  //the problem is written such that there is certain to be an answer
  //https://leetcode.com/problems/minimum-index-sum-of-two-lists/
  //beats 95% of java submissions while being really basic
  public String[] findRestaurant(String[] list1, String[] list2) {
    Map<String, Integer> list1StrToIndex = new HashMap<>();
    for(int i=0; i<list1.length; i++){
      list1StrToIndex.put(list1[i], i);
    }
    int lowestSum = list1.length+list2.length;
    List<String> result = new ArrayList<>();

    for(int j=0; j<list2.length; j++){
      if(list1StrToIndex.containsKey(list2[j])){
        int list1Index = list1StrToIndex.get(list2[j]);
        if(list1Index+j<lowestSum){
          lowestSum = list1Index + j;
          result.clear();
          result.add(list2[j]);
        }
        else if(list1Index+j==lowestSum) result.add(list2[j]);;
      }
    }
    return result.toArray(new String[result.size()]);
  }

  //given a tree, a depth d, and a value v
  //book a level of tree nodes at depth d with values v
  //https://leetcode.com/problems/add-one-row-to-tree/
  //the depth is guaranteed within range 1->maximum tree depth;
  //standard solution beats 77% of java submissions
  public TreeNode addOneRow(TreeNode root, int v, int d) {
    if(d==1){
      TreeNode result = new TreeNode(v);
      result.left=root;
      return result;
    }
    Queue<TreeNode> level = new LinkedList<>();
    level.offer(root);
    int curDepth = 1;
    int levelSize;
    while(curDepth!=d-1){
      levelSize = level.size();
      for(int i=0; i<levelSize; i++){
        TreeNode tmp = level.poll();
        if(tmp.left!=null) level.offer(tmp.left);
        if(tmp.right!=null) level.offer(tmp.right);
      }
      curDepth++;
    }
    levelSize = level.size();
    for(int j=0; j<levelSize; j++){
      TreeNode tmp = level.poll();
      TreeNode addedRight = new TreeNode(v);
      TreeNode addedLeft = new TreeNode(v);
      addedLeft.left = tmp.left;
      tmp.left = addedLeft;
      addedRight.right = tmp.right;
      tmp.right = addedRight;
    }
    return root;
  }

  //starting with a single char ('A') in a notepad and able to perform copy all and paste ops
  //calculate minimum number of steps to get to n chars
  //https://leetcode.com/problems/2-keys-keyboard/
  //beats 100% of java submissions
  public int minSteps(int n) {
    int curNum = 1;
    int steps = 0;
    int curCopy = 0;
    while(curNum!=n){
      if(n%(curNum*2)==0||n%curNum==0&&curCopy!=curNum){
        curCopy=curNum;
        steps++;
      }
      if(curCopy==0){
        curCopy = curNum;
        steps++;
      }
      curNum+=curCopy;
      steps++;
    }
    return steps;
  }

  //given two parties of senators voting
  //with each senator either choosing to ban a senator from the opposing party from voting
  //or announcing a victory should all remaining members be from the same party
  //return either "Radiant" or "Dire" for which party wins
  //given an in-order list of actions taken by senators as a string of their affiliations "RD"
  //https://leetcode.com/problems/dota2-senate/
  //beats 69% of java submissions
  public String predictPartyVictory(String senate) {
    List<Integer> dSenators = new LinkedList<>();
    List<Integer> rSenators = new LinkedList<>();

    for(int i=0; i<senate.length(); i++){
      if(senate.charAt(i)=='D') dSenators.add(i);
      else rSenators.add(i);
    }
    while(!dSenators.isEmpty()&&!rSenators.isEmpty()){
      List<Integer> remainingDSenators = new LinkedList<>();
      List<Integer> remainingRSenators = new LinkedList<>();
      while(!dSenators.isEmpty()||!rSenators.isEmpty()){
        if(!dSenators.isEmpty()&&!rSenators.isEmpty()){
          if(dSenators.get(0)<rSenators.get(0)){
            remainingDSenators.add(dSenators.get(0));
            dSenators.remove(0);
            rSenators.remove(0);
          }
          else{
            remainingRSenators.add(rSenators.get(0));
            rSenators.remove(0);
            dSenators.remove(0);
          }
        }
        else if(rSenators.isEmpty()){
          remainingDSenators.add(dSenators.get(0));
          dSenators.remove(0);
          if(!remainingRSenators.isEmpty()) remainingRSenators.remove(0);
          else return "Dire";
        } else if (dSenators.isEmpty()) {
          remainingRSenators.add(rSenators.get(0));
          rSenators.remove(0);
          if(!remainingDSenators.isEmpty()) remainingDSenators.remove(0);
          else return "Radiant";
        }
      }
      rSenators = remainingRSenators;
      dSenators = remainingDSenators;
    }
    return rSenators.isEmpty()? "Dire":"Radiant";
  }

  //alt solution using pointers to indeces
  //not actually better though
  public String altPredictPartyVictory(String senate) {
    char[] senateArr = senate.toCharArray();
    boolean[] banned = new boolean[senateArr.length];
    boolean atLeastOneR = true;
    boolean atLeastOneD = true;
    int firstRemainingD = 0;
    int firstRemainingR = 0;
    while(atLeastOneR&&atLeastOneD){
      for(int i=0; i<senateArr.length; i++){
        if(banned[i]) continue;
        if(senateArr[i]=='D'){
          firstRemainingR = banSenator(senateArr, banned, i, 'R', firstRemainingR);
          if(firstRemainingR==-1) return "Dire";
        }
        else{
          firstRemainingD = banSenator(senateArr, banned, i, 'D', firstRemainingD);
          if(firstRemainingD==-1) return "Radiant";
        }
      }
      int remainder = 0;
      for(int l=0; l<banned.length; l++){
        if(!banned[l]) remainder++;
      }
      char[] nextArr = new char[remainder];
      int index = 0;
      atLeastOneD = false;
      atLeastOneR = false;
      for(int j=0; j<senateArr.length; j++){
        if(!banned[j]){
          if(!atLeastOneD&&senateArr[j]=='D') atLeastOneD = true;
          if(!atLeastOneR&&senateArr[j]=='R') atLeastOneR = true;
          nextArr[index] = senateArr[j];
          index++;
        }
      }
      senateArr = nextArr;
      banned = new boolean[nextArr.length];
      firstRemainingD=0;
      firstRemainingR=0;
    }
    return atLeastOneD? "Dire":"Radiant";
  }

  private int banSenator(char[] arr, boolean[] banned, int curIndex, char opposer, int firstOpposer){
    for(int i=curIndex+1; i<arr.length; i++){
      if(!banned[i]&&arr[i]==opposer){
        banned[i]=true;
        return firstOpposer;
      }
    }
    for(int j=firstOpposer; j<curIndex; j++){
      if(!banned[j]&&arr[j]==opposer){
        banned[j]=true;
        return j;
      }
    }
    return -1;
  }

  //N-ary tree traversal
  //https://leetcode.com/problems/n-ary-tree-preorder-traversal/
  //trivial problem, beats 77% of java submissions
  public List<Integer> naryPreorder(NaryTreeNode root) {
    List<Integer> result = new ArrayList<>();
    if(root==null) return result;
    recursNaryPreorder(root, result);
    return result;
  }

  private void recursNaryPreorder(NaryTreeNode root, List<Integer> result){
    result.add(root.val);
    for(NaryTreeNode ntn: root.children){
      recursNaryPreorder(ntn, result);
    }
  }

  //given an unsorted array from 1 to n where one value has been replaced with a dupe of another
  //return an integer array containing [the dupe val, the missing val]
  //https://leetcode.com/problems/set-mismatch/
  //beats 96% of java submissions
  public int[] findErrorNums(int[] nums) {
    if(nums==null||nums.length<2) return new int[] {-1,-1};
    boolean[] foundVals = new boolean[nums.length];
    int duplicate = -1;
    int missing = -1;
    for(int i=0; i<nums.length; i++){
      if(!foundVals[nums[i]-1]) foundVals[nums[i]-1] = true;
      else duplicate = nums[i];
    }
    for(int j=0; j<foundVals.length; j++){
      if(foundVals[j]) continue;
      else{
        missing = j+1;
        break;
      }
    }
    return new int[] {duplicate, missing};
  }

  //given an array nums and a value k, return the maximum average
  //produced by summing k contiguous elements in nums
  //https://leetcode.com/problems/maximum-average-subarray-i/
  //beats 74% of java submissions
  public double findMaxAverage(int[] nums, int k) {
    double kDouble = (double)k;
    int movingSum = 0;
    for(int i=0; i<k; i++){
      movingSum+=nums[i];
    }
    double maxAvg = movingSum/kDouble;
    for(int i=0; i<nums.length-k; i++){
      movingSum-=nums[i];
      movingSum+=nums[i+k];
      if(movingSum/kDouble>maxAvg) maxAvg = movingSum/kDouble;
    }
    return maxAvg;
  }

  //given certain text as a list of strings, return the top k most frequent words in sorted order
  //if two words have same frequency, return in alphabetical order
  //https://leetcode.com/problems/top-k-frequent-words/
  //beats 97% of java submissions
  public List<String> topKFrequentWords(String[] words, int k) {
    Queue<WordFrequency> maxHeap = new PriorityQueue<>();
    Map<String, Integer> wordToFreq = new HashMap<>();
    List<String> results = new ArrayList<>(k);
    for(int i=0; i<words.length; i++){
      if(wordToFreq.containsKey(words[i])){
        wordToFreq.put(words[i], wordToFreq.get(words[i])+1);
      }
      else{
        wordToFreq.put(words[i], 1);
      }
    }
    for(String key: wordToFreq.keySet()){
      maxHeap.offer(new WordFrequency(key, wordToFreq.get(key)));
    }
    for(int j=0; j<k; j++){
      results.add(maxHeap.poll().word);
    }
    return results;
  }

  class WordFrequency implements Comparable<WordFrequency>{
    String word;
    int occurrence;

    public WordFrequency(String word, int occurrence){
      this.word = word;
      this.occurrence = occurrence;
    }

    public int compareTo(WordFrequency wf){
      return wf.occurrence!=this.occurrence? wf.occurrence-this.occurrence:this.word.compareTo(wf.word);
    }
  }

  //given a list of b-tree nodes, each holding an int and a list of child nodes
  //return the sum of values of the subtree starting at a certain node
  //https://leetcode.com/problems/employee-importance/
  //basic bfs solution, only beats 49% of java submissions
  public int getImportance(List<Employee> employees, int id) {
    Map<Integer, Employee> idToIndex = new HashMap<>();
    for(int i=0; i<employees.size(); i++){
      idToIndex.put(employees.get(i).id, employees.get(i));
    }
    if(!idToIndex.containsKey(id)) return 0;
    Queue<Employee> curList = new LinkedList<>();
    int totalImportance = 0;
    curList.add(idToIndex.get(id));
    while(!curList.isEmpty()){
      Employee tmp = curList.poll();
      totalImportance+=tmp.importance;
      for(int j=0; j<tmp.subordinates.size(); j++){
        curList.offer(idToIndex.get(tmp.subordinates.get(j)));
      }
    }
    return totalImportance;
  }

  //dfs solution, same performance
  public int altGetImportance(List<Employee> employees, int id){
    Map<Integer, Employee> idToIndex = new HashMap<>();
    for(int i=0; i<employees.size(); i++){
      idToIndex.put(employees.get(i).id, employees.get(i));
    }
    if(!idToIndex.containsKey(id)) return 0;
    int[] totalVal = new int[] {0};
    recursGetImportance(idToIndex, id, totalVal);
    return totalVal[0];
  }

  private void recursGetImportance(Map<Integer, Employee> idToIndex, int id, int[] totalVal){
    Employee tmp = idToIndex.get(id);
    totalVal[0]+=tmp.importance;
    for(int i=0; i<tmp.subordinates.size(); i++){
      recursGetImportance(idToIndex, tmp.subordinates.get(i), totalVal);
    }
  }

  //relies on the idea that employee ids will be densely packed into a region
  //beats 79% of java submissions
  public int alt2GetImportance(List<Employee> employees, int id) {
    int biggestVal = Integer.MIN_VALUE;
    int smallestVal = Integer.MAX_VALUE;
    for(int i=0; i<employees.size(); i++){
      int tmpID = employees.get(i).id;
      if(tmpID<smallestVal) smallestVal=tmpID;
      if(tmpID>biggestVal) biggestVal=tmpID;
    }
    Employee[] employeeArr = new Employee[biggestVal-smallestVal+1];
    for(int j=0; j<employees.size(); j++){
      Employee curEmployee = employees.get(j);
      employeeArr[curEmployee.id-smallestVal]=curEmployee;
    }
    if(id-smallestVal<0||id-smallestVal>=employeeArr.length||employeeArr[id-smallestVal]==null) return 0;
    int[] totalVal = new int[] {0};
    recurs2GetImportance(employeeArr, smallestVal, id, totalVal);
    return totalVal[0];
  }

  private void recurs2GetImportance(Employee[] employees, int smallestVal, int id, int[] totalVal){
    Employee tmp = employees[id-smallestVal];
    totalVal[0]+=tmp.importance;
    for(int i=0; i<tmp.subordinates.size(); i++){
      recurs2GetImportance(employees, smallestVal, tmp.subordinates.get(i), totalVal);
    }
  }

  //given a sequence of commands provided to a robot in position (n,n)
  //determine if it will end at same position
  //commands given as U D L R
  //basic solutuion beats 49% of java submissions
  public boolean judgeCircle(String moves) {
    int[] dirSums = new int[4];
    for(int i=0; i<moves.length(); i++){
      dirSums[commandToIndex(moves.charAt(i))]++;
    }
    return dirSums[0]==dirSums[1]&&dirSums[2]==dirSums[3];
  }

  private int commandToIndex(char ch){
    switch(ch){
      case 'U': return 0;
      case 'D': return 1;
      case 'L': return 2;
      case 'R': return 3;
      default: return -1;
    }
  }

  //given a lower and upper bound values
  //return every value in that range, inclusive
  //such that the number is wholly divisible by each of its digits
  //no 0s in the number
  //https://leetcode.com/problems/self-dividing-numbers/
  //really basic implementation that beats 71% of java submissions
  public List<Integer> selfDividingNumbers(int left, int right) {
    List<Integer> results = new ArrayList<>();
    for(int i=left; i<=right; i++){
      int tmpNum = i;
      boolean notFullyDivisible = false;
      while(tmpNum>0){
        if(tmpNum%10==0||i%(tmpNum%10)!=0){
          notFullyDivisible=true;
          break;
        }
        tmpNum/=10;
      }
      if(!notFullyDivisible) results.add(i);
    }
    return results;
  }

  //hackerrank entry problem
  //given an int array with 5 elements, return the sum of the 4 smallest
  //and the sum of the 5 biggest
  public long[] miniMaxSum(int[] arr) {
    long maxVal = Long.MIN_VALUE;
    int maxIndex = 0;
    Long minVal = Long.MAX_VALUE;
    int minIndex = 0;
    for(int i=0; i<arr.length; i++){
      if(arr[i]>maxVal){
        maxVal = (long)arr[i];
        maxIndex = i;
      }
      if(arr[i]<minVal){
        minVal = (long)arr[i];
        minIndex = i;
      }
    }
    if(minIndex==maxIndex){
      minVal-=arr[0];
      maxVal-=arr[0];
    }
    for(int i=0; i<arr.length; i++){
      if(i!=maxIndex&&i!=minIndex){
        maxVal+=arr[i];
        minVal+=arr[i];
      }
    }
    return new long[] {minVal, maxVal};
  }

  public String timeConversion(String s) {
    char[] sArr = s.toCharArray();
    int hours = Integer.parseInt(s.substring(0,2));
    if(hours==12){
      if(sArr[sArr.length-2]=='A'){
        sArr[0]='0';
        sArr[1]='0';
      }
    }
    else{
      if(sArr[sArr.length-2]=='P'){
        hours+=12;
        sArr[0]=Character.forDigit(hours/10, 10);
        sArr[1]=Character.forDigit(hours%10, 10);
      }
    }
    return new String(sArr, 0, sArr.length-2);
  }

  public int utopianTree(int n) {
    int treeHeight = 1;
    boolean spring = true;
    for(int i=n; i>0; i--){
      if(spring){
        treeHeight*=2;
        spring = false;
      }
      else{
        treeHeight++;
        spring=true;
      }
    }
    return treeHeight;
  }

  public int viralAdvertising(int n) {
    int lastLiked = 0;
    int cumulative = 0;
    int lastShared = 5;
    for(int i=1; i<=n; i++){
      lastLiked = lastShared/2;
      cumulative += lastLiked;
      lastShared=lastLiked*3;
    }
    return cumulative;
  }

  public List<Integer> freqQuery(List<List<Integer>> queries) {
    Map<Integer, Integer> valCounts = new HashMap<>();
    Map<Integer, Integer> countsOfVals = new HashMap<>();
    List<Integer> results = new ArrayList<>();
    for(int i=0; i<queries.size(); i++){
      List<Integer> query = queries.get(i);
      Integer val = query.get(1);
      if(query.get(0)==1){
        if(!valCounts.containsKey(val)){
          valCounts.put(val, 1);
          addToCount(1, countsOfVals);
        }
        else{
          int prevCount = valCounts.get(val);
          cutFromCount(prevCount, countsOfVals);
          valCounts.put(val, prevCount+1);
          addToCount(prevCount+1, countsOfVals);
        }
      }
      else if(query.get(0)==2){
        if(valCounts.containsKey(val)){
          int prevCount = valCounts.get(val);
          if(prevCount==1){
            valCounts.remove(val);
            cutFromCount(prevCount, countsOfVals);
          }
          else{
            cutFromCount(prevCount, countsOfVals);
            valCounts.put(val, prevCount-1);
            addToCount(prevCount-1, countsOfVals);
          }
        }
      }
      else if(query.get(0)==3){
        int count = query.get(1);
        results.add(countsOfVals.containsKey(count)?1:0);
      }
    }
    return results;
  }

  private void cutFromCount(int count, Map<Integer, Integer> countsOfVals){
    if(!countsOfVals.containsKey(count)) return;
    if(countsOfVals.get(count)==1) countsOfVals.remove(count);
    else countsOfVals.put(count, countsOfVals.get(count)-1);
  }
  private void addToCount(int count, Map<Integer, Integer> countsOfVals){
    if(countsOfVals.containsKey(count)) countsOfVals.put(count,countsOfVals.get(count)+1);
    else countsOfVals.put(count, 1);
  }

  public  int hourglassSum(int[][] arr) {
    if(arr.length<3||arr[0].length<3) return 0;
    int biggestVal = 0;
    for(int i=0; i<arr.length-2; i++){
      int firstSum = arr[i][0]+arr[i][1]+arr[i][2];
      firstSum += arr[i+1][1];
      firstSum+= arr[i+2][0]+arr[i+2][1]+arr[i+2][2];
      biggestVal = firstSum>=biggestVal? firstSum:biggestVal;
      for(int j=1; j <arr[i].length-2; j++){
        firstSum-=arr[i][j-1]+arr[i+2][j-1]+arr[i+1][j];
        firstSum+=arr[i][j+2]+arr[i+2][j+2]+arr[i+1][j+1];
        biggestVal = firstSum>=biggestVal? firstSum:biggestVal;
      }
    }
    return biggestVal;
  }

  //https://leetcode.com/problems/longest-word-in-dictionary/
  //beats 72% of java submissions
  public String longestWord(String[] words) {
    Queue<String> longerWords = new PriorityQueue<>(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o2.length()!=o1.length()?o2.length()-o1.length():o1.compareTo(o2);
      }
    });
    Set<String> stringInList = new HashSet<>();
    for(int i=0; i<words.length; i++){
      longerWords.offer(words[i]);
      stringInList.add(words[i]);
    }
    while(!longerWords.isEmpty()){
      String curString = longerWords.poll();
      boolean wordCanBeBuilt = true;
      for(int j=0; j<curString.length()-1; j++){
        if(!stringInList.contains(curString.substring(0, j+1))){
          wordCanBeBuilt=false;
          break;
        }
      }
      if(wordCanBeBuilt) return new String(curString);
    }
    return "";
  }

  //https://leetcode.com/problems/daily-temperatures/
  //beats 78% of java submissions
  public int[] dailyTemperatures(int[] T) {
    Queue<TupleWithIndex> lowValFirst = new PriorityQueue<>(new Comparator<TupleWithIndex>() {
      @Override
      public int compare(TupleWithIndex o1, TupleWithIndex o2) {
        return o1.value-o2.value;
      }
    });
    int[] results = new int[T.length];
    for(int i=T.length-1; i>=0; i--){
      while(!lowValFirst.isEmpty()&&lowValFirst.peek().value<=T[i]) lowValFirst.poll();
      if(lowValFirst.isEmpty()) results[i]=0;
      else{
        results[i]=lowValFirst.peek().index-i;
      }
      TupleWithIndex cur = new TupleWithIndex(T[i], i);
      lowValFirst.add(cur);
    }
    return results;
  }

  //rotate an array to the left d times
  static int[] rotLeft(int[] a, int d) {
    d = d%a.length;
    int[] result = new int[a.length];
    int index = 0;
    for(int i=d; i<a.length; i++){
      result[index]=a[i];
      index++;
    }
    for(int i=0; i<d; i++){
      result[index]=a[i];
      index++;
    }
    return result;
  }

  //https://leetcode.com/problems/network-delay-time
  //beats 62% of java submissions
  public int networkDelayTime(int[][] times, int N, int K) {
    Map<Integer, DjikstraNode> nodes = new HashMap<>();
    Queue<DjikstraNode> lowestDist = new PriorityQueue<>();


    for(int i=0; i<times.length; i++){
      int source = times[i][0];
      int dest = times[i][1];
      int weight = times[i][2];
      if(!nodes.containsKey(source)){
        DjikstraNode tmp = new DjikstraNode();
        nodes.put(source, tmp);
        lowestDist.offer(tmp);
      }
      if(!nodes.containsKey(dest)){
        DjikstraNode tmp = new DjikstraNode();
        nodes.put(dest, tmp);
        lowestDist.offer(tmp);
      }
      nodes.get(source).links.add(new int[] {dest, weight});
    }
    DjikstraNode startNode = nodes.get(K);
    lowestDist.remove(startNode);
    startNode.bestWeight=0;
    lowestDist.offer(startNode);

    Integer maxDelay = 0;
    int nodeCounter=0;
    while(!lowestDist.isEmpty()){
      DjikstraNode cur = lowestDist.poll();
      nodeCounter++;
      List<int[]> links = cur.links;
      for(int i=0; i<links.size(); i++){
        DjikstraNode dest = nodes.get(links.get(i)[0]);
        int weight = links.get(i)[1];
        if(lowestDist.contains(dest)&&weight+cur.bestWeight<dest.bestWeight){
          lowestDist.remove(dest);
          dest.bestWeight=cur.bestWeight+weight;
          lowestDist.offer(dest);
        }
      }
      maxDelay = cur.bestWeight>maxDelay? cur.bestWeight:maxDelay;
    }
    if(nodeCounter!=N) return -1;
    return maxDelay==Integer.MAX_VALUE?-1:maxDelay;
  }

  //given an array of values representing asteroids
  //with the sign representing movement direction and value representing size
  //a collision between a + and - of same size yields nothing
  //a collision between a smaller and bigger yields resulting value with sign of bigger
  //return array after all collisions have occured
  //https://leetcode.com/problems/asteroid-collision/
  public int[] asteroidCollision(int[] asteroids) {
    Deque<Integer> orderedVals = new ArrayDeque<>();
    for(int i=0; i<asteroids.length; i++){
      if(orderedVals.isEmpty()) orderedVals.push(asteroids[i]);
      else{
        if(orderedVals.peek()<0||asteroids[i]>0) orderedVals.push(asteroids[i]);
        else{
          int tmp = asteroids[i];
          while(tmp<0&&!orderedVals.isEmpty()&&orderedVals.peek()>0){
            if(-tmp>orderedVals.peek()) orderedVals.pop();
            else if(-tmp<orderedVals.peek()) tmp = orderedVals.pop();
            else {
              orderedVals.pop();
              tmp = 0;
            }
          }
          if(tmp!=0) orderedVals.push(tmp);
        }
      }
    }
    int[] result = new int[orderedVals.size()];
    for(int j=result.length-1; j>=0; j--){
      result[j]=orderedVals.pop();
    }
    return result;
  }

  //merge accounts where element 0 in list string is name
  //and the rest are emailSet associated with the name
  //duplicate accounts will have same name and at least one same email
  //https://leetcode.com/problems/accounts-merge/
  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<String, List<Integer>> nameToIndices = new HashMap<>();
    Map<Integer, Set<String>> indexToEmails = new HashMap<>();
    //we sort as name mapped to every index with that name
    //and index to the set of emailSet at that index
    for(int i=0; i<accounts.size(); i++){
      String name = accounts.get(i).get(0);
      if(nameToIndices.containsKey(name)){
        nameToIndices.get(name).add(i);
      }
      else{
        List<Integer> indices = new ArrayList<>();
        indices.add(i);
        nameToIndices.put(name, indices);
      }
      List<String> dupeIndices = new ArrayList<>();
      Set<String> indexEmails = new HashSet<>();
      for(int j=1; j<accounts.get(i).size(); j++){
        String email = accounts.get(i).get(j);
        if(indexEmails.contains(email)) dupeIndices.add(email);
        else indexEmails.add(email);
      }
      for(int k=0; k<dupeIndices.size(); k++){
        accounts.get(i).remove(dupeIndices.get(k));
      }
      indexToEmails.put(i, indexEmails);
    }
    List<List<String>> results = new ArrayList<>();
    //for every name, we look at all the indices associated with it
    for(String name: nameToIndices.keySet()){
      List<Integer> indices = nameToIndices.get(name);
      while(!indices.isEmpty()){
        Integer mergeIndex = indices.get(0);
        indices.remove(0);
        List<String> mergeList = accounts.get(mergeIndex);
        List<Integer> dupes = new ArrayList<>();
        for(int k=0; k<mergeList.size(); k++){
          String curEmail = mergeList.get(k);
          for(Integer index: indices){
            if(indexToEmails.get(index).contains(curEmail)){
              dupes.add(index);
            }
          }

          Set<String> mergeSet = indexToEmails.get(mergeIndex);
          for(Integer index: dupes){
            indices.remove(index);
            Set<String> dupeSet = indexToEmails.get(index);
            dupeSet.removeAll(mergeSet);
            for(String email: dupeSet){
              mergeList.add(email);
              mergeSet.add(email);
            }
          }
          dupes.clear();
        }
        //once we've checked every email against the index,
        //anything that remains is certain not to be a dupe
        mergeList.remove(0);
        mergeList.sort(Comparator.naturalOrder());
        mergeList.add(0, name);
        results.add(mergeList);
      }
    }
    return results;
  }

  //alternative solution to accounts merge, still using sets and maps, now map email->account
  public List<List<String>> accountsMerge2(List<List<String>> accounts) {
    Map<String, Account> emailToAccount = new HashMap<>();
    Set<Account> accountSet = new HashSet<>();
    for(int i=0; i<accounts.size(); i++){
      List<String> emails = accounts.get(i);
      String name = emails.get(0);
      Account curAccount = null;
      for(int j=1; j<emails.size(); j++){
        String email = emails.get(j);
        //first initializing of an account
        if(curAccount==null){
          if(emailToAccount.containsKey(email)){
            curAccount = emailToAccount.get(email);
          }
          else{
            curAccount = new Account(name);
            curAccount.emailSet.add(email);
            emailToAccount.put(email,curAccount);
          }
        }
        //if we're already working with an account
        else{
          if(!emailToAccount.containsKey(email)){
            curAccount.emailSet.add(email);
            emailToAccount.put(email, curAccount);
          }
          else{
            if(emailToAccount.get(email)!=curAccount){
              curAccount = mergeAccounts(emailToAccount.get(email), curAccount, emailToAccount, accountSet);
            }
          }
        }
      }
      accountSet.add(curAccount);
    }
    List<List<String>> results = new ArrayList<>(accountSet.size());
    for(Account acct: accountSet){
      List<String> acctAsList = new ArrayList<>(acct.emailSet.size()+1);
      acctAsList.add(acct.name);
      while(!acct.emailSet.isEmpty()) acctAsList.add(acct.emailSet.pollFirst());
      results.add(acctAsList);
    }
    return results;
  }

  private Account mergeAccounts(Account mainAcc, Account dupeAcc,
      Map<String, Account> emailToAccount, Set<Account> accountSet){
    dupeAcc.emailSet.removeAll(mainAcc.emailSet);
    for(String email: dupeAcc.emailSet) emailToAccount.put(email, mainAcc);
    mainAcc.emailSet.addAll(dupeAcc.emailSet);
    accountSet.remove(dupeAcc);
    return mainAcc;
  }

  public int dominantIndex(int[] nums) {
    int biggest = -1;
    int biggestIndex = -1;
    int secondBiggest = -1;
    for(int i=0; i<nums.length; i++){
      if(nums[i]>biggest){
        biggest=nums[i];
        biggestIndex=i;
      }
    }
    for(int i=0; i<nums.length; i++){
      if(i!=biggestIndex&&nums[i]>secondBiggest) secondBiggest=nums[i];
    }
    if(secondBiggest==-1) return biggestIndex;
    else if(secondBiggest*2<=biggest) return biggestIndex;
    return -1;
  }


  //given a matrix of color values (a picture), a coordinate (sr, sc) and a new color to paint it
  //color every contiguous block of same color the new color
  //beats 99.9% of java submissions despite being completely standard
  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    if(image==null||image.length==0||image[0].length==0) return image;
    boolean[][] coloredIn= new boolean[image.length][image[0].length];
    int srcColor = image[sr][sc];
    Queue<int[]> colorsToChange = new LinkedList<>();
    colorsToChange.offer(new int[] {sr, sc});
    while(!colorsToChange.isEmpty()){
      int[] curChange = colorsToChange.poll();
      int row = curChange[0];
      int col = curChange[1];
      coloredIn[row][col] = true;
      image[row][col] = newColor;
      if(row>0){
        if(image[row-1][col]==srcColor&&!coloredIn[row-1][col]){
          colorsToChange.add(new int[] {row-1, col});
        }
      }
      if(col>0){
        if(image[row][col-1]==srcColor&&!coloredIn[row][col-1]){
          colorsToChange.add(new int[] {row, col-1});
        }
      }
      if(row<image.length-1){
        if(image[row+1][col]==srcColor&&!coloredIn[row+1][col]){
          colorsToChange.add(new int[] {row+1, col});
        }
      }
      if(col<image[0].length-1){
        if(image[row][col+1]==srcColor&&!coloredIn[row][col+1]){
          colorsToChange.add(new int[] {row, col+1});
        }
      }
    }
    return image;
  }

  public boolean isOneBitCharacter(int[] bits) {
    boolean oneBitChar = false;
    int index = 0;
    while(index<bits.length){
      if(bits[index]==1){
        oneBitChar=false;
        index+=2;
      }
      else{
        oneBitChar=true;
        index++;
      }
    }
    return oneBitChar;
  }

  //given a linked-list, order so that all even numbered (0,2,4..) nodes
  //come before all odd numbered (1,3,5...) ones with O(1) space and O(N) time
  //3 ms for 71 test cases, beats 99% of java submissions
  public ListNode oddEvenList(ListNode head) {
    if(head == null || head.next == null) return head;

    ListNode evenHead = null;
    ListNode evenTail = null;
    ListNode oddHead = null;
    ListNode oddTail = null;

    int count = 0;
    while(head != null) {
      if(count%2 == 0){
        if(evenHead == null) {
          evenHead = head;
          evenTail = evenHead;
        } else {
          evenTail.next = head;
          evenTail = head;
        }
      } else {
        if(oddHead == null) {
          oddHead = head;
          oddTail = oddHead;
        } else {
          oddTail.next = head;
          oddTail = head;
        }
      }
      count++;
      head = head.next;
    }
    oddTail.next = null;
    evenTail.next = oddHead;
    return evenHead;
  }


  //using ascii representation of lower-case keys as a value from 97-122
  //given an arr of strings, find the max product of string lengths
  //where the two strings don't share any letters
  //modified version, got rid of sorting by string length
  //and all associated early stops
  //now beats 80% of java submissions
  public int maxProduct(String[] words) {
    if(words == null || words.length < 2) return 0;
    int[] bitWords = new int[words.length];
    int[] wordLens = new int[words.length];
    for(int i = 0; i < words.length; i++) {
      wordLens[i] = words[i].length();
      for(int j = 0; j < wordLens[i]; j++) {
        bitWords[i] |= 1 << words[i].charAt(j)-97;
      }
    }

    int biggestSum = 0;
    for(int i = 0; i < words.length; i++) {
      if(wordLens[i]==0) continue;
      for(int j = i+1; j < words.length; j++) {
        if((bitWords[i] ^ bitWords[j]) == (bitWords[i] | bitWords[j])) {
          if(biggestSum < wordLens[i]*wordLens[j]) biggestSum = wordLens[i] * wordLens[j];
        }
      }
    }
    return biggestSum;
  }

  //given an array of n integers and an int k, return the k most frequent elements
  //https://leetcode.com/problems/top-k-frequent-elements/description/
  //beats 98% of java runtimes, so I've gotten better since the last time I was doing these.
  public List<Integer> altTopKFrequent(int[] nums, int k) {
    Map<Integer, FrequencyCount> numCounts = new HashMap<>();
    for(int i = 0; i < nums.length; i++) {
      if(!numCounts.containsKey(nums[i])) {
        numCounts.put(nums[i], new FrequencyCount(nums[i], 1));
      } else {
        numCounts.get(nums[i]).count++;
      }
    }
    Queue<FrequencyCount> maxHeap = new PriorityQueue<>();
    for(FrequencyCount count: numCounts.values()){
      maxHeap.offer(count);
    }
    List<Integer> result = new ArrayList<>(k);
    for(int i = 0; i < k; i++) {
      result.add(i,maxHeap.poll().val);
    }
    return result;
  }

  class FrequencyCount implements Comparable<FrequencyCount>{
    int val;
    int count;
    public FrequencyCount(int val, int count){
      this.val = val;
      this.count = count;
    }

    public int compareTo(FrequencyCount f){
      return f.count - this.count;
    }
  }

  //given an array of ones and zeros, find max consecutive ones
  //this is almost too trivial to even commit
  public int findMaxConsecutiveOnes(int[] nums) {
    int maxOnes = 0;
    int tmpOnes = 0;
    for(int i = 0; i < nums.length; i++){
      if(nums[i] == 0) {
        maxOnes = maxOnes >= tmpOnes ? maxOnes : tmpOnes;
        tmpOnes = 0;
      } else {
        tmpOnes++;
      }
    }
    return maxOnes > tmpOnes ? maxOnes : tmpOnes;
  }

  //get the complement of a given number (but without flipping all leading zeros as ~ would do)
  //basic solution using string as an intermediary. Less than stellar (beat 14% of java submissions)
  public int findComplement(int num) {
    char[] binArr = Integer.toBinaryString(num).toCharArray();
    for(int i = 0; i < binArr.length; i++){
      if(binArr[i] == '1') binArr[i] = '0';
      else binArr[i] = '1';
    }
    return Integer.parseInt(new String(binArr), 2);
  }

  //fairly standard bit op solution
  //6 ms, beats 80% of java submissions (with 149 test cases)
  public int altFindComplement(int num) {
    int result = 0;
    int shift = 0;
    while(num != 0){
      if(num%2 == 0) result |= 1 << shift;
      shift++;
      num >>= 1;
    }
    return result;
  }

  //given a string enccded as num[string], possibly nested in itself
  //return the decoded string
  //pretty average solution, some overuse of StringBuilder
  //beats 40% of java submissions at 3 ms
  public String decodeString(String s) {
    return recursDecodeStr(s, new int[] {0}, 1);
  }


  //index passed with array wrapper so we can put updated index of closing bracket + 1 into it
  private String recursDecodeStr(String s, int[] index, int loops){
    StringBuilder sb = new StringBuilder();
    int i = index[0];
    while(i < s.length() && s.charAt(i) != ']') {
      if(Character.isDigit(s.charAt(i))) {
        int digitEnd = i+1;
        while(Character.isDigit(s.charAt(digitEnd))) digitEnd++;
        int loopCount = Integer.parseInt(s.substring(i, digitEnd));

        int[] updatedIndex = new int[] {digitEnd+1};
        sb.append(recursDecodeStr(s, updatedIndex, loopCount));
        i = updatedIndex[0];
      } else {
        sb.append(s.charAt(i));
        i++;
      }
    }
    String oneLoop = sb.toString();
    for(int j = 1; j < loops; j++){
      sb.append(oneLoop);
    }
    index[0] = ++i;
    return sb.toString();
  }

  //find mode in bst that can store duplicates
  //let's start off with a recursive solution that doesn't use extra memory storage
  //beats 56% of java submissions at 5 ms for 25 test cases, pretty average
  public int[] findModeInTree(TreeNode root) {
    if(root == null) return new int[] {};
    CountsOfVals counts = new CountsOfVals();
    int[] curValCount = new int[] {0, 0};
    recurFindMode(root, counts, curValCount);
    counts.offerVal(curValCount[0], curValCount[1]);

    int[] results = new int[counts.valList.size()];
    int i = 0;
    for(Integer val : counts.valList) {
      results[i] = val;
      i++;
    }
    return results;
  }

  private void recurFindMode(TreeNode root, CountsOfVals valCounts, int[] curValCount) {
    if(root.left != null) recurFindMode(root.left, valCounts, curValCount);
    if(curValCount[1] == 0){
      curValCount[0] = root.val;
      curValCount[1] = 1;
    }
    else {
      if(curValCount[0] == root.val) curValCount[1]++;
      else {
        valCounts.offerVal(curValCount[0], curValCount[1]);
        curValCount[0] = root.val;
        curValCount[1] = 1;
      }
    }
    if(root.right != null) recurFindMode(root.right, valCounts, curValCount);
  }

  class CountsOfVals{
    int count;
    List<Integer> valList;
    public CountsOfVals() {
      count = 0;
      valList = new LinkedList<>();
    }

    public void offerVal(int val, int count){
      if(this.count == count) valList.add(val);
      else if(this.count < count){
        this.count = count;
        valList.clear();
        valList.add(val);
      }
    }
  }

  //now let's do the same, but with a map for extra storage instead of the special data struct
  //aaand it runs worse
  public int[] altFindMode(TreeNode root) {
    if(root == null) return new int[] {};
    Map<Integer, Integer> valCounts  = new HashMap<>();
    altRecursFindMode(root, valCounts);
    List<Integer> topVals = new ArrayList<>();
    int topFreq = 0;
    for(Integer key: valCounts.keySet()){
      int freq = valCounts.get(key);
      if(freq == topFreq) topVals.add(key);
      else if(freq > topFreq){
        topVals.clear();
        topVals.add(key);
        topFreq = freq;
      }
    }
    int[] results = new int[topVals.size()];
    for(int i = 0; i < results.length; i++) {
      results[i] = topVals.get(i);
    }
    return results;
  }

  private void altRecursFindMode(TreeNode root, Map<Integer, Integer> valCounts){
    if(root.left != null) altRecursFindMode(root.left, valCounts);
    if(!valCounts.containsKey(root.val)) valCounts.put(root.val, 1);
    else valCounts.put(root.val, valCounts.get(root.val)+1);
    if(root.right != null) altRecursFindMode(root.right, valCounts);
  }

  //https://leetcode.com/problems/trapping-rain-water/description/
  //given an array of ints representing heights
  //after it rains, how much water will get trapped between these heights
  //going to try this approach - go through array, find any instance of tallest height
  //separate mirrored algorithm going left to right or right to left to this height
  //keep track of last tallest height until a height equal or greater is reached
  //once a height greater or equal is reached, calculate the water trapped as the
  //difference between those vals and the lesser of the two edge heights
  //then set the reached height as the new 'tallest height'
  //beats 72% of java submissions at 11 ms for 315 unit tests
  public int trap(int[] height) {
    if(height == null || height.length < 3) return 0;
    int tallestIndex = -1;
    int tallestVal = -1;
    for(int i = 0; i < height.length; i++) {
      if(height[i] > tallestVal) {
        tallestVal = height[i];
        tallestIndex = i;
      }
    }
    int collectedWater = 0;
    //left to right
    int leftIndex = 0;
    int leftHeight = height[leftIndex];
    for(int i = 1; i <= tallestIndex; i++) {
      if(height[i] >= leftHeight) {
        for(int j = leftIndex + 1; j < i; j++) {
          collectedWater += leftHeight - height[j];
        }
        leftIndex = i;
        leftHeight = height[i];
      }
    }

    //right to left
    int rightIndex = height.length-1;
    int rightHeight = height[rightIndex];
    for(int i = rightIndex - 1; i >= tallestIndex; i--){
      if(height[i] >= rightHeight) {
        for(int j = i+1; j < rightIndex; j++) {
          collectedWater += rightHeight - height[j];
        }
        rightIndex = i;
        rightHeight = height[i];
      }
    }

    return collectedWater;
  }

  //return sum of ones in an integer (treat as an unsigned integer)
  //1 ms for 600 unit tests, technically beats 100% of java submissions
  //despite the fact that they're at the same value
  public int hammingWeight(int n) {
    int sumOfOnes = 0;
    for(int i = 0; i < 32; i++){
      sumOfOnes =  (n & (1 << i)) == 0 ? sumOfOnes : sumOfOnes + 1;
    }
    return sumOfOnes;
  }

  //find total area of rectangles with potential overlap
  //(A,B), (C,D) and (E, F), (G, H) (A,B and E,F are the lowest leftest corner coords)
  //first step - establish if there is overlap in both x and y planes
  //second step - establish the size of the overlap
  //subtract overlap if any such exists, else return area of both rectangles
  //beats 100% of java submissions at 2 s for 2082 test cases
  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    int sumArea = (C-A)*(D-B) + (G-E)*(H-F);
    int xOverlap = 0;
    int yOverlap = 0;

    boolean eInside = A <= E && E <= C;
    boolean gInside = A <= G && G <= C;
    boolean xEncompassing = E < A && C < G;
    if(eInside && gInside) xOverlap = G - E;
    else if(eInside) xOverlap = C - E;
    else if(gInside) xOverlap = G - A;
    else if(xEncompassing) xOverlap = C - A;
    else return sumArea;

    boolean fInside = B <= F && F <= D;
    boolean hInside = B <= H && H <= D;
    boolean yEncompassing = F < B && D < H;
    if(fInside && hInside) yOverlap = H - F;
    else if(fInside) yOverlap = D - F;
    else if(hInside) yOverlap = H - B;
    else if(yEncompassing) yOverlap = D - B;
    else return sumArea;

    return sumArea - xOverlap*yOverlap;
  }

  //given an array of integers and a value s
  //find such the minimal number of contiguous elements
  //such that their sum is greater or equal to s
  //refactored to use subarray start index rather than queue
  //now is a bog-standard solution beating 50% of java submissions
  public int minSubArrayLen(int s, int[] nums) {
    int subArrStart = 0;
    int curSum = 0;
    int minElems = Integer.MAX_VALUE;
    for(int i = 0; i < nums.length; i++){
      curSum += nums[i];
      while(curSum - nums[subArrStart] >= s) curSum -= nums[subArrStart++];
      if(curSum >= s && i - subArrStart +1 < minElems) minElems = i - subArrStart +1;
    }
    return minElems == Integer.MAX_VALUE ? 0 : minElems;
  }

  //given an array where some values may constitute more than a third of the array
  //find such values if they exist
  //completely standard majority-voting algorithm beats 47% of java submissions
  public List<Integer> majorityElements(int[] nums) {
    int firstVal = 0;
    int firstCount = 0;
    int secondVal = 0;
    int secondCount = 0;
    for(int i = 0; i < nums.length; i++) {
      if(nums[i] == firstVal) {
        firstCount ++;
        continue;
      } else if(nums[i] == secondVal) {
        secondCount ++;
        continue;
      } else if(firstCount == 0) {
        firstVal = nums[i];
        firstCount = 1;
      } else if(secondCount == 0) {
        secondVal = nums[i];
        secondCount = 1;
      } else {
        firstCount--;
        secondCount--;
      }
    }

    //second step is verification
    firstCount = 0;
    secondCount = 0;
    for(int j = 0; j < nums.length; j++) {
      if(nums[j] == firstVal) firstCount++;
      else if(nums[j] == secondVal) secondCount++;
    }

    List<Integer> results = new ArrayList<>(2);
    if(firstCount*3 > nums.length) results.add(firstVal);
    if(secondCount*3 > nums.length) results.add(secondVal);
    return results;
  }


  //refactored dp solution
  //beat 67% of java submissions at 3 ms for 47 unit tests
  public int integerReplacement(int n) {
   Map<Double, Integer> dpMap = new HashMap<>();
   dpMap.put(1.0,0);
    return recurIntReplacement(n, dpMap);
  }

  private int recurIntReplacement(double n, Map<Double, Integer> dpMap) {
    if(dpMap.containsKey(n)) return dpMap.get(n);
    double val = n;
    int withMinus = 0;
    int withPlus = 0;
    int divByTwo = 0;
    if(val % 2 == 0){
      while(val % 2 == 0){
        val /= 2;
        divByTwo++;
      }
      int afterDivide = dpMap.containsKey(val)? dpMap.get(val) : recurIntReplacement(val, dpMap);
      dpMap.put(val, afterDivide);
      return afterDivide + divByTwo;
    } else {
      withMinus = dpMap.containsKey(val-1)? dpMap.get(val-1) : recurIntReplacement(val -1, dpMap);
      withPlus = dpMap.containsKey(val+1)? dpMap.get(val+1) : recurIntReplacement(val+1, dpMap);
    }
    int lesser = withMinus < withPlus ? withMinus : withPlus;
    lesser += divByTwo;
    dpMap.put(n, 1+lesser);
    return 1 + lesser;
  }

  //given a set of letters as a string, find the longest palindrome it is possible to build with it
  //much shorter, faster solution beats 95% of java submissions at 5 ms for 95 test cases
  public int longestPalindromeFromSet(String s) {
    int[] asciiCounts = new int[58];
    for(int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      asciiCounts[c - 65]++;
    }

    int palinLen = 0;
    boolean atLeastOneOdd = false;
    for(int j = 0; j < asciiCounts.length; j++){
      palinLen += asciiCounts[j] - asciiCounts[j] % 2;
      if(atLeastOneOdd == false && asciiCounts[j] % 2 == 1) atLeastOneOdd = true;
    }

    if(atLeastOneOdd) palinLen++;
    return palinLen;
  }

  //given an mxn matrix of values representing two oceans
  //with values as heights and water only able to flow down
  //find the set of values where water is able to reach both
  //the top/left and the bottom/right edges
  //https://leetcode.com/problems/pacific-atlantic-water-flow/description/
  //beats 93% of java submissions at 9 ms for 113 test cases
  public List<int[]> pacificAtlantic(int[][] matrix) {
    if(matrix ==  null || matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
    int[][] visitedBy = new int[matrix.length][matrix[0].length];
    for(int i = 0; i < matrix.length; i++) {
      if(visitedBy[i][0] == 0) pacificPourIn(matrix, visitedBy, i, 0);
    }
    for(int i = 1; i < matrix[0].length; i++) {
      if(visitedBy[0][i] == 0) pacificPourIn(matrix, visitedBy, 0, i);
    }

    List<int[]> result = new ArrayList<>();
    for(int i = 0; i < matrix.length; i++) {
      if(visitedBy[i][matrix[0].length-1] != 2){
        atlanticPourIn(matrix, visitedBy, i, matrix[0].length-1, result);
      }
    }
    for(int i = 0; i < matrix[0].length; i++) {
      if(visitedBy[matrix.length-1][i] != 2){
        atlanticPourIn(matrix, visitedBy, matrix.length-1, i, result);
      }
    }
    return result;
  }

  private void pacificPourIn(int[][] matrix, int[][] visited, int r, int c){
    int curVal = matrix[r][c];
    visited[r][c] = 1;
    if(r > 0 && curVal <= matrix[r-1][c] && visited[r-1][c] == 0) {
      pacificPourIn(matrix, visited, r-1, c);
    }
    if(c > 0 && curVal <= matrix[r][c-1] && visited[r][c-1] == 0) {
      pacificPourIn(matrix, visited, r, c-1);
    }
    if(r < matrix.length -1 && curVal <= matrix[r+1][c] && visited[r+1][c] == 0) {
      pacificPourIn(matrix, visited, r+1, c);
    }
    if(c < matrix[0].length -1 && curVal <= matrix[r][c+1] && visited[r][c+1] == 0) {
      pacificPourIn(matrix, visited, r, c+1);
    }
  }


  private void atlanticPourIn(int[][] matrix, int[][] visited, int r, int c, List<int[]> result) {
    if(visited[r][c]==1) result.add(new int[] {r, c});
    int curVal = matrix[r][c];
    visited[r][c] = 2;
    if(r > 0 && curVal <= matrix[r-1][c] && visited[r-1][c] != 2) {
      atlanticPourIn(matrix, visited, r-1, c, result);
    }
    if(c > 0 && curVal <= matrix[r][c-1] && visited[r][c-1] != 2) {
      atlanticPourIn(matrix, visited, r, c-1, result);
    }
    if(r < matrix.length -1 && curVal <= matrix[r+1][c] && visited[r+1][c] != 2) {
      atlanticPourIn(matrix, visited, r+1, c, result);
    }
    if(c < matrix[0].length -1 && curVal <= matrix[r][c+1] && visited[r][c+1] != 2) {
      atlanticPourIn(matrix, visited, r, c+1, result);
    }
  }

  //given two numbers as linked linsts of digits (most significant digit at start of list)
  //add the two numbers
  //bog-standard solution beats 58% of java submissions at 31 ms for 1563 test cases
  //low readability code though
  public ListNode addTwoNumbersList(ListNode l1, ListNode l2) {
    Stack<ListNode> l1Stack = new Stack<>();
    Stack<ListNode> l2Stack = new Stack<>();

    ListNode tmp1 = l1;
    while(tmp1 != null) {
      l1Stack.push(tmp1);
      tmp1 = tmp1.next;
    }
    ListNode tmp2 = l2;
    while(tmp2 != null) {
      l2Stack.push(tmp2);
      tmp2 = tmp2.next;
    }

    boolean l1Longer = l1Stack.size() >= l2Stack.size() ? true : false;
    boolean carryOne = false;
    int curSum = 0;

    while(!l1Stack.isEmpty() && !l2Stack.isEmpty()) {
      curSum = l1Stack.peek().val + l2Stack.peek().val;
      curSum += carryOne? 1 : 0;
      carryOne = false;
      if(curSum > 9){
        carryOne = true;
        curSum %= 10;
      }

      if(l1Longer) l1Stack.peek().val = curSum;
      else l2Stack.peek().val = curSum;
      l1Stack.pop();
      l2Stack.pop();
    }

    if(l1Stack.isEmpty() && l2Stack.isEmpty()){
      if(carryOne) {
        ListNode head = new ListNode(1);
        head.next = l1Longer? l1 : l2;
        return head;
      } else return l1Longer? l1 : l2;
    } else {
      Stack<ListNode> remStack = l1Longer? l1Stack : l2Stack;
      if(carryOne) {
        while(!remStack.isEmpty() && carryOne) {
          int curVal = remStack.peek().val;
          curVal++;
          if(curVal  < 10) carryOne = false;
          else curVal %= 10;
          remStack.pop().val = curVal;
        }
        if(!carryOne) return l1Longer ? l1 : l2;
        else{
          ListNode head = new ListNode(1);
          head.next = l1Longer? l1 : l2;
          return head;
        }
      } else {
        return l1Longer? l1 : l2;
      }
    }
  }

  //given an array of size n of integers with values 1 <= a[i] <= n
  //find all duplicate values with constant extra space use
  //not the fastest solution (beats 58% of java submissions) but is constant space use
  public List<Integer> findDuplicates(int[] nums) {
    List<Integer> result = new ArrayList<>();

    for(int i = 0; i < nums.length; i++){
      if(nums[i] != i+1) {
        int storedVal = nums[i];
        nums[i] = -1;
        while(nums[storedVal-1] != storedVal && nums[storedVal-1] != -1){
          int newVal = nums[storedVal-1];
          nums[storedVal-1] = storedVal;
          storedVal = newVal;
        }

        if(nums[storedVal-1] == -1) nums[storedVal-1] = storedVal;
        else result.add(storedVal);
      }
    }
    return result;
  }

  //given a board of battleships (marked as x, no adjacent battleships)
  //count the number of battleships in a single pass without modifying the board
  //with constant space
  //constant space solution, but the hashmap slows it down significantly
  //beats only 12% of solutions (4 ms for 28 test cases) primarily due to hashmap
  //that is the constant space tradeoff
  public int countBattleships(char[][] board) {
    Map<Integer, Boolean> vertShips = new HashMap<>();
    int result = 0;
    final char SHIP = 'X';
    for(int i = 0; i < board.length; i++) {
      for(int j = 0; j < board[0].length; j++) {
        char curChar = board[i][j];
        if(curChar != SHIP) continue;
        if(j+1 < board[0].length && board[i][j+1] == SHIP){
          result++;
          while(j < board[0].length && board[i][j] == SHIP) j++;
          continue;
        }
        if(vertShips.containsKey(j)) {
          if(i+1 == board.length || board[i+1][j] != SHIP){
            result++;
            vertShips.remove(j);
            continue;
          }
        } else if (i + 1 < board.length && board[i+1][j] == SHIP){
          vertShips.put(j, true);
          continue;
        } else result++;
      }
    }
    return result;
  }

  //to illustrate that it's a space-time tradeoff, let's do the same thing
  //but ignore constant space constraints
  //beats 100% of java submissions at 2 ms for 28 test cases
  //but uses O(M) extra space where M is the number of columns on the board
  //https://leetcode.com/problems/battleships-in-a-board/
  public int altCountBattleships(char[][] board) {
    boolean[] vertShip = new boolean[board[0].length];
    int result = 0;
    final char SHIP = 'X';
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        char curChar = board[i][j];
        if (curChar != SHIP)
          continue;
        if (j + 1 < board[0].length && board[i][j + 1] == SHIP) {
          result++;
          while (j < board[0].length && board[i][j] == SHIP)
            j++;
          continue;
        }
        if (vertShip[j]) {
          if (i + 1 == board.length || board[i + 1][j] != SHIP) {
            result++;
            vertShip[j] = false;
            continue;
          }
        } else if (i + 1 < board.length && board[i + 1][j] == SHIP) {
          vertShip[j] = true;
          continue;
        } else
          result++;
      }
    }
    return result;
  }

  //count number of space-separated segments in string
  //worth noting that string.split fails here
  //https://leetcode.com/problems/number-of-segments-in-a-string/description/
  //beats 100% of java submissions at 1 ms for 26 test cases
  public int countSegments(String s) {
    int segmentCount = 0;
    boolean inSegment = false;
    char curChar = ' ';
    for(int i = 0; i < s.length(); i++) {
      curChar = s.charAt(i);
      if(inSegment){
        if(curChar == ' ') inSegment = false;
      } else if(curChar != ' ') {
        inSegment = true;
        segmentCount++;
      }
    }
    return segmentCount;
  }


  //there was a meme about doing this wrong on r/programmerhumor and I wanted to try to do it right
  //the reason I only do half of each line is that I timed it and it's ~twice as fast
  public String[] drawFigure(int size, char whitespace, char mark){
    String[] results = new String[size];
    int halfSize = size%2==0?size/2:size/2+1;
    char[] curStr = new char[size];

    for(int i=0; i<halfSize; i++){
      for(int j=0; j<halfSize; j++){
        if(i==0 || j==0 || i==j || i==size-1-j){
          curStr[j] = mark;
          curStr[size-1-j] = mark;
        } else {
          curStr[j] = whitespace;
          curStr[size-1-j] = whitespace;
        }
      }
      results[i] = new String(curStr);
    }
    for(int i=0; i<halfSize; i++){
      results[size-1-i] = results[i];
    }
    return results;
  }

  //given a bag of n white gloves and n black gloves
  //grabbing two random gloves out at a time
  //if two same colored gloves are grabbed, a black glove is added to the bag
  //otherwise, a white colored glove is added to the bag
  //what is the color of the last glove
  public char gloveBag(int white, int black){
    char[] bag = new char[white+black];
    Random rnd = new Random();

    for(int i=0; i<white; i++){
      bag[i] = 'w';
    }
    for(int i=white; i<white+black; i++){
      bag[i] = 'b';
    }
    for(int i=0; i<white+black-1; i++){
      char tmp = bag[white+black-1-i];
      int rndInd = rnd.nextInt(white+black-i);
      bag[white+black-1-i] = bag[rndInd];
      bag[rndInd] = tmp;
    }

    int remGlvs = white+black;
    while(remGlvs>1){
      int rndVal = rnd.nextInt(remGlvs-1);
      char first = bag[rndVal];
      bag[rndVal] = bag[remGlvs-1];
      rndVal = rnd.nextInt(remGlvs-1);
      if(bag[rndVal]==first){
        bag[rndVal] = 'b';
      } else {
        bag[rndVal] = 'w';
      }
      remGlvs--;
    }
    return bag[0];
  }
}
