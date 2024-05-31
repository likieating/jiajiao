package com.jiajiao.utils;

import java.security.MessageDigest;

/*
 * 负责给字符串数据进行加密
 */
public class MD5Utils {

	public static String MD5(String strs) {
		/*
		 * 加密需要使用JDK中提供的类
		 */
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] bs = digest.digest(strs.getBytes());
			/*
			 * 加密后的数据是-128 到 127 之间的数字，这个数字也不安全。 取出每个数组的某些二进制位进行某些运算，得到一个新的加密结果
			 * 
			 * 0000 0011 0000 0100 0010 0000 0110 0001 &0000 0000 0000 0000 0000
			 * 0000 1111 1111 --------------------------------------------- 0000
			 * 0000 0000 0000 0000 0000 0110 0001
			 * 
			 * 把取出的数据转成十六进制数
			 */
			for (byte b : bs) {
				int x = b & 255;
				String s = Integer.toHexString(x);
				if (x < 16) {
					sb.append("0");
				}
				sb.append(s);
			}

		} catch (Exception e) {
			System.out.println("加密失败");
		}
		return sb.toString();
	}
	
	
	public static void main(String[] args) {		
		System.out.println(MD5Utils.MD5("123456")); //21232f297a57a5a743894a0e4a801fc3
	}
}