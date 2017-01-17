package com.example.try_wolfman.framework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;

/**
 * * 组件的管理类,用于存放组件，绘制所有组件，添加一个组件，删除一个组件， 插入一组件 * * * @author Administrator *
 */
public class LayerManager {
	public static Vector<ALayer> vec = new Vector<ALayer>(); // Vector对象用于存放所有组件

	public static List<List<ALayer>> layerLevelList = new ArrayList<List<ALayer>>();

	/** * 绘制所有组件的方法 * * @param canvas * @param paint */
	public static void drawLayerManager(Canvas canvas, Paint paint) {
		for (int i = 0; i < vec.size(); i++) {
			vec.elementAt(i).drawSelf(canvas, paint);// 把存在于Vector对象中的组件绘制出来
		}
	}

	/** * 添加一个组件的方法 * * @param layer */
	// public static synchronized void addLayer(ALayer layer) {
	// vec.add(layer);// 在Vector对象中添加此组件
	// }

	/** * 删除一个组件的方法 * * @param layer */
	// public static synchronized void deleteLayer(ALayer layer) {
	// vec.remove(layer);// 在Vector对象中删除此组件
	// }

	/**
	 * * 在before指定的位置插入layer，原来对象以及此后的对象依次往后顺延。 * * @param layer * @param before
	 */

	public static void insert(ALayer layer, ALayer before) {
		for (int i = 0; i < vec.size(); i++) {
			// 遍历Vector对象
			if (before == vec.elementAt(i)) {
				vec.insertElementAt(layer, i);// 在before对象前面插入layer,该对象位于before之上
				return;
			}
		}
	}

	public static void initLayerManager() {
		// TODO Auto-generated constructor stub
		layerLevelList.add(new ArrayList<ALayer>());
	}

	public static synchronized List<ALayer> getLayerByLayerLevel(int layerLevel) {
		return layerLevelList.get(layerLevel);
	}

	public static synchronized void addLayer(ALayer layer) {
		layerLevelList.get(0).add(layer);// 在Vector对象中添加此组件
	}

	public static synchronized void addLayerByLayerLevel(ALayer layer,
			int layerLevel) {
		List<ALayer> layersByTheSameLevel = layerLevelList.get(layerLevel);
		layersByTheSameLevel.add(layer);
	}

	public static void insertLayer(ALayer layer, ALayer before) {
		List<ALayer> layersByTheSameLevel = layerLevelList.get(0);
		for (int i = 0; i < layersByTheSameLevel.size(); i++) {
			// 遍历Vector对象
			if (before == layersByTheSameLevel.get(i)) {
				layersByTheSameLevel.add(i, layer);// 在before对象前面插入layer,该对象位于before之上
				return;
			}
		}
	}

	public static void insertLayerByLayerLevel(ALayer layer, ALayer before,
			int layerLevel) {
		List<ALayer> layersByTheSameLevel = layerLevelList.get(layerLevel);
		for (int i = 0; i < layersByTheSameLevel.size(); i++) {
			// 遍历Vector对象
			if (before == layersByTheSameLevel.get(i)) {
				layersByTheSameLevel.add(i, layer);// 在before对象前面插入layer,该对象位于before之上
				return;
			}
		}
	}

	public static synchronized void changeLayerToNewLayerLevel(ALayer layer,
			int newLevel) {
		int offsetLayerLevel = newLevel - layer.layerLevel;
		for (List<ALayer> layersByTheSameLevel : layerLevelList) {
			int layerIndex = layersByTheSameLevel.indexOf(layer);
			if (layerIndex >= 0) {
				layersByTheSameLevel.remove(layerIndex);
				layerLevelList.get(newLevel).add(layer);
				layer.layerLevel = newLevel;
				layer.moveAllChild(offsetLayerLevel);
				break;
			}
		}
	}

	public static synchronized void changeLayerToNewLayerLevel(ALayer layer,
			int oldLevel, int newLevel) {
		int offsetLayerLevel = newLevel - oldLevel;
		for (List<ALayer> layersByTheSameLevel : layerLevelList) {
			int layerIndex = layersByTheSameLevel.indexOf(layer);
			if (layerIndex >= 0) {
				layersByTheSameLevel.remove(layerIndex);
				layerLevelList.get(newLevel).add(layer);
				layer.layerLevel = newLevel;
				layer.moveAllChild(offsetLayerLevel);
				break;
			}
		}
	}

	public static synchronized void exchangeLayerLevel(int layerLevel1,
			int layerLevel2) {
		List<ALayer> temp = layerLevelList.get(layerLevel1);
		layerLevelList.set(layerLevel1, layerLevelList.get(layerLevel2));
		layerLevelList.set(layerLevel2, temp);
	}

	public static synchronized void deleteLayer(ALayer layer) {
		layerLevelList.get(0).remove(layer);// 在Vector对象中删除此组件
	}

	public static synchronized void deleteLayerByLayerLevel(ALayer layer,
			int layerLevel) {
		layerLevelList.get(layerLevel).remove(layer);// 在Vector对象中删除此组件
	}

	public static synchronized void drawLayers(Canvas canvas, Paint paint) {
		for (List<ALayer> layersByTheSameLevel : layerLevelList) {
			for (ALayer layer : layersByTheSameLevel) {
				layer.drawSelf(canvas, paint);
			}
		}
	}

	public static void drawLayersBySpecificLevel(Canvas canvas, Paint paint,
			int level) {
		List<ALayer> layersByTheSameLevel = layerLevelList.get(level);
		for (ALayer layer : layersByTheSameLevel) {
			layer.drawSelf(canvas, paint);
		}
	}

	public static synchronized void increaseNewLayer() {
		layerLevelList.add(new ArrayList<ALayer>());
	}

	public static List<ALayer> getLayersBySpecificLevel(int level) {
		return layerLevelList.get(level);
		// for(ALayer layer : layersByTheSameLevel){
		// layer.drawSelf(canvas, paint);
		// }
	}

	public static synchronized RectF checkClickSmallView(MotionEvent event){
		boolean isFirstRectF=true;
		RectF maxRangeRectF = new RectF();
		Exit:
		for(List<ALayer> layersByTheSameLevel : layerLevelList){
			for(ALayer layer : layersByTheSameLevel){
				if(layer.dst.contains((int)event.getX(), (int)event.getY())){
					ALayer rootLayer = getRootParent(layer);
					
					if(rootLayer!=layer){
						if(isFirstRectF){
							maxRangeRectF = new RectF(rootLayer.dst);
							isFirstRectF=false;
						}
						Iterator iterator = rootLayer.createIterator();
						while(iterator.hasNext()){
							ALayer childLayer = (ALayer) iterator.next();
								setMaxRangeRectF(maxRangeRectF, childLayer.dst);
						}
					}else{
						if(isFirstRectF){
							maxRangeRectF.set(layer.dst.left, layer.dst.top, layer.dst.right, layer.dst.bottom);
							isFirstRectF=false;
						}
						Iterator iterator = rootLayer.createIterator();
						while(iterator.hasNext()){
							ALayer childLayer = (ALayer) iterator.next();
								setMaxRangeRectF(maxRangeRectF, childLayer.dst);
						}
					}
					break Exit;
				}
			}
		}
	return maxRangeRectF;
	}
	
	private static void setMaxRangeRectF(RectF maxRangeRectF, Rect childRfct){
		if(childRfct.top < maxRangeRectF.top){
			maxRangeRectF.top = childRfct.top;
		}else if(childRfct.bottom > maxRangeRectF.bottom){
			maxRangeRectF.bottom = childRfct.bottom;
		}else if(childRfct.left < maxRangeRectF.left){
			maxRangeRectF.left = childRfct.left;
		}else if(childRfct.right > maxRangeRectF.right){
			maxRangeRectF.right = childRfct.right;
		}
	}
	
	public static synchronized void drawGroupRange(Canvas canvas, Paint paint, RectF groupRangeRectF) {
		canvas.drawRect(groupRangeRectF, paint);
	}
	
//	public static synchronized RectF getRootParent(ALayer layer){
//		RectF rootRectF = null;
//		if(layer.parent!=null){
//			rootRectF = getRootParent(layer.parent);
//		}
//		return rootRectF;
//	}
	
	public static synchronized ALayer getRootParent(ALayer layer){
		ALayer rootLayer = null;
		if(layer.parent!=null){
			rootLayer = getRootParent(layer.parent);
		}else{
			rootLayer = layer;
		}
		return rootLayer;
	}

	public static boolean inRect(float x, float y, float w, float h, float px,
			float py) {
		if (px > x && px < x + w && py > y && py < y + h) {
			return true;
		}
		return false;
	}

	public static boolean colliseWidth(float x, float y, float w, float h,
			float x2, float y2, float w2, float h2) {
		if (x > x2 + w2 || x2 > x + w || y > y2 + h2 || y2 > y + h) {
			return false;
		}
		return true;
	}
}
