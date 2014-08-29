package com.tarena.day1206;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
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
			values.put(Data.RAW_CONTACT_ID, rawContactId);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/name");
			values.put(Data.DATA1, "王老五");
			cr.insert(Data.CONTENT_URI, values);

			// 插入电话信息
			values.clear();
			values.put(Data.RAW_CONTACT_ID, rawContactId);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/phone_v2");
			values.put(Data.DATA1, "13844055698");
			values.put(Data.DATA2, 2);
			cr.insert(Data.CONTENT_URI, values);
			values.clear();
			values.put(Data.RAW_CONTACT_ID, rawContactId);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/phone_v2");
			values.put(Data.DATA1, "010-65585832");
			values.put(Data.DATA2, 1);
			cr.insert(Data.CONTENT_URI, values);

			// 插入邮箱信息
			values.clear();
			values.put(Data.RAW_CONTACT_ID, rawContactId);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/email_v2");
			values.put(Data.DATA1, "wanglw@tarena.com.cn");
			values.put(Data.DATA2, 2);
			cr.insert(Data.CONTENT_URI, values);
			values.clear();
			values.put(Data.RAW_CONTACT_ID, rawContactId);
			values.put(Data.MIMETYPE, "vnd.android.cursor.item/email_v2");
			values.put(Data.DATA1, "wanglaowu@163.com");
			values.put(Data.DATA2, 1);
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
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				values.put(Data.MIMETYPE	, "vnd.android.cursor.item/photo");
				values.put(Data.DATA15, data);
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
