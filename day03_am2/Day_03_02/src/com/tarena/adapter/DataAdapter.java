package com.tarena.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DataAdapter extends BaseAdapter {
	private ArrayList<String> data;
	private LayoutInflater inflater;

	public void setData(ArrayList<String> data) {
		if (data != null)
			this.data = data;
		else
			this.data = new ArrayList<String>();
	}

	public DataAdapter(Context context, ArrayList<String> data) {
		this.setData(data);
		this.inflater = LayoutInflater.from(context);
	}

	public void append(ArrayList<String> data) {
		this.data.addAll(data);
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(android.R.layout.simple_list_item_1,
					null);
		}
		if (isFling == false) {
			// 获取数据
			String item = data.get(position);

			// 写入到界面
			((TextView) convertView).setText(item);
		}
		return convertView;
	}

	private boolean isFling;

	public void setFling(boolean isFling) {
		this.isFling = isFling;
	}
	
	

}
