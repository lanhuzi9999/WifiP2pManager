package com.tarena.day1304;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	private TelephonyManager tm;
	private PhoneStateListener listener;
	private AudioManager am;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		am = (AudioManager) getSystemService(AUDIO_SERVICE);

		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		listener = new PhoneStateListener() {

			@Override
			public void onCallStateChanged(int state, String incomingNumber) {
				// am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:// 空闲
					Log.i("info", "电话空闲");
					break;
				case TelephonyManager.CALL_STATE_RINGING:// 响铃
					Log.i("info", "来电话啦." + incomingNumber);
					if ("110".equals(incomingNumber)) {
						// am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
						try {
							// 挂断电话
							Method method = Class.forName(
									"android.os.ServiceManager").getDeclaredMethod(
									"getService", String.class);
							IBinder binder = (IBinder) method.invoke(null,
									TELEPHONY_SERVICE);
							ITelephony tel = ITelephony.Stub.asInterface(binder);
							tel.endCall();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:// 摘机
					Log.i("info", "摘机状态");
					break;
				}
			}

		};
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		tm.listen(listener, PhoneStateListener.LISTEN_NONE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
