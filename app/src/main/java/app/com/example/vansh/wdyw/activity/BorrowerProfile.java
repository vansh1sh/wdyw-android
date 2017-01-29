package app.com.example.vansh.wdyw.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.model.Data;
import app.com.example.vansh.wdyw.model.LenderProfileGet;
import app.com.example.vansh.wdyw.model.LoanPostRequest;
import app.com.example.vansh.wdyw.model.ProfilePic;
import app.com.example.vansh.wdyw.model.Radio;
import app.com.example.vansh.wdyw.adapter.RecyclerViewAdapter;
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

public class BorrowerProfile extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    String data="sds";

    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Bind(R.id.but)
    Button b2;
    @Bind(R.id.img)
    ImageView imageView;


    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        ButterKnife.bind(this);


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        final VideoView videoView =
                (VideoView) findViewById(R.id.myvideoview);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.vid;
        videoView.setVideoPath(uriPath);

        videoView.start();


/*
       final ApiInterface apiService =
                ApiClient.getClient(this).create(ApiInterface.class);


        final ProgressDialog dialog = new ProgressDialog(BorrowerProfile.this,R.style.AppTheme_Dark_Dialog);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading Profile...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        Call<LenderProfileGet> call = apiService.profile2();

        call.enqueue(new Callback<LenderProfileGet>() {
            @Override
            public void onResponse(Call<LenderProfileGet> call, final Response<LenderProfileGet> response) {
                Data data=new Data();
                data=response.body().getData();
                //profile.setText(data.getLender().getName().toString());

                DataFetch.fetchImage(data.getLender().getProPic().toString(), BorrowerProfile.this, imageView);


                dialog.hide();


            }


            @Override
            public void onFailure(Call<LenderProfileGet> call, Throwable t) {
                dialog.hide();
                DialogUtil.createDialog("Oops! Please check your internet connection!", BorrowerProfile.this, new DialogUtil.OnPositiveButtonClick() {
                    @Override
                    public void onClick() {
                    }
                });
                // Log error here since request failed
                Log.e("Error", t.toString());
            }
        });

*/

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code



            }
        });


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    imageView.setImageURI(selectedImage);
                    String filepath=getRealPathFromURI(this,selectedImage);


                    try {
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
                        byte[] b = stream.toByteArray();

                        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                        data=encodedImage;
                        Log.i("sdfsf",data);



                        final ProfilePic profilePic = new ProfilePic();
                        final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);


                            profilePic.setFileType(filepath.substring(filepath.lastIndexOf(".") + 1));
                            //profilePic.setData(data);


                        Call<ProfilePic> call = apiInterface.pic(profilePic);

                            final ProgressDialog dialog = new ProgressDialog(BorrowerProfile.this);
                            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            dialog.setMessage("Uploading Photo...");
                            dialog.setIndeterminate(true);
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.show();

                            call.enqueue(new Callback<ProfilePic>() {
                                @Override
                                public void onResponse(Call<ProfilePic> call, Response<ProfilePic> response) {

                                    dialog.hide();
                                    DialogUtil.createDialog("Upload Successful", BorrowerProfile.this, new DialogUtil.OnPositiveButtonClick() {
                                        @Override
                                        public void onClick() {
                                            finish();

                                        }
                                    });


                                }

                                @Override
                                public void onFailure(Call<ProfilePic> call, Throwable t) {
                                    dialog.hide();
                                    DialogUtil.createDialog("Oops! Please check your internet connection!", BorrowerProfile.this, new DialogUtil.OnPositiveButtonClick() {
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
