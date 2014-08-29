package com.tarena.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory.Options;

public class BitmapUtils {
	/**
	 * ��ָ���ֽ����鰴 ָ����ߵȱ�������λͼ
	 * 
	 * @param data
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap loadBitmap(byte[] data, int width, int height) {
		// ����Options
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		// ���ر߽���Ϣ
		BitmapFactory.decodeByteArray(data, 0, data.length, opts);
		// ������������
		int xScale = opts.outWidth / width;
		int yScale = opts.outHeight / height;
		// ������������
		opts.inSampleSize = xScale > yScale ? xScale : yScale;
		opts.inJustDecodeBounds = false;
		// ���� ����λͼ
		return BitmapFactory.decodeByteArray(data, 0, data.length, opts);
	}

	/**
	 * ��ָ��·������ͼƬ
	 * 
	 * @param path
	 * @return
	 */
	public static Bitmap loadBitmap(String path) {
		return BitmapFactory.decodeFile(path);
	}

	/**
	 * ��λͼ���� ���浽ָ���ļ�
	 * 
	 * @param bm
	 * @param file
	 */
	public static void save(Bitmap bm, File file) throws IOException {
		// ����ļ�����Ŀ¼�����ڣ��򴴽�
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		// ����ļ�������,�򴴽�
		if (!file.exists()) {
			file.createNewFile();
		}

		// �����ļ������
		FileOutputStream out = new FileOutputStream(file);
		// ����ͼƬ��ָ�������
		bm.compress(CompressFormat.JPEG, 100, out);
		out.close();
	}
}
