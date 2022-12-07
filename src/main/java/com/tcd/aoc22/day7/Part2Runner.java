package com.tcd.aoc22.day7;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.tcd.aoc22.utils.MiscUtils;

public class Part2Runner {
  public static void main(String[] args) throws URISyntaxException {
    System.out.println("--- Day 7 Part 2 ---");
    // Read file
    List<String> lines = MiscUtils.ReadFile("day7/d7p1.txt");
    
    final int TOTAL_SYSTEM_SIZE = 70_000_000;
    final int SIZE_NEEDED = 30_000_000;
    
    VirtualDir root = new VirtualDir("/", null);
    List<VirtualDir> flatDirList = new ArrayList<>();
    
    buildFileSystemFromConsole(lines, root, flatDirList);
    
    final int currFreeSpace = TOTAL_SYSTEM_SIZE - root.getSize();
    final int remainingSpaceNeeded = SIZE_NEEDED - currFreeSpace;
    
    VirtualDir dirToDelete = root;
    
    for (var dir : flatDirList) {
      if (dir.getSize() < dirToDelete.getSize() && dir.getSize() >= remainingSpaceNeeded ) {
        dirToDelete = dir;
      }
    }
    
    System.out.println(dirToDelete.getSize());
  }
  
  private static void buildFileSystemFromConsole (List<String> consoleOutput, VirtualDir root, List<VirtualDir> flatDirList) {
    flatDirList.add(root);
    
    VirtualDir currDir = root;
    for (String line : consoleOutput) {
      if (line.startsWith("$")) {
        var splitCommand = line.split(" ");
        if (splitCommand[1].equals("cd")) {
          if (splitCommand[2].equals("..")) {
            currDir = currDir.getParent();
          } else {
            VirtualDir targetDir = currDir.getSubdir(splitCommand[2]);
            if (null != targetDir) {
              currDir = targetDir;
            } else {
              System.out.println("Target dir missing or is current dir");
            }
          }
        }
      } else if (line.startsWith("dir ")) {
        var splitDirLine = line.split(" ");
        var dir = new VirtualDir(splitDirLine[1], currDir);
        currDir.addSubdir(dir);
        flatDirList.add(dir);
      } else {
        var splitFileLine = line.split(" ");
        currDir.addFile(new VirtualFile(splitFileLine[1], currDir, Integer.parseInt(splitFileLine[0])));
      }
    }
  }
}