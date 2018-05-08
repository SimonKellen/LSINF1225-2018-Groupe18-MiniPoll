package be.lsinf1225.minipoll.Classes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.util.ArrayList;

public class Utilisateur {

    // Variables

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

    private Utilisateur(int id, String motDePasse, String nom, String prenom, String identifiant, String photo, String mail) {
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

    /**
     * Connecte l'utilisateur courant.
     *
     * @param passwordToTry le mot de passe entré.
     * @return Vrai (true) si l'utilisateur à l'autorisation de se connecter, false sinon.
     */
    public boolean login(String passwordToTry) {
        if (this.motDePasse.equals(passwordToTry)) {
            // Si le mot de passe est correct, modification de l'utilisateur connecté.
            Utilisateur.connectedUser = this;
            return true;
        }
        return false;
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

    public void modifierMotDePasse(String nouveauMotDePasse, String nouveauMotDePasse1, String ancienMotDePasse) {
        String ancienMotDePasse1 = "db.getancienmotdepasse";
        if ((ancienMotDePasse == ancienMotDePasse1) && (nouveauMotDePasse == nouveauMotDePasse1)) {
            this.motDePasse = nouveauMotDePasse;
        } else {
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

    /**
     * <<<<<<< HEAD
     * Verifie si l utilisateur existe.
     */
    public Utilisateur isUtilisateur(String identifiant) {
        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonne à récupérer
        String[] colonnes = {"ID_User", "Password", "Nom", "Prenom", "Identifiant", "Pic", "BestFriend", "Mail"};

        // Requête de selection (SELECT)
        Cursor cursor = db.query("Utilisateur", colonnes, null, null, null, null, null);
        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            String ident = cursor.getString(4);

            if (ident == identifiant) {
                int idu = cursor.getInt(0);
                String motDePasse = cursor.getString(1);
                String name = cursor.getString(2);
                String pren = cursor.getString(3);
                String pho = cursor.getString(5);
                String email = cursor.getString(6);
                cursor.close();
                db.close();
                Utilisateur user = new Utilisateur(idu, motDePasse, name, pren, ident, pho, email);
                return user;
            }

            // Passe à la ligne suivante.
            cursor.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return null;
    }

    /**
     *
     * @return liste des amis d un utilisateur
     */
    public SparseArray<Utilisateur> getListeAmis(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Utilisateurs WHERE ID_User!=9",null );//TODO Simon: commande pour recuperer toute la ligne des utilisateurs etant amis avec l utilisateur dont l id vaut 9 par exemple
        cursor.moveToFirst();
        SparseArray<Utilisateur> users = new SparseArray<>();
        while (!cursor.isAfterLast()){
            int idu = cursor.getInt(0);
            String prenom = cursor.getString(1);
            String nom = cursor.getString(2);
            String password = cursor.getString(3);
            String photo = cursor.getString(5);
            String email = cursor.getString(6);
            String identifiant = cursor.getString(7);
            Utilisateur user = Utilisateur.userSparseArray.get(idu);
            if(user==null){
                user=new Utilisateur(idu,password,nom,prenom,identifiant,photo,mail);
            }
            users.put(idu,user);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return users;
    }

}

