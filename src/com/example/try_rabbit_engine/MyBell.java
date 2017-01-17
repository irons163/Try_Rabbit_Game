package com.example.try_rabbit_engine;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.example.try_rabbit_engine.MyRabbit.Rabbit_action;
import com.example.try_wolfman.framework.BitmapUtil;
import com.example.try_wolfman.framework.CommonUtil;
import com.example.try_wolfman.framework.IActionListener;
import com.example.try_wolfman.framework.Sprite;

public class MyBell extends Sprite{
	public boolean isExploded = false;
	
	enum Rabbit_action {

		BellExplode(
				"BELL_EXPLODE",
				new Bitmap[] {
						
						BitmapUtil
								.getBitmapFromRes(R.drawable.bell_explode1),
						BitmapUtil
								.getBitmapFromRes(R.drawable.bell_explode2),
						BitmapUtil
								.getBitmapFromRes(R.drawable.bell_explode2),
						 }
		);

		String name;
		Bitmap[] bitmaps;
		float scale;
		
		private Rabbit_action(String name, Bitmap[] bitmaps) {
			this.name = name;
			this.bitmaps = bitmaps;
			this.scale = 1.0f;
		}
		
		private Rabbit_action(String name, Bitmap[] bitmaps, float scale) {
			this.name = name;
			Matrix matrix = new Matrix();
		    matrix.postScale(scale, scale);
			for(Bitmap bitmap : bitmaps){
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			}
			this.bitmaps = bitmaps;
			this.scale = scale;
		}

		public String getName() {
			return name;
		}

		public Bitmap[] getBitmaps() {
			return bitmaps;
		}
		
		public float getScale(){
			return scale;
		}
	}
	
	public MyBell(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		// TODO Auto-generated constructor stub
		addAction(Rabbit_action.BellExplode.getName(),
				Rabbit_action.BellExplode.getBitmaps(), new int[] { 0, 500, 500 }, false, new IActionListener() {
					
					@Override
					public void beforeChangeFrame(int nextFrameId) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterChangeFrame(int periousFrameId) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void actionFinish() {
						// TODO Auto-generated method stub
						isExploded = true;
					}
				});
	}

	public void bellExplode(){
		canCollision = false;
		setAction(Rabbit_action.BellExplode.getName());
	}

	@Override
	public boolean isNeedCreateNewInstance() {
		// TODO Auto-generated method stub
		boolean isNeedCreateNewInstance = false;
		if(getY() >= 0){
			isNeedCreateNewInstance = true;
		}
		return isNeedCreateNewInstance; 
	}

	@Override
	public boolean isNeedRemoveInstance() {
		// TODO Auto-generated method stub
		return getY() > CommonUtil.screenHeight;
	}

}
