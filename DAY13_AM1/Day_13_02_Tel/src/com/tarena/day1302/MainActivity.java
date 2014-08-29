package com.tarena.day1302;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.TelephonyManager;
import android.view.Menu;

public class MainActivity extends Activity {
	private TelephonyManager tm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		tm.getCallState();//获取通话状态
		tm.getCellLocation();//获取模糊位置 android.permission.ACCESS_COARSE_LOCATION
		tm.getDataState();//获取数据网络连接状态
		tm.getDeviceId();//社区手机的设备码
		tm.getDeviceSoftwareVersion();//获取设备的软件版本号
		tm.getLine1Number();//获取本机号码
		tm.getNetworkCountryIso();//手机网络的国家码
		tm.getNetworkOperator();//手机网络的移动运营商
		tm.getNetworkOperatorName();//运营商名
		tm.getPhoneType();//手机网络类型
		tm.getSimCountryIso();//sim卡运营商的国家码
		tm.getSimOperator();//sim卡运营商信息
		tm.getSimOperatorName();//sim卡 运营商名
		tm.getSimSerialNumber();//sim卡的序列码
		tm.getSubscriberId();//sim卡的唯一标识码
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
