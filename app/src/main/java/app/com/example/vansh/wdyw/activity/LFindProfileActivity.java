package app.com.example.vansh.wdyw.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.model.BData;
import app.com.example.vansh.wdyw.model.BorrowerProfileGet;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.DataFetch;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LFindProfileActivity extends AppCompatActivity
    implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    @Bind(R.id.prof)
    TextView profile;

   @Bind(R.id.title_profile)
    TextView profile2;

    @Bind(R.id.email)
    TextView subhead;

    @Bind(R.id.phoneno)
    TextView phoneno;

    @Bind(R.id.address)
    TextView address;

    @Bind(R.id.city)
    TextView city;

    @Bind(R.id.state)
    TextView state;

    @Bind(R.id.sex)
    TextView sex;


    @Bind(R.id.load)
    ImageView load;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bprofile_activity_main);

        bindActivity();
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String abc=intent.getStringExtra("id");


        mAppBarLayout.addOnOffsetChangedListener(this);

        mToolbar.inflateMenu(R.menu.menu_main);

        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        final ApiInterface apiService =
                ApiClient.getClient(this).create(ApiInterface.class);


        final ProgressDialog dialog = new ProgressDialog(LFindProfileActivity.this,R.style.AppTheme_Dark_Dialog);
        dialog.setMessage("Loading Profile...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        Call<BorrowerProfileGet> call = apiService.lenderviewprofile(abc);

        call.enqueue(new Callback<BorrowerProfileGet>() {
            @Override
            public void onResponse(Call<BorrowerProfileGet> call, final Response<BorrowerProfileGet> response) {
                BData data=response.body().getData();
                DataFetch.fetchImage(data.getProPic().toString(), LFindProfileActivity.this, load);

                profile.setText(data.getName().toString());
                profile2.setText(data.getName().toString());
                subhead.setText("Borrower");
                phoneno.setText(data.getPhone().toString());
                address.setText(data.getAddress().toString());
                //sex.setText(data.gettoString());
                city.setText(data.getCity().toString());
                //state.setText(data.getAddress().getState().toString());

                dialog.hide();


            }


            @Override
            public void onFailure(Call<BorrowerProfileGet> call, Throwable t) {
                dialog.hide();
                DialogUtil.createDialog("Oops! Please check your internet connection!", LFindProfileActivity.this, new DialogUtil.OnPositiveButtonClick() {
                    @Override
                    public void onClick() {
                    }
                });
                // Log error here since request failed
                Log.e("Error", t.toString());
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.main_toolbar);
        mTitle          = (TextView) findViewById(R.id.title_profile);
        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.main_appbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.home:
                Intent i = new Intent(LFindProfileActivity.this,LenderMainActivity.class);
                startActivity(i);
               // Toast.makeText(getBaseContext(), "This is a cool STARTUP", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
                Intent j = new Intent(LFindProfileActivity.this,CheckCredit.class);
                startActivity(j);
               // Toast.makeText(getBaseContext(), "This is a cool STARTUP", Toast.LENGTH_SHORT).show();
                break;



        }
        return true;

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
            if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

                if(!mIsTheTitleVisible) {
                    startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                    mIsTheTitleVisible = true;
                }

            } else {

                if (mIsTheTitleVisible) {
                    startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                    mIsTheTitleVisible = false;
                }
            }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
            ? new AlphaAnimation(0f, 1f)
            : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

}
