package com.suudupa.lacoupe.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.suudupa.lacoupe.R
import com.suudupa.lacoupe.adapter.AddNewGamePlayerListAdapter
import com.suudupa.lacoupe.databinding.FragmentAddNewGameMatchPlayersBinding
import com.suudupa.lacoupe.model.MatchModel
import com.suudupa.lacoupe.model.UserListModel
import com.suudupa.lacoupe.model.PlayerModel
import com.suudupa.lacoupe.model.UserModel
import com.suudupa.lacoupe.utility.Utils
import com.suudupa.lacoupe.viewModel.StandingsViewModel

class AddNewGameMatchPlayersFragment : Fragment() {

    private lateinit var binding: FragmentAddNewGameMatchPlayersBinding
    private lateinit var playerListAdapter: AddNewGamePlayerListAdapter
    private lateinit var match: MatchModel

    private val standingsViewModel: StandingsViewModel by lazy {
        ViewModelProvider(this).get(StandingsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Utils.changeStatusBarColor(requireActivity(), R.color.colorPrimary)
        binding = FragmentAddNewGameMatchPlayersBinding.inflate(inflater, container, false)
        try {
            val args = requireArguments()
            match = args.getParcelable("match")!!
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
        binding.titleTv.text = requireContext().getString(R.string.add_participants)
        binding.doneBtn.setImageResource(R.drawable.ic_next)
        playerListAdapter = AddNewGamePlayerListAdapter(standingsViewModel.players.value)
        binding.playerListRv.adapter = playerListAdapter
        val animationController: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_fall_down)
        binding.playerListRv.layoutAnimation = animationController
    }

    private fun initEvents() {
        binding.doneBtn.setOnClickListener {
            if (playerListAdapter.participants.size > 1) {
                val bundle = Bundle()
                bundle.putParcelable("match", match)
                bundle.putParcelable("players", UserListModel(playerListAdapter.participants))
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_add_new_game_match_players_score, bundle)
            }
        }
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