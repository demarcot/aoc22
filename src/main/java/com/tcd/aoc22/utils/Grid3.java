package com.tcd.aoc22.utils;

import java.util.ArrayList;
import java.util.List;

public class Grid3<T> {
  private List<List<List<Node3<T>>>> grid = new ArrayList<>();
  
  public Node3<T> getNode (Coord3 coord) {
    return grid.get(coord.z).get(coord.y).get(coord.x);
  }
  
  public Node3<T> getNode (int x, int y, int z) {
    return grid.get(z).get(y).get(x);
  }
  
  public void init (int width, int height, int depth, T i) {
    for (int z = 0; z < depth; z++) {
      grid.add(new ArrayList<>());
      for (int y = 0; y < height; y++) {
        grid.get(z).add(new ArrayList<>());
        for (int x = 0; x < width; x++) {
          var n = new Node3<T>(x, y, z, i);
          
          if (x > 0) {
            var l = grid.get(z).get(y).get(x - 1);
            n.addRelative("left", l);
            l.addRelative("right", n);
          }
          
          if (y > 0) {
            var d = grid.get(z).get(y - 1).get(x);
            n.addRelative("down", d);
            d.addRelative("up", n);
          }
          
          if (z > 0) {
            var b = grid.get(z - 1).get(y).get(x);
            n.addRelative("back", b);
            b.addRelative("front", n);
          }
          
          grid.get(z).get(y).add(n);
        }
      }
    }
  }
  
  public int getDepth () {
    return grid.size();
  }
  
  public int getHeight () {
    return grid.get(0).size();
  }
  
  public int getWidth () {
    return grid.get(0).get(0).size();
  }
}
