package com.tarena.bll;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;

import com.tarena.dal.ImageDao;
import com.tarena.entity.ImageInfo;

public class ImageBiz {
	private ImageDao dao;

	public ImageBiz(Context context) {
		dao = new ImageDao(context);
	}

	/**
	 * ����ͼƬid ��ȡ����ͼ
	 * 
	 * @param id
	 * @return
	 */
	public Bitmap getThumbnail(int id) {
		return dao.getThumbnail(id);
	}

	/**
	 * ����ͼƬid ��ȡͼƬ
	 * 
	 * @param id
	 * @return
	 */
	public Bitmap getBitmap(int id) {
		return dao.getBitmap(id);
	}

	/**
	 * ��ѯ����ͼƬ��Ϣ
	 * 
	 * @return
	 */
	public ArrayList<ImageInfo> getImages() {
		return dao.getImages();
	}
}
