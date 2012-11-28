package com.example.my.first.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity{
	
	MyView ourView;
	WakeLock wL;

	// zmiany robione lokalnie - do wrzucenia na webowe repo
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		PowerManager pM = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "tag for WakeLock");
		
		super.onCreate(savedInstanceState);
		wL.acquire();
		
		ourView = new MyView(this);
		setContentView(ourView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wL.release();
	}
	
	
	

}
