package com.suudupa.lacoupe.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.suudupa.lacoupe.R
import com.suudupa.lacoupe.viewModel.StandingsViewModel

class StandingsFragment : Fragment() {

    private val standingsViewModel: StandingsViewModel by lazy {
        ViewModelProvider(this).get(StandingsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_standings, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        standingsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}