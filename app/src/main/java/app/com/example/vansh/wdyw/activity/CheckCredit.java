package app.com.example.vansh.wdyw.activity;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import app.com.example.vansh.wdyw.R;

public class CheckCredit extends AppCompatActivity {

    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_credit);
        ll =(LinearLayout)findViewById(R.id.ll);

        ImageView gi = (ImageView) findViewById(R.id.gotit);
        gi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(CheckCredit.this,R.anim.abc_fade_out);
                animation.setDuration(500);
                animation.setFillAfter(true);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ll.setVisibility(View.GONE);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                ll.startAnimation(animation);
            }
        });


       /* final VideoView videoView =
                (VideoView) findViewById(R.id.myvideoview1);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.checkcredits;
        videoView.setVideoPath(uriPath);*/
        /*videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });*/
        //videoView.start();

    }
}
