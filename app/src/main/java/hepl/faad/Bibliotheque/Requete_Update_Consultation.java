package hepl.faad.Bibliotheque;

import hepl.faad.model.entity.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Requete_Update_Consultation implements Requete{
    Integer id;
    Integer id_cons;
    LocalDate date_consultation;
    LocalTime time_consultation;
    Patient patient;
    String reason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_cons() {
        return id_cons;
    }

    public void  setId_cons(Integer id_cons) {
        this.id_cons = id_cons;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalTime getTime_consultation() {
        return time_consultation;
    }

    public void setTime_consultation(LocalTime time_consultation) {
        this.time_consultation = time_consultation;
    }

    public LocalDate getDate_consultation() {
        return date_consultation;
    }

    public void setDate_consultation(LocalDate date_consultation) {
        this.date_consultation = date_consultation;
    }

    public Requete_Update_Consultation(Integer id,Integer id_cons, LocalDate date, LocalTime time, Patient patient, String reason){
        setId(id);
        setId_cons(id_cons);
        setDate_consultation(date);
        setPatient(patient);
        setReason(reason);
        setTime_consultation(time);
    }
}
