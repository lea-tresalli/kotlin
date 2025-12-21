package hepl.faad.Bibliotheque;

public class Reponse_Delete_Consultation implements Reponse{

    private Boolean done;

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getDone() {
        return done;
    }
    public Reponse_Delete_Consultation(Boolean done) {
        setDone(done);
    }
}
