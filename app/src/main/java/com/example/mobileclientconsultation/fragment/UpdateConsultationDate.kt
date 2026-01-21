package com.example.mobileclientconsultation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.ViewModel.ViewModelHome
import com.example.mobileclientconsultation.databinding.UpdateconsultationdateBinding

import com.example.mobileclientconsultation.entity.kConsultation

import com.example.mobileclientconsultation.mapper.toJava

import hepl.faad.Bibliotheque.Requete_Update_Consultation
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.getValue

class UpdateConsultationDate : Fragment(R.layout.updateconsultationdate) {
    private val viewModel: ViewModelHome by activityViewModels()
    private var idDoc: Int = -1

    private lateinit var binding: UpdateconsultationdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idDoc = requireActivity()
            .intent
            .getIntExtra("docId", -1)
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UpdateconsultationdateBinding.bind(view)
        var currentConsult : kConsultation
        viewModel.currentConsultPublic.observe(viewLifecycleOwner) { newList ->
            var currentConsult = newList
            binding.editTextDate.setText(currentConsult.date.toString())
            binding.timeedit.setText(currentConsult.hour.toString())
        }

        binding.buttonUpdate.setOnClickListener {
                view ->
            viewModel.currentConsultPublic.observe(viewLifecycleOwner) { newList ->
                currentConsult = newList

                try {
                    val date = binding.editTextDate.text.toString()
                    val time = binding.timeedit.text.toString()

                    if (date.isEmpty() || time.isEmpty()) {
                        Toast.makeText(context, getString(R.string.mesMissing), Toast.LENGTH_SHORT).show()
                    } else {
                        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                        val dateReq = LocalDate.parse(date, dateFormatter)

                        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                        val timeReq = LocalTime.parse(time, timeFormatter)

                        val req = Requete_Update_Consultation(
                            idDoc,
                            currentConsult.id,
                            dateReq,
                            timeReq,
                            currentConsult.patient?.toJava(),
                            currentConsult.reason
                        )
                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.updateConsultation(req)

                        }
                        findNavController().navigate(R.id.action_updateConsultationDate_to_mainFragment)
                    }
                }
                catch (e: Exception){
                    Toast.makeText(context, getString(R.string.dateFormat), Toast.LENGTH_SHORT).show()
                }

            }
        }

        binding.buttonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_updateConsultationDate_to_mainFragment)
        }

    }

}