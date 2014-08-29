package com.tarena.day0602;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentResolver cr = getContentResolver();
		Uri uri = Uri.parse("content://com.tarena.providers.book/book");

		// 插入
		ContentValues values = new ContentValues();
		values.put("name", "你好 中国");
		values.put("price", 100);
		cr.insert(uri, values);

		// 查询
		Cursor c = cr.query(uri, null, null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				String[] cols = c.getColumnNames();
				for (String col : cols) {
					Log.i("info",
							col + ":" + c.getString(c.getColumnIndex(col)));
				}
				Log.i("info", "---------------------------");
			}
			c.close();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
