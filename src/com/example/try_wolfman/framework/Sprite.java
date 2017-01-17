package com.example.try_wolfman.framework;

import java.util.Hashtable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Sprite extends ALayer {
	public int frameIdx;// 当前帧下标
	public int currentFrame = 0;// 当前帧
	public Hashtable<String, SpriteAction> actions;// 动作集合
	public SpriteAction currentAction;// 当前动作

	public boolean isStop = false;
//	public boolean isEnableInterruptAction = false;
	
	public Sprite(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		actions = new Hashtable<String, Sprite.SpriteAction>();// 用Hashtable保存动作集合

	}

	public void setAction(String actionName) {
		
		frameIdx = 0;
		currentAction = actions.get(actionName);// 从动作集合中得到该动作
		currentAction.updateTime = System.currentTimeMillis() + currentAction.frameTime[frameIdx];;
		isStop = false;
		

		
	}

	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		long a = System.currentTimeMillis();
		if (currentAction != null) {
			if(currentAction.frames!=null){
				currentFrame = currentAction.frames[frameIdx];// 获取当前需要的帧
			}else{
				bitmap = currentAction.bitmapFrames[frameIdx];
			}
		} // 截取图片中需要的帧
		src.left = currentFrame * w;// 左端宽度：当前帧乘上帧的宽度
		src.top = 0;// 上端高度：0
		src.right = src.left + w;// 右端宽度：左端宽度加上帧的宽度
		src.bottom = h;// 下端高度为帧的高度 // 绘制在界面上，取中心点绘制
//		dst.left = (int) x - w / 2;
//		dst.top = (int) y - h / 2;
		dst.left = (int) x ;
		dst.top = (int) y ;
		dst.right = dst.left + w;
		dst.bottom = dst.top + h;
		canvas.drawBitmap(bitmap, src, dst, paint);// 绘制当前帧
		if (currentAction != null) {
			if(currentAction.frames!=null){
				currentAction.nextFrame();// 绘制下一帧
			}else{
				currentAction.nextBitmap();// 绘制下一帧
			}		
		}
		a = System.currentTimeMillis() - a;
		Log.e("time2", a+""+"XX"+System.currentTimeMillis());
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
		addAction(name, bitmapFrames, frameTime, true);
	}
	
	public void addAction(String name, Bitmap[] bitmapFrames, int[] frameTime, boolean isLoop) {
		SpriteAction sp = new SpriteAction();// 创建SpiteAction的对象
		sp.bitmapFrames = bitmapFrames;// 当前帧的数量，用下标表示
		sp.frameTime = frameTime;// 每一帧切换的时间
		sp.isLoop = isLoop;
		sp.name = name;
		actions.put(name, sp);// 将名字为"name"的动作集合，值为sp的动作集合放进acitons中
	}

	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	} // 精灵动作类
	
	public String getActionName(){
		return currentAction.name;
	}
	
	public void forceToNextFrameBitmap(){
		currentAction.forceToNextBitmap();
	}

	class SpriteAction {
		public int[] frames;// 该动作的帧序列
		public int[] frameTime;// 帧序列中每一帧切换对应的时间

		public Bitmap[] bitmapFrames;
		
		public boolean isLoop;
		
		public String name;
		
		private long updateTime;// 记录上次失效时间

		public void nextFrame() {
			if (System.currentTimeMillis() > updateTime) {
				frameIdx++;// 帧下标增加
				frameIdx %= frames.length;
				updateTime = System.currentTimeMillis() + frameTime[frameIdx];// 切换下一帧所需要的时间
			}
		}
		
		public void nextBitmap(){			
			if (System.currentTimeMillis() > updateTime && !isStop) {
				frameIdx++;// 帧下标增加
				frameIdx %= bitmapFrames.length;
				
				if(!isLoop && frameIdx==0){
					isStop = true;		
				}else{
					bitmap = bitmapFrames[frameIdx];
					updateTime = System.currentTimeMillis() + frameTime[frameIdx];// 切换下一帧所需要的时间
				}
				
			}
		}
		
		public void forceToNextBitmap(){
			frameIdx++;// 帧下标增加
			frameIdx %= bitmapFrames.length;
			if(!isLoop && frameIdx==0){
				isStop = true;		
			}
		}
	}
}
