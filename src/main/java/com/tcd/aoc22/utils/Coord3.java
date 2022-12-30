package com.tcd.aoc22.utils;

public class Coord3 {
  public int x;
  public int y;
  public int z;
  
  public Coord3 (int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public int mDistance (Coord3 o) {
    var xDiff = this.x - o.x;
    var yDiff = this.y - o.y;
    var zDiff = this.z - o.z;
    if (xDiff < 0) {
      xDiff = -xDiff;
    }
    
    if (yDiff < 0) {
      yDiff = -yDiff;
    }
    
    if (zDiff < 0) {
      zDiff = -zDiff;
    }
    
    return xDiff + yDiff + zDiff;
  }
  
  public boolean isNeighbor (Coord3 o) {
    var d = this.mDistance(o);
    if (d == 1) {
      return true;
    } else if (d == 2 && this.x != o.x && this.y != o.y) {
      return true;
    } else {
      return false;
    }
  }
  
  public boolean isCardinalNeighbor (Coord3 o) {
    var d = this.mDistance(o);
    if (d == 1) {
      return true;
    }
    
    return false;
  }
  
  public Coord3 clone() {
    return new Coord3(this.x, this.y, this.z);
  }
  
  @Override
  public boolean equals (Object o) {
    var c = (Coord3)o;
    
    return this.x == c.x && this.y == c.y && this.z == c.z;
  }
  
  @Override
  public String toString () {
    return "(" + this.x + ", " + this.y + ", " + this.z + ")";
  }
  
}

