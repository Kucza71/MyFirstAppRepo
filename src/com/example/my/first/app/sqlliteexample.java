package com.example.my.first.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class sqlliteexample extends Activity implements View.OnClickListener {

	TextView tvName = null;
	TextView tvHonesty = null;

	Button btSave = null;
	Button btOpen = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlliteexample);

		init();

	}

	public void init() {
		tvName = (TextView) findViewById(R.id.etName);
		tvHonesty = (TextView) findViewById(R.id.etHonesty);

		btSave = (Button) findViewById(R.id.bSave);
		btOpen = (Button) findViewById(R.id.bOpenDBView);
		btSave.setOnClickListener(this);
		btOpen.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.bSave:

			String name = tvName.getText().toString();
			String hotness = tvHonesty.getText().toString();

			try {
				HotOrNot entry = new HotOrNot(this);
				entry.open();
				entry.createEntry(name, hotness);
				entry.close();
				Toast t = Toast
						.makeText(sqlliteexample.this,
								"Create Entry completed succesfully",
								Toast.LENGTH_LONG);
				t.show();
				Dialog d = new Dialog(this);
				d.setTitle("Udalo sie zaladowac sql do bazy");
				d.show();

			} catch (Exception e) {
				e.printStackTrace();
				Toast t = Toast.makeText(sqlliteexample.this,
						"Create Entry FAILED!!!", Toast.LENGTH_LONG);
				t.show();
			}
			break;
		case R.id.bOpenDBView:
			Intent i = new Intent("com.example.my.first.app.SQLVIEW");
			startActivity(i);

			break;
		default:
			break;
		}

	}

}
