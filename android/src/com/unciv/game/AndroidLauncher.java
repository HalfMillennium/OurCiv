package com.unciv.game;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.unciv.UnCivGame;
import com.google.android.gms.ads.MobileAds;

public class AndroidLauncher extends AndroidApplication {

	private InterstitialAd mInterstitialAd;

	MediaPlayer backgroundMusic;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new UnCivGame(), config);

		backgroundMusic = MediaPlayer.create(AndroidLauncher.this, R.raw.heroic_demise_song);
		backgroundMusic.start();
		backgroundMusic.setLooping(true);

		MobileAds.initialize(this, "ca-app-pub-1761948387867531~9236960845");

		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-1761948387867531/8291135717");
		mInterstitialAd.setAdListener(new AdListener());
		// test: ca-app-pub-3940256099942544/1033173712
		mInterstitialAd.loadAd(new AdRequest.Builder().build());
		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				mInterstitialAd.show();
			}
		});

		AdView adView = new AdView(this);
		adView.setAdSize(AdSize.BANNER);
		adView.setAdUnitId("ca-app-pub-1761948387867531/6036082431");

		//ca-app-pub-1761948387867531/6036082431
	}

	@Override
	protected void onPause() {
		super.onPause();
		backgroundMusic.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		backgroundMusic.start();
	}
}
