package com.example.my.first.app;

import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;

public class Game extends Activity {

	Bitmap moon;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        InputStream is = getResources().openRawResource(R.drawable.sky);
        moon = BitmapFactory.decodeStream(is);
        // int i = 0;
        // dupadupa
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_game, menu);
        return true;
    }
}
