package app.com.example.vansh.wdyw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import app.com.example.vansh.wdyw.R;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.

        final VideoView videoView =
                (VideoView) findViewById(R.id.myvideoview);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.vid;
        videoView.setVideoPath(uriPath);

        videoView.start();


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
                    Toast.makeText(SplashActivity.this, "Welcome to WDYW", Toast.LENGTH_LONG)
                            .show();

                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                            .putBoolean("isFirstRun", false).commit();
                }

                else{

                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();}
            }

        }.start();
    }



}

