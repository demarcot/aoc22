package com.tcd.aoc22.utils;

public class Coord {
  public int x;
  public int y;
  
  public Coord (int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public int mDistance (Coord o) {
    var xDiff = this.x - o.x;
    var yDiff = this.y - o.y;
    if (xDiff < 0) {
      xDiff = -xDiff;
    }
    
    if (yDiff < 0) {
      yDiff = -yDiff;
    }
    
    return xDiff + yDiff;
  }
  
  public boolean isNeighbor (Coord o) {
    var d = this.mDistance(o);
    if (d == 1) {
      return true;
    } else if (d == 2 && this.x != o.x && this.y != o.y) {
      return true;
    } else {
      return false;
    }
  }
  
  public Coord clone() {
    return new Coord(this.x, this.y);
  }
  
  @Override
  public boolean equals (Object o) {
    var c = (Coord)o;
    
    return this.x == c.x && this.y == c.y;
  }
  
  @Override
  public String toString () {
    return "(" + this.x + ", " + this.y + ")";
  }
  
}
