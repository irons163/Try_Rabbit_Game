package com.example.try_rabbit_engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.try_gameengine.framework.Sprite;


public class MyButton extends Sprite{
	private boolean isVisiable = true;
	
	public MyButton(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		if(isVisiable)
		super.drawSelf(canvas, paint);
	}



	public void setIsVisiable(boolean isVisiable){
		this.isVisiable =isVisiable;
	}
	
	public boolean isVisiable(){
		return isVisiable;
	}
}
