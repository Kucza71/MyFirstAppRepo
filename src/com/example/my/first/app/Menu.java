package com.example.my.first.app;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.ListActivity;
import android.content.Intent;

public class Menu extends ListActivity {

	String options[] = { "MainActivity", "SecundoActivity", "Splash", "Email",
			"Game", "GFX", "GFXSurface", "SoundStuff", "Slider", "ExternalData",
			"sqlliteexample", "SQLView", "Accelerate",
			"StartBouncing", "SurfaceViewActivity"};
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_expandable_list_item_1, options));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		String chosenClass = options[position];
		try {
			Class secundo = Class.forName("com.example.my.first.app."
					+ chosenClass);
	
			Intent intent = new Intent(Menu.this, secundo);
			startActivity(intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {

		case R.id.about:
			Toast.makeText(Menu.this, item.getItemId()+"id--", Toast.LENGTH_SHORT).show();
			break;
		case R.id.prefrences:
			Intent i = new Intent("com.example.my.first.app.PREFRENCES");
			startActivity(i);
			
			Toast.makeText(Menu.this, item.getItemId()+"id--", Toast.LENGTH_SHORT).show(); 	
			break;

		}

		return true;
	}
}
