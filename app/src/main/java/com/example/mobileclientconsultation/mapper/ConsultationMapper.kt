package com.example.mobileclientconsultation.mapper

import com.example.mobileclientconsultation.entity.kConsultation
import com.example.mobileclientconsultation.entity.kPatient
import hepl.faad.model.entity.Consultation
import hepl.faad.model.entity.Patient

fun kConsultation.toJava() : Consultation{
    val jConsultation = Consultation()
    jConsultation.id = this.id
    jConsultation.doctor = this.idDoctor
    jConsultation.patient = this.patient?.toJava()
    jConsultation.reason = this.reason
    jConsultation.hour = this.hour
    jConsultation.date = this.date
    return jConsultation
}

fun Consultation.toKotlin() : kConsultation{

    return kConsultation(
        id = this.id,
        idDoctor = this.doctor ,
        patient = this.patient?.toKotlin(),
        reason = this.reason,
        hour = this.hour,
        date = this.date
    )
}