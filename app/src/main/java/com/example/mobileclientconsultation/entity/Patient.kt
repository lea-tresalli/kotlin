package com.example.mobileclientconsultation.entity

import java.time.LocalDate


data class Patient (
    var id: Int? = null,
    var firstName: String?=null,
    var lastName: String?=null,
    var birthDate: LocalDate? = null
){
    override fun toString() :String{
        return "$lastName $firstName($id)"
    }
}