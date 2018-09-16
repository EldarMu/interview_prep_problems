package com.eldar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//class for getting random sample of input doubles
public class RandomSample {
  private int sampleSize;
  private List<Double> internalStorage;
  private int count;
  private Random rand;

  public RandomSample(int sampleSize) {
    if (sampleSize <= 0) {
      throw new IllegalArgumentException("sample size must be a positive value above 0");
    }
    rand = new Random();
    this.sampleSize = sampleSize;
    internalStorage = new ArrayList<>(sampleSize);
  }

  public void next(double value) {
    count++;
    if(internalStorage.size() < sampleSize){
      internalStorage.add(value);
      return;
    }
    if(rand.nextDouble() < sampleSize/count){
      internalStorage.set((int)rand.nextDouble()*sampleSize, value);
    }
  }

  public  List<Double> getSample() {
    return internalStorage;
  }


}
