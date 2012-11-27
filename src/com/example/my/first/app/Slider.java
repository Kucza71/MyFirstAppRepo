package com.example.my.first.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Slider extends Activity implements OnClickListener,
		OnCheckedChangeListener, OnDrawerOpenListener {

	Button bHandle1;
	Button bHandle2;
	Button bHandle3;
	
	CheckBox cbSlidable;
	
	SlidingDrawer sldDrawer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		
		bHandle1 = (Button)findViewById(R.id.handle1);
		bHandle2 = (Button)findViewById(R.id.handle2);
		bHandle3 = (Button)findViewById(R.id.handle3);
		
		bHandle1.setOnClickListener(this);
		bHandle2.setOnClickListener(this);
		bHandle3.setOnClickListener(this);
		
		cbSlidable = (CheckBox)findViewById(R.id.cbSlidable);
		cbSlidable.setOnCheckedChangeListener(this);
		
		sldDrawer = (SlidingDrawer)findViewById(R.id.slidingD);
		sldDrawer.setOnDrawerOpenListener(this);
		
		

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(buttonView.isChecked()) {
			sldDrawer.lock();
			
		}else {
			sldDrawer.unlock();
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
		case R.id.handle1:
			sldDrawer.open();
			break;
		case R.id.handle2:
			sldDrawer.close();
			break;
		case R.id.handle3:
			sldDrawer.toggle();
			break;
		}
	}

	@Override
	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		
	}

	
	
}
