package com.example.try_wolfman.framework;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class ChessBoard implements IChessBoard {
	
	private static int ROW_POINT_COUNT = 8;
	private static int COL_POINT_COUNT = 8;

	// 畫棋盤
	private List<Line> lines = new ArrayList<Line>();// 此Line集合在onSizeChange時已被初始化，內有數條線(EX:25)

	private int maxX;
	private int maxY;
	private int xOffset;
	private int yOffset;
	private int lineDistance;

	protected int[][] allExistPoints = new int[][] { { 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 2, 0, 2, 0, 2, 0, 2, 0 }, { 0, 2, 0, 2, 0, 2, 0, 2 },
			{ 2, 0, 2, 0, 2, 0, 2, 0 } };

	private int width, height;
	private int widthPaddingDimension;

	private List<IPlayer> players = new ArrayList<IPlayer>();

	public ChessBoard(int width, int height, int colPointNum, int rowPointNum) {
		this.width = width;
		this.height = height;

		COL_POINT_COUNT = colPointNum;
		ROW_POINT_COUNT = rowPointNum;
		
		maxX = COL_POINT_COUNT + 1;
		maxY = ROW_POINT_COUNT + 1;

		lineDistance = width / (maxX);
		widthPaddingDimension = lineDistance / 2;

		this.xOffset = 0;
		this.yOffset = 0;

	}

	// 產生棋盤上所有的線
	public void createLines() {
		for (int i = 0; i < maxX; i++) {// 豎線 0-24 共25條
			// (5+0-10) (240+20-10) (-5+480-10)
			lines.add(new Line((i + 1) * lineDistance - widthPaddingDimension,
					widthPaddingDimension, (i + 1) * lineDistance
							- widthPaddingDimension, maxY * lineDistance
							- widthPaddingDimension));
		}
		for (int i = 0; i < maxY; i++) {// 橫線
			lines.add(new Line(widthPaddingDimension, (i + 1) * lineDistance
					- widthPaddingDimension, maxX * lineDistance
					- widthPaddingDimension, (i + 1) * lineDistance
					- widthPaddingDimension));
		}
	}

	public void createPoints() {

		// for (int i = 0; i < maxX - 1; i++) {
		// for (int j = 0; j < maxY - 1; j++) {
		// allExistPoints[i][j] = 0;
		// }
		// }
		for (int j = 0; j < maxY - 1; j++) {
			for (int i = 0; i < maxX - 1; i++) {
				allExistPoints[i][j] = 0;
			}
		}
		
	}

	// 根據觸摸點座標找到對應點
	// public int newPoint(Float x, Float y) {
	// // Point p = new Point(-1, -1);// 創建橫軸編號為0(橫軸的第一個點)，縱軸編號也為0(縱軸的第一個點)的點
	// int positionX = -1;
	// if (y >= widthPaddingDimension + yOffset
	// && y <= ROW_POINT_COUNT * lineDistance + widthPaddingDimension
	// + yOffset)
	// for (int i = 0; i < maxX - 1; i++) {// 0-23 共24點
	// // (0-5)<0 0<(20-5)
	// if ((i * lineDistance + widthPaddingDimension + xOffset) <= x
	// && x < ((i + 1) * lineDistance + widthPaddingDimension + xOffset)) {
	// // p.setX(i);//設定p的x為i，也就是橫軸第i+1個點
	// positionX = i;
	// }
	// }
	//
	// return positionX; // 回傳 ponit p
	// }

	// 根據觸摸點座標找到對應點
	public Point newPoint(Float x, Float y) {
		Point p = new Point(0, 0);// 創建橫軸編號為0(橫軸的第一個點)，縱軸編號也為0(縱軸的第一個點)的點
		for (int i = 0; i < maxX - 1; i++) {// 0-23 共24點
			// (0-5)<0 0<(20-5)
			if ((i * lineDistance + widthPaddingDimension + xOffset) <= x
					&& x < ((i + 1) * lineDistance + widthPaddingDimension + xOffset)) {
				// p.setX(i);//設定p的x為i，也就是橫軸第i+1個點
				p.x = i;
			}
		}
		for (int i = 0; i < maxY - 1; i++) {// 跟上面橫軸差不多，這裡是處理縱軸
			if ((i * lineDistance + widthPaddingDimension + yOffset) <= y
					&& y < ((i + 1) * lineDistance + widthPaddingDimension + yOffset)) {
				// p.setY(i);
				p.y = i;
			}
		}
		return p; // 回傳 ponit p
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public int getLineDistance() {
		return lineDistance;
	}

	public void setLineDistance(int lineDistance) {
		this.lineDistance = lineDistance;
	}

	public int[][] getAllExistPoints() {
		return allExistPoints;
	}

	public void setAllExistPoints(int[][] allExistPoints) {
		this.allExistPoints = allExistPoints;
	}

	@Override
	public void drawChessboardLines(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		for (Line line : lines) {
			// 在View本身的畫布上畫線
			line.draw(canvas, paint);
		}
	}

	@Override
	public void drawAllExistPoints(Canvas canvas) {
		// TODO Auto-generated method stub
		for (int i = 0; i < allExistPoints.length; i++) { // 畫所有舊棋子
			for (int j = 0; j < allExistPoints[i].length; j++) {
				if (allExistPoints[i][j] == 0)
					continue;
				// drawPoint(canvas, new Point(j, i),
				// pointArray[jumpChessBoard.allExistPoints[i][j]-1]);
				drawPoint(canvas, new Point(i, j),
						players.get(allExistPoints[i][j] - 1).getPocessableMvoeChessPoint()
								.getChessPointBitmap());
			}
		}
	}
	
	@Override
	public void drawPlayerPocessableMovePoints(Canvas canvas) {
		for(int i = 0; i < Logic.jumps.size(); i++){
			Point point = Logic.jumps.get(i);
			drawPoint(canvas, point,
				players.get(Logic.whoPlay-1).getChessPoint()
						.getChessPointBitmap());
		}
	}

	// 畫點(畫棋子)
	private void drawPoint(Canvas canvas, Point p, Bitmap pointBmp) {
		canvas.drawBitmap(pointBmp, p.x * lineDistance + lineDistance / 2, p.y
				* lineDistance + lineDistance / 2, null);
	}

	@Override
	public void setPlayersBySquential(List<IPlayer> playersBySquential) {
		// TODO Auto-generated method stub
		this.players = playersBySquential;
	}
}

// 線類別
class Line {
	float xStart, yStart, xStop, yStop;

	// 建構子
	public Line(float xStart, float yStart, float xStop, float yStop) {
		// onSizeChange初始化時，把各個座標傳入(開始的xy座標到結束的xy座標)
		this.xStart = xStart;
		this.yStart = yStart;
		this.xStop = xStop;
		this.yStop = yStop;
	}

	public void draw(Canvas canvas, Paint paint) {
		canvas.drawLine(xStart, yStart, xStop, yStop, paint);
	}
}