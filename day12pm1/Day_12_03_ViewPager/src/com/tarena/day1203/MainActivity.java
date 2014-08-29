package com.tarena.day1203;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ViewPager pager;
	private ArrayList<View> views;
	private LinearLayout tabs;
	private ImageView ivCursor;

	private void setupView() {
		/* ��ʼ��pager */
		// ��ȡpager������
		pager = (ViewPager) findViewById(R.id.pager);
		// ׼����View�ļ���
		views = createViews();
		// ����adapter
		MyPagerAapter adapter = new MyPagerAapter(views);
		// ����adapter
		pager.setAdapter(adapter);

		/* ��ʼ��ivCursor */
		// ��ȡ�ؼ�����
		ivCursor = (ImageView) findViewById(R.id.ivCursor);
		// ����ؼ��Ŀ��
		int w = getResources().getDisplayMetrics().widthPixels / views.size();
		// ���ò��ֲ���
		LayoutParams params = new LayoutParams(w, 10);
		ivCursor.setLayoutParams(params);

		/* ��ʼ��tabs */
		// ��ȡ���� ������
		tabs = (LinearLayout) findViewById(R.id.tabs);
		tabs.setBackgroundColor(Color.GRAY);
		// ����view�����е�view ��Ӧ����tab
		for (View v : views) {
			tabs.addView(createTab(v.getTag().toString()));
		}
	}

	private View createTab(String title) {
		TextView tv = new TextView(this);
		tv.setText(title);
		LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT,
				1.0f);
		tv.setLayoutParams(params);
		tv.setTextSize(30);
		tv.setTextColor(Color.WHITE);
		tv.setPadding(0, 3, 0, 3);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

	private ArrayList<View> createViews() {
		ArrayList<View> views = new ArrayList<View>();
		views.add(createView(R.drawable.p02, "P02"));
		views.add(createView(R.drawable.p03, "P03"));
		views.add(createView(R.drawable.p04, "P04"));
		views.add(createView(R.drawable.p05, "P05"));
		return views;
	}

	private View createView(int imgRes, String title) {
		ImageView iv = new ImageView(this);
		iv.setImageResource(imgRes);
		iv.setTag(title);
		iv.setScaleType(ScaleType.FIT_CENTER);
		return iv;
	}

	private int currentIndex;// ViewPager�е�ǰѡ��ҳ������

	private void addListeners() {
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int positioin) {
				// ��������
				int w = ivCursor.getWidth();
				Animation anim = new TranslateAnimation(currentIndex * w,
						positioin * w, 0, 0);
				anim.setDuration(500);
				anim.setFillAfter(true);
				// ִ�ж���
				ivCursor.startAnimation(anim);
				// ���õ�ǰѡ��ҳ������
				currentIndex = positioin;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();
		addListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
