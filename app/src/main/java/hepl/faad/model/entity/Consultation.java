package hepl.faad.model.entity;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Consultation implements Serializable {
    private Integer id;
    private Integer idDoctor;
    private Patient patient;
    private LocalDate date;
    private String reason;
    private LocalTime hour;

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctor() {
        return idDoctor;
    }

    public void setDoctor(Integer doctor) {
        this.idDoctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Consultation(Integer id, Integer doctor, Patient patient, LocalDate date, String reason, LocalTime hour) {
        setId(id);
        setDoctor(doctor);
        setPatient(patient);
        setDate(date);
        setReason(reason);
        setHour(hour);
    }

    public Consultation() {
        setId(null);
        setDoctor(null);
        setPatient(null);
        setDate(null);
        setReason(null);
        setHour(null);
    }

    public String toString() {
        return "Consultation [id=" + id + ", doctor=" + idDoctor + " patient=" + patient.toString() + ", date=" + date +  ", reason=" + reason + "]";
    }

}
