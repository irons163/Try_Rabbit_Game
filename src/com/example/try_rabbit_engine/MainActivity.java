package com.example.try_rabbit_engine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private MyGameModel gameModel;
	private MyGameController gameController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		initWindow();
//		bg = BitmapFactory.decodeResource(this.getResources(), R.drawable.introduce);
		ImageView view = new ImageView(this);
		view.setImageResource(R.drawable.introduce);
		setContentView(view);
	}
	
	private void initWindow() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("screen touched");
			if (event.getAction() == MotionEvent.ACTION_UP) {
				startActivity(new Intent(this, HelpActivity.class));
			} 
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
