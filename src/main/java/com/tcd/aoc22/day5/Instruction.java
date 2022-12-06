package com.tcd.aoc22.day5;

public class Instruction {
  private int moveCnt;
  private int src;
  private int dest;
  
  public Instruction (int moveCnt, int src, int dest) {
    this.moveCnt = moveCnt;
    this.src = src;
    this.dest = dest;
  }
  
  public int getMoveCnt () {
    return this.moveCnt;
  }
  
  public int getSrc () {
    return this.src;
  }
  
  public int getDest () {
    return this.dest;
  }
}
