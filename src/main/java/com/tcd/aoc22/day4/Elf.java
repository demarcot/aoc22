package com.tcd.aoc22.day4;

public class Elf {
  private int sectionStart;
  private int sectionEnd;
  
  public Elf (int sectionStart, int sectionEnd) {
    this.sectionStart = sectionStart;
    this.sectionEnd = sectionEnd;
  }
  
  public static boolean hasFullOverlap (Elf e1, Elf e2) {
    if (e1.sectionStart >= e2.sectionStart && e1.sectionEnd <= e2.sectionEnd) {
      return true;
    }
    
    if (e2.sectionStart >= e1.sectionStart && e2.sectionEnd <= e1.sectionEnd) {
      return true;
    }
    
    return false;
  }
  
  public static boolean hasOverlap (Elf e1, Elf e2) {
    if (e1.sectionStart == e2.sectionStart || e1.sectionStart == e2.sectionEnd || e1.sectionEnd == e2.sectionStart) {
      return true;
    }
    
    if (e1.sectionStart >= e2.sectionStart &&
        e1.sectionStart <= e2.sectionEnd) {
      return true;
    } else if (e2.sectionStart >= e1.sectionStart &&
        e2.sectionStart <= e1.sectionEnd) {
      return true;
    }
    
    return false;
  }
}
