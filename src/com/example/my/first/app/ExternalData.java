package com.example.my.first.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener, View.OnClickListener {

	Spinner myspinner = null;
	TextView tvPath = null;

	String[] dataForSpinner = { "music", "data", "pictures" };
	int position = -1;
	File path = null;
	File filetosavepath = null;
	
	Button bSave = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.external_data);
		init();
	}

	public void init() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
		myspinner = (Spinner) findViewById(R.id.myspinner);
		}
		tvPath = (TextView) findViewById(R.id.tvpath);
		myspinner.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ExternalData.this, android.R.layout.simple_spinner_item, dataForSpinner);

		myspinner.setAdapter(adapter);
		
		bSave = (Button)findViewById(R.id.bSave);
		bSave.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.external_data, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
		// myspinner.getSelectedItemPosition();
//		if (Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED)) {
			position = arg0.getSelectedItemPosition();
			switch (position) {
			case 0:
				path = Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
				tvPath = (TextView) findViewById(R.id.tvpath);
				tvPath.setText(Environment.getExternalStoragePublicDirectory(
						Environment.DIRECTORY_MUSIC).toString());
				break;

			case 1:
				path = Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
				tvPath = (TextView) findViewById(R.id.tvpath);
				tvPath.setText(Environment.getExternalStoragePublicDirectory(
						Environment.DIRECTORY_DOWNLOADS).toString());
				break;

			case 2:
				path = Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
				tvPath = (TextView) findViewById(R.id.tvpath);
				tvPath.setText(Environment.getExternalStoragePublicDirectory(
						Environment.DIRECTORY_PICTURES).toString());
				break;
			}
		//}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		path.mkdirs();
		filetosavepath = new File(path, "aaamyfileWithButton.bmp");

		
		try {
			InputStream is = getResources().openRawResource(R.drawable.button2);
			OutputStream os = new FileOutputStream(filetosavepath);
			
			byte[] data = new byte[is.available()];
			is.read(data);
			os.write(data);
			is.close();
			os.close();

			Toast t = Toast.makeText(ExternalData.this, "File has been sent", Toast.LENGTH_LONG);
			t.show();
			
			MediaScannerConnection.scanFile(ExternalData.this, new String[] {filetosavepath.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
				
				@Override
				public void onScanCompleted(String path, Uri uri) {
					// TODO Auto-generated method stub
					Toast t = Toast.makeText(ExternalData.this, "Scan Complete", Toast.LENGTH_LONG);
					t.show();
				}
			});
			
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


}
