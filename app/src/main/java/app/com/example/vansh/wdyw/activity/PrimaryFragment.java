package app.com.example.vansh.wdyw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.com.example.vansh.wdyw.R;

/**
 * Created by Vansh on 7/29/2015.
 */
public class PrimaryFragment extends Fragment {

    Button bt;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sent_layout,null);
    }
}
