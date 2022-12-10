package com.tcd.aoc22.day10;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tcd.aoc22.utils.MiscUtils;

public class Part1Runner {
  
  final static int ADDX_CYCLE_SIZE = 2;
  
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 10 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day10/d10p1.txt");
    List<State> states = new ArrayList<>();
    
    // Build cpu states
    int x = 1;
    for (var line : lines) {
      var splitCommand = line.split(" ");
      if (splitCommand[0].equals("noop")) {
        x = handleNoop(states, x);
      } else if (splitCommand[0].equals("addx")) {
        x = handleAddx(states, x, Integer.parseInt(splitCommand[1]));
      }
    }
    
    // Sum values of selected cycles
    int sum = 0;
    for (int i = 20; i < states.size(); i += 40) {
      var state = states.get(i - 1);
      int cycle = state.getCycle();
      int register = state.getRegisters().get("x");
      sum += (cycle * register);
    }
    System.out.println(sum);
  }

  private static int handleNoop (List<State> states, int x) {
    states.add(new State(states.size() + 1, "noop", new HashMap<>() {{ put("x", x); }}));
    
    return x;
  }
  
  private static int handleAddx (List<State> states, int x, int v) {
    for (int i = 0; i < ADDX_CYCLE_SIZE; i++) {
      states.add(new State(states.size() + 1, "addx " + v, new HashMap<>() {{ put("x", x); }}));
    }
    
    return x + v;
  }
}
