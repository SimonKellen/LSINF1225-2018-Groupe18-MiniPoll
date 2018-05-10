package be.lsinf1225.minipoll.Classes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;
import android.content.ContentValues;

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

    public Utilisateur(int id, String motDePasse, String nom, String prenom, String identifiant, String photo, String mail) {
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


    public void demande_ami(Utilisateur utilisateur){
        this.getDemandeAmis().put(utilisateur.id, utilisateur);

        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_User1", this.getIdentifiant());
        values.put("ID_User2", utilisateur.getIdentifiant());
        values.put("Etat", "E");
        // Inserting Row
        db.insert("Ami", null, values);
        db.close(); // Closing database connection
    }

    public void accepter_demande_ami(Utilisateur demandeAmis){
        this.getAmis().put(demandeAmis.id, demandeAmis);
        demandeAmis.getAmis().put(this.id, this);

        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("Etat", "A");
        // updating row
        db.update("Ami", values, "ID_User1 = '" + demandeAmis.getIdentifiant() + "' AND ID_User2 = '" + this.getIdentifiant() + "'", null);
        db.close();
    }

    public void addUtilisateurInDb(Utilisateur utilisateur){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_User",utilisateur.getId());
        values.put("Prenom",utilisateur.getPrenom());
        values.put("Nom",utilisateur.getNom());
        values.put("Password",utilisateur.getMotDePasse());
        values.put("BestFriend",utilisateur.getBestFriend().getId());
        values.put("Pic",utilisateur.getPhoto());
        values.put("Mail",utilisateur.getMail());
        values.put("Identifiant",utilisateur.getIdentifiant());
        db.insert("Utilisateurs",null,values);
        db.close();
    }

    public void addurInDb(Utilisateur utilisateur,Question question,int choosenIndex, int givenvalue){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_User",utilisateur.getId());
        int rep[]=question.getIdreponse();
        for(int i=0;i<rep.length;i++){
            values.put("ID_Reponse",rep[i]);
            if(i==choosenIndex){
                values.put("Score",givenvalue);
            }
            else{
                values.put("Score",0);
            }
            db.insert("UtilisateurRéponse",null,values);
        }
        db.close();
    }




    //la fonction peut aussi être appelée pour supprimer un amis
    public void refuser_demande_ami(Utilisateur demandeAmis){
        this.getAmis().remove(demandeAmis.id);
        demandeAmis.getAmis().remove(this.id);

        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        db.delete("Amis", "ID_User1 = '" + demandeAmis.getIdentifiant() + "' AND ID_User2 = '" + this.getIdentifiant() + "'",null);//TODO: c'est pas getID que tu voulais appeler? et faut aussi faire le cas inverse au cas où l'amitier aurait été définie dans l'autre sens comme ça on peut utiliser la fonction aussi pour supprimer un ami
        db.close(); // Closing database connection
    }




    /**
     * Contient les instances déjà existantes des utilisateurs afin d'éviter de créer deux instances
     * du même utilisateur.
     */
    public static SparseArray<Utilisateur> userSparseArray = new SparseArray<>();

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

    public int getId() {
        return id;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAmis(Utilisateur ami) {
        this.amis.put(ami.id, ami);
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
        String ancienMotDePasse1 = "db.getancienmotdepasse";//TODO: genre ça marche ça?(question de Martin)
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
    public static Utilisateur isUtilisateur(String identifiant) {
        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonne à récupérer
        String[] colonnes = {"ID_User", "Password", "Nom", "Prenom", "Identifiant", "Pic", "BestFriend", "Mail"};

        // Requête de selection (SELECT)
        Cursor cursor = db.query("Utilisateurs", colonnes, null, null, null, null, null);
        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            String ident = cursor.getString(4);

            if (ident.equals(identifiant)) {
                int idu = cursor.getInt(0);
                Utilisateur user = Utilisateur.userSparseArray.get(idu);
                if(user==null){
                    String motDePasse = cursor.getString(1);
                    String name = cursor.getString(2);
                    String pren = cursor.getString(3);
                    String pho = cursor.getString(5);
                    String email = cursor.getString(6);
                    cursor.close();
                    db.close();
                    user = new Utilisateur(idu, motDePasse, name, pren, ident, pho, email);
                }
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
        Cursor cursor = db.rawQuery("SELECT * FROM Utilisateurs WHERE ID_User in (SELECT ID_User1 FROM Ami WHERE ID_User2 = 9 and Etat = 'A') UNION SELECT * FROM Utilisateurs WHERE ID_User in (SELECT ID_User2 FROM Ami WHERE ID_User1 = 9 and Etat = 'A')",null );//TODO Martin remplacer le 9 (Simon: commande pour recuperer toute la ligne des utilisateurs etant amis avec l utilisateur dont l id vaut 9 par exemple)
        cursor.moveToFirst();
        SparseArray<Utilisateur> users = new SparseArray<>();
        while (!cursor.isAfterLast()){
            int idu = cursor.getInt(0);
            String prenom = cursor.getString(1);
            String nom = cursor.getString(2);
            String password = cursor.getString(3);
            String photo = cursor.getString(5);
            String mail = cursor.getString(6);
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
        this.amis=users;
        return users;
    }

    /**
     *
     * @return liste des utilisateurs lui ayant envoye une demande d ami
     */
    public SparseArray<Utilisateur> getDemandeAmisdb(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT \"ID_User\", \"Nom\", \"Prenom\", \"Mail\", \"Pic\", \"Password\", \"BestFriend\" FROM Utilisateurs U INNER JOIN Ami A ON U.ID_User = A.ID_User1 WHERE Etat = 'E' AND A.ID_User2 = 2",null );//TODO Martin remplacer le 2, on est bien d'accord que quand on envoie une demande d'ami, il crée une ligne avec comme ID_User1 celui qui a envoyé la demande, ID_User2 celui qui reçoit la demande ?(Simon: commande pour recuperer toute la ligne des utilisateurs ayant envoye une demande d ami a l utilisateur dont l id est 9 par exemple)
        cursor.moveToFirst();
        SparseArray<Utilisateur> users = new SparseArray<>();
        while (!cursor.isAfterLast()){
            int idu = cursor.getInt(0);
            String prenom = cursor.getString(1);
            String nom = cursor.getString(2);
            String password = cursor.getString(3);
            String photo = cursor.getString(5);
            String mail = cursor.getString(6);
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
        this.amis=users;
        return users;
    }

    /**
     *
     * @return liste des poll cree par un utilisateur
     */
    public SparseArray<Poll> getListePoll(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT \"ID_Poll\", \"ID_User\", \"Etat\", \"Titre\", \"Type\" FROM Poll P INNER JOIN Utilisateurs U on P.ID_User = U.ID_User WHERE U.ID_User = 4",null );//TODO Martin Remplacer le 4(Simon: commande pour recuperer toute la ligne des Poll cree par l utilisateur dont l id vaut 9 par exemple)
        cursor.moveToFirst();
        SparseArray<Poll> polls = new SparseArray<>();
        while (!cursor.isAfterLast()){
            int idp = cursor.getInt(0);
            String etat = cursor.getString(2);
            String titre = cursor.getString(3);
            String type = cursor.getString(4);
            if(type=="A"){
                //alors c est un sondage pour choix (2 propositions)
                Sondage_Pour_Choix spc = Sondage_Pour_Choix.spcSparseArray.get(idp);
                if(spc==null){
                    spc = new Sondage_Pour_Choix(idp, titre, this, null, null);
                }
                polls.put(idp,spc);
            }
            if(type=="S"){
                //alors c est un sondage pour accord
                Sondage_Pour_Accord spa = Sondage_Pour_Accord.spaSparseArray.get(idp);
                if(spa==null){
                    spa=new Sondage_Pour_Accord(idp,this, titre, null, null, -1);
                }
                polls.put(idp,spa);
            }
            else{
                //alors c est un questionnaire
                Questionnaire ques = Questionnaire.quesSparseArray.get(idp);
                if(ques==null){
                    ques=new Questionnaire(idp, this ,titre, null, null,etat="o");
                }
                polls.put(idp,ques);
            }
        }
        cursor.close();
        db.close();
        this.poll=polls;
        return polls;
    }

    public static ArrayList<Utilisateur> getUtilisateurs()
    {
        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer
        String[] colonnes = {"ID_User", "Password", "Nom", "Prenom", "Identifiant", "Pic", "BestFriend", "Mail"};

        // Requête de selection (SELECT)
        Cursor cursor = db.query("Utilisateurs", colonnes, null, null, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Initialisation la liste des utilisateurs.
        ArrayList<Utilisateur> users = new ArrayList<>();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int uId = cursor.getInt(0);
            String motDePasse = cursor.getString(1);
            String name = cursor.getString(2);
            String pren = cursor.getString(3);
            String identifiant = cursor.getString(4);
            String pho = cursor.getString(5);
            String email = cursor.getString(6);

            // Vérification pour savoir s'il y a déjà une instance de cet utilisateur.
            Utilisateur user = Utilisateur.userSparseArray.get(uId);
            if (user == null) {
                // Si pas encore d'instance, création d'une nouvelle instance.
                user = new Utilisateur(uId,motDePasse,name,pren,identifiant,pho,email);
            }

            // Ajout de l'utilisateur à la liste.
            users.add(user);

            // Passe à la ligne suivante.
            cursor.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return users;
    }
    public int getLowestUserIdAvailable(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(ID_User) FROM Utilisateurs ",null );
        cursor.moveToFirst();
        int uIdMAX=0;
        while (!cursor.isAfterLast()) {
            uIdMAX = cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        return uIdMAX+1;
    }

}

