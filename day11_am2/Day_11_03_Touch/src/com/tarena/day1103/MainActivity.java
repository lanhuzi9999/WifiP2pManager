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
		//����textview�ؼ�
		tvTest = new MyTextView(this);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 100);
		tvTest.setBackgroundColor(Color.GRAY);
		tvTest.setLayoutParams(params);
		//��������
		layout = new MyLinearLayout(this);
		//�򲼾������textView�ؼ�
		layout.addView(tvTest);
		//����������Ϊactivity����ʾ����
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
	 * touch�¼����ɷ�����
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.i("info",
				"MainActivity.dispatchTouchEvent,action=" + event.getAction());
		return super.dispatchTouchEvent(event);
	}

	/**
	 * touch�¼��Ĵ�����
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("info", "MainActivity.onTouchEvent,action=" + event.getAction());
		return super.onTouchEvent(event);
	}

}
