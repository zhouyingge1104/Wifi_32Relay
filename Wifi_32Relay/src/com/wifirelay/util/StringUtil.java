package com.wifirelay.util;

public class StringUtil {
	
  
	/**
	 * ��ǰ����������ַ�(�ַ����� �ܳ���)
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
     * �������ַ��ں��油�볤�ȣ��ַ������ܳ��ȣ�����ַ���
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
