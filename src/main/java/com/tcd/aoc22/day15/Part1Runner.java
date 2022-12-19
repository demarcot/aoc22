package com.tcd.aoc22.day15;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.ArraySet;
import com.tcd.aoc22.utils.Coord;
import com.tcd.aoc22.utils.MiscUtils;
import com.tcd.aoc22.utils.Node;

public class Part1Runner {
  // TODO(Tom): ugly. this needs cleanup
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 15 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day15/d15p1.txt");
    // sensor can be Node<Integer> where val is mDist to beacon
    List<Node<Integer>> sensors = new ArrayList<>();
    // beacon can be Node<Integer> where val doesn't matter?
    ArraySet<Node<Integer>> beacons = new ArraySet<>();
    
    
    int smallestX = 0;
    int biggestX = 0;
    
    for (var line : lines) {
      var splitLine = line.replace("Sensor at ", "")
          .replace("closest beacon is at ", "")
          .replace("x=", "")
          .replace("y=", "")
          .split(": ");
      var splitSensorStr = splitLine[0].split(", ");
      var splitBeaconStr = splitLine[1].split(", ");
      
      var sensorNode = new Node<Integer>(new Coord(Integer.parseInt(splitSensorStr[0]), Integer.parseInt(splitSensorStr[1])), 0);
      var beaconNode = new Node<Integer>(new Coord(Integer.parseInt(splitBeaconStr[0]), Integer.parseInt(splitBeaconStr[1])), 0);
      sensorNode.setVal(sensorNode.getCoord().mDistance(beaconNode.getCoord()));
      beacons.add(beaconNode);
      sensorNode.addRelative("b", beacons.getItem(beaconNode));
      sensors.add(sensorNode);
      
      if (beaconNode.getCoord().x < smallestX) {
        smallestX = beaconNode.getCoord().x;
      }
      
      if (beaconNode.getCoord().x > biggestX) {
        biggestX = beaconNode.getCoord().x;
      }
      
      if ((sensorNode.getCoord().x - sensorNode.getVal()) < smallestX) {
        System.out.println("yep smol");
        smallestX = (sensorNode.getCoord().x - sensorNode.getVal());
      }
      
      if ((sensorNode.getCoord().x + sensorNode.getVal()) > biggestX) {
        System.out.println("yep big");
        biggestX = (sensorNode.getCoord().x + sensorNode.getVal());
      }
    }
    
    // Loop through coords on target y
    // if greater than all mDist, unknown
    // if less <= any mDist and != any beacon, inc counter
    System.out.println(sensors.size());
    System.out.println(beacons.size());
    int count = 0;
//    var inspectionRow = 10;
    var inspectionRow = 2_000_000;
    // +1 to include biggest?
    for (int x = smallestX; x < biggestX; x++) {
      var good = false;
      var n = new Node<Integer>(new Coord(x, inspectionRow), 0);
      
      
      if (isBadSpot(sensors, beacons, n)) {
        count++;
      }
    }
    System.out.println(smallestX + " - " + biggestX);
    System.out.println(count);
    
  }
  
  private static boolean isBadSpot (List<Node<Integer>> sensors, ArraySet<Node<Integer>> beacons, Node<Integer> n) {
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
}
