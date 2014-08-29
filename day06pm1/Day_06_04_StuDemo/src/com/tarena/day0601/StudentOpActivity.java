package com.tarena.day0601;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.utils.GlobalConsts;
import com.tarena.bll.StudentBiz;
import com.tarena.entity.Student;

public class StudentOpActivity extends Activity {
	private int opType;
	private EditText etId, etName, etAge;
	private RadioGroup rgSex;
	private Button btnOp;
	private StudentBiz stuBiz;

	private void setupView() {
		// 控件或引用
		etId = (EditText) findViewById(R.id.etStuId);
		etName = (EditText) findViewById(R.id.etStuName);
		etAge = (EditText) findViewById(R.id.etStuAge);

		rgSex = (RadioGroup) findViewById(R.id.rgSex);

		btnOp = (Button) findViewById(R.id.btnOp);

		// 初始化
		switch (opType) {
		case GlobalConsts.OP_TYPE_ADD:// 添加
			btnOp.setText("添加");
			break;
		case GlobalConsts.OP_TYPE_UPDATE:// 修改
			btnOp.setText("修改");
			Student stu = (Student) getIntent().getSerializableExtra(
					GlobalConsts.EXTRA_OP_DATA);
			etId.setText(stu.getId() + "");
			etName.setText(stu.getName());
			etAge.setText("" + stu.getAge());
			if ("男".equals(stu.getSex())) {
				rgSex.check(R.id.rdoMale);
			} else {
				rgSex.check(R.id.rdoFemale);
			}
			break;
		}
	}

	public void doClick(View v) {
		// 获取输入
		Student stu = new Student();
		stu.setName(etName.getText().toString());
		stu.setSex(rgSex.getCheckedRadioButtonId() == R.id.rdoFemale ? "女"
				: "男");
		stu.setAge(Integer.parseInt(etAge.getText().toString()));

		// 执行操作
		switch (opType) {
		case GlobalConsts.OP_TYPE_ADD:// 添加
			stuBiz.addStudent(stu);
			break;
		case GlobalConsts.OP_TYPE_UPDATE:// 修改
			stu.setId(Integer.parseInt(etId.getText().toString()));
			stuBiz.update(stu);
			break;
		}
		// 结束Activity
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
