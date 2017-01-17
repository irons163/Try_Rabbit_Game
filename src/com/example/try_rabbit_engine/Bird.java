package com.example.try_rabbit_engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Bird {
	private Context context;
	private float speed = Constant.BIRD_SPEED;
	private float speed_power = Constant.BIRD_SPEED_POWER;
	private float center_x;
	private float center_y;
	private boolean on_screen = false;
	private int state;
	private Bitmap[] bitmaps  = new Bitmap[8];
	private Paint paint = new Paint();
	
	public static final float BIRD_WIDTH = Constant.BIRD_WIDTH;
	public static final float BIRD_HIGHT = Constant.BIRD_HIGHT;
	
	public static final float BIRD_SPEED = Constant.BIRD_SPEED;
	public static final float BIRD_SPEED_POWER = Constant.BIRD_SPEED_POWER;
	
	public static final int BIRD_LEFT_FLY0 = 0;
	public static final int BIRD_LEFT_FLY1 = 1;
	public static final int BIRD_LEFT_FLY2 = 2;
	public static final int BIRD_RIGHT_FLY0 = 3;
	public static final int BIRD_RIGHT_FLY1 = 4;
	public static final int BIRD_RIGHT_FLY2 = 5;
	public static final int BIRD_LEFT_FLY_POWER = 6;
	public static final int BIRD_RIGHT_FLY_POWER = 7;

	public Bird(Context context){
		this.context = context;
		state = BIRD_LEFT_FLY0;
		init_bitmaps();
	}
	
	public void init_bitmaps(){
		bitmaps[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_left_fly0);
		bitmaps[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_left_fly1);
		bitmaps[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_left_fly2);
		bitmaps[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_right_fly0);
		bitmaps[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_right_fly1);
		bitmaps[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird_right_fly2);
		bitmaps[6] = bitmaps[1];
		bitmaps[7] = bitmaps[4];
	}
	
	public void onDraw(Canvas canvas){
		canvas.drawBitmap(bitmaps[state], center_x-BIRD_WIDTH/2, center_y-BIRD_HIGHT/2, paint);
		
	}
	
	public boolean isHited(){
		return (state == BIRD_LEFT_FLY_POWER || state == BIRD_RIGHT_FLY_POWER);
	}

	public boolean isFaceLeft(){
		return (state == BIRD_LEFT_FLY0 || state == BIRD_LEFT_FLY1 || state == BIRD_LEFT_FLY2 || state == BIRD_LEFT_FLY_POWER);
	}
	
	public boolean isFaceRight(){
		return !isFaceLeft();
	}
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public float getSpeed_power() {
		return speed_power;
	}

	public void setSpeed_power(int speedPower) {
		speed_power = speedPower;
	}

	public float getCenter_x() {
		return center_x;
	}

	public void setCenter_x(float centerX) {
		center_x = centerX;
	}

	public float getCenter_y() {
		return center_y;
	}

	public void setCenter_y(float centerY) {
		center_y = centerY;
	}

	public boolean isOn_screen() {
		return on_screen;
	}

	public void setOn_screen(boolean onScreen) {
		on_screen = onScreen;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
