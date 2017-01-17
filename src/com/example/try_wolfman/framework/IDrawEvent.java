package com.example.try_wolfman.framework;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface IDrawEvent {
	public void drawChessboardLines(Canvas canvas, Paint paint);
	public void drawAllExistPoints(Canvas canvas);
	public void drawPlayerPocessableMovePoints(Canvas canvas);
}
