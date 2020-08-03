package com.suudupa.lacoupe.fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.suudupa.lacoupe.R
import com.suudupa.lacoupe.databinding.FragmentAddNewGameBinding
import com.suudupa.lacoupe.utility.Utils
import java.text.SimpleDateFormat
import java.util.*


class AddNewGameFragment : Fragment() {

    private lateinit var binding: FragmentAddNewGameBinding
    private val calendar = Calendar.getInstance()

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
        binding.dateEt.setOnClickListener {
            DatePickerDialog(requireContext(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.doneBtn.setOnClickListener {
            Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).popBackStack()
        }
    }

    private val date = OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateLabel()
    }

    private fun updateLabel() {
        val format = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        binding.dateEt.setText(sdf.format(calendar.time))
    }
}