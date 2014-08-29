package com.tarena.entity;

import com.tarena.utils.FormatUtils;

public class Music {
	private int id;
	private String name;
	private String artist;
	private String album;
	private String composer;
	private String albumPicPath;
	private String musicPath;
	private long duration;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getAlbumPicPath() {
		return albumPicPath;
	}

	public void setAlbumPicPath(String albumPicPath) {
		this.albumPicPath = albumPicPath;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("������Ϣ\n");
		sb.append("���:").append(id).append('\n');
		sb.append("����:").append(name).append('\n');
		sb.append("����:").append(artist).append('\n');
		sb.append("ר��:").append(album).append('\n');
		sb.append("����:").append(composer).append('\n');
		sb.append("ʱ��:").append(FormatUtils.format(duration)).append('\n');
		return sb.toString();
	}
}
