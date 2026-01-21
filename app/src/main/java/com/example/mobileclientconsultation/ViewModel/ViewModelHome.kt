package com.example.mobileclientconsultation.ViewModel



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.mobileclientconsultation.entity.kConsultation
import com.example.mobileclientconsultation.entity.kPatient
import com.example.mobileclientconsultation.mapper.toKotlin
import com.example.mobileclientconsultation.network.networkConnection.contacteServeur
import hepl.faad.Bibliotheque.Reponse_Add_Consultation
import hepl.faad.Bibliotheque.Reponse_Add_Patient
import hepl.faad.Bibliotheque.Reponse_All_Patient
import hepl.faad.Bibliotheque.Reponse_Delete_Consultation
import hepl.faad.Bibliotheque.Reponse_Search_Consultations
import hepl.faad.Bibliotheque.Reponse_Update_Consultation
import hepl.faad.Bibliotheque.Requete_Add_Consultation
import hepl.faad.Bibliotheque.Requete_Add_Patient
import hepl.faad.Bibliotheque.Requete_All_Patient
import hepl.faad.Bibliotheque.Requete_Delete_Consultation
import hepl.faad.Bibliotheque.Requete_Logout
import hepl.faad.Bibliotheque.Requete_Search_Consultations
import hepl.faad.Bibliotheque.Requete_Update_Consultation
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

    private val currentConsultPrivate = MutableLiveData<kConsultation>()
    val currentConsultPublic : LiveData<kConsultation> get() = currentConsultPrivate

    fun setDoctorId(id: Int) {
        doctorIdPrivate.value = id
    }

    fun setCurrentConsult(consult : kConsultation){
        currentConsultPrivate.value = consult
    }


    suspend fun getPatient(req : Requete_All_Patient){
        val listPatient = withContext(Dispatchers.IO){contacteServeur(req)}

        (listPatient as? Reponse_All_Patient)
            ?.listePatient?.let { patients ->
                patientPrivate.postValue(patients.map { it.toKotlin() })
            }
    }

    suspend fun addConsult(req : Requete_Add_Consultation){
        val consult = withContext(Dispatchers.IO){contacteServeur(req)}

        (consult as? Reponse_Add_Consultation)
            ?.done?.let { done ->
                if(done){
                    messageToastPrivate.value = "consultation supprimée"

                }
                else{
                    messageToastPrivate.value = "erreur de suppression"
                }
            }
    }

    suspend fun updateConsultation(req : Requete_Update_Consultation){
        val consult = withContext(Dispatchers.IO){contacteServeur(req)}

        (consult as? Reponse_Update_Consultation)
            ?.done?.let { done ->
                if(done){
                    messageToastPrivate.value = "consultation supprimée"

                }
                else{
                    messageToastPrivate.value = "erreur de suppression"
                }
            }
    }


     suspend fun getConsultations(req : Requete_Search_Consultations){

            val listeConsult = withContext(Dispatchers.IO){contacteServeur(req)}

            (listeConsult as? Reponse_Search_Consultations)
                ?.consultation?.let { consultations ->
                     consultationPrivate.postValue(consultations.map { it.toKotlin() })



                }
    }

    suspend fun logout(req : Requete_Logout){
        withContext(Dispatchers.IO){contacteServeur(req)}
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