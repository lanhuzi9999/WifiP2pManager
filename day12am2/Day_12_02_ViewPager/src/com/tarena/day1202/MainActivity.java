package com.tarena.day1202;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {
	private ViewPager pager;

	private void setupView() {
		// 获取viewPager的引用
		pager = (ViewPager) findViewById(R.id.pager);
		// 准备views集合
		ArrayList<View> views = createViews();
		// 创建PagerAdapter
		MyPagerAdapter adapter = new MyPagerAdapter(views);
		// 设置适配器
		pager.setAdapter(adapter);
	}

	private ArrayList<View> createViews() {
		ArrayList<View> views = new ArrayList<View>();
		views.add(createView(R.drawable.p02));
		views.add(createView(R.drawable.p03));
		views.add(createView(R.drawable.p04));
		views.add(createView(R.drawable.p05));
		return views;
	}

	private View createView(int imgRes) {
		ImageView iv = new ImageView(this);
		iv.setImageResource(imgRes);
		iv.setScaleType(ScaleType.FIT_CENTER);
		return iv;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
