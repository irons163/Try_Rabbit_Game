package com.example.try_rabbit_engine;

import android.graphics.Bitmap;

import com.example.try_rabbit_engine.MyBell.Rabbit_action;
import com.example.try_wolfman.framework.BitmapUtil;
import com.example.try_wolfman.framework.CommonUtil;
import com.example.try_wolfman.framework.Sprite;

public class MyBird extends Sprite{
	private int birdRightMoveSpeed = 3;
	private int birdLeftMoveSpeed = -birdRightMoveSpeed;
	private int flyFromDirectionType;
	
	enum Rabbit_action {

		LBirdFly(
				"LBIRD_FLY",
				new Bitmap[] {
						BitmapUtil
								.getBitmapFromRes(R.drawable.bird_left_fly0),
						BitmapUtil
								.getBitmapFromRes(R.drawable.bird_left_fly1),
						BitmapUtil
								.getBitmapFromRes(R.drawable.bird_left_fly2)
						 }
		),
		RBirdFly(
				"RBIRD_FLY",
				new Bitmap[] {
						BitmapUtil
								.getBitmapFromRes(R.drawable.bird_right_fly0),
						BitmapUtil
								.getBitmapFromRes(R.drawable.bird_right_fly1),
						BitmapUtil
								.getBitmapFromRes(R.drawable.bird_right_fly2)
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
	
	public MyBird(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		// TODO Auto-generated constructor stub
		addAction(Rabbit_action.LBirdFly.getName(),
				Rabbit_action.LBirdFly.getBitmaps(), new int[] { 500, 500, 500 }, true);
		addAction(Rabbit_action.RBirdFly.getName(),
				Rabbit_action.RBirdFly.getBitmaps(), new int[] { 500, 500, 500 }, true);
	}
	
	public void setFlyFromDirection(int flyFromDirectionType){
		this.flyFromDirectionType = flyFromDirectionType;
	}

	@Override
	public void move(int dx, int dy) {
		// TODO Auto-generated method stub
		if(flyFromDirectionType == BirdFactory.flyFromDirectionLeft){
			super.move(birdRightMoveSpeed, 0);
		}else{
			super.move(birdLeftMoveSpeed, 0);
		}
	}

	public void setAction(){
		if(flyFromDirectionType == BirdFactory.flyFromDirectionLeft){
			setAction(Rabbit_action.RBirdFly.getName());
		}else{
			setAction(Rabbit_action.LBirdFly.getName());
		}
	}
	
	@Override
	public boolean isNeedRemoveInstance(){
		boolean isNeedRemoveInstance = false;
		if(flyFromDirectionType == BirdFactory.flyFromDirectionLeft && getX() >= CommonUtil.screenWidth){
			isNeedRemoveInstance = true;
		}else if(flyFromDirectionType == BirdFactory.flyFromDirectionRight && getX() <= 0){
			isNeedRemoveInstance = true;
		}
		return isNeedRemoveInstance; 
	}
}
