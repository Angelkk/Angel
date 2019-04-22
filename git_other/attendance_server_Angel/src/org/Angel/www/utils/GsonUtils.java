package org.Angel.www.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.Angel.www.domain.ClockData;
import org.Angel.www.domain.Employee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonUtils {

	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public static List<Employee> compileJsonByEmployee(String json) {
		Type mapType = new TypeToken<HashMap<String, String>>() {
		}.getType();

		HashMap<String, String> value = gson.fromJson(json, mapType);
		System.out.println(value.get("result"));

		Type listType = new TypeToken<List<Employee>>() {
		}.getType();

		List<Employee> resultList = gson.fromJson(value.get("result"), listType);

		return resultList;
	}

	public static String compileJsonGetFlag(String json) {
		Type mapType = new TypeToken<HashMap<String, String>>() {
		}.getType();

		HashMap<String, String> value = gson.fromJson(json, mapType);

		return value.get("flag").toString();
	}

	public static String booleanFlagJson(String flag) {
		return flag;
	}

	public static String compileJsonByClockDataList(String message, List<ClockData> clockDataList) {
		HashMap<String, String> data = new HashMap<>();
		data.put("flag", message);
		data.put("result", gson.toJson(clockDataList));
		String json = gson.toJson(data);
		System.out.println(json);
		return json;
	}

	

	
}
