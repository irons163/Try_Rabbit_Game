package com.example.try_rabbit_engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.try_wolfman.framework.BitmapUtil;
import com.example.try_wolfman.framework.LayerManager;
import com.example.try_wolfman.framework.Sprite;

public class MyEnemy extends Sprite{
    public int timeSpan = 1000;     
    public long updateTime = 0;
    
	public MyEnemy(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.drawSelf(canvas, paint);
	}
	
	public boolean isNeedCreateEnemy(){
		boolean isNeedCreateEnemy = false;
		if (System.currentTimeMillis() > updateTime) {
			isNeedCreateEnemy = true;
			updateTime = System.currentTimeMillis() + timeSpan;
		}
		return isNeedCreateEnemy;
	}
	
	public Sprite createEnemy(){
//		MyBullet b = null;
		                         
		MyEnemy	b = new MyEnemy(BitmapUtil.getBitmapFromRes(R.drawable.bird_left_fly0), 50, 50, true);                         
//			b.setPosition(x + w / 2, y);                    
			b.setAction("bb");                     
			LayerManager.insert(b, this);           
//			GameData.bullets.add(b);                      
//			updateTime = System.currentTimeMillis() + timeSpan; 	
		
		return b;
	}

}
