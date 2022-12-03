package com.tcd.aoc22.day2;

import java.net.URISyntaxException;
import java.util.List;

import com.tcd.aoc22.utils.MiscUtils;

public class Part1Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 2 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day2/d2p1.txt");
    int pScore = 0;
    
    for (String line : lines) {
      String[] handArr = line.split(" ");
      
      HandEnum oppHand = HandEnum.fromCode(handArr[0]);
      HandEnum pHand = HandEnum.fromCode(handArr[1]);
      
      GameResultEnum pResult = pHand.getGameResult(oppHand);
      
      pScore += pHand.getVal() + pResult.getVal();
    }
    
    System.out.println(pScore);
  }
}
