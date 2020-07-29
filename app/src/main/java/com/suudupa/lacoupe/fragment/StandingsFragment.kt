package com.suudupa.lacoupe.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.suudupa.lacoupe.R
import com.suudupa.lacoupe.adapter.StandingsAdapter
import com.suudupa.lacoupe.databinding.FragmentStandingsBinding
import com.suudupa.lacoupe.utility.Utils
import com.suudupa.lacoupe.viewModel.StandingsViewModel

class StandingsFragment : Fragment() {

    private lateinit var binding: FragmentStandingsBinding
    private lateinit var standingsAdapter: StandingsAdapter

    private val standingsViewModel: StandingsViewModel by lazy {
        ViewModelProvider(this).get(StandingsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Utils.changeStatusBarColor(requireActivity(), android.R.color.transparent)
        binding = FragmentStandingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        standingsAdapter = StandingsAdapter(standingsViewModel.players.value)
        binding.standingsRv.adapter = standingsAdapter
        val animationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_fall_down)
        binding.standingsRv.layoutAnimation = animationController
    }

    override fun onStart() {
        super.onStart()
        standingsViewModel.players.value?.addChangeListener(standingsViewModel.playersChangeListener)
    }

    override fun onStop() {
        super.onStop()
        standingsViewModel.players.value?.removeChangeListener(standingsViewModel.playersChangeListener)
    }
}