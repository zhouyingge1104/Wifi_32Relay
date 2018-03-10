package com.wifirelay.util;

public class StringUtil {
	
  
	/**
	 * 在前面填充任意字符(字符串， 总长度)
	 * @return
	 */
	public static String fillChar(String str, int length, String c){
		if(str.length() >= length){
			return str;
		}else{
			int bits = length - str.length();
			for(int i = 0; i < bits; i++){
				str = c + str;
			}
			return str;
		}
		
	}
    
	/**
     * 用任意字符在后面补齐长度（字符串，总长度，填充字符）
     * @return
     */
	public static String fillCharInTheEnd(String str, int length, String c){
		if(str.length() >= length){
			return str;
		}else{
			int bits = length - str.length();
			for(int i = 0; i < bits; i++){
				str = str + c;
			}
			return str;
		}
		
	}
	
}
