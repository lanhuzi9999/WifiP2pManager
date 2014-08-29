package com.tarena.day1203;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAapter extends PagerAdapter {
	private ArrayList<View> views;

	public MyPagerAapter(ArrayList<View> views) {
		if (views != null)
			this.views = views;
		else
			this.views = new ArrayList<View>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// 从集合中获取制定position的view
		View v = views.get(position);
		// 将该view 从容器中移除
		container.removeView(v);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// 从集合中获取制定position的view
		View v = views.get(position);
		// 将该view 添加到容器
		container.addView(v);
		return v;
	}

}
