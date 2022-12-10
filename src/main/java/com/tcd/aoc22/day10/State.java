package com.tcd.aoc22.day10;

import java.util.Map;

public class State {
  private int cycle;
  private String op;
  private Map<String, Integer> registers;
  
  public State (int cycle, String op, Map<String, Integer> registers) {
    this.cycle = cycle;
    this.op = op;
    this.registers = registers;
  }
  
  public int getCycle () {
    return this.cycle;
  }
  
  public String getOp () {
    return this.op;
  }
  
  public Map<String, Integer> getRegisters () {
    return this.registers;
  }
  
}
