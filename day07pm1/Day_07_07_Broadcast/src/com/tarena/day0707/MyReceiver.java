package com.tarena.day0707;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//��ȡ��ǰ���յ��Ĺ㲥�Ĺ㲥����
		String action = intent.getAction();
		//���ݶ����жϵ�ǰ���յĹ㲥������
		if("com.tarena.action.BROADCAST".equals(action)){
			//�յ��Զ���㲥
			Log.i("info", "�յ��Զ���㲥");
		}else if(Intent.ACTION_BOOT_COMPLETED.equals(action)){
			//�յ������㲥
		}else if(Intent.ACTION_BATTERY_LOW.equals(action)){
			//�յ������͹㲥
		}
	}

}
