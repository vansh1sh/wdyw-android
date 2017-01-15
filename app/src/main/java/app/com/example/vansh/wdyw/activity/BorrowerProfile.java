package app.com.example.vansh.wdyw.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.adapter.Radio;
import app.com.example.vansh.wdyw.adapter.RecyclerViewAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class BorrowerProfile extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        ButterKnife.bind(this);

        initializeViewsAdapter();
        loadData();

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        final VideoView videoView =
                (VideoView) findViewById(R.id.myvideoview);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.vid;
        videoView.setVideoPath(uriPath);

        videoView.start();

    }

    /**
     * Initializes views and adapter
     */
    private void initializeViewsAdapter(){

        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setTitle("My WdyW");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    /**
     * load mock data to adapter
     */
    private void loadData(){
        Radio radio = new Radio("AADHAR CARD", R.drawable.ver, "OK");
        Radio radio2 = new Radio("EMAIL", R.drawable.ver, "OK");
        Radio radio3 = new Radio("ADDRESS", R.drawable.ver, "OK");


        List<Radio> radioList = new ArrayList<>();


            radioList.add(radio);
        radioList.add(radio2);
        radioList.add(radio3);

        adapter.setRadioList(radioList);
    }

}
