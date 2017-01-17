package com.example.try_rabbit_engine;

import java.util.List;
import java.util.Random;

import android.graphics.Point;

import com.example.try_wolfman.framework.BitmapUtil;
import com.example.try_wolfman.framework.CommonUtil;

public class BirdFactory {
	private int birdWidth = 60;
	private int birdHeight = 60;
	private int minDistanceX = (int) (birdWidth*1.5);
	private int maxDistanceX = birdWidth*3;
	private int minDistanceY = MyRabbit.SPEED_JUMP/4 * MyRabbit.SPEED_JUMP;
	private int maxDistanceY = MyRabbit.SPEED_JUMP/2 * MyRabbit.SPEED_JUMP;
	private int baseYToCalculateNextRowY;
	public static final int flyFromDirectionLeft = 0;
	public static final int flyFromDirectionRight = 1;
	private int flyFromDirectionTypeCount = 2;
	
	public BirdFactory() {
		// TODO Auto-generated constructor stub
//		baseYToCalculateNextRowY = (int) rabbitY;
	}
	
	public MyBird createBird(){
		Point newBirdXY;
		newBirdXY = randomMyBirdXY();
		
		MyBird myBird = new MyBird(BitmapUtil.getBitmapFromRes(R.drawable.bird_left_fly0), birdWidth, birdHeight, true);
		myBird.setPosition(newBirdXY.x, newBirdXY.y);
		return myBird;		
	}
	
	private Point randomMyBirdXY(){
		
		Point point = new Point();
		Random random = new Random();
		int x;
		if(random.nextInt(flyFromDirectionTypeCount) == flyFromDirectionLeft){
			x = 0 - birdWidth;
		} else{
			x = CommonUtil.screenWidth;
		}
		
		int y =  random.nextInt(CommonUtil.screenHeight - birdHeight);
		point.set(x, y);
		return point;
	}
}
