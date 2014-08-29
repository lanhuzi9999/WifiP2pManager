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
	 * 界面初始化方法
	 */
	private void setupView() {
		// 获取控件对象
		chkUseBlackList = (CheckBox) findViewById(R.id.chkUseBlackList);
		etNumber = (EditText) findViewById(R.id.etNumber);
		lvBlackList = (ListView) findViewById(R.id.lvBlackList);
		// 初始化
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
	 * 为控件添加事件监听
	 */
	private void addListener() {
		chkUseBlackList
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						// 设置黑名单启用状态(true 或 false)
						biz.setBlackListState(isChecked);
					}
				});

		lvBlackList
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						// TODO Auto-generated method stub
						menu.setHeaderTitle("操作");
						menu.add(1, MENU_CTX_REMOVE, 1, "删除");
					}
				});
	}

	/**
	 * 按钮的单击事件处理
	 */
	public void doClick(View v) {
		// 获取输入
		String numb = etNumber.getText().toString();
		// 添加到数据库
		long rowId = biz.addNumber(numb);
		if (rowId != -1) {
			// 更新ListView
			adapter.add(numb);
			etNumber.setText("");
		} else {
			Toast.makeText(this, "插入数据失败，请重新输入", 3000).show();

		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		String numb = adapter.getItem(menuInfo.position);
		switch (item.getItemId()) {
		case MENU_CTX_REMOVE:
			// 删除电话号码从黑名单中
			int count = biz.removeNumber(numb);
			// 如果删除成功更新listView
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

		//启动TelService
		Intent service = new Intent(this, TelService.class);
		startService(service);
	}
}