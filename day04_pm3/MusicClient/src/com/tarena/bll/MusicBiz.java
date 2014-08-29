package com.tarena.bll;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.xmlpull.v1.XmlPullParserException;

import com.tarena.entity.Music;
import com.tarena.utils.HttpUtils;

public class MusicBiz {
	/**
	 * 获取指定服务端的 数据
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws XmlPullParserException
	 * @throws NumberFormatException
	 */
	public ArrayList<Music> getMusics(String uri) throws IllegalStateException,
			IOException, NumberFormatException, XmlPullParserException {
		ArrayList<Music> musics = null;
		// 连接http服务端，获取实体输入流
		HttpEntity entity = HttpUtils
				.getEntity(uri, null, HttpUtils.METHOD_GET);
		// 解析输入流 获得实体集合数据
		InputStream is = HttpUtils.getStream(entity);
		// 返回实体集合
		musics = new MusicXmlParser().parse(is);
		return musics;
	}
}
