package app.com.example.vansh.wdyw.activity;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import app.com.example.vansh.wdyw.R;

public class CheckCredit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_credit);

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
