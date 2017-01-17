package com.example.try_rabbit_engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.renderscript.Script;

import com.example.try_wolfman.framework.BitmapUtil;
import com.example.try_wolfman.framework.CommonUtil;
import com.example.try_wolfman.framework.LayerManager;
import com.example.try_wolfman.framework.Sprite;
import com.example.try_wolfman.framework.Utils;

public class MyPlane extends Sprite{
    public int timeSpan = 1000;     
    public long updateTime = 0;
    
	public MyPlane(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.drawSelf(canvas, paint);
		
	}
	
	public boolean isNeedCreateBullet(){
		boolean isNeedCreateBullet = false;
		if (System.currentTimeMillis() > updateTime) {
			isNeedCreateBullet = true;
			updateTime = System.currentTimeMillis() + timeSpan;
		}
		return isNeedCreateBullet;
	}
	
	public Sprite createBullet(){
//		MyBullet b = null;
		                         
		MyBullet	b = new MyBullet(BitmapUtil.getBitmapFromRes(R.drawable.bell_ok), 50, 50, true);                         
//			b.setPosition(x + w / 2, y);  
		b.setPosition(getX() - 50/2, getY());
			b.setAction("bb");                     
			LayerManager.insert(b, this);           
//			GameData.bullets.add(b);                      
//			updateTime = System.currentTimeMillis() + timeSpan; 	
		
		return b;
	}

}
