package com.tarena.bll;

import java.util.ArrayList;

public class DataSource {
	public ArrayList<String> getData(int page) {
		ArrayList<String> data = new ArrayList<String>();
		int x = (page - 1) * 20;
		for (int i = x; i < x + 20; i++) {
			data.add("item" + i);
		}
		return data;
	}
}
