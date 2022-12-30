package com.tcd.aoc22.day18;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.Coord3;
import com.tcd.aoc22.utils.MiscUtils;

// 8167 - too high
public class Part1Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 18 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day18/d18p1.txt");

    List<Coord3> points = new ArrayList<>();
    for (var l : lines) {
      var splitStr = l.split(",");
      var x = Integer.parseInt(splitStr[0]);
      var y = Integer.parseInt(splitStr[1]);
      var z = Integer.parseInt(splitStr[2]);
      points.add(new Coord3(x, y, z));
    }

    int surfaceArea = 0;
    for (int i = 0; i < points.size(); i++) {
        int faces = 6;
        for (int j = 0; j < points.size(); j++) {
            if (i != j && points.get(i).isCardinalNeighbor(points.get(j))) {
                faces--;
            }
        }

        if (faces < 0) {
            System.out.println("shoot");
        }
        surfaceArea += faces;
    }

    System.out.println(surfaceArea);
  }

}
