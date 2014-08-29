package com.tarena.day1005;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;

import com.tarena.adapter.ImageAdapter;
import com.tarena.bll.ImageBiz;
import com.tarena.entity.ImageInfo;

public class MainActivity extends Activity {
	private ImageView ivPic;
	private Gallery galThumbs;
	private ImageBiz imgBiz;
	private ImageAdapter adapter;
	private GestureDetector detector;

	private void setupView() {
		// ��ȡ����
		galThumbs = (Gallery) findViewById(R.id.galThumbnails);
		// ��ȡ����
		ArrayList<ImageInfo> infos = imgBiz.getImages();
		// ����������
		adapter = new ImageAdapter(this, infos);
		// ����������
		galThumbs.setAdapter(adapter);

		// ��ʼ��imageview
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	private void addListeners() {
		galThumbs.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// ��ȡͼƬ
				Bitmap bm = imgBiz.getBitmap((int) id);
				// ��ʾͼƬ
				if (bm != null)
					ivPic.setImageBitmap(bm);
				else
					ivPic.setImageResource(R.drawable.ic_launcher);
				;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imgBiz = new ImageBiz(this);
		setupView();
		addListeners();

		detector = new GestureDetector(this, new MyGestureListener());

		// galThumbs.getSelectedItemPosition();
		// galThumbs.setSelection(0);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		detector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class MyGestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// ��ȡ��ǰѡ���������
			int currentPosition = galThumbs.getSelectedItemPosition();
			// ������һ�� �� ��һ��ͼƬ������
			if (e1.getX() - e2.getX() > 20) {
				// ��������,��һ��ͼƬ
				if (++currentPosition == galThumbs.getCount()) {
					currentPosition = 0;
				}
			} else if (e2.getX() - e1.getX() > 20) {
				// ��������,��һ��ͼƬ
				if (--currentPosition < 0) {
					currentPosition = galThumbs.getCount() - 1;
				}
			}
			// ����Ϊѡ����
			galThumbs.setSelection(currentPosition);
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}

}
