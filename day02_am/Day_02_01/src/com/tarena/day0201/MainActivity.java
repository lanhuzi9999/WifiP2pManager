package com.tarena.day0201;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private LinearLayout root, row1, row2, row3;
	private TextView tvName, tvPassword;
	private EditText etName, etPassword;
	private Button btnLogin, btnCancel;

	private void setupView() {
		// 实例化所有的view对象
		root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);

		row1 = new LinearLayout(this);
		row1.setOrientation(LinearLayout.HORIZONTAL);
		row2 = new LinearLayout(this);
		row2.setOrientation(LinearLayout.HORIZONTAL);
		row3 = new LinearLayout(this);
		row3.setOrientation(LinearLayout.HORIZONTAL);

		tvName = new TextView(this);
		tvName.setText("用户名");
		tvPassword = new TextView(this);
		tvPassword.setText("密码");

		etName = new EditText(this);
		etPassword = new EditText(this);

		btnLogin = new Button(this);
		btnLogin.setText("登录");
		btnCancel = new Button(this);
		btnCancel.setText("取消");
		// 组织视图树
		row1.addView(tvName);
		row1.addView(etName);

		row2.addView(tvPassword);
		row2.addView(etPassword);

		row3.addView(btnLogin);
		row3.addView(btnCancel);

		root.addView(row1);
		root.addView(row2);
		root.addView(row3);
		// 添加到Activity的内容区
		setContentView(root);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupView();
		getResources().getString(R.string.app_name);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
