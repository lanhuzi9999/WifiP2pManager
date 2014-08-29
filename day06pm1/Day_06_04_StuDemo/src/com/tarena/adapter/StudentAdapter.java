package com.tarena.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tarena.day0601.R;
import com.tarena.entity.Student;

public class StudentAdapter extends BaseAdapter {
	private ArrayList<Student> stus;
	private LayoutInflater inflater;

	public void setStus(ArrayList<Student> stus) {
		if (stus != null)
			this.stus = stus;
		else
			this.stus = new ArrayList<Student>();
	}

	/**
	 * 变更数据集 并更新界面
	 * 
	 * @param stus
	 */
	public void changeData(ArrayList<Student> stus) {
		this.setStus(stus);
		this.notifyDataSetChanged();
	}

	/**
	 * 从集合中移除一条数据
	 * 
	 * @param position
	 */
	public void removeItem(int position) {
		if (position >= 0 && position < stus.size()) {
			stus.remove(position);
			notifyDataSetChanged();
		}
	}

	public StudentAdapter(Context context, ArrayList<Student> stus) {
		this.setStus(stus);
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return stus.size();
	}

	@Override
	public Student getItem(int position) {
		// TODO Auto-generated method stub
		return stus.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 加载或复用item界面
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_stu, null);
			holder = new ViewHolder();
			holder.tvId = (TextView) convertView.findViewById(R.id.tvId);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvSex = (TextView) convertView.findViewById(R.id.tvSex);
			holder.tvAge = (TextView) convertView.findViewById(R.id.tvAge);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 获取指定位置的数据
		Student stu = getItem(position);
		// 绑定到item界面
		holder.tvId.setText("" + stu.getId());
		holder.tvAge.setText("" + stu.getAge());
		holder.tvName.setText(stu.getName());
		holder.tvSex.setText(stu.getSex());
		// 返回item界面
		return convertView;
	}

	class ViewHolder {
		private TextView tvId;
		private TextView tvName;
		private TextView tvSex;
		private TextView tvAge;
	}

}
