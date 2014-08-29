package com.tarena.day0702;

import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//创建显示意图的方法1
		Intent intent = new Intent();
		ComponentName component = new ComponentName(this, MainActivity.class);
		intent.setComponent(component);
		//创建显式意图的方法2
		intent = new Intent(this,MainActivity.class);
		//创建显式意图的方法3
		intent = new Intent();
		intent.setClass(this, MainActivity.class);
		// intent.putExtras(extras);
		// intent.getExtras();
		
		
		//创建ComponentName对象的三种方式
		component = new ComponentName(this, MainActivity.class);
		component = new ComponentName(this, "com.tarena.day0702.MainActivity");
		component = new ComponentName(this.getPackageName(), "com.tarena.day0702.MainActivity");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
