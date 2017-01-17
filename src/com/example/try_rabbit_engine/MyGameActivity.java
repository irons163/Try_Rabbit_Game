package com.example.try_rabbit_engine;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;

import com.example.try_wolfman.framework.BaseActivity;
import com.example.try_wolfman.framework.CommonUtil;
import com.example.try_wolfman.framework.GameController;
import com.example.try_wolfman.framework.GameModel;
import com.example.try_wolfman.framework.IGameController;
import com.example.try_wolfman.framework.IGameModel;

public class MyGameActivity extends BaseActivity {
	private MyGameModel gameModel;
	private MyGameController gameController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
	}

	
	
	@Override
	protected void initGameModel() {
		// TODO Auto-generated method stub
		gameModel = new MyGameModel(this, new MyData());
//		gameModel.setData(new MyData());
	}



	@Override
	protected void initGameController() {
		// TODO Auto-generated method stub
		gameController = new MyGameController(this, gameModel);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
