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
	 * touch�¼����ɷ�����
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.i("info",
				"MyLinearLayout.dispatchTouchEvent,action=" + event.getAction());
		return super.dispatchTouchEvent(event);
	}

	/**
	 * touch�¼��Ĵ�����
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.i("info", "MyLinearLayout.onTouchEvent,action=" + event.getAction());
		return true;
	}
}
