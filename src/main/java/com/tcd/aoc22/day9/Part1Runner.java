package com.tcd.aoc22.day9;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tcd.aoc22.utils.ArraySet;
import com.tcd.aoc22.utils.Coord;
import com.tcd.aoc22.utils.MiscUtils;

public class Part1Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 9 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day9/d9p1.txt");
    
    Set<Coord> tailVisitedCoords = new ArraySet<>();
    
    // Initialize rope
    Coord head = new Coord(0, 0);
    Coord tail = new Coord(0, 0);
    tailVisitedCoords.add(tail.clone());
    
    // Process and run commands
    for (var line : lines) {
      var splitCommand = line.split(" ");
      CommandEnum c = CommandEnum.fromCode(splitCommand[0]);
      c.setDistance(Integer.parseInt(splitCommand[1]));
      
      var newVisitedCoords = runCommand(c, head, tail);
      tailVisitedCoords.addAll(newVisitedCoords);
    }
    
    System.out.println(tailVisitedCoords.size());
  }
  
  private static List<Coord> runCommand (CommandEnum command, Coord head, Coord tail) {
    List<Coord> visitedCoords = new ArrayList<>();
    
    for (int i = 0; i < command.getDistance(); i ++) {
      
      switch (command) {
      case UP:
        head.y++;
        break;
      case DOWN:
        head.y--;
        break;
      case LEFT:
        head.x--;
        break;
      case RIGHT:
        head.x++;
        break;
      }
      
      updateTail(head, tail);
      visitedCoords.add(tail.clone());
    }
    
    return visitedCoords;
  }
  
  private static void updateTail (Coord head, Coord tail) {
    if (head.isNeighbor(tail) || head.equals(tail)) {
      return;
    }
    
    // Ugly conditional that I don't care enough to consolidate
    if (head.x != tail.x && head.y != tail.y) {
      if (head.x > tail.x) {
        tail.x++;
      } else {
        tail.x--;
      }
      
      if (head.y > tail.y) {
        tail.y++;
      } else {
        tail.y--;
      }
    } else {
      if (head.x != tail.x) {
        if (head.x > tail.x) {
          tail.x++;
        } else {
          tail.x--;
        }
      } else if (head.y > tail.y) {
        tail.y++;
      } else {
        tail.y--;
      }
    }
  }
}
