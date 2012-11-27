package com.example.my.first.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	SharedPreferences prefs;
	FileOutputStream fos;
	String filename = "InternalString";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);
		setUpVariables();
		// or
		// Intent intent = new Intent();
		// intent.setClassName(getApplicationContext(),
		// SecundoActivity.class.getName());
		// startActivity(intent);
		// setContentView(R.layout.activity_secundo);

	}

	public void setUpVariables() {

		final EditText nameField = (EditText) findViewById(R.id.nameField);
		final TextView textViewWithName = (TextView) findViewById(R.id.nameView);
		Button myButton = (Button) findViewById(R.id.button);

		prefs = getSharedPreferences("myPreferences", 0);

		// Fullscreen programistycznie
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		myButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				textViewWithName.setText("Hello, " + nameField.getText());

				// SharedPreferences input
				Editor ed = prefs.edit();
				ed.putString("sharedString", nameField.getText().toString());
				ed.commit();

				// Save via file
				// FileOutputStream input - ZAMU£KA BUCKYEGO
				// File f = new File(filename);
				// try {
				// fos = new FileOutputStream(f);
				// fos.close();
				// } catch (FileNotFoundException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }

				// FileOutputStream input
				try {
					fos = openFileOutput(filename, Context.MODE_PRIVATE);
					fos.write("dupa dupa ".getBytes());
					fos.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {

					Class secundo = Class
							.forName("com.example.my.first.app.SecundoActivity");
					Intent intent = new Intent(MainActivity.this, secundo);
					Bundle basket = new Bundle();
					basket.putString("adres", "Mochnackiego21");
					intent.putExtras(basket);
					startActivity(intent);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
