package com.example.my.first.app;

import java.util.Timer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends Activity {

	MediaPlayer ambient;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_splash);
      
        ambient = MediaPlayer.create(Splash.this, R.raw.ambient	);
        
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean isCheckboxFromPrefsSelected = prefs.getBoolean("checkbox", true);
        
        //preferences
        if(isCheckboxFromPrefsSelected)        
        ambient.start();
        
        
        ambient.setLooping(true);
        
        Thread timer = new Thread() {
        	public void run(){
        		try {
					sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					Intent intent = new Intent("com.example.my.first.app.MENU");
					startActivity(intent);
				}
        	}
        };
        

        timer.start();


		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_splash, menu);
        return true;
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
    
    
}
