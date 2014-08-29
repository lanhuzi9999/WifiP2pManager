package com.tarena.day2201;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;
import com.tarena.bll.BlackListBiz;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class TelService extends Service {
	private class MyPhoneStateListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:
				// 如果黑名单启用且该呼入号码存在于黑名单中
				if (biz.isUsed() && biz.exists(incomingNumber)) {
					endCall();// 挂断电话
				}
				break;
			}
		}

		private void endCall() {
			try {
				iTel.endCall();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private SmsReceiver receiver;
	private TelephonyManager tm;
	private MyPhoneStateListener listener;
	private BlackListBiz biz;
	private ITelephony iTel;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		biz = new BlackListBiz(this);

		// 动态注册广播接收器
		receiver = new SmsReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(1000);
		registerReceiver(receiver, filter);

		// 注册电话状态监听
		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		listener = new MyPhoneStateListener();
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

		// 通过反射 获得ITelephony对象
		try {
			Method method = Class.forName("android.os.ServiceManager")
					.getDeclaredMethod("getService", String.class);
			IBinder binder = (IBinder) method.invoke(null, TELEPHONY_SERVICE);
			iTel = ITelephony.Stub.asInterface(binder);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
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

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
		tm.listen(listener, PhoneStateListener.LISTEN_NONE);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
