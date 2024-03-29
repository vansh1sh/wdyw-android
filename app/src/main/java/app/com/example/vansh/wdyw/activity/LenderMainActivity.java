package app.com.example.vansh.wdyw.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.adapter.LoanDetailsAdapter;
import app.com.example.vansh.wdyw.model.BloanResponse;
import app.com.example.vansh.wdyw.model.BorrowerLoanData;
import app.com.example.vansh.wdyw.model.Lender;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.Consts;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import app.com.example.vansh.wdyw.utility.Preferences;
import app.com.example.vansh.wdyw.utility.RevealActivity;
import co.geeksters.googleplaceautocomplete.lib.CustomAutoCompleteTextView;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LenderMainActivity extends RevealActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    String city="Vellore";
    String cit="";
    Integer flag =0;
    String  pageid="1";
    String loanAmt="3000";
    LinearLayout ll;
    private Bundle mSavedInstanceState;

    ImageView prev, next;
    TextView pageno;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lender_activity_main);
        showRevealEffect(mSavedInstanceState, findViewById(R.id.la));
        city=Preferences.getPrefs(Consts.DEFAULT_CITY,LenderMainActivity.this);
        pageid=Preferences.getPrefs(Consts.DEFAULT_PAGE,LenderMainActivity.this);


        ll = (LinearLayout)findViewById(R.id.ll);
        pageno = (TextView) findViewById(R.id.pageno);

        pageno.setText("Page "+pageid);


        Preferences.setPrefs(Consts.AUTO_LOGIN,"lender",this);


        if(Preferences.getPrefs(Consts.CHECK_BORROWER,LenderMainActivity.this).equals("Yes")) {//getextra
            Intent intent = getIntent();
            city = intent.getStringExtra("city");
            loanAmt = intent.getStringExtra("loan");
            Preferences.setPrefs(Consts.CHECK_BORROWER, "No", LenderMainActivity.this);

        }


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        ImageView gi = (ImageView) findViewById(R.id.gotit);
        prev = (ImageView) findViewById(R.id.actionprev);
        next  = (ImageView) findViewById(R.id.actionnext);


        gi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(LenderMainActivity.this,R.anim.abc_fade_out);
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
                int myNum = Integer.parseInt(Preferences.getPrefs(Consts.DEFAULT_PAGE,LenderMainActivity.this));
                if (myNum>1)
                {myNum=myNum-1;
                    pageid= Integer.toString(myNum);
                    Preferences.setPrefs(Consts.DEFAULT_PAGE, Integer.toString(myNum),LenderMainActivity.this);
                    //Toast.makeText(getBaseContext(), "Page: "+Integer.toString(myNum), Toast.LENGTH_SHORT).show();


                    Intent it=new Intent(LenderMainActivity.this,BorrowerMainActivity.class);
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
                //Toast.makeText(getBaseContext(), "This is a cool STARTUP", st.LENGTH_SHORT).show();


            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int myNum2 = Integer.parseInt(Preferences.getPrefs(Consts.DEFAULT_PAGE,LenderMainActivity.this));
                myNum2=myNum2+1;
                pageid= Integer.toString(myNum2);
                Toast.makeText(getBaseContext(), "Page: "+Integer.toString(myNum2), Toast.LENGTH_SHORT).show();

                Preferences.setPrefs(Consts.DEFAULT_PAGE, Integer.toString(myNum2),LenderMainActivity.this);
                Intent it=new Intent(LenderMainActivity.this,BorrowerMainActivity.class);
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
       /* recyclerView.setItemAnimator(new SlideInLeftAnimator());*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        final ApiInterface apiService =
                ApiClient.getClient(this).create(ApiInterface.class);


        final ProgressDialog dialog = new ProgressDialog(LenderMainActivity.this,R.style.AppTheme_Dark_Dialog);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        Call<BloanResponse> call = apiService.loandetails(city,pageid,loanAmt);

        call.enqueue(new Callback<BloanResponse>() {
            @Override
            public void onResponse(Call<BloanResponse> call, final Response<BloanResponse> response) {

                List<BorrowerLoanData> sold = response.body().getData();
                recyclerView.setAdapter(new LoanDetailsAdapter(sold, R.layout.list_item_loan, getApplicationContext()));
                dialog.hide();


            }


            @Override
            public void onFailure(Call<BloanResponse> call, Throwable t) {
                dialog.hide();
                DialogUtil.createDialog("Oops! Please check your internet connection!", LenderMainActivity.this, new DialogUtil.OnPositiveButtonClick() {
                    @Override
                    public void onClick() {
                    }
                });
                // Log error here since request failed
                Log.e("Error", t.toString());
            }

        });



        /**
         *Setup the DrawerLayout and NavigationView
         */
             mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
             mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;


        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

             mFragmentManager = getSupportFragmentManager();
             mFragmentTransaction = mFragmentManager.beginTransaction();
        /**
         * Setup click events on the Navigation View Items.
         */

             mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();



                 if (menuItem.getItemId() == R.id.nav_item_home) {
                     Intent i = new Intent(LenderMainActivity.this,LenderMainActivity.class);
                     startActivity(i);
                 }
                 if (menuItem.getItemId() == R.id.nav_item_profile) {
                     Intent i = new Intent(LenderMainActivity.this,LProfileActivity.class);
                     startActivity(i);
                 }

                if (menuItem.getItemId() == R.id.how) {
                    final Dialog dialog = new Dialog(LenderMainActivity.this);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.setContentView(R.layout.dialog_how_it_works);
                    dialog.show();
                     }
                 if (menuItem.getItemId() == R.id.loans) {
                     Intent i = new Intent(LenderMainActivity.this,LenderLendActivity.class);
                     startActivity(i);
                 }
                 if (menuItem.getItemId() == R.id.checkcredits) {

                     Toast.makeText(getBaseContext(), "Coming Soon!", Toast.LENGTH_SHORT).show();
                     /*Intent i = new Intent(LenderMainActivity.this,CheckCredit.class);
                     startActivity(i);*/
                 }
                 if (menuItem.getItemId() == R.id.terms) {
                     final Dialog dialog = new Dialog(LenderMainActivity.this);
                     dialog.setCanceledOnTouchOutside(true);
                     dialog.setContentView(R.layout.dialog_terms);
                     dialog.show();
                     }

                 if (menuItem.getItemId() == R.id.contactus) {
                     final Dialog dialog = new Dialog(LenderMainActivity.this);
                     dialog.setCanceledOnTouchOutside(true);
                     dialog.setContentView(R.layout.dialog_contact);
                     dialog.show();

                 }


                 return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */









        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbarlender);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, mToolbar,R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_logo);



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

                Intent i = new Intent(LenderMainActivity.this,FirstPage.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "Signed Out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_filter:

                openDialogSelect();
                //Toast.makeText(getBaseContext(), "This is a cool STARTUP", Toast.LENGTH_SHORT).show();
                break;
           /* case R.id.action_prev:

                int myNum = Integer.parseInt(Preferences.getPrefs(Consts.DEFAULT_PAGE,LenderMainActivity.this));
                if (myNum>1)
                {myNum=myNum-1;
                    pageid= Integer.toString(myNum);
                    Preferences.setPrefs(Consts.DEFAULT_PAGE, Integer.toString(myNum),LenderMainActivity.this);
                    Toast.makeText(getBaseContext(), "Page: "+Integer.toString(myNum), Toast.LENGTH_SHORT).show();

                    Intent it=new Intent(LenderMainActivity.this,LenderMainActivity.class);
                    startActivity(it);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Already On The First Page", Toast.LENGTH_SHORT).show();

                }
                //Toast.makeText(getBaseContext(), "This is a cool STARTUP", Toast.LENGTH_SHORT).show();
                break;


            case R.id.action_next:
                int myNum2 = Integer.parseInt(Preferences.getPrefs(Consts.DEFAULT_PAGE,LenderMainActivity.this));
                myNum2=myNum2+1;
                pageid= Integer.toString(myNum2);
                Toast.makeText(getBaseContext(), "Page: "+Integer.toString(myNum2), Toast.LENGTH_SHORT).show();

                Preferences.setPrefs(Consts.DEFAULT_PAGE, Integer.toString(myNum2),LenderMainActivity.this);
                Intent it=new Intent(LenderMainActivity.this,LenderMainActivity.class);
                startActivity(it);
                //Toast.makeText(getBaseContext(), "This is a cool STARTUP", Toast.LENGTH_SHORT).show();
                break;
*/


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


                try {
                    cit = customAutoCompleteTextView.googlePlace.getCity(); //Return the city
                } catch (Exception e) {
                    cit="empty";
                    e.printStackTrace();
                }


                if (cit.equals("empty")){


                    DialogUtil.createDialog("Please Select The City Properly From The List!", LenderMainActivity.this, new DialogUtil.OnPositiveButtonClick() {
                        @Override
                        public void onClick() {
                            finish();
                        }
                    });


                }

                else {

                    Preferences.setPrefs(Consts.CHECK_BORROWER, "Yes", LenderMainActivity.this);
                    String cit = customAutoCompleteTextView.googlePlace.getCity(); //Return the city
                    Intent it = new Intent(LenderMainActivity.this, LenderMainActivity.class);
                    it.putExtra("city", cit);
                    it.putExtra("loan", amount.getText().toString());
                    startActivity(it);
                }

            }
        });

    }


}
