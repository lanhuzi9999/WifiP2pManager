package com.tarena.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Handler;
import android.os.Message;

public class StreamUtils {
	/**
	 * 从输入流中读取数据 写出到输出流
	 * 
	 * @param in
	 * @param out
	 */
	public static void readData(InputStream in, OutputStream out,
			Handler handler) throws IOException {
		if (in != null && out != null) {
			BufferedInputStream bis = new BufferedInputStream(in);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			int len = -1;
			byte[] buffer = new byte[1024];
			int LoadedLength = 0;
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
				bos.flush();
				if (handler != null && LoadedLength++ % 200 == 0) {
					Message msg = Message.obtain();
					msg.what = GlobalConsts.MSG_WHAT_UPDATE_PROGRESS;
					msg.arg1 = LoadedLength;
					handler.sendMessage(msg);
				}
			}
			bos.close();
			in.close();
			bis.close();
			out.close();
		}
	}

	/**
	 * 从指定输入流读取数据 保存到指定文件
	 * 
	 * @param in
	 * @param file
	 * @throws IOException
	 */
	public static void save(InputStream in, File file, Handler handler)
			throws IOException {
		if (in != null && file != null) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			FileOutputStream out = new FileOutputStream(file);
			readData(in, out, handler);
		}
	}
}
