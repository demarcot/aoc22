package com.tcd.aoc22.day20;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.IntegerWrapper;
import com.tcd.aoc22.utils.MiscUtils;
import com.tcd.aoc22.utils.SimpleNode;

// -1210 wrong
public class Part1Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 20 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day20/d20p1.txt");
    
    List<IntegerWrapper> originalList = new ArrayList<>();
    SimpleNode<IntegerWrapper> head = new SimpleNode<>();
    SimpleNode<IntegerWrapper> tail = head;
    SimpleNode<IntegerWrapper> z = null;
    
    System.out.println("Building list...");
    var headVal = new IntegerWrapper(Integer.parseInt(lines.get(0)));
    originalList.add(headVal);
    head.val = headVal;
    
    for (int i = 1; i < lines.size(); i++) {
      var val = new IntegerWrapper(Integer.parseInt(lines.get(i)));
      originalList.add(val);
      var n = new SimpleNode<>(val);
      tail.next = n;
      tail.next.prev = tail;
      tail = tail.next;
      
      if (val.getVal() == 0) {
        System.out.println("Found zero");
        z = n;
      }
    }
    
    // Make linked list circular
    head.prev = tail;
    tail.next = head;
    
    System.out.println("Moving...");
    for (var i : originalList) {
      var curr = find(head, i);
      curr.move(curr.val.getVal());
    }
    
    System.out.println("Printing answer...");
    var a = findNext(z, 1000).val.getVal();
    var b = findNext(z, 2000).val.getVal();
    var c = findNext(z, 3000).val.getVal();
        
    System.out.println( ("" + a) + (", " + b) + (", " + c));
    System.out.println(a + b + c);
  }
  
  private static SimpleNode<IntegerWrapper> find (SimpleNode<IntegerWrapper> head, IntegerWrapper val) {
    var curr = head;
    do {
      if (curr.val == val) {
        return curr;
      }
      
      curr = curr.next;
    } while (curr != head);
    
    System.out.println("Failed to find " + val.getVal());
    return null;
  }
  
  private static SimpleNode<IntegerWrapper> findNext (SimpleNode<IntegerWrapper> head, int steps) {
    var curr = head;
    for (int i = 0; i < steps; i++) {
      curr = curr.next;
    }
    
    return curr;
  }
}
