package com.example.try_wolfman.framework;

import android.graphics.Bitmap;
import android.graphics.Point;

public abstract class IChessPoint extends Point{
	public abstract Bitmap getChessPointBitmap();
	public abstract void setChessPointBitmap(Bitmap ChessPointBimap);
}
