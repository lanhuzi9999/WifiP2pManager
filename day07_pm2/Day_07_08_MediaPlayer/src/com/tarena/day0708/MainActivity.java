package com.tarena.day0708;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	private MediaPlayer player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			player = new MediaPlayer();
			player.reset();
			player.setDataSource("/mnt/sdcard/Music/musics/yingbi.mp3");
			player.prepare();
			player.start();
			// player.prepareAsync();
			// player.setOnPreparedListener(new OnPreparedListener() {
			//
			// @Override
			// public void onPrepared(MediaPlayer mp) {
			// // TODO Auto-generated method stub
			// player.start();
			// }
			// });

			// player.pause();
			// player.stop();
			// player.isLooping();
			// player.isPlaying();
			// player.setOnCompletionListener(new OnCompletionListener() {
			//
			// @Override
			// public void onCompletion(MediaPlayer mp) {
			//
			// }
			// });
			// player.getCurrentPosition();
			// player.getDuration();
			// player.seekTo(156000);
			Log.i("info", "play");

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		player.release();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
