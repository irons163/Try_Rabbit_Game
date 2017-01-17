package com.example.try_rabbit_engine;

import java.util.List;
import java.util.Random;

import com.example.try_wolfman.framework.BitmapUtil;
import com.example.try_wolfman.framework.CommonUtil;
import com.example.try_wolfman.framework.Sprite;

import android.graphics.Point;

public class BellFactory {
	private int bellWidth = 60;
	private int bellHeight = 60;
	private int minDistanceX = (int) (bellWidth*1.5);
	private int maxDistanceX = bellWidth*3;
	private int minDistanceY = MyRabbit.SPEED_JUMP/4 * MyRabbit.SPEED_JUMP;
	private int maxDistanceY = MyRabbit.SPEED_JUMP/2 * MyRabbit.SPEED_JUMP;
	private int baseYToCalculateNextRowY;
	
	public BellFactory(float rabbitY) {
		// TODO Auto-generated constructor stub
		baseYToCalculateNextRowY = (int) rabbitY;
	}
	
	public MyBell createBell(List<MyBell> bells){
		Point newBellXY;
		if(bells.size()==0){
			newBellXY = randomFirstBellXY();
		}else{
			newBellXY = randomNextBellXY(bells.get(bells.size()-1));
		}
		
		MyBell myBell = new MyBell(BitmapUtil.getBitmapFromRes(R.drawable.bell_ok), bellWidth, bellHeight, true);
		myBell.setPosition(newBellXY.x, newBellXY.y);
		return myBell;		
	}
	
	private Point randomFirstBellXY(){
		return createNextBellXY(0, baseYToCalculateNextRowY);
	}
	
	private Point randomNextBellXY(MyBell currentBell){
		int currentBellX = (int) currentBell.x;
		int currentBellY = (int) currentBell.y;
		
		return createNextBellXY(currentBellX, currentBellY);
	}
	
	private Point createNextBellXY(int currentBellX, int currentBellY){
		Point point = new Point();
		Random random = new Random();
		int x = random.nextInt(maxDistanceX - minDistanceX) + minDistanceX + currentBellX;
		int y;
		if(x > CommonUtil.screenWidth - bellWidth){
			baseYToCalculateNextRowY = currentBellY;
			x -= CommonUtil.screenWidth;
			y = currentBellY - (random.nextInt(maxDistanceY - minDistanceY) + minDistanceY);
		}else{
			y = baseYToCalculateNextRowY - (random.nextInt(maxDistanceY - minDistanceY) + minDistanceY);
		}
		point.set(x, y);
		return point;
	}
	
}
