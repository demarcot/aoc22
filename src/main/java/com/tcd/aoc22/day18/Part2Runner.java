package com.tcd.aoc22.day18;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.ArraySet;
import com.tcd.aoc22.utils.Coord3;
import com.tcd.aoc22.utils.Grid3;
import com.tcd.aoc22.utils.MiscUtils;
import com.tcd.aoc22.utils.Node3;

// Build Grid (make sure there is a buffer of air around the whole grid)
// Crawl Exterior
//   - add air blocks to search frontier (Set)
// Search the frontier while not empty
// - pop off search frontier and add to exhaust frontier (Set)
// - if neighbor is air and not in exhaust frontier, add to search frontier
// - if neighbor is rock, add to target frontier (List, duplicates mean multiple faces exposed)
// Print size of target frontier
// 2114 - too high
public class Part2Runner {
  
  private final static int AIR = 0;
  private final static int ROCK = 1;
  
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 18 Part 2 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day18/d18p1.txt");
    
    int t = 0, r = 0, ba = 0;
    
    List<Coord3> points = new ArrayList<>();
    for (var line : lines) {
      var splitStr = line.split(",");
      int x = Integer.parseInt(splitStr[0]);
      int y = Integer.parseInt(splitStr[1]);
      int z = Integer.parseInt(splitStr[2]);
      var c = new Coord3(x, y, z);
      
      if (x > r) {
        r = x;
      }
      
      if (y > t) {
        t = y;
      }
      
      if (z > ba) {
        ba = z;
      }
      
      points.add(c);
    }

    Grid3<Integer> grid = new Grid3<>();
    grid.init(r + 4, t + 4, ba + 4, AIR);
    
    for (var coord : points) {
      grid.getNode(coord.x + 1, coord.y + 1, coord.z + 1).setVal(ROCK);
    }
    
    List<Node3<Integer>> goodLands = new ArrayList<>();
    ArraySet<Node3<Integer>> badLands = new ArraySet<>();
    ArraySet<Node3<Integer>> searchLands = new ArraySet<>();
    
    // Search exterior
    // if rock, add to goodLands
    // if air, add to searchLands
    // top/bot
    for (int d = 0; d < grid.getDepth(); d++) {
      for (int x = 0; x < grid.getWidth(); x++) {
        var n1 = grid.getNode(x, grid.getHeight() - 1, d);
        if (n1.getVal() == ROCK) {
          System.out.println("??");
          badLands.add(n1);
        } else {
          searchLands.add(n1);
        }
        
        var n2 = grid.getNode(x, 0, d);
        if (n2.getVal() == ROCK) {
          System.out.println("??");
          badLands.add(n2);
        } else {
          searchLands.add(n2);
        }
      }
    }
    
    // left/right
    for (int d = 0; d < grid.getDepth(); d++) {
      for (int y = 0; y < grid.getHeight(); y++) {
        var n1 = grid.getNode(0, y, d);
        if (n1.getVal() == ROCK) {
          System.out.println("??");
          badLands.add(n1);
        } else {
          searchLands.add(n1);
        }
        
        var n2 = grid.getNode(grid.getWidth() - 1, y, d);
        if (n2.getVal() == ROCK) {
          System.out.println("??");
          badLands.add(n2);
        } else {
          searchLands.add(n2);
        }
      }
    }
    
    // front/back
    for (int y = 0; y < grid.getHeight(); y++) {
      for (int x = 0; x < grid.getWidth(); x++) {
        var n1 = grid.getNode(x, y, 0);
        if (n1.getVal() == ROCK) {
          System.out.println("??");
          badLands.add(n1);
        } else {
          searchLands.add(n1);
        }
        
        var n2 = grid.getNode(x, y, grid.getDepth() - 1);
        if (n2.getVal() == ROCK) {
          System.out.println("??");
          badLands.add(n2);
        } else {
          searchLands.add(n2);
        }
      }
    }
    
    // Search searchLands
    // for neighbors,
    // if rock, add to goodLands
    // if air, add to searchLands
    while (!searchLands.isEmpty()) {
      var n = searchLands.pop();
      badLands.add(n);
      
      var neighbors = n.getNeighbors();
//      boolean isGood = false;
      for (var neighbor : neighbors) {
        if (neighbor.getVal() == ROCK) {
          goodLands.add(neighbor);
        } else if (!badLands.contains(neighbor)) {
          searchLands.add(neighbor);
        }
      }
      
//      if (isGood) {        
//        goodLands.add(n);
//      }
    }
    
//    int c = 0;
//    for (var node : goodLands.getArr()) {
//      for (var neighbor : node.getNeighbors()) {
//        if (neighbor.getVal() == AIR) {
//          c++;
//        }
//      }
//    }
    
    System.out.println(goodLands.size());
  }

}