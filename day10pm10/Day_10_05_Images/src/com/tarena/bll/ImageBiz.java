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
	 * 根据图片id 获取缩略图
	 * 
	 * @param id
	 * @return
	 */
	public Bitmap getThumbnail(int id) {
		return dao.getThumbnail(id);
	}

	/**
	 * 根据图片id 获取图片
	 * 
	 * @param id
	 * @return
	 */
	public Bitmap getBitmap(int id) {
		return dao.getBitmap(id);
	}

	/**
	 * 查询所有图片信息
	 * 
	 * @return
	 */
	public ArrayList<ImageInfo> getImages() {
		return dao.getImages();
	}
}
