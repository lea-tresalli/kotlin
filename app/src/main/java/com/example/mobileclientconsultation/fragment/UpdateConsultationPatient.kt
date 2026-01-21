package com.example.mobileclientconsultation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.ViewModel.ViewModelHome

import com.example.mobileclientconsultation.databinding.UpdateconsultationpatientBinding
import com.example.mobileclientconsultation.entity.kConsultation
import com.example.mobileclientconsultation.entity.kPatient
import com.example.mobileclientconsultation.mapper.toJava
import hepl.faad.Bibliotheque.Requete_All_Patient
import hepl.faad.Bibliotheque.Requete_Update_Consultation
import hepl.faad.model.entity.Patient
import kotlinx.coroutines.launch
import java.util.ArrayList
import kotlin.getValue

class UpdateConsultationPatient : Fragment(R.layout.updateconsultationpatient){
    private val viewModel: ViewModelHome by activityViewModels()
    private var idDoc: Int = -1
    var patientSelected : kPatient? = null
    private lateinit var binding: UpdateconsultationpatientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idDoc = requireActivity()
            .intent
            .getIntExtra("docId", -1)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UpdateconsultationpatientBinding.bind(view)

        var tmpPatient : List<kPatient> = ArrayList<kPatient>()
        var currentConsult : kConsultation
        var adapter : ArrayAdapter<String>


        viewModel.patientPublic.observe(viewLifecycleOwner){ newList ->
            tmpPatient = newList
            val patientNull : kPatient = kPatient(-1, "tous les patients", "", null)
            tmpPatient = listOf(patientNull) + tmpPatient
            val listString = tmpPatient.map{patients -> "${patients.lastName} ${patients.firstName} (${patients.id})"}
            adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listString)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.listPatient.adapter = adapter
        }

        binding.listPatient.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                patientSelected = tmpPatient[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                patientSelected = null
            }
        }




        binding.buttonUpdate.setOnClickListener {
            view ->
            viewModel.currentConsultPublic.observe(viewLifecycleOwner) { newList ->
                currentConsult = newList

                val reason = binding.reason.text.toString()
                val req =
                    Requete_Update_Consultation(idDoc, currentConsult.id, currentConsult.date, currentConsult.hour, patientSelected?.toJava(), reason)
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.updateConsultation(req)
                }
            }
            findNavController().navigate(R.id.action_updateConsultationPatient_to_mainFragment)
        }

        binding.buttonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_updateConsultationPatient_to_mainFragment)
        }




    }





}