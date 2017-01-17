package com.example.try_rabbit_engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.try_gameengine.framework.Sprite;

public class MyBullet extends Sprite{
	public int speed = 10;
//	private MyData myBulletData;
	
	public MyBullet(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		// TODO Auto-generated constructor stub
//		myBulletData = new MyData();
	}

	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.drawSelf(canvas, paint);
		
//		x += speed;
//		if(x >= 800){
//			LayerManager.deleteLayer(this);
//			myBulletData.getAllExistPointsIterator().ge
//		}
	}

	@Override
	public void move(float dx, float dy) {
		// TODO Auto-generated method stub
		super.move(dx, dy);
	}
	
	

}
