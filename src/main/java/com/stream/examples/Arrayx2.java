package com.stream.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//Find in int [] all members of the array 
//received by x2 other members of the array.

public class Arrayx2 {
//	static int[] arr = { 1, 3, 5, 2, 6, 10, 4, 12, 20 };
	static int[] arr = {49,3,1,12,7,9,21,15,5,2,6,29,10,4,20};
	static Map<Integer, List<Integer>> statMap = new HashMap<>();

	public static void main(String[] args) {
		testArrayx2();
	}
	
	static void testArrayx2() {
		buildStatMap();
		printStatMap();
	}
	
	static int getOddBase(int num) {
		while (num % 2 == 0)
			num /= 2;
		return num;
	}

	static void buildStatMap() {
		
		for(int i=0; i<arr.length; i++) {
			
			int key = getOddBase(arr[i]);
			List<Integer> value = statMap.get(key);
			
			if(value == null) {
				value = new ArrayList<Integer>();
			}
			value.add(arr[i]);
			
			statMap.put(key, value);			
		}
	}
	static void printStatMap() {
		statMap.entrySet().stream()
		.sorted(Arrayx2::compareEntryByKey)
		.filter(x->x.getValue().size() > 1)
		.forEach(x->System.out.printf("%s\n", x.getValue() )); 
	}
	private static int compareEntryByKey(Entry<Integer,List<Integer>>x, Entry<Integer,List<Integer>>y) {
		return x.getKey()-y.getKey();
	}
	
}
