package com.tarena.day1204;

import java.io.InputStream;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ContactsContract.RawContacts
		// ContactsContract.Contacts
		// ContactsContract.Data
		// ContactsContract.CommonDataKinds.Phone
		// CommonDataKinds.Email
		// CommonDataKinds.StructuredName

		// ׼��ContentResolver
		ContentResolver cr = getContentResolver();
		// ׼��uri
		Uri uri = Contacts.CONTENT_URI;
		// ׼����ѯ��
		String[] projection = { Contacts._ID, Contacts.DISPLAY_NAME };
		// ִ�в�ѯ
		Cursor c = cr.query(uri, projection, null, null, null);
		// ���������
		if (c != null) {
			while (c.moveToNext()) {
				int id = c.getInt(c.getColumnIndex(Contacts._ID));
				Log.i("info", "contact_id:" + id);
				Log.i("info",
						"����:"
								+ c.getString(c
										.getColumnIndex(Contacts.DISPLAY_NAME)));

				// ����id ��data���ѯ�绰��Ϣ
				// Cursor c1 = cr.query(Data.CONTENT_URI,
				// new String[] { Data.DATA1 }, Data._ID + "=? and "
				// + Data.MIMETYPE + "=?", new String[] { id + "",
				// "vnd.android.cursor.item/phone_v2" }, null);
				Cursor c1 = cr.query(Data.CONTENT_URI,
						new String[] { Data.DATA1 }, Data.CONTACT_ID + "=? and "+Data.MIMETYPE+"=?" ,
						new String[]{id+"","vnd.android.cursor.item/phone_v2"}, null);
				if (c1 != null) {
					Log.i("info", "tel count=" + c1.getCount());
					while (c1.moveToNext()) {
						Log.i("info", "�绰:" + c1.getString(0));
					}
					c1.close();
				}
				// ����id ��data���ѯ������Ϣ
				c1 = cr.query(Data.CONTENT_URI, new String[] { Data.DATA1 },
						Data.CONTACT_ID + "=? and " + Data.MIMETYPE + "=?",
						new String[] { id + "",
								"vnd.android.cursor.item/email_v2" }, null);
				if (c1 != null) {
					Log.i("info", "email count=" + c1.getCount());
					while (c1.moveToNext()) {
						Log.i("info", "����:" + c1.getString(0));
					}
					c1.close();
				}
				// ����id ��data���ѯͷ��
				InputStream is = Contacts.openContactPhotoInputStream(cr,
						ContentUris.withAppendedId(uri, id));
				Bitmap bm = BitmapFactory.decodeStream(is);
				Log.i("info", "ͷ��:" + bm);
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
