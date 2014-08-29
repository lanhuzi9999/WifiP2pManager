package com.tarena.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class BitmapUtils {
	public static Bitmap loadBitmap(String path, int width, int height) {
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, opts);
		int x = opts.outWidth / width;
		int y = opts.outHeight / height;
		opts.inSampleSize = x > y ? x : y;
		opts.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, opts);
	}
}
