package com.suudupa.lacoupe.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.suudupa.lacoupe.R;
import com.suudupa.lacoupe.fragment.LoginFragment;
import com.suudupa.lacoupe.utility.SharedPreferences;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(isUserLoggedIn()) {
            //show home screen
            startActivity(new Intent(this, MainActivity.class));
        } else {
            //show login screen
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.splashScreenContainer, new LoginFragment())
                    .commit();
        }
    }

    private boolean isUserLoggedIn() {
        return SharedPreferences.INSTANCE.readBoolean(getApplicationContext(), SharedPreferences.isUserLoggedIn, false);
    }
}