package com.tcd.aoc22.utils;

import java.util.ArrayList;
import java.util.List;

public class Grid<T> {
  private List<List<Node<T>>> grid = new ArrayList<>();
  
  public Node<T> getNode (Coord coord) {
    return grid.get(coord.y).get(coord.x);
  }
  
  public Node<T> getNode (int x, int y) {
    return grid.get(y).get(x);
  }
  
  public void addNode (Node<T> node) {
    int y = node.getCoord().y;
    
    if (grid.size() <= y) {
      grid.add(new ArrayList<>());
    }
    
    grid.get(y).add(node);
  }
  
  public int getWidth () {
    return grid.get(0).size();
  }
  
  public int getHeight () {
    return grid.size();
  }
}
