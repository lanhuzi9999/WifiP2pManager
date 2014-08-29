package com.tarena.day1109;

import android.os.Bundle;
import android.app.Activity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	private ViewFlipper vfContainer;
	private LayoutInflater inflater;
	private GestureDetector detector;

	private void setupView() {
		vfContainer = (ViewFlipper) findViewById(R.id.vfcontainer);
		// 添加childView
		vfContainer.addView(createChildView(R.drawable.p02, "P02"));
		vfContainer.addView(createChildView(R.drawable.p03, "P03"));
		vfContainer.addView(createChildView(R.drawable.p04, "P04"));
		// 设置过场动画
		vfContainer.setInAnimation(this, android.R.anim.fade_in);
		vfContainer.setOutAnimation(this, android.R.anim.fade_out);
	}

	private View createChildView(int drawalbeRes, String title) {
		View view = inflater.inflate(R.layout.child_view, null);
		ImageView iv = (ImageView) view.findViewById(R.id.ivPic);
		TextView tv = (TextView) view.findViewById(R.id.tvTitle);

		iv.setImageResource(drawalbeRes);
		tv.setText(title);

		return view;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inflater = LayoutInflater.from(this);
		setupView();

		detector = new GestureDetector(this, new MyGestureListener());
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
			if (e1.getX() - e2.getX() > 20) {
				// 从右向左 下一页
				vfContainer.showNext();
			} else if (e2.getX() - e1.getX() > 20) {
				// 从左向右上一页
				vfContainer.showPrevious();
			}
			return super.onFling(e1, e2, velocityX, velocityY);
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			if (vfContainer.isFlipping()) {
				vfContainer.stopFlipping();
			} else {
				vfContainer.startFlipping();
			}
			return super.onDoubleTap(e);
		}
	}

}
