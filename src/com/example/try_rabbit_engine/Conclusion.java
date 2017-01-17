package com.example.try_rabbit_engine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Conclusion {
	private boolean pressed = false;
	private GameSurfaceView context;
	private Bitmap bg;
	private Bitmap replay_button;
	private Paint paint = new Paint();

	public Conclusion(GameSurfaceView context) {
		this.context = context;
		this.bg = BitmapFactory.decodeResource(context.getContext()
				.getResources(), R.drawable.conclusion);
		replay_button = BitmapFactory.decodeResource(context.getContext()
				.getResources(), R.drawable.replay_button);
	}

	public void onDraw(Canvas canvas) {
		if (context.getHighest_score() > context.getScore()) {
			canvas.drawBitmap(bg, 95, 0, paint);
			paint.setColor(Color.WHITE);
			paint.setTextSize(17);
			canvas.drawText("SCORE", 175, 51, paint);
			paint.setColor(Color.RED);
			canvas.drawText("" + context.getScore(), 180, 77, paint);
			paint.setColor(Color.WHITE);
			canvas.drawText("HIGHEST SCORE", 148, 105, paint);
			paint.setColor(Color.YELLOW);
			canvas.drawText(""+context.getHighest_score(), 175, 138, paint);

			if (pressed) {
				canvas.drawBitmap(replay_button, 164, 156, paint);
			}
		} else {
			canvas.drawBitmap(bg, 95, 0, paint);
			paint.setColor(Color.RED);
			paint.setTextSize(20);
			canvas.drawText("CONGRATULATIONS!", 100, 70, paint);
			paint.setColor(Color.WHITE);
			paint.setTextSize(17);
			canvas.drawText("New Hightest Score:", 125, 115, paint);
			canvas.drawText(""+context.getScore(), 180, 147, paint);
		}
	}

	public void init() {
		pressed = false;
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

}
