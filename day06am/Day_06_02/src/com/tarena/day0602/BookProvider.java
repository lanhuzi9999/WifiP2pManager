package com.tarena.day0602;

import com.tarena.dal.DBOpenHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class BookProvider extends ContentProvider {
	private DBOpenHelper helper;
	private static UriMatcher matcher;
	static {
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
		// 添加uri格式
		matcher.addURI("com.tarena.providers.book", "book", 1);
		matcher.addURI("com.tarena.providers.book", "book/#", 2);
	}

	@Override
	public boolean onCreate() {
		// 实例化helper
		helper = new DBOpenHelper(this.getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// 分析uri 确定访问的表 和 条件
		String where = null;
		switch (matcher.match(uri)) {
		case 1:// 访问book的多条数据的uri
			where = selection;
			break;
		case 2:// 访问book的单条数据的uri
			where = "_id=" + uri.getLastPathSegment();
			// where = "_id=" + ContentUris.parseId(uri);
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("无效的Uri:" + uri);
		}
		// 获取数据库访问对象
		SQLiteDatabase db = helper.getReadableDatabase();
		// 执行查询
		Cursor c = db.query("booktbl", projection, where, selectionArgs, null,
				null, sortOrder);
		return c;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// 分析uri 确定访问的表
		if (matcher.match(uri) != 1) {
			throw new IllegalArgumentException("非法Uri:" + uri);
		}
		// 获取数据库访问对象
		SQLiteDatabase db = helper.getWritableDatabase();
		// 执行操作
		long rowId = db.insert("booktbl", null, values);
		Uri retUri = ContentUris.withAppendedId(uri, rowId);
		// 释放资源
		db.close();
		return retUri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// 分析uri 确定访问的表 和 条件
		String where = null;
		switch (matcher.match(uri)) {
		case 1:// 访问book的多条数据的uri
			where = selection;
			break;
		case 2:// 访问book的单条数据的uri
			where = "_id=" + uri.getLastPathSegment();
			// where = "_id=" + ContentUris.parseId(uri);
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("无效的Uri:" + uri);
		}
		// 获取数据库访问对象
		SQLiteDatabase db = helper.getWritableDatabase();
		// 执行修改
		int count = db.delete("booktbl", where, selectionArgs);
		// 释放资源
		db.close();
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// 分析uri 确定访问的表 和 条件
		String where = null;
		switch (matcher.match(uri)) {
		case 1:// 访问book的多条数据的uri
			where = selection;
			break;
		case 2:// 访问book的单条数据的uri
			where = "_id=" + uri.getLastPathSegment();
			// where = "_id=" + ContentUris.parseId(uri);
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("无效的Uri:" + uri);
		}
		// 获取数据库访问对象
		SQLiteDatabase db = helper.getWritableDatabase();
		// 执行修改
		int count = db.update("booktbl", values, where, selectionArgs);
		// 释放资源
		db.close();
		return count;
	}

}
