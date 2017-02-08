package app.com.example.vansh.wdyw.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.model.Data;
import app.com.example.vansh.wdyw.model.LenderProfileGet;
import app.com.example.vansh.wdyw.model.ProfilePic;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.Consts;
import app.com.example.vansh.wdyw.utility.DataFetch;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import app.com.example.vansh.wdyw.utility.Preferences;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LProfileActivity extends AppCompatActivity
    implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    public Toolbar mToolbar;

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

    @Bind(R.id.type)
    TextView type;

    @Bind(R.id.sex)
    TextView sex;

    @Bind(R.id.credit)
    TextView credit;

    @Bind(R.id.load)
    ImageView load;

    @Bind(R.id.quote)
    TextView quote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lprofile_activity_main);

        bindActivity();
        ButterKnife.bind(this);


        mAppBarLayout.addOnOffsetChangedListener(this);

        mToolbar.inflateMenu(R.menu.profile_menu_main);
        setSupportActionBar(mToolbar);

        startAlphaAnimation(mTitle, 0, View.INVISIBLE);



        final ApiInterface apiService =
                ApiClient.getClient(this).create(ApiInterface.class);


        final ProgressDialog dialog = new ProgressDialog(LProfileActivity.this,R.style.AppTheme_Dark_Dialog);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        Call<LenderProfileGet> call = apiService.profile();

        call.enqueue(new Callback<LenderProfileGet>() {
            @Override
            public void onResponse(Call<LenderProfileGet> call, final Response<LenderProfileGet> response) {
                Data data=new Data();
                data=response.body().getData();
                DataFetch.fetchImage(data.getLender().getProPic().toString(), LProfileActivity.this, load);

                profile.setText(data.getLender().getName().toString());
                profile2.setText(data.getLender().getName().toString());
                subhead.setText(data.getLender().getEmail().toString());
                phoneno.setText(data.getLender().getPhone().toString());
                address.setText(data.getLender().getAddress().toString());
                city.setText(data.getLender().getCity().toString());
                state.setText(data.getLender().getState().toString());
                credit.setText(data.getLender().getCredit().toString());
                type.setText(data.getLender().getType().toString());
                sex.setText(data.getLender().getSex().toString());
                quote.setText(data.getLender().getQuote().toString());




                dialog.hide();


            }


            @Override
            public void onFailure(Call<LenderProfileGet> call, Throwable t) {
                dialog.hide();
                DialogUtil.createDialog("Oops! Please check your internet connection!", LProfileActivity.this, new DialogUtil.OnPositiveButtonClick() {
                    @Override
                    public void onClick() {
                    }
                });
                // Log error here since request failed
                Log.e("Error", t.toString());
            }
        });



        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);*/
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code



            }
        });

    }


    public void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.main_toolbar);
        mTitle          = (TextView) findViewById(R.id.title_profile);
        mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.main_appbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.home:
                Intent i = new Intent(LProfileActivity.this,LenderMainActivity.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "This is a cool STARTUP", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
                Intent j = new Intent(LProfileActivity.this,CheckCredit.class);
                startActivity(j);
                Toast.makeText(getBaseContext(), "This is a cool STARTUP", Toast.LENGTH_SHORT).show();
                break;



        }
        return super.onOptionsItemSelected(item);

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

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    load.setImageURI(selectedImage);
                    String filepath=getRealPathFromURI(this,selectedImage);


                    try {
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
                        byte[] b = stream.toByteArray();

                        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);



                        final ProfilePic profilePic = new ProfilePic();
                        final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
                        profilePic.setFileType(filepath.substring(filepath.lastIndexOf(".") + 1));
                       // profilePic.setData(Preferences.getPrefs(Consts.P_SP_KEY.toString(),this));
                        profilePic.setImage(encodedImage);


                        Call<ProfilePic> call = apiInterface.pic(profilePic);

                        final ProgressDialog dialog = new ProgressDialog(LProfileActivity.this,R.style.AppTheme_Dark_Dialog);
                        dialog.setMessage("Uploading Photo...");
                        dialog.setIndeterminate(true);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();

                        call.enqueue(new Callback<ProfilePic>() {
                            @Override
                            public void onResponse(Call<ProfilePic> call, Response<ProfilePic> response) {

                                dialog.hide();
                                DialogUtil.createDialog("Upload Successful", LProfileActivity.this, new DialogUtil.OnPositiveButtonClick() {
                                    @Override
                                    public void onClick() {
                                        finish();

                                    }
                                });


                            }

                            @Override
                            public void onFailure(Call<ProfilePic> call, Throwable t) {
                                dialog.hide();
                                DialogUtil.createDialog("Oops! Please check your internet connection!", LProfileActivity.this, new DialogUtil.OnPositiveButtonClick() {
                                    @Override
                                    public void onClick() {
                                    }
                                });
                            }
                        });



                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
                break;
        }
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
