package com.tarena.day1205;

import java.io.InputStream;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentResolver cr = getContentResolver();
		Uri uri = Contacts.CONTENT_URI;
		String[] projection = { Contacts._ID, Contacts.DISPLAY_NAME };
		Cursor c = cr.query(uri, projection, null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				int contact_id = c.getInt(c.getColumnIndex(Contacts._ID));
				Log.i("info", "contact_id:" + contact_id);
				Log.i("info",
						"姓名:"
								+ c.getString(c
										.getColumnIndex(Contacts.DISPLAY_NAME)));

				// 根据联系人id 在data表查询电话信息
				Cursor c1 = cr.query(Phone.CONTENT_URI, new String[] {
						Phone.NUMBER, Phone.TYPE }, Phone.CONTACT_ID + "="
						+ contact_id, null, null);
				if (c1 != null) {
					while (c1.moveToNext()) {
						String number = c1.getString(c1
								.getColumnIndex(Phone.NUMBER));
						int type = c1.getInt(c1.getColumnIndex(Phone.TYPE));
						switch (type) {
						case Phone.TYPE_HOME:// 家庭电话
							Log.i("info", "家庭电话:" + number);
							break;
						case Phone.TYPE_WORK:// 单位电话
							Log.i("info", "单位电话:" + number);
							break;
						case Phone.TYPE_MOBILE:// 手机
							Log.i("info", "移动电话:" + number);
							break;
						default:
							Log.i("info", "其他电话:" + number);
							break;
						}
					}
					c1.close();
				}

				// 根据联系人id 在data表查询邮箱信息
				c1 = cr.query(Email.CONTENT_URI, new String[] { Email.DATA,
						Email.TYPE }, Email.CONTACT_ID + "=" + contact_id,
						null, null);
				if (c1 != null) {
					while (c1.moveToNext()) {
						String email = c1.getString(c1
								.getColumnIndex(Email.DATA));
						int type = c1.getInt(c1.getColumnIndex(Email.TYPE));
						switch (type) {
						case Email.TYPE_HOME:// 家庭
							Log.i("info", "家庭邮箱:" + email);
							break;
						case Email.TYPE_WORK:// 单位
							Log.i("info", "单位电话:" + email);
							break;
						default:
							Log.i("info", "其他电话:" + email);
							break;
						}
					}
					c1.close();
				}
				// 根据联系人id 在data表查询头像
				InputStream is = Contacts.openContactPhotoInputStream(cr,
						ContentUris.withAppendedId(uri, contact_id));
				Bitmap bm = BitmapFactory.decodeStream(is);
				Log.i("info", "头像:" + bm);
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
