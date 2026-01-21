package com.example.mobileclientconsultation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.ViewModel.ViewModelHome
import com.example.mobileclientconsultation.adapters.ListeConsultationAdapter
import com.example.mobileclientconsultation.databinding.ListeconsultationBinding
import hepl.faad.Bibliotheque.Requete_Search_Consultations
import hepl.faad.model.entity.Patient

import kotlinx.coroutines.launch


class listeConsultation : Fragment() {
    private val viewModel: ViewModelHome by activityViewModels ()
    private lateinit var listeView: ListView
    private lateinit var adapter: ListeConsultationAdapter

    private var idDoc: Int = -1

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.listeconsultation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){


        super.onViewCreated(view, savedInstanceState)
        val binding = ListeconsultationBinding.bind(view)
        adapter = ListeConsultationAdapter(requireContext(), mutableListOf())
        binding.recyclerVieConsult.adapter = adapter
        viewModel.doctorIdPublic.observe(viewLifecycleOwner) { id ->
            idDoc = id



            val patient = ArrayList<Patient>()
            val req = Requete_Search_Consultations(patient, null, null, idDoc)
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getConsultations(req)


            }
        }
        viewModel.consultationPublic.observe(viewLifecycleOwner){
            newList -> adapter.update(newList)
        }
    }



}