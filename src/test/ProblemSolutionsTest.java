import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by eldar on 7/2/2018.
 */
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
        assertTrue("Hash array with no valid result did not return empty list",
                tester.findSumInArray(new int[] {-1,0}, 1).isEmpty());

        for(int i=0; i<testList.size(); i++){
            List<int[]> naiveResult = tester.naiveSumInArray(testList.get(i), testedSumList.get(i));
            List<int[]> hashResult = tester.findSumInArray(testList.get(i), testedSumList.get(i));

            assertTrue("Naive sum search returned unexpected number of results",
                    naiveResult.size() == resultList.get(i).length);
            assertTrue("Hash sum search returned unexpected number of results",
                    hashResult.size() == resultList.get(i).length);

            for(int j = 0; j < resultList.get(i).length; j++)
            {
                assertArrayEquals("naive result does not match expected result",
                        naiveResult.get(j), resultList.get(i)[j]);
                assertArrayEquals("hash result does not match expected result",
                        hashResult.get(j), resultList.get(i)[j]);
            }
        }





    }

    @Test
    public void findSumInArray() throws Exception {

    }

}