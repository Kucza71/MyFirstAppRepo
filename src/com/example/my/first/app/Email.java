package com.example.my.first.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Email extends Activity implements View.OnClickListener {

	EditText personsEmail, intro, personsName, stupidThings, hatefulAction,
			outro;
	TextView textViewClock;
	String emailAdd, beginning, name, stupidAction, hatefulAct, out;
	Button sendEmail;
	AnalogClock cl;
	
	private Handler handler;
	MyUpdater mu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		initializeVars();
		sendEmail.setOnClickListener(this);
		handler  = new Handler();
        
		mu = new MyUpdater();
	}

	private void initializeVars() {
		// TODO Auto-generated method stub
		personsEmail = (EditText) findViewById(R.id.etEmails);
		intro = (EditText) findViewById(R.id.etIntro);
		personsName = (EditText) findViewById(R.id.etName);
		stupidThings = (EditText) findViewById(R.id.etThings);
		hatefulAction = (EditText) findViewById(R.id.etAction);
		outro = (EditText) findViewById(R.id.etOutro);
		sendEmail = (Button) findViewById(R.id.bSentEmail);
		
		cl = (AnalogClock)findViewById(R.id.myanalogClock);
		cl.setSoundEffectsEnabled(true);
		textViewClock = (TextView)findViewById(R.id.textViewClock);
		
		textViewClock.setText("1");

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

		convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated();
		String emailaddress[] = { emailAdd };
		String message = "Well hello hello hello!!!!!!!!!!!!!!!"
				+ name
				+ " I just wanted to say "
				+ beginning
				+ ".  Not only that but I hate when you "
				+ stupidAction
				+ ", that just really makes me crazy.  I just want to make you "
				+ hatefulAct
				+ ".  Welp, thats all I wanted to chit-chatter about, oh and"
				+ out
				+ ".  Oh also if you get bored you should check out www.mybringback.com"
				+ '\n' + "PS. I think I love you...    ";
		
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Fuck!");
		intent.setType("plain/text");	
		intent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		
		startActivity(intent);
		
	}

	private void convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated() {
		// TODO Auto-generated method stub
		emailAdd = personsEmail.getText().toString();
		beginning = intro.getText().toString();
		name = personsName.getText().toString();
		stupidAction = stupidThings.getText().toString();
		hatefulAct = hatefulAction.getText().toString();
		out = outro.getText().toString();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//mu.pause();
		finish();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//mu.resume();
	}
	
	

	public class MyUpdater implements Runnable {

		public MyUpdater() {
			new Thread(this).start();
		}
		
		@Override
        public void run() {
        	while (true) {
           // final int value = i;
            try {
              Thread.sleep(2000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            handler.post(new Runnable() {
              @Override
              public void run() {
            	  Integer liczba = 0;
  				try {
  					System.out.println("bede parsowa³: " + textViewClock.getText().toString());
  					liczba = Integer.parseInt(textViewClock.getText().toString());
  					liczba++;
  				}catch(Exception e) {
  					e.printStackTrace();
  				}
  				textViewClock.setText( liczba+"" );
                //progress.setProgress(value);
              }
            });
          }
        }


	}
	
}