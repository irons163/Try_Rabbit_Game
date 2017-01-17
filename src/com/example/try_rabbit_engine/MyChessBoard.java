package com.example.try_rabbit_engine;

import com.example.try_wolfman.framework.ArrayUtil;
import com.example.try_wolfman.framework.ChessBoard;

public class MyChessBoard extends ChessBoard{

	public MyChessBoard(int width, int height, int colPointNum, int rowPointNum) {
		super(width, height, colPointNum, rowPointNum);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPoints() {
		// TODO Auto-generated method stub
		allExistPoints = new int[][] { { 0, 1, 0, 1, 0, 1, 0, 1 },
				{ 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 2, 0, 2, 0, 2, 0, 2, 0 }, { 0, 2, 0, 2, 0, 2, 0, 2 },
				{ 2, 0, 2, 0, 2, 0, 2, 0 } };
		allExistPoints = ArrayUtil.arrayTranspose(allExistPoints);
	}
}
