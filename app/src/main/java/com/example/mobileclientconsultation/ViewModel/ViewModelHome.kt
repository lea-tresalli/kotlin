package com.example.mobileclientconsultation.ViewModel


import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.mobileclientconsultation.adapters.ListeConsultationAdapter

import com.example.mobileclientconsultation.entity.kConsultation
import com.example.mobileclientconsultation.mapper.toKotlin
import com.example.mobileclientconsultation.network.networkConnection.contacteServeur
import hepl.faad.Bibliotheque.Reponse_Search_Consultations
import hepl.faad.Bibliotheque.Requete_Search_Consultations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ViewModelHome : ViewModel(){
    private val consultationPrivate = MutableLiveData<List<kConsultation>>()
    val consultationPublic : LiveData<List<kConsultation>> get() = consultationPrivate

    private val doctorIdPrivate = MutableLiveData<Int>()
    val doctorIdPublic: LiveData<Int> = doctorIdPrivate

    fun setDoctorId(id: Int) {
        doctorIdPrivate.value = id
    }




    /*suspend fun getConsultation(req : Requete_Search_Consultations) {
        var consultList : List<kConsultation> = emptyList()

        val listConsult = contacteServeur(req)
        withContext(Dispatchers.Main) {
            (listConsult as? Reponse_Search_Consultations)
                ?.consultation?.let { consultations ->
                    consultationsPrivate.value = consultations.map{it.toKotlin()}
                } ?: run {
                consultationPrivate = emptyList()
            }
        }*/


     suspend fun getConsultations(req : Requete_Search_Consultations){

            val listeConsult = withContext(Dispatchers.IO){contacteServeur(req)}

            (listeConsult as? Reponse_Search_Consultations)
                ?.consultation?.let { consultations ->
                     consultationPrivate.postValue(consultations.map { it.toKotlin() })



                }
    }





}