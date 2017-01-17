package com.example.try_rabbit_engine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class RabitActivity extends Activity {
	private IntroduceView introduceView;
	private View currentView;
	private boolean audio_on = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        Constant.SCREEN_WIDTH = dm.widthPixels;
        Constant.SCREEN_HEIGHT= dm.heightPixels;
        
		introduceView = new IntroduceView(this);
//		helpView = new HelpView(this);
//		audioView = new AudioView(this);
		initWindow();
		setContentView(introduceView);
		this.currentView = introduceView;
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		this.finish();
	}

	private void initWindow() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("screen touched");
		if (currentView == introduceView) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				startActivity(new Intent(this, HelpActivity.class));
			}
		} 
		return true;
	}

}