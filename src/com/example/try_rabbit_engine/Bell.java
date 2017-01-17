package com.example.try_rabbit_engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Bell {
	private float center_x;
	private float center_y;
	private Bitmap[] bitmaps;
	private Context context;
	private int state;
	private Paint paint;
	
	public static final float BELL_OK_WIDTH = Constant.BELL_OK_WIDTH;
	public static final float BELL_OK_HEIGHT= Constant.BELL_OK_HEIGHT;
	public static final float BELL_EXPLODE_WIDTH = Constant.BELL_EXPLODE_WIDTH;
	public static final float BELL_EXPLODE_HEIGHT = Constant.BELL_EXPLODE_HEIGHT;
	// Bell state
	public static final int BELL_OK = 0;
	public static final int BELL_EXPLODE0 = 1;
	public static final int BELL_EXPLODE1 = 2;
	public static final int BELL_EXPLODE2 = 3;

	
	public Bell(Context context, float center_x, float center_y) {
		this.center_x = center_x;
		this.center_y = center_y;
		this.context = context;
		initBitmaps();
		state = BELL_OK;
		paint = new Paint();
	}
	
	public Bell(Context context){
		this.context = context;
		initBitmaps();
		state = BELL_OK;
		paint = new Paint();
	}

	private void initBitmaps() {
		bitmaps = new Bitmap[4];
		bitmaps[0] = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.bell_ok);
		bitmaps[1] = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.bell_explode0);
		bitmaps[2] = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.bell_explode1);
		bitmaps[3] = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.bell_explode2);
	}

	public void onDraw(Canvas canvas) {
		switch(state){
		case BELL_OK:
			canvas.drawBitmap(bitmaps[BELL_OK], center_x-BELL_OK_WIDTH/2, center_y-BELL_OK_HEIGHT/2, paint);
			break;
		case BELL_EXPLODE0:
			canvas.drawBitmap(bitmaps[BELL_EXPLODE0], center_x-BELL_EXPLODE_WIDTH/2, center_y-BELL_EXPLODE_HEIGHT/2, paint);
			break;
		case BELL_EXPLODE1:
			canvas.drawBitmap(bitmaps[BELL_EXPLODE1], center_x-BELL_EXPLODE_WIDTH/2, center_y-BELL_EXPLODE_HEIGHT/2, paint);
			break;
		case BELL_EXPLODE2:
			canvas.drawBitmap(bitmaps[BELL_EXPLODE2], center_x-BELL_EXPLODE_WIDTH/2, center_y-BELL_EXPLODE_HEIGHT/2, paint);
			break;
		default:
			canvas.drawBitmap(bitmaps[BELL_OK], center_x-BELL_OK_WIDTH/2, center_y-BELL_OK_HEIGHT/2, paint);
			break;	
		}
	}
	
	public boolean isOK(){
		return state==BELL_OK;
	}
	
	public boolean isExplode(){
		return !isOK();
	}

	public float getCenter_x() {
		return center_x;
	}

	public void setCenter_x(float centerX) {
		center_x = centerX;
	}

	public float getCenter_y() {
		return center_y;
	}

	public void setCenter_y(float centerY) {
		center_y = centerY;
	}

	public Bitmap[] getBitmaps() {
		return bitmaps;
	}

	public void setBitmaps(Bitmap[] bitmaps) {
		this.bitmaps = bitmaps;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	
}
