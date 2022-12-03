package com.tcd.aoc22.day3;

import java.util.Arrays;

public class Rucksack {
  private char[] allItems;
  private char[] compartmentA;
  private char[] compartmentB;
  
  public Rucksack(String items) {
    this.allItems = items.toCharArray();
    this.compartmentA = items.substring(0, items.length() / 2).toCharArray();
    this.compartmentB = items.substring(items.length() / 2).toCharArray();
    Arrays.sort(this.allItems);
    Arrays.sort(this.compartmentA);
    Arrays.sort(this.compartmentB);
  }
  
  public char getCommonItem () {
    for (char c : this.compartmentA) {
      if (Arrays.binarySearch(this.compartmentB, c) >= 0) {
        return c;
      }
    }
    
    System.out.println(new String(this.compartmentA) + " : " + new String(this.compartmentB));
    return '?';
  }
  
  public static int getPriority (char c) {
    // A - 65 Z - 90
    // a - 97 z - 122
    if (!Character.isLetter(c)) {
      System.out.println("Bad char: " + c);
      return 0;
    }
    
    int castedChar = (int)c;
    
    // Convert to problem's prio ranges
    if (castedChar <= 90) {
      return castedChar - 38;
    } else {
      return castedChar - 96;
    }    
  }
  
  public static char findBadgeItem (Rucksack s1, Rucksack s2, Rucksack s3) {
    for (char c : s1.allItems) {
      if (Arrays.binarySearch(s2.allItems, c) >= 0 &&
          Arrays.binarySearch(s3.allItems, c) >= 0) {
        return c;
      }
    }
    
    System.out.println("No badge found...");
    return '?';
  }
}
