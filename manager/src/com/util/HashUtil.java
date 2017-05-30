package com.util;

public class HashUtil {

	public static int computeKey(String key){
		
			int res=0;
			for(int i=0;i<key.length();i++){
				res+=(key.charAt(i));
			}
			return res;
		
	}
}
