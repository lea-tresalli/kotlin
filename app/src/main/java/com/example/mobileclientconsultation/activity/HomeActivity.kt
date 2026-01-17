package com.example.mobileclientconsultation.activity

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.adapters.ListeConsultationAdapter
import com.example.mobileclientconsultation.entity.kPatient
import com.example.mobileclientconsultation.mapper.toJava
import com.example.mobileclientconsultation.mapper.toKotlin
import com.example.mobileclientconsultation.network.networkConnection.contacteServeur
import hepl.faad.Bibliotheque.Reponse_Search_Consultations
import hepl.faad.Bibliotheque.Requete_Search_Consultations
import hepl.faad.model.entity.Patient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState : Bundle? ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home)
        val idDoc = intent.getIntExtra("docId", -1)
        val patient = ArrayList<Patient>()
        val req = Requete_Search_Consultations(patient, null, null, idDoc)
        CoroutineScope(Dispatchers.IO).launch {


            val listeConsult = contacteServeur(req)
            withContext(Dispatchers.Main) {
                (listeConsult as? Reponse_Search_Consultations)
                    ?.consultation?.let { consultations ->
                        val consultListe = consultations.map{it.toKotlin()}
                        val adapter = ListeConsultationAdapter(this@HomeActivity, consultListe)
                        val listeView = findViewById<ListView>(R.id.listeView)
                        listeView.adapter = adapter

                    } ?: run {
                Toast.makeText(this@HomeActivity, "pas de consultation trouvée", Toast.LENGTH_SHORT)
                    .show()
            }
        }


            }


    }


}