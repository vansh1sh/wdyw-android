package app.com.example.vansh.wdyw.activity;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.model.LSignupRequest;
import app.com.example.vansh.wdyw.model.Occupation;
import app.com.example.vansh.wdyw.model.Quote;
import app.com.example.vansh.wdyw.model.Type;
import app.com.example.vansh.wdyw.model.User;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LSignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    String stf="ds";
    String stf2="gg";
    String sta="gds";
    String stl="dsf";
    String stf1="sdf";
    String acc1;
    String des1;
    String web1;
    String ch;
    User us;
    Type ty;
    Type tya;
    Type tyl;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.input_user)
    Button button;
    @Bind(R.id.link_login)
    TextView _loginLink;
    String check="No";




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lender_activity_signup);
        ButterKnife.bind(this);
        us=new User();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialoguser();
            }
        });


        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Initializing a String Array
        String[] plants = new String[]{
                "Select Type...",
                "Financial Institution",
                "Agent",
                "Local"
        };
        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plantsList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                    if (position > 0) {
                        if (selectedItemText.equals("Financial Institution")) {
                            ch = selectedItemText;
                            us.setType(ch);

                            openDialogfin();


                        }
                        if (selectedItemText.equals("Agent")) {
                            ch = selectedItemText;
                            us.setType(ch);

                            openDialogagent();

                        }
                        if (selectedItemText.equals("Local")) {
                            ch = selectedItemText;
                            us.setType(ch);

                            openDialoglocal();

                        }
                        // Notify the selected item text
                        Toast.makeText
                                (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                                .show();
                    }
                }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");


        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LSignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();



        final LSignupRequest BS = new LSignupRequest();
        final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);

                BS.setUser(us);
        if (ch.equals("Financial Institution")){

                    BS.setType(ty);
                    BS.setAccHolderName(acc1);
                    BS.setDesignation(des1);
                    BS.setRecognization(stf1);
                    BS.setWebsite(web1);}
        if (ch.equals("Agent")) {
            BS.setType(tya);
        }
        if (ch.equals("Local")) {
            BS.setType(tyl);
        }



            Call<LSignupRequest> call = apiInterface.L_SIGNUP_REQUEST_CALL(BS);

                final ProgressDialog dialog2 = new ProgressDialog(LSignupActivity.this);
                dialog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog2.setMessage("Adding the item to your inventory");
                dialog2.setIndeterminate(true);
                dialog2.setCanceledOnTouchOutside(false);
                dialog2.show();


                call.enqueue(new Callback<LSignupRequest>() {
                    @Override
                    public void onResponse(Call<LSignupRequest> call, Response<LSignupRequest> response) {
                        dialog2.hide();
                        // if (response.body().getCode().equals(Consts.SUCCESS)){
                        //   Toast.makeText(getBaseContext(), "Username exists", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(LSignupActivity.this, BorrowerLoginActivity.class);
                        startActivity(intent);
                    }


                    @Override
                    public void onFailure(Call<LSignupRequest> call, Throwable t) {
                        dialog2.hide();
                        DialogUtil.createDialog("Oops! Please check your internet connection!", LSignupActivity.this, new DialogUtil.OnPositiveButtonClick() {
                            @Override
                            public void onClick() {
                            }
                        });
                    }
                });

    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }



    public void openDialogfin(){
        stf="Financial Institution";

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_fin);
        Button cancel=(Button) dialog.findViewById(R.id.dialog_cancel);
        Button submit=(Button) dialog.findViewById(R.id.dialog_submit);




        final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);

        // Initializing a String Array
        String[] plants = new String[]{
                "Select an Organization...",
                "Bank",
                "NBFC",
                "NGO",
                "Private",
                "Others"
        };
        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plantsList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                stf2=selectedItemText;
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){

                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        final Spinner spinner1 = (Spinner) dialog.findViewById(R.id.spinner1);

        // Initializing a String Array
        String[] plantss = new String[]{
                "Select Recognization...",
                "Pan India",
                "recognization",
                "locals"
        };
        final List<String> plantsLists = new ArrayList<>(Arrays.asList(plantss));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapters = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plantsLists){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapters.setDropDownViewResource(R.layout.spinner_item);
        spinner1.setAdapter(spinnerArrayAdapters);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                stf1=selectedItemText;
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){


                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        final EditText acc=(EditText) dialog.findViewById(R.id.input_acc);
        final EditText des=(EditText) dialog.findViewById(R.id.input_des);
        final EditText web=(EditText) dialog.findViewById(R.id.input_website);



        dialog.setTitle("Product Details");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setLayout(lp.width, lp.height);
        dialog.show();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                acc1=acc.getText().toString();
                des1=des.getText().toString();
                web1=web.getText().toString();

                ty=new Type();
                ty.setOrganization(stf2);

                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


     public void openDialogagent(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_agent);
        Button cancel=(Button) dialog.findViewById(R.id.dialog_cancel);
        Button submit=(Button) dialog.findViewById(R.id.dialog_submit);




         final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);

         // Initializing a String Array
         String[] plants = new String[]{
                 "Select a Type...",
                 "Bank",
                 "Independent",
                 "Other"
         };
         final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

         // Initializing an ArrayAdapter
         final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                 this,R.layout.spinner_item,plantsList){
             @Override
             public boolean isEnabled(int position){
                 if(position == 0)
                 {
                     // Disable the first item from Spinner
                     // First item will be use for hint
                     return false;
                 }
                 else
                 {
                     return true;
                 }
             }
             @Override
             public View getDropDownView(int position, View convertView,
                                         ViewGroup parent) {
                 View view = super.getDropDownView(position, convertView, parent);
                 TextView tv = (TextView) view;
                 if(position == 0){
                     // Set the hint text color gray
                     tv.setTextColor(Color.GRAY);
                 }
                 else {
                     tv.setTextColor(Color.BLACK);
                 }
                 return view;
             }
         };
         spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
         spinner.setAdapter(spinnerArrayAdapter);

         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 String selectedItemText = (String) parent.getItemAtPosition(position);
                 sta=selectedItemText;
                 // If user change the default selection
                 // First item is disable and it is used for hint
                 if(position > 0){

                     if (selectedItemText.equals("Financial Institution")){
                         openDialogfin();


                     }
                     if (selectedItemText.equals("Agent")){
                         openDialogagent();

                     }
                     if (selectedItemText.equals("Local")){
                         openDialoglocal();
                     }
                     // Notify the selected item text
                     Toast.makeText
                             (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                             .show();
                 }
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });



         final EditText aadhar=(EditText) dialog.findViewById(R.id.input_aadhaar);
        final EditText ear=(EditText) dialog.findViewById(R.id.input_earning);



        dialog.setTitle("Product Details");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setLayout(lp.width, lp.height);
        dialog.show();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tya=new Type();
                tya.setAdharNo(aadhar.getText().toString());
                Integer myNum = Integer.parseInt(ear.getText().toString());
                tya.setEarning(myNum);
                tya.setType(sta);
                Log.i("STA",sta);
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void openDialoglocal(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_local);
        Button cancel=(Button) dialog.findViewById(R.id.dialog_cancel);
        Button submit=(Button) dialog.findViewById(R.id.dialog_submit);


        final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);

        // Initializing a String Array
        String[] plants = new String[]{
                "Select Occupation...",
                "Goverment",
                "Army",
                "Finanace",
                "Lawyer",
                "Doctor",
                "Engineer",
                "Buissness",
                "Other"
        };
        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,plantsList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             final String selectedItemText = (String) parent.getItemAtPosition(position);
                    stl=selectedItemText;
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){

                    if (selectedItemText.equals("Financial Institution")){
                        openDialogfin();


                    }
                    if (selectedItemText.equals("Agent")){
                        openDialogagent();

                    }
                    if (selectedItemText.equals("Local")){
                        openDialoglocal();
                    }
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        final EditText income=(EditText) dialog.findViewById(R.id.input_income);
        final EditText email=(EditText) dialog.findViewById(R.id.input_email);
        final EditText aadhar=(EditText) dialog.findViewById(R.id.input_aadhaar);


        dialog.setTitle("Product Details");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setLayout(lp.width, lp.height);
        dialog.show();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Occupation oc=new Occupation();
                oc.setType(stl);
                oc.setAdharNo(aadhar.getText().toString());
                oc.setEmail(email.getText().toString());
                Integer myNum = Integer.parseInt(income.getText().toString());

                oc.setIncome(myNum);



                tyl=new Type();
                tyl.setOccupation(oc);
                dialog.dismiss();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void openDialoguser(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_user);
        Button cancel=(Button) dialog.findViewById(R.id.dialog_cancel);
        Button submit=(Button) dialog.findViewById(R.id.dialog_submit);



        final EditText name=(EditText) dialog.findViewById(R.id.input_name);
        final EditText phone=(EditText) dialog.findViewById(R.id.input_phone);
        final EditText address=(EditText) dialog.findViewById(R.id.input_address);
        final EditText city=(EditText) dialog.findViewById(R.id.input_city);
        final EditText state=(EditText) dialog.findViewById(R.id.input_state);
        final EditText sex=(EditText) dialog.findViewById(R.id.input_sex);
        final EditText email=(EditText) dialog.findViewById(R.id.input_email);
        final EditText pass=(EditText) dialog.findViewById(R.id.input_password);
        final EditText min=(EditText) dialog.findViewById(R.id.input_min);
        final EditText max=(EditText) dialog.findViewById(R.id.max);


        dialog.setTitle("Product Details");
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setLayout(lp.width, lp.height);
        dialog.show();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                us.setName(name.getText().toString());
                Integer myNum = Integer.parseInt(phone.getText().toString());
                us.setPhone(myNum);
                us.setAddress(address.getText().toString());
                us.setCity(city.getText().toString());
                us.setState(state.getText().toString());
                us.setSex(sex.getText().toString());
                us.setEmail(email.getText().toString());
                us.setPassword(pass.getText().toString());



                Quote qu=new Quote();
                Integer max1 = Integer.parseInt(max.getText().toString());
                Integer min1 = Integer.parseInt(min.getText().toString());
                qu.setMax(max1);
                qu.setMin(min1);

                us.setQuote(qu);


                dialog.dismiss();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}