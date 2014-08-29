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
			// 接收短信
			if (biz.isUsed()) {// 启用黑名单
				Object[] pdus = (Object[]) intent.getExtras().get("pdus");
				for (Object pdu : pdus) {
					SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
					// 如果短信号码存在于黑名单中，则拦截广播
					if (biz.exists(msg.getDisplayOriginatingAddress())) {
						abortBroadcast();
					}
				}
			}
		} else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
			// 开机广播,启动Service
			Intent service = new Intent(context, TelService.class);
			context.startService(service);
		}
	}
}
