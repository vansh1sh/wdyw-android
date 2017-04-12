package app.com.example.vansh.wdyw.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import app.com.example.vansh.wdyw.R;


public class SplashActivity extends AppCompatActivity {

    ImageView imageView;
    Bitmap bitmap;
    Transformation transformation;
    float t=0.1f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.splashbg);
                BlurAnimation blurAnimation = new BlurAnimation(imageView,bitmap,0,150);
                blurAnimation.applyTransformation(t,transformation);


            }
        }, 1000);*/



        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.

       /* final VideoView videoView =
                (VideoView) findViewById(R.id.myvideoview);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.video;
        videoView.setVideoPath(uriPath);
 
        videoView.start();*/

        new CountDownTimer(3000, 3000) {

            public void onTick(long millisUntilFinished) {
                //nothing
            }

            public void onFinish() {
                Boolean isFirstRun=getSharedPreferences("PREFERENCE",MODE_PRIVATE).getBoolean("isFirstRun",true);

                if(isFirstRun)
                {
                    //show start activity

                    startActivity(new Intent(SplashActivity.this, StartupActivity.class));
                    Toast.makeText(SplashActivity.this, "Welcome to Miscaa", Toast.LENGTH_LONG)
                            .show();

                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                            .putBoolean("isFirstRun", false).commit();
                }

                else{

                    Intent i = new Intent(SplashActivity.this, FirstPage.class);
                    startActivity(i);
                    finish();}
            }

        }.start();
    }




}

