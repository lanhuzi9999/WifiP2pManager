package com.tarena.day1106;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btnTest;

	private void setupView() {
		btnTest = (Button) findViewById(R.id.btnTest);
	}

	public void doClick(View v) {
		// 创建动画
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.set);
		// 执行动画
		btnTest.startAnimation(anim);
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
