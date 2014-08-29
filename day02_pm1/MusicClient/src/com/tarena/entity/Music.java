package com.tarena.entity;

public class Music {
	private int id;
	private String name;
	private String artist;
	private String album;
	private String author;
	private String composer;
	private String albumPicPath;
	private String musicPath;
	private String duration;
	private int downCount;
	private int favCount;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getDownCount() {
		return downCount;
	}
	public void setDownCount(int downCount) {
		this.downCount = downCount;
	}
	public int getFavCount() {
		return favCount;
	}
	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("������Ϣ\n");
		sb.append("���:").append(id).append('\n');
		sb.append("����:").append(name).append('\n');
		sb.append("����:").append(artist).append('\n');
		sb.append("ר��:").append(album).append('\n');
		sb.append("����:").append(author).append('\n');
		sb.append("����:").append(composer).append('\n');
		sb.append("ʱ��:").append(duration).append('\n');
		sb.append("���ش���:").append(downCount).append('\n');
		sb.append("�ղش���:").append(favCount).append('\n');
		return sb.toString();
	}
}
