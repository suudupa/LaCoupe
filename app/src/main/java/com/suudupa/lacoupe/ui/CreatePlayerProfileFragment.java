package com.suudupa.lacoupe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.suudupa.lacoupe.R;
import com.suudupa.lacoupe.databinding.FragmentCreatePlayerProfileBinding;
import com.suudupa.lacoupe.model.UserModel;
import com.suudupa.lacoupe.repository.RealmRepo;
import com.suudupa.lacoupe.utility.SharedPreferences;
import com.suudupa.lacoupe.utility.Utils;

public class CreatePlayerProfileFragment extends Fragment implements View.OnClickListener {

    private FragmentCreatePlayerProfileBinding binding;
    private int imageView;
    private int textView;
    private boolean isUserFirstTime;
    private RealmRepo realmRepo = new RealmRepo();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Utils.INSTANCE.changeStatusBarColor(requireActivity(), R.color.colorPrimary);
        binding = FragmentCreatePlayerProfileBinding.inflate(inflater, container, false);
        Bundle args = getArguments();
        assert args != null;
        imageView = args.getInt("imageView", R.drawable.ic_soccer_player_running);
        textView = args.getInt("textView", R.string.add_new_player_profile_message);
        isUserFirstTime = args.getBoolean("isUserFirstTime", false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initEvents();
    }

    private void initViews() {
        binding.imageView.setImageResource(imageView);
        binding.textView.setText(textView);
    }

    private void initEvents() {
        binding.doneBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String playerName = binding.playerNameEt.getText().toString().trim();
        String jerseyNumber = binding.playerNumberEt.getText().toString().trim();
        if (isUserProfileValid(playerName, jerseyNumber)) {
            realmRepo.insertOrUpdate(new UserModel(playerName, Integer.parseInt(jerseyNumber), 0, 0, isUserFirstTime));
            if (isUserFirstTime) {
                SharedPreferences.INSTANCE.saveBoolean(requireContext(), SharedPreferences.isUserFirstTime, false);
                startActivity(new Intent(getContext(), MainActivity.class));
                requireActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                requireActivity().finish();
            } else {
                showToast("Added " + playerName);
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).popBackStack();
            }
        }
    }

    private boolean isUserProfileValid(String name, String number) {
        if (name.isEmpty() || number.isEmpty()) {
            showErrorMessage(R.string.error_empty_fields);
            return false;
        } else if (name.length() > 50) {
            showErrorMessage(R.string.error_name_invalid);
            return false;
        } else if (Integer.parseInt(number) < 1 || Integer.parseInt(number) > 99) {
            showErrorMessage(R.string.error_number_invalid);
            return false;
        } else if (isNumberTaken(Integer.parseInt(number))) {
            showErrorMessage(R.string.error_number_taken);
            return false;
        } else return true;
    }

    private boolean isNumberTaken(int number) {
        return realmRepo.getRealm().where(UserModel.class).equalTo("jerseyNumber", number).findFirst() != null;
    }

    private void showErrorMessage(int resId) {
        binding.errorTv.setText(resId);
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        binding.errorTv.startAnimation(fadeIn);
        binding.errorTv.startAnimation(fadeOut);
        fadeIn.setDuration(1000);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(1500 + fadeIn.getStartOffset());
    }

    private void showToast(String value) {
        Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();
    }
}