package com.example.mobileclientconsultation.entity

import android.os.Parcelable
import java.time.LocalDate

import java.time.LocalTime

data class Consultation (
   var id: Int? = null,
    var idDoctor: Int?=null,
    var Patient: Patient?=null,
    var date: LocalDate? = null,
    var hour: LocalTime? = null,
    var reason: String? = null,
){
    override fun toString() :String{
        return "$id ${Patient?.firstName} ${Patient?.lastName}($id) $date $hour $reason ";
    }
}