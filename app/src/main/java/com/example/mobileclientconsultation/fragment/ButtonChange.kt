package com.example.mobileclientconsultation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.ViewModel.ViewModelHome

import com.example.mobileclientconsultation.activity.LoginActivity
import hepl.faad.Bibliotheque.Requete_Logout
import kotlinx.coroutines.launch
import kotlin.getValue

class ButtonChange : Fragment(){
    private val viewModel: ViewModelHome by activityViewModels()
    private var idDoc : Int = -1

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        idDoc = requireActivity()
            .intent
            .getIntExtra("docId", -1)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.buttonhome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAddPatient = view.findViewById<Button>(R.id.buttonAddPatient)
        btnAddPatient.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addPatient)

        }

        val btnAddConsultation = view.findViewById<Button>(R.id.ButtonAddConsultation)
        btnAddConsultation.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addConsultation)
        }

        val btnDeco = view.findViewById<Button>(R.id.buttonLogout)
        btnDeco.setOnClickListener {
            val req : Requete_Logout = Requete_Logout(idDoc)
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.logout(req)
                val intentLogin = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intentLogin)
                requireActivity().finish()

            }

        }


    }

}