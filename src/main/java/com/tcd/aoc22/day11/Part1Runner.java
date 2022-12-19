package com.tcd.aoc22.day11;

import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.MiscUtils;

public class Part1Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 11 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day11/sample.txt");
    
    List<Monkey> monkeyList = new ArrayList<>();
    for (int i = 0; i < (lines.size() / 7) + 1; i++) {
      monkeyList.add(new Monkey(i));
    }
    
    List<Long> divs = new ArrayList<>();
    
    Monkey currMonkey;
    for (int i = 1, m = 0; i < lines.size(); i += 7, m++) {
      currMonkey = monkeyList.get(m);
      
      var startingItemsLine = lines.get(i);
      var items = startingItemsLine.split(": ")[1].split(", ");
      for (String item : items) {
        currMonkey.addItem(new BigInteger(item));
      }
      
      var opLine = lines.get(i + 1);
      var opStrs = opLine.split("old ")[1].split(" ");
      var opChar = opStrs[0];        
      var opVal = opStrs[1];
      var opValBI = opVal.equals("old") ? null : new BigInteger(opVal);
      
      if (opChar.equals("+")) {
        currMonkey.setOp((old) -> {
          if (opVal.equals("old")) {            
            return old.add(old); 
          } else {
            return old.add(opValBI); 
          }
        });
      } else {
        currMonkey.setOp((old) -> {
          if (opVal.equals("old")) {            
            return old.multiply(old); 
          } else {
            return old.multiply(opValBI); 
          }
        });
      }
      
      var testLine = lines.get(i + 2);
      var testValBI = new BigInteger(testLine.split("by ")[1]);
      divs.add(Long.parseLong(testLine.split("by ")[1]));
      currMonkey.setDiv(divs.get(divs.size() - 1));
      currMonkey.setTest((v) -> { return v.mod(testValBI).compareTo(BigInteger.ZERO) == 0; });
      
      var targetLine = lines.get(i + 3);
      var target = monkeyList.get(Integer.parseInt(targetLine.split("monkey ")[1]));
      currMonkey.setTarget(target);
      
      var altTargetLine = lines.get(i + 4);
      var altTarget = monkeyList.get(Integer.parseInt(altTargetLine.split("monkey ")[1]));
      currMonkey.setAltTarget(altTarget);      
    }
    
    var lcd = MiscUtils.getLcm(divs);
    for (Monkey m : monkeyList) {
      lcd = m.getDiv() * lcd;
    }
    
    for (Monkey m : monkeyList) {
      m.setLcd(lcd);
    }
    
    // Run rounds
    for (int i = 0; i < 20; i++) {
      for (Monkey m : monkeyList) {
        m.runInspection();
        m.runWorryDiminisher();
        m.runTest();
      }
    }
    
    long biggest = 0;
    long otherBiggest = 0;
    for (Monkey m : monkeyList) {
      if (biggest < m.getTotalInspection()) {
        otherBiggest = biggest;
        biggest = m.getTotalInspection();
      } else if (otherBiggest < m.getTotalInspection()) {
        otherBiggest = m.getTotalInspection();
      }
    }
    
    System.out.println(biggest * otherBiggest);
  }
}
