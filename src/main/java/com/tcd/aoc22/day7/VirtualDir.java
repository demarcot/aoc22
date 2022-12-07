package com.tcd.aoc22.day7;

import java.util.ArrayList;
import java.util.List;

// NOTE(Tom): I could have made dirs a type of file, but decided not to
public class VirtualDir {
  private String name;
  private VirtualDir parent;
  private List<VirtualDir> dirs = new ArrayList<>();
  private List<VirtualFile> files = new ArrayList<>();
  private int size = -1;
  
  public VirtualDir (String name, VirtualDir parent) {
    this.name = name;
    this.parent = parent;
  }
  
  public void addSubdir (VirtualDir dir) {
    this.dirs.add(dir);
  }
  
  public void addFile (VirtualFile file) {
    this.files.add(file);
  }
  
  public VirtualDir getSubdir (String name) {
    for (var dir : dirs) {
      if (dir.getName().equals(name)) {
        return dir;
      }
    }
    
    System.out.println("Couldn't find subdir: " + this.name + "->" + name);
    return null;
  }
  
  public String getName () {
    return this.name;
  }
  
  public VirtualDir getParent () {
    return this.parent;
  }
  
  public int getSize () {
    // Return saved size if already calculated
    if (this.size > -1) {
      return this.size;
    }
    
    this.size = calculateSize();
    
    return this.size;
  }
  
  private int calculateSize () {
    int size = 0;
    
    for (var f : files) {
      size += f.getSize();
    }
    
    for (var dir : dirs) {
      size += dir.getSize();
    }
    
    return size;
  }
}
