package hepl.faad.Bibliotheque;

import java.time.LocalDate;
import java.time.LocalTime;

public class Requete_Add_Consultation implements Requete{
    LocalDate date;
    LocalTime time;
    Integer duree;
    Integer nbConsultation;

    Integer idDoctor;

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Integer getNbConsultation() {
        return nbConsultation;
    }

    public void setNbConsultation(Integer nbConsultation) {
        this.nbConsultation = nbConsultation;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Requete_Add_Consultation(LocalDate date, LocalTime time, Integer duree, Integer nbConsultation, Integer idDoctor) {
        setDate(date);
        setTime(time);
        setDuree(duree);
        setNbConsultation(nbConsultation);
        setIdDoctor(idDoctor);
    }

    public Requete_Add_Consultation(){
        setTime(null);
        setDuree(null);
        setNbConsultation(null);
        setDate(null);
        setIdDoctor(null);
    }
}
