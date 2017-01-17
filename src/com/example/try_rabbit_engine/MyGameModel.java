package com.example.try_rabbit_engine;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

public class MyGameModel extends GameModel implements IMoveEvent,
		IAddAndRemoveEvent {
	// private MyData myPlaneData;
	// private MyData myEnemyData;
	// private MyData myBulletData;

	private MyData myRabbitData;
	private MyData myBirdData;
	private MyData myBellData;
//	private MyData myBellExplodeData;

	// private MyPlane myPlane;
	// private MyEnemy myEnemy;
	// private Sprite myBullet;

	private MyRabbit myRabbit;
	private MyBird myBird;
	private MyBell myBell;

	private int moveUpDestance = -5;
	private int moveDownDestance = 5;
	private int moveRightDestance = 5;
	private int moveLeftDestance = -5;

	// private DirectionController directionController;

	private MyButton myJumpBtn;
	private BellFactory bellFactory;
	private BirdFactory birdFactory;

	private boolean isEnableCreateBird = false;
	
	private boolean isGamePrepareToStop = false;
	
	private final int INIT_MAX_BIRD_NUMBER = 1;
	private int currentMaxBirdNum = INIT_MAX_BIRD_NUMBER;

	private int explodedBellCount = 0;
	
	private int processCount = 0;
	
	private final int INCREASE_BIRD_MAX_NUM_BY_HOW_MANY_EXPLODE_BELL = 20;
	
	private String maxHeightMeter;
	
	public MyGameModel(Context context, Data data) {
		super(context, data);
		// TODO Auto-generated constructor stub
		// myPlaneData = new MyData();
		// myEnemyData = new MyData();
		// myBulletData = new MyData();

		myRabbitData = new MyData();
		myBirdData = new MyData();
		myBellData = new MyData();

		// myPlane = new
		// MyPlane(BitmapUtil.getBitmapFromRes(R.drawable.rabit_on_ground_left_jump0),
		// 150, 150, true);
		// myPlane.setPosition(CommonUtil.screenWidth/2, CommonUtil.screenHeight
		// - 300);

		myRabbit = new MyRabbit(
				BitmapUtil
						.getBitmapFromRes(R.drawable.rabit_on_ground_left_jump0),
				150, 150, true);
		myRabbit.setPosition(CommonUtil.screenWidth / 2,
				CommonUtil.screenHeight - 300);

		// myEnemy = new MyEnemy(BitmapUtil.getBitmapFromRes(R.drawable.enemy),
		// CommonUtil.screenWidth, CommonUtil.screenHeight, true);
		// myPlane.setPosition(CommonUtil.screenWidth/2, CommonUtil.screenHeight
		// - 100);
		addRabbitToData();
		addBirdToData();
		myRabbit = (MyRabbit) myRabbitData.getAllExistPointsIterator().next();

		// directionController = new DirectionController();

		bellFactory = new BellFactory(myRabbit.getY(), (List<MyBell>) myBellData.getAllExistPoints());
		for (int i = 0; i < 70; i++) {
			myBellData.addSprite(bellFactory
					.createBell((List<MyBell>) myBellData.getAllExistPoints()));
		}

		birdFactory = new BirdFactory();

		myJumpBtn = new MyButton(
				BitmapUtil.getBitmapFromRes(R.drawable.button), 150, 150, true);
		myJumpBtn.setPosition(CommonUtil.screenWidth - 300,
				CommonUtil.screenHeight - 300);
	}

	// @Override
	// public void drawChessboardLines(Canvas canvas, Paint paint) {
	// // TODO Auto-generated method stub
	// paint = new Paint();
	// paint.setColor(Color.LTGRAY);
	//
	// chessBoard.drawChessboardLines(canvas, paint);
	// }
	//
	// @Override
	// public void drawAllExistPoints(Canvas canvas) {
	// // TODO Auto-generated method stub
	// chessBoard.drawAllExistPoints(canvas);
	// }
	//
	// @Override
	// public void drawPlayerPocessableMovePoints(Canvas canvas) {
	// // TODO Auto-generated method stub
	// chessBoard.drawPlayerPocessableMovePoints(canvas);
	// }

	@Override
	public void movePlane() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveEnemy() {
		// TODO Auto-generated method stub
		// myEnemyData.
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
		// MyEnemy myEnemy = new
		// MyEnemy(BitmapUtil.getBitmapFromRes(R.drawable.bird_left_fly0),150,
		// 150, true);
		// myEnemy.setPosition(50, 50);
		// myEnemyData.addSprite(myEnemy);
	}

	@Override
	public void addBellToData() {
		// TODO Auto-generated method stub
		// myBulletData.addSprite(myRabbit.createBullet());

	}

	private void checkNeedAddBulletToData() {
		// if(myRabbit.isNeedCreateBullet())
		addBellToData();
	}

	private void checkNeedAddEnemyToData() {
		// if(myEnemy.isNeedCreateEnemy())
		addBirdToData();
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		// movePlane();
		// moveEnemy();
		// moveBullet();
		
		if(isGamePrepareToStop)
			isGameRun = false;
		
//		moveSprite(myBirdData.getAllExistPointsIterator(), 0, 0);
		// moveSprite(myEnemyData.getAllExistPointsIterator(),
		// moveRightDestance, moveDownDestance);
		// moveSprite(myBulletData.getAllExistPointsIterator(), 0,
		// moveUpDestance);

		checkNeedAddBulletToData();
		checkNeedAddEnemyToData();

		// Iterator<Sprite> iterator = myBellData.getAllExistPointsIterator();
		// while(iterator.hasNext()){
		// MyBell myBell = (MyBell) iterator.next();
		// if(Rect.intersects(myRabbit.dst, myBell.dst)){
		// myBell.bellExplode();
		// iterator.remove();
		// if(DirectionController.getDirectionType()==DirectionType.LEFT){
		// // myRabbit.move(-10, MyRabbit.SPEED_JUMP);
		// myRabbit.setPlayerJumping(true, true);
		// }else{
		// // myRabbit.move(10, MyRabbit.SPEED_JUMP);
		// myRabbit.setPlayerJumping(true, true);
		// }
		// break;
		// }
		// }

		moveSprite(myRabbitData.getAllExistPointsIterator(), 0, 0);
		if(myRabbit.isDownBellowScreen())
			isGamePrepareToStop = true;
		
		MyBell collisionBell;
		if (!myRabbit.isUpToScreenMid) {
			collisionBell = (MyBell) checkSpriteCollision((List<Sprite>) myBellData
					.getAllExistPoints());
		} else {
			collisionBell = (MyBell) checkSpriteCollisionAndMoveAndCreateRemoveInstance(
					(List<Sprite>) myBellData.getAllExistPoints(), 0,
					(int) myRabbit.speedY);
		}

		if (collisionBell != null) {
			collisionBell.bellExplode();
			explodedBellCount++;
//			((List<Sprite>) myBellData.getAllExistPoints())
//					.remove(collisionBell);
			if (DirectionController.getDirectionType() == DirectionType.LEFT) {
				// myRabbit.move(-10, MyRabbit.SPEED_JUMP);
				myRabbit.setPlayerJumping(true, true);
			} else {
				// myRabbit.move(10, MyRabbit.SPEED_JUMP);
				myRabbit.setPlayerJumping(true, true);
			}
		}

		if (!myRabbit.isPlayerJumping()) {
			myJumpBtn.setIsVisiable(true);
		}

		calcuCurrentMaxBirdNum();
		processCount++;
		if (isEnableCreateBird && processCount>30) {
			processCount=0;
			Random random = new Random();
			int birdCount = ((List<Sprite>) myBirdData.getAllExistPoints())
					.size();
			if (birdCount <= random.nextInt(currentMaxBirdNum)) {
				MyBird myBird = birdFactory.createBird();
				myBirdData.addSprite(myBird);
				myBird.setAction();
			}
		}
		
		MyBird collisionBird;
		if (!myRabbit.isUpToScreenMid) {
//			collisionBird = (MyBird) checkSpriteCollision((List<Sprite>) myBirdData
//					.getAllExistPoints());
			collisionBird = (MyBird) checkSpriteCollisionAndMoveAndCreateRemoveInstance(
					(List<Sprite>) myBirdData.getAllExistPoints(), 0, 0);
		} else {
			collisionBird = (MyBird) checkSpriteCollisionAndMoveAndCreateRemoveInstance(
					(List<Sprite>) myBirdData.getAllExistPoints(), 0,
					(int) myRabbit.speedY);
		}
		if (collisionBird != null) {
			isGamePrepareToStop = true;
		}
		
		for(MyBell bell : (List<MyBell>)myBellData.getAllExistPoints()){
			if(bell.isExploded){
				((List<MyBell>)myBellData.getAllExistPoints()).remove(bell);
				break;
			}
		}
		
		maxHeightMeter = getMaxHeightMeter(myRabbit.getMaxHeight());
	}

	private Sprite checkSpriteCollision(List<Sprite> movingObjectUtilarrayList) {
		return checkSpriteCollisionAndMoveAndCreateRemoveInstance(
				movingObjectUtilarrayList, 0, 0, false, false);
	}

	private Sprite checkSpriteCollisionAndMove(
			List<Sprite> movingObjectUtilarrayList, int dx, int dy) {
		return checkSpriteCollisionAndMoveAndCreateRemoveInstance(
				movingObjectUtilarrayList, dx, dy, true, false);
	}

	private Sprite checkSpriteCollisionAndMoveAndCreateRemoveInstance(
			List<Sprite> movingObjectUtilarrayList, int dx, int dy) {
		return checkSpriteCollisionAndMoveAndCreateRemoveInstance(
				movingObjectUtilarrayList, dx, dy, true, true);
	}

	private Sprite checkSpriteCollisionAndMoveAndCreateRemoveInstance(
			List<Sprite> movingObjectUtilarrayList, int dx, int dy,
			boolean isMoveSprite, boolean isSpriteCreateRemove) {

		// ArrayList<CarUtil> carUtils = carLines.get(carLinePosition);
		
		boolean isCollision = false;
		Sprite collisionSprite = null;
		for (int carPosition = 0; carPosition < movingObjectUtilarrayList
				.size(); carPosition++) {
			Sprite movingObjectUtil = movingObjectUtilarrayList
					.get(carPosition);

			if (!isCollision && movingObjectUtil.canCollision) {
				isCollision = Rect.intersects(myRabbit.dst,
						movingObjectUtil.dst);
				if (isCollision) {
					collisionSprite = movingObjectUtil;
					if (!isMoveSprite)
						break;
				}
			}
			if (isMoveSprite){
				if(!movingObjectUtil.canCollision)
					Log.e("dx", dx +" " + dy);
				moveSprite(movingObjectUtil, dx, dy);
			}
			if (isSpriteCreateRemove){
//				boolean isNeedCreateNewInstance = false;
//				boolean isNeedRemoveInstance = false;
//				checkNeedCreateRemoveInstanceAndDoWithNeed(carPosition,
//						movingObjectUtil, movingObjectUtilarrayList);
				checkNeedCreateInstanceAndDoWithNeed(carPosition, movingObjectUtil, movingObjectUtilarrayList);
				if(checkNeedRemoveInstanceAndDoWithNeed(carPosition, movingObjectUtil, movingObjectUtilarrayList))
					carPosition--;
					
			}
		}

		return collisionSprite;
	}
	
	private Sprite checkSpriteCollisionAndMoveAndCreateRemoveInstance2(
			List<Sprite> movingObjectUtilarrayList, int dx, int dy,
			boolean isMoveSprite, boolean isSpriteCreateRemove) {

		// ArrayList<CarUtil> carUtils = carLines.get(carLinePosition);
		
		boolean isCollision = false;
		Sprite collisionSprite = null;
		for (int carPosition = 0; carPosition < movingObjectUtilarrayList
				.size(); carPosition++) {
			Sprite movingObjectUtil = movingObjectUtilarrayList
					.get(carPosition);

			if (!isCollision) {
				isCollision = Rect.intersects(myRabbit.dst,
						movingObjectUtil.dst);
				if (isCollision) {
					collisionSprite = movingObjectUtil;
					if (!isMoveSprite)
						break;
				}
			}
			if (isMoveSprite)
				moveSprite(movingObjectUtil, dx, dy);
			if (isSpriteCreateRemove){
				checkNeedCreateRemoveInstanceAndDoWithNeed(carPosition,
						movingObjectUtil, movingObjectUtilarrayList);
					
			}
		}

		return collisionSprite;
	}

	private void moveSprite(Sprite movingObjectUtil, int dx, int dy) {
		movingObjectUtil.move(dx, dy);
	}

	private void checkNeedCreateRemoveInstanceAndDoWithNeed(int carPosition,
			Sprite movingObjectUtil, List<Sprite> movingObjectUtilarrayList) {
		int firstCarPosition = 0;
		int LastCatPosition = movingObjectUtilarrayList.size() - 1;
		if (carPosition == LastCatPosition)
			checkNeedCreateInstance(movingObjectUtil, movingObjectUtilarrayList);
		if (carPosition == firstCarPosition) {
			checkNeedRemoveInstance(movingObjectUtil, movingObjectUtilarrayList);		
		}		
	}
	
	private boolean checkNeedCreateInstanceAndDoWithNeed(int carPosition,
			Sprite movingObjectUtil, List<Sprite> movingObjectUtilarrayList) {
		boolean isNeedCreateNewInstance = false;
		int LastCatPosition = movingObjectUtilarrayList.size() - 1;
		if (carPosition == LastCatPosition)
			isNeedCreateNewInstance = checkNeedCreateInstance(movingObjectUtil, movingObjectUtilarrayList);
		if (isNeedCreateNewInstance) {
			createNewInstance(movingObjectUtilarrayList);
		}
		return isNeedCreateNewInstance;
	}
	
	private boolean checkNeedRemoveInstanceAndDoWithNeed(int carPosition,
			Sprite movingObjectUtil, List<Sprite> movingObjectUtilarrayList) {
		boolean isNeedRemoveInstance = false;
		int firstCarPosition = 0;
		if (carPosition == firstCarPosition)
			isNeedRemoveInstance = checkNeedRemoveInstance(movingObjectUtil);
		if (isNeedRemoveInstance) {
			removeInstance(movingObjectUtilarrayList);
		}
		return isNeedRemoveInstance;
	}
	
	private boolean checkNeedCreateInstance(Sprite movingObjectUtil, List<Sprite> movingObjectUtilarrayList){
//		boolean isNeedCreateNewInstance = false;
//		isNeedCreateNewInstance = movingObjectUtil
//				.isNeedCreateNewInstance();
//		if (isNeedCreateNewInstance) {
//			createNewInstance(movingObjectUtilarrayList);
//		}
		return movingObjectUtil
				.isNeedCreateNewInstance();
	}
	
	private void createNewInstance(List<Sprite> movingObjectUtilarrayList){
		movingObjectUtilarrayList
		.add(createNewMoveObject((Sprite) movingObjectUtilarrayList
				.get(0)));
	}
	
	private void checkNeedRemoveInstance(Sprite movingObjectUtil, List<Sprite> movingObjectUtilarrayList){
		boolean isNeedRemoveInstance = false;
		isNeedRemoveInstance = movingObjectUtil.isNeedRemoveInstance();
		if (isNeedRemoveInstance) {
			removeInstance(movingObjectUtilarrayList);
		}
	}
	
	private boolean checkNeedRemoveInstance(Sprite movingObjectUtil){
		return movingObjectUtil.isNeedRemoveInstance();
	}
	
	private void removeInstance(List<Sprite> movingObjectUtilarrayList){
		movingObjectUtilarrayList.remove(movingObjectUtilarrayList
				.get(0));
	}

	private Sprite createNewMoveObject(Sprite lastMovingObjectUtil) {
		Sprite newMovingObjectUtil = null;
		if (lastMovingObjectUtil instanceof MyBird) {
			newMovingObjectUtil = birdFactory.createBird();
		}else if(lastMovingObjectUtil instanceof MyBell){
			newMovingObjectUtil = bellFactory.createBell();
		}
		return newMovingObjectUtil;
	}

	// private void movePlane() {
	// gameController.movePlane();
	// }
	//
	// private void moveEnemy() {
	// gameController.moveEnemy();
	// }
	//
	// private void moveBullet() {
	// gameController.moveBullet();
	// }

	@Override
	protected void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// drawChssboardLines(canvas);
		// drawAllExistPoints(canvas);
		// drawPlayerPocessableMovePoints(canvas);
		drawSprite(myRabbitData.getAllExistPointsIterator(), canvas);

		drawSprite(myBellData.getAllExistPointsIterator(), canvas);
		List<Sprite> a = (List<Sprite>) myBellData.getAllExistPoints();
		for(Sprite sprite : a){
			if(!sprite.canCollision){
			Log.e("b", sprite.w +" " + sprite.h);
			Log.e("bb", sprite.centerX +" " + sprite.centerY);
			}
		}
		drawSprite(myBirdData.getAllExistPointsIterator(), canvas);
		myJumpBtn.drawSelf(canvas, null);
		
		drawExplodeBellCount(canvas);
		drawMaxHeightMeter(canvas);
		
	}

	private void drawSprite(Iterator<Sprite> iterator, Canvas canvas) {
		// gameController.drawPlane(canvas);
		while (iterator.hasNext()) {
			iterator.next().drawSelf(canvas, null);
		}
	}

	private void drawExplodeBellCount(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(40);
		canvas.drawText(explodedBellCount+"", 50, 50, paint);
	}
	
	private void drawMaxHeightMeter(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(40);
		canvas.drawText(maxHeightMeter+"", 150, 50, paint);
	}
	
	private void moveSprite(Iterator<Sprite> iterator, int dx, int dy) {
		// gameController.drawPlane(canvas);
		while (iterator.hasNext()) {
			iterator.next().move(dx, dy);
		}
	}

	private void calcuCurrentMaxBirdNum(){
		currentMaxBirdNum = explodedBellCount/INCREASE_BIRD_MAX_NUM_BY_HOW_MANY_EXPLODE_BELL +1 ;
	}
	
	private String getMaxHeightMeter(float maxHeight) {
		DecimalFormat df=new DecimalFormat("#.##");
		String maxHeightMeter = df.format(maxHeight/200);
		return maxHeightMeter;
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// super.onTouchEvent(event);
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.e("jumpBtn", "x:" + myJumpBtn.getX() + "y:" + myJumpBtn.getY() + "w:"
					+ myJumpBtn.w + "h:" + myJumpBtn.h + "X:" + event.getX()
					+ "Y:" + event.getY());
			if (Utils.inRect(myJumpBtn.getX(), myJumpBtn.getY(), myJumpBtn.w,
					myJumpBtn.h, event.getX(), event.getY())
					&& myJumpBtn.isVisiable()) {
				myJumpBtn.setIsVisiable(false);
				// if(DirectionController.getDirectionType()==DirectionType.LEFT){
				myRabbit.setPlayerJumping(true, false);
				// }else{
				// myRabbit.setPlayerJumping(true);
				// }

				isEnableCreateBird = true;
			} else if (event.getX() < CommonUtil.screenWidth / 2) {
				myRabbit.move(-3, 0);
				DirectionController.setDirectionType(DirectionType.LEFT);
			} else {
				myRabbit.move(3, 0);
				DirectionController.setDirectionType(DirectionType.RIGHT);
			}
		}
	}
	
	public void isGamePrepareToStop(){
		isGamePrepareToStop = true;
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

	// private void drawAllExistPoints(Canvas canvas) {
	// gameController.drawEnemy(canvas);
	// }
	//
	// private void drawPlayerPocessableMovePoints(Canvas canvas) {
	// gameController.drawBullet(canvas);
	// }

}
