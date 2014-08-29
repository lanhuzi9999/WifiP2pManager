package com.tarena.day1102;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv;

	private void setupView() {
		tv = new TextView(this) {
			@Override
			public boolean onTouchEvent(MotionEvent event) {
				Log.i("info", "tv.onTouchEvenet");
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					Log.i("info", "[" + event.getX() + "," + event.getY()
							+ "] action=down");
					break;
				case MotionEvent.ACTION_UP:
					Log.i("info", "[" + event.getX() + "," + event.getY()
							+ "] action=up");
					break;
				case MotionEvent.ACTION_MOVE:
					Log.i("info", "[" + event.getX() + "," + event.getY()
							+ "] action=move");
					break;
				}
				return true;
			}
		};
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		tv.setLayoutParams(params);

		setContentView(tv);
	}

	private void addListener() {
		tv.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("info", "listener.onTouch");
				return true;
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		setupView();
		addListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
