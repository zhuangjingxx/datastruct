package com.util;

public class HashUtil {

	public static int computeKey(String key){
		
			
			return Math.abs(key.hashCode());
		
	}
}
