package com.example.try_rabbit_engine;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class IntroduceView extends View{
	private Bitmap bg;
	private Paint paint;

	public IntroduceView(Context context) {
		super(context);
		paint = new Paint();
		bg = BitmapFactory.decodeResource(this.getResources(), R.drawable.introduce);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(bg, 0, 0, paint);
	}
}
