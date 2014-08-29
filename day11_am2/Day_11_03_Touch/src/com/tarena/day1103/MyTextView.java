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
	 * touch�¼����ɷ�����
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.i("info",
				"MyTextView.dispatchTouchEvent,action=" + event.getAction());
		return super.dispatchTouchEvent(event);
	}

	/**
	 * touch�¼��Ĵ�����
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("info", "MyTextView.onTouchEvent,action=" + event.getAction());
		return false;
	}
}
