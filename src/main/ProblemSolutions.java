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
    public List<int[]> findSumInArray(int[] array, int sum){
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


}
