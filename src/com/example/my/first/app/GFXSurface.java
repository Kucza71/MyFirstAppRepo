package com.example.my.first.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{

	MyViewSurface ourSurfaceView;
	float x, y;
	boolean buttonVisible = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView  = new MyViewSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		x=0;
		y=0;
		
		setContentView(ourSurfaceView);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = event.getX();
		y = event.getY();
		
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			buttonVisible = true;
			break;
			
		case MotionEvent.ACTION_UP:
			buttonVisible = false;
		
		}
		
		return true;
	}
	
	
	public class MyViewSurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = true;

		public MyViewSurface(Context context) {
			super(context);
			ourHolder = getHolder();

			ourThread = new Thread(this);
			ourThread.start();

		}

		public void pause() {
			isRunning = false;
			while (true) {
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			}
			ourThread = null;
		}

		public void resume() {
			isRunning = true;

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRunning) {
				if (!ourHolder.getSurface().isValid())
					continue;

				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(02, 02, 150);
				if(x != 0 && y!=0 && buttonVisible) 
				{
					Bitmap bit = BitmapFactory.decodeResource(getResources(), R.drawable.button1);
					canvas.drawBitmap(bit, x-bit.getWidth()/2,y-bit.getHeight()/2,null);
				}
				ourHolder.unlockCanvasAndPost(canvas);

			}
		}

	}
}
