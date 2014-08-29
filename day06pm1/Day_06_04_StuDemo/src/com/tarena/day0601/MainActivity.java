package com.tarena.day0601;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.example.utils.GlobalConsts;
import com.tarena.adapter.StudentAdapter;
import com.tarena.bll.StudentBiz;
import com.tarena.entity.Student;

public class MainActivity extends Activity {
	private static final int MENU_ID_OPTS_ADD = 1;
	private static final int MENU_ID_OPTS_EXIT = 2;

	private static final int MENU_ID_CTX_UPDATE = 10;
	private static final int MENU_ID_CTX_DELETE = 11;

	private ListView lvStudents;
	private StudentBiz stuBiz;
	private StudentAdapter adapter;

	private void setupView() {
		// ��ȡ����
		lvStudents = (ListView) findViewById(R.id.lvStudents);
		// ��ȡ����
		ArrayList<Student> stus = stuBiz.getStudents();
		// ����������
		adapter = new StudentAdapter(this, stus);
		// ����������
		lvStudents.setAdapter(adapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		stuBiz = new StudentBiz(this);
		setupView();
		addListener();
	}

	private void addListener() {
		lvStudents
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						menu.setHeaderIcon(android.R.drawable.ic_dialog_info)
								.setHeaderTitle("����");

						menu.add(2, MENU_ID_CTX_UPDATE, 1, "�޸�");
						menu.add(2, MENU_ID_CTX_DELETE, 2, "ɾ��");
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, MENU_ID_OPTS_ADD, 1, "���");
		menu.add(1, MENU_ID_OPTS_EXIT, 2, "�˳�");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ID_OPTS_ADD:// ���
			Intent intent = new Intent(this, StudentOpActivity.class);
			intent.putExtra(GlobalConsts.EXTRA_OP_TYPE,
					GlobalConsts.OP_TYPE_ADD);
			startActivity(intent);
			break;
		case MENU_ID_OPTS_EXIT:// �˳�
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// ��ȡ����������
		int position = ((AdapterContextMenuInfo) item.getMenuInfo()).position;
		Student stu = adapter.getItem(position);
		// ִ�в���
		switch (item.getItemId()) {
		case MENU_ID_CTX_UPDATE:// �޸�
			Intent intent = new Intent(this, StudentOpActivity.class);
			intent.putExtra(GlobalConsts.EXTRA_OP_TYPE,
					GlobalConsts.OP_TYPE_UPDATE);
			intent.putExtra(GlobalConsts.EXTRA_OP_DATA, stu);
			startActivity(intent);
			break;
		case MENU_ID_CTX_DELETE:// ɾ��
			int count = stuBiz.deleteStudent(stu.getId());
			if (count != 0) {
				adapter.removeItem(position);
			}
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		// ˢ��listview
		adapter.changeData(stuBiz.getStudents());

	}

}
