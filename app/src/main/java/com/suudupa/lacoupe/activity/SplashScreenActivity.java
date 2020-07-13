package com.suudupa.lacoupe.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.suudupa.lacoupe.R;
import com.suudupa.lacoupe.fragment.SetupUserProfileFragment;
import com.suudupa.lacoupe.utility.SharedPreferences;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(!isUserFirstTime()) {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.splashScreenContainer, new SetupUserProfileFragment())
                    .commit();
        }
    }

    private boolean isUserFirstTime() {
        return SharedPreferences.INSTANCE.readBoolean(getApplicationContext(), SharedPreferences.isUserFirstTime, true);
    }
}