package com.tarena.day1004;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取ContentResolver的引用
		ContentResolver cr = getContentResolver();
		// 准备Uri
		Uri uri = Media.EXTERNAL_CONTENT_URI;
		// 准备要查询的列
		String[] projection = { Media._ID, Media.TITLE, Media.DISPLAY_NAME,
				Media.DATA, Media.HEIGHT, Media.WIDTH };

		// 执行查询
		Cursor c = cr.query(uri, projection, null, null, null);
		if (c != null) {
			while (c.moveToNext()) {
				for (String col : projection) {
					Log.i("info",
							col + ":" + c.getString(c.getColumnIndex(col)));
				}
				try {
					int id = c.getInt(c.getColumnIndex(Media._ID));
					Bitmap bm = Media.getBitmap(cr,
							ContentUris.withAppendedId(uri, id));
					Log.i("info", "image:" + bm);
					Bitmap thumb = Thumbnails.getThumbnail(cr, id,
							Thumbnails.MICRO_KIND, null);
					Log.i("info", "thumbnail:" + thumb);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.i("info", "-----------------------------------");
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
