package be.lsinf1225.minipoll.Classes;

import java.util.ArrayList;
import be.lsinf1225.minipoll.Classes.Tools;

/**
 * Created by Simon Kellen on 30-04-18. J'avoue c mwa ki lé fé
 */

public class Utilisateur {

    // Variablees


    public String motDePasse;
    public String nom;
    public String prenom;
    public String identifiant;
    public String photo;
    public Utilisateur bestFriend;
    public ArrayList amis;
    public ArrayList demandeAmis;
    public ArrayList poll;

    // Constructeur

    public Utilisateur(String motDePasse, String nom, String prenom, String identifiant, String photo){
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.photo = photo;
        this.bestFriend = null;
        this.amis = null;
        this.demandeAmis = null;
        this.poll = null;
    }

    //Demande d'ami (TODO) (lien avec la bdd)

    //public void demande_ami(Utilisateur utilisateur){
        //utilisateur.ajout_Ami(this);
    //}



    public String getIdentifiant() {
        return identifiant;
    }

    public ArrayList getAmis() {
        return amis;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public Utilisateur getBestFriend() {
        return bestFriend;
    }

    public ArrayList getPoll() {
        return poll;
    }

    public ArrayList getDemandeAmis() {
        return demandeAmis;
    }

    public void setPoll(ArrayList poll) {
        this.poll = poll;
    }

    public void setAmis(ArrayList amis) {
        this.amis = amis;
    }

    public void setDemandeAmis(ArrayList demandeAmis) {
        this.demandeAmis = demandeAmis;
    }

    public void setBestFriend(Utilisateur bestFriend) {
        this.bestFriend = bestFriend;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setMotDePasse(String motDePasse) {
        if (this.motDePasse == null) {
            this.motDePasse = motDePasse;
        }
    }

    public void modifierMotDePasse(String nouveauMotDePasse, String nouveauMotDePasse1, String ancienMotDePasse){
        String ancienMotDePasse1 = "db.getancienmotdepasse";
                if((ancienMotDePasse == ancienMotDePasse1) && (nouveauMotDePasse == nouveauMotDePasse1)){
                    this.motDePasse = nouveauMotDePasse;
                }
                else{
                    //"toast mauvais mot de passe"
                }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
