package com.deepak.viewmodeldemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    AppCompatTextView mTextView;
    MainActivityViewModel model;
    AppCompatButton mFetchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tvNumber);
        mFetchData = findViewById(R.id.fetchBtn);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        model = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        LiveData<String> myRandomNumber = model.getNumber();
        myRandomNumber.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextView.setText(s);
            }
        });

        mFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.createNumber();
            }
        });
        Log.i(TAG, "Random Number Set");
    }
}