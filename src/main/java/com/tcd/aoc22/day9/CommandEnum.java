package com.tcd.aoc22.day9;

public enum CommandEnum {
  UP("U"),
  DOWN("D"),
  RIGHT("R"),
  LEFT("L");
  
  private String code;
  private int distance;
  
  private CommandEnum (String code) {
    this.code = code;
  }
  
  public void setDistance (int distance) {
    this.distance = distance;
  }
  
  public int getDistance () {
    return this.distance;
  }
  
  public static CommandEnum fromCode (String code) {
    for (var c : values()) {
      if (c.code.equalsIgnoreCase(code)) {
        return c;
      }
    }
    
    System.out.println("Command does not exist: " + code);
    return null;
  }
}
