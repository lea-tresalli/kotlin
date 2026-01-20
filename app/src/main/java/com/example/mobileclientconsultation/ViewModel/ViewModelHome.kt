package com.example.mobileclientconsultation.ViewModel

import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileclientconsultation.R
import com.example.mobileclientconsultation.adapters.ListeConsultationAdapter
import com.example.mobileclientconsultation.entity.kConsultation
import com.example.mobileclientconsultation.mapper.toKotlin
import com.example.mobileclientconsultation.network.networkConnection.contacteServeur
import hepl.faad.Bibliotheque.Reponse_Search_Consultations
import hepl.faad.Bibliotheque.Requete_Search_Consultations
import hepl.faad.model.entity.Consultation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelHome : ViewModel(){
    /*public val consultationPrivate = MutableLiveData<List<kConsultation>>()
    val consultationPublic : LiveData<List<kConsultation>> get() = consultationPrivate

    suspend fun getConsultation(req : Requete_Search_Consultations) {
        var consultList : List<kConsultation> = emptyList()

        val listConsult = contacteServeur(req)
        withContext(Dispatchers.Main) {
            (listConsult as? Reponse_Search_Consultations)
                ?.consultation?.let { consultations ->
                    consultationsPrivate.value = consultations.map{it.toKotlin()}
                } ?: run {
                consultationPrivate = emptyList()
            }
        }

    }*/

}