package com.example.day_12_01_viewflipper;

import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	private ViewFlipper vfContainer;
	private GestureDetector detector;

	private void setupView() {
		// ��ȡ�ؼ�������
		vfContainer = (ViewFlipper) findViewById(R.id.vfContainer);
		// Ϊ�����childView
		vfContainer.addView(createView(1));
		vfContainer.addView(createView(2));
		vfContainer.addView(createView(3));
		// ���� ��������
		vfContainer.setInAnimation(this, android.R.anim.fade_in);
		vfContainer.setOutAnimation(this, android.R.anim.fade_out);
	}

	private View createView(int type) {
		// ��������ʼ��ListView
		ListView lv = new ListView(this);
		// ��ȡ����
		List<HashMap<String, Object>> data = null;
		String[] from = new String[4];
		switch (type) {
		case 1:// ѧԱ��Ϣ
			data = DataSource.getStudents(from);
			break;
		case 2:// �γ���Ϣ
			data = DataSource.getCourses(from);
			break;
		case 3:// �ɼ���Ϣ
			data = DataSource.getScores(from);
			break;
		}
		// ����������
		int[] to = { R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4 };
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item,
				from, to);
		// ����������
		lv.setAdapter(adapter);
		return lv;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupView();

		detector = new GestureDetector(this, new MYGestureListener());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		detector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class MYGestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if (e1.getX() - e2.getX() > 100) {
				vfContainer.showNext();
			} else if (e2.getX() - e1.getX() > 100) {
				vfContainer.showPrevious();
			}
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}

}
