package com.example.mobileclientconsultation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.ViewModel.ViewModelHome

import com.example.mobileclientconsultation.databinding.FiltreBinding
import com.example.mobileclientconsultation.entity.kPatient
import com.example.mobileclientconsultation.mapper.toJava

import com.google.android.material.datepicker.MaterialDatePicker

import hepl.faad.Bibliotheque.Requete_All_Patient
import hepl.faad.Bibliotheque.Requete_Search_Consultations
import hepl.faad.model.entity.Patient

import kotlinx.coroutines.launch

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.ArrayList
import kotlin.getValue

class Filter : Fragment() {
    private val viewModel: ViewModelHome by activityViewModels ()

     var startDate : LocalDate? = null
    var endDate : LocalDate? = null
    var patientSelected : kPatient? = null
    private var idDoc: Int = -1
    private lateinit var binding: FiltreBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.filtre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        binding = FiltreBinding.bind(view)

        viewModel.doctorIdPublic.observe(viewLifecycleOwner){id ->
            idDoc = id
        }
        var tmpPatient : List<kPatient> = ArrayList<kPatient>()
        val req = Requete_All_Patient()
        var adapter : ArrayAdapter<String>

        viewModel.patientPublic.observe(viewLifecycleOwner){ newList ->
            tmpPatient = newList
            val patientNull : kPatient = kPatient(-1, getString(R.string.allPatient), "", null)
            tmpPatient = listOf(patientNull) + tmpPatient
            val listString = tmpPatient.map{patients -> "${patients.lastName} ${patients.firstName} (${patients.id})"}
            adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listString)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.listPatient.adapter = adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPatient(req)
        }
        binding.listPatient.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                patientSelected = tmpPatient[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                patientSelected = null
            }
        }

        binding.datePickerActions.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText(getString(R.string.dateFormat)).build()
            datePicker.show(parentFragmentManager, "RangePicker")
            datePicker.addOnPositiveButtonClickListener {
                selectionPair ->
                val instantStart = Instant.ofEpochMilli(selectionPair.first)
                startDate = instantStart.atZone(ZoneId.of("UTC")).toLocalDate()
                val instantEnd = Instant.ofEpochMilli(selectionPair.second)
                endDate = instantEnd.atZone(ZoneId.of("UTC")).toLocalDate()
                //format !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                binding.datePickerActions.setText("$startDate - $endDate")

            }
        }

        binding.btnRechercher.setOnClickListener {
            val listePatient : ArrayList<Patient> = ArrayList<Patient>()

            if(patientSelected != null && patientSelected!!.id != -1){
                listePatient.add(patientSelected!!.toJava())
            }


            val req = Requete_Search_Consultations(listePatient, startDate, endDate, idDoc)
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getConsultations(req)
            }
            binding.datePickerActions.setText("")
            startDate = null
            endDate = null
        }




    }





}