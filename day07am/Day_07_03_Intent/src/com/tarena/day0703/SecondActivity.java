package com.tarena.day0703;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();

		String name = extras.getString("name");
		int age = extras.getInt("age");

		Toast.makeText(this, name + "," + age, Toast.LENGTH_LONG).show();
	}
}
