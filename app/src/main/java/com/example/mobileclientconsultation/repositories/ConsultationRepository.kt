package com.example.mobileclientconsultation.repositories

import com.example.mobileclientconsultation.entity.kConsultation
import com.example.mobileclientconsultation.mapper.toKotlin
import com.example.mobileclientconsultation.network.networkConnection.contacteServeur
import hepl.faad.Bibliotheque.Reponse_Search_Consultations
import hepl.faad.Bibliotheque.Requete_Search_Consultations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext




