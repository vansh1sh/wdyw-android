package app.com.example.vansh.wdyw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import app.com.example.vansh.wdyw.R;
import butterknife.Bind;
import butterknife.ButterKnife;


public class BorrowerFirstPage extends AppCompatActivity{

    @Bind(R.id.bor)
    Button bor;
    @Bind(R.id.len)
    Button len;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        bor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BorrowerFirstPage.this, BorrowerLoginActivity.class);
                startActivity(it);
            }
        });

        len.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BorrowerFirstPage.this, LLoginActivity.class);
                startActivity(it);
            }

        });



    }


    @Override
    public void onBackPressed() {
      finish();    // Disable going back to the BorrowerMainActivity
    }
}


