package com.tarena.dal;

import java.util.ArrayList;

import com.tarena.entity.Music;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Audio.Media;

public class MusicDao {
	private ContentResolver cr;

	public MusicDao(Context context) {
		cr = context.getContentResolver();
	}

	/**
	 * 根据albumKey 查找AlbumArt
	 * 
	 * @param albumKey
	 * @return
	 */
	private String getAlbumPath(String albumKey) {
		String path = null;
		// 准备Uri
		Uri uri = Albums.EXTERNAL_CONTENT_URI;
		// 执行查询
		Cursor c = cr.query(uri, new String[] { Albums.ALBUM_ART },
				Albums.ALBUM_KEY + "=?", new String[] { albumKey }, null);
		if (c != null && c.moveToFirst()) {
			path = c.getString(0);
			c.close();
		}
		return path;
	}

	/**
	 * 查询本地媒体库中的所有时长大于30秒音乐信息
	 * 
	 * @return
	 */
	public ArrayList<Music> getMusics() {
		ArrayList<Music> musics = null;
		// 准备Uri
		Uri uri = Media.EXTERNAL_CONTENT_URI;
		// 准备查询的列
		String[] projection = { "_id", "_data", "composer", "artist", "album",
				"duration", "album_key", "title" };
		// 查询
		Cursor c = cr.query(uri, projection, Media.DURATION + ">30000", null,
				null);
		if (c != null) {
			musics = new ArrayList<Music>();
			while (c.moveToNext()) {
				Music m = new Music();
				m.setId(c.getInt(c.getColumnIndex("_id")));
				m.setName(c.getString(c.getColumnIndex("title")));
				m.setAlbum(c.getString(c.getColumnIndex("album")));
				m.setComposer(c.getString(c.getColumnIndex("composer")));
				m.setArtist(c.getString(c.getColumnIndex("artist")));
				m.setMusicPath(c.getString(c.getColumnIndex("_data")));
				m.setAlbumPicPath(getAlbumPath(c.getString(c
						.getColumnIndex("album_key"))));
				m.setDuration(c.getLong(c.getColumnIndex("duration")));
				// 添加音乐信息到集合
				musics.add(m);
			}
			c.close();
		}
		return musics;
	}
}
