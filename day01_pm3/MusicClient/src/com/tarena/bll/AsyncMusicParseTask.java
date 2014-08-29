package com.tarena.bll;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.conn.ConnectTimeoutException;
import org.xmlpull.v1.XmlPullParserException;

import com.tarena.entity.Music;
import com.tarena.musicclient.MainActivity;

import android.os.AsyncTask;
import android.widget.Toast;

public class AsyncMusicParseTask extends
		AsyncTask<String, String, ArrayList<Music>> {
	private MainActivity context;

	public AsyncMusicParseTask(MainActivity context) {
		this.context = context;
	}

	@Override
	protected ArrayList<Music> doInBackground(String... params) {
		ArrayList<Music> musics = null;
		try {
			publishProgress("开始解析xml,请稍后...");
			// 连接http服务端，获取实体集合
			musics = new MusicBiz().getMusics(params[0]);
			publishProgress("xml解析完成");
		} catch (ConnectTimeoutException ex) {
			publishProgress("连接超时");
			ex.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return musics;
	}

	@Override
	protected void onPostExecute(ArrayList<Music> result) {
		// TODO Auto-generated method stub
		// 更新界面
		context.updateListView(result);
	}

	@Override
	protected void onProgressUpdate(String... values) {
		Toast.makeText(context, values[0], Toast.LENGTH_SHORT).show();
	}

}
