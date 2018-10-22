package com.eldar.commonDataStructs;

//given a set of non-overlapping rectangles as coordinates (x1, y1, x2, y2) with x2>x1 y2>y1
//when the method pick is called, return a random point out of one of the rectangles
public class PickRandPointInRectangle {
  long[][] rectangles;
  public PickRandPointInRectangle(int[][] rectangles){
    this.rectangles = new long[rectangles.length][rectangles[0].length];
    for(int i=0; i<rectangles.length; i++){
      for(int j=0; j<rectangles[0].length; j++){
        this.rectangles[i][j]=(long)rectangles[i][j];
      }
    }
  }

  public int[] pick(){
    int[] coords = new int[2];
    long[] chosenRectangle = rectangles[java.util.concurrent.ThreadLocalRandom.current().nextInt(rectangles.length)];
    if(chosenRectangle[0]==chosenRectangle[2]) coords[0]=(int)chosenRectangle[0];
    else coords[0] = (int)java.util.concurrent.ThreadLocalRandom.current().nextLong(chosenRectangle[0], chosenRectangle[2]+1);
    if(chosenRectangle[1]==chosenRectangle[3]) coords[1]=(int)chosenRectangle[1];
    else coords[1] = (int)java.util.concurrent.ThreadLocalRandom.current().nextLong(chosenRectangle[1], chosenRectangle[3]+1);
    return coords;
  }

}
