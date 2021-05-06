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
import com.suudupa.lacoupe.databinding.FragmentAddNewGameMatchDetailsBinding
import com.suudupa.lacoupe.model.MatchModel
import com.suudupa.lacoupe.repository.RealmRepo
import com.suudupa.lacoupe.utility.Utils
import com.suudupa.lacoupe.utility.Utils.showErrorMessage
import java.text.SimpleDateFormat
import java.util.*

class AddNewGameMatchDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAddNewGameMatchDetailsBinding
    private val calendar = Calendar.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Utils.changeStatusBarColor(requireActivity(), R.color.colorPrimary)
        binding = FragmentAddNewGameMatchDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvents()
    }

    private fun initEvents() {
        binding.dateEt.setOnClickListener {
            Utils.hideSoftKeyboard(activity)
            DatePickerDialog(requireContext(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.nextBtn.setOnClickListener {
            val fieldName = binding.fieldEt.text.toString().trim()
            val matchDate = binding.dateEt.text.toString().trim()
            if (isFieldValid(fieldName)) {
                val match = MatchModel().apply {
                    matchId = RealmRepo().matchId()
                    field = fieldName
                    date = if (matchDate == "Select Date") Utils.todayDate() else matchDate
                }
                val bundle = Bundle()
                bundle.putParcelable("match", match)
                Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment)).navigate(R.id.navigation_add_new_game_match_players, bundle)
            }
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
        binding.dateEt.text = sdf.format(calendar.time)
    }

    private fun isFieldValid(field: String): Boolean {
        return if (field.isEmpty()) {
            showErrorMessage(binding.errorTv, R.string.error_empty_field_name)
            false
        } else if (field.length > 50) {
            showErrorMessage(binding.errorTv, R.string.error_field_invalid)
            false
        } else true
    }
}