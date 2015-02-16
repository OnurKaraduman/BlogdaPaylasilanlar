package com.example.takepicture;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	private int IMAGE_CAPTURE = 100;
	private Button btnOpenCamera;
	private ImageView imageView;
	private int MEDIA_TYPE_IMAGE = 101;
	private int MEDIA_TYPE_VIDEO = 102;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.imageView1);
		btnOpenCamera = (Button) findViewById(R.id.button1);
		btnOpenCamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openCamera();
			}
		});
	}

	public void openCamera() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, IMAGE_CAPTURE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == this.RESULT_OK) {
			if (requestCode == IMAGE_CAPTURE) {
				Bundle extras = data.getExtras();
				Bitmap imageBitmap = (Bitmap) extras.get("data");
				byte[] imageData = convertToByteArray(imageBitmap);
				try {
					onPictureTaken(imageData);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				imageView.setImageBitmap(imageBitmap);
			}
		}
	}

	public byte[] convertToByteArray(Bitmap imageBitmap) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		return byteArray;
	}

	public void onPictureTaken(byte[] imageData) throws IOException {
		File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
		if (pictureFile == null) {
			Log.d("Error","Error creating media file, check storage permission");
			return;
		}
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		FileOutputStream fos  = new FileOutputStream(pictureFile);
		fos.write(imageData);
		fos.close();

	}

	public File getOutputMediaFile(int type) {
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"MyCameraApp");
		if (!mediaStorageDir.exists()) {
			Log.d("MyCameraApp", "failed to create directory");
			return null;
		}

		File mediFile;
		// resim dosyamizin ismini olustururken o anki tarih ve saati alarak
		// buna gore isim olusturuyoruz.
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		String filePath;

		// dosyanin video mu yoksa fotograf dosyasimi oldugunu belirliyoruz
		if (type == MEDIA_TYPE_IMAGE) {
			filePath = mediaStorageDir.getPath() + File.separator + "IMG_"
					+ timeStamp + ".jpg";
		} else if (type == MEDIA_TYPE_VIDEO) {
			filePath = mediaStorageDir.getPath() + File.separator + "VID_"
					+ timeStamp + ".mp4";

		} else
			return null;
		
		mediFile = new File(filePath);
		return mediFile;

	}

}
