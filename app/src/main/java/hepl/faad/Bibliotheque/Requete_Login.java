package hepl.faad.Bibliotheque;



public class Requete_Login implements Requete{
    String nom;
    String mdp;

    public Requete_Login(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
