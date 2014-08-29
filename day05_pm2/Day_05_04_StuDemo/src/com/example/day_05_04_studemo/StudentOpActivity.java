package com.example.day_05_04_studemo;

import com.example.biz.StudentBiz;
import com.example.entity.Student;
import com.example.utils.GlobalConsts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class StudentOpActivity extends Activity {
	private int opType;
	private EditText etId, etName, etAge;
	private RadioGroup rgSex;
	private Button btnOp;
	private StudentBiz stuBiz;

	private void setupView() {
		// 获取控件的引用
		etId = (EditText) findViewById(R.id.etStuId);
		etName = (EditText) findViewById(R.id.etStuName);
		etAge = (EditText) findViewById(R.id.etStuAge);

		rgSex = (RadioGroup) findViewById(R.id.rgSex);

		btnOp = (Button) findViewById(R.id.btnOp);

		// 初始化
		switch (opType) {
		case GlobalConsts.OP_TYPE_ADD:// 添加操作
			btnOp.setText("添加");
			break;
		case GlobalConsts.OP_TYPE_UPDATE:// 修改操作
			btnOp.setText("修改");
			// 获取待修改数据
			Student stu = (Student) getIntent().getSerializableExtra(
					GlobalConsts.EXTRA_OP_DATA);
			//将待修改数据显示在界面上
			if (stu != null) {
				etId.setText(stu.getId() + "");
				etName.setText(stu.getName());
				etAge.setText("" + stu.getAge());
				if ("男".equals(stu.getSex())) {
					rgSex.check(R.id.rdoMale);
				} else {
					rgSex.check(R.id.rdoFemale);
				}
			}
			break;
		}
	}

	public void doClick(View v) {
		// 获取用户输入
		Student stu = new Student();
		stu.setName(etName.getText().toString());
		stu.setSex(rgSex.getCheckedRadioButtonId() == R.id.rdoFemale ? "女"
				: "男");
		stu.setAge(Integer.parseInt(etAge.getText().toString()));
		// 执行操作
		switch (opType) {
		case GlobalConsts.OP_TYPE_ADD:// 添加操作
			stuBiz.addStudent(stu);
			break;
		case GlobalConsts.OP_TYPE_UPDATE:// 修改操作
			stu.setId(Integer.parseInt(etId.getText().toString()));
			stuBiz.update(stu);
			break;
		}
		// 结束本Activity
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stuop);
		opType = getIntent().getIntExtra(GlobalConsts.EXTRA_OP_TYPE,
				GlobalConsts.OP_TYPE_ADD);
		stuBiz = new StudentBiz(this);
		setupView();

	}
}
