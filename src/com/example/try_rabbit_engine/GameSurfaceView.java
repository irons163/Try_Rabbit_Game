package com.example.try_rabbit_engine;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements
SurfaceHolder.Callback {
	private Context context;
	private Rabit rabit;
	private Backgroud bg;
	private Conclusion conclusion;
	
	private Bitmap bitmap_jump;
	
	private BellCreator bell_creator;
	private Bird bird;
	
	private RefurbishThread refurbishThread;
	private SurfaceHolder holder;
	private Paint paint;
	
	private int score = 0;
	private int highest_score;
	
	private boolean audio_on = false;
	private AudioProvider audioProvider;
	
	private List<Bell> bell_list = new ArrayList<Bell>();
	private boolean jump_comm_flag = false;
	
	private int hit_count = 0;
	private boolean game_over = false;
	private boolean jump_button_clicked = false;
	
	public GameSurfaceView(Context context) {
		super(context);
		this.context = context;
		rabit = new Rabit(context);
		bg = new Backgroud(context);
		conclusion = new Conclusion(this);
		bitmap_jump = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.button);
		init_audio();
		init_highest_score();
		bell_creator = new BellCreator(context);
		bird = new Bird(context);

		refurbishThread = new RefurbishThread();
		init_bell_list();
		paint = new Paint();
		this.holder = this.getHolder();
		holder.addCallback(this);
		this.setFocusable(true);
		
	}
	
	
	private void init_audio() {
		this.audio_on = ((GameActivity) context).isAudio_on();
		if (audio_on) {
			audioProvider = new AudioProvider(context);
		}
	}
	
	private void init_highest_score() {
		SharedPreferences settings = ((GameActivity) context)
				.getPreferences(Activity.MODE_PRIVATE);
		highest_score = settings.getInt("highestscore", 0);
	}
	
	private void init_bell_list() {
		Bell bell0 = bell_creator.createBell();
		Bell bell1 = bell_creator.createBell();
		Bell bell2 = bell_creator.createBell();
		Bell bell3 = bell_creator.createBell();
		Bell bell4 = bell_creator.createBell();

		bell0.setCenter_y(170);
		bell1.setCenter_y(130);
		bell2.setCenter_y(90);
		bell3.setCenter_y(50);
		bell4.setCenter_y(10);

		bell_list.removeAll(bell_list);
		bell_list.add(bell0);
		bell_list.add(bell1);
		bell_list.add(bell2);
		bell_list.add(bell3);
		bell_list.add(bell4);
	}
	
	private void update_rabit(){
		/**
		 * rabit need jump up 93.72px per time 4 23 5 18 6 15
		 * */
	
		if (rabit.isOnGround()) {
			// Â§ÑÁ?Ê∞¥Âπ≥?πÂ?ÁßªÂä®
			// rabit ?∞Ëææ?ÆÁ?‰ΩçÁΩÆ
			if (Math.abs(rabit.getX() - rabit.getX_destination()) < 5) {
				if (rabit.isFaceLeft()) {
					rabit.setPic_state(Rabit.RABIT_PIC_LEFT_STOP);
					rabit.setFace_state(Rabit.RABIT_FACE_LEFT);
					rabit.setGround_state(Rabit.RABIT_LEFT_STOP);
				} else {
					rabit.setPic_state(Rabit.RABIT_PIC_RIGHT_STOP);
					rabit.setFace_state(Rabit.RABIT_FACE_RIGHT);
					rabit.setGround_state(Rabit.RABIT_RIGHT_STOP);
				}
			} else if (rabit.getX() - rabit.getX_destination() >= 5) {
				// rabit ?®destination?ÑÂè≥?¢Ô?ÂÆÉÂ??ëÂ∑¶ÁßªÂä®
				rabit.setFace_state(Rabit.RABIT_FACE_LEFT);
				rabit.setX(rabit.getX() - Rabit.SPEED_X);
				if (rabit.getGround_state() == Rabit.RABIT_LEFT_MOVE1_ON_GROUND) {
					rabit.setGround_state(Rabit.RABIT_LEFT_MOVE2_ON_GROUND);
					rabit.setPic_state(Rabit.RABIT_PIC_ON_GROUND_LEFT_JUMP1);
				} else if (rabit.getGround_state() == Rabit.RABIT_LEFT_MOVE2_ON_GROUND) {
					rabit.setGround_state(Rabit.RABIT_LEFT_MOVE1_ON_GROUND);
					rabit.setPic_state(Rabit.RABIT_PIC_ON_GROUND_LEFT_JUMP0);
				} else {
					rabit.setGround_state(Rabit.RABIT_LEFT_MOVE1_ON_GROUND);
					rabit.setPic_state(Rabit.RABIT_PIC_ON_GROUND_LEFT_JUMP0);
				}

			} else if (rabit.getX_destination() - rabit.getX() > 5) {
				// rabit ?®destination?ÑÂ∑¶?¢Ô?ÂÆÉÂ??ëÂè≥ÁßªÂä®
				rabit.setX(rabit.getX() + Rabit.SPEED_X);
				rabit.setFace_state(Rabit.RABIT_FACE_RIGHT);
				if (rabit.getGround_state() == Rabit.RABIT_RIGHT_MOVE1_ON_GROUND) {
					rabit.setGround_state(Rabit.RABIT_RIGHT_MOVE2_ON_GROUND);
					rabit.setPic_state(Rabit.RABIT_PIC_ON_GROUND_RIGHT_JUMP1);
				} else if (rabit.getGround_state() == Rabit.RABIT_RIGHT_MOVE2_ON_GROUND) {
					rabit.setGround_state(Rabit.RABIT_RIGHT_MOVE1_ON_GROUND);
					rabit.setPic_state(Rabit.RABIT_PIC_ON_GROUND_RIGHT_JUMP0);
				} else {
					rabit.setGround_state(Rabit.RABIT_RIGHT_MOVE1_ON_GROUND);
					rabit.setPic_state(Rabit.RABIT_PIC_ON_GROUND_RIGHT_JUMP0);
				}
			}
			// process jump command
			if (jump_comm_flag) {
				rabit.setAir_state(Rabit.RABIT_ON_AIR_UP0);
				rabit.setGround_state(Rabit.RABIT_NOT_ON_GROUND);
				rabit.setY(rabit.getY() - Rabit.SPEED_Y);
				if (rabit.isFaceLeft()) {
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_JUMP);
				} else {
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_JUMP);
				}
				jump_comm_flag = false;
			}

		} else {
			// ////////////////////////////////////////////////////////////////////
			// rabit?®Á©∫‰∏?
			// Â§ÑÁ?Ê∞¥Êñπ?ëÁßª??
			// Ê≠§Â?10?ºÂ??ÜÊ¶∑ÔºåÂ?Â§öÂ??ºÈ?‰∏âÊ?ÔºÅÔ?ÔºÅÔ?ÔºÅÔ?ÔºÅÔ?ÔºÅÔ?ÔºÅÔ?Ôº?
			if (rabit.getX() - rabit.getX_destination() >= 10) {
				// rabit ?®destination?ÑÂè≥?¢Ô?ÂÆÉÂ??ëÂ∑¶ÁßªÂä®
				rabit.setFace_state(Rabit.RABIT_FACE_LEFT);
				rabit.setX(rabit.getX() - Rabit.SPEED_X_ON_AIR);
			} else if (rabit.getX_destination() - rabit.getX() > 10) {
				// rabit ?®destination?ÑÂ∑¶?¢Ô?ÂÆÉÂ??ëÂè≥ÁßªÂä®
				rabit.setX(rabit.getX() + Rabit.SPEED_X_ON_AIR);
				rabit.setFace_state(Rabit.RABIT_FACE_RIGHT);
			}
			// Â§ÑÁ??ÇÁõ¥?πÂ? ÁßªÂä®
			if (rabit.getAir_state() == Rabit.RABIT_ON_AIR_UP0) {
				// rabit.setY(rabit.getY()-Rabit.SPEED_Y);
				rabit_move_up();
				rabit.setAir_state(Rabit.RABIT_ON_AIR_UP1);
				if (rabit.isFaceLeft())
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_JUMP);
				else
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_JUMP);
			} else if (rabit.getAir_state() == Rabit.RABIT_ON_AIR_UP1) {
				// rabit.setY(rabit.getY()-Rabit.SPEED_Y);
				rabit_move_up();
				rabit.setAir_state(Rabit.RABIT_ON_AIR_UP2);
				if (rabit.isFaceLeft())
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_JUMP);
				else
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_JUMP);
			} else if (rabit.getAir_state() == Rabit.RABIT_ON_AIR_UP2) {
				// rabit.setY(rabit.getY()-Rabit.SPEED_Y);
				rabit_move_up();
				rabit.setAir_state(Rabit.RABIT_ON_AIR_UP3);
				if (rabit.isFaceLeft())
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_JUMP);
				else
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_JUMP);
			} else if (rabit.getAir_state() == Rabit.RABIT_ON_AIR_UP3) {
				// rabit.setY(rabit.getY()-Rabit.SPEED_Y);
				rabit_move_up();
				rabit.setAir_state(Rabit.RABIT_ON_AIR_UP4);
				if (rabit.isFaceLeft())
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_JUMP);
				else
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_JUMP);
			} else if (rabit.getAir_state() == Rabit.RABIT_ON_AIR_UP4) {
				// rabit.setY(rabit.getY()-Rabit.SPEED_Y);
				rabit_move_up();
				rabit.setAir_state(Rabit.RABIT_ON_AIR_UP5);
				if (rabit.isFaceLeft())
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_JUMP);
				else
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_JUMP);
			} else if (rabit.getAir_state() == Rabit.RABIT_ON_AIR_UP5) {
				rabit.setAir_state(Rabit.RABIT_ON_AIR_STOP);
				if (rabit.isFaceLeft())
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_STOP);
				else
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_STOP);
			} else if (rabit.getAir_state() == Rabit.RABIT_ON_AIR_STOP) {
				rabit.setAir_state(Rabit.RABIT_ON_AIR_DOWN);
				if (rabit.isFaceLeft())
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_STOP);
				else
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_STOP);
			} else if (rabit.getAir_state() == Rabit.RABIT_ON_AIR_DOWN) {
				// rabit.setY(rabit.getY() + Rabit.SPEED_Y);
				rabit.setAir_state(Rabit.RABIT_ON_AIR_DOWN);
				if (rabit.isFaceLeft())
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_DOWN);
				else
					rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_DOWN);
				rabit_move_down();
			}
		}
	}
	
	private void rabit_move_down() {
		if (rabit.getY() > 210) {
			//rabitÂ∑≤Á?‰∏ãËêΩ?≥Â?ÂπïÂ??®Ô?‰∏çÂèØ?ΩÂú®Á¢∞Âà∞bellÔº?rabitËæì‰?È´òÁ©∫‰∏ãÂ??∂Ë??ØÔ?bellÔºåbird?Ñ‰?ÁΩÆÂ???
			if (bg.isLowest()) {
				game_over = true;
				rabit.setY(Constant.RABIT_INIT_Y);
				if (rabit.isFaceLeft()) {
					rabit.setPic_state(Rabit.RABIT_PIC_LEFT_STOP);
				} else {
					rabit.setPic_state(Rabit.RABIT_PIC_RIGHT_STOP);
				}
				bg.init();
			} else {
				// bg ?ë‰??ñÂä®
				bg.drag_up((int) Rabit.SPEED_Y);
				//Â±èÂ?‰∏äÁ?bird?ë‰?ÁßªÂä®
				if (bird.isOn_screen()) {
					bird.setCenter_y(bird.getCenter_y() - Rabit.SPEED_Y);
					if (bird.getCenter_y() < 0) {
						bird.setOn_screen(false);
					}
				}
				// ????Ñbell?ë‰?ÁßªÂä®
				for (int i = 0; i < bell_list.size(); ++i) {
					Bell bell = bell_list.get(i);
					bell.setCenter_y(bell.getCenter_y() - Rabit.SPEED_Y);
				}
				// Â§ÑÁ?ÁßªÂä®?∞Â?ÂπïÂ??Ñbell
				if (bell_list.size() > 0) {
					Bell bell_up = bell_list.get(bell_list.size() - 1);
					if (bell_up.getCenter_y() < -Bell.BELL_OK_HEIGHT / 2) {
						bell_list.remove(bell_up);
						bell_creator.recycle(bell_up);
					}
				}
			}
		} else {
			// rabit ËøòÊ≤°?âÁé©ÂÆ?
			rabit.setY(rabit.getY() + Rabit.SPEED_Y);
		}
	}
	
	private void update_bells(){
		for (int i = 0; i < bell_list.size(); ++i) {
			Bell bell = bell_list.get(i);
			if (bell.isExplode()) {
				if (bell.getState() == Bell.BELL_EXPLODE0) {
					bell.setState(Bell.BELL_EXPLODE1);
				} else if (bell.getState() == Bell.BELL_EXPLODE1) {
					bell.setState(Bell.BELL_EXPLODE2);
				} else if (bell.getState() == Bell.BELL_EXPLODE2) {
					bell_list.remove(bell);
					bell_creator.recycle(bell);
					--i;
				}
			} else {
				if (rabit.isHitBell(bell)) {
					++hit_count;
					score += hit_count * 10;
					if(audio_on) audioProvider.play_bell_ding();
					bell.setState(Bell.BELL_EXPLODE0);
					rabit.setAir_state(Rabit.RABIT_ON_AIR_UP0);
					if (rabit.isFaceLeft()) {
						rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_JUMP);
					} else {
						rabit.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_JUMP);
					}
				}
			}
		}
	}
	
	private void update_bird(){
		if (bird.isOn_screen()) {
			// Â§ÑÁ??ÇÁõ¥
			if (bird.getCenter_y() > Constant.SCREEN_HEIGHT) {
				bird.setOn_screen(false);
			}
		}
		if (bird.isOn_screen()) {
			// Â§ÑÁ?Ê∞¥Âπ≥
			if (bird.isHited()) {
				if (bird.isFaceLeft()) {
					bird
							.setCenter_x(bird.getCenter_x()
									- Bird.BIRD_SPEED_POWER);
					if (bird.getCenter_x() < -5) {
						bird.setOn_screen(false);
					}
				} else {
					bird
							.setCenter_x(bird.getCenter_x()
									+ Bird.BIRD_SPEED_POWER);
					if (bird.getCenter_x() > Constant.SCREEN_WIDTH + 5) {
						bird.setOn_screen(false);
					}
				}
			} else {
				if (bird.isFaceLeft()) {
					bird.setCenter_x(bird.getCenter_x() - Bird.BIRD_SPEED);
					if (bird.getCenter_x() < -5) {
						bird.setCenter_x(0);
						bird.setState(Bird.BIRD_RIGHT_FLY0);
					} else {
						if (rabit.isHitBird(bird)) {
							++hit_count;
							score *= 2;
							if(audio_on) audioProvider.play_twitter();
							bird.setState(Bird.BIRD_LEFT_FLY_POWER);
							rabit.setAir_state(Rabit.RABIT_ON_AIR_UP0);
							if (rabit.isFaceLeft()) {
								rabit
										.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_JUMP);
							} else {
								rabit
										.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_JUMP);
							}
						} else if (bird.getState() == Bird.BIRD_LEFT_FLY0) {
							bird.setState(Bird.BIRD_LEFT_FLY1);
						} else if (bird.getState() == Bird.BIRD_LEFT_FLY1) {
							bird.setState(Bird.BIRD_LEFT_FLY2);
						} else {
							bird.setState(Bird.BIRD_LEFT_FLY0);
						}
					}

				} else {
					bird.setCenter_x(bird.getCenter_x() + Bird.BIRD_SPEED);
					if (bird.getCenter_x() > Constant.SCREEN_WIDTH + 5) {
						bird.setCenter_x(Constant.SCREEN_WIDTH);
						bird.setState(Bird.BIRD_LEFT_FLY0);
					} else {
						if (rabit.isHitBird(bird)) {
							++hit_count;
							score *= 2;
							if(audio_on) audioProvider.play_twitter();
							bird.setState(Bird.BIRD_LEFT_FLY_POWER);
							rabit.setAir_state(Rabit.RABIT_ON_AIR_UP0);
							if (rabit.isFaceLeft()) {
								rabit
										.setPic_state(Rabit.RABIT_PIC_ON_AIR_LEFT_JUMP);
							} else {
								rabit
										.setPic_state(Rabit.RABIT_PIC_ON_AIR_RIGHT_JUMP);
							}
						} else if (bird.getState() == Bird.BIRD_RIGHT_FLY0) {
							bird.setState(Bird.BIRD_RIGHT_FLY1);
						} else if (bird.getState() == Bird.BIRD_RIGHT_FLY1) {
							bird.setState(Bird.BIRD_RIGHT_FLY2);
						} else {
							bird.setState(Bird.BIRD_RIGHT_FLY0);
						}
					}
				}
			}
		}
		}
	
	
	private void update_all_components() {
		if (game_over)
			return;
//		update_rabit();
//		update_bells();
//		update_bird();
	}
	
	private void rabit_move_up() {
		if (rabit.getCenter_y() > Constant.SCREEN_HEIGHT / 2) {
			rabit.setY(rabit.getY() - Rabit.SPEED_Y);
			return;
		}

		// Â§ÑÁ?Â±èÂ??åÊôØ?ÑÂ???
		// Â§ÑÁ?bells?Ñ‰?ÁΩ?
		// ????Ñbell‰∏ãÁßª
		
		// rabitË∑≥Ë??≥Â?Âπ?/2Â§Ñ~2/3?∂Ô?Â§ÑÁ??¥‰∏™?∫ÊôØComponents?òÂ?
		if (rabit.getCenter_y() > 0.33 * Constant.SCREEN_HEIGHT) {
			for (int i = 0; i < bell_list.size(); ++i) {
				Bell bell = bell_list.get(i);
				bell.setCenter_y(bell.getCenter_y() + Rabit.SPEED_Y / 2);
			}
			bg.drag_down((int) Rabit.SPEED_Y / 2);
			bird.setCenter_y(bird.getCenter_y() + Rabit.SPEED_Y / 2);
			rabit.setCenter_y(rabit.getCenter_y() - Rabit.SPEED_Y / 2);
		} else {
			// rabitË∑≥Ë?ÊØîÂ?ÂπïÁ?2/3Â§ÑË?È´òÊó∂ÔºåÂ??ÜÊï¥‰∏™Âú∫?ØÁ??òÂ?
			for (int i = 0; i < bell_list.size(); ++i) {
				Bell bell = bell_list.get(i);
				bell.setCenter_y(bell.getCenter_y() + Rabit.SPEED_Y);
			}
			bg.drag_down((int) Rabit.SPEED_Y);
			if (bird.isOn_screen()) {
				bird.setCenter_y(bird.getCenter_y() + Rabit.SPEED_Y);
			}
		}
		// ?ØÂê¶???Ê∏ÖÁ??ΩÂú∞?Ñbell, bell_list[0] Â§Ñ‰????Â§ÑÔ??§Êñ≠ÂÆÉÊòØ?¶ËêΩ??
		Bell lowBell = bell_list.get(0);
		if (lowBell != null) {
			if (lowBell.getCenter_y() > 230) {
				bell_list.remove(lowBell);
				bell_creator.recycle(lowBell);
			}
		}
		// ?§Êñ≠?ØÂê¶???Ê∑ªÂ??∞Á?bellÔº?bell_list[size-1] Â§Ñ‰????Â§ÑÔ??öË?ÂÆÉÊù•?§Êñ≠
		if (bell_list.size() > 0) {
			Bell upBell = bell_list.get(bell_list.size() - 1);
			if (upBell.getCenter_y() > 50)
				bell_list.add(bell_creator.createBell());
		}

	}

	
	class RefurbishThread extends Thread {
		private boolean go_on = false;

		public boolean isGo_on() {
			return go_on;
		}

		public void setGo_on(boolean goOn) {
			go_on = goOn;
		}

		@SuppressLint("WrongCall")
		public void run() {
			while (go_on) {
				try {
					// Thread.sleep(50);
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update_all_components();
				synchronized (holder) {
					Canvas canvas = holder.lockCanvas();
					GameSurfaceView.this.onDraw(canvas);
					holder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
	
	@SuppressLint("WrongCall")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		bg.onDraw(canvas);
		
		if (!jump_button_clicked) {
			canvas.drawBitmap(bitmap_jump, 333, 199, paint);
		}
		
		drawBell(canvas);
		rabit.onDraw(canvas);
		drawScore(canvas);
		if (game_over) {
			conclusion.onDraw(canvas);
		}
		if (bird.isOn_screen()) {
			bird.onDraw(canvas);
		}
	}
	
	@SuppressLint("WrongCall")
	private void drawBell(Canvas canvas) {
		for (int i = 0; i < bell_list.size(); ++i) {
			bell_list.get(i).onDraw(canvas);
		}
	}

	private void drawScore(Canvas canvas) {
		paint.setColor(Color.WHITE);
		paint.setTextSize(15);
		canvas.drawText("" + score, 22, 22, paint);
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHighest_score() {
		return highest_score;
	}

	public void setHighest_score(int highestScore) {
		highest_score = highestScore;
	}
	
	private void init() {
		rabit.init();
		bg.init();
		init_bird();
		jump_button_clicked = false;
		jump_comm_flag = false;
		for (int i = 0; i < bell_list.size(); ++i) {
			bell_creator.recycle(bell_list.get(i));
		}
		bell_creator.init();
		init_bell_list();
		highest_score = (highest_score > score) ? highest_score : score;
		score = 0;
		hit_count = 0;
	}
	
	private void init_bird() {
		bird.setState(Bird.BIRD_LEFT_FLY0);
		bird.setCenter_x(240);
		bird.setCenter_y(30);
		bird.setOn_screen(true);
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			this.refurbishThread.setGo_on(false);
		}
		return true;
	}

	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		if (game_over) {
			////////////////////////////Ê∏∏Ê?ÁªìÊ??éÁ?Ëß¶Â?Â§ÑÁ?
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (x >= 165 && x <= 237 && y >= 154 && y <= 180) {
					conclusion.setPressed(true);
				}
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			case MotionEvent.ACTION_UP:
				if (x >= 165 && x <= 237 && y >= 154 && y <= 180) {
					conclusion.setPressed(false);
					init();
					game_over = false;
				}
				break;
			default:
				break;
			}
		} else {
			////////////////////////////Ê∏∏Ê?‰∏≠Á?Ëß¶Â?Â§ÑÁ?
			if (jump_button_clicked) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_UP:
					rabit.setX_destination(x);
					break;
				default:
					rabit.setX_destination(rabit.getX());
				}
			} else {
				if (x > 327 && x < 1364 && y > 196 && y < 2300) {
					if(event.getAction() == MotionEvent.ACTION_UP){
						jump_comm_flag = true;
						jump_button_clicked = true;
					}
				} else {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
					case MotionEvent.ACTION_MOVE:
					case MotionEvent.ACTION_UP:
						rabit.setX_destination(x);
						break;
					default:
						rabit.setX_destination(rabit.getX());
					}
				}
			}
		}
		return true;
	}

	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		refurbishThread.setGo_on(true);
		refurbishThread.start();
		if (audio_on) {
			audioProvider.play_bg();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		refurbishThread.setGo_on(false);
		if (audio_on) {
//			player.stop();
//			player.release();
//			soundPool.release();
			audioProvider.release();
		}
		// save highest score
		highest_score = (highest_score > score) ? highest_score : score;
		SharedPreferences pre = ((GameActivity) context).getPreferences(0);
		SharedPreferences.Editor editor = pre.edit();
		editor.putInt("highestscore", highest_score);
	}
	
	
}
