package com.example.try_rabbit_engine;

import android.graphics.Bitmap;

import com.example.try_rabbit_engine.MyRabbit.Rabbit_action;
import com.example.try_wolfman.framework.BitmapUtil;
import com.example.try_wolfman.framework.Sprite;

public class MyBell extends Sprite{

	enum Rabbit_action {

		BellExplode(
				"BELL_EXPLODE",
				new Bitmap[] {
						BitmapUtil
								.getBitmapFromRes(R.drawable.bell_explode1),
						BitmapUtil
								.getBitmapFromRes(R.drawable.bell_explode2),
						 }
		);

		String name;
		Bitmap[] bitmaps;

		private Rabbit_action(String name, Bitmap[] bitmaps) {
			this.name = name;
			this.bitmaps = bitmaps;
		}

		public String getName() {
			return name;
		}

		public Bitmap[] getBitmaps() {
			return bitmaps;
		}
	}
	
	public MyBell(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		// TODO Auto-generated constructor stub
		addAction(Rabbit_action.BellExplode.getName(),
				Rabbit_action.BellExplode.getBitmaps(), new int[] { 500, 500, 500 }, false);
	}

	public void bellExplode(){
		setAction(Rabbit_action.BellExplode.getName());
	}
}
