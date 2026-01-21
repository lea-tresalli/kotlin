package com.example.mobileclientconsultation.ViewModel



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.mobileclientconsultation.entity.kConsultation
import com.example.mobileclientconsultation.entity.kPatient
import com.example.mobileclientconsultation.mapper.toKotlin
import com.example.mobileclientconsultation.network.networkConnection.contacteServeur
import hepl.faad.Bibliotheque.Reponse_Add_Patient
import hepl.faad.Bibliotheque.Reponse_All_Patient
import hepl.faad.Bibliotheque.Reponse_Delete_Consultation
import hepl.faad.Bibliotheque.Reponse_Search_Consultations
import hepl.faad.Bibliotheque.Requete_Add_Patient
import hepl.faad.Bibliotheque.Requete_All_Patient
import hepl.faad.Bibliotheque.Requete_Delete_Consultation
import hepl.faad.Bibliotheque.Requete_Search_Consultations
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.withContext


class ViewModelHome : ViewModel(){
    private val patientPrivate = MutableLiveData<List<kPatient>>()
    val patientPublic : LiveData<List<kPatient>> get() = patientPrivate
    private val messageToastPrivate = MutableLiveData<String>()
    val messageToastPublic: LiveData<String> = messageToastPrivate

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

    suspend fun getPatient(req : Requete_All_Patient){
        val listPatient = withContext(Dispatchers.IO){contacteServeur(req)}

        (listPatient as? Reponse_All_Patient)
            ?.listePatient?.let { patients ->
                patientPrivate.postValue(patients.map { it.toKotlin() })
            }
    }


     suspend fun getConsultations(req : Requete_Search_Consultations){

            val listeConsult = withContext(Dispatchers.IO){contacteServeur(req)}

            (listeConsult as? Reponse_Search_Consultations)
                ?.consultation?.let { consultations ->
                     consultationPrivate.postValue(consultations.map { it.toKotlin() })



                }
    }
    suspend fun DeleteConsultation(req : Requete_Delete_Consultation){

        val listeConsult = withContext(Dispatchers.IO){contacteServeur(req)}

        (listeConsult as? Reponse_Delete_Consultation)
            ?.done?.let { done ->
                if(done){
                    messageToastPrivate.value = "consultation supprimée"
                }
                else{
                    messageToastPrivate.value = "erreur de suppression"
                }


            }
    }

    suspend fun addPatient(req : Requete_Add_Patient){

        val listeConsult = withContext(Dispatchers.IO){contacteServeur(req)}

        (listeConsult as? Reponse_Add_Patient)
            ?.message?.let { mess ->
                messageToastPrivate.value = mess


            }
    }






}