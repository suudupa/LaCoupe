package com.suudupa.lacoupe.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.suudupa.lacoupe.R;
import com.suudupa.lacoupe.utility.SharedPreferences;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (!isUserFirstTime()) {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        } else {
            CreatePlayerProfileFragment createPlayerProfileFragment = new CreatePlayerProfileFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("imageView", R.drawable.ic_soccer_player_dribbling);
            bundle.putInt("textView", R.string.setup_player_profile_message);
            bundle.putBoolean("isUserFirstTime", true);
            createPlayerProfileFragment.setArguments(bundle);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.splashScreenContainer, createPlayerProfileFragment)
                    .commit();
        }
    }

    private boolean isUserFirstTime() {
        return SharedPreferences.INSTANCE.readBoolean(getApplicationContext(), SharedPreferences.isUserFirstTime, true);
    }
}