package com.example.try_rabbit_engine;

import com.example.try_wolfman.framework.BitmapUtil;

import android.graphics.Bitmap;

public interface IA {
//	String name = null;
//	Bitmap[] bitmaps = null;
	
		public String getName();

		public Bitmap[] getBitmaps();
}

enum AAA implements IA{
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

	private AAA(String name, Bitmap[] bitmaps) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.bitmaps = bitmaps;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bitmap[] getBitmaps() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
