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
		// 获取引用
		galThumbs = (Gallery) findViewById(R.id.galThumbnails);
		// 获取数据
		ArrayList<ImageInfo> infos = imgBiz.getImages();
		// 创建适配器
		adapter = new ImageAdapter(this, infos);
		// 设置适配器
		galThumbs.setAdapter(adapter);

		// 初始化imageview
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	private void addListeners() {
		galThumbs.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// 获取图片
				Bitmap bm = imgBiz.getBitmap((int) id);
				// 显示图片
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
			// 获取当前选中项的索引
			int currentPosition = galThumbs.getSelectedItemPosition();
			// 计算上一幅 或 下一幅图片的索引
			if (e1.getX() - e2.getX() > 20) {
				// 从右向左,下一幅图片
				if (++currentPosition == galThumbs.getCount()) {
					currentPosition = 0;
				}
			} else if (e2.getX() - e1.getX() > 20) {
				// 从左向右,上一幅图片
				if (--currentPosition < 0) {
					currentPosition = galThumbs.getCount() - 1;
				}
			}
			// 设置为选中项
			galThumbs.setSelection(currentPosition);
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}

}
