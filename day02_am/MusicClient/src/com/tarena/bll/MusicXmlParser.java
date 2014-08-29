package com.tarena.bll;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ApplicationErrorReport.CrashInfo;

import com.tarena.entity.Music;

public class MusicXmlParser {
	/**
	 * ʹ����ʽ����������xml����
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 * @throws NumberFormatException
	 */
	public ArrayList<Music> parse(InputStream is) throws NumberFormatException,
			XmlPullParserException, IOException {
		ArrayList<Music> musics = null;
		Music currentMusic = null;
		// ��������������
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		// ��������Դ
		parser.setInput(new InputStreamReader(is));
		// ��ȡ��ʼ�¼�����
		int eventType = parser.getEventType();
		// ���¼����Ͳ����� END_DOCUMENTʱ ѭ������
		while (eventType != XmlPullParser.END_DOCUMENT) {
			// �¼�����
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:// ��ʼ����xml�ĵ�
				// ��ʼ������
				musics = new ArrayList<Music>();
				break;
			case XmlPullParser.START_TAG:// ��������ʼ��ǩ
				// ��ȡ��ǰ��ǩ��
				String tagName = parser.getName();
				if ("music".equals(tagName)) {
					// ʵ����currentMusic����
					currentMusic = new Music();
					// ��ȡ������id����
					currentMusic.setId(Integer.parseInt(parser
							.getAttributeValue(null, "id")));
				} else if ("name".equals(tagName)) {
					currentMusic.setName(parser.nextText());
				} else if ("singer".equals(tagName)) {
					currentMusic.setArtist(parser.nextText());
				} else if ("author".equals(tagName)) {
					currentMusic.setAuthor(parser.nextText());
				} else if ("composer".equals(tagName)) {
					currentMusic.setComposer(parser.nextText());
				} else if ("album".equals(tagName)) {
					currentMusic.setAlbum(parser.nextText());
				} else if ("albumpic".equals(tagName)) {
					currentMusic.setAlbumPicPath(parser.nextText());
				} else if ("musicpath".equals(tagName)) {
					currentMusic.setMusicPath(parser.nextText());
				} else if ("time".equals(tagName)) {
					currentMusic.setDuration(parser.nextText());
				} else if ("downcount".equals(tagName)) {
					currentMusic.setDownCount(Integer.parseInt(parser
							.nextText()));
				} else if ("favcount".equals(tagName)) {
					currentMusic
							.setFavCount(Integer.parseInt(parser.nextText()));
				}
				break;
			case XmlPullParser.END_TAG:// ������������ǩ
				if ("music".equals(parser.getName())) {
					// ���ʵ����� ������
					musics.add(currentMusic);
				}
				break;
			}
			// ��������ȡ��һ�¼�
			eventType = parser.next();
		}
		// ���ؽ������
		return musics;
	}
}
