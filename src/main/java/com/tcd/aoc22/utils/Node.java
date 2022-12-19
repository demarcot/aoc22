package com.tcd.aoc22.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node<T> implements Comparable<Node<T>> {
  private Coord coord;
  private T val;
  // TODO(Tom): added for a* prob. make subclass and move these fields to that
  public int f = 1000;
  public int g = 1000;
  public int h;
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
  
  public void setVal (T val) {
    this.val = val;
  }
  
  public void addRelative (String key, Node<T> node) {
    if (relatives.containsKey(key)) {
      relatives.replace(key, node);
    } else {      
      relatives.put(key, node);
    }
  }
  
  public void clearParents () {
    for (var r : relatives.values()) {
      r.removeRelative("parent");
    }
  }
  
  public Node<T> getRelative (String key) {
    return relatives.get(key);
  }
  
  public void removeRelative (String key) {
    if (relatives.containsKey(key)) {
      relatives.remove(key);
    }
  }
  
  public List<Node<T>> getNeighbors () {
    List<Node<T>> n = new ArrayList<>();
    var t = this.getRelative("up");
    if (null != t) {
      n.add(t);      
    }
    
    t = this.getRelative("down");
    if (null != t) {
      n.add(t);      
    }
    
    t = this.getRelative("left");
    if (null != t) {
      n.add(t);      
    }
    
    t = this.getRelative("right");
    if (null != t) {
      n.add(t);      
    }
    
    return n;
  }

  @Override
  public int compareTo(Node<T> o) {
    return Integer.compare(this.f, o.f);
  }
  
  public String toString () {
    return this.val.toString();
  }
  
  @Override
  public boolean equals (Object o) {
    var b = this.coord.x == ((Node<T>)o).getCoord().x && this.coord.y == ((Node<T>)o).coord.y; 
    return b;
  }
}
