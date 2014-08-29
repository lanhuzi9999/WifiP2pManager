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
		// ��ȡ�ؼ�����
		etName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etUserPass);
		chkUserName = (CheckBox) findViewById(R.id.chkSaveName);

		// ��ʼ��
		// ��ȡƫ�������ļ��б�����û���
		String name = prefBiz.getUserName();
		// ������ڱ�����û���
		if (name != null) {
			etName.setText(name);// �����û���
			chkUserName.setChecked(true);// ��ѡ������Ϊѡ��״̬
		}
	}

	/**
	 * ��ť�ĵ����¼�
	 * @param v
	 */
	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:// ��¼
			// ��ȡ����
			String name = etName.getText().toString();
			String password = etPassword.getText().toString();
			// �����û�����
			User user = new User(name, password);
			// ��¼
			if (userBiz.exists(user)) {
				// ��¼�ɹ�,������Activity
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);

				// �����û�������
				if (chkUserName.isChecked()) {
					prefBiz.saveUserName(name);
				} else {
					prefBiz.removeUserName();
				}
				// ������¼activity
				finish();
			} else {
				// ��¼ʧ��
				Toast.makeText(this, "�û������������������", Toast.LENGTH_LONG).show();
				etPassword.getText().clear();
			}

			break;
		case R.id.btnReset:// ����
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
