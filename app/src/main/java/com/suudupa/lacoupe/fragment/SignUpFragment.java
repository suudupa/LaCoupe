package com.suudupa.lacoupe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.suudupa.lacoupe.R;
import com.suudupa.lacoupe.activity.MainActivity;
import com.suudupa.lacoupe.utility.SharedPreferences;

public class SignUpFragment extends Fragment {

    private EditText nameField;
    private EditText usernameField;
    private EditText passwordField;

    public SignUpFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        View signUpView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        nameField = signUpView.findViewById(R.id.newAccountNameField);
        usernameField = signUpView.findViewById(R.id.newAccountUsernameField);
        passwordField = signUpView.findViewById(R.id.newAccountPasswordField);
        Button signUpBtn = signUpView.findViewById(R.id.signUpBtn);
        TextView loginTv = signUpView.findViewById(R.id.loginTv);

        signUpBtn.setOnClickListener(view -> signUp());
        loginTv.setOnClickListener(view -> login());

        return signUpView;
    }

    private void signUp() {
        if (isSignUpSuccessful(nameField.getText().toString(), usernameField.getText().toString().trim(), passwordField.getText().toString().trim())) {
            startActivity(new Intent(getActivity(), MainActivity.class));
        } else {
            nameField.getText().clear();
            usernameField.getText().clear();
            passwordField.getText().clear();
        }
    }

    private void login() {
        LoginFragment loginFragment = new LoginFragment();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.signUpFragmentContainer, loginFragment, "loginFragment")
                .addToBackStack(null)
                .commit();
    }

    private boolean isSignUpSuccessful(String name, String username, String password) {
        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showToast(getString(R.string.signUpFailText));
            return false;
        } else {
            showToast(getString(R.string.signUpSuccessText));
            SharedPreferences.INSTANCE.saveBoolean(requireContext(), SharedPreferences.isUserLoggedIn, true);
            SharedPreferences.INSTANCE.save(requireContext(), SharedPreferences.user, name);
            return true;
        }
    }

    private void showToast(String value) {
        Toast.makeText(getContext(), value, Toast.LENGTH_LONG).show();
    }
}