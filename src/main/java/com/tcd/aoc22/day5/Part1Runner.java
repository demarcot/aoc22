package com.tcd.aoc22.day5;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tcd.aoc22.utils.MiscUtils;

public class Part1Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 5 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day5/d5p1.txt");

    // Initialize container stacks
    var containerStacks = buildContainerStacks(lines);

    // Build and run instructions
    for (int i = getInstructionsStart(lines); i < lines.size(); i++) {
      var splitLine = lines.get(i).split(" ");

      // Instruction in format: [move], cnt, [from], src, [to], dest
      Instruction instruction = new Instruction(
          Integer.parseInt(splitLine[1]),
          Integer.parseInt(splitLine[3]) - 1,
          Integer.parseInt(splitLine[5]) - 1);
      
      runInstruction(instruction, containerStacks);
    }

    // Print top of each stack
    for (var stack : containerStacks) {
      System.out.print(stack.get(stack.size() - 1));
    }
  }

  public static List<List<Character>> buildContainerStacks(List<String> lines) {
    int stackCnt = (lines.get(0).length() + 1) / 4;

    List<List<Character>> containerStacks = new ArrayList<>();
    for (int i = 0; i < stackCnt; i++) {
      containerStacks.add(new ArrayList<>());
    }

    for (int i = 0; i < lines.size(); i++) {
      var line = lines.get(i);

      // Break when done with initial layout
      if (line.contains("1")) {
        break;
      }

      // Add
      for (int j = 1, currStack = 0; j < line.length(); j += 4) {
        char container = line.charAt(j);
        if (Character.isAlphabetic(container)) {
          containerStacks.get(currStack).add(0, container);
        }
        currStack++;
      }
    }
    
    return containerStacks;
  }

  public static int getInstructionsStart(List<String> lines) {
    for (int i = 0; i < lines.size(); i++) {
      var line = lines.get(i);

      // Break when done with initial layout
      if (line.contains("1")) {
        return i + 2;
      }
    }

    System.out.println("No instructions...");
    return -1;
  }
  
  // NOTE(Tom): couldn't find quick way to do this without creating new lists instances
  public static void runInstruction (Instruction instruction, List<List<Character>> containerStacks) {
    var src = containerStacks.get(instruction.getSrc());
    var dest = containerStacks.get(instruction.getDest());
    
    var movingContainers = new ArrayList<>(src.subList(src.size() - instruction.getMoveCnt(), src.size()));
    var remainingContainers = new ArrayList<>(src.subList(0, src.size() - instruction.getMoveCnt()));
    
    Collections.reverse(movingContainers); // Flip order when inserting
    
    containerStacks.set(instruction.getSrc(), remainingContainers);
    dest.addAll(movingContainers);
  }
}
