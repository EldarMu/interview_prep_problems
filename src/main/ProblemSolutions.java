import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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



}
