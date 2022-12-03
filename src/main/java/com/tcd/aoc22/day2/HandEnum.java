package com.tcd.aoc22.day2;

public enum HandEnum {
  ROCK("A", "X", 1),
  PAPER("B", "Y", 2),
  SCISSOR("C", "Z", 3);

  private String opponentCode;
  private String playerCode;
  private int handVal;

  private HandEnum(String opponentCode, String playerCode, int handVal) {
    this.opponentCode = opponentCode;
    this.playerCode = playerCode;
    this.handVal = handVal;
  }

  public int getVal() {
    return this.handVal;
  }

  public GameResultEnum getGameResult(HandEnum otherHand) {
    if (this.equals(otherHand)) {
      return GameResultEnum.DRAW;
    }
    
    if ((this.equals(ROCK) && (otherHand.equals(SCISSOR))) ||
        (this.equals(PAPER) && otherHand.equals(ROCK)) ||
        (this.equals(SCISSOR) && otherHand.equals(PAPER))) {
      return GameResultEnum.WIN;
    }
    
    return GameResultEnum.LOSE;
  }
  
  public static HandEnum fromCode(String code) {
    for (HandEnum e : values()) {
      if (e.opponentCode.equalsIgnoreCase(code) || e.playerCode.equalsIgnoreCase(code)) {
        return e;
      }
    }

    System.out.println("No enum for that code...");
    return null;
  }
}
