package com.funnyvideos.newvideos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.funnyvideos.newvideos.Config;
import com.funnyvideos.newvideos.R;
import com.funnyvideos.newvideos.models.Setting;
import com.funnyvideos.newvideos.rests.ApiInterface;
import com.funnyvideos.newvideos.rests.RestAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActivitySplash extends AppCompatActivity {
    private static final String TAG ="tagfb" ;
    InterstitialAd fanInterstitialAd;
    private com.google.android.gms.ads.InterstitialAd mInterstitialAd;

    Boolean isCancelled = false;
    private ProgressBar progressBar;
    long nid = 0;
    LinearLayout layutprogressbar;
    String url = "";
    public  static String admob_appid="",admob_banner="",admob_inter="",admob_status="",startup_status="",startup_id="",faninter="";

    Button buttonstart;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        layutprogressbar=findViewById(R.id.llProgressBar);
        buttonstart=findViewById(R.id.buttonstart);




        if(getIntent().hasExtra("nid")) {
            nid = getIntent().getLongExtra("nid", 0);
            url = getIntent().getStringExtra("external_link");
        }

        getads();





    }

    public void getads() {
        ApiInterface apiInterface = RestAdapter.createAPI();
        Call<Setting> call = apiInterface.get_Ads();
        call.enqueue(new Callback<Setting>() {
            @Override
            public void onResponse(Call<Setting> call, Response<Setting> response) {
                assert response.body() != null;
                admob_appid = response.body().getAdmob_appid();
                faninter=response.body().getFaninter();

                admob_banner =response.body().getAdmob_banner();
                admob_inter=response.body().getAdmob_inter();
                admob_status=response.body().getAdmob_status();
                startup_status=response.body().getStartup_status();
                if (startup_status.equals("1")){
                    startup_id=response.body().getStartup_id();


                }

                  else   {
                    startup_id=null;

                }
                buttonstart.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

                buttonstart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadinter(faninter);
                        layutprogressbar.setVisibility(View.VISIBLE);
                    }
                });




            }

            @Override
            public void onFailure(Call<Setting> call, Throwable t) {
            }

        });
    }

    public void loadinter (String inter){

        fanInterstitialAd = new InterstitialAd(this,inter);

        fanInterstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

                layutprogressbar.setVisibility(View.GONE);
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                layutprogressbar.setVisibility(View.GONE);

                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");

                Intent intent = new Intent(ActivitySplash.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(Ad ad, AdError adError) {

                loadinteradmob(admob_inter);


                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }



            @Override
            public void onAdLoaded(Ad ad) {
                layutprogressbar.setVisibility(View.GONE);

                fanInterstitialAd.show();



                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad

            }

            @Override
            public void onAdClicked(Ad ad) {
                layutprogressbar.setVisibility(View.GONE);

                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        });

        fanInterstitialAd.loadAd();
    }



    public void loadinteradmob(String inter){

        mInterstitialAd = new com.google.android.gms.ads.InterstitialAd(this);
        mInterstitialAd.setAdUnitId(inter);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                layutprogressbar.setVisibility(View.GONE);

                mInterstitialAd.show();

                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

                StartAppAd startAppAd = new StartAppAd(getApplicationContext());
                startAppAd.showAd(new AdDisplayListener() {
                    @Override
                    public void adHidden(com.startapp.android.publish.adsCommon.Ad ad) {
                        layutprogressbar.setVisibility(View.GONE);

                        Intent intent = new Intent(ActivitySplash.this,MainActivity.class);

                        startActivity(intent);

                    }

                    @Override
                    public void adDisplayed(com.startapp.android.publish.adsCommon.Ad ad) {

                    }

                    @Override
                    public void adClicked(com.startapp.android.publish.adsCommon.Ad ad) {
                        layutprogressbar.setVisibility(View.GONE);

                        Intent intent = new Intent(ActivitySplash.this,MainActivity.class);

                        startActivity(intent);

                    }

                    @Override
                    public void adNotDisplayed(com.startapp.android.publish.adsCommon.Ad ad) {

                        layutprogressbar.setVisibility(View.GONE);

                        Intent intent = new Intent(ActivitySplash.this,MainActivity.class);

                        startActivity(intent);

                    }
                })   ;


//                final Handler   handler = new Handler();
//
//                final Runnable r = new Runnable() {
//                    public void run() {
//                        StartAppAd.showAd(getContext());
//
//                        layutprogressbar.setVisibility(View.GONE);
//
//                        Intent intent = new Intent(SplashscreenActivity.this,MainActivity.class);
//
//                        startActivity(intent);
//                    }
//                };
//
//                handler.postDelayed(r, 3000);



                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                layutprogressbar.setVisibility(View.GONE);

                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                layutprogressbar.setVisibility(View.GONE);


                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                layutprogressbar.setVisibility(View.GONE);

                Intent intent = new Intent(ActivitySplash.this,MainActivity.class);

                startActivity(intent);

                // Code to be executed when the interstitial ad is closed.
            }
        });
    }

}
