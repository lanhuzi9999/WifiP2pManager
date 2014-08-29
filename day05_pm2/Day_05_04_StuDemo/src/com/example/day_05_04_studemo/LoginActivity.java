package com.example.day_05_04_studemo;

import com.example.biz.UserBiz;
import com.example.biz.UserPrefBiz;
import com.example.entity.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText etName, etPassword;
	private CheckBox chkUserName;
	private UserBiz userBiz;
	private UserPrefBiz prefBiz;

	private void setupView() {
		// 获取控件引用
		etName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etUserPass);
		chkUserName = (CheckBox) findViewById(R.id.chkSaveName);

		// 初始化
		// 获取偏好设置文件中保存的用户名
		String name = prefBiz.getUserName();
		// 如果存在保存的用户名
		if (name != null) {
			etName.setText(name);// 设置用户名
			chkUserName.setChecked(true);// 复选框设置为选中状态
		}
	}

	/**
	 * 按钮的单击事件
	 * @param v
	 */
	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:// 登录
			// 获取输入
			String name = etName.getText().toString();
			String password = etPassword.getText().toString();
			// 创建用户对象
			User user = new User(name, password);
			// 登录
			if (userBiz.exists(user)) {
				// 登录成功,启动主Activity
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);

				// 保存用户名操作
				if (chkUserName.isChecked()) {
					prefBiz.saveUserName(name);
				} else {
					prefBiz.removeUserName();
				}
				// 结束登录activity
				finish();
			} else {
				// 登录失败
				Toast.makeText(this, "用户名或密码错误，请重试", Toast.LENGTH_LONG).show();
				etPassword.getText().clear();
			}

			break;
		case R.id.btnReset:// 重置
			// etName.setText("");
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
