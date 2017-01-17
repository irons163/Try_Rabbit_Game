package com.example.try_wolfman.framework;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public interface IGameController {
	public void start();
	public void stop();
	public void showWin();
	public void showLose();
	public void onTouchEvent(MotionEvent event);
	public void setSurfaceHolder(SurfaceHolder surfaceHolder);
}
