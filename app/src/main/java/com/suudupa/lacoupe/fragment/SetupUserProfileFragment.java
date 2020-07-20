package com.suudupa.lacoupe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.suudupa.lacoupe.activity.MainActivity;
import com.suudupa.lacoupe.databinding.FragmentSetupUserProfileBinding;
import com.suudupa.lacoupe.model.UserModel;
import com.suudupa.lacoupe.repository.RealmRepo;
import com.suudupa.lacoupe.utility.SharedPreferences;

public class SetupUserProfileFragment extends Fragment implements View.OnClickListener {

    private FragmentSetupUserProfileBinding binding;
    private RealmRepo realmRepo = new RealmRepo();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSetupUserProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEvents();
    }

    private void initEvents() {
        binding.readyBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String playerName = binding.playerNameEt.getText().toString().trim();
        String jerseyNumber = binding.playerNumberEt.getText().toString().trim();
        if (isUserProfileValid(playerName, jerseyNumber)) {
            realmRepo.insertOrUpdate(new UserModel(playerName, Integer.parseInt(jerseyNumber), 0));
            SharedPreferences.INSTANCE.saveBoolean(requireContext(), SharedPreferences.isUserFirstTime, false);
            startActivity(new Intent(getContext(), MainActivity.class));
            requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            requireActivity().finish();
        }
    }

    private boolean isUserProfileValid(String name, String number) {
        if (name.isEmpty() || number.isEmpty()) {
            showToast("Fields cannot be empty.");
            return false;
        } else if (name.length() > 50) {
            showToast("Player name cannot exceed 50 characters.");
            return false;
        } else if(Integer.parseInt(number) < 1 || Integer.parseInt(number) > 99) {
            showToast("Jersey number must be between 1 and 99.");
            return false;
        } else return true;
    }

    private void showToast(String value) {
        Toast.makeText(getContext(), value, Toast.LENGTH_LONG).show();
    }
}
