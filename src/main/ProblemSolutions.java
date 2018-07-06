import java.util.*;

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
        String[] morseCode = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
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
    //runtime better than 97.4% of submissions
    public int[][] flipAndInvertImage(int[][] A) {
        int leftVal = 0;
        int rightVal = 0;
        int midVal = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < (A[0].length/2); j++){
                leftVal = A[i][j];
                rightVal = A[i][A[0].length-1-j];
                leftVal = leftVal == 0 ? 1 : 0;
                rightVal = rightVal == 0 ? 1 : 0;
                A[i][j] = rightVal;
                A[i][A[0].length-1-j] = leftVal;
            }
            if(A[0].length%2 == 1){
                midVal = A[i][(A[0].length/2)];
                midVal = midVal == 0 ? 1 : 0;
                A[i][(A[0].length/2)] = midVal;
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

}
