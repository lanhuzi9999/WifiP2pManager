package com.tarena.day1301;

import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentResolver cr = getContentResolver();
		Uri uri = Calls.CONTENT_URI;
		String[] projection = { Calls._ID, Calls.NUMBER, Calls.DATE,
				Calls.DURATION, Calls.TYPE };
		Cursor c = cr.query(uri, projection, null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				for (String col : projection) {
					Log.i("info",
							col + ":" + c.getString(c.getColumnIndex(col)));
				}
				Log.i("info", "---------------------------");
			}
			c.close();
		}

		// Calls.TYPE
		// Calls.INCOMING_TYPE
		// Calls.OUTGOING_TYPE
		// Calls.MISSED_TYPE
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
