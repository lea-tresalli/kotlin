package hepl.faad.Bibliotheque;

import com.example.mobileclientconsultation.entity.Patient;

import java.sql.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Requete_Search_Consultations implements Requete{
    ArrayList<Patient> patient;
    Integer id_Doctor;
    LocalDate dateDebut;
    LocalDate dateFin;


    public Requete_Search_Consultations(Patient patient, LocalDate dateDebut, LocalDate dateFin, Integer id_Doctor) {
        this.patient.add(patient);
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.id_Doctor = id_Doctor;
    }
    public Requete_Search_Consultations(ArrayList<Patient> patient, LocalDate dateDebut, LocalDate dateFin, Integer id_Doctor) {
        this.patient = patient;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.id_Doctor = id_Doctor;
    }

    public Integer getId_Doctor() {
        return id_Doctor;
    }

    public void setId_Doctor(Integer id_Doctor) {
        this.id_Doctor = id_Doctor;
    }

    public ArrayList<Patient> getPatient() {
        return patient;
    }

    public void setPatient(ArrayList<Patient> patient) {
        this.patient = patient;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}
