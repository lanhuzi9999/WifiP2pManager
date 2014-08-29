package com.tarena.day0404;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText etFileName;

	private void setupView() {
		etFileName = (EditText) findViewById(R.id.etFileName);
	}

	public void doClick(View v) {
		// 获取用户输入
		String fileName = etFileName.getText().toString();
		// 启动Service 进行下载
		Intent intent = new Intent(this, MyService.class);
		intent.putExtra("filename", fileName);
		startService(intent);
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
