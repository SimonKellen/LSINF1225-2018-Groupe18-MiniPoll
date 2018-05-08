package be.lsinf1225.minipoll.Classes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.util.ArrayList;

public class Utilisateur {

    // Variablees

    private final int id;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String identifiant;
    private String photo;
    private Utilisateur bestFriend;
    private SparseArray<Utilisateur> amis;
    private SparseArray<Utilisateur> demandeAmis;
    private SparseArray<Poll> poll;
    private String mail;

    // Constructeur

    private Utilisateur(int id, String motDePasse, String nom, String prenom, String identifiant, String photo, String mail){
        this.id = id;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.photo = photo;
        this.bestFriend = null;
        this.amis = null;
        this.demandeAmis = null;
        this.poll = null;
        this.mail = mail;
        Utilisateur.userSparseArray.put(id, this);
    }

    //Demande d'ami TODO (lien avec la bdd)

    //public void demande_ami(Utilisateur utilisateur){
        //utilisateur.ajout_Ami(this);
    //}


    /**
     * Contient les instances déjà existantes des utilisateurs afin d'éviter de créer deux instances
     * du même utilisateur.
     */
    private static SparseArray<Utilisateur> userSparseArray = new SparseArray<>();

    /**
     * Utilisateur actuellement connecté à l'application. Correspond à null si aucun utilisateur
     * n'est connecté.
     */
    private static Utilisateur connectedUser = null;

    /**
     * Fournit l'utilisateur actuellement connecté.
     */
    public static Utilisateur getConnectedUser() {
        return Utilisateur.connectedUser;
    }

    /**
     * Déconnecte l'utilisateur actuellement connecté à l'application.
     */
    public static void logout() {
        Utilisateur.connectedUser = null;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public SparseArray<Utilisateur> getAmis() {
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

    public SparseArray<Poll> getPoll() {
        return poll;
    }

    public SparseArray<Utilisateur> getDemandeAmis() {
        return demandeAmis;
    }

    public void setPoll(SparseArray<Poll> poll) {
        this.poll = poll;
    }

    public void setAmis(SparseArray<Utilisateur> amis) {
        this.amis = amis;
    }

    public void setDemandeAmis(SparseArray<Utilisateur> demandeAmis) {
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
