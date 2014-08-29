package com.tarena.day1108;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	private ViewFlipper vfContainer;

	private void setupView() {
		vfContainer = (ViewFlipper) findViewById(R.id.vfContainer);
		vfContainer.setInAnimation(this, android.R.anim.fade_in);
		vfContainer.setOutAnimation(this, android.R.anim.fade_out);
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnPrevious:
			vfContainer.showPrevious();
			break;

		case R.id.btnNext:
			vfContainer.showNext();
			break;
		}
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
