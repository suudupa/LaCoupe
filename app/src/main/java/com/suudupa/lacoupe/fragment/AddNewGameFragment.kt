package com.suudupa.lacoupe.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.suudupa.lacoupe.R
import com.suudupa.lacoupe.databinding.FragmentAddNewGameBinding
import com.suudupa.lacoupe.utility.Utils

class AddNewGameFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentAddNewGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Utils.changeStatusBarColor(requireActivity(), R.color.colorPrimary)
        binding = FragmentAddNewGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvents()
    }

    private fun initEvents() {
        binding.doneBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).popBackStack()
    }
}