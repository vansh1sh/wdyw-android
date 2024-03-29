package app.com.example.vansh.wdyw.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.adapter.LenderDetailsAdapter;
import app.com.example.vansh.wdyw.model.LenderData;
import app.com.example.vansh.wdyw.model.LenderDetails;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.Consts;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import app.com.example.vansh.wdyw.utility.Preferences;
import co.geeksters.googleplaceautocomplete.lib.CustomAutoCompleteTextView;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowerMainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    String city="Vellore";
    String  pageid="1";
    Integer flag =0;
    String loanAmt="3000";
    LinearLayout ll;
    String cit;
    TextView pageno;
    ImageView prev,next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city=Preferences.getPrefs(Consts.DEFAULT_CITY,BorrowerMainActivity.this);
        pageid=Preferences.getPrefs(Consts.DEFAULT_PAGE,BorrowerMainActivity.this);

        ll = (LinearLayout) findViewById(R.id.ll);
        prev = (ImageView) findViewById(R.id.actionprev);
        next  = (ImageView) findViewById(R.id.actionnext);
        pageno = (TextView ) findViewById(R.id.pageno);
        pageno.setText("Page "+pageid);


        Preferences.setPrefs(Consts.AUTO_LOGIN,"borrower",this);

        if(Preferences.getPrefs(Consts.CHECK_BORROWER,BorrowerMainActivity.this).equals("Yes")) {//getextra
            Intent intent = getIntent();
            city = intent.getStringExtra("city");
            loanAmt = intent.getStringExtra("loan");
            Preferences.setPrefs(Consts.CHECK_BORROWER, "No", BorrowerMainActivity.this);

        }




        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }





        /**
         *Setup the DrawerLayout and NavigationView
         */
             mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
             mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;



             mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();



                 if (menuItem.getItemId() == R.id.nav_item_home) {
                     Intent i = new Intent(BorrowerMainActivity.this,BorrowerMainActivity.class);
                     startActivity(i);
                 }
                 if (menuItem.getItemId() == R.id.nav_item_profile) {
                     Intent i = new Intent(BorrowerMainActivity.this,BProfileActivity.class);
                     startActivity(i);
                 }

                 if (menuItem.getItemId() == R.id.nav_lender) {
                     Intent i = new Intent(BorrowerMainActivity.this,BorrowerLenderActivity.class);
                     startActivity(i);
                 }
                if (menuItem.getItemId() == R.id.nav_item_loan) {
                    Intent i = new Intent(BorrowerMainActivity.this,BorrowerLoanPostActivity.class);
                    startActivity(i);}

                 if (menuItem.getItemId() == R.id.how) {
                     final Dialog dialog = new Dialog(BorrowerMainActivity.this);
                     dialog.setCanceledOnTouchOutside(true);
                     dialog.setContentView(R.layout.dialog_how_it_works);
                     dialog.show();}
                 if (menuItem.getItemId() == R.id.terms) {

                     final Dialog dialog = new Dialog(BorrowerMainActivity.this);
                     dialog.setCanceledOnTouchOutside(true);
                     dialog.setContentView(R.layout.dialog_terms);
                     dialog.show();
                     // Intent i = new Intent(BorrowerMainActivity.this,TermsActivity.class);
                     //startActivity(i);
                 }

                 if (menuItem.getItemId() == R.id.contactus) {
                     final Dialog dialog = new Dialog(BorrowerMainActivity.this);
                     dialog.setCanceledOnTouchOutside(true);
                     dialog.setContentView(R.layout.dialog_contact);
                     dialog.show();}
                 return false;

            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbarborrower);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, mToolbar,R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_logo);

        ImageView gi = (ImageView) findViewById(R.id.gotit);
        gi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(BorrowerMainActivity.this,R.anim.abc_fade_out);
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
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int myNum = Integer.parseInt(Preferences.getPrefs(Consts.DEFAULT_PAGE,BorrowerMainActivity.this));
                flag =1;
                if (myNum>1)
                {myNum=myNum-1;
                    pageid= Integer.toString(myNum);
                    Preferences.setPrefs(Consts.DEFAULT_PAGE, Integer.toString(myNum),BorrowerMainActivity.this);
                    //Toast.makeText(getBaseContext(), "Page: "+Integer.toString(myNum), Toast.LENGTH_SHORT).show();


                    Intent it=new Intent(BorrowerMainActivity.this,BorrowerMainActivity.class);
                    startActivity(it);
                    if (flag==1)
                    {
                        flag =0;
                        pageno.setText("Page "+Integer.toString(myNum));
                    }


                }
                else
                {
                    Toast.makeText(getBaseContext(), "Already On The First Page", Toast.LENGTH_SHORT).show();

                }
                //Toast.makeText(getBaseContext(), "This is a cool STARTUP", Toast.LENGTH_SHORT).show();


            }




        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int myNum2 = Integer.parseInt(Preferences.getPrefs(Consts.DEFAULT_PAGE,BorrowerMainActivity.this));
                flag=2;
                myNum2=myNum2+1;
                pageid= Integer.toString(myNum2);
                //Toast.makeText(getBaseContext(), "Page: "+Integer.toString(myNum2), Toast.LENGTH_SHORT).show();


                Preferences.setPrefs(Consts.DEFAULT_PAGE, Integer.toString(myNum2),BorrowerMainActivity.this);
                Intent it=new Intent(BorrowerMainActivity.this,BorrowerMainActivity.class);
                startActivity(it);
                //Toast.makeText(getBaseContext(), "This is a cool STARTUP", Toast.LENGTH_SHORT).show();
                //pageno.setText("Page "+Integer.toString(myNum2));

                if (flag==2)
                {
                    flag =0;
                    pageno.setText("Page "+Integer.toString(myNum2));
                }

            }
        });

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.stock_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        final ApiInterface apiService =
                ApiClient.getClient(this).create(ApiInterface.class);


        final ProgressDialog dialog = new ProgressDialog(BorrowerMainActivity.this,R.style.AppTheme_Dark_Dialog);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();



       Call<LenderData> call = apiService.lenderDetails(city,pageid,loanAmt);

        call.enqueue(new Callback<LenderData>() {
            @Override
            public void onResponse(Call<LenderData> call, final Response<LenderData> response) {

                List<LenderDetails> sold = response.body().getData();
                LenderDetailsAdapter adapter=new LenderDetailsAdapter(sold, R.layout.list_item_lender_details, getApplicationContext());
                dialog.hide();
                SlideInRightAnimationAdapter sd= new SlideInRightAnimationAdapter(adapter);
                sd.setFirstOnly(true);
                sd.setDuration(500);
                sd.setInterpolator(new OvershootInterpolator(.5f));
                recyclerView.setAdapter(adapter);


            }


            @Override
            public void onFailure(Call<LenderData> call, Throwable t) {
                dialog.hide();
                DialogUtil.createDialog("Oops! Please check your internet connection!", BorrowerMainActivity.this, new DialogUtil.OnPositiveButtonClick() {
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

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.action_settings:
                Preferences.setPrefs(Consts.AUTO_LOGIN,"user",this);
                Intent i = new Intent(BorrowerMainActivity.this,FirstPage.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "Signed Out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_filter:

                openDialogSelect();
                //Toast.makeText(getBaseContext(), "This is a cool STARTUP", Toast.LENGTH_SHORT).show();
                break;






        }
        return true;

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void openDialogSelect() {
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_filter);
        dialog.show();
        final EditText amount=(EditText)dialog.findViewById(R.id.amountdialog);
        final CustomAutoCompleteTextView customAutoCompleteTextView = (CustomAutoCompleteTextView)dialog.findViewById(R.id.atv_places);

        Button save=(Button) dialog.findViewById(R.id.dialogsave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.setPrefs(Consts.CHECK_BORROWER,"Yes",BorrowerMainActivity.this);


                try {
                    cit = customAutoCompleteTextView.googlePlace.getCity(); //Return the city
                } catch (Exception e) {
                    cit="empty";
                    e.printStackTrace();
                }


                if (cit.equals("empty")){


                    DialogUtil.createDialog("Please Select The City Properly From The List!", BorrowerMainActivity.this, new DialogUtil.OnPositiveButtonClick() {
                        @Override
                        public void onClick() {
                            finish();
                        }
                    });


                }

                else {
                    Intent it = new Intent(BorrowerMainActivity.this, BorrowerMainActivity.class);
                    it.putExtra("city", cit);
                    it.putExtra("loan", amount.getText().toString());
                    startActivity(it);
                }

            }
        });

    }

    }
