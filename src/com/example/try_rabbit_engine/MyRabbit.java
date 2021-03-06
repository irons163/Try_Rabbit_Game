package com.example.try_rabbit_engine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.try_gameengine.framework.CommonUtil;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_rabbit_engine.DirectionController.DirectionType;


public class MyRabbit extends Sprite {
	public boolean isUpToScreenMid = false;
	
	enum Rabbit_action {

		LMove(
				"RABIT_LMOVE",
				new Bitmap[] {
						
						BitmapUtil
								.getBitmapFromRes(R.drawable.rabit_on_ground_left_jump0),
						BitmapUtil
								.getBitmapFromRes(R.drawable.rabit_on_ground_left_jump1),
								BitmapUtil.getBitmapFromRes(R.drawable.rabit_left_stop),}), RMove(
				"RABIT_RMOVE",
				new Bitmap[] {
						
						BitmapUtil
								.getBitmapFromRes(R.drawable.rabit_on_ground_right_jump0),
						BitmapUtil
								.getBitmapFromRes(R.drawable.rabit_on_ground_right_jump1),
								BitmapUtil
								.getBitmapFromRes(R.drawable.rabit_right_stop),}), LJump(
				"RABIT_LJUMP",
				new Bitmap[] { BitmapUtil
						.getBitmapFromRes(R.drawable.rabit_on_ground_left_jump0) }), RJump(
				"RABIT_RJUMP",
				new Bitmap[] { BitmapUtil
						.getBitmapFromRes(R.drawable.rabit_on_ground_right_jump0) }), LDown(
				"RABIT_LDOWN", new Bitmap[] {  BitmapUtil
						.getBitmapFromRes(R.drawable.rabit_on_air_left_down),
						BitmapUtil.getBitmapFromRes(R.drawable.rabit_left_stop),}), RDown(
				"RABIT_RDOWN", new Bitmap[] { BitmapUtil
						.getBitmapFromRes(R.drawable.rabit_on_air_right_down),
						BitmapUtil
						.getBitmapFromRes(R.drawable.rabit_right_stop),});

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

	public MyRabbit(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		// TODO Auto-generated constructor stub
		addAction(Rabbit_action.LMove.getName(),
				Rabbit_action.LMove.getBitmaps(), new int[] { 50, 250, 250 },
				false);
		addAction(Rabbit_action.RMove.getName(),
				Rabbit_action.RMove.getBitmaps(), new int[] { 50, 250, 250 },
				false);
		addAction(Rabbit_action.LJump.getName(),
				Rabbit_action.LJump.getBitmaps(), new int[] { 0, 1500, 1500 },
				false);
		addAction(Rabbit_action.RJump.getName(),
				Rabbit_action.RJump.getBitmaps(), new int[] { 0, 1500, 1500 },
				false);
		addAction(Rabbit_action.LDown.getName(),
				Rabbit_action.LDown.getBitmaps(), new int[] { 0, 3000},
				false);
		addAction(Rabbit_action.RDown.getName(),
				Rabbit_action.RDown.getBitmaps(), new int[] { 0, 3000},
				false);
		setAction(Rabbit_action.LMove.getName());
		
		
	}

	@Override
	public void setPosition(float x, float y) {
		// TODO Auto-generated method stub
		super.setPosition(x, y);
		initY = (int) y;
	}



	@Override
	public void setAction(String actionName) {
		// TODO Auto-generated method stub
		super.setAction(actionName);
	}

	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.drawSelf(canvas, paint);
	}

	@Override
	public void addAction(String name, int[] frames, int[] frameTime) {
		// TODO Auto-generated method stub
		super.addAction(name, frames, frameTime);
	}

	public static final int SPEED_JUMP = 10;
	public float speedX;
	public float speedY;
	public static float speedG = 0.2f;
	private boolean isPlayerJumping = false;
	private float initY;
	private boolean isFirstDowning = true;
	private float continueMoveX, continueMoveY;
	private float maxHeight;
	private boolean isDownBellowScreen = false;

	@Override
	public synchronized void move(float dx, float dy) {
		// TODO Auto-generated method stub
		// super.move(dx, dy);
		float x = getX();
		float y = getY();
		if (isStop && (getActionName().equals(Rabbit_action.RMove.getName())||getActionName().equals(Rabbit_action.LMove.getName())||getActionName().equals(Rabbit_action.RDown.getName())||getActionName().equals(Rabbit_action.LDown.getName()))) {
			frameIdx = 0;
			continueMoveX = dx;
			continueMoveY = dy;
			
			 if (dy == 0) {
					if (dx > 0) {
						setAction(Rabbit_action.RMove.getName());
					} else if(dx <0){
						setAction(Rabbit_action.LMove.getName());
					}
			 }
		}else{
			
			if (isPlayerJumping) {
				long a = System.currentTimeMillis();
				Log.e("time", a+""+"X"+System.currentTimeMillis());
				if (speedY < 0 && isFirstDowning) {
					isFirstDowning = false;
					if (getActionName().equals(Rabbit_action.RJump.getName())) {
						setAction(Rabbit_action.RDown.getName());
					} else {
						setAction(Rabbit_action.LDown.getName());
					}
				}
				
				a = System.currentTimeMillis() - a;
				Log.e("time", a+""+"X"+System.currentTimeMillis());
				
					speedY -= speedG;

					setX(x += speedX);
					setY(y -= speedY);
					if (y >= initY) {
						isPlayerJumping = false;						
						setY(initY);
						forceToNextFrameBitmap();
					}else if(y > CommonUtil.screenHeight){
						//over
						isDownBellowScreen = true;
					}
				
//					setX(x += dx);
//					setY(y += dy);
			}else{
				setX(x += continueMoveX);
				setY(y += continueMoveY);
			}	
					
		}
		
		if(y <= CommonUtil.screenHeight/2){
			maxHeight += CommonUtil.screenHeight/2 - y; 
			setY(CommonUtil.screenHeight/2);
//			if(maxHeight > CommonUtil.screenHeight/2 - initY)
			initY += CommonUtil.screenHeight/2 - y;	
			isUpToScreenMid = true;
		}else if(maxHeight < initY - CommonUtil.screenHeight/2 && speedY >= 0){
			maxHeight += speedY;
		}
		
		if(speedY < 0){
			isUpToScreenMid = false;
		}
	}

	// if(){
	//
	// }
	public boolean isPlayerJumping() {

		return isPlayerJumping;
	}

	public void setPlayerJumping(boolean isPlayerJumping, boolean isInterruptPreviousAction) {
		if(isStop || isInterruptPreviousAction){
			if (DirectionController.getDirectionType() == DirectionType.RIGHT) {
				setAction(Rabbit_action.RJump.getName());
				this.speedX = 3;
			} else {
				setAction(Rabbit_action.LJump.getName());
				this.speedX = -3;
			}
			
			this.isPlayerJumping = isPlayerJumping;		
			this.speedY = SPEED_JUMP;
			this.isFirstDowning = true;
		}
	}
	
	public float getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(float maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	public boolean isDownBellowScreen(){
		return isDownBellowScreen;
	}
}
