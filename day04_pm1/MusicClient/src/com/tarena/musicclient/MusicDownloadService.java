package com.tarena.musicclient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;

import com.tarena.utils.GlobalConsts;
import com.tarena.utils.HttpUtils;
import com.tarena.utils.StreamUtils;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class MusicDownloadService extends IntentService {
	private static final int MSG_WHAT_FILE_EXISTS = 1;
	private static final int MSG_WHAT_STARTEDED = 4;
	private static final int MSG_WHAT_FINISHED = 2;
	private static final int MSG_WHAT_FAILED = 3;
	private Handler handler;

	public MusicDownloadService() {
		super("workThread");
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				Toast.makeText(MusicDownloadService.this, msg.obj.toString(),
						Toast.LENGTH_LONG).show();
			};
		};
	}

	/**
	 * ������Ϣ�ķ���
	 * 
	 * @param what
	 * @param obj
	 */
	private void sendMessage(int what, Object obj) {
		Message msg = Message.obtain(handler, what, obj);
		msg.sendToTarget();
	}

	/**
	 * �ڹ����߳������еģ����ڴ������ҵ��
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		// ��ȡ����·��
		String path = intent.getStringExtra("path");
		// �ж��ļ��Ƿ����
		File file = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),
				path);
		if (file.exists()) {
			sendMessage(MSG_WHAT_FILE_EXISTS, "�ļ��Ѵ��ڣ������ظ�����");
			return;
		}
		try {
			// ִ������
			sendMessage(MSG_WHAT_STARTEDED, "��ʼ��������");
			HttpEntity entity = HttpUtils.getEntity(GlobalConsts.BASE_URL
					+ path, null, HttpUtils.METHOD_GET);
			InputStream in = HttpUtils.getStream(entity);
			StreamUtils.save(in, file);
			sendMessage(MSG_WHAT_FINISHED, "�����������");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sendMessage(MSG_WHAT_FAILED, "��������ʧ��");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sendMessage(MSG_WHAT_FAILED, "��������ʧ��");
		}

	}

}
