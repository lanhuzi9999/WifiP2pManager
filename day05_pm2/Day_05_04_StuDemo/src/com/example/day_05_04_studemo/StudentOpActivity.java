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
		// ��ȡ�ؼ�������
		etId = (EditText) findViewById(R.id.etStuId);
		etName = (EditText) findViewById(R.id.etStuName);
		etAge = (EditText) findViewById(R.id.etStuAge);

		rgSex = (RadioGroup) findViewById(R.id.rgSex);

		btnOp = (Button) findViewById(R.id.btnOp);

		// ��ʼ��
		switch (opType) {
		case GlobalConsts.OP_TYPE_ADD:// ��Ӳ���
			btnOp.setText("���");
			break;
		case GlobalConsts.OP_TYPE_UPDATE:// �޸Ĳ���
			btnOp.setText("�޸�");
			// ��ȡ���޸�����
			Student stu = (Student) getIntent().getSerializableExtra(
					GlobalConsts.EXTRA_OP_DATA);
			//�����޸�������ʾ�ڽ�����
			if (stu != null) {
				etId.setText(stu.getId() + "");
				etName.setText(stu.getName());
				etAge.setText("" + stu.getAge());
				if ("��".equals(stu.getSex())) {
					rgSex.check(R.id.rdoMale);
				} else {
					rgSex.check(R.id.rdoFemale);
				}
			}
			break;
		}
	}

	public void doClick(View v) {
		// ��ȡ�û�����
		Student stu = new Student();
		stu.setName(etName.getText().toString());
		stu.setSex(rgSex.getCheckedRadioButtonId() == R.id.rdoFemale ? "Ů"
				: "��");
		stu.setAge(Integer.parseInt(etAge.getText().toString()));
		// ִ�в���
		switch (opType) {
		case GlobalConsts.OP_TYPE_ADD:// ��Ӳ���
			stuBiz.addStudent(stu);
			break;
		case GlobalConsts.OP_TYPE_UPDATE:// �޸Ĳ���
			stu.setId(Integer.parseInt(etId.getText().toString()));
			stuBiz.update(stu);
			break;
		}
		// ������Activity
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
