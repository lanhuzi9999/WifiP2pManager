package com.tarena.day1307;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if ("android.provider.Telephony.SMS_RECEIVED".equals(action)) {
			// 收到短信
			Object[] pdus = (Object[]) intent.getExtras().get("pdus");
			for (Object pdu : pdus) {
				SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
				String number = sms.getDisplayOriginatingAddress();
				String content = sms.getDisplayMessageBody();

				Log.i("info", number + ":" + content);
			}
		}
		//如果当前广播是有序广播
		if(isOrderedBroadcast()){
			abortBroadcast();//拦截广播
		}
	}

}
