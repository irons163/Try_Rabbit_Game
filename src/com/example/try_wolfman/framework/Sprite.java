package com.example.try_wolfman.framework;

import java.util.Hashtable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

public class Sprite extends ALayer {
	public int frameIdx;// 当前帧下标
	public int currentFrame = 0;// 當前幀
	public Hashtable<String, SpriteAction> actions;// 動作集合
	public SpriteAction currentAction;// 當前動作

	public boolean isStop = false;
//	public boolean isEnableInterruptAction = false;
	public float scale = 1.0f;
	public boolean canCollision = true;
	
	public Sprite(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		actions = new Hashtable<String, Sprite.SpriteAction>();// 用Hashtable保存动作集合
	}
	
	public Sprite(Bitmap bitmap, int scale, boolean autoAdd) {
		super(bitmap, 0, 0, autoAdd);
		this.scale = scale;
		Matrix matrix = new Matrix();
	     matrix.postScale(scale, scale);
	      
	     Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		setWidth(resizedBitmap.getWidth());
		setHeight(resizedBitmap.getHeight());
	     actions = new Hashtable<String, Sprite.SpriteAction>();// 用Hashtable保存动作集合

	}
	
	public Sprite(Bitmap bitmap, int resId, int w, int h, float scale, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		this.scale = scale;
		Matrix matrix = new Matrix();
	     matrix.postScale(scale, scale);
	      
	     Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		actions = new Hashtable<String, Sprite.SpriteAction>();// 用Hashtable保存动作集合

	}

	public void setAction(String actionName) {
		frameIdx = 0;
		currentAction = actions.get(actionName);// 从动作集合中得到该动作
		currentAction.updateTime = System.currentTimeMillis() + currentAction.frameTime[frameIdx];;
		scale = currentAction.scale;
		isStop = false;			
	}

	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		long a = System.currentTimeMillis();
//		if (currentAction != null) {
//			if(currentAction.frames!=null){
//				currentFrame = currentAction.frames[frameIdx];// 获取当前需要的帧
//			}else{
//				bitmap = currentAction.bitmapFrames[frameIdx];
//			}
//		} // 截取图片中需要的帧
		src.left = (int) (currentFrame * w * scale);// 左端宽度：当前帧乘上帧的宽度
		src.top = 0;// 上端高度：0
		src.right = (int) (src.left + w * scale);// 右端宽度：左端宽度加上帧的宽度
		src.bottom = h;// 下端高度为帧的高度 // 绘制在界面上，取中心点绘制
//		dst.left = (int) x - w / 2;
//		dst.top = (int) y - h / 2;
		dst.left = (int) (centerX - w / 2);
		dst.top = (int) (centerY - h / 2);
		dst.right = (int) (dst.left + w * scale);
		dst.bottom = (int) (dst.top + h * scale);
		canvas.drawBitmap(bitmap, src, dst, paint);// 绘制当前帧
		if (currentAction != null) {
			if(currentAction.frames!=null){
				currentAction.nextFrame();// 绘制下一帧
			}else{
				currentAction.nextBitmap();// 绘制下一帧
			}		
		}
		a = System.currentTimeMillis() - a;
//		Log.e("time2", a+""+"XX"+System.currentTimeMillis());
	}

	/**
	 * * 添加一个动作集合的方法 * * @param name * @param frames * @param frameTime
	 * */
	public void addAction(String name, int[] frames, int[] frameTime) {
		SpriteAction sp = new SpriteAction();// 创建SpiteAction的对象
		sp.frames = frames;// 当前帧的数量，用下标表示
		sp.frameTime = frameTime;// 每一帧切换的时间
		sp.name = name;
		actions.put(name, sp);// 将名字为"name"的动作集合，值为sp的动作集合放进acitons中
	}
	
	public void addAction(String name, Bitmap[] bitmapFrames, int[] frameTime) {
		addAction(name, bitmapFrames, frameTime, 1.0f, true, new DefaultActionListener());
	}
	
	public void addAction(String name, Bitmap[] bitmapFrames, int[] frameTime, boolean isLoop) {
		addAction(name, bitmapFrames, frameTime, 1.0f, isLoop, new DefaultActionListener());
	}
	
	public void addAction(String name, Bitmap[] bitmapFrames, int[] frameTime, boolean isLoop, IActionListener actionListener) {
		addAction(name, bitmapFrames, frameTime, 1.0f, isLoop, actionListener);
	}
	
	public void addAction(String name, Bitmap[] bitmapFrames, int[] frameTime, float scale, boolean isLoop, IActionListener actionListener) {
		SpriteAction sp = new SpriteAction();// 创建SpiteAction的对象
		sp.bitmapFrames = bitmapFrames;// 当前帧的数量，用下标表示
		sp.frameTime = frameTime;// 每一帧切换的时间
		sp.isLoop = isLoop;
		sp.name = name;
		sp.scale = scale;
		sp.actionListener = actionListener;
		actions.put(name, sp);// 将名字为"name"的动作集合，值为sp的动作集合放进acitons中
	}

	public void move(int dx, int dy) {
		setX(getCenterX() + dx - w/2);
		setY(getCenterY() + dy - h/2);
	} // 精灵动作类
	
	public String getActionName(){
		return currentAction.name;
	}
	
	public void forceToNextFrameBitmap(){
		currentAction.forceToNextBitmap();
	}
	
	public boolean isNeedCreateNewInstance(){
		return false;
	}
	
	public boolean isNeedRemoveInstance(){
		return getX()<0 || getX() > CommonUtil.screenWidth || getY() < 0 || getY() > CommonUtil.screenHeight;
	}

	class SpriteAction {
		public int[] frames;// 该动作的帧序列
		public int[] frameTime;// 帧序列中每一帧切换对应的时间

		public Bitmap[] bitmapFrames;
		
		public boolean isLoop;
		
		public String name;
		
		private long updateTime;// 记录上次失效时间

		public float scale;
		
		public IActionListener actionListener = new DefaultActionListener();
		
		public void nextFrame() {
			if (System.currentTimeMillis() > updateTime) {
				frameIdx++;// 帧下标增加
				frameIdx %= frames.length;
				updateTime = System.currentTimeMillis() + frameTime[frameIdx];// 切换下一帧所需要的时间
			}
		}
		
		public void nextBitmap(){			
			if (System.currentTimeMillis() > updateTime && !isStop) {
				actionListener.beforeChangeFrame(frameIdx+1);
//				frameIdx++;// 帧下标增加
//				frameIdx %= bitmapFrames.length;
				
				if(!isLoop && frameIdx==bitmapFrames.length-1){
					bitmap = bitmapFrames[frameIdx];
					isStop = true;
					actionListener.actionFinish();
				}else{
					bitmap = bitmapFrames[frameIdx];
					
					frameIdx++;// 帧下标增加
					frameIdx %= bitmapFrames.length;
					
					updateTime = System.currentTimeMillis() + frameTime[frameIdx];// 切换下一帧所需要的时间
					
					int w = bitmap.getWidth();
					int h = bitmap.getHeight();
					
					setWidth(bitmap.getWidth());
					setHeight(bitmap.getHeight());
					int periousId = frameIdx-1<0 ? bitmapFrames.length+(frameIdx-1) : frameIdx-1;
					actionListener.afterChangeFrame(periousId);
					
				}

			}
		}
		
		public void forceToNextBitmap(){
			bitmap = bitmapFrames[frameIdx];
			frameIdx++;// 帧下标增加
			frameIdx %= bitmapFrames.length;
			if(!isLoop && frameIdx==0){
				isStop = true;		
			}
		}
	}
}
