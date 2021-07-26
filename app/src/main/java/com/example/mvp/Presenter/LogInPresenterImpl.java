package com.example.mvp.Presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.mvp.Model.User;
import com.example.mvp.Model.UserImpl;
import com.example.mvp.view.LogInView;

public class LogInPresenterImpl implements LogInPresenter{

    private LogInView logInView;
    private Handler handler;

    public LogInPresenterImpl(LogInView logInView) {
        this.logInView = logInView;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void doLogin(String email, String password) {

        User user = new UserImpl(email,password);
        int Logincode = user.CheckUserValidity();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Logincode == 0){
                    logInView.onLogInError("Please Enter Your Email");
                }
                else if(Logincode == 1){
                    logInView.onLogInError("Please Enter Valid Email");
                }
                else if(Logincode == 2){
                    logInView.onLogInError("Please Enter Your Password");
                }
                else if(Logincode == 3){
                    logInView.onLogInError("Minimum length is 6");
                }
                else{
                    logInView.onLogInSuccess("LogIn Success");
                }
            }
        },2000);






    }

    @Override
    public void progressbarVisibility(int visibility) {

        logInView.onSetProgressbarVisibility(visibility);

    }
}
