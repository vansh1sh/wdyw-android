//package app.com.example.vansh.wdyw.activity;
//
///**
// * Created by Akanshi on 3/19/2017.
// */
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class ToBeDeleted extends Activity {
//
//    private EditText etNormalText;
//    private EditText etEmailAddrss;
//    private EditText etPhoneNumber;
//    private Button btnSubmit;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//
//        registerViews();
//    }
//
//    private void registerViews() {
//        etNormalText = (EditText) findViewById(R.id.et_normal_text);
//        // TextWatcher would let us check validation error on the fly
//        etNormalText.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//                Validation.hasText(etNormalText);
//            }
//            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
//            public void onTextChanged(CharSequence s, int start, int before, int count){}
//        });
//
//        etEmailAddrss = (EditText) findViewById(R.id.et_email_address);
//        etEmailAddrss.addTextChangedListener(new TextWatcher() {
//            // after every change has been made to this editText, we would like to check validity
//            public void afterTextChanged(Editable s) {
//                Validation.isEmailAddress(etEmailAddrss, true);
//            }
//            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
//            public void onTextChanged(CharSequence s, int start, int before, int count){}
//        });
//
//        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
//        etPhoneNumber.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//                Validation.isPhoneNumber(etPhoneNumber, false);
//            }
//            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
//            public void onTextChanged(CharSequence s, int start, int before, int count){}
//        });
//
//        btnSubmit = (Button) findViewById(R.id.btn_submit);
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /*
//                Validation class will check the error and display the error on respective fields
//                but it won't resist the form submission, so we need to check again before submit
//                 */
//                if ( checkValidation () )
//                    submitForm();
//                else
//                    Toast.makeText(MyActivity.this, "Form contains error", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void submitForm() {
//        // Submit your form here. your form is valid
//        Toast.makeText(this, "Submitting form...", Toast.LENGTH_LONG).show();
//    }
//
//    private boolean checkValidation() {
//        boolean ret = true;
//
//        if (!Validation.hasText(etNormalText)) ret = false;
//        if (!Validation.isEmailAddress(etEmailAddrss, true)) ret = false;
//        if (!Validation.isPhoneNumber(etPhoneNumber, false)) ret = false;
//
//        return ret;
//    }
//}