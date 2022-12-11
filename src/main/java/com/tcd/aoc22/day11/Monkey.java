package com.tcd.aoc22.day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Monkey {
  private int id;
  private List<BigInteger> items = new ArrayList<>();
  private Function<BigInteger, BigInteger> op;
  private Function<BigInteger, Boolean> test;
  private Monkey target;
  private Monkey altTarget;
  private Long div;
  private Long lcd;
  
  private long totalInspections = 0;
  
  public Monkey (int id) {
    this.id = id;
  }
  
  public void addItem (BigInteger i) {
    items.add(i);
  }
  
  public void removeItem (BigInteger i) {
    items.remove(items.indexOf(i));
  }
  
  public void setOp (Function<BigInteger, BigInteger> op) {
    this.op = op;
  }
  
  public void setTest (Function<BigInteger, Boolean> test) {
    this.test = test;
  }
  
  public void setTarget (Monkey m) {
    this.target = m;
  }
  
  public void setAltTarget (Monkey m) {
    this.altTarget = m;
  }
  
  public void setDiv (Long div) {
    this.div = div;
  }
  
  public Long getDiv () {
    return this.div;
  }
  
  public void setLcd (Long lcd) {
    this.lcd = lcd;
  }
  
  public Long getLcd () {
    return this.lcd;
  }
  
  public Long getTotalInspection () {
    return this.totalInspections;
  }
  
  public void runInspection () {
    for (int i = 0; i < items.size(); i++) {
      items.set(i, items.get(i).mod(BigInteger.valueOf(getLcd())));
      items.set(i, this.op.apply(items.get(i)));
    }
    
    this.totalInspections += items.size();
  }
  
  public void runWorryDiminisher () {
    for (int i = 0; i < items.size(); i++) {
      items.set(i, items.get(i).divide(BigInteger.valueOf(3)));
    }
  }
  
  public void runTest () {
    for (int i = items.size() - 1; i >= 0; i--) {
      if (this.test.apply(items.get(i))) {
        this.target.addItem(this.items.get(i));
      } else {
        this.altTarget.addItem(this.items.get(i));
      }
      
      items.remove(items.get(i));
    }
  }
}
