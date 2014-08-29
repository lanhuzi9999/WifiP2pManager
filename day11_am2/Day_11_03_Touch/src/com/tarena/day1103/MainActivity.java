package com.tarena.day1103;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
	private MyLinearLayout layout;
	private MyTextView tvTest;

	private void setupView() {
		//创建textview控件
		tvTest = new MyTextView(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 100);
		tvTest.setBackgroundColor(Color.GRAY);
		tvTest.setLayoutParams(params);
		//创建布局
		layout = new MyLinearLayout(this);
		//向布局中添加textView控件
		layout.addView(tvTest);
		//将布局设置为activity的显示内容
		setContentView(layout);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * touch事件的派发方法
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.i("info",
				"MainActivity.dispatchTouchEvent,action=" + event.getAction());
		return super.dispatchTouchEvent(event);
	}

	/**
	 * touch事件的处理方法
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("info", "MainActivity.onTouchEvent,action=" + event.getAction());
		return super.onTouchEvent(event);
	}

}
