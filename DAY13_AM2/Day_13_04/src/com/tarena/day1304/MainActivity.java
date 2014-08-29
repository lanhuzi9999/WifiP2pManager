package com.tarena.day1304;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
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
				am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:// ����
					Log.i("info", "�绰����");
					break;
				case TelephonyManager.CALL_STATE_RINGING:// ����
					Log.i("info", "���绰��." + incomingNumber);
					if ("110".equals(incomingNumber)) {
						am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					}
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:// ժ��
					Log.i("info", "ժ��״̬");
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
