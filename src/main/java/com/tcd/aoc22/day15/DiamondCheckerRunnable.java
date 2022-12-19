package com.tcd.aoc22.day15;

import java.util.List;
import java.util.concurrent.Callable;

import com.tcd.aoc22.utils.Coord;
import com.tcd.aoc22.utils.Node;

public class DiamondCheckerRunnable implements Callable<Boolean> {
  private int smallest;
  private int biggest;
  private int hOffset;
  private List<Node<Integer>> sensors;
  
  public DiamondCheckerRunnable (int smallest, int biggest, int hOffset, List<Node<Integer>> sensors) {
    this.smallest = smallest;
    this.biggest = biggest;
    this.hOffset = hOffset;
    this.sensors = sensors;
  }
  
  public Boolean call() {
    for (int y = hOffset; y < biggest && (y - hOffset) < 100; y++) {
      System.out.println(y);
      for (int x = smallest; x < biggest; x++) {
        var n = new Node<Integer>(new Coord(x, y), 0);

        if (isMaybeGood(sensors, n)) {
          System.out.println(n.getCoord().toString() + " - " + ((n.getCoord().x * 4000000) + n.getCoord().y));
          return true;
        }
      }
    }
    return false;
  }
  
  private boolean isMaybeGood(List<Node<Integer>> sensors, Node<Integer> n) {
    for (var s : sensors) {
      var nx = n.getCoord().x;
      var ny = n.getCoord().y;
      var x1 = s.getCoord().x - s.getVal();
      var y1 = s.getCoord().y;
      var x2 = s.getCoord().x;
      var y2 = s.getCoord().y + s.getVal();
      var x3 = s.getCoord().x;
      var y3 = s.getCoord().y - s.getVal();
//      var d = s.getCoord().mDistance(n.getCoord());
      // && d <= s.getVal()
      if (isPointInTriangle(nx, ny, x1, y1, x2, y2, x3, y3)) {
        return false;
      }
      
      x1 = s.getCoord().x + s.getVal();
      if (isPointInTriangle(nx, ny, x1, y1, x2, y2, x3, y3)) {
        return false;
      }
    }
    return true;
  }

  public boolean isPointInTriangle(double x, double y, double x1, double y1, double x2, double y2, double x3, double y3) {
// Calculate the area of the triangle
    double area = Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2);

// Calculate the area of the three smaller triangles
    double a1 = Math.abs((x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2)) / 2);
    double a2 = Math.abs((x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y)) / 2);
    double a3 = Math.abs((x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2)) / 2);

// If the sum of the areas of the smaller triangles is equal to the
// area of the original triangle, the point is inside the triangle.
// If the sum is equal to zero, the point is on the boundary.
//    return (a1 + a2 + a3 == area) || (a1 + a2 + a3 == 0);
    return (a1 + a2 + a3 == area);
  }
}
