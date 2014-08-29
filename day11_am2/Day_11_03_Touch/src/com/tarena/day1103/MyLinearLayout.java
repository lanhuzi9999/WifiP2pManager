package com.tarena.day1103;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
	public MyLinearLayout(Context context) {
		super(context);
	}

	/**
	 * touch事件的派发方法
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.i("info",
				"MyLinearLayout.dispatchTouchEvent,action=" + event.getAction());
		return super.dispatchTouchEvent(event);
	}

	/**
	 * touch事件的处理方法
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("info", "MyLinearLayout.onTouchEvent,action=" + event.getAction());
		return true;
	}
}
