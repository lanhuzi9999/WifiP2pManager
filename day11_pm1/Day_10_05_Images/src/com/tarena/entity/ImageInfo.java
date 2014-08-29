package com.tarena.entity;

import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class ImageInfo {
	private int id;
	private String title;
	private SoftReference<Bitmap> thumbnail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SoftReference<Bitmap> getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(SoftReference<Bitmap> thumbnail) {
		this.thumbnail = thumbnail;
	}

}
