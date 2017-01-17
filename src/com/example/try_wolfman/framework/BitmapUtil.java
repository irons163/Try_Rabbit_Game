package com.example.try_wolfman.framework;

import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings.Global;

public class BitmapUtil {
	static Context context;
	
	public static void initBitmap(Context context){
		BitmapUtil.context = context;
//		initBitmap();
	}
	
	// ��l�Ʀn������I
	public static Bitmap createSpecificSizeBitmap(Drawable drawable, int width, int height) {
		// �s�ؤ@��bitmap�A��e20�A�ϥ�ARGB_8888�]�w�A��bitmap�{�b�ť�bitmap��Dnull�C
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap); // �s�صe���A�Ϊť�bitmap��e��
		drawable.setBounds(0, 0, width, height);// �]�wdrawable�����(��Ϥ�ۤv����e)
		drawable.draw(canvas); // �b�e���W�e�W��drawable(����bitmap�w�g�Q�e�W�F��A���O�ťդF)
		return bitmap;
	}
	
	public static Bitmap getBitmap(String path) {
		try {
			InputStream is = context.getAssets().open(path);

			return BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Bitmap getBitmapFromRes(int resId){
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
		return bitmap;
	}
	
}
