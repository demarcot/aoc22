package com.tcd.aoc22.utils;

import java.util.ArrayList;
import java.util.List;

public class Line {
  private List<Coord> coords = new ArrayList<>();
  
  public Line (Coord p1, Coord p2) {
    if (p1.x == p2.x) {
      if (p1.y < p2.y) {
        for (int i = 0; i <= p2.y - p1.y; i++) {
          coords.add(new Coord(p1.x, p1.y + i));
        }
      } else {
        for (int i = 0; i <= p1.y - p2.y; i++) {
          coords.add(new Coord(p1.x, p2.y + i));
        }
      }
      
    } else if (p1.y == p2.y) {
      if (p1.x < p2.x) {
        for (int i = 0; i <= p2.x - p1.x; i++) {
          coords.add(new Coord(p1.x + i, p1.y));
        }
      } else {
        for (int i = 0; i <= p1.x - p2.x; i++) {
          coords.add(new Coord(p2.x + i, p2.y));
        }
      }
    }
  }
  
  public List<Coord> getCoords () {
    return this.coords;
  }
  
}
