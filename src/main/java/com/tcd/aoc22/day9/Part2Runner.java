package com.tcd.aoc22.day9;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tcd.aoc22.utils.ArraySet;
import com.tcd.aoc22.utils.Coord;
import com.tcd.aoc22.utils.MiscUtils;

public class Part2Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 9 Part 2 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day9/d9p1.txt");
    
    Set<Coord> tailVisitedCoords = new ArraySet<>();
    
    // Initialize rope
    List<Coord> allCoords = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      allCoords.add(new Coord(0, 0));
    }
    Coord head = allCoords.get(0);
    Coord tail = allCoords.get(allCoords.size() - 1);
    tailVisitedCoords.add(tail.clone());
    
    // Process and run command
    for (var line : lines) {
      var splitCommand = line.split(" ");
      CommandEnum c = CommandEnum.fromCode(splitCommand[0]);
      c.setDistance(Integer.parseInt(splitCommand[1]));
      
      
      var newVisitedCoords = runCommand(c, head, tail, allCoords);
      tailVisitedCoords.addAll(newVisitedCoords);
    }
    
    System.out.println(tailVisitedCoords.size());
  }
  
  private static List<Coord> runCommand (CommandEnum command, Coord head, Coord tail, List<Coord> allCoords) {
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
      
      updateRope(allCoords);
      visitedCoords.add(tail.clone());
    }
    
    return visitedCoords;
  }
  
  private static void updateRope (List<Coord> allCoords) {
    for (int i = 1; i < allCoords.size(); i++) {
      var prevCoord = allCoords.get(i - 1);
      var currCoord = allCoords.get(i);
      
      if (prevCoord.isNeighbor(currCoord) || prevCoord.equals(currCoord)) {
        return;
      }
      
      // Ugly conditional that I don't care enough to consolidate
      if (prevCoord.x != currCoord.x && prevCoord.y != currCoord.y) {
        if (prevCoord.x > currCoord.x) {
          currCoord.x++;
        } else {
          currCoord.x--;
        }
        
        if (prevCoord.y > currCoord.y) {
          currCoord.y++;
        } else {
          currCoord.y--;
        }
      } else {
        if (prevCoord.x != currCoord.x) {
          if (prevCoord.x > currCoord.x) {
            currCoord.x++;
          } else {
            currCoord.x--;
          }
        } else if (prevCoord.y > currCoord.y) {
          currCoord.y++;
        } else {
          currCoord.y--;
        }
      }
    }
  }
}
