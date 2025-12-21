package hepl.faad.Bibliotheque;

import com.example.mobileclientconsultation.entity.Patient;

import java.util.ArrayList;

public class Reponse_All_Patient implements Reponse{
    ArrayList<Patient> listePatient = new ArrayList<>();

    public Reponse_All_Patient(ArrayList<Patient> listePatient) {
        this.listePatient = listePatient;
    }

    public ArrayList<Patient> getListePatient() {
        return listePatient;
    }
    public void setListePatient(ArrayList<Patient> listePatient) {
        this.listePatient = listePatient;
    }


}
