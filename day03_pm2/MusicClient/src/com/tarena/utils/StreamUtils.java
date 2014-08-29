package com.tarena.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtils {
	/**
	 * ���������ж�ȡ���� д���������
	 * 
	 * @param in
	 * @param out
	 */
	public static void readData(InputStream in, OutputStream out)
			throws IOException {
		if (in != null && out != null) {
			BufferedInputStream bis = new BufferedInputStream(in);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			int len = -1;
			byte[] buffer = new byte[1024];
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
				bos.flush();
			}
			bos.close();
			in.close();
			bis.close();
			out.close();
		}
	}

	/**
	 * ��ָ����������ȡ���� ���浽ָ���ļ�
	 * 
	 * @param in
	 * @param file
	 * @throws IOException
	 */
	public static void save(InputStream in, File file) throws IOException {
		if (in != null && file != null) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			FileOutputStream out = new FileOutputStream(file);
			readData(in, out);
		}
	}
}
