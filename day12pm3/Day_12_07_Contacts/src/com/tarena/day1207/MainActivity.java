package com.tarena.day1207;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentResolver cr = getContentResolver();
		// 向原始联系人表插入空行，并获取新插入行的id
		ContentValues values = new ContentValues();
		Uri uri = cr.insert(RawContacts.CONTENT_URI, values);
		if (uri != null) {
			long rawContactId = ContentUris.parseId(uri);
			// 使用原始联系人id 向data表插入联系人的具体数据
			// 插入姓名信息
			values.clear();
			values.put(StructuredName.RAW_CONTACT_ID, rawContactId);
			values.put(StructuredName.MIMETYPE,
					StructuredName.CONTENT_ITEM_TYPE);
			values.put(StructuredName.DISPLAY_NAME, "王老吉");
			cr.insert(Data.CONTENT_URI, values);

			// 插入电话信息
			values.clear();
			values.put(Phone.RAW_CONTACT_ID, rawContactId);
			values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
			values.put(Phone.NUMBER, "13844055699");
			values.put(Phone.TYPE, Phone.TYPE_MOBILE);
			cr.insert(Data.CONTENT_URI, values);
			values.clear();
			values.put(Phone.RAW_CONTACT_ID, rawContactId);
			values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
			values.put(Phone.NUMBER, "010-65585866");
			values.put(Phone.TYPE, Phone.TYPE_HOME);
			cr.insert(Data.CONTENT_URI, values);

			// 插入邮箱信息
			values.clear();
			values.put(Email.RAW_CONTACT_ID, rawContactId);
			values.put(Email.MIMETYPE, Email.CONTENT_ITEM_TYPE);
			values.put(Email.DATA, "wanglj@tarena.com.cn");
			values.put(Email.TYPE, Email.TYPE_WORK);
			cr.insert(Data.CONTENT_URI, values);
			values.clear();
			values.put(Email.RAW_CONTACT_ID, rawContactId);
			values.put(Email.MIMETYPE, Email.CONTENT_ITEM_TYPE);
			values.put(Email.DATA, "wanglaoji@163.com");
			values.put(Email.TYPE, Email.TYPE_HOME);
			cr.insert(Data.CONTENT_URI, values);

			try {
				// 插入头像信息
				String path = "/mnt/sdcard/Pictures/p01.png";
				Bitmap bm = BitmapFactory.decodeFile(path);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				bm.compress(CompressFormat.PNG, 100, out);
				out.close();
				byte[] data = out.toByteArray();
				values.clear();
				values.put(Photo.RAW_CONTACT_ID, rawContactId);
				values.put(Photo.MIMETYPE, Photo.CONTENT_ITEM_TYPE);
				values.put(Photo.PHOTO, data);
				cr.insert(Data.CONTENT_URI, values);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
