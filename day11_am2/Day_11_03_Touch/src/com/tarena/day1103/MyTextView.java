package com.tarena.day1103;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MyTextView extends TextView {
	public MyTextView(Context context) {
		super(context);
	}

	/**
	 * touch事件的派发方法
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.i("info",
				"MyTextView.dispatchTouchEvent,action=" + event.getAction());
		return super.dispatchTouchEvent(event);
	}

	/**
	 * touch事件的处理方法
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("info", "MyTextView.onTouchEvent,action=" + event.getAction());
		return false;
	}
}
