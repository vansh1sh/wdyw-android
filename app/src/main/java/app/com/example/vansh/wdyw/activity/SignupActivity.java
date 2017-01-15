package app.com.example.vansh.wdyw.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";


    String ch;
    @Bind(R.id.input_name)
    EditText _nameText;
    @Bind(R.id.input_phone)
    EditText _phone;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.input_address)
    EditText _address;
    @Bind(R.id.input_city)
    EditText _city;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.link_login)
    TextView _loginLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Initializing a String Array
        String[] plants = new String[]{
                "Select Profession...",
                "Salaried",
                "Self Employed",
                "Self Employed Buissness"
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

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();



        final BSignupRequest BS = new BSignupRequest();
        final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);


                BS.setName(_nameText.getText().toString());
                BS.setPassword(_passwordText.getText().toString());
                BS.setAddress(_address.getText().toString());
                BS.setCity(_city.getText().toString());
                BS.setProfession(ch);


                Integer myNum = Integer.parseInt(_phone.getText().toString());
                BS.setPhone(myNum);



                Call<BSignupRequest> call = apiInterface.BSignUp(BS);

                final ProgressDialog dialog2 = new ProgressDialog(SignupActivity.this);
                dialog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog2.setMessage("Adding the item to your inventory");
                dialog2.setIndeterminate(true);
                dialog2.setCanceledOnTouchOutside(false);
                dialog2.show();


                call.enqueue(new Callback<BSignupRequest>() {
                    @Override
                    public void onResponse(Call<BSignupRequest> call, Response<BSignupRequest> response) {
                        dialog2.hide();
                        // if (response.body().getCode().equals(Consts.SUCCESS)){
                        //   Toast.makeText(getBaseContext(), "Username exists", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(SignupActivity.this, BorrowerLoginActivity.class);
                        startActivity(intent);
                    }


                    @Override
                    public void onFailure(Call<BSignupRequest> call, Throwable t) {
                        dialog2.hide();
                        DialogUtil.createDialog("Oops! Please check your internet connection!", SignupActivity.this, new DialogUtil.OnPositiveButtonClick() {
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