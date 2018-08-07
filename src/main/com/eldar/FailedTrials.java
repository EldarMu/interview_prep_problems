package com.eldar;

import java.util.LinkedList;
import java.util.List;

//storage for failed approaches to solving the various programming problems
//where the issue was not some error but an incorrect approach to solving the problem
//revealed only through test cases
public class FailedTrials {
    //given array A and value K, return the length of the shortest contiguous subarray summing to K
    //https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/
    //initial try, going to borrow ideas from the rabin-karp string search method
    //O(N^2)
    //failed with array length of 260,000 elements
    public int lenOfSubArray(int[] A, int K){
        int length = -1;
        int windowSize = 1;
        boolean solnFound = false;
        while(windowSize <= A.length && !solnFound){
            int sum = 0;
            for(int j = 0; j <= A.length - windowSize; j++){
                if(j==0){
                    for(int k = 0; k < windowSize; k++){
                        sum += A[k];
                    }
                }
                else{
                    sum +=A[j+windowSize-1] -A[j-1];
                }
                if(K <= sum){
                    length = windowSize;
                    solnFound = true;
                }
            }
            windowSize++;
        }
        return length;
    }
    //the basic idea is that we don't look for the smallest runs intially
    //instead we collect the runs of values over a certain % of the K value
    //and then, having collected such runs, we reduce them to their minimum length
    //that satisfies the requirement
    //turned out to be a dud. fails for [2,-1,2], 3 due to treating -1 as a definite false
    public int partMatchLenOfSubArray(int[] A, int K){
        double soughtValueReductionCoeff = .6;
        double comparedVal = (double)K;
        List<StoredMatchSequences> matches = new LinkedList<>();
        StoredMatchSequences bestMatch = new StoredMatchSequences(-1,-1,-1,50000000);
        //iterate with decreasing values of compared value (for more inclusive and longer runs)
        while(comparedVal>=1.0 && bestMatch.sum < K){
            int runStart = 0;
            int runEnd = 0;
            int runSum = 0;
            int runLen = 0;
            boolean newRun = false;
            for(int i = 0; i < A.length; i++){
                //edge case ugliness
                if(i == A.length-1){
                    if(!newRun && A[i] >= comparedVal){
                        if(A[i] >= K){
                            matches.add(new StoredMatchSequences(i,i,A[i],1));
                        }
                    }
                    else if(newRun && A[i]>= comparedVal){
                        runEnd = i;
                        runSum += A[i];
                        runLen++;
                        if(runSum >= K){
                            matches.add(new StoredMatchSequences(runStart,runEnd,runSum, runLen));
                        }
                    }
                    else if(!newRun && A[i] < comparedVal){
                        if(runSum >= K){
                            matches.add(new StoredMatchSequences(runStart,runEnd,runSum, runLen));
                        }
                    }
                }
                if(!newRun && A[i] >= comparedVal){
                    newRun = true;
                    runStart = i;
                    runEnd = i;
                    runSum = A[i];
                    runLen = 1;
                }
                else if(newRun && A[i] >= comparedVal){
                    runEnd = i;
                    runSum += A[i];
                    runLen++;
                }
                else if(newRun && A[i] < comparedVal){
                    if(runSum>=K){
                        matches.add(new StoredMatchSequences(runStart, runEnd, runSum, runLen));
                    }
                    newRun = false;
                    runStart = 0;
                    runEnd = 0;
                    runSum = 0;
                    runLen = 0;
                }
            }
            //any run consisting of values greater than comparedVal and summing to >= K are now stored

            for(int j = 0; j < matches.size(); j++){
                int leftIndex = matches.get(j).start;
                int rightIndex = matches.get(j).end;
                int sum = matches.get(j).sum;
                int length = matches.get(j).runLen;
                boolean minRunLength = false;
                while(sum>=K && !minRunLength){
                    if((sum-A[leftIndex]-A[rightIndex])>=K)
                    {
                        sum -= A[leftIndex];
                        leftIndex++;
                        sum-= A[rightIndex];
                        rightIndex--;
                        length -=2;
                    }
                    else if((sum-A[leftIndex])>=K){
                        sum -= A[leftIndex];
                        leftIndex++;
                        length--;
                    }
                    else if((sum-A[rightIndex])>=K){
                        sum -= A[rightIndex];
                        rightIndex--;
                        length--;
                    }
                    else{
                        if(length < bestMatch.runLen)
                        {
                            bestMatch = new StoredMatchSequences(leftIndex, rightIndex, sum, runLen);
                        }
                        minRunLength =true;
                    }
                }
            }
            //outcome is the best match (if any) found during this run.
            comparedVal *= soughtValueReductionCoeff;
        }
        return bestMatch.sum == -1 ?  -1 : bestMatch.runLen;
    }
    public class StoredMatchSequences{
        int start;
        int end;
        int sum;
        int runLen;
        public StoredMatchSequences(int start, int end, int sum, int runLen){
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.runLen = runLen;
        }
    }

    //let's start with the max size and see if we can iteratively remove elements
    //from the left and the right to get to the shortest run
    public int maxReduceLenOfSubArray(int[] A, int K){
        int sum = 0;
        int length = 0;
        int leftIndex = 0;
        int rightIndex = A.length-1;
        boolean minimumLenReached = false;
        for(int i = 0; i < A.length; i++){
            sum += A[i];
            length++;
        }
        while(!minimumLenReached){
            if(A[leftIndex]>=A[rightIndex]){
                if((sum-A[rightIndex])>= K){
                    sum -= A[rightIndex];
                    rightIndex--;
                    length--;
                }
                else{minimumLenReached=true;}
            }
            else{
                if((sum-A[leftIndex])>=K){
                    sum -= A[leftIndex];
                    leftIndex++;
                    length--;
                }
                else{minimumLenReached = true;}
            }
        }
        return sum >= K ? length : -1;
    }
}
