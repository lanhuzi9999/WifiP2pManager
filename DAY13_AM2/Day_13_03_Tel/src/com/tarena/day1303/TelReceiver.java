package com.tarena.day1303;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TelReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (Intent.ACTION_NEW_OUTGOING_CALL.equals(action)) {
			// �绰����
			// ��ȡ��������
			String number = getResultData();
			// String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			Log.i("info", "�е绰����:" + number);

			setResultData(null);
		}
	}

}
