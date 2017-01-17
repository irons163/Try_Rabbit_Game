package com.example.try_rabbit_engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Rabit {
	private Context context;
	private float x;
	private float y;
	private float center_x;
	private float center_y;
	private Bitmap currentBitmap;
	private Bitmap[] allBitmaps;
	private float speed_x_left;
	private float speed_x_right;
	private float speed_y_up;
	private float speed_y_down;
	private float x_destination;
	
	private int pic_state;
	private int face_state;
	private int ground_state;
	private int air_state;
	private Bitmap[] bitmaps;
	
	public static final float RABIT_WIDTH = Constant.RABIT_WIDTH;
	public static final float RABIT_HEIGHT = Constant.RABIT_HEIGHT;
	
	//rabit ͼƬ��״̬
	public static final int RABIT_PIC_LEFT_STOP = 0;
	public static final int RABIT_PIC_RIGHT_STOP = 1;
	public static final int RABIT_PIC_ON_GROUND_LEFT_JUMP0 = 2;
	public static final int RABIT_PIC_ON_GROUND_LEFT_JUMP1 = 3;
	public static final int RABIT_PIC_ON_GROUND_RIGHT_JUMP0 = 4;
	public static final int RABIT_PIC_ON_GROUND_RIGHT_JUMP1 = 5;
	public static final int RABIT_PIC_ON_AIR_LEFT_JUMP = 6;
	public static final int RABIT_PIC_ON_AIR_RIGHT_JUMP = 7;
	public static final int RABIT_PIC_ON_AIR_LEFT_STOP = 8;
	public static final int RABIT_PIC_ON_AIR_RIGHT_STOP = 9;
	public static final int RABIT_PIC_ON_AIR_LEFT_DOWN = 10;
	public static final int RABIT_PIC_ON_AIR_RIGHT_DOWN = 11;
	//rabit��������״̬
	public static final int RABIT_FACE_LEFT = 1;
	public static final int RABIT_FACE_RIGHT = 2;
	//rabit�ڵ���״̬
	public static final int RABIT_NOT_ON_GROUND = 0;
	public static final int RABIT_LEFT_STOP = 1;
	public static final int RABIT_RIGHT_STOP = 2;
	public static final int RABIT_LEFT_MOVE1_ON_GROUND = 3;
	public static final int RABIT_LEFT_MOVE2_ON_GROUND = 4;
	public static final int RABIT_RIGHT_MOVE1_ON_GROUND = 5;
	public static final int RABIT_RIGHT_MOVE2_ON_GROUND = 6;
	//rabit �ڿ��е�״̬ 
	public static final int RABIT_ON_AIR_UP0 = 0;
	public static final int RABIT_ON_AIR_UP1 = 1;
	public static final int RABIT_ON_AIR_UP2 = 2;
	public static final int RABIT_ON_AIR_UP3 = 3;
	public static final int RABIT_ON_AIR_UP4 = 4;
	public static final int RABIT_ON_AIR_UP5 = 5;
	public static final int RABIT_ON_AIR_STOP = 6;
	public static final int RABIT_ON_AIR_DOWN = 7;
	
	//rabit ÿ����Ծˢ�µĸ߶��ǲ�ͬ��
	public static final float RABIT_UP_DESTIATON0 = 30;
	public static final float RABIT_UP_DESTIATON1 = 20;
	public static final float RABIT_UP_DESTIATON2 = 10;
	//rabit ÿ���½�ˢ�µľ����ǲ�ͬ��
	public static final float RABIT_DOWN_DESTIATON0 = 10;
	public static final float RABIT_DOWN_DESTIATON1 = 20;
	public static final float RABIT_DWON_DESTIATON2 = 30;
	public static final float SPEED_X = 10;
	public static final float SPEED_Y = 15;
	public static final float SPEED_X_ON_AIR = 15;
	
	
	private Paint paint;
	
	public Rabit(Context context){
		this.context = context;
		initBitmaps();
//		x = 100;
//		y = 100;
		this.setX(Constant.RABIT_INIT_X);
		this.setY(Constant.RABIT_INIT_Y);
		x_destination = x;
		speed_x_left = 0;
		speed_x_right = 0;
		speed_y_down = 15;
		speed_y_up = 15;
		paint = new Paint();
		ground_state = RABIT_RIGHT_STOP;
		air_state = RABIT_ON_AIR_UP0;
		face_state = RABIT_FACE_RIGHT;
		this.pic_state = RABIT_PIC_RIGHT_STOP;

	}
		
	public void init(){
		this.setY(Constant.RABIT_INIT_Y);
		ground_state = RABIT_RIGHT_STOP;
		air_state = RABIT_ON_AIR_UP0;
		face_state = RABIT_FACE_RIGHT;
		this.pic_state = RABIT_PIC_RIGHT_STOP;
	}
	
	private void initBitmaps(){
		bitmaps = new Bitmap[12];
		bitmaps[Rabit.RABIT_PIC_LEFT_STOP] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabit_left_stop);
		bitmaps[Rabit.RABIT_PIC_RIGHT_STOP] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabit_right_stop);
		bitmaps[RABIT_PIC_ON_GROUND_LEFT_JUMP0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabit_on_ground_left_jump0);
		bitmaps[RABIT_PIC_ON_GROUND_LEFT_JUMP1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabit_on_ground_left_jump1);
		bitmaps[RABIT_PIC_ON_GROUND_RIGHT_JUMP0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabit_on_ground_right_jump0);
		bitmaps[RABIT_PIC_ON_GROUND_RIGHT_JUMP1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabit_on_ground_right_jump1);
		bitmaps[RABIT_PIC_ON_AIR_LEFT_JUMP] = bitmaps[RABIT_PIC_ON_GROUND_LEFT_JUMP0];
		bitmaps[RABIT_PIC_ON_AIR_RIGHT_JUMP] = bitmaps[RABIT_PIC_ON_GROUND_RIGHT_JUMP0];
		bitmaps[RABIT_PIC_ON_AIR_LEFT_DOWN] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabit_on_air_left_down);
		bitmaps[RABIT_PIC_ON_AIR_RIGHT_DOWN] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabit_on_air_right_down);;
		bitmaps[RABIT_PIC_ON_AIR_LEFT_STOP] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabit_on_air_left_stop);;
		bitmaps[RABIT_PIC_ON_AIR_RIGHT_STOP] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabit_on_air_right_stop);;
	}
	
	
	public void onDraw(Canvas canvas){
		canvas.drawBitmap(bitmaps[pic_state], x, y, paint);
	}
	
	public boolean isHitBell(Bell bell){
		if(bell.isExplode()) return false;
//		return Math.abs(bell.getCenter_x()-this.getCenter_x())<=10 && Math.abs(bell.getCenter_y()-this.getCenter_y())<=10;
		float x1 = center_x;
		float y1 = center_y;
		float x2 = bell.getCenter_x();
		float y2 = bell.getCenter_y();
		//(x1-x2)^2+(y1-y2)^2 < (RABIT_WIDTH-8)^2
		return Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2) < 400;
	}
	
	public boolean isHitBird(Bird bird){
//		return Math.abs(bell.getCenter_x()-this.getCenter_x())<=10 && Math.abs(bell.getCenter_y()-this.getCenter_y())<=10;
		float x1 = center_x;
		float y1 = center_y;
		float x2 = bird.getCenter_x();
		float y2 = bird.getCenter_y();
		//(x1-x2)^2+(y1-y2)^2 < (RABIT_WIDTH-8)^2
		return Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2) < 400;
	}
	
	public boolean isOnGround(){
		return !(ground_state==RABIT_NOT_ON_GROUND);
	}
	
	public boolean isOnAir(){
		return !isOnGround();
	}
	
	public boolean isOnGroundStop(){
		return ground_state==RABIT_LEFT_STOP||ground_state==RABIT_RIGHT_STOP;
	}
	public boolean isFaceLeft(){
		return face_state==RABIT_FACE_LEFT;
	}
	
	public boolean isFaceRight(){
		return !isFaceLeft();
	}
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		this.center_x = x + RABIT_WIDTH/2;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		this.center_y = y + RABIT_HEIGHT/2;
	}

	public Bitmap getCurrentBitmap() {
		return currentBitmap;
	}

	public void setCurrentBitmap(Bitmap currentBitmap) {
		this.currentBitmap = currentBitmap;
	}

	public Bitmap[] getAllBitmaps() {
		return allBitmaps;
	}

	public void setAllBitmaps(Bitmap[] allBitmaps) {
		this.allBitmaps = allBitmaps;
	}

	public float getSpeed_x_left() {
		return speed_x_left;
	}

	public void setSpeed_x_left(float speedXLeft) {
		speed_x_left = speedXLeft;
	}

	public float getSpeed_x_right() {
		return speed_x_right;
	}

	public void setSpeed_x_right(float speedXRight) {
		speed_x_right = speedXRight;
	}

	public float getSpeed_y_up() {
		return speed_y_up;
	}

	public void setSpeed_y_up(float speedYUp) {
		speed_y_up = speedYUp;
	}

	public float getSpeed_y_down() {
		return speed_y_down;
	}

	public void setSpeed_y_down(float speedYDown) {
		speed_y_down = speedYDown;
	}


	public Bitmap[] getBitmaps() {
		return bitmaps;
	}

	public void setBitmaps(Bitmap[] bitmaps) {
		this.bitmaps = bitmaps;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public float getX_destination() {
		return x_destination;
	}

	public void setX_destination(float xDestination) {
		x_destination = xDestination;
	}

	public int getFace_state() {
		return face_state;
	}

	public void setFace_state(int faceState) {
		face_state = faceState;
	}

	public int getGround_state() {
		return ground_state;
	}

	public void setGround_state(int groundState) {
		ground_state = groundState;
	}

	public int getAir_state() {
		return air_state;
	}

	public void setAir_state(int airState) {
		air_state = airState;
	}

	public int getPic_state() {
		return pic_state;
	}

	public void setPic_state(int picState) {
		pic_state = picState;
	}

	public float getCenter_x() {
		return center_x;
	}

	public void setCenter_x(float centerX) {
		center_x = centerX;
		this.x = centerX - RABIT_WIDTH/2;
	}

	public float getCenter_y() {
		return center_y;
	}

	public void setCenter_y(float centerY) {
		center_y = centerY;
		y = centerY - RABIT_HEIGHT/2;
	}

	
}
