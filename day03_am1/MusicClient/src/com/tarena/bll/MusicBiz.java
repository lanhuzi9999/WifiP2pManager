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
	 * ��ȡָ������˵� ����
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
		// ����http����ˣ���ȡʵ��������
		HttpEntity entity = HttpUtils
				.getEntity(uri, null, HttpUtils.METHOD_GET);
		// ���������� ���ʵ�弯������
		InputStream is = HttpUtils.getStream(entity);
		// ����ʵ�弯��
		musics = new MusicXmlParser().parse(is);
		return musics;
	}
}
