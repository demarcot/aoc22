package com.tcd.aoc22.day2;

public enum GameResultEnum {

  WIN("Z", 6),
  LOSE("X", 0),
  DRAW("Y", 3);

  private String code; // Only needed for part 2
  private int val;

  private GameResultEnum(String code, int val) {

    this.code = code;
    this.val = val;
  }

  public int getVal() {
    return this.val;
  }
  
  public static GameResultEnum fromCode(String code) {
    for (GameResultEnum r : values()) {
      if (r.code.equalsIgnoreCase(code)) {
        return r;
      }
    }
    
    System.out.println("No enum for that code...");
    return null;
  }
}
