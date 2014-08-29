package com.tarena.day0605;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ContentResolver
		ContentResolver cr = getContentResolver();
		// 准备操作数据或条件
		String[] projection = { Media._ID, Media.DATA, Media.TITLE,
				Media.ALBUM, Media.ARTIST, Media.COMPOSER, Media.DURATION,
				Media.SIZE, Media.DISPLAY_NAME, Media.ALBUM_KEY };
		// 准备Uri
		Uri uri = Media.EXTERNAL_CONTENT_URI;
		// 执行查询
		Cursor c = cr.query(uri, projection, Media.DURATION + ">30000", null,
				null);
		if (c != null) {
			while (c.moveToNext()) {
				for (String col : projection) {
					Log.i("info",
							col + ":" + c.getString(c.getColumnIndex(col)));
				}
				// 对每条音乐 根据其album_key 查找album_art(专辑图片路径)
				String album_key = c.getString(c
						.getColumnIndex(Media.ALBUM_KEY));
				Cursor c1 = cr.query(Albums.EXTERNAL_CONTENT_URI,
						new String[] { Albums.ALBUM_ART }, Albums.ALBUM_KEY
								+ "=?", new String[] { album_key }, null);
				if (c1 != null && c1.moveToFirst()) {
					Log.i("info", "album_path:" + c1.getString(0));
					c1.close();
				}

				Log.i("info", "---------------------");
			}
			c.close();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
		intent.setData(Uri.parse("file://"
				+ Environment.getExternalStorageDirectory()));
		sendBroadcast(intent);
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 1, "刷新");
		return true;
	}

}
