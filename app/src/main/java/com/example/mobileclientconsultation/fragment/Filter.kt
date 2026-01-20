package com.example.mobileclientconsultation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.adapters.ListeConsultationAdapter
import com.example.mobileclientconsultation.databinding.FiltreBinding
import com.example.mobileclientconsultation.entity.kPatient
import com.example.mobileclientconsultation.mapper.toJava
import com.example.mobileclientconsultation.mapper.toKotlin
import com.example.mobileclientconsultation.network.networkConnection.contacteServeur
import com.google.android.material.datepicker.MaterialDatePicker
import hepl.faad.Bibliotheque.Reponse_All_Patient
import hepl.faad.Bibliotheque.Reponse_Search_Consultations
import hepl.faad.Bibliotheque.Requete_All_Patient
import hepl.faad.Bibliotheque.Requete_Search_Consultations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.ArrayList

class Filter : Fragment() {

    lateinit var startDate : LocalDate
    lateinit var endDate : LocalDate
    var patientSelected : kPatient? = null
    private var idDoc: Int = -1
    private lateinit var binding: FiltreBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        arguments?.let{
            idDoc = it.getInt("docId", -1)
        }

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

        var tmpPatient : List<kPatient> = ArrayList<kPatient>()
        val req = Requete_All_Patient()
        var adapter : ArrayAdapter<String>
        viewLifecycleOwner.lifecycleScope.launch {
            val listPatient = withContext(Dispatchers.IO){contacteServeur(req)}

            (listPatient as? Reponse_All_Patient)
                ?.listePatient?.let { patients ->
                    tmpPatient = patients.map { it.toKotlin()
                    }
                    val listString = tmpPatient.map{patients -> "${patients.lastName} ${patients.firstName} (${patients.id})"}

                    adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listString)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.listPatient.adapter = adapter

                } ?: run {
                Toast.makeText(
                    requireContext(),
                    "pas de consultation trouvée",
                    Toast.LENGTH_SHORT
                ).show()
            }


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
                .setTitleText("période de consultation").build()
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
            val req = Requete_Search_Consultations(patientSelected?.toJava(), startDate, endDate, idDoc)
            req
        }




    }





}