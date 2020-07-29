package com.suudupa.lacoupe.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.suudupa.lacoupe.databinding.FragmentScoresBinding
import com.suudupa.lacoupe.utility.Utils.changeStatusBarColor
import com.suudupa.lacoupe.viewModel.ScoresViewModel

class ScoresFragment : Fragment() {

    private lateinit var binding: FragmentScoresBinding
    private val scoresViewModel: ScoresViewModel by lazy {
        ViewModelProvider(this).get(ScoresViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        changeStatusBarColor(requireActivity(), android.R.color.transparent)
        binding = FragmentScoresBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        scoresViewModel.greeting.observe(viewLifecycleOwner, Observer {
            binding.greetingTv.text = Html.fromHtml(it)
        })
    }
}