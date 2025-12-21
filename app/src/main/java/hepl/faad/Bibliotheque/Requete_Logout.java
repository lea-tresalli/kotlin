package hepl.faad.Bibliotheque;

public class Requete_Logout implements Requete{
    Integer id;

    public Requete_Logout(Integer id){
        this.id = id;
    }

    public void setId(Integer id){
        this.id = id;
    }
    public  Integer getId(){
        return id;
    }
}
