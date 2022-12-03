package com.tcd.aoc22.day1;

import java.util.ArrayList;
import java.util.List;

public class Elf {
  private List<Integer> calList = new ArrayList<>();
  private int totalCals;
  
  public Elf (List<Integer> calList) {
    this.calList = calList;
    this.totalCals = calList.stream().mapToInt(i -> i.intValue()).sum();
  }
  
  public int getTotalCalories() {
    return this.totalCals;
  }
}
