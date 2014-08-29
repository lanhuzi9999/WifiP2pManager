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
		// ׼���������ݻ�����
		String[] projection = { Media._ID, Media.DATA, Media.TITLE,
				Media.ALBUM, Media.ARTIST, Media.COMPOSER, Media.DURATION,
				Media.SIZE, Media.DISPLAY_NAME, Media.ALBUM_KEY };
		// ׼��Uri
		Uri uri = Media.EXTERNAL_CONTENT_URI;
		// ִ�в�ѯ
		Cursor c = cr.query(uri, projection, Media.DURATION + ">30000", null,
				null);
		if (c != null) {
			while (c.moveToNext()) {
				for (String col : projection) {
					Log.i("info",
							col + ":" + c.getString(c.getColumnIndex(col)));
				}
				// ��ÿ������ ������album_key ����album_art(ר��ͼƬ·��)
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
		menu.add(1, 1, 1, "ˢ��");
		return true;
	}

}
