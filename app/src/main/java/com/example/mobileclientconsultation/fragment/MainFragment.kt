package com.example.mobileclientconsultation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.ViewModel.ViewModelHome

class MainFragment : Fragment(R.layout.home){
    private val viewModel: ViewModelHome by activityViewModels()


    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)

        val idDoctor = requireActivity()
            .intent
            .getIntExtra("docId", -1)

        viewModel.setDoctorId(idDoctor)
    }
}