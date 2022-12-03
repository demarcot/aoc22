package com.tcd.aoc22.day2;

import java.net.URISyntaxException;
import java.util.List;

import com.tcd.aoc22.utils.MiscUtils;

public class Part2Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 2 Part 2 ---");

    // Read file
    List<String> lines = MiscUtils.ReadFile("day2/d2p1.txt");
    int pScore = 0;

    for (String line : lines) {
      String[] handArr = line.split(" ");

      HandEnum2 oppHand = HandEnum2.fromCode(handArr[0]);
      GameResultEnum pResult = GameResultEnum.fromCode(handArr[1]);

      HandEnum2 pHand = oppHand.lookupCardFromOpposingResult(pResult);

      pScore += pHand.getVal() + pResult.getVal();
    }

    System.out.println(pScore);
  }
}
