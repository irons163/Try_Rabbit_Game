package com.example.try_rabbit_engine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class HelpActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bitmap bg = BitmapFactory.decodeResource(getResources(), R.drawable.help);
		ImageView view = new ImageView(this);
		view.setImageBitmap(bg);
		setContentView(view);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("screen touched");
			if (event.getAction() == MotionEvent.ACTION_UP) {
				startActivity(new Intent(this, AudioActivity.class));
			}
		return true;
	}
	
}
