package com.example.try_wolfman.framework;

import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Menu;

public abstract class BaseActivity extends Activity {
	private IGameModel gameModel;
	private IGameController gameController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		CommonUtil.screenHeight = dm.heightPixels;
		CommonUtil.screenWidth = dm.widthPixels;
		
		BitmapUtil.initBitmap(this);
		
		LayerManager.initLayerManager();
		
		initGameModel();
		initGameController();
	}
	
	protected void initGameModel() {
		gameModel = new GameModel(this, new Data() {
			
			@Override
			public void setAllExistPoints(Object allExistPoints) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Object getAllExistPoints() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Iterator getAllExistPointsIterator() {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
	
	protected void initGameController() {
		gameController = new GameController(this, gameModel);
	}

}

