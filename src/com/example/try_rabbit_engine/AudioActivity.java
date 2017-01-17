package com.example.try_rabbit_engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class AudioActivity extends Activity{
	AudioView audioView;
	boolean audio_on;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		audioView = new AudioView(this);
		setContentView(audioView);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("screen touched");
		switch (event.getAction()) {
				
				
				case MotionEvent.ACTION_DOWN:
					// 33 197 336 197
					float x = event.getX();
					float y = event.getY();
					System.out.println("x->" + x);
					System.out.println("y->" + y);
					if (x > 30 && x < 600 && y > 177 && y < 2070) {
						System.out.println("yes");
						audioView.setClick(true);
						audioView.setAudio_on(true);
						audioView.invalidate();
						this.audio_on = true;
					}
					if (x > 330 && x < 360 && y > 177 && y < 207) {
						System.out.println("no");
						audioView.setClick(true);
						audioView.setAudio_on(false);
						audioView.invalidate();
						this.audio_on = false;
					}
					break;
				case MotionEvent.ACTION_UP:
					if (audioView.isClick()) {
						startActivity(new Intent(this, MyGameActivity.class).putExtra("audio", audio_on));
					}
					break;
				}
		return true;
	}
	
	class AudioView extends View{
		Paint paint;
		boolean audio_on = false;
		boolean click = false;
		
		public AudioView(Context context) {
			super(context);
			paint = new Paint();
		}

		public void onDraw(Canvas canvas){
			super.onDraw(canvas);
			canvas.drawColor(Color.BLACK);
			paint.setTextSize(50);
			paint.setColor(Color.WHITE);
			canvas.drawText("是否開啟音樂?", 400, 200, paint);
			if(click){
				if(audio_on){
					paint.setColor(Color.RED);
					canvas.drawText("開啟", 300, 500, paint);
					paint.setColor(Color.WHITE);
					canvas.drawText("關閉", 600, 500, paint);
				}else{
					paint.setColor(Color.WHITE);
					canvas.drawText("開啟", 300, 500, paint);
					paint.setColor(Color.RED);
					canvas.drawText("關閉", 600, 500, paint);
				}
			}else {
					paint.setColor(Color.WHITE);
					canvas.drawText("開啟", 300, 500, paint);
					canvas.drawText("關閉", 600, 500, paint);
			}
		}

		public boolean isAudio_on() {
			return audio_on;
		}

		public void setAudio_on(boolean audioOn) {
			audio_on = audioOn;
		}

		public boolean isClick() {
			return click;
		}

		public void setClick(boolean click) {
			this.click = click;
		}
	}
}
