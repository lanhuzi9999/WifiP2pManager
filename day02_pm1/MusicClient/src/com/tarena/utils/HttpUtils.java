package com.tarena.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	public static final int METHOD_GET = 1;
	public static final int METHOD_POST = 2;

	/**
	 * ����ָ��·������Դ����ȡ��Ӧʵ�����
	 * 
	 * @param uri
	 * @param params
	 * @param method
	 * @return
	 */
	public static HttpEntity getEntity(String uri, List<NameValuePair> params,
			int method) throws IOException {
		HttpEntity entity = null;
		// �����ͻ��˶���
		HttpClient client = new DefaultHttpClient();
		// �������ӳ�ʱʱ��
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		// �����������
		HttpUriRequest request = null;
		switch (method) {
		case METHOD_GET:// get����
			StringBuilder sb = new StringBuilder(uri);
			// �����Ҫ�ϴ�����
			if (params != null && !params.isEmpty()) {
				sb.append('?');
				for (NameValuePair pair : params) {
					sb.append(pair.getName()).append('=')
							.append(pair.getValue()).append('&');
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			request = new HttpGet(sb.toString());
			break;

		case METHOD_POST:// post����
			request = new HttpPost(uri);
			if (params != null && !params.isEmpty()) {
				// ��������ʵ�����
				UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(
						params);
				// ��������ʵ��
				((HttpPost) request).setEntity(reqEntity);
			}
			break;
		}
		// ִ����������Ӧ����
		HttpResponse response = client.execute(request);
		// �ж���Ӧ�� ���Ϊ 200 ���ȡʵ�����
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			entity = response.getEntity();
		}
		// ����ʵ�����
		return entity;
	}

	/**
	 * ��ȡʵ�峤��
	 * 
	 * @param entity
	 * @return
	 */
	public static long getLength(HttpEntity entity) {
		if (entity != null) {
			return entity.getContentLength();
		}
		return 0;
	}

	/**
	 * ��ȡʵ��������
	 * 
	 * @param entity
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static InputStream getStream(HttpEntity entity)
			throws IllegalStateException, IOException {
		if (entity != null) {
			return entity.getContent();
		}
		return null;
	}

	/**
	 * ��ȡʵ�����ݣ������ֽ�����
	 * 
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBytes(HttpEntity entity) throws IOException {
		if (entity != null) {
			return EntityUtils.toByteArray(entity);
		}
		return null;
	}
}
