package hepl.faad.Bibliotheque;

public class Requete_Delete_Consultation implements Requete{
    Integer id;

    public  Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Requete_Delete_Consultation(Integer id) {
        setId(id);
    }
}
