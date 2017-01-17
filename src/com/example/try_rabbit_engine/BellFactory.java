package com.example.try_rabbit_engine;

import java.util.List;
import java.util.Random;

import com.example.try_gameengine.framework.CommonUtil;

import android.graphics.Point;
import android.util.Log;

public class BellFactory {
	private int bellWidth = 60;
	private int bellHeight = 60;
	private int minDistanceX = (int) (bellWidth*2);
	private int maxDistanceX = (int) (bellWidth*3.5);
	private int minDistanceY = (int) (MyRabbit.SPEED_JUMP/2.5 * MyRabbit.SPEED_JUMP/MyRabbit.speedG);
	private int maxDistanceY = (int)(MyRabbit.SPEED_JUMP/1.6 * MyRabbit.SPEED_JUMP/MyRabbit.speedG);
//	private int baseYToCalculateNextRowY;
	private MyBell baseMyBellToCalculateNextRowY;
	private List<MyBell> bells;
	public BellFactory(float rabbitY) {
		// TODO Auto-generated constructor stub
		initeBellFactory(rabbitY, bells);
	}
	
	public BellFactory(float rabbitY, List<MyBell> bells) {
		// TODO Auto-generated constructor stub
		initeBellFactory(rabbitY, bells);
	}
	
	private void initeBellFactory(float rabbitY, List<MyBell> bells){
		baseMyBellToCalculateNextRowY = new MyBell(null, 0, 0, false);
		baseMyBellToCalculateNextRowY.setPosition(0, rabbitY);
		this.bells = bells;
	}
	
	public MyBell createBell(){
		return createBell(bells);
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
		return createNextBellXY(baseMyBellToCalculateNextRowY);
	}
	
	private Point randomNextBellXY(MyBell currentBell){
//		int currentBellX = (int) currentBell.x;
//		int currentBellY = (int) currentBell.y;
		
//		return createNextBellXY(currentBellX, currentBellY);
		return createNextBellXY(currentBell);
	}
	
	private Point createNextBellXY(MyBell currentBell){
		Point point = new Point();
		Random random = new Random();
		int currentBellX = (int) currentBell.getX();
		int currentBellY = (int) currentBell.getY();
		int x = random.nextInt(maxDistanceX - minDistanceX) + minDistanceX + currentBellX;
		int y;
		if(x > CommonUtil.screenWidth - bellWidth){
			baseMyBellToCalculateNextRowY = currentBell;
			x -= (CommonUtil.screenWidth - bellWidth);
			Log.e("X", x+"");
			y = currentBellY - (random.nextInt(maxDistanceY - minDistanceY) + minDistanceY);
		}else{
			y = (int) (baseMyBellToCalculateNextRowY.getY() - (random.nextInt(maxDistanceY - minDistanceY) + minDistanceY));
		}
		point.set(x, y);
		return point;
	}
	
}
