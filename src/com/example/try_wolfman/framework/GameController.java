package com.example.try_wolfman.framework;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class GameController implements IGameController{
	protected IGameModel gameModel;
	
	public GameController() {
		// TODO Auto-generated constructor stub
	}
	
	public GameController(Activity activity, IGameModel gameModel) {
		// TODO Auto-generated constructor stub
		this.gameModel = gameModel;
		GameView gameView = new GameView(activity, this, gameModel);
		activity.setContentView(gameView);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		gameModel.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showWin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showLose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		gameModel.onTouchEvent(event);
	}

	@Override
	public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
		// TODO Auto-generated method stub
		gameModel.setSurfaceHolder(surfaceHolder);
	}

}
