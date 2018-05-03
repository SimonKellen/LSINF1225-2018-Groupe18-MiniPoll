package be.lsinf1225.minipoll;

import java.sql.Blob;
import java.util.ArrayList;

/**
 * Created by Simon Kellen on 30-04-18. J'avoue c mwa ki lé fé
 */

public class Utilisateur {

    // Variables

    public static int idUtilisateur = 1;
    public int id;
    public static ArrayList listeUtilisateurs;
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
        listeUtilisateurs.add(this);
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.photo = photo;
        this.bestFriend = null;
        this.amis = null;
        this.demandeAmis = null;
        this.poll = null;
        this.id = idUtilisateur;
        idUtilisateur++;
    }

    //Demande d'ami (TODO) (lien avec la bdd)

    public void demande_ami(Utilisateur utilisateur){
        utilisateur.ajout_Ami(this);
    }
}
