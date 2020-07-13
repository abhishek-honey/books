package ch08.ConcurrentHashMap;

import java.util.concurrent.ConcurrentHashMap;

public class CHashMap {
 public static void main(String[] args) {
	   ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
	   map.put(1, 5);
	   map.put(1, 6);
	   
	   map.putIfAbsent(1, 19);
	   map.replace(1, 16);
	   
	   System.out.println(map);
}
}
