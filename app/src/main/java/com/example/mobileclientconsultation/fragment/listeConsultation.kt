package com.example.mobileclientconsultation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.adapters.ListeConsultationAdapter
import com.example.mobileclientconsultation.mapper.toKotlin
import com.example.mobileclientconsultation.network.networkConnection.contacteServeur
import hepl.faad.Bibliotheque.Reponse_Search_Consultations
import hepl.faad.Bibliotheque.Requete_Search_Consultations
import hepl.faad.model.entity.Patient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class listeConsultation : Fragment() {

    private lateinit var listeView: ListView

    private var idDoc: Int = -1

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
        return inflater.inflate(R.layout.listeconsultation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        listeView = view.findViewById(R.id.recyclerVieConsult)
        val patient = ArrayList<Patient>()
        val req = Requete_Search_Consultations(patient, null, null, idDoc)
        viewLifecycleOwner.lifecycleScope.launch {


            val listeConsult = withContext(Dispatchers.IO){contacteServeur(req)}

                (listeConsult as? Reponse_Search_Consultations)
                    ?.consultation?.let { consultations ->
                        val consultListe = consultations.map { it.toKotlin() }
                        val adapter = ListeConsultationAdapter(requireContext(), consultListe)
                        listeView.adapter = adapter

                    } ?: run {
                    Toast.makeText(
                        requireContext(),
                        "pas de consultation trouvée",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

        }
    }



}