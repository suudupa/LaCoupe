package com.suudupa.lacoupe.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.suudupa.lacoupe.R;
import com.suudupa.lacoupe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener, View.OnClickListener {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.navView, navController);
        navController.addOnDestinationChangedListener(this);
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        switch (destination.getId()) {
            case R.id.navigation_create_player_profile:
            case R.id.navigation_add_new_game_match_details:
            case R.id.navigation_add_new_game_match_players:
            case R.id.navigation_add_new_game_match_players_score:
                binding.newItemBtn.setVisibility(View.GONE);
                binding.navView.setVisibility(View.GONE);
                break;
            default:
                binding.newItemBtn.setVisibility(View.VISIBLE);
                binding.newItemBtn.setOnClickListener(this);
                binding.navView.setVisibility(View.VISIBLE);
                break;
        }
        if (destination.getId() == R.id.navigation_standings) {
            binding.newItemBtn.setImageResource(R.drawable.ic_new_player);
            binding.newItemBtn.setTag(R.drawable.ic_new_player);
        } else {
            binding.newItemBtn.setImageResource(R.drawable.ic_new_game);
            binding.newItemBtn.setTag(R.drawable.ic_new_game);
        }
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        if (tag == R.drawable.ic_new_player) {
            Bundle bundle = new Bundle();
            bundle.putInt("imageView", R.drawable.ic_soccer_player_running);
            bundle.putInt("textView", R.string.add_new_player_profile_message);
            bundle.putBoolean("isUserFirstTime", false);
            navController.navigate(R.id.navigation_create_player_profile, bundle);
        } else {
            navController.navigate(R.id.navigation_add_new_game_match_details);
        }
    }
}