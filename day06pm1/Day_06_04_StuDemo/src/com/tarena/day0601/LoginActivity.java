package com.tarena.day0601;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.tarena.bll.UserBiz;
import com.tarena.bll.UserPrefBiz;
import com.tarena.entity.User;

public class LoginActivity extends Activity {
	private EditText etName, etPassword;
	private CheckBox chkUserName;
	private UserBiz userBiz;
	private UserPrefBiz prefBiz;

	private void setupView() {
		// 获取控件的引用
		etName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etUserPass);
		chkUserName = (CheckBox) findViewById(R.id.chkSaveName);

		// 获取保存的用户名 并初始化控件
		String name = prefBiz.getUserName();
		if (name != null) {
			etName.setText(name);
			chkUserName.setChecked(true);
		}
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			// 获取输入
			String name = etName.getText().toString();
			String password = etPassword.getText().toString();
			// 构建对象
			User user = new User(name, password);
			// 登录验证
			if (userBiz.exists(user)) {
				// 登录成功
				// 启动MainActivity
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				// 保存用户名
				if (chkUserName.isChecked()) {
					prefBiz.saveUserName(name);
				} else {
					prefBiz.removeUserName();
				}
				// 结束本Activity
				finish();
			} else {
				// 登录失败
				Toast.makeText(this, "登录失败", Toast.LENGTH_LONG).show();
				etPassword.getText().clear();
			}
			break;
		case R.id.btnReset:
			etName.getText().clear();
			etPassword.getText().clear();
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		userBiz = new UserBiz(this);
		prefBiz = new UserPrefBiz(this);
		setupView();
	}
}
