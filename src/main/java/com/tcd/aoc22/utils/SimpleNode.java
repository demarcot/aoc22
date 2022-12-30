package com.tcd.aoc22.utils;

public class SimpleNode<T> {
  public T val;
  
  public SimpleNode<T> prev;
  public SimpleNode<T> next;
  
  public SimpleNode () {}
  public SimpleNode (T val) {
    this.val = val;
  }
  
  public void move (int movement) {
    SimpleNode<T> target = null;
    if (movement == 0) {
      return;
    }
    
    var prev = this.prev;
    var next = this.next;
    
    // remove
    this.prev.next = this.next;
    this.next.prev = this.prev;
    
    this.next = null;
    this.prev = null;
    
    // move
    if (movement < 0) {
      for (int i = 0; i < -movement; i++) {
        prev = prev.prev;
      }
      
      target = prev;
    } else {
      for (int i = 0; i < movement - 1; i++) {
        next = next.next;
      }
      
      target = next;
    }
    
    if (target == this) {
      return;
    }
    
    // insert
    this.next = target.next;
    this.prev = target;
    
    target.next = this;
    this.next.prev = this;
  }
  
  public String toString () {
    var curr = this;
    var str = "";
    var cnt = 0;
    do {
      str += curr.val + " ";
      curr = curr.next;
      cnt++;
    } while (curr.next != null && curr != this);
    
    return cnt + ": " + str;
  }
}
