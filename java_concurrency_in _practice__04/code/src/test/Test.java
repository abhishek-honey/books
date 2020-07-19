package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Test {

//	Array int: program next greatest ele
//	5, 4, 1, 6, 7, 5
//	5 -> 6
//	4 -> 6
//	6 -> 7
//	7 -> -1

	public static void main(String[] args) {
		int[] arr = new int[] { 5, 4, 1, 6, 7, 5 };
		
		int nextGreatest = nextGreatest(7, arr);
		System.out.println(nextGreatest);
		
	}

	public static int nextGreatest(int num, int[] arr) {
		int nextGreatest = Integer.MAX_VALUE;

		for (int curr : arr) {
			if (curr > num && nextGreatest > curr)
				nextGreatest = curr;
		}

		if (nextGreatest == Integer.MAX_VALUE)
			return -1;

		return nextGreatest;
	}
}
