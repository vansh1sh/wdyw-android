package app.com.example.vansh.wdyw.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.model.LLoginRequest;
import app.com.example.vansh.wdyw.model.LLoginResponse;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.Consts;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import app.com.example.vansh.wdyw.utility.Preferences;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LLoginActivity extends AppCompatActivity {
    private static final String TAG = "BorrowerLoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Bind(R.id.input_phone)
    EditText phone;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Bind(R.id.link_signup)
    TextView _signupLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), LSignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if(phone.length()!=10)
            phone.setError("Number should have 10 digits");

        if(_passwordText.getText().toString().isEmpty()|| _passwordText.length() < 4 || _passwordText.length() > 10)
            _passwordText.setError("between 4 and 10 alphanumeric characters");

        if (phone.getText().toString().isEmpty() || _passwordText.getText().toString().isEmpty()) {
            DialogUtil.createDialog("Please Fill All the information!", LLoginActivity.this, new DialogUtil.OnPositiveButtonClick() {
                @Override
                public void onClick() {
                    finish();
                }
            });


        } else {


        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LLoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        LLoginRequest loginRequest = new LLoginRequest();
        loginRequest.setPassword(_passwordText.getText().toString());

        Long myNum = Long.parseLong(phone.getText().toString());

        loginRequest.setPhone(myNum);

        Call<LLoginResponse> call = apiInterface.L_LOGIN_REQUEST_CALL(loginRequest);

        call.enqueue(new Callback<LLoginResponse>() {
            @Override
            public void onResponse(Call<LLoginResponse> call, Response<LLoginResponse> response) {
                progressDialog.hide();
                if (response.body().getStatus().equals(Boolean.TRUE)){
                    Toast.makeText(getBaseContext(), "Welcome to WdyW", Toast.LENGTH_LONG).show();
                    Preferences.setPrefs(Consts.TOKEN_SP_KEY,response.body().getToken(),LLoginActivity.this);
                    Log.d(TAG, Preferences.getPrefs(Consts.TOKEN_SP_KEY,LLoginActivity.this));

                    Intent intent = new Intent(LLoginActivity.this, LenderMainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getBaseContext(), "Welcome To WdyW", Toast.LENGTH_SHORT).show();

                }
                else

                {Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                    onLoginFailed();}

            }


            @Override
            public void onFailure(Call<LLoginResponse> call, Throwable t) {
                progressDialog.hide();
            }
        });

        // TODO: Implement your own authentication logic here.

    }}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        // Disable going back to the BorrowerMainActivity
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String password = _passwordText.getText().toString();


        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}
