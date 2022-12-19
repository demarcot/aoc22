package com.tcd.aoc22.day12;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.tcd.aoc22.utils.Coord;
import com.tcd.aoc22.utils.Grid;
import com.tcd.aoc22.utils.MiscUtils;
import com.tcd.aoc22.utils.Node;

public class Part2Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 12 Part 2 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day12/d12p1.txt");
    
    // TODO(Tom): Finish converting to cleaner grid based solution
    Grid<Character> grid = buildGrid(lines);
    List<Node<Character>> starts = findStarts(grid);
    Node<Character> goal = findGoal(grid);
    
    
    
    List<Integer> costs = new ArrayList<>();
    for (var s : starts) {
      for (int y = 0; y < grid.getHeight(); y++) {
        for (int x = 0; x < grid.getWidth(); x++) {
          var n = grid.getNode(new Coord(x, y));
          n.f = 1000;
          n.g = 1000;
          n.clearParents();
          if (n.getVal().equals('E')) {
            n.h = 0;
          } else {
            n.h = n.getCoord().mDistance(goal.getCoord());
          }
        }
      }
      
      costs.add(aStar(s, goal));
    }
    
    int smallestSteps = 1000;
    for (var c : costs) {
      
      if (null != c && c < smallestSteps) {
        smallestSteps = c;
      }
    }
    
    
    System.out.println(smallestSteps);
  }
  
  private static Grid<Character> buildGrid (List<String> gridRows) {
    int gridWidth = gridRows.get(0).length();
    int gridHeight = gridRows.size();
    
    Grid<Character> grid = new Grid<>();
    
    for (int y = 0; y < gridHeight; y++) {
      for (int x = 0; x < gridWidth; x++) {
        var val = gridRows.get(y).charAt(x);
        Node<Character> node = new Node<>(new Coord(x, y), val);
        if (x > 0) {
          var l = grid.getNode(new Coord(x - 1, y));
          var nVal = getNormalizedVal(node);
          var lVal = getNormalizedVal(l);
          
          if (nVal - lVal < 2) {
            l.addRelative("right", node);
          }

          if (lVal - nVal < 2) {            
            node.addRelative("left", l); 
          }
           

//          if (nVal - lVal == 0 || nVal - lVal == 1 || lVal - nVal == 1) {            
//            node.addRelative("left", l); 
//            l.addRelative("right", node);
//          }
        }
        
        if (y > 0) {
          var u = grid.getNode(new Coord(x, y - 1));
          int nVal = getNormalizedVal(node);
          int uVal = getNormalizedVal(u);
          
          if (nVal - uVal < 2) {
            u.addRelative("down", node);
          }

          if (uVal - nVal < 2) {            
            node.addRelative("up", u); 
          }
          
//          if (nVal - uVal == 0 || nVal - uVal == 1 || uVal - nVal == 1) {  
//            node.addRelative("up", u);
//            u.addRelative("down", node);            
//          }
        }
        
        grid.addNode(node);
      }
    }
    
    return grid;
  }
  
  private static Node<Character> findStart (Grid<Character> grid) {
    for (int y = 0; y < grid.getHeight(); y++) {
      for (int x = 0; x < grid.getWidth(); x++) {
        var n = grid.getNode(new Coord(x, y));
        if (n.getVal().equals('S')) {
          return n;
        }
      }
    }
    
    System.out.println("No start detected");
    return null;
  }
  
  private static List<Node<Character>> findStarts (Grid<Character> grid) {
    List<Node<Character>> starts = new ArrayList<>();
    for (int y = 0; y < grid.getHeight(); y++) {
      for (int x = 0; x < grid.getWidth(); x++) {
        var n = grid.getNode(new Coord(x, y));
        if (n.getVal().equals('a')) {
          starts.add(n);
        }
      }
    }
    
    return starts;
  }
  
  private static Node<Character> findGoal (Grid<Character> grid) {
    for (int y = 0; y < grid.getHeight(); y++) {
      for (int x = 0; x < grid.getWidth(); x++) {
        var n = grid.getNode(new Coord(x, y));
        if (n.getVal().equals('E')) {
          return n;
        }
      }
    }
    
    System.out.println("No goal detected");
    return null;
  }
  
  public static Integer aStar(Node<Character> start, Node<Character> target){
    
    PriorityQueue<Node<Character>> closedList = new PriorityQueue<>();
    PriorityQueue<Node<Character>> openList = new PriorityQueue<>();

    start.g = 0;
    start.f = start.g + 1;
    start.h = start.getCoord().mDistance(target.getCoord());
    openList.add(start);

    while(!openList.isEmpty()){
        Node<Character> n = openList.peek();
        if(n == target){
          int steps = -1;
          boolean hasParent = true;
          while (hasParent) {
            var parent = n.getRelative("parent");
            if (null == parent) {
              hasParent = false;
            }
            
            n = parent;
            steps++;
          }
            return steps;
        }

        for(var neighbor : n.getNeighbors()){
            int totalWeight = n.g + 1;

            if(!openList.contains(neighbor) && !closedList.contains(neighbor)){
              neighbor.addRelative("parent", n);
              neighbor.g = totalWeight;
              neighbor.f = neighbor.g + neighbor.getCoord().mDistance(target.getCoord());
                openList.add(neighbor);
            } else {
                if(totalWeight < neighbor.g){
                  neighbor.addRelative("parent", n);
                  neighbor.g = totalWeight;
                  neighbor.f = neighbor.g + neighbor.getCoord().mDistance(target.getCoord());

                    if(closedList.contains(neighbor)){
                        closedList.remove(neighbor);
                        openList.add(neighbor);
                    }
                }
            }
        }

        openList.remove(n);
        closedList.add(n);
    }
    
    System.out.println("noooo");
    return null;
}
  
  public static int getNormalizedVal (Node<Character> node) {
    int v;
    if (node.getVal().equals('S')) {
      v = MiscUtils.charToNormalizedInt('a');
    } else if (node.getVal().equals('E')) {
      v = MiscUtils.charToNormalizedInt('z');
    } else {
      v = MiscUtils.charToNormalizedInt(node.getVal());
    }
    return v;
  }
}
