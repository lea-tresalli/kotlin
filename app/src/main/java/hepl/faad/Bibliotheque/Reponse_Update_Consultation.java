package hepl.faad.Bibliotheque;

public class Reponse_Update_Consultation implements Reponse{
    Boolean done;
    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getDone() {
        return done;
    }

    public Reponse_Update_Consultation(Boolean done) {
        setDone(done);
    }
}
