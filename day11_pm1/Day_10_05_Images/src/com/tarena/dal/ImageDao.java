package com.tarena.dal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

import com.tarena.entity.ImageInfo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;

public class ImageDao {
	private ContentResolver cr;

	public ImageDao(Context context) {
		cr = context.getContentResolver();
	}

	/**
	 * ����ͼƬid ��ȡ����ͼ
	 * 
	 * @param id
	 * @return
	 */
	public Bitmap getThumbnail(int id) {
		return Thumbnails.getThumbnail(cr, id, Thumbnails.MICRO_KIND, null);
	}

	/**
	 * ����ͼƬid ��ȡͼƬ
	 * 
	 * @param id
	 * @return
	 */
	public Bitmap getBitmap(int id) {
		Uri uri = ContentUris.withAppendedId(Media.EXTERNAL_CONTENT_URI, id);
		try {
			return Media.getBitmap(cr, uri);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ѯ����ͼƬ��Ϣ
	 * 
	 * @return
	 */
	public ArrayList<ImageInfo> getImages() {
		ArrayList<ImageInfo> infos = null;
		// ׼��uri
		Uri uri = Media.EXTERNAL_CONTENT_URI;
		// ׼����ѯ��
		String[] projection = { Media._ID, Media.TITLE };
		// ��ѯ
		Cursor c = cr.query(uri, projection, null, null, null);
		// ���������
		if (c != null) {
			infos = new ArrayList<ImageInfo>();
			while (c.moveToNext()) {
				ImageInfo info = new ImageInfo();
				info.setId(c.getInt(c.getColumnIndex(Media._ID)));
				info.setTitle(c.getString(c.getColumnIndex(Media.TITLE)));
				info.setThumbnail(new SoftReference<Bitmap>(getThumbnail(info
						.getId())));

				infos.add(info);
			}
			c.close();
		}
		return infos;
	}

}
