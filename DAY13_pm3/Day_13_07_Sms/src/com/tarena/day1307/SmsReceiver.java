package com.tarena.day1307;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if ("android.provider.Telephony.SMS_RECEIVED".equals(action)) {
			// �յ�����
			Object[] pdus = (Object[]) intent.getExtras().get("pdus");
			ContentResolver cr = context.getContentResolver();
			for (Object pdu : pdus) {
				SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
				String number = sms.getDisplayOriginatingAddress();
				String content = sms.getDisplayMessageBody();

				// �������ݿ�
				Uri uri = Uri.parse("content://sms/inbox");
				ContentValues values = new ContentValues();
				values.put("address", number);
				values.put("body", content);
				values.put("date", System.currentTimeMillis());
				cr.insert(uri, values);

				Log.i("info", number + ":" + content);
			}
		}
		// �����ǰ�㲥������㲥
		if (isOrderedBroadcast()) {
			abortBroadcast();// ���ع㲥
		}
	}

}
