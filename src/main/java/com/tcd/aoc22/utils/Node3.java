package com.tcd.aoc22.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node3<T> implements Comparable<Node3<T>> {
  private Coord3 coord;
  private T val;
  private Map<String, Node3<T>> relatives = new HashMap<>();
  
  public Node3 (Coord3 coord, T val) {
    this.coord = coord;
    this.val = val;
  }
  
  public Node3 (int x, int y, int z, T val) {
    this.coord = new Coord3(x, y, z);
    this.val = val;
  }
  
  public Coord3 getCoord () {
    return this.coord;
  }
  
  public T getVal () {
    return this.val;
  }
  
  public void setVal (T val) {
    this.val = val;
  }
  
  public void addRelative (String key, Node3<T> node) {
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
  
  public Node3<T> getRelative (String key) {
    return relatives.get(key);
  }
  
  public void removeRelative (String key) {
    if (relatives.containsKey(key)) {
      relatives.remove(key);
    }
  }
  
  public List<Node3<T>> getNeighbors () {
    List<Node3<T>> n = new ArrayList<>();
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
    
    t = this.getRelative("front");
    if (null != t) {
      n.add(t);
    }
    
    t = this.getRelative("back");
    if (null != t) {
      n.add(t);
    }
    
    return n;
  }

  @Override
  public int compareTo(Node3<T> o) {
    return Integer.compare((Integer)this.val, (Integer)o.val);
  }
  
  public String toString () {
    return this.val.toString();
  }
  
  @Override
  public boolean equals (Object o) {
    var b = this.coord.x == ((Node3<T>)o).coord.x && this.coord.y == ((Node3<T>)o).coord.y && this.coord.z == ((Node3<T>)o).coord.z; 
    return b;
  }
}
