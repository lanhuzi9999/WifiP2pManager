package cm.tarena.day0202;

import java.lang.ref.SoftReference;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Student stu = new Student();
		stu.id = 1;
		stu.name = "ÕÅÈý";

		SoftReference<Student> r = new SoftReference<MainActivity.Student>(stu);
		stu = null;
		
		if(r.get()!=null)
			r.get().id = 2;

	}

	private class Student {
		private int id;
		private String name;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
