package com.suudupa.lacoupe.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.suudupa.lacoupe.R
import com.suudupa.lacoupe.adapter.AddNewGamePlayerScoreListAdapter
import com.suudupa.lacoupe.databinding.FragmentAddNewGameMatchPlayersBinding
import com.suudupa.lacoupe.model.MatchModel
import com.suudupa.lacoupe.model.UserListModel
import com.suudupa.lacoupe.utility.Utils
import com.suudupa.lacoupe.viewModel.AddNewGameViewModel

class AddNewGameMatchPlayersScoreFragment : Fragment() {

    private lateinit var binding: FragmentAddNewGameMatchPlayersBinding
    private lateinit var playerListAdapter: AddNewGamePlayerScoreListAdapter
    private lateinit var match: MatchModel
    private lateinit var players: UserListModel

    private val addNewGameViewModel: AddNewGameViewModel by lazy {
        ViewModelProvider(this).get(AddNewGameViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Utils.changeStatusBarColor(requireActivity(), R.color.colorPrimary)
        binding = FragmentAddNewGameMatchPlayersBinding.inflate(inflater, container, false)
        try {
            val args = requireArguments()
            match = args.getParcelable("match")!!
            players = args.getParcelable("players")!!
        } catch (e: Exception) {
            Utils.showToast(requireContext(), "Sorry, something went wrong.")
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initEvents()
    }

    private fun initViews() {
        binding.titleTv.text = requireContext().getString(R.string.enter_score)
        binding.doneBtn.setImageResource(R.drawable.ic_done)
        playerListAdapter = AddNewGamePlayerScoreListAdapter(players)
        binding.playerListRv.adapter = playerListAdapter
        val animationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_fall_down)
        binding.playerListRv.layoutAnimation = animationController
    }

    private fun initEvents() {
        binding.doneBtn.setOnClickListener {
            if (addNewGameViewModel.addNewGame(match.apply { players = playerListAdapter.players })) {
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_scores)
            } else {
                //error adding game...
            }
        }
    }
}