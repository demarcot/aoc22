package com.tcd.aoc22.day3;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.MiscUtils;

public class Part2Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 3 Part 2 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day3/d3p1.txt");
    
    List<Rucksack> sacks = new ArrayList<>();

    // Build list of rucksacks
    for (String line : lines) {
      sacks.add(new Rucksack(line));
    }
    
    // Don't really need to loop over again, but makes it easier to read
    // Sum the priority of the badge items of each 3-elf group
    int sum = 0;
    for (int i = 0; i < sacks.size(); i+=3) {
      char badge = Rucksack.findBadgeItem(sacks.get(i), sacks.get(i + 1), sacks.get(i + 2));
      sum += Rucksack.getPriority(badge);
    }
    
    System.out.println(sum);
  }
}
