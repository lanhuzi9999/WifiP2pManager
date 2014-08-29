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
	 * 请求指定路径的资源，获取响应实体对象
	 * 
	 * @param uri
	 * @param params
	 * @param method
	 * @return
	 */
	public static HttpEntity getEntity(String uri, List<NameValuePair> params,
			int method) throws IOException {
		HttpEntity entity = null;
		// 创建客户端对象
		HttpClient client = new DefaultHttpClient();
		// 设置连接超时时间
		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		// 创建请求对象
		HttpUriRequest request = null;
		switch (method) {
		case METHOD_GET:// get请求
			StringBuilder sb = new StringBuilder(uri);
			// 如果需要上传数据
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

		case METHOD_POST:// post请求
			request = new HttpPost(uri);
			if (params != null && !params.isEmpty()) {
				// 创建请求实体对象
				UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(
						params);
				// 设置请求实体
				((HttpPost) request).setEntity(reqEntity);
			}
			break;
		}
		// 执行请求获得响应对象
		HttpResponse response = client.execute(request);
		// 判断响应吗 如果为 200 则获取实体对象
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			entity = response.getEntity();
		}
		// 返回实体对象
		return entity;
	}

	/**
	 * 获取实体长度
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
	 * 获取实体输入流
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
	 * 读取实体内容，返回字节数组
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
