package com.example.day_12_01_viewflipper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataSource {
	public static List<HashMap<String, Object>> getStudents(String[] from) {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		from[0] = "id";
		from[1] = "name";
		from[2] = "sex";
		from[3] = "age";
		for (int i = 1; i <= 20; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("id", i);
			item.put("name", "张老" + i);
			item.put("sex", "男");
			item.put("age", 18);

			data.add(item);
		}
		return data;
	}

	public static List<HashMap<String, Object>> getCourses(String[] from) {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		from[0] = "id";
		from[1] = "name";
		from[2] = "days";
		from[3] = "teacher";
		for (int i = 1; i <= 5; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("id", i);
			item.put("name", "java 入门" + i);
			item.put("days", 5);
			item.put("teacher", "王海涛");

			data.add(item);
		}
		return data;
	}

	public static List<HashMap<String, Object>> getScores(String[] from) {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		from[0] = "id";
		from[1] = "name";
		from[2] = "course";
		from[3] = "score";
		for (int i = 1; i <= 5; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("id", i);
			item.put("name", "张老" + i);
			item.put("course", "java入门" + i);
			item.put("score", 90);

			data.add(item);
		}
		return data;
	}
}
