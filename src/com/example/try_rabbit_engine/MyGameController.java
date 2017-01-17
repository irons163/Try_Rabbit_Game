package com.example.try_rabbit_engine;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.try_wolfman.framework.GameController;
import com.example.try_wolfman.framework.GameModel;
import com.example.try_wolfman.framework.GameView;
import com.example.try_wolfman.framework.IGameModel;

public class MyGameController extends GameController implements IMoveEvent{
	MyGameModel myGameModel;
	public MyGameController(Activity activity, MyGameModel gameModel) {
		this.gameModel = gameModel;
		myGameModel = gameModel;
		GameView gameView = new MyGameView(activity, this, gameModel);
		activity.setContentView(gameView);
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
}
