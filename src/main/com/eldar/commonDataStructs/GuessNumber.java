package com.eldar.commonDataStructs;

//class for storing a number, and returning -1/0/1 when the guess method is called
public class GuessNumber {
  private int number;
  public GuessNumber(int number){
    this.number = number;
  }
  public int guess(int num){
    if(num<number) return 1;
    else if(num>number) return -1;
    else return 0;
  }
}
