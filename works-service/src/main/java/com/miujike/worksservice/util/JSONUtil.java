package com.miujike.worksservice.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 把数据转换成json字符串
 * @author 张权
 *
 */
public class JSONUtil {
	 

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如： {"id" : idValue, "name" :
	 * nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}, beansList:[{}, {},
	 * ...]}
	 * 
	 * @param jsonString
	 * @param clazz
	 * @param map
	 * 集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" :
	 * Bean.class)
	 * @return
	 */
	public static Object getObject(String jsonString, Class clazz) {
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.parseObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJavaObject(jsonObject, clazz);
	}

	/**
	 * 获取jsonArray中的每一个key(前提是jsonArray中的每一个jsonObject的key值都相同)
	 * @return
	 */
	public ArrayList<String> getKeys(String jsonArrayStr){

		ArrayList<String> results = new ArrayList<>();
		JSONArray jsonArray = JSONArray.parseArray(jsonArrayStr);
		JSONObject jsonObject = jsonArray.getJSONObject(0);
		String jsonStr = jsonObject.toString();
		LinkedHashMap<String, String> jsonMap = JSON.parseObject(jsonStr, new TypeReference<LinkedHashMap<String, String>>() {});
		for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
			results.add(entry.getKey());
		}
		System.out.println(results);
		return results;
	}

	public static JSONArray addSubjectsToPaper(JSONArray structureArray, JSONArray typeArray, JSONObject typeTitleObject){
		structureArray.add(typeTitleObject);
		for(int i = 0;i<typeArray.size();i++){
			structureArray.add(typeArray.get(i));
		}
		return  structureArray;
	}

	public static void main(String[] args) {
		String mstem = " -The sweater is too large. Would you mind showing me a smaller one? -_______. Here you are.";
		if(mstem.contains("-")){
			String s[] = mstem.split("-");
			for(int i = 0;i<s.length-1;i++){
				mstem = "-" + s[i] + "\n";
			}
			mstem += "-" + s[s.length-1];
		}
		System.out.println(mstem);
	}
}
