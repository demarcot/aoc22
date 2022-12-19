package com.tcd.aoc22.day17;

import java.net.URISyntaxException;
import java.util.List;

import com.tcd.aoc22.utils.Coord;
import com.tcd.aoc22.utils.MiscUtils;
import com.tcd.aoc22.utils.Node;

//TODO(Tom): haven't started
public class Part2Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 17 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day17/d17p1.txt");

    Shape obj = new Shape(new Node<Integer>(new Coord(3, -1), 0));
        
    int commandIndex = 0;
    for (int i = 0; i < 2022; i++) {
      boolean isFalling = true;
      
      Shape fallingObj = null;
      if (i % 5 == 0) {
        fallingObj = genHLine(obj);
      } else if (i % 5 == 1) {
        fallingObj = genCross(obj);
      } else if (i % 5 == 2) {
        fallingObj = genBL(obj);
      } else if (i % 5 == 3) {
        fallingObj = genVLine(obj);
      } else if (i % 5 == 4) {
        fallingObj = genBox(obj);
      }
      
//      System.out.println(fallingObj.getBot().getCoord().toString());
      
      while(isFalling) {
        // sim movement
        if (lines.get(0).charAt(commandIndex) == '<' && canMove(fallingObj, obj, -1, 0)) {
          fallingObj.move(-1, 0);
        } else if (lines.get(0).charAt(commandIndex) == '>' && canMove(fallingObj, obj, 1, 0)) {
          fallingObj.move(1, 0);
        }
        
        commandIndex++;
        commandIndex = commandIndex % lines.get(0).length();
        
        if (canMove(fallingObj, obj, 0, -1)) {
          fallingObj.move(0, -1);
        } else {
          obj.addNodes(fallingObj.getNodes());
          isFalling = false;
//          System.out.println(obj);
        }
      }
    }
    
    // Create obj types
    // Create empty grid, 7 width (dynamic height?)
    // Gen obj (mod by 4 to get proper obj type)
    // Move dir of input
    // Check nodes below for collision
    // Move or set nodes/neighbors (collect into one obj w all neighbors?)
    // (every obj has a top node and when settle, you update w that?)
    System.out.println(obj.getTop().getCoord().y);
    System.out.println(obj.toString());
  }

  // Order:
  // --
  // +
  // _|
  // |
  // #
  
  private static Shape genHLine (Shape curShape) {  
    int curY = (null != curShape) ? curShape.getTop().getCoord().y : 0;
    //(0 + 2, prevTop.y + 4), (0 + 2 + 1, prevTop.y + 4), (0 + 2 + 2, prevTop.y + 4), (0 + 2 + 4, prevTop.y + 4)
    var root = new Node<Integer>(new Coord(0 + 2, curY + 4), 1);
    Shape hLine = new Shape(root);
    
    hLine.addNode(new Node<Integer>(new Coord(0 + 2 + 1, curY + 4), 1));
    hLine.addNode(new Node<Integer>(new Coord(0 + 2 + 2, curY + 4), 1));
    hLine.addNode(new Node<Integer>(new Coord(0 + 2 + 3, curY + 4), 1));
    
    return hLine;
  }
  
  private static Shape genCross (Shape curShape) {
    int curY = (null != curShape) ? curShape.getTop().getCoord().y : -1;
    //(0 + 2 + 1, prevTop.y + 4 + 2)
    //(0 + 2, prevTop.y + 4 + 1), (0 + 2 + 1, prevTop.y + 4 + 1), (0 + 2 + 2, prevTop.y + 4 + 1)
    //(0 + 2 + 1, prevTop.y + 4)
    var root = new Node<Integer>(new Coord(0 + 2 + 1, curY + 4), 1);
    Shape cross = new Shape(root);
    
    // mid
    cross.addNode(new Node<Integer>(new Coord(0 + 2 + 1, curY + 4 + 1), 1));
    cross.addNode(new Node<Integer>(new Coord(0 + 2, curY + 4 + 1), 1));
    cross.addNode(new Node<Integer>(new Coord(0 + 2 + 2, curY + 4 + 1), 1));
    
    // top
    cross.addNode(new Node<Integer>(new Coord(0 + 2 + 1, curY + 4 + 2), 1));
    return cross;
  }
  
  private static Shape genBL (Shape curShape) {
    int curY = (null != curShape) ? curShape.getTop().getCoord().y : 0;
    //(0 + 2 + 2, prevTop.y + 4 + 2)
    //(0 + 2 + 2, prevTop.y + 4 + 1)
    //(0 + 2, prevTop.y + 4), (0 + 2 + 1, prevTop.y + 4), (0 + 2 + 2, prevTop.y + 4)
    var root = new Node<Integer>(new Coord(0 + 2, curY + 4), 1);
    Shape bL = new Shape(root);
    
    bL.addNode(new Node<Integer>(new Coord(0 + 2 + 1, curY + 4), 1));
    bL.addNode(new Node<Integer>(new Coord(0 + 2 + 2, curY + 4), 1));
    bL.addNode(new Node<Integer>(new Coord(0 + 2 + 2, curY + 4 + 1), 1));
    bL.addNode(new Node<Integer>(new Coord(0 + 2 + 2, curY + 4 + 2), 1));
    
    return bL;
  }
  
  private static Shape genVLine (Shape curShape) {
    int curY = (null != curShape) ? curShape.getTop().getCoord().y : 0;
    //(0 + 2, prevTop.y + 4), (0 + 2, prevTop.y + 4 + 1), (0 + 2, prevTop.y + 4 + 2), (0 + 2, prevTop.y + 4 + 4)
    var root = new Node<Integer>(new Coord(0 + 2, curY + 4), 1);
    Shape vLine = new Shape(root);
    
    vLine.addNode(new Node<Integer>(new Coord(0 + 2, curY + 4 + 1), 1));
    vLine.addNode(new Node<Integer>(new Coord(0 + 2, curY + 4 + 2), 1));
    vLine.addNode(new Node<Integer>(new Coord(0 + 2, curY + 4 + 3), 1));
    
    return vLine;
  }
  
  private static Shape genBox (Shape curShape) {
    int curY = (null != curShape) ? curShape.getTop().getCoord().y : 0;
    //(0 + 2, prevTop.y + 4), (0 + 2 + 1, prevTop.y + 4), (0 + 2, prevTop.y + 4 + 1), (0 + 2 + 1, prevTop.y + 4 + 1)
    var root = new Node<Integer>(new Coord(0 + 2, curY + 4), 1);
    Shape box = new Shape(root);
    
    box.addNode(new Node<Integer>(new Coord(0 + 2 + 1, curY + 4), 1));
    box.addNode(new Node<Integer>(new Coord(0 + 2, curY + 4 + 1), 1));
    box.addNode(new Node<Integer>(new Coord(0 + 2 + 1, curY + 4 + 1), 1));
    
    return box;
  }
  
  private static boolean canMove (Shape mover, Shape terrain, int xDiff, int yDiff) {
    if (mover.getLeft().getCoord().x + xDiff < 0 ||
        mover.getRight().getCoord().x + xDiff > 6 ||
        mover.getBot().getCoord().y + yDiff < 0) {
      return false;
    }
    
    // NOTE(Tom): this short circuit logic is wrong I guess
//    if ((mover.getLeft().getCoord().x + xDiff > terrain.getRight().getCoord().x || mover.getRight().getCoord().x + xDiff < terrain.getLeft().getCoord().x) ||
//        (mover.getLeft().getCoord().y + yDiff > terrain.getRight().getCoord().y || mover.getRight().getCoord().y + yDiff < terrain.getLeft().getCoord().y)) {
//      return true;
//    }
    
    for (var tn : terrain.getNodes()) {
      for (var mn : mover.getNodes() ) {
        var tCoord = new Coord(mn.getCoord().x + xDiff, mn.getCoord().y + yDiff);
        if (tn.getVal() != 0 && tCoord.equals(tn.getCoord())) {
          return false;
        }
      }
    }
    
    return true;
  }
}
