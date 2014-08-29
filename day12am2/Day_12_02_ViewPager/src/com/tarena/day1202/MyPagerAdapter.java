package com.tarena.day1202;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {
	private ArrayList<View> views;

	public MyPagerAdapter(ArrayList<View> views) {
		if (views != null)
			this.views = views;
		else
			this.views = new ArrayList<View>();
	}

	@Override
	public int getCount() {
		Log.i("info", "getCount");
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		Log.i("info", "isViewFromObject");
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		Log.i("info", "destroyItem");
		// ����position�Ӽ����л�ȡ һ��View
		View v = views.get(position);
		// ��viewPager���Ƴ���view
		container.removeView(v);
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.finishUpdate(container);
		Log.i("info", "finishUpdate");
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Log.i("info", "instantiateItem");
		// ����position�Ӽ����л�ȡһ��view
		View v = views.get(position);
		// ����view���� ��ӵ�viewpager��
		container.addView(v);
		return v;
	}

	@Override
	public void startUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.startUpdate(container);
		Log.i("info", "startUpdate");
	}

}
