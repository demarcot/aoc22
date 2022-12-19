package com.tcd.aoc22.day14;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.Coord;
import com.tcd.aoc22.utils.Grid;
import com.tcd.aoc22.utils.Line;
import com.tcd.aoc22.utils.MiscUtils;
import com.tcd.aoc22.utils.Node;

public class Part2Runner {
  private final static int AIR = 0;
  private final static int ROCK = 1;
  private final static int SAND = 2;
  
  // TODO(Tom): ugly. break this up
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 14 Part 2 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day14/d14p1.txt");
    
    Grid<Integer> grid = new Grid<>();
    List<Line> rockFormations = new ArrayList<>();
    int smallestX = 1000;
    int biggestX = 0;
    int biggestY = 0;
    
    for (var l : lines) {
      var splitLine = l.split(" -> ");
      for (int i = 0; i < splitLine.length - 1; i++) {
        var start = splitLine[i].split(",");
        var end  = splitLine[i+1].split(",");
        
        var startCoord = new Coord(Integer.parseInt(start[0]), Integer.parseInt(start[1]));
        var endCoord = new Coord(Integer.parseInt(end[0]), Integer.parseInt(end[1]));
        
        if (startCoord.x < smallestX) {
          smallestX = startCoord.x;
        }
        
        if (endCoord.x < smallestX) {
          smallestX = endCoord.x;
        }
        
        if (startCoord.x > biggestX) {
          biggestX = startCoord.x;
        }
        
        if (endCoord.x > biggestX) {
          biggestX = endCoord.x;
        }
        
        if (startCoord.y > biggestY) {
          biggestY = startCoord.y;
        }
        if (endCoord.y > biggestY) {
          biggestY = endCoord.y;
        }
        rockFormations.add(new Line(startCoord, endCoord));
      }
    }
//    System.out.println(biggestY + 2);
//    System.out.println((2.0 / Math.sqrt(3) * (biggestY + 2)));
    
    for (int y = 0; y < biggestY + 3; y++) {
      for (int x = 0; x < biggestX + 200; x++) {
        var n = new Node<Integer>(new Coord(x, y), AIR);
        if (x != 0) {
          var l = grid.getNode(x - 1, y);          
          n.addRelative("left", l);
          l.addRelative("right", n);
        }
        
        if (y != 0) {
          var u = grid.getNode(x, y - 1);  
          n.addRelative("up", u);
          u.addRelative("down", n);
        }
        
        grid.addNode(n);
      }
    }
    
    var h = grid.getHeight() - 1;
    for (int x = 0; x < grid.getWidth(); x++) {
      var n = grid.getNode(x, h);
      n.setVal(ROCK);
    }
    
    for (var formation : rockFormations) {
      for (var c : formation.getCoords()) {
        grid.getNode(c.x, c.y).setVal(ROCK);
      }
    }
    
    int sandCount = 0;
    boolean isRunning = true;
    boolean isSimulating = true;
    while (isRunning) {
      sandCount++;
      isSimulating = true;
      
      var sandNode = grid.getNode(500, 0);
      
      if (sandNode.getVal() == SAND) {
        isRunning = false;
        for (int y = 0; y < grid.getHeight(); y++) {
          String r = "";
          for (int x = 0; x < grid.getWidth(); x++) {
            var n = grid.getNode(x, y);
            if (n.getVal() == AIR) {
              r += '.';
            } else if (n.getVal() == ROCK) {
              r += '#';
            } else if (n.getVal() == SAND) {
              r += 'O';
            }
          }
          
          System.out.println(r);
        }
        System.out.println(sandCount);
        continue;
      }
      
      while (isSimulating) {
        var sandCoord = sandNode.getCoord();
//        if ((sandCoord.y + 1) > biggestY) {
//          isSimulating = false;
//          System.out.println(sandCount);
//          continue;
//        }
        
        var d = sandNode.getRelative("down");
        if (null == d) {
          System.out.println(sandCount);
          for (int y = 0; y < grid.getHeight(); y++) {
            String r = "";
            for (int x = 0; x < grid.getWidth(); x++) {
              var n = grid.getNode(x, y);
              if (n.getVal() == AIR) {
                r += '.';
              } else if (n.getVal() == ROCK) {
                r += '#';
              } else if (n.getVal() == SAND) {
                r += 'O';
              }
            }
            
            System.out.println(r);
          }
          return;
        }
        
        if (d.getVal() == AIR) {
          sandNode = d;
          continue;
        }
        
        var dl = d.getRelative("left");
        if (null == dl) {
          System.out.println("bad left" + sandCount);
          for (int y = 0; y < grid.getHeight(); y++) {
            String r = "";
            for (int x = 0; x < grid.getWidth(); x++) {
              var n = grid.getNode(x, y);
              if (n.getVal() == AIR) {
                r += '.';
              } else if (n.getVal() == ROCK) {
                r += '#';
              } else if (n.getVal() == SAND) {
                r += 'O';
              }
            }
            
            System.out.println(r);
          }
          return;
        }
        
        if (dl.getVal() == AIR) {
          sandNode = dl;
          continue;
        }
        
        var dr = d.getRelative("right");
        if (null == dr) {
          System.out.println("bad right" + sandCount);
          return;
        }
        
        if (dr.getVal() == AIR) {
          sandNode = dr;
          continue;
        }
        
//        System.out.println(sandNode.getCoord().toString() + " is sand now");
        sandNode.setVal(SAND);
        isSimulating = false;
      }
    }
    
    System.out.println("Smallest X: " + smallestX + ", Biggest X: " + biggestX + ", Biggest Y: " + biggestY);
  }
  
}
