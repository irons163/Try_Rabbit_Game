package com.example.try_rabbit_engine;

import java.util.Iterator;

import android.content.Context;
import android.graphics.Canvas;

import com.example.try_wolfman.framework.Data;
import com.example.try_wolfman.framework.GameModel;
import com.example.try_wolfman.framework.GameView;
import com.example.try_wolfman.framework.IGameController;
import com.example.try_wolfman.framework.IGameModel;

public class MyGameView extends GameView{
	private Data data;
//	private int[][] allExistPoints;
//	private Iterator<MyEnemy> allExistPointsIterator; 
	MyGameController gameController;
	public MyGameView(Context context, MyGameController gameController,
			GameModel gameModel) {
		super(context, gameController, gameModel);
		// TODO Auto-generated constructor stub
		data = gameModel.getData();
//		allExistPoints = (int[][]) data.getAllExistPoints();
//		allExistPointsIterator = data.getAllExistPointsIterator();
		this.gameController = gameController;
	}

	
	

}
