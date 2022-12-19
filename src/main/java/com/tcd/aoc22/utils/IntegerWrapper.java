package com.tcd.aoc22.utils;

// NOTE(Tom): used when passing ref is needed
public class IntegerWrapper {
  private Integer i;
  
  public IntegerWrapper () {
    
  }
  
  public IntegerWrapper (int i) {
    this.i = i;
  }
  
  public int add (int i) {
    this.i += i;
    return this.i;
  }
  
  public int minus (int i) {
    this.i -= i;
    return this.i;
  }
  
  public int getVal() {
    return this.i;
  }
}
