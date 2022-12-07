package com.tcd.aoc22.day6;

import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tcd.aoc22.utils.MiscUtils;

public class Part2Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 6 Part 2 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day6/d6p1.txt");
    
    // Only one line for this problem
    var stream = lines.get(0);
    
    // Checking for 4char start-of-packet indicator
    int sopSize = 14;
    
    int packetOffset = getFirstPacketOffset(stream, sopSize);
    
    System.out.println(packetOffset);
  }
  
  private static int getFirstPacketOffset (String stream, int sopSize) {
    // Guaranteed valid input, so not worried about index oob
    Deque<Character> queue = new ArrayDeque<>();
    Set<Character> s = new HashSet<>();
    
    for (int i = 0; i < sopSize; i++) {
      queue.add(stream.charAt(i));
      s.add(stream.charAt(i));
    }
    
    if (s.size() == sopSize) {
      return sopSize;
    }
    
    for (int i = sopSize; i < stream.length(); i++) {
      queue.remove();
      queue.add(stream.charAt(i));
      
      s = new HashSet<>(queue);
      if (s.size() == sopSize) {
        return i + 1;
      }
    }
    
    System.out.println("Couldn't find packet offset");
    return -1;
  }
}
