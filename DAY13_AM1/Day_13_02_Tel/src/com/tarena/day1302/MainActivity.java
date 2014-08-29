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
		tm.getCallState();//��ȡͨ��״̬
		tm.getCellLocation();//��ȡģ��λ�� android.permission.ACCESS_COARSE_LOCATION
		tm.getDataState();//��ȡ������������״̬
		tm.getDeviceId();//�����ֻ����豸��
		tm.getDeviceSoftwareVersion();//��ȡ�豸������汾��
		tm.getLine1Number();//��ȡ��������
		tm.getNetworkCountryIso();//�ֻ�����Ĺ�����
		tm.getNetworkOperator();//�ֻ�������ƶ���Ӫ��
		tm.getNetworkOperatorName();//��Ӫ����
		tm.getPhoneType();//�ֻ���������
		tm.getSimCountryIso();//sim����Ӫ�̵Ĺ�����
		tm.getSimOperator();//sim����Ӫ����Ϣ
		tm.getSimOperatorName();//sim�� ��Ӫ����
		tm.getSimSerialNumber();//sim����������
		tm.getSubscriberId();//sim����Ψһ��ʶ��
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
