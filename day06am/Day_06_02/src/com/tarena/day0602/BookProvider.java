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
		// ���uri��ʽ
		matcher.addURI("com.tarena.providers.book", "book", 1);
		matcher.addURI("com.tarena.providers.book", "book/#", 2);
	}

	@Override
	public boolean onCreate() {
		// ʵ����helper
		helper = new DBOpenHelper(this.getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// ����uri ȷ�����ʵı� �� ����
		String where = null;
		switch (matcher.match(uri)) {
		case 1:// ����book�Ķ������ݵ�uri
			where = selection;
			break;
		case 2:// ����book�ĵ������ݵ�uri
			where = "_id=" + uri.getLastPathSegment();
			// where = "_id=" + ContentUris.parseId(uri);
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("��Ч��Uri:" + uri);
		}
		// ��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getReadableDatabase();
		// ִ�в�ѯ
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
		// ����uri ȷ�����ʵı�
		if (matcher.match(uri) != 1) {
			throw new IllegalArgumentException("�Ƿ�Uri:" + uri);
		}
		// ��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getWritableDatabase();
		// ִ�в���
		long rowId = db.insert("booktbl", null, values);
		Uri retUri = ContentUris.withAppendedId(uri, rowId);
		// �ͷ���Դ
		db.close();
		return retUri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// ����uri ȷ�����ʵı� �� ����
		String where = null;
		switch (matcher.match(uri)) {
		case 1:// ����book�Ķ������ݵ�uri
			where = selection;
			break;
		case 2:// ����book�ĵ������ݵ�uri
			where = "_id=" + uri.getLastPathSegment();
			// where = "_id=" + ContentUris.parseId(uri);
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("��Ч��Uri:" + uri);
		}
		// ��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getWritableDatabase();
		// ִ���޸�
		int count = db.delete("booktbl", where, selectionArgs);
		// �ͷ���Դ
		db.close();
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// ����uri ȷ�����ʵı� �� ����
		String where = null;
		switch (matcher.match(uri)) {
		case 1:// ����book�Ķ������ݵ�uri
			where = selection;
			break;
		case 2:// ����book�ĵ������ݵ�uri
			where = "_id=" + uri.getLastPathSegment();
			// where = "_id=" + ContentUris.parseId(uri);
			if (selection != null)
				where += " and (" + selection + ")";
			break;
		default:
			throw new IllegalArgumentException("��Ч��Uri:" + uri);
		}
		// ��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getWritableDatabase();
		// ִ���޸�
		int count = db.update("booktbl", values, where, selectionArgs);
		// �ͷ���Դ
		db.close();
		return count;
	}

}
