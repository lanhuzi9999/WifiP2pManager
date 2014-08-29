package com.tarena.day1101;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tvTest;

	private void setupView() {
		tvTest = (TextView) findViewById(R.id.tvTest);
	}

	private void addListeners() {
		tvTest.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// event.getX();
				// event.getY();
				// event.getAction();//touch事件的动作类型 down up move

				Log.i("info", "[" + event.getX() + "," + event.getY()
						+ "]  action=" + event.getAction());
				return false;
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
