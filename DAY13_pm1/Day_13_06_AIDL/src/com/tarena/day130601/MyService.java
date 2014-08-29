package com.tarena.day130601;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.tarena.aidl.IPlayControllor;

public class MyService extends Service {
	private class MyBinder extends IPlayControllor.Stub {

		@Override
		public void play() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i("info", "MyService.play()");
		}

		@Override
		public void pause() throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public void next() throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public void previous() throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public void seekTo(int time) throws RemoteException {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new MyBinder();
	}
}
