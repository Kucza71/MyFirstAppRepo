package com.example.my.first.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SecundoActivity extends Activity implements OnClickListener {

	Button myBackButton;
	Intent i;
	Bitmap bmp;
	ImageView iv;

	SharedPreferences prefs;
	String FILENAME = "InternalString";
	Button setWall;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		init();
	}

	public void init() {
		// make it full screen
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_secundo);

		iv = (ImageView) findViewById(R.id.returnedPhoto);

		myBackButton = (Button) findViewById(R.id.buttonBack);
		myBackButton.setOnClickListener(this);

		setWall = (Button) findViewById(R.id.setWall);
		// setWall.setVisibility(0);//bmp == null ? true : false
		setWall.setOnClickListener(this);

		try {
			myBackButton.setText(getIntent().getExtras().getString("adres"));
		} catch (Exception e) {

			Toast.makeText(SecundoActivity.this, R.string.noadres,
					Toast.LENGTH_LONG).show();
		}

		prefs = getSharedPreferences("myPreferences", 0);
		myBackButton.setText(myBackButton.getText()
				+ "---- pobrany z preferencji = "
				+ prefs.getString("sharedString", "defaultowa dupa"));

		loadStuffFromFile loader = new loadStuffFromFile();
		loader.execute(new String("filename"));
		//loader.
		
		//new loadStuffFromFile.execute(FILENAME);

	}

	@Override
	public void onBackPressed() {
		finish();
		// return;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			iv.setImageBitmap(bmp);

		}
	}

	// @Override
	// public boolean onCreateOptionsMenu(android.view.Menu menu) {
	// super.onCreateOptionsMenu(menu);
	//
	// getMenuInflater().inflate(R.menu.activity_secundo, menu);
	// return true;
	// }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// powrót do poprzedniej activity

		switch (v.getId()) {
		case R.id.buttonBack:
			// zmiana widoku za pomoc¹ nazwy klasy
			// Intent intent = new Intent(Intent.ACTION_VIEW);
			// intent.setClassName(getApplicationContext(),
			// MainActivity.class.getName());
			// startActivity(intent);

			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, 0);
			break;

		case R.id.setWall:
			try {
				getApplicationContext().setWallpaper(bmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

		// Intent intent = new Intent(Intent.ACTION_VIEW);
		// intent.setClassName(getApplicationContext(),
		// MainActivity.class.getName());
		// startActivity(intent);

		// setContentView(R.layout.activity_main);

		// try {
		// Class secundo =
		// Class.forName("com.example.my.first.app.MainActivity");
		// Intent intent = new Intent(SecundoActivity.this, secundo);
		// startActivity(intent);
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public class loadStuffFromFile extends AsyncTask<String, Integer, String> {

		String collected = null;
		String f = "nie zdefiniowany";
		ProgressDialog dialog;
		Button mb = (Button)findViewById(R.id.buttonBack);
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			//super.onPreExecute();
			f = "Whatever";
			dialog = new ProgressDialog(SecundoActivity.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
						
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

		@Override
		protected void onCancelled(String result) {
			// TODO Auto-generated method stub
			super.onCancelled(result);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			//super.onPostExecute(result);
			
			mb.setText(myBackButton.getText()	+ "--//n-- pobrany z pliku InternalString = "+ collected  );
		}



		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			//super.onProgressUpdate(values);
			dialog.incrementProgressBy(values[0]);
			mb.setText(myBackButton.getText() + "|" + dialog.getProgress()  );
		}
		

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			// FileOutputStream input
			FileInputStream fis = null;
			String FILENAME = "InternalString";
			

			for(int i = 0; i < 20; i++) {
				publishProgress(5);
				try {
					Thread.sleep(88);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
			}
			
			dialog.dismiss();
			
			try {
				fis = openFileInput(FILENAME);
				byte[] dataArray = new byte[fis.available()];
				while (fis.read(dataArray) >= 0) {
					collected = new String(dataArray);
				}
				// fos.write("dupa dupa ".getBytes());

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					return collected;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return null;
		}

	}
}
