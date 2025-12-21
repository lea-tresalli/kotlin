package hepl.faad.Bibliotheque;

public class Reponse_Login implements Reponse{
    String message;
    Integer idDoctor;

    public Reponse_Login(String message, Integer idDoctor) {
        this.message = message;
        this.idDoctor = idDoctor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }
}
