package com.tcd.aoc22.day15;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.tcd.aoc22.utils.ArraySet;
import com.tcd.aoc22.utils.Coord;
import com.tcd.aoc22.utils.MiscUtils;
import com.tcd.aoc22.utils.Node;

// NOTE(Tom): not done
public class Part2Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 15 Part 2 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day15/d15p1.txt");
    // sensor can be Node<Integer> where val is mDist to beacon
    List<Node<Integer>> sensors = new ArrayList<>();
    // beacon can be Node<Integer> where val doesn't matter?
    ArraySet<Node<Integer>> beacons = new ArraySet<>();

    int smallest = 0;
//    int biggest = 20;
    int biggest = 4_000_000;

    for (var line : lines) {
      var splitLine = line.replace("Sensor at ", "").replace("closest beacon is at ", "").replace("x=", "")
          .replace("y=", "").split(": ");
      var splitSensorStr = splitLine[0].split(", ");
      var splitBeaconStr = splitLine[1].split(", ");

      var sensorNode = new Node<Integer>(
          new Coord(Integer.parseInt(splitSensorStr[0]), Integer.parseInt(splitSensorStr[1])), 0);
      var beaconNode = new Node<Integer>(
          new Coord(Integer.parseInt(splitBeaconStr[0]), Integer.parseInt(splitBeaconStr[1])), 0);
      sensorNode.setVal(sensorNode.getCoord().mDistance(beaconNode.getCoord()));
      beacons.add(beaconNode);
      sensorNode.addRelative("b", beacons.getItem(beaconNode));
      sensors.add(sensorNode);

    }

    // Loop through coords on target y
    // if greater than all mDist, unknown
    // if less <= any mDist and != any beacon, inc counter
//    System.out.println(sensors.size());
//    System.out.println(beacons.size());
    // +1 to include biggest?
//    for (int y = 0; y < biggest; y++) {
//      System.out.println(y);
//      for (int x = smallest; x < biggest; x++) {
//        var n = new Node<Integer>(new Coord(x, y), 0);
//
//        if (isMaybeGood(sensors, beacons, n)) {
//          System.out.println(n.getCoord().toString() + " - " + ((n.getCoord().x * 4000000) + n.getCoord().y));
//        }
//      }
//    }
    
    // NOTE(Tom): optimization attempt. helped a good amount, but orders of magnitude too slow still. need new approach
    ExecutorService executor = Executors.newFixedThreadPool(5);
    //31500
    for (int y = 5000; y < biggest; y += 500) {
      List<Future<Boolean>> futures = new ArrayList<>();
      for (int z = 0; z < 5; z++) {
        futures.add(executor.submit(new DiamondCheckerRunnable(0, biggest, y + (z*100), sensors)));
      }
      for (var f : futures) {
        try {
          var done = f.get();
          if (done) {
            executor.shutdown();
            return;
          }
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (ExecutionException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
//    System.out.println(smallest + " - " + biggest);
//    System.out.println(count);

  }

  private static boolean isBadSpot(List<Node<Integer>> sensors, ArraySet<Node<Integer>> beacons, Node<Integer> n) {
    for (var s : sensors) {
      var d = s.getCoord().mDistance(n.getCoord());

      if (d <= 0 || s.getVal() <= 0) {
        System.out.println("?? - " + d + ", " + s.getVal());
      }

      if (d <= s.getVal() && !beacons.contains(n)) {
        return true;
      }
    }
    return false;
  }

  private static boolean isMaybeGood(List<Node<Integer>> sensors, ArraySet<Node<Integer>> beacons, Node<Integer> n) {
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

  // NOTE(Tom): optimization attempt. didn't help enough
  public static boolean isPointInTriangle(double x, double y, double x1, double y1, double x2, double y2, double x3, double y3) {
    double area = Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2);

    double a1 = Math.abs((x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2)) / 2);
    double a2 = Math.abs((x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y)) / 2);
    double a3 = Math.abs((x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2)) / 2);

    return (a1 + a2 + a3 == area);
  }

}
