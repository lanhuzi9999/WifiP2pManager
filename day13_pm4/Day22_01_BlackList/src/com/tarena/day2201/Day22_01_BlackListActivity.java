package com.tarena.day2201;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.tarena.bll.BlackListBiz;

public class Day22_01_BlackListActivity extends Activity {
	private static final int MENU_CTX_REMOVE = 1;
	private CheckBox chkUseBlackList;
	private ListView lvBlackList;
	private EditText etNumber;
	private ArrayAdapter<String> adapter;
	private BlackListBiz biz;

	/**
	 * �����ʼ������
	 */
	private void setupView() {
		// ��ȡ�ؼ�����
		chkUseBlackList = (CheckBox) findViewById(R.id.chkUseBlackList);
		etNumber = (EditText) findViewById(R.id.etNumber);
		lvBlackList = (ListView) findViewById(R.id.lvBlackList);
		// ��ʼ��
		if (biz.isUsed()) {
			chkUseBlackList.setChecked(true);
		} else {
			chkUseBlackList.setChecked(false);
		}

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, biz.getBlackList());
		lvBlackList.setAdapter(adapter);
	}

	/**
	 * Ϊ�ؼ�����¼�����
	 */
	private void addListener() {
		chkUseBlackList
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						// ���ú���������״̬(true �� false)
						biz.setBlackListState(isChecked);
					}
				});

		lvBlackList
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						// TODO Auto-generated method stub
						menu.setHeaderTitle("����");
						menu.add(1, MENU_CTX_REMOVE, 1, "ɾ��");
					}
				});
	}

	/**
	 * ��ť�ĵ����¼�����
	 */
	public void doClick(View v) {
		// ��ȡ����
		String numb = etNumber.getText().toString();
		// ��ӵ����ݿ�
		long rowId = biz.addNumber(numb);
		if (rowId != -1) {
			// ����ListView
			adapter.add(numb);
			etNumber.setText("");
		} else {
			Toast.makeText(this, "��������ʧ�ܣ�����������", 3000).show();

		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		String numb = adapter.getItem(menuInfo.position);
		switch (item.getItemId()) {
		case MENU_CTX_REMOVE:
			// ɾ���绰����Ӻ�������
			int count = biz.removeNumber(numb);
			// ���ɾ���ɹ�����listView
			if (count > 0) {
				adapter.remove(numb);
			}
			break;
		}
		return super.onContextItemSelected(item);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new BlackListBiz(this);
		setupView();
		addListener();

		//����TelService
		Intent service = new Intent(this, TelService.class);
		startService(service);
	}
}