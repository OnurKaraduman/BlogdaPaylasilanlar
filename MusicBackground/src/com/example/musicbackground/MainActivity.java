package com.example.musicbackground;

import com.example.service.MusicService;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

public class MainActivity extends ActionBarActivity {

	private Switch switchMusic;
	private Intent musicIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		switchMusic = (Switch) findViewById(R.id.switch1);
		switchMusic.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					initMusicService();
				}
				else {
					stopService(musicIntent);
				}
			}
		});
	}

	private void initMusicService(){
		musicIntent = new Intent(this, MusicService.class);
		startService(musicIntent);
	}
}
