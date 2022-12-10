package com.tcd.aoc22.day8;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.ArraySet;
import com.tcd.aoc22.utils.Coord;
import com.tcd.aoc22.utils.Grid;
import com.tcd.aoc22.utils.MiscUtils;
import com.tcd.aoc22.utils.Node;

public class Part1Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 8 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day8/d8p1.txt");
    
    // TODO(Tom): Finish converting to cleaner grid based solution
    Grid<Integer> grid = buildGrid(lines);
    
    ArraySet<Coord> visibleTrees = new ArraySet<>();
    
    
    visibleTrees.addAll(visibleHorizontal(lines));
    visibleTrees.addAll(visibleVertical(lines));
    
    System.out.println(visibleTrees.size());
  }
  
  private static Grid<Integer> buildGrid (List<String> treeRows) {
    int gridWidth = treeRows.get(0).length();
    int gridHeight = treeRows.size();
    
    Grid<Integer> grid = new Grid<>();
    
    for (int y = 0; y < gridHeight; y++) {
      for (int x = 0; x < gridWidth; x++) {
        int val = Integer.parseInt("" + treeRows.get(y).charAt(x));
        Node<Integer> node = new Node<>(new Coord(x, y), val);
        if (x > 0) {
          var l = grid.getNode(new Coord(x - 1, y));
          node.addRelative("left", l);
          l.addRelative("right", node);
        }
        
        if (y > 0) {
          var u = grid.getNode(new Coord(x, y - 1));
          node.addRelative("up", u);
          u.addRelative("down", node);
        }
        
        grid.addNode(node);
      }
    }
    
    return grid;
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
