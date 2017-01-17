package com.example.try_wolfman.framework;

import android.graphics.Bitmap;

public class ChessPoint extends IChessPoint{
	Bitmap chessPointBimap;
	
	@Override
	public Bitmap getChessPointBitmap() {
		// TODO Auto-generated method stub
		return chessPointBimap;
	}

	@Override
	public void setChessPointBitmap(Bitmap chessPointBimap) {
		// TODO Auto-generated method stub
		this.chessPointBimap = chessPointBimap;
	}
}
