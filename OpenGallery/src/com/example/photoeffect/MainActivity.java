package com.example.photoeffect;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	private Button btnOpenGallery;
	private int SELECT_PICTURE = 100;
	private ImageView imgView;
	private Uri selectedImageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		 * Layouta ekledigimiz imageviewe ulasmak icin
		 */
		imgView = (ImageView) findViewById(R.id.imageView1);

		/*
		 * Layouta ekledigimiz Buttona ulasmak icin
		 */
		btnOpenGallery = (Button) findViewById(R.id.button1);

		/*
		 * Butonumuza tiklaninca yapacagi islemi asagidaki gibi tanimliyoruz
		 */
		btnOpenGallery.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openGallery();
			}
		});

	}

	/*
	 * OpenGallery fonksiyonumuzda galeriyi acmak icin gerekli olan kodlari
	 * yaziyoruz.
	 */
	public void openGallery() {
		// uygulamamizda haberlesmeyi saglamak icin intentler kullaniyoruz.
		// Bu intent ise galleriyi acmak icin kullanilacak
		Intent i = new Intent();

		// intent icin filtre olusturuyoruz
		i.setType("image/*");
		// intentin yapacagi islemi tanimliyoruz
		i.setAction(Intent.ACTION_GET_CONTENT);

		// intenti baslatiyoruz. Bu koddan sonra galleri acilmis olacak
		startActivityForResult(Intent.createChooser(i, "Select Picture"),
				SELECT_PICTURE);
	}

	/*
	 * Galeriyi actiktan sonra geriye secilen resmi dondurmemiz gerekiyor bunun
	 * icin baslatilan intente bir numara vermiþtik openGallery fonksiyonu
	 * icinde SELEC_PICTURE diye. Bu kodumuzu kullanarak donen datayi
	 * onActivityResult fonksiyonunu override ederek almýþ oluyoruz.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == this.RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				selectedImageUri = data.getData();
				imgView.setImageURI(selectedImageUri);

			}
		}
	}

}
