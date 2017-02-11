package app.com.example.vansh.wdyw.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.adapter.BLenderDetailsAdapter;
import app.com.example.vansh.wdyw.adapter.LLentDetailsAdapter;
import app.com.example.vansh.wdyw.adapter.LenderDetailsAdapter;
import app.com.example.vansh.wdyw.model.LLentResponse;
import app.com.example.vansh.wdyw.model.LenderData;
import app.com.example.vansh.wdyw.model.LenderDetails;
import app.com.example.vansh.wdyw.model.LlentDatum;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowerLenderActivity extends AppCompatActivity {

    String city="vellore";
    String  pageid="1";
    String loanAmt="3000";
    CardView cd;
    LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lender);

        Toolbar t=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cd = (CardView) findViewById(R.id.card);
        ll = (LinearLayout) findViewById(R.id.ll);


        Button gi = (Button) findViewById(R.id.gotit);
        gi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(BorrowerLenderActivity.this,R.anim.abc_fade_out);
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


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.stock_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final ApiInterface apiService =
                ApiClient.getClient(this).create(ApiInterface.class);


        final ProgressDialog dialog = new ProgressDialog(BorrowerLenderActivity.this, R.style.AppTheme_Dark_Dialog);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        Call<LLentResponse> call = apiService.LenderLend();

        call.enqueue(new Callback<LLentResponse>() {
            @Override
            public void onResponse(Call<LLentResponse> call, final Response<LLentResponse> response) {

                List<LlentDatum> sold = response.body().getData();
                BLenderDetailsAdapter adapter=new BLenderDetailsAdapter(sold, R.layout.list_item_lenderlent, getApplicationContext());
                dialog.hide();
                SlideInRightAnimationAdapter alphaAdapter = new SlideInRightAnimationAdapter(adapter);
                alphaAdapter.setDuration(400);
                //alphaAdapter.setInterpolator(new OvershootInterpolator());
                alphaAdapter.setFirstOnly(false);
                recyclerView.setAdapter(alphaAdapter);

            }


            @Override
            public void onFailure(Call<LLentResponse> call, Throwable t) {
                dialog.hide();
                DialogUtil.createDialog("Oops! Please check your internet connection!", BorrowerLenderActivity.this, new DialogUtil.OnPositiveButtonClick() {
                    @Override
                    public void onClick() {
                    }
                });
                // Log error here since request failed
                Log.e("Error", t.toString());
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
