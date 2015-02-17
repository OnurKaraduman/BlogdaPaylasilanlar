package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

	private MediaPlayer player;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/* servisimiz olusturuldugu zaman bu method otomatik tetiklenecektir. */
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		initPlayer();
		startPlaying();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopPlaying();
	}

	/*
	 * bir adet mediaPlayer nesnesi olusturuyoruz ve gerekli baslangic
	 * ayarlarini yapiyoruz
	 */
	private void initPlayer() {

		// mediaPlayer nesnemizden bir adet oluþturuyoruz
		player = new MediaPlayer();
		// mediaplayerin oynatacagi muzigi projemize res/raw klosoru altina
		// ekledikten sonra burada yolunu belirtiyoruz
		player = MediaPlayer.create(this,
				com.example.musicbackground.R.raw.istanbul);
		// muzik oynatmak istedigimizi belirtiyoruz
		player.setAudioStreamType(AudioManager.STREAM_MUSIC);

		// surekli ayni muzigi tekrar tekrar calacagini belirtiyoruz
		player.setLooping(true);

	}

	private void startPlaying() {
		player.start();
	}

	private void stopPlaying() {
		if (player.isPlaying()) {
			player.stop();
			player.release();
		}
	}

}
