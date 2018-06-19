package com.ahuang.bookCornerServer.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
	/**
	* @Title: beanToMap
	* @Description: javaBean 转 Map
	* @param object
	* @return
	* @throws Exception Map<String,Object>    返回类型
	* @author ahuang  
	* @date 2018年6月19日 下午9:30:45
	* @version V1.0
	* @throws
	*/
	public static Map<String, Object> beanToMap(Object object) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Class<? extends Object> cls = object.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(object));
		}
		return map;
	}
	/**
	* @Title: mapToBean
	* @Description: map对象转换为object
	* @param map 需要转换的map
	* @param cls 目标javaBean的类对象
	* @return
	* @throws Exception Object    返回类型
	* @author ahuang  
	* @date 2018年6月19日 下午9:29:30
	* @version V1.0
	* @throws
	*/
	public static Object mapToBean(Map<String, Object> map, @SuppressWarnings("rawtypes") Class cls) throws Exception {
		Object object = cls.newInstance();
		for (String key : map.keySet()) {
			Field temFiels = cls.getDeclaredField(key);
			temFiels.setAccessible(true);
			temFiels.set(object, map.get(key));
		}
		return object;
	}
}
