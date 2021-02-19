package com.example.registerform.ui.java;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.registerform.R;
import com.example.registerform.domain.User;

import static com.example.registerform.ui.java.MainActivity.USER_EXTRA;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        User user = getIntent().getParcelableExtra(USER_EXTRA);
        bindUserToView(user);
    }

    public void bindUserToView(User user) {
        TextView textView = findViewById(R.id.tvName);
        textView.setText(user.getName());
    }

}