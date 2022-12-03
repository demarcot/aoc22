package com.tcd.aoc22.day3;

import java.net.URISyntaxException;
import java.util.List;

import com.tcd.aoc22.utils.MiscUtils;

public class Part1Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 3 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day3/d3p1.txt");
    
    int sum = 0;
    
    // Sum the priority of the common items in the 2 compartments of the rucksacks
    for (String line : lines) {      
      sum += Rucksack.getPriority(new Rucksack(line).getCommonItem());
    }
    
    System.out.println(sum);
  }
}
