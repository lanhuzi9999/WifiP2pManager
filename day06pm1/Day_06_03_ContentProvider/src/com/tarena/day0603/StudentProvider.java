package com.tarena.day0603;

import com.tarena.dal.DBOpenHelper;
import com.tarena.student.StuProviderInfo;
import com.tarena.student.StuProviderInfo.StudentInfo;
import com.tarena.student.StuProviderInfo.UserInfo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class StudentProvider extends ContentProvider {
	private static final int CODE_MULTI_STU = 1;
	private static final int CODE_MULTI_USER = 2;
	private static final int CODE_SINGLE_STU = 3;
	private static final int CODE_SINGLE_USER = 4;

	private DBOpenHelper helper;
	private static UriMatcher matcher;
	static {
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
		// ���uri
		matcher.addURI(StuProviderInfo.AUTHORITY,
				StuProviderInfo.MULTI_STU_PATH, CODE_MULTI_STU);
		matcher.addURI(StuProviderInfo.AUTHORITY,
				StuProviderInfo.SINGLE_STU_PATH, CODE_SINGLE_STU);
		matcher.addURI(StuProviderInfo.AUTHORITY,
				StuProviderInfo.MULTI_USER_PATH, CODE_MULTI_USER);
		matcher.addURI(StuProviderInfo.AUTHORITY,
				StuProviderInfo.SINGLE_USER_PATH, CODE_SINGLE_USER);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		helper = new DBOpenHelper(this.getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// ����uri ȷ���� �� ����
		String tblName = null;
		String where = null;
		switch (matcher.match(uri)) {
		case CODE_MULTI_STU:// ����ѧ����������ݵ�uri
			tblName = "stutbl";
			where = selection;
			break;
		case CODE_SINGLE_STU:// ����ѧ���������ݵ�uri
			tblName = "stutbl";
			where = StudentInfo.ID + "=" + ContentUris.parseId(uri);
			if (selection != null) {
				where += " and(" + selection + ")";
			}
			break;
		case CODE_MULTI_USER:// �����û���������ݵ�uri
			tblName = "usertbl";
			where = selection;
			break;
		case CODE_SINGLE_USER:// �����û��������ݵ�uri
			tblName = "usertbl";
			where = UserInfo.ID + "=" + ContentUris.parseId(uri);
			if (selection != null) {
				where += " and(" + selection + ")";
			}
			break;
		default:
			throw new IllegalArgumentException("δ֪Uri:" + uri);
		}
		// ��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getReadableDatabase();
		// ִ�в�ѯ����
		Cursor c = db.query(tblName, projection, where, selectionArgs, null,
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
		// ����uri ȷ���� �� ����
		String tblName = null;
		switch (matcher.match(uri)) {
		case CODE_MULTI_STU:// ����ѧ����������ݵ�uri
			tblName = "stutbl";
			break;
		case CODE_MULTI_USER:// �����û���������ݵ�uri
			tblName = "usertbl";
			break;
		default:
			throw new IllegalArgumentException("δ֪Uri:" + uri);
		}

		// ��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getWritableDatabase();
		// ִ�в���
		long rowId = db.insert(tblName, null, values);
		Uri retUri = ContentUris.withAppendedId(uri, rowId);
		// �ͷ���Դ
		db.close();
		return retUri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// ����uri ȷ���� �� ����
		String tblName = null;
		String where = null;
		switch (matcher.match(uri)) {
		case CODE_MULTI_STU:// ����ѧ����������ݵ�uri
			tblName = "stutbl";
			where = selection;
			break;
		case CODE_SINGLE_STU:// ����ѧ���������ݵ�uri
			tblName = "stutbl";
			where = StudentInfo.ID + "=" + ContentUris.parseId(uri);
			if (selection != null) {
				where += " and(" + selection + ")";
			}
			break;
		case CODE_MULTI_USER:// �����û���������ݵ�uri
			tblName = "usertbl";
			where = selection;
			break;
		case CODE_SINGLE_USER:// �����û��������ݵ�uri
			tblName = "usertbl";
			where = UserInfo.ID + "=" + ContentUris.parseId(uri);
			if (selection != null) {
				where += " and(" + selection + ")";
			}
			break;
		default:
			throw new IllegalArgumentException("δ֪Uri:" + uri);
		}
		// ��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getWritableDatabase();
		// ִ�в���
		int count = db.delete(tblName, where, selectionArgs);
		// �ͷ���Դ
		db.close();
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// ����uri ȷ���� �� ����
		String tblName = null;
		String where = null;
		switch (matcher.match(uri)) {
		case CODE_MULTI_STU:// ����ѧ����������ݵ�uri
			tblName = "stutbl";
			where = selection;
			break;
		case CODE_SINGLE_STU:// ����ѧ���������ݵ�uri
			tblName = "stutbl";
			where = StudentInfo.ID + "=" + ContentUris.parseId(uri);
			if (selection != null) {
				where += " and(" + selection + ")";
			}
			break;
		case CODE_MULTI_USER:// �����û���������ݵ�uri
			tblName = "usertbl";
			where = selection;
			break;
		case CODE_SINGLE_USER:// �����û��������ݵ�uri
			tblName = "usertbl";
			where = UserInfo.ID + "=" + ContentUris.parseId(uri);
			if (selection != null) {
				where += " and(" + selection + ")";
			}
			break;
		default:
			throw new IllegalArgumentException("δ֪Uri:" + uri);
		}
		// ��ȡ���ݿ���ʶ���
		SQLiteDatabase db = helper.getWritableDatabase();
		// ִ�в���
		int count = db.update(tblName, values, where, selectionArgs);
		// �ͷ���Դ
		db.close();
		return count;
	}

}
