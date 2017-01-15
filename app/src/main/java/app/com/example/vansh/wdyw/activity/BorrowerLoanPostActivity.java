package app.com.example.vansh.wdyw.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.model.LoanPostRequest;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowerLoanPostActivity extends AppCompatActivity {


    String selectedItemText;
    @Bind(R.id.comsave)
    Button sub;
    @Bind(R.id.city)
    EditText city;

    @Bind(R.id.interest)
    EditText interest;
    @Bind(R.id.loanamount)
    EditText lamount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_post);

        ButterKnife.bind(this);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Initializing a String Array
        String[] plants = new String[]{
                "Select Loan Type...",
                "Personal",
                "vehicle",
                "Education",
                "Mortage",
                "Buissness",
                "House"
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
                selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (selectedItemText.equals("Select Loan Type...")){
                    selectedItemText="";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final LoanPostRequest loanPostRequest = new LoanPostRequest();
        final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lamount.getText().toString().isEmpty() || interest.getText().toString().isEmpty() || city.getText().toString().isEmpty() || selectedItemText.isEmpty()) {
                    DialogUtil.createDialog("Please Fill All the information!", BorrowerLoanPostActivity.this, new DialogUtil.OnPositiveButtonClick() {
                        @Override
                        public void onClick() {
                            finish();
                        }
                    });


                }
                else {
                    Integer myNum = Integer.parseInt(lamount.getText().toString());
                    loanPostRequest.setLoanAmt(myNum);


                    loanPostRequest.setCity(city.getText().toString());
                    Integer myNum1 = Integer.parseInt(interest.getText().toString());
                    loanPostRequest.setExpectedinterest(myNum1);

                    loanPostRequest.setLoanType(selectedItemText);


                    Call<LoanPostRequest> call = apiInterface.LoanPost(loanPostRequest);

                    final ProgressDialog dialog = new ProgressDialog(BorrowerLoanPostActivity.this);
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("Posting Loan...");
                    dialog.setIndeterminate(true);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                    call.enqueue(new Callback<LoanPostRequest>() {
                        @Override
                        public void onResponse(Call<LoanPostRequest> call, Response<LoanPostRequest> response) {

                            dialog.hide();
                            DialogUtil.createDialog("Loan Request Posted Successfully", BorrowerLoanPostActivity.this, new DialogUtil.OnPositiveButtonClick() {
                                @Override
                                public void onClick() {
                                    finish();
                                    Intent it = new Intent(BorrowerLoanPostActivity.this, BorrowerMainActivity.class);
                                    startActivity(it);
                                }
                            });


                        }

                        @Override
                        public void onFailure(Call<LoanPostRequest> call, Throwable t) {
                            dialog.hide();
                            DialogUtil.createDialog("Oops! Please check your internet connection!", BorrowerLoanPostActivity.this, new DialogUtil.OnPositiveButtonClick() {
                                @Override
                                public void onClick() {
                                }
                            });
                        }
                    });
                }
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


        }
        return true;

    }

    }
