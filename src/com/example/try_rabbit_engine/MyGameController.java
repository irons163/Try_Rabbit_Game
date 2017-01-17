package com.example.try_rabbit_engine;

import android.app.Activity;

import com.example.try_gameengine.framework.GameController;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameModel;

public class MyGameController extends GameController implements IMoveEvent{
	MyGameModel myGameModel;
	GameView gameView;
	
	public MyGameController(Activity activity, MyGameModel gameModel) {
		super(activity, gameModel);
		
		initStart();
	}
	
//	@Override
//	public void drawChessboardLines(Canvas canvas, Paint paint) {
//		myGameModel.drawChessboardLines(canvas, paint);
//	}
//
//	@Override
//	public void drawAllExistPoints(Canvas canvas) {
//		// TODO Auto-generated method stub
//		myGameModel.drawAllExistPoints(canvas);
//	}
//
//	@Override
//	public void drawPlayerPocessableMovePoints(Canvas canvas) {
//		// TODO Auto-generated method stub
//		myGameModel.drawPlayerPocessableMovePoints(canvas);
//	}

//	@Override
//	public void drawPlane(Canvas canvas) {
//		// TODO Auto-generated method stub
//		myGameModel.drawPlane(canvas);
//	}
//
//	@Override
//	public void drawEnemy(Canvas canvas) {
//		// TODO Auto-generated method stub
//		myGameModel.drawEnemy(canvas);
//	}
//
//	@Override
//	public void drawBullet(Canvas canvas) {
//		// TODO Auto-generated method stub
//		myGameModel.drawBullet(canvas);
//	}

	@Override
	public void movePlane() {
		// TODO Auto-generated method stub
		myGameModel.movePlane();
	}

	@Override
	public void moveEnemy() {
		// TODO Auto-generated method stub
		myGameModel.moveEnemy();
	}

	@Override
	public void moveBullet() {
		// TODO Auto-generated method stub
		myGameModel.moveBullet();
	}

	@Override
	protected void initGameView(Activity activity, IGameModel gameModel) {
		// TODO Auto-generated method stub
		this.gameModel = gameModel;
		myGameModel = (MyGameModel) gameModel;
		gameView = new MyGameView(activity, this, gameModel);
		
	}

	@Override
	protected void arrangeView() {
		// TODO Auto-generated method stub
		activity.setContentView(gameView);
	}

	@Override
	protected void beforeGameStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void afterGameStart() {
		// TODO Auto-generated method stub
		
	}
}
