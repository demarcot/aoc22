package com.tcd.aoc22.day13;

import java.util.ArrayList;
import java.util.List;

public class PacketSegment {
  private Integer val = null;
  private List<PacketSegment> segments = new ArrayList<>();
  
  public PacketSegment () {}
  public PacketSegment (int val) {
    this.val = val;
  }
  
  public Integer getVal () {
    return this.val;
  }
  
  public List<Integer> getVals () {
    List<Integer> vals = new ArrayList<>();
    if (null != val) {
      vals.add(val);
      return vals;
    }
    
    for (var s : segments) {
      vals.addAll(s.getVals());
    }
    
    return vals;
  }
  
  public void addSegment (PacketSegment s) {
    segments.add(s);
  }
  
  public List<PacketSegment> getSegments () {
    return this.segments;
  }
  
  public String toString () {
    if (null != val) {
      return "_" + val + "_";
    }
    
    String s = "";
    for (var p : segments) {
      s += "["+ p.toString() + "]";
    }
    return s;
  }
}
