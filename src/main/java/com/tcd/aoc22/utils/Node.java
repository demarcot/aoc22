package com.tcd.aoc22.utils;

import java.util.HashMap;
import java.util.Map;

public class Node<T> {
  private Coord coord;
  private T val;
  private Map<String, Node<T>> relatives = new HashMap<>();
  
  public Node (Coord coord, T val) {
    this.coord = coord;
    this.val = val;
  }
  
  public Coord getCoord () {
    return this.coord;
  }
  
  public T getVal () {
    return this.val;
  }
  
  public void addRelative (String key, Node<T> node) {
    relatives.put(key, node);
  }
  
  public Node<T> getRelative (String key) {
    return relatives.get(key);
  }
}
