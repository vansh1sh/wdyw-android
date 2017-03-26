package app.com.example.vansh.wdyw.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.model.BSignupRequest;
import app.com.example.vansh.wdyw.model.BSignupResponse;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import co.geeksters.googleplaceautocomplete.lib.CustomAutoCompleteTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowerSignupActivity extends AppCompatActivity {
    private static final String TAG = "BorrowerSignupActivity";


    String ch;
    String gender;
    @Bind(R.id.input_name)
    EditText _nameText;
    @Bind(R.id.input_phone)
    EditText _phone;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.input_address)
    EditText _address;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.link_login)
    TextView _loginLink;

    String cit="";
    ProgressDialog dialog2;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Initializing a String Array
        String[] plantss = new String[]{
                "Select Gender...",
                "Male",
                "Female",
                "Transgender"
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
        spinner2.setAdapter(spinnerArrayAdapters);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    gender=selectedItemText;

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

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Initializing a String Array
        String[] plants = new String[]{
                "Select Profession...",
                "Salaried",
                "Self Employed",
                "Self Employed Business"
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
                if(position > 0){

                    if (selectedItemText.equals("Salaried")){

                        ch=selectedItemText;

                    }
                    if (selectedItemText.equals("Self Employed")){
                        ch=selectedItemText;

                    }
                    if (selectedItemText.equals("Self Employed Buissness")){
                        ch=selectedItemText;

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


        CustomAutoCompleteTextView customAutoCompleteTextView = (CustomAutoCompleteTextView) findViewById(R.id.atv_places);


            try {
                cit = customAutoCompleteTextView.googlePlace.getCity(); //Return the city
            } catch (Exception e) {
                cit="empty";
                e.printStackTrace();
            }


        if(_passwordText.getText().toString().isEmpty()|| _passwordText.length() < 4 || _passwordText.length() > 10)
            _passwordText.setError("between 4 and 10 alphanumeric characters");

        if (_nameText.getText().toString().isEmpty())
        _nameText.setError("Name cannot not be blank");

        if (_address.getText().toString().isEmpty())
        _address.setError("Address cannot be blank");

        if(_phone.length()!=10)
            _phone.setError("Number should have 10 digits");

        if (_nameText.getText().toString().isEmpty() || _passwordText.getText().toString().isEmpty() || _address.getText().toString().isEmpty() ||_phone.getText().toString().isEmpty()) {


            DialogUtil.createDialog("Please fill all the information Properly!", BorrowerSignupActivity.this, new DialogUtil.OnPositiveButtonClick() {



                @Override
                public void onClick() {
                    finish();
                }
            });
        }


        else {

            if (cit.equals("empty")){


                DialogUtil.createDialog("Please Select The City Properly From The List!", BorrowerSignupActivity.this, new DialogUtil.OnPositiveButtonClick() {
                    @Override
                    public void onClick() {
                        finish();
                    }
                });


            }

            else{



                if (!validate()) {
                    onSignupFailed();
                    return;


            }
            else {
            _signupButton.setEnabled(false);



                    final ProgressDialog progressDialog = new ProgressDialog(BorrowerSignupActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();

            final BSignupRequest BS = new BSignupRequest();
            final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);



            BS.setName(_nameText.getText().toString());
            BS.setPassword(_passwordText.getText().toString());
            BS.setAddress(_address.getText().toString());
            BS.setCity(cit);
            BS.setGender(gender);
            BS.setProfession(ch);


            Long myNum = Long.parseLong(_phone.getText().toString());
            BS.setPhone(myNum);


            Call<BSignupResponse> call = apiInterface.BSignUp(BS);

            dialog2 = new ProgressDialog(BorrowerSignupActivity.this, R.style.AppTheme_Dark_Dialog);
            dialog2.setMessage("Creating Account");
            dialog2.show();


            call.enqueue(new Callback<BSignupResponse>() {
                @Override
                public void onResponse(Call<BSignupResponse> call, Response<BSignupResponse> response) {
                    dialog2.dismiss();
                    // if (response.body().getCode().equals(Consts.SUCCESS)){
                    //   Toast.makeText(getBaseContext(), "Username exists", Toast.LENGTH_LONG).show();
                    if(response.body().getStatus().equals(Boolean.TRUE)){
                    Intent intent = new Intent(BorrowerSignupActivity.this, BorrowerLoginActivity.class);
                    startActivity(intent);}
                    else
                    {
                        dialog2.hide();
                        DialogUtil.createDialog("Some Error Occurred, Try With A Different Number", BorrowerSignupActivity.this, new DialogUtil.OnPositiveButtonClick() {
                            @Override
                            public void onClick() {
                                dialog2.dismiss();
                            }
                        });

                    }
                }


                @Override
                public void onFailure(Call<BSignupResponse> call, Throwable t) {
                    dialog2.hide();
                    DialogUtil.createDialog("Oops! Please check your internet connection!", BorrowerSignupActivity.this, new DialogUtil.OnPositiveButtonClick() {
                        @Override
                        public void onClick() {
                        }
                    });
                }
            });
        }}
    }}


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }


        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}