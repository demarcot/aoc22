package com.tcd.aoc22.day7;

public class VirtualFile {
  private String name; // Don't care about name yet
  private VirtualDir parent; // Prob won't come into play at all
  private int size;
  
  public VirtualFile (String name, VirtualDir parent, int size) {
    this.name = name;
    this.parent = parent;
    this.size = size;
  }
  
  public int getSize () {
    return this.size;
  }
}
