package com.example.try_rabbit_engine;

import android.content.Context;

import com.example.try_gameengine.framework.Data;
import com.example.try_gameengine.framework.GameModel;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameModel;



public class MyGameView extends GameView{
	private Data data;
//	private int[][] allExistPoints;
//	private Iterator<MyEnemy> allExistPointsIterator; 
	MyGameController gameController;
	public MyGameView(Context context, MyGameController gameController,
			IGameModel gameModel) {
		super(context, gameController, gameModel);
		// TODO Auto-generated constructor stub
		data = gameModel.getData();
//		allExistPoints = (int[][]) data.getAllExistPoints();
//		allExistPointsIterator = data.getAllExistPointsIterator();
		this.gameController = gameController;
	}

	
	

}
