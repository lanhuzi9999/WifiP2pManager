package com.tarena.bll;

import java.util.ArrayList;

import com.tarena.entity.Music;

import android.os.AsyncTask;

public class AsyncMusicParseTask extends
		AsyncTask<String, String, ArrayList<Music>> {

	@Override
	protected ArrayList<Music> doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<Music> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

}
