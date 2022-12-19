package com.tcd.aoc22.day13;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.IntegerWrapper;
import com.tcd.aoc22.utils.MiscUtils;

// TODO(Tom): not done. think I was done with the input parsing though and rest should be easy enough
public class Part1Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 13 Part 1 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day13/sample.txt");
    
    List<PacketSegment> packetList = new ArrayList<>();
    for (int i = 0; i < lines.size(); i+=3) {
      var p1 = new PacketSegment();
      var p2 = new PacketSegment();
      p1 = buildPacket(p1, p1, lines.get(i), new IntegerWrapper(1));
      p2 = buildPacket(p2, p2, lines.get(i + 1), new IntegerWrapper(1));
      packetList.add(p1);
      packetList.add(p2);
    }
    System.out.println("packets good?");
  }
  
  // [1, [[2], 3], 4, [5]]
  // [[8,7,6]]
  // seg( <seg( <seg(8), seg(7), seg(6)> )> )
  // seg
  private static PacketSegment buildPacket (PacketSegment packet, PacketSegment segment, String packetStr, IntegerWrapper packetStrOffset) {
    int currVal = 0;
    for (; packetStrOffset.getVal() < packetStr.length(); packetStrOffset.add(1)) {
      char c = packetStr.charAt(packetStrOffset.getVal());
      if (c == '[') {
        packetStrOffset.add(1);
        packet.addSegment(buildPacket(new PacketSegment(), new PacketSegment(), packetStr, packetStrOffset));
      } else if (c == ']') {
        if (currVal > 0) {
          packet.addSegment(new PacketSegment(currVal));
          currVal = 0;
        }
        packetStrOffset.add(1);
        return packet;
      } else if (c == ',') {
        packet.addSegment(new PacketSegment(currVal));
        currVal = 0;
      } else {
        currVal = (currVal * 10) + Character.getNumericValue(c);
      }
    }
    
    System.out.println("Should this happen?");
    return packet;
  }
  
}
