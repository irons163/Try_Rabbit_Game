package com.example.try_rabbit_engine;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class AudioProvider {
	private MediaPlayer media_player;
	private SoundPool soundPool;
	private int soundID_ding;
	private int soundID_twitter;
	private float volume;
	
	public AudioProvider(Context context) {
		media_player = MediaPlayer.create(context, R.raw.bg);
		media_player.setLooping(true);
		
		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
		soundID_ding = soundPool.load(context, R.raw.ding,1 );
		soundID_twitter = soundPool.load(context, R.raw.twitter, 1);
		
		AudioManager mgr = (AudioManager) context.getSystemService(
				Context.AUDIO_SERVICE);
		float streamVolumeCurrent = mgr
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mgr
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		volume = streamVolumeCurrent / streamVolumeMax;
	}
	public void play_bell_ding(){
		soundPool.play(soundID_ding, volume, volume, 1, 0, 1);
	}
	
	public void play_twitter(){
		soundPool.play(soundID_twitter, volume, volume, 1, 0, 1);
	}
	
	public void play_bg(){
		media_player.start();
	}
	
	public void release(){
		if(media_player.isPlaying())
			media_player.stop();
		media_player.release();
		soundPool.release();
	}
}
