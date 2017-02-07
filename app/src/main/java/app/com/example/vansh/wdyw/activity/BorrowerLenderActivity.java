package app.com.example.vansh.wdyw.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.adapter.BLenderDetailsAdapter;
import app.com.example.vansh.wdyw.adapter.LLentDetailsAdapter;
import app.com.example.vansh.wdyw.adapter.LenderDetailsAdapter;
import app.com.example.vansh.wdyw.model.LLentResponse;
import app.com.example.vansh.wdyw.model.LenderData;
import app.com.example.vansh.wdyw.model.LenderDetails;
import app.com.example.vansh.wdyw.model.LlentDatum;
import app.com.example.vansh.wdyw.rest.ApiClient;
import app.com.example.vansh.wdyw.rest.ApiInterface;
import app.com.example.vansh.wdyw.utility.DialogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrowerLenderActivity extends AppCompatActivity {

    String city="vellore";
    String  pageid="1";
    String loanAmt="3000";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lender);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.stock_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final ApiInterface apiService =
                ApiClient.getClient(this).create(ApiInterface.class);


        final ProgressDialog dialog = new ProgressDialog(BorrowerLenderActivity.this, R.style.AppTheme_Dark_Dialog);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        Call<LLentResponse> call = apiService.LenderLend();

        call.enqueue(new Callback<LLentResponse>() {
            @Override
            public void onResponse(Call<LLentResponse> call, final Response<LLentResponse> response) {

                List<LlentDatum> sold = response.body().getData();
                recyclerView.setAdapter(new BLenderDetailsAdapter(sold, R.layout.list_item_loanlent, getApplicationContext()));
                dialog.hide();


            }


            @Override
            public void onFailure(Call<LLentResponse> call, Throwable t) {
                dialog.hide();
                DialogUtil.createDialog("Oops! Please check your internet connection!", BorrowerLenderActivity.this, new DialogUtil.OnPositiveButtonClick() {
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
                Intent i = new Intent(BorrowerLenderActivity.this,BProfileActivity.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "LOL I'm cool-huehuehue", Toast.LENGTH_SHORT).show();
                break;


        }
        return true;

    }

    }
