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

//SEE https://sourcey.com/articles/beautiful-android-login-and-signup-screens-with-material-design
//FOR AUTH PROCESS DETAILS

public class LoginFragment extends Fragment {

    private EditText usernameField;
    private EditText passwordField;

    public LoginFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        View loginView = inflater.inflate(R.layout.fragment_login, container, false);
        usernameField = loginView.findViewById(R.id.usernameField);
        passwordField = loginView.findViewById(R.id.passwordField);
        Button loginBtn = loginView.findViewById(R.id.loginBtn);
        TextView signUpTv = loginView.findViewById(R.id.signUpTv);

        loginBtn.setOnClickListener(view -> login());
        signUpTv.setOnClickListener(view -> signUp());

        return loginView;
    }

    private void login() {
        if (isSignInSuccessful(usernameField.getText().toString().trim(), passwordField.getText().toString().trim())) {
            startActivity(new Intent(getActivity(), MainActivity.class));
        } else {
            usernameField.getText().clear();
            passwordField.getText().clear();
        }
    }

    private void signUp() {
        SignUpFragment signUpFragment = new SignUpFragment();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.loginFragmentContainer, signUpFragment, "signUpFragment")
                .addToBackStack(null)
                .commit();
    }

    private boolean isSignInSuccessful(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            showToast(getString(R.string.loginFailText));
            return false;
        } else {
            showToast(getString(R.string.loginSuccessText));
            SharedPreferences.INSTANCE.saveBoolean(requireContext(), SharedPreferences.isUserLoggedIn, true);
            SharedPreferences.INSTANCE.save(requireContext(), SharedPreferences.user, username);
            return true;
        }
    }

    private void showToast(String value) {
        Toast.makeText(getContext(), value, Toast.LENGTH_LONG).show();
    }
}
