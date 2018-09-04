package com.eldar;

import java.util.*;
import java.util.stream.Stream;

/*
//Class to store the various methods implemented over the course of getting interview
//interview problem urls listed
//occasionally, numerous solutions (generally a naive one to structure thoughts and an actually good one) are listed
//each problem prefaced with a commented description
*/

public class ProblemSolutions {

    //Given an array of ints, find whether the provided value exists as a sum of two values in the array
    //http://www.techiedelight.com/find-pair-with-given-sum-array/
    //will return list of int pairs, if no values found, list will be empty
    public List<int[]> naiveSumInArray(int[] array, int sum){
        List<int[]> results = new LinkedList<>();
        for(int i = 0; i < array.length; i++){
            for(int j = i+1; j < array.length; j++){
                if(array[i] + array[j] == sum){
                    int[] indices = {i, j};
                    results.add(indices);
                }
            }
        }
        return results;
    }
    public List<int[]> hashSumInArray(int[] array, int sum){
        List<int[]> results = new LinkedList<>();
        Map<Integer, Integer> indecesOfValues = new HashMap<>();
        for(int i = 0; i < array.length; i++){
            indecesOfValues.put(array[i], i);
        }

        Map<Integer, Integer> alreadyAddedPairs = new HashMap<>();
        for(int i = 0; i < array.length; i++)
        {
            int soughtValue = sum - array[i];
            if(indecesOfValues.containsKey(soughtValue) && i != indecesOfValues.get(soughtValue)){
                int[] indices = {i, indecesOfValues.get(soughtValue)};

                if(!alreadyAddedPairs.containsKey(indices[0]) || alreadyAddedPairs.get(indices[0])!=indices[1]){
                    alreadyAddedPairs.put(indices[1], indices[0]);
                    results.add(indices);
                }
            }
        }
        return results;
    }

    //Given a string of distinct characters J, return a count of those characters appearing in string S
    //https://leetcode.com/problems/jewels-and-stones/description/
    public int hashCountJewels(String J, String S){
        Map<Character, Boolean> jewelTypes = new HashMap<>();

        for(char ch : J.toCharArray()){
            jewelTypes.put(ch, true);
        }
        int count = 0;
        for(char ch: S.toCharArray()){
            count = jewelTypes.containsKey(ch) ? count+1 : count;
        }
        return count;
    }
    //all characters are guaranteed to be letters, so let's try using this to our advantage
    //lowercase z is number 122, uppercase A is 65, difference is 57, 58 elements though since it starts at 0
    //solution runtime faster than 93.32% other Java solutions, alright
    public int asciiCountJewels(String J, String S){
        boolean[] asciiCounts = new boolean[58];
        for(int i = 0; i < J.length(); i++){
            asciiCounts[(int)J.charAt(i)-65] = true;
        }
        int count = 0;
        for(int j = 0; j < S.length(); j++){
            count = asciiCounts[(int)S.charAt(j)-65] ? count+1 : count;

        }
        return count;
    }

    //given a 2d array of positive ints, find by how much all the elements in the array can be increased
    //while preserving the maximum value of each row and column
    //https://leetcode.com/problems/max-increase-to-keep-city-skyline/description/
    public int maxIncreaseInSkyline(int[][] grid){
        int vertArrHeight = grid.length;
        int horizArrHeight = grid[0].length;
        int[] maxVertHeights = new int[vertArrHeight];
        int[] maxHorizHeights = new int[horizArrHeight];

        for(int i = 0; i < vertArrHeight; i++){
            for(int j = 0; j < vertArrHeight; j++){
                int heightInCell = grid[i][j];
                maxVertHeights[i] = maxVertHeights[i] < heightInCell ? heightInCell : maxVertHeights[i];
                maxHorizHeights[j] = maxHorizHeights[j] < heightInCell ? heightInCell : maxHorizHeights[j];
            }
        }
        int sumOfExtraHeights = 0;
        for(int i = 0; i < vertArrHeight; i++){
            for(int j = 0; j < vertArrHeight; j++){
                int heightInCell = grid[i][j];
                if(maxVertHeights[i] > maxHorizHeights[j]){
                    sumOfExtraHeights += maxHorizHeights[j] - heightInCell;
                }
                else{
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
    public int uniqueMorseCodeRepresentations(String[] words){
        int count = 0;
        String[] morseCode = {".-","-...","-.-.","-..",".","..-.","--.","....", "..",".---",
                "-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--",
                "-..-","-.--","--.."};
        Map<String, Boolean> wordAlreadyOccured = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            StringBuilder morseString = new StringBuilder();
            for(int j = 0; j < words[i].length(); j++){
                morseString.append(morseCode[(int)words[i].charAt(j)-97]);
            }
            if(!wordAlreadyOccured.containsKey(morseString.toString())){
                count++;
                wordAlreadyOccured.put(morseString.toString(),true);
            }
        }
        return count;
    }

    //calculate sum of a and b without using + or -
    //using bit operations is pretty straightforward here
    //https://leetcode.com/problems/sum-of-two-integers/
    public int sumWithoutOperators(int a, int b){
        int intermed;
        while(b!=0)
        {
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
    public boolean returnChange(int[] bills){
        boolean possibleToReturnChange = true;
        int tens = 0;
        int fives = 0;
        int iterator = 0;
        while(iterator < bills.length && possibleToReturnChange){
            if(bills[iterator] == 5){
                fives++;
            }
            else if(bills[iterator] == 10){
                if(fives==0){
                    possibleToReturnChange = false;
                }
                else{
                    tens++;
                    fives--;
                }
            }
            else{
                if(tens>0&&fives>0){
                    tens--;
                    fives--;
                }
                else if(fives>2){
                    fives -=3;
                }
                else{
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
    public List<Integer> topKFrequent(int[] nums, int k){
        NumCount[] mostFrequentArr = new NumCount[nums.length];
        int lastUnfilledIndex = 0;
        Map<Integer, NumCount> occuringNumsMap = new HashMap<>();
        int maxCount = 0;
        for(int i=0; i<nums.length; i++) {
            if (!occuringNumsMap.containsKey(nums[i])) {
                NumCount newVal = new NumCount(nums[i], 1, lastUnfilledIndex);
                occuringNumsMap.put(nums[i], newVal);
                mostFrequentArr[lastUnfilledIndex] = newVal;
                lastUnfilledIndex++;
            } else {
                NumCount curVal = occuringNumsMap.get(nums[i]);
                curVal.count++;
                while(curVal.index != 0 && curVal.count > mostFrequentArr[curVal.index-1].count){
                    int curIndex = curVal.index;
                    int newIndex = curVal.index-1;
                    NumCount temp = curVal;
                    mostFrequentArr[curIndex] = mostFrequentArr[newIndex];
                    mostFrequentArr[curIndex].index = curIndex;
                    mostFrequentArr[newIndex] = curVal;
                    curVal.index = newIndex;
                }
            }
        }
        List<Integer> results = new LinkedList<>();
        for(int i = 0; i < k; i++)
        {
            results.add(mostFrequentArr[i].value);
        }
        return results;
    }
    class NumCount{
        int index;
        int count;
        int value;
        public NumCount(int value, int count, int index){
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
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return recursBuildTree(0, nums.length-1, nums);
    }
    private TreeNode recursBuildTree(int leftIndex, int rightIndex, int[] nums){
        int maxVal = nums[leftIndex];
        int maxValIndex = leftIndex;
        if(leftIndex == rightIndex){
            return new TreeNode(nums[leftIndex]);
        }
        else{
            for(int i = leftIndex; i <= rightIndex; i++){
                if(maxVal < nums[i]){
                    maxVal = nums[i];
                    maxValIndex = i;
                }
            }
        }

        TreeNode curNode = new TreeNode(maxVal);
        curNode.left = maxValIndex == leftIndex ? null : recursBuildTree(leftIndex, maxValIndex-1, nums);
        curNode.right = maxValIndex == rightIndex? null : recursBuildTree(maxValIndex+1, rightIndex, nums);
        return curNode;
    }

    //given a two dimensional matrix of ones and zeros, flip it horizontally, then invert the values
    //https://leetcode.com/problems/flipping-an-image
    //it's just a test of understanding java 2d matrices
    //runtime better than 100% of submissions
    public int[][] flipAndInvertImage(int[][] A) {
        int columnLen = A[0].length;
        boolean oddColumnNum = columnLen%2 == 1;
        int tmp = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < (columnLen/2); j++){
                tmp = A[i][j];
                A[i][j] = 1^A[i][columnLen-1-j];
                A[i][columnLen-1-j] = 1^tmp;
            }
            if(oddColumnNum){
                A[i][(columnLen/2)] = 1^A[i][(columnLen/2)];
            }
        }
        return A;
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
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<ListNode> listNodeLinkedList = new LinkedList<>();
        boolean carryone = false;
        ListNode curL1 = l1;
        ListNode curL2 = l2;
        while(curL1 != null || curL2 != null){
            boolean firstListDone = curL1 == null;
            boolean secondListDone = curL2 == null;
            int initVal = 0;
            if(!firstListDone && !secondListDone){
                initVal = curL1.val + curL2.val;
            }
            else if(firstListDone && !secondListDone){
                initVal = curL2.val;
            }
            else if(!firstListDone && secondListDone){
                initVal = curL1.val;
            }
            initVal = carryone ? initVal + 1 : initVal;
            carryone = initVal > 9;
            initVal = initVal%10;
            listNodeLinkedList.add(new ListNode(initVal));
            curL1 = curL1 == null? null : curL1.next;
            curL2 = curL2 == null? null : curL2.next;
        }
        if(carryone){listNodeLinkedList.add(new ListNode(1));}
        for(int i = 0; i < listNodeLinkedList.size()-1; i++){
            listNodeLinkedList.get(i).next = listNodeLinkedList.get(i+1);
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
    while(substrEndIndex < s.length()){
      char curChar = s.charAt(substrEndIndex);
      if(asciiVals[curChar]==-1){
        asciiVals[curChar] = substrEndIndex;
        substrLen++;
        result = substrLen > result ? substrLen : result;
        substrEndIndex++;
      }
      else{
        int repeatIndex = asciiVals[curChar];
        for(int i = startIndex; i <= repeatIndex; i++){
          asciiVals[s.charAt(i)] = -1;
          substrLen--;
        }
        startIndex = repeatIndex+1;
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
        if (strAsCharArr[i-1] == strAsCharArr[i+1]) {
          int tempPalinLen = 3;
          int left = i-1;
          int right = i+1;
          while(left>0&&right<strAsCharArr.length-1){
            if(strAsCharArr[left-1]==strAsCharArr[right+1]){
              tempPalinLen+=2;
              left--;
              right++;
            }
            else{break;}
          }
          if(longestPalinLen<tempPalinLen){
            longestPalinLen = tempPalinLen;
            leftPalinIndex = left;
            rightPalinIndex = right;
          }
        }
      }
      if(i<strAsCharArr.length-1){
        if(strAsCharArr[i]==strAsCharArr[i+1]){
          int tempPalinLen = 2;
          int left = i;
          int right = i+1;
          while(left>0&&right<strAsCharArr.length-1){
            if(strAsCharArr[left-1]==strAsCharArr[right+1]){
              tempPalinLen += 2;
              left--;
              right++;
            }
            else{break;}
          }
          if(longestPalinLen < tempPalinLen){
            longestPalinLen = tempPalinLen;
            leftPalinIndex = left;
            rightPalinIndex = right;
          }
        }
      }
    }
    return s.substring(leftPalinIndex, rightPalinIndex+1);
  }

  //convert zig-zag text (string, with number of rows it's spread on)
  //into the resulting flat string with rows read top to bottom
  //https://leetcode.com/problems/zigzag-conversion/description/
  //text first goes down, with 1 per row, then up diagonally, and repeat
  public String zigZagConvert(String s, int numRows) {
    if(numRows==1){return s;}
    List<StringBuilder> rowStrings = new ArrayList<>(numRows);
    char[] charVerOfStr = s.toCharArray();
    for(int i = 0; i < numRows; i++){
      rowStrings.add(i, new StringBuilder(""));
    }
    boolean goingDown = false;
    boolean goingUpDiagonally = false;
    int rowPtr = 0;
    for(int j = 0; j < charVerOfStr.length; j++){
      if(rowPtr == 0){
        goingDown = true;
        goingUpDiagonally = false;
      }
      else if(rowPtr == numRows-1){
        goingDown = false;
        goingUpDiagonally = true;
      }
      if(goingDown){
        rowStrings.get(rowPtr).append(charVerOfStr[j]);
        rowPtr++;
      }
      else if(goingUpDiagonally){
        rowStrings.get(rowPtr).append(charVerOfStr[j]);
        rowPtr--;
      }
    }
    StringBuilder result = new StringBuilder("");
    for(int k = 0; k < rowStrings.size(); k++){
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
    int startVal = intAsChArr.length()-1;
    char[] reverseChArr = new char[startVal-endVal+1];
    int secondaryIndex = 0;
    for(int j = startVal; j >= endVal; j--){
      reverseChArr[secondaryIndex] = intAsChArr.charAt(j);
      secondaryIndex++;
    }
    long absResult = Long.parseLong(new String(reverseChArr));
    if(absResult>Integer.MAX_VALUE){ return 0;}
    return negative ? (int)-absResult : (int) absResult;
  }
  //parse an int from a string that may contain words or other noise;
  //
  public int myAtoi(String str) {
    if(str.length()==0){return 0;}
    int startOfNum = 0;
    int endOfNum = 0;
    boolean startFound = false;
    boolean endFound = false;
    int indexPtr = 0;
    boolean initialSignFound = false;
    boolean initialMinus = false;
    while(!startFound&&indexPtr<str.length()) {
      char ch = str.charAt(indexPtr);
      if((ch=='-'||ch=='+')&&indexPtr+1<str.length()&&Character.isDigit(str.charAt(indexPtr+1))){
        if(ch=='-'){initialMinus=true;}
        startFound = true;
        startOfNum = indexPtr;
        initialSignFound = true;
      }
      else if(!Character.isDigit(ch)&&!(ch==' ')){return 0;}
      else if(Character.isDigit(str.charAt(indexPtr))){
        startFound = true;
        startOfNum = indexPtr;
      }
      else{
        indexPtr++;
        startOfNum++;
      }
    }
    endOfNum = initialSignFound? ++indexPtr : indexPtr;
    while(!endFound&&indexPtr<str.length()){
      char ch = str.charAt(indexPtr);
      if(Character.isDigit(ch)){
        indexPtr++;
        endOfNum++;
      }
      else{
        endFound = true;
      }
    }
    if(startOfNum>=endOfNum){return 0;}
    long result = Long.MAX_VALUE;
    try{
      result = Long.parseLong(str.substring(startOfNum, endOfNum));
    }
    catch(Exception e){
      return initialMinus? Integer.MIN_VALUE: Integer.MAX_VALUE;
    }
    if(result>Integer.MAX_VALUE){return Integer.MAX_VALUE;}
    if(result<Integer.MIN_VALUE){return Integer.MIN_VALUE;}
    return (int)result;
  }

  //determine if a number is a palindrome. Signs matter
  //https://leetcode.com/problems/palindrome-number/
  public boolean isNumAPalindrome(int x) {
    String str = Integer.toString(x);
    for(int i = 0; i < str.length()/2; i++){
      if(str.charAt(i)!=str.charAt(str.length()-i-1)){return false;}
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
    int rightPtr = height.length-1;
    while(leftPtr!=rightPtr){
      boolean leftHeightSmaller = height[leftPtr] < height[rightPtr];
      int tmpArea = leftHeightSmaller ? height[leftPtr]*(rightPtr-leftPtr) : height[rightPtr] * (rightPtr-leftPtr);
      if(tmpArea > curMaxArea){curMaxArea = tmpArea;}
      if(leftHeightSmaller){
        leftPtr++;
      }
      else{
        rightPtr--;
      }
    }
    return curMaxArea;
  }

  //find longest common prefix in a string array
  //return empty string if no common prefix
  //https://leetcode.com/problems/longest-common-prefix/description/
  public String longestCommonPrefix(String[] strs) {
    if(strs.length<1){return "";} //in reality ought to throw an IllegalArgumentException for empty array
    int maxPotentialPrefixLen = strs[0].length();
    for(int i = 1; i < strs.length; i++){
      maxPotentialPrefixLen = maxPotentialPrefixLen > strs[i].length()? strs[i].length() : maxPotentialPrefixLen;
      int counter = maxPotentialPrefixLen-1;
      while(counter>=0){
        if(strs[0].charAt(counter)!=strs[i].charAt(counter)){
          maxPotentialPrefixLen = counter;
        }
        counter--;
      }
      if(maxPotentialPrefixLen==0){return "";}
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
    for(int i = 0; i < nums.length-2; i++){
      if(i==0||nums[i]!=nums[i-1]){
        int leftPtr = i+1;
        int rightPtr = nums.length-1;
        int soughtVal = 0 - nums[i];
        while(leftPtr < rightPtr){
          if(nums[leftPtr]+nums[rightPtr]==soughtVal){
            List<Integer> tmp = new LinkedList<>();
            tmp.add(nums[i]);
            tmp.add(nums[leftPtr]);
            tmp.add(nums[rightPtr]);
            results.add(tmp);
            while(leftPtr < rightPtr && nums[leftPtr]==nums[leftPtr+1]){leftPtr++;}
            while(leftPtr < rightPtr && nums[rightPtr]==nums[rightPtr-1]){rightPtr--;}
            leftPtr++;
            rightPtr--;
          } else if(nums[leftPtr]+nums[rightPtr] < soughtVal){
            leftPtr++;
          }
          else{
            rightPtr--;
          }
        }
      }
    }
    return results;
  }

  //given a stream of integers, calculate the median value
  public double medianOfIntStream(Stream<Integer> intStream){
    Queue<Integer> minHeap = new PriorityQueue<>();
    Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    intStream.forEach(x -> updateMedian(x, minHeap, maxHeap));
    if(minHeap.size()==maxHeap.size()){
      return (double)(minHeap.peek()+maxHeap.peek())/2.0;
    }
    else if (minHeap.size()>maxHeap.size()){
      return (double)minHeap.peek();
    }
    else{
      return (double)maxHeap.peek();
    }
  }
  private void updateMedian(int x, Queue<Integer> minHeap, Queue<Integer> maxHeap){
    minHeap.offer(x);
    maxHeap.offer(minHeap.poll());
    if(maxHeap.size()!=minHeap.size()){
      minHeap.offer(maxHeap.poll());
    }
  }

  //given a string consisting of digits 2-9, with each digit corresponding to up to 4 letters
  //(as one might see on a home phone), return an array of strings consisting of all possible
  //letter combinations resulting from the digits
  //https://leetcode.com/problems/letter-combinations-of-a-phone-number/
  public List<String> letterCombinations(String digits) {
    if(digits.length()==0) {
      return new LinkedList<>();
    }
    Map<Character,String> combinations = new HashMap<>();
    combinations.put('2', "abc");
    combinations.put('3', "def");
    combinations.put('4', "ghi");
    combinations.put('5', "jkl");
    combinations.put('6', "mno");
    combinations.put('7', "pqrs");
    combinations.put('8', "tuv");
    combinations.put('9', "wxyz");

    int totalStringNum = 1;
    for(int i = 0; i < digits.length(); i++){
      totalStringNum *= combinations.get(digits.charAt(i)).length();
    }

    char[][] resultsAsChArr = new char[totalStringNum][digits.length()];
    int digitsIndex = 0;
    while(digitsIndex < digits.length()){
      char tmpChar = digits.charAt(digitsIndex);
      String tmpStr = combinations.get(tmpChar);
      int repeatsPerChar = totalStringNum/tmpStr.length();
      int letterIndex = 0;
      int letterRepeatCount = 0;
      for(int i = 0; i < resultsAsChArr.length; i++){
        resultsAsChArr[i][digitsIndex] = tmpStr.charAt(letterIndex);
        letterRepeatCount++;
        if(letterRepeatCount==repeatsPerChar){
          letterRepeatCount = 0;
          letterIndex = letterIndex+1==tmpStr.length()? 0 : letterIndex+1;
        }
      }
      totalStringNum/=tmpStr.length();
      digitsIndex++;
    }
    List<String> results = new LinkedList<>();
    for(int k = 0; k < resultsAsChArr.length; k++){
      results.add(new String(resultsAsChArr[k]));
    }
    return results;
  }


  //remove nth from the end list node in linear time
  //given a singly-linked list (e.g. the head of a linked list)
  //https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
  //beats 99.9% of java submissions.
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if(head.next==null){return null;}
    ListNode pointerToNth = head;
    ListNode curNode = head;
    int iterator = 0;
    while(curNode.next!=null){
      curNode = curNode.next;
      iterator++;
      if(iterator > n){
        pointerToNth = pointerToNth.next;
      }
    }
    //all of that works with the premise that the curNode has a chance to move at least once
    //if the ith element is the head, just return head.next
    if(iterator+1==n){return head.next;}
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
    boolean pointerAtZero = true;
    for(char c : s.toCharArray()){
      pointerAtZero = ptr==0;
      switch(c){
        case '(':
        case '{':
        case '[':
          stack[ptr++] = c;
          break;
        case ')':
          if(pointerAtZero||stack[--ptr]!='('){return false;}
          break;
        case '}':
          if(pointerAtZero||stack[--ptr]!='{'){return false;}
          break;
        case ']':
          if(pointerAtZero||stack[--ptr]!='['){return false;}
          break;
      }
    }
    return ptr==0;
  }

  //merge k sorted lists of listnodes (given head nodes in an array)
  //https://leetcode.com/problems/merge-k-sorted-lists/description/
  //took three revisions, but it beats 70% of java submissions
  public ListNode mergeKLists(ListNode[] lists) {
    if(lists.length==0){return null;} //really should thrown InvalidArgumentException
    int listArrMax = lists.length;
    int listIndex = 0;
    int firstListFoundIndex = 0;
    boolean firstListFound = false;
    ListNode currentList = null;
    while(listArrMax>1){
      for(int i = 0; i < listArrMax; i++){
        if(lists[i]==null){continue;}
        else if(firstListFound){
          lists[listIndex++]= merge2Lists(lists[firstListFoundIndex], lists[i]);
          firstListFound = false;
        }
        else{
          firstListFoundIndex = i;
          firstListFound = true;
        }
      }
      if(firstListFound){
        lists[listIndex++] = lists[firstListFoundIndex];
      }
      firstListFound = false;
      firstListFoundIndex = 0;
      listArrMax = listIndex;
      listIndex = 0;
    }
    return lists[0];
  }
  private ListNode merge2Lists(ListNode l1, ListNode l2){
    if(l1==null&&l2==null){return null;}
    else if(l1==null){return l2;}
    else if(l2==null){return l1;}
    ListNode head = null;
    ListNode cur = null;
    if(l1.val < l2.val){
      head = l1;
      cur = head;
      l1 = l1.next;
    }
    else{
      head = l2;
      cur = head;
      l2 = l2.next;
    }
    while(l1!=null&&l2!=null){
      if(l1.val < l2.val){
        cur.next = l1;
        cur = l1;
        l1 = l1.next;
      }
      else{
        cur.next = l2;
        cur = l2;
        l2 = l2.next;
      }
    }
    if(l1==null){
      cur.next = l2;
    }
    if(l2==null){
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
    while(cur.next!=null&&cur.next.next!=null){
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
    if(nums.length<2){return nums.length;}
    int duplWriteIndex = 1;
    //given that we know nothing of the range of values
    //and thus can't simply use a new array's index positions
    //we choose to use a hashmap
    for(int i = 1; i < nums.length; i++){
      if(!(nums[i]==nums[i-1])){
        nums[duplWriteIndex]=nums[i];
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
  private void combineInts(int[] candidates, List<Integer> curList, List<List<Integer>> fullList, int target, int startPos){
    if(target==0){
      fullList.add(new ArrayList<>(curList));
    }
    else if(target<0){return;}
    else{
      for(int i = startPos; i < candidates.length; i++){
        curList.add(candidates[i]);
        combineInts(candidates, curList, fullList, target-candidates[i], i);
        curList.remove(curList.size()-1);
      }
    }
    return;
  }
  //given a double x and int n, return x to the nth power
  //x is between 100 and -100
  //https://leetcode.com/problems/powx-n/description/
  //ultimately the fastest solution was to use the "egyptian/russian/peasant" method
  //with bitwise operators
  //inspiration: FlorenceMachine on leetcode
  //beats 100% of results
  public double myPow(double x, int n) {
    long m = n>0? n: -(long)n;
    double result = 1.0;
    while(m!=0){
      if((m&1)==1){
        result*=x;
      }
      x*=x;
      m>>=1;
    }
    return n>0? result:1/result;
  }

  //given an array and a value requiring removal
  //return the same array and an int indicating new end of array
  //such that the selected value has been removed from that part
  //and all other values shifted left
  //https://leetcode.com/problems/remove-element/description/
  //faster than 99.98% of solutions
  public int removeElement(int[] nums, int val) {
    if(nums.length==0){return 0;}
    int writeIndex = 0;
    for(int i = 0; i < nums.length; i++){
      if(nums[i]!=val){
        if(i==writeIndex){
          writeIndex++;
        }
        else{
          nums[writeIndex]=nums[i];
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
  public String reverseString(String words){
    char[] wordsArr = words.toCharArray();
    int runningPtr = 0;
    int wordLen=0;
    int wordStart =0;
    while(runningPtr<wordsArr.length){
      while(runningPtr<wordsArr.length&&wordsArr[runningPtr]!=' '){
        runningPtr++;
        wordLen++;
      }
      for(int i = 0; i < wordLen/2; i++){
        char tmp = wordsArr[wordStart+i];
        wordsArr[wordStart+i]=wordsArr[runningPtr-1-i];
        wordsArr[runningPtr-1-i]=tmp;
      }
      runningPtr++;
      wordStart=runningPtr;
      wordLen=0;
    }
    for(int j = 0; j < wordsArr.length/2; j++){
      char tmp = wordsArr[j];
      wordsArr[j] = wordsArr[wordsArr.length-1-j];
      wordsArr[wordsArr.length-1-j] = tmp;
    }
    return new String(wordsArr);
  }

  //find first occurence of a substring in a string
  //https://leetcode.com/problems/implement-strstr/description/
  //bog standard solution without any substring algorithms used, just a hashmap.
  //technically linear time, though having to get all those substrings is a hidden cost
  public int strStr(String haystack, String needle) {
    if(haystack.length()==0){return 0;}
    if(needle.length()>haystack.length()){return -1;}
    Map<String, Boolean> needleStorage = new HashMap<>(1);
    needleStorage.put(needle, true);
    for(int i = 0; i <= haystack.length()-needle.length(); i++){
      if(needleStorage.containsKey(haystack.substring(i,i+needle.length()))){return i;}
    }
    return -1;
  }

  //given a low->high sorted int array, find the starting and ending position of a value
  //return [-1,-1] if it is not present
  //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
  public int[] searchRange(int[] nums, int target) {
    if(nums.length==0){return new int[] {-1,-1};}
    return recursSearchRange(nums, 0, nums.length-1, target);
  }

  private int[] recursSearchRange(int[] nums, int leftIndex, int rightIndex, int target){
    if(leftIndex>rightIndex){return new int[] {-1,-1};}
    if(rightIndex==leftIndex){
      if (nums[leftIndex]==target){return new int[] {leftIndex, leftIndex};}
      else{return new int[] {-1, -1};}
    }
    int centerVal = leftIndex + (rightIndex-leftIndex)/2;
    if(nums[centerVal] == target){
      int leftEnd = centerVal;
      int rightEnd = centerVal;
      while(leftEnd>0&&nums[leftEnd-1]==target){
        leftEnd--;
      }
      while(rightEnd<nums.length-1&&nums[rightEnd+1]==target){
        rightEnd++;
      }
      return new int[] {leftEnd, rightEnd};
    }
    else if(nums[centerVal]>target){
      return recursSearchRange(nums, leftIndex, centerVal-1, target);
    }
    else{
      return recursSearchRange(nums, centerVal+1, rightIndex, target);
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
    int right = nums.length-1;
    int curMid = 0;
    while(left<right){
      curMid = left +(right-left)/2;
      if(nums[curMid]>target){
        right = curMid;
      }
      else if(nums[curMid]<target){
        left = curMid+1;
      }
      else{
        return curMid;
      }
    }
    //edge case - left==right, at target value
    //after this, we know for sure the value was not found
    if(nums[curMid]==target){return curMid;}
    //only exception to rule - if target < nums[0] return 0;
    if(curMid==0&&nums[curMid]>target){return 0;}
    //decrement curMid if possible in case curMid is currently stuck
    //on the first value larger than it
    if(curMid!=0&&nums[curMid]>target){curMid--;}
    //find index of largest value smaller than target, then add 1 to it.
    boolean rightAdjustDone = false;
    while(curMid<nums.length-1&&!rightAdjustDone){
      if(nums[curMid+1]<target){
        curMid++;
      }
      else{
        rightAdjustDone=true;
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
    while(m>=mPtr||n>=nPtr){
      if(m>=mPtr&&n>=nPtr){
        if(nums1[m-mPtr]<=nums2[n-nPtr]){
          nums1[nums1.length-1-j]=nums2[n-nPtr];
          nPtr++;
          j++;
        }
        else{
          nums1[nums1.length-1-j]=nums1[m-mPtr];
          mPtr++;
          j++;
        }
      }
      else if(mPtr > m){
        nums1[nums1.length-1-j]=nums2[n-nPtr];
        nPtr++;
        j++;
      }
      else{
        nums1[nums1.length-1-j]=nums1[m-mPtr];
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
    if(root==null){return results;}
    Stack<TreeNode> recursStack = new Stack<>();
    Map<TreeNode, Boolean> alreadyWritten = new HashMap<>();
    recursStack.push(root);
    while(!recursStack.isEmpty()){
      TreeNode tmp = recursStack.peek();
      if(tmp.left!=null&&!alreadyWritten.containsKey(tmp.left)){recursStack.push(tmp.left);}
      else{
        results.add(tmp.val);
        tmp = recursStack.pop();
        alreadyWritten.put(tmp, true);
        if(tmp.right!=null&&!alreadyWritten.containsKey(tmp.right)){recursStack.push(tmp.right);}
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
    if(n<1){return 0;}
    if(n==1){return 1;}
    int firstVal = 1;
    int secondVal = 2;
    int tmp;
    for(int i = 3; i <= n; i++){
      tmp = firstVal+secondVal;
      firstVal = secondVal;
      secondVal = tmp;
    }
    return secondVal;
  }
  //class for next problem concerning inserting/merging intervals
  public class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
  }

  //given a list of non-overlapping intervals sorted by start times
  //and an interval to be inserted, insert and merge as needed
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
    for(int i = 0; i < intervArr.length; i++){
      if(intervArr[i].start<=newInterval.start){
        smallerStartValIndex++;
      }
      if(intervArr[intervArr.length-1-i].end>=newInterval.end){
        biggerEndValIndex--;
      }
    }
    if(smallerStartValIndex>-1&&intervArr[smallerStartValIndex].end>=newInterval.start){
      startInInterval = true;
    }
    if(biggerEndValIndex<intervArr.length&&intervArr[biggerEndValIndex].start<=newInterval.end){
      endInInterval = true;
    }
    if(smallerStartValIndex==-1&&biggerEndValIndex==intervArr.length){
      resultList.add(newInterval);
      return resultList;
    }
    else if(smallerStartValIndex==-1){
      if(endInInterval){
        newInterval.end = intervArr[biggerEndValIndex].end;
        biggerEndValIndex++;
      }
      resultList.add(newInterval);
      for(int j = biggerEndValIndex; j<intervArr.length; j++){
        resultList.add(intervArr[j]);
      }
    }
    else if(biggerEndValIndex==intervArr.length){
      if(startInInterval){
        newInterval.start=intervArr[smallerStartValIndex].start;
        smallerStartValIndex--;
      }
      for(int j = 0; j <=smallerStartValIndex; j++){
        resultList.add(intervArr[j]);
      }
      resultList.add(newInterval);
    }
    else{
      if(startInInterval){
        newInterval.start = intervArr[smallerStartValIndex].start;
        smallerStartValIndex--;
      }
      if(endInInterval){
        newInterval.end = intervArr[biggerEndValIndex].end;
        biggerEndValIndex++;
      }
      for(int j = 0; j <= smallerStartValIndex; j++){
        resultList.add(intervArr[j]);
      }
      resultList.add(newInterval);
      for(int j = biggerEndValIndex; j <intervArr.length; j++){
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
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        if(board[i][j]==wordArr[0]){
          visited[i][j] = true;
          if(recursWordExists(board, visited, i, j, wordArr, 1)){return true;}
          visited[i][j] = false;
        }
      }
    }
    return false;
  }
  private boolean recursWordExists(char[][] board, boolean[][] visited, int row, int col, char[] word, int wordIndex){
    if(wordIndex==word.length){return true;}
    int tmpRow = 0;
    int tmpCol = 0;
    if(row>0&&board[row-1][col]==word[wordIndex]){
      tmpRow = row-1;
      tmpCol = col;
      if(!visited[tmpRow][tmpCol]){
        visited[tmpRow][tmpCol]=true;
        if(recursWordExists(board, visited, tmpRow, tmpCol, word, wordIndex+1)){return true;}
        visited[tmpRow][tmpCol]=false;
      }
    }
    if(row<board.length-1&&board[row+1][col]==word[wordIndex]){
      tmpRow = row+1;
      tmpCol = col;
      if(!visited[tmpRow][tmpCol]){
        visited[tmpRow][tmpCol]=true;
        if(recursWordExists(board, visited, tmpRow, tmpCol, word, wordIndex+1)){return true;}
        visited[tmpRow][tmpCol]=false;
      }
    }
    if(col>0&&board[row][col-1]==word[wordIndex]){
      tmpRow = row;
      tmpCol = col-1;
      if(!visited[tmpRow][tmpCol]){
        visited[tmpRow][tmpCol]=true;
        if(recursWordExists(board, visited, tmpRow, tmpCol, word, wordIndex+1)){return true;}
        visited[tmpRow][tmpCol]=false;
      }
    }
    if(col<board[0].length-1&&board[row][col+1]==word[wordIndex]){
      tmpRow = row;
      tmpCol = col+1;
      if(!visited[tmpRow][tmpCol]){
        visited[tmpRow][tmpCol]=true;
        if(recursWordExists(board, visited, tmpRow, tmpCol, word, wordIndex+1)){return true;}
        visited[tmpRow][tmpCol]=false;
      }
    }
    return false;
  }

  //given a sorted int array, remove duplicates (>2) by shifting array vals left
  //https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
  //a pretty standard solution
  public int removeTriplicates(int[] nums) {
    if(nums.length<3){return nums.length;}
    boolean foundWriteIndex = false;
    int curInt = nums[0];
    int curIntCount = 1;
    int writeIndex = 0;
    for(int i = 1; i < nums.length; i++){
      if(curInt==nums[i]){curIntCount++;}
      else{
        curInt = nums[i];
        curIntCount = 1;
      }
      if(!foundWriteIndex){
        if(curIntCount==3){
          writeIndex = i;
          foundWriteIndex = true;
        }
      }
      else{
        if(curIntCount<3){
          nums[writeIndex]=nums[i];
          writeIndex++;
        }
      }
    }
    return foundWriteIndex? writeIndex : nums.length;
  }

  //given a string S and a set of characters T
  //find the smallest substring of S containing all characters in T
  //https://leetcode.com/problems/minimum-window-substring/description/
  public String minWindow(String s, String t) {
    if(t==null||s==null||t.length()==0||s.length()==0||t.length()>s.length()){return "";}
    Map<Character, Integer> subStrChars = new HashMap<>();
    for(int i=0; i<t.length(); i++){
      subStrChars.put(t.charAt(i), 0);
    }
    Map<Character, List<Integer>> instOfChars = new HashMap<>();
    for(int j=0; j<s.length(); j++) {
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
      if(curMinListIndex == instOfChars.get(minChar).size()-1){
        minimalSolFound = true;
      }
      else{
        subStrChars.put(minChar, subStrChars.get(minChar) + 1);
      }

    }
    return s.substring(minDistLeft, minDistRight+1);
    }

  //validate a BST
  //https://leetcode.com/submissions/detail/173807875/
  //borrowed idea from jeantimex of leetcode for a more performant solution
  public boolean isValidBST(TreeNode root) {
    return recursValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }
  private boolean recursValidBST(TreeNode node, long min, long max){
    if(node==null){return true;}
    if(node.val<=min||node.val>=max){return false;}
    if(node.left!=null){
      if(!recursValidBST(node.left, min, node.val)){return false;}
    }
    if(node.right!=null){
      if(!recursValidBST(node.right, node.val, max)){return false;}
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
    for(int i = 0; i < matrix.length; i++){
      for(int j = 0; j < matrix[0].length; j++){
        if(matrix[i][j]==0){
          rows.put(j, true);
          cols.put(i, true);
        }
      }
    }
    for(Integer j: rows.keySet()){
      for(int i = 0; i < matrix.length; i++){
        if(matrix[i][j]!=0){matrix[i][j]=0;}
      }
    }
    for(Integer i: cols.keySet()){
      for(int j = 0; j < matrix[0].length; j++){
        if(matrix[i][j]!=0){matrix[i][j]=0;}
      }
    }
  }

  //given a number represented as an array of digits
  //add one to the value, and return as resulting array
  //https://leetcode.com/problems/plus-one/description/
  //straightforward, beats 100% of solutions
  public int[] plusOne(int[] digits) {
    boolean carryOne = false;
    for(int i = digits.length-1; i>=0; i--){
      if(digits[i]<9){
        digits[i]=digits[i]+1;
        break;
      }
      else{
        digits[i]=0;
        if(i==0){carryOne=true;}
      }
    }
    int[] newResult = new int[] {};
    if(carryOne){
      newResult = new int[digits.length+1];
      newResult[0]=1;
      for(int j = 1; j < newResult.length; j++){
        newResult[j]=digits[j-1];
      }
    }
    return carryOne? newResult : digits;
  }


}
