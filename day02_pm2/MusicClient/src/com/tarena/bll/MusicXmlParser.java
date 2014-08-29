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
	 * 使用流式解析，解析xml数据
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
		// 创建解析器对象
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		// 设置输入源
		parser.setInput(new InputStreamReader(is));
		// 获取初始事件类型
		int eventType = parser.getEventType();
		// 当事件类型不等于 END_DOCUMENT时 循环解析
		while (eventType != XmlPullParser.END_DOCUMENT) {
			// 事件解析
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:// 开始解析xml文档
				// 初始化集合
				musics = new ArrayList<Music>();
				break;
			case XmlPullParser.START_TAG:// 解析到开始标签
				// 获取当前标签名
				String tagName = parser.getName();
				if ("music".equals(tagName)) {
					// 实例化currentMusic对象
					currentMusic = new Music();
					// 获取并设置id属性
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
			case XmlPullParser.END_TAG:// 解析到结束标签
				if ("music".equals(parser.getName())) {
					// 添加实体对象 到集合
					musics.add(currentMusic);
				}
				break;
			}
			// 驱动并获取下一事件
			eventType = parser.next();
		}
		// 返回解析结果
		return musics;
	}
}
