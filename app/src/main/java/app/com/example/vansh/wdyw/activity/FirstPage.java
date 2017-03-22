package app.com.example.vansh.wdyw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import app.com.example.vansh.wdyw.R;
import app.com.example.vansh.wdyw.utility.Consts;
import app.com.example.vansh.wdyw.utility.Preferences;
import butterknife.Bind;
import butterknife.ButterKnife;


public class FirstPage extends AppCompatActivity{

    @Bind(R.id.bor)
    Button bor;
    @Bind(R.id.len)
    Button len;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        if (Preferences.getPrefs(Consts.AUTO_LOGIN,this).equals("borrower")){
            Intent it = new Intent(FirstPage.this, BorrowerMainActivity.class);
            startActivity(it);
            Toast.makeText(getBaseContext(), "Welcome Back!", Toast.LENGTH_SHORT).show();

        }
        if (Preferences.getPrefs(Consts.AUTO_LOGIN,this).equals("lender")){
            Intent it = new Intent(FirstPage.this, LenderMainActivity.class);
            startActivity(it);
            Toast.makeText(getBaseContext(), "Welcome Back!", Toast.LENGTH_SHORT).show();
        }

        bor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FirstPage.this, BorrowerLoginActivity.class);
                startActivity(it);
            }
        });

        len.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FirstPage.this, LLoginActivity.class);
                startActivity(it);
            }

        });



    }


    @Override
    public void onBackPressed() {
      finish();    // Disable going back to the BorrowerMainActivity
    }
}


