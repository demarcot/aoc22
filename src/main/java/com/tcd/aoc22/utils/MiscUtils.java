package com.tcd.aoc22.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MiscUtils {
  // NOTE(Tom): If you want final entry to be empty str, end file with 2 newlines
  public static List<String> ReadFile(String path) throws URISyntaxException {
    List<String> lines = new ArrayList<>();
    
    ClassLoader classLoader = MiscUtils.class.getClassLoader();
    URL resource = classLoader.getResource(path);
    File file;
    if (resource == null) {
        throw new IllegalArgumentException("file not found! " + path);
    } else {
        file = new File(resource.toURI());
    }
    
    try {
      Scanner myReader = new Scanner(file);
      while (myReader.hasNextLine()) {
        String line = myReader.nextLine();
        lines.add(line);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found...");
      e.printStackTrace();
    }
        
    return lines;
  }
}
