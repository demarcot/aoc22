package com.tcd.aoc22.day1;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tcd.aoc22.utils.MiscUtils;

public class Part1Runner {

	public static void main(String[] args) throws URISyntaxException {
		System.out.println("--- Day 1 Part 1 ---");
		// Read file
		List<String> lines = MiscUtils.ReadFile("day1/d1p1.txt");
		
		// Build list of elves
		List<Elf> elfList = new ArrayList<>();
		List<Integer> calList = new ArrayList<>();
		for (String line : lines) {
		  if (line.isBlank()) {
		    elfList.add(new Elf(calList));
		    calList = new ArrayList<>();
		  } else {
		    calList.add(Integer.valueOf(line));
		  }
		}
		
		// Sort by total calories
		Comparator<Elf> elfCalorieComparator = Comparator.comparing(Elf::getTotalCalories);
		Collections.sort(elfList, elfCalorieComparator);
		
		// Find top elf
		Elf first = elfList.get(elfList.size() - 1);
		
		System.out.println(first.getTotalCalories());
	}	
}
