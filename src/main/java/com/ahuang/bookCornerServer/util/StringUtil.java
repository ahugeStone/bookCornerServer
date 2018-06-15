package com.ahuang.bookCornerServer.util;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Map;

public class StringUtil {
	public static String trim(String str) {
		return (str == null || str.length() == 0) ? "" : str.trim();
	}

	public static boolean isNullOrEmpty(String str) {
		return (null == str || str.length() == 0) ? true : false;
	}

	/**
	 * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null)
			return true;

		if (obj instanceof CharSequence)
			return ((CharSequence) obj).length() == 0;

		if (obj instanceof Collection)
			return ((Collection<?>) obj).isEmpty();

		if (obj instanceof Map)
			return ((Map<?, ?>) obj).isEmpty();

		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			boolean empty = true;
			for (int i = 0; i < object.length; i++)
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			return empty;
		}
		return false;
	}

	public static byte[] string2ByteArray(String s) {
		byte[] b = null;
		try {
			b = s.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			b = s.getBytes();
		}
		return b;
	}

	public static String byteArray2String(byte[] b) {
		String s = null;
		try {
			s = new String(b, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			s = new String(b);
		}
		return s;
	}
}
