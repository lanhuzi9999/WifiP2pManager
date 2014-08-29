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
						"����:"
								+ c.getString(c
										.getColumnIndex(Contacts.DISPLAY_NAME)));

				// ������ϵ��id ��data���ѯ�绰��Ϣ
				Cursor c1 = cr.query(Phone.CONTENT_URI, new String[] {
						Phone.NUMBER, Phone.TYPE }, Phone.CONTACT_ID + "="
						+ contact_id, null, null);
				if (c1 != null) {
					while (c1.moveToNext()) {
						String number = c1.getString(c1
								.getColumnIndex(Phone.NUMBER));
						int type = c1.getInt(c1.getColumnIndex(Phone.TYPE));
						switch (type) {
						case Phone.TYPE_HOME:// ��ͥ�绰
							Log.i("info", "��ͥ�绰:" + number);
							break;
						case Phone.TYPE_WORK:// ��λ�绰
							Log.i("info", "��λ�绰:" + number);
							break;
						case Phone.TYPE_MOBILE:// �ֻ�
							Log.i("info", "�ƶ��绰:" + number);
							break;
						default:
							Log.i("info", "�����绰:" + number);
							break;
						}
					}
					c1.close();
				}

				// ������ϵ��id ��data���ѯ������Ϣ
				c1 = cr.query(Email.CONTENT_URI, new String[] { Email.DATA,
						Email.TYPE }, Email.CONTACT_ID + "=" + contact_id,
						null, null);
				if (c1 != null) {
					while (c1.moveToNext()) {
						String email = c1.getString(c1
								.getColumnIndex(Email.DATA));
						int type = c1.getInt(c1.getColumnIndex(Email.TYPE));
						switch (type) {
						case Email.TYPE_HOME:// ��ͥ
							Log.i("info", "��ͥ����:" + email);
							break;
						case Email.TYPE_WORK:// ��λ
							Log.i("info", "��λ�绰:" + email);
							break;
						default:
							Log.i("info", "�����绰:" + email);
							break;
						}
					}
					c1.close();
				}
				// ������ϵ��id ��data���ѯͷ��
				InputStream is = Contacts.openContactPhotoInputStream(cr,
						ContentUris.withAppendedId(uri, contact_id));
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
