package hepl.faad.Bibliotheque;

import com.example.mobileclientconsultation.entity.Patient;

public class Reponse_Add_Patient implements Reponse {
    Integer id;
    String message;

    public Reponse_Add_Patient(Integer id, String message) {

        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
