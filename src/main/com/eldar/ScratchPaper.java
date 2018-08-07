package com.eldar;

//class for checking random language functionality
//in this case seeing if there's a quicker way to solve the jewels and stones problem than with a hashmap
public class ScratchPaper {
    public static void main(String[] args){
        Boolean b = false;
        ScratchPaper tester = new ScratchPaper();
        tester.testEncapsulationValueChange(b);
        System.out.print(b + "");

        //com.eldar.ProblemSolutions tester = new com.eldar.ProblemSolutions();
        //System.out.print(tester.maxReduceLenOfSubArray(new int[] {1}, 1));
        //System.out.println(tester.maxReduceLenOfSubArray(new int[] {77,19,35,10,-14}, 19));
        //System.out.println(tester.maxReduceLenOfSubArray(new int[] {56,-21,56,35}, 19));

    }

    public void testEncapsulationValueChange(Boolean b){
        b = true;
        return;
    }

    public int shortestSubarray(int[] A, int K) {
        int length = -1;
        for(int i = 1; i <= A.length; i++){
            int sum = 0;
            for(int j = 0; j <= A.length - i; j++){
                if(j==0){
                    for(int k = 0; k < i; k++){
                        sum += A[k];
                    }
                }
                else{
                    sum +=A[j+i-1] -A[j-1];
                }
                if(K >=sum){
                    length = i;
                    break;
                }
            }
        }
        return length;
    }
}
