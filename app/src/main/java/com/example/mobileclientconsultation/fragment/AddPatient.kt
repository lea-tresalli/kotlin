package com.example.mobileclientconsultation.fragment

import android.os.Bundle

import android.view.View

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.ViewModel.ViewModelHome
import com.example.mobileclientconsultation.databinding.AddpatientBinding
import hepl.faad.Bibliotheque.Requete_Add_Patient
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.getValue

class AddPatient : Fragment(R.layout.addpatient) {
    private val viewModel: ViewModelHome by activityViewModels()
    private lateinit var binding: AddpatientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddpatientBinding.bind(view)



        binding.buttonAdd.setOnClickListener {
            val nom = binding.nameText.text.toString()
            val prenom = binding.firstNameText.text.toString()

            if(nom.isNotEmpty() && prenom.isNotEmpty()){
                val req = Requete_Add_Patient(nom, prenom, LocalDate.now())
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.addPatient(req)

                }
                findNavController().navigate(R.id.action_addPatient_to_mainFragment)
            }
            else{
                Toast.makeText(context, getString(R.string.mesMissing), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.messageToastPublic.observe(viewLifecycleOwner){
                message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        binding.buttonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_addPatient_to_mainFragment)
        }




    }


}

