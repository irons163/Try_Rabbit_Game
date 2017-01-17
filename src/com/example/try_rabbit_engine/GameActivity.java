package com.example.try_rabbit_engine;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {
	private GameSurfaceView gameSurfaceView;
	private boolean audio_on = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		Constant.SCREEN_HEIGHT = dm.heightPixels;
		Constant.SCREEN_WIDTH = dm.widthPixels;
		
		audio_on = this.getIntent().getBooleanExtra("audio", true);
		gameSurfaceView = new GameSurfaceView(this);
		initWindow();
		this.setContentView(gameSurfaceView);
		gameSurfaceView.requestFocus();
	}

	public void initWindow(){
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		System.out.println("game activity key up");
		gameSurfaceView.onKeyUp(keyCode, event);
		if(keyCode == KeyEvent.KEYCODE_BACK){
			this.finish();
		}
		return true;
	}

	public boolean isAudio_on() {
		return audio_on;
	}

	public void setAudio_on(boolean audioOn) {
		audio_on = audioOn;
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
//		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			// 33 197 336 197
//			float x = event.getX();
//			float y = event.getY();
//			System.out.println("x->" + x);
//			System.out.println("y->" + y);
//			if (x > 30 && x < 60 && y > 177 && y < 207) {
//				System.out.println("yes");
//				audioView.setClick(true);
//				audioView.setAudio_on(true);
//				audioView.invalidate();
//				this.audio_on = true;
//			}
//			if (x > 330 && x < 360 && y > 177 && y < 207) {
//				System.out.println("no");
//				audioView.setClick(true);
//				audioView.setAudio_on(false);
//				audioView.invalidate();
//				this.audio_on = false;
//			}
//			break;
//		case MotionEvent.ACTION_UP:
//			if (audioView.isClick()) {
//				this.toGameActivity();
//			}
//			break;
//		}
//		return true;
//	}
	
	
}
