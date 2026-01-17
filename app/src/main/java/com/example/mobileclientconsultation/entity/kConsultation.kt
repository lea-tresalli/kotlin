package com.example.mobileclientconsultation.entity

import java.time.LocalDate

import java.time.LocalTime

data class kConsultation (
    var id: Int? = null,
    var idDoctor: Int?=null,
    var patient: kPatient?=null,
    var date: LocalDate? = null,
    var hour: LocalTime? = null,
    var reason: String? = null,
){
    override fun toString() :String{
        return "$id ${patient?.firstName} ${patient?.lastName}($id) $date $hour $reason ";
    }
}