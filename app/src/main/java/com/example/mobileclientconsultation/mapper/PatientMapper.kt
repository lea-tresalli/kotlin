package com.example.mobileclientconsultation.mapper

import com.example.mobileclientconsultation.entity.kPatient
import hepl.faad.model.entity.Patient

fun kPatient.toJava() : Patient{
    val JPatient = Patient()
    JPatient.id = this.id
    JPatient.lastName = this.lastName
    JPatient.firstName = this.firstName
    JPatient.birthDate = this.birthDate
    return JPatient
}

fun Patient.toKotlin() : kPatient{
    return kPatient(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        birthDate = this.birthDate

    )
}