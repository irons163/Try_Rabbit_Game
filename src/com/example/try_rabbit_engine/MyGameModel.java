package com.example.try_rabbit_engine;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.Log;
import android.view.MotionEvent;

import com.example.try_rabbit_engine.DirectionController.DirectionType;
import com.example.try_rabbit_engine.MyBell.Rabbit_action;
import com.example.try_wolfman.framework.BitmapUtil;
import com.example.try_wolfman.framework.ChessBoard;
import com.example.try_wolfman.framework.ChessPointManager;
import com.example.try_wolfman.framework.CommonUtil;
import com.example.try_wolfman.framework.Data;
import com.example.try_wolfman.framework.GameModel;
import com.example.try_wolfman.framework.LayerManager;
import com.example.try_wolfman.framework.Logic;
import com.example.try_wolfman.framework.PlayerManager;
import com.example.try_wolfman.framework.Sprite;
import com.example.try_wolfman.framework.Utils;

public class MyGameModel extends GameModel implements IMoveEvent, IAddAndRemoveEvent{
//	private MyData myPlaneData;
//	private MyData myEnemyData;
//	private MyData myBulletData;
	
	private MyData myRabbitData;
	private MyData myBirdData;
	private MyData myBellData;
	
//	private MyPlane myPlane;
//	private MyEnemy myEnemy;
//	private Sprite myBullet;
	
	private MyRabbit myRabbit;
	private MyBird myBird;
	private MyBell myBell;
	
	private int moveUpDestance = -5;
	private int moveDownDestance = 5;
	private int moveRightDestance = 5;
	private int moveLeftDestance = -5;
	
//	private DirectionController directionController;
	
	private MyButton myJumpBtn;
	private BellFactory bellFactory;
	private BirdFactory birdFactory;
	
	private boolean isEnableCreateBire = false;
	
	public MyGameModel(Context context, Data data) {
		super(context, data);
		// TODO Auto-generated constructor stub
//		myPlaneData = new MyData();
//		myEnemyData = new MyData();
//		myBulletData = new MyData();
		
		myRabbitData = new MyData();
		myBirdData = new MyData();
		myBellData = new MyData();
		
//		myPlane = new MyPlane(BitmapUtil.getBitmapFromRes(R.drawable.rabit_on_ground_left_jump0), 150, 150, true);
//		myPlane.setPosition(CommonUtil.screenWidth/2, CommonUtil.screenHeight - 300);
		
		myRabbit = new MyRabbit(BitmapUtil.getBitmapFromRes(R.drawable.rabit_on_ground_left_jump0), 150, 150, true);
		myRabbit.setPosition(CommonUtil.screenWidth/2, CommonUtil.screenHeight - 300);
		
//		myEnemy = new MyEnemy(BitmapUtil.getBitmapFromRes(R.drawable.enemy), CommonUtil.screenWidth, CommonUtil.screenHeight, true);
//		myPlane.setPosition(CommonUtil.screenWidth/2, CommonUtil.screenHeight - 100);
		addRabbitToData();
		addBirdToData();
		myRabbit = (MyRabbit) myRabbitData.getAllExistPointsIterator().next();
		
//		directionController = new DirectionController();
		
		bellFactory= new BellFactory(myRabbit.y);
		for(int i=0; i<50; i++){
		myBellData.addSprite(bellFactory.createBell((List<MyBell>) myBellData.getAllExistPoints()));
		}
		
		birdFactory = new BirdFactory();
		
		myJumpBtn = new MyButton(BitmapUtil.getBitmapFromRes(R.drawable.button), 150, 150, true);
		myJumpBtn.setPosition(CommonUtil.screenWidth-300, CommonUtil.screenHeight - 300);
	}

	//	@Override
//	public void drawChessboardLines(Canvas canvas, Paint paint) {
//		// TODO Auto-generated method stub
//		paint = new Paint();
//		paint.setColor(Color.LTGRAY);
//		
//		chessBoard.drawChessboardLines(canvas, paint);
//	}
//
//	@Override
//	public void drawAllExistPoints(Canvas canvas) {
//		// TODO Auto-generated method stub
//		chessBoard.drawAllExistPoints(canvas);
//	}
//
//	@Override
//	public void drawPlayerPocessableMovePoints(Canvas canvas) {
//		// TODO Auto-generated method stub
//		chessBoard.drawPlayerPocessableMovePoints(canvas);
//	}

	@Override
	public void movePlane() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveEnemy() {
		// TODO Auto-generated method stub
//		myEnemyData.
	}
	@Override
	public void moveBullet() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addRabbitToData() {
		// TODO Auto-generated method stub
		myRabbitData.addSprite(myRabbit);
	}
	@Override
	public void addBirdToData() {
		// TODO Auto-generated method stub
//		MyEnemy myEnemy = new MyEnemy(BitmapUtil.getBitmapFromRes(R.drawable.bird_left_fly0),150, 150, true);
//		myEnemy.setPosition(50, 50);
//		myEnemyData.addSprite(myEnemy);
	}
	@Override
	public void addBellToData() {
		// TODO Auto-generated method stub
//		myBulletData.addSprite(myRabbit.createBullet());
		
	}
	
	private void checkNeedAddBulletToData(){
//		if(myRabbit.isNeedCreateBullet())
			addBellToData();
	}
	
	private void checkNeedAddEnemyToData(){
//		if(myEnemy.isNeedCreateEnemy())
			addBirdToData();
	}
	
	@Override
	protected void process() {
		// TODO Auto-generated method stub
//		movePlane();
//		moveEnemy();
//		moveBullet();
		moveSprite(myRabbitData.getAllExistPointsIterator(), 0, 0);
		moveSprite(myBirdData.getAllExistPointsIterator(), 0, 0);
//		moveSprite(myEnemyData.getAllExistPointsIterator(), moveRightDestance, moveDownDestance);
//		moveSprite(myBulletData.getAllExistPointsIterator(), 0, moveUpDestance);
	
		checkNeedAddBulletToData();
		checkNeedAddEnemyToData();
		
		Iterator<Sprite> iterator = myBellData.getAllExistPointsIterator();
		while(iterator.hasNext()){
			MyBell myBell = (MyBell) iterator.next();
			if(Rect.intersects(myRabbit.dst, myBell.dst)){
				myBell.bellExplode();
				iterator.remove();
				if(DirectionController.getDirectionType()==DirectionType.LEFT){
//					myRabbit.move(-10, MyRabbit.SPEED_JUMP);
					myRabbit.setPlayerJumping(true, true);
				}else{
//					myRabbit.move(10, MyRabbit.SPEED_JUMP);
					myRabbit.setPlayerJumping(true, true);
				}
				break;
			}
		}
		
		if(!myRabbit.isPlayerJumping()){
			myJumpBtn.setIsVisiable(true);
		}
		
		if(isEnableCreateBire){
			Random random = new Random();
			int birdCount = ((List<Sprite>)myBirdData.getAllExistPoints()).size();
			if( birdCount <= random.nextInt(1)){
				MyBird myBird = birdFactory.createBird();
				myBirdData.addSprite(myBird);
				myBird.setAction();
			}
		}
	}
	
//	private void movePlane() {
//		gameController.movePlane();
//	}
//	
//	private void moveEnemy() {
//		gameController.moveEnemy();
//	}
//	
//	private void moveBullet() {
//		gameController.moveBullet();
//	}

	@Override
	protected void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
//		drawChssboardLines(canvas);
//		drawAllExistPoints(canvas);
//		drawPlayerPocessableMovePoints(canvas);
		drawSprite(myRabbitData.getAllExistPointsIterator(), canvas);
		
		drawSprite(myBellData.getAllExistPointsIterator(), canvas);
		drawSprite(myBirdData.getAllExistPointsIterator(), canvas);
		myJumpBtn.drawSelf(canvas, null);
	}
	
	private void drawSprite(Iterator<Sprite> iterator, Canvas canvas) {
//		gameController.drawPlane(canvas);
		while(iterator.hasNext()){
			iterator.next().drawSelf(canvas, null);
		}
	}
	
	private void moveSprite(Iterator<Sprite> iterator, int dx, int dy) {
//		gameController.drawPlane(canvas);
		while(iterator.hasNext()){
			iterator.next().move(dx, dy);
		}
	}
	
	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
//		super.onTouchEvent(event);
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			Log.e("jumpBtn", "x:"+myJumpBtn.x+"y:"+myJumpBtn.y+"w:"+myJumpBtn.w+"h:"+myJumpBtn.h+"X:"+event.getX()+"Y:"+event.getY());
			if(Utils.inRect(myJumpBtn.x, myJumpBtn.y, myJumpBtn.w, myJumpBtn.h, event.getX(), event.getY()) && myJumpBtn.isVisiable()){
				myJumpBtn.setIsVisiable(false);
//				if(DirectionController.getDirectionType()==DirectionType.LEFT){
					myRabbit.setPlayerJumping(true, false);
//				}else{
//					myRabbit.setPlayerJumping(true);
//				}
					
					isEnableCreateBire = true;
			}
			else if(event.getX() < CommonUtil.screenWidth/2){
				myRabbit.move(-3, 0);
				DirectionController.setDirectionType(DirectionType.LEFT);
			}else{
				myRabbit.move(3, 0);
				DirectionController.setDirectionType(DirectionType.RIGHT);
			}
		}
	}

	@Override
	public void removeRabbit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBell() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBird() {
		// TODO Auto-generated method stub
		
	}
	
//	private void drawAllExistPoints(Canvas canvas) {
//		gameController.drawEnemy(canvas);
//	}
//	
//	private void drawPlayerPocessableMovePoints(Canvas canvas) {
//		gameController.drawBullet(canvas);
//	}

	
	
}
