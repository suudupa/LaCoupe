package com.suudupa.lacoupe.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.suudupa.lacoupe.databinding.FragmentScoresBinding;
import com.suudupa.lacoupe.viewModel.ScoresViewModel;

public class ScoresFragment extends Fragment {

    private FragmentScoresBinding binding;
    private ScoresViewModel scoresViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScoresBinding.inflate(inflater, container, false);
        scoresViewModel = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(ScoresViewModel.class);
        scoresViewModel.getText().observe(getViewLifecycleOwner(), s -> binding.greetingTv.setText(Html.fromHtml(s)));
        return binding.getRoot();
    }
}
