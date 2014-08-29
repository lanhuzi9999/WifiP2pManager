package com.tarena.day2201;

import com.tarena.bll.BlackListBiz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		BlackListBiz biz = new BlackListBiz(context);
		String action = intent.getAction();
		if ("android.provider.Telephony.SMS_RECEIVED".equals(action)) {
			// ���ն���
			if (biz.isUsed()) {// ���ú�����
				Object[] pdus = (Object[]) intent.getExtras().get("pdus");
				for (Object pdu : pdus) {
					SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
					// ������ź�������ں������У������ع㲥
					if (biz.exists(msg.getDisplayOriginatingAddress())) {
						abortBroadcast();
					}
				}
			}
		} else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
			// �����㲥,����Service
			Intent service = new Intent(context, TelService.class);
			context.startService(service);
		}
	}
}
