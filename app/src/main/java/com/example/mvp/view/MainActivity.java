package com.example.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvp.Presenter.LogInPresenter;
import com.example.mvp.Presenter.LogInPresenterImpl;
import com.example.mvp.R;

public class MainActivity extends AppCompatActivity implements LogInView {

    private EditText email,password;
    private Button logbtn;
    private ProgressBar progressBar;
    private LogInPresenter logInPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        logbtn = findViewById(R.id.logInbtn);
        progressBar = findViewById(R.id.progressLogin);
        logInPresenter = new LogInPresenterImpl(this);

        progressBar.setProgress(View.INVISIBLE);


        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInPresenter.progressbarVisibility(View.VISIBLE);
                logInPresenter.doLogin(email.getText().toString().trim(),password.getText().toString().trim());
            }
        });




    }

    @Override
    public void onLogInSuccess(String msg) {
        logInPresenter.progressbarVisibility(View.INVISIBLE);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLogInError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSetProgressbarVisibility(int visibility) {

    }


}