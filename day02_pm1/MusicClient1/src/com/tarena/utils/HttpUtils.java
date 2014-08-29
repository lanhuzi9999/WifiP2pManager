package com.tarena.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;

import com.tarena.entity.Music;

public class HttpUtils {
	public static final int METHOD_GET = 1;
	public static final int METHOD_POST = 2;

	public static HttpEntity getEntity(String uri, List<NameValuePair> params,
			int method) {
		HttpEntity entity = null;
		return entity;
	}

	public static long getLength(HttpEntity entity) {
		return 0;
	}

	public static byte[] getBytes(HttpEntity entity) {
		return null;
	}

	public static InputStream getStream(HttpEntity entity) {
		return null;
	}
}
