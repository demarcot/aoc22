package com.tcd.aoc22.day8;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.Coord;
import com.tcd.aoc22.utils.MiscUtils;

public class Part2Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 8 Part 2 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day8/d8p1.txt");
    
    
    System.out.println(findBestScore(lines));
  }
  
  private static int findBestScore (List<String> treeRows) {
    int gridWidth = treeRows.get(0).length();
    int gridHeight = treeRows.size();
    
    int highScore = 0;
    
    for (int y = 0; y < gridHeight; y++) {
      for (int x = 0; x < gridWidth; x++) {
        int currHeight = Integer.parseInt("" + treeRows.get(y).charAt(x));
        int currScore = getScore(new Coord(x, y), currHeight, treeRows); 
        if (currScore > highScore) {
          highScore = currScore;
        }
      }
    }
    
    return highScore;
  }
  
  private static int getScore (Coord coord, int height, List<String> treeRows) {
    int gridWidth = treeRows.get(0).length();
    int gridHeight = treeRows.size();
    int right = 0;
    int left = 0;
    int up = 0;
    int down = 0;
    
    int currHeight;
    for (int x = coord.x + 1; x < gridWidth; x++) {
      right++;
      currHeight = Integer.parseInt("" + treeRows.get(coord.y).charAt(x));
      if (currHeight >= height) {
        break;
      }
    }
    
    for (int x = coord.x - 1; x >= 0; x--) {
      left++;
      currHeight = Integer.parseInt("" + treeRows.get(coord.y).charAt(x));
      if (currHeight >= height) {
        break;
      }
    }
    
    for (int y = coord.y + 1; y < gridHeight; y++) {
      up++;
      currHeight = Integer.parseInt("" + treeRows.get(y).charAt(coord.x));
      if (currHeight >= height) {
        break;
      }
    }
    
    for (int y = coord.y - 1; y >= 0; y--) {
      down++;
      currHeight = Integer.parseInt("" + treeRows.get(y).charAt(coord.x));
      if (currHeight >= height) {
        break;
      }
    }
    
    return right * left * up * down;
  }
  
  private static List<Coord> visibleHorizontal (List<String> treeRows) {
    int gridWidth = treeRows.get(0).length();
    int gridHeight = treeRows.size();
    
    List<Coord> visibleTrees = new ArrayList<>();
    
    for (int y = 0; y < gridHeight; y++) {
      int prevHeight = -1;
      for (int x = 0; x < gridWidth; x++) {
        int currHeight = Integer.parseInt("" + treeRows.get(y).charAt(x));
        if (prevHeight < currHeight) {          
          visibleTrees.add(new Coord(x, y));
          prevHeight = currHeight;
        } else {
          continue;
        }
      }
      
      prevHeight = -1;
      for (int x = gridWidth - 1; x >= 0 ; x--) {
        int currHeight = Integer.parseInt("" + treeRows.get(y).charAt(x));
        if (prevHeight < currHeight) {
          visibleTrees.add(new Coord(x, y));
          prevHeight = currHeight;
        } else {
          continue;
        }
      }
    }
    
    return visibleTrees;
  }
  
  private static List<Coord> visibleVertical (List<String> treeRows) {
    int gridWidth = treeRows.get(0).length();
    int gridHeight = treeRows.size();
    
    List<Coord> visibleTrees = new ArrayList<>();
    
    for (int x = 0; x < gridWidth; x++) {
      int prevHeight = -1;
      for (int y = 0; y < gridHeight; y++) {
        int currHeight = Integer.parseInt("" + treeRows.get(y).charAt(x));
        if (prevHeight < currHeight) {
          visibleTrees.add(new Coord(x, y));
          prevHeight = currHeight;
        } else {
          continue;
        }
      }
      
      prevHeight = -1;
      for (int y = gridHeight - 1; y >= 0; y--) {
        int currHeight = Integer.parseInt("" + treeRows.get(y).charAt(x));
        if (prevHeight < currHeight) {
          visibleTrees.add(new Coord(x, y));
          prevHeight = currHeight;
        } else {
          continue;
        }
      }      
    }
    
    return visibleTrees;
  }
}
