package com.example.my.first.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.util.Log;
import android.view.View;

public class MyView extends View{
	
	Bitmap bitMoon;
	Bitmap bitSky;
	
	Typeface font;
	Paint textPaint;
	
	
	public MyView(Context context) {
		super(context);

		bitMoon = BitmapFactory.decodeResource(getResources(), R.drawable.moon_final);
		bitSky = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
		
		font = Typeface.createFromAsset(context.getAssets(), "fonts/samplefont.ttf");
		//"fonts/Roboto-Bold.ttf"
		textPaint = new Paint();
		
		textPaint.setARGB(50, 254, 10, 50);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(50);
		textPaint.setTypeface(font);
	
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
				
		Rect moon = setMoonRect1700(canvas);
		Rect sky = setSkyRect2600(canvas, moon);
		//canvas.
		canvas.drawBitmap(bitSky, null, sky, null);
		canvas.drawBitmap(bitMoon, null, moon, null);
		
		canvas.drawText("MyView", canvas.getWidth()/3, canvas.getHeight()/3, textPaint);
		
		invalidate();
	}
	
	Rect setMoonRect1700(Canvas canvas){
		int xMoon, yMoon, widthMoon;
		widthMoon = (int)(2.13 * canvas.getWidth());
		xMoon = (canvas.getWidth()/2)-(widthMoon/2);
		yMoon = ( canvas.getHeight()-(int)(0.0588*(double)widthMoon) );
		Rect moon = new Rect(xMoon,yMoon,xMoon+widthMoon, yMoon+widthMoon);
//		Log.w("Wiadomoœæ: widthMoon="+widthMoon+"  ", "x="+xMoon+",  y="+yMoon+"//ncanvas.getWidth() = "+canvas.getWidth() + "//ncanvas.getHeight() = "+canvas.getHeight()+
//		"/n ");
		return moon;	
	}
	Rect setSkyRect2600(Canvas canvas, Rect moon){
		int xSky, ySky, widthSky;
		widthSky = (int)(5.42 * canvas.getWidth());
		xSky = (moon.left+moon.width()/2);
		ySky = (moon.top+moon.height()/2);
		Rect sky = new Rect(xSky-widthSky/2,ySky-widthSky/2,xSky+widthSky/2, ySky+widthSky/2);
//		Log.w("Wiadomoœæ: widthMoon="+widthMoon+"  ", "x="+xMoon+",  y="+yMoon+"//ncanvas.getWidth() = "+canvas.getWidth() + "//ncanvas.getHeight() = "+canvas.getHeight()+
//		"/n ");
		return sky;	
	}
	

}
