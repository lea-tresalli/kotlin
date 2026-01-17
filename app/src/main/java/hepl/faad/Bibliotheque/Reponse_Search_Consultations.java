package hepl.faad.Bibliotheque;

import hepl.faad.model.entity.Consultation;

import java.util.ArrayList;

public class Reponse_Search_Consultations implements Reponse
{
    private ArrayList<Consultation> consultation;

    public Reponse_Search_Consultations(ArrayList<Consultation> consultation)
    {
        this.consultation = consultation;
    }
    public ArrayList<Consultation> getConsultation() {
        return consultation;
    }
    public void setConsultation(ArrayList<Consultation> consultation) {
        this.consultation = consultation;
    }
}
