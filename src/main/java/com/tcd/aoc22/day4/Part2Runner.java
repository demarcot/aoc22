package com.tcd.aoc22.day4;

import java.net.URISyntaxException;
import java.util.List;

import com.tcd.aoc22.utils.MiscUtils;

public class Part2Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 4 Part 2 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day4/d4p1.txt");
    
    int sum = 0;
    
    for (String line : lines) {
      // Split line into each elf's section range
      var pairSectionArr = line.split(",");
      
      // Split the section ranges into start and end
      var elf1SectionArr = pairSectionArr[0].split("-");
      var elf2SectionArr = pairSectionArr[1].split("-");
      
      // Build elves
      Elf elf1 = new Elf(Integer.parseInt(elf1SectionArr[0]), Integer.parseInt(elf1SectionArr[1]));
      Elf elf2 = new Elf(Integer.parseInt(elf2SectionArr[0]), Integer.parseInt(elf2SectionArr[1]));
      
      // Sum all pairs that have overlaps
      if (Elf.hasOverlap(elf1, elf2)) {
        sum++;
      }
    }
    
    System.out.println(sum);
  }
}
