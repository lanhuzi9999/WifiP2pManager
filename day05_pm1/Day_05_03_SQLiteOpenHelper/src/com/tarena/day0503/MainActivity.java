package com.tarena.day0503;

import com.tarena.dal.DBOpenHelper;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DBOpenHelper helper = new DBOpenHelper(this, "stu.db");
		// SQLiteDatabase db = helper.getWritableDatabase();
		// db.close();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from stutbl", null);
		if (c != null) {
			while (c.moveToNext()) {
				String[] cols = c.getColumnNames();
				for (String col : cols) {
					Log.i("info",
							col + ":" + c.getString(c.getColumnIndex(col)));
				}
				Log.i("info", "--------------------------");
			}
			c.close();
		}
		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
