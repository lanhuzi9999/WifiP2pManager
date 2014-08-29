package com.tarena.day0707;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//获取当前接收到的广播的广播动作
		String action = intent.getAction();
		//依据动作判断当前接收的广播的类型
		if("com.tarena.action.BROADCAST".equals(action)){
			//收到自定义广播
			Log.i("info", "收到自定义广播");
		}else if(Intent.ACTION_BOOT_COMPLETED.equals(action)){
			//收到开机广播
		}else if(Intent.ACTION_BATTERY_LOW.equals(action)){
			//收到电量低广播
		}
	}

}
