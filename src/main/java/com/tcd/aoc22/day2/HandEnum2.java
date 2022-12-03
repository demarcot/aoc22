package com.tcd.aoc22.day2;

public enum HandEnum2 {
  ROCK("A", 1),
  PAPER("B", 2),
  SCISSOR("C", 3);

  private String code;
  private int handVal;

  private HandEnum2(String code, int handVal) {
    this.code = code;
    this.handVal = handVal;
  }

  public int getVal() {
    return this.handVal;
  }

  public GameResultEnum getGameResult(HandEnum2 otherHand) {
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
  
  public HandEnum2 lookupCardFromOpposingResult (GameResultEnum opposingRes) {
    if (opposingRes.equals(GameResultEnum.DRAW)) {
      return this;
    }
    
    if (opposingRes.equals(GameResultEnum.WIN)) {
      return HandEnum2.getLoseCond(this);
    } else {
      return HandEnum2.getWinCond(this);
    }
  }
  
  public static HandEnum2 fromCode(String code) {
    for (HandEnum2 e : values()) {
      if (e.code.equalsIgnoreCase(code)) {
        return e;
      }
    }

    System.out.println("No enum for that code...");
    return null;
  }
  
  private static HandEnum2 getWinCond (HandEnum2 hand) {
    switch (hand) {
    case PAPER:
      return ROCK;
    case SCISSOR:
      return PAPER;
    case ROCK:
      return SCISSOR;
    }
    
    System.out.println("Invalid hand");
    return null;
  }
  
  private static HandEnum2 getLoseCond (HandEnum2 hand) {
    switch (hand) {
    case ROCK:
      return PAPER;
    case PAPER:
      return SCISSOR;
    case SCISSOR:
      return ROCK;
    }
    
    System.out.println("Invalid hand");
    return null;
  }
}
