package hepl.faad.Bibliotheque;



import java.sql.Date;
import java.time.LocalDate;

public class Requete_Add_Patient implements Requete {
    String nom;
    String prenom;

    LocalDate date;

    public Requete_Add_Patient(String nom, String prenom, LocalDate date) {
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
