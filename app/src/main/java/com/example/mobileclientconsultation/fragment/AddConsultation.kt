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
import com.example.mobileclientconsultation.databinding.AddconsultationBinding

import hepl.faad.Bibliotheque.Requete_Add_Consultation
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.getValue

class AddConsultation : Fragment(R.layout.addconsultation) {
    private val viewModel: ViewModelHome by activityViewModels()
    private var idDoc: Int = -1
    private lateinit var binding: AddconsultationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idDoc = requireActivity()
            .intent
            .getIntExtra("docId", -1)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddconsultationBinding.bind(view)

        binding.buttonAdd.setOnClickListener {
            view ->
            try {
                val date = binding.date.text.toString()
                val time = binding.time.text.toString()
                val nbrCons = binding.nombreconsecutive.text.toString()
                val duration = binding.duree.text.toString()

                if(date.isEmpty() || time.isEmpty() || nbrCons.isEmpty()|| duration.isEmpty()){
                    Toast.makeText(context, getString(R.string.mesMissing), Toast.LENGTH_SHORT).show()
                }
                else {


                    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    val dateReq = LocalDate.parse(date, dateFormatter)

                    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                    val timeReq = LocalTime.parse(time, timeFormatter)

                    val req = Requete_Add_Consultation(
                        dateReq,
                        timeReq,
                        duration.toInt(),
                        nbrCons.toInt(),
                        idDoc
                    )

                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.addConsult(req)

                    }
                    findNavController().navigate(R.id.action_addConsultation_to_mainFragment)
                }

            }
            catch (e: Exception){
                Toast.makeText(context, getString(R.string.dateFormat), Toast.LENGTH_SHORT).show()
            }

        }

        binding.buttonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_addConsultation_to_mainFragment)
        }


    }
}