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

    //les Méthodes

    /*
    Lorsque l'utilisateur envoit une demande d'ami à un autre utilisateur(Vérifier auparavant qu'il existe avec isUtilisateur)
    Met à jour la bdd une fois l'opération effectuée
     */
    public void demande_ami(Utilisateur utilisateur){
        utilisateur.getDemandeAmis().put(this.getId(), this);

        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_User1", this.getId());
        values.put("ID_User2", utilisateur.getId());
        values.put("Etat", "E");
        // Inserting Row
        db.insert("Ami", null, values);
        db.close(); // Closing database connection
    }

    /*
    Lorsque l'on veut accepter des demandes d'amis on en choisit une pour l'accepter, la base de données est mise à jour
     */
    public void accepter_demande_ami(Utilisateur demandeAmis){
        this.getAmis().put(demandeAmis.getId(), demandeAmis);
        demandeAmis.getAmis().put(this.id, this);

        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("Etat", "A");
        // updating row
        db.update("Ami", values, "ID_User1 = '" + demandeAmis.getId() + "' AND ID_User2 = '" + this.getId() + "'", null);
        db.close();
    }

    /*
    ajoute l'utilisateur en argument dans la base de donnée(vérifier auparavent si il existait déjà)
     */
    public static void addUtilisateurInDb(Utilisateur utilisateur){
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

    /*
    ajoute un utilisateur-reponse dans la base de donnée
     */
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



    /*
    refuse la demande d'amitier d'un utilisateur, supprime la relation dans la bdd et retire l'utilisateur de la liste des demandes d'ami
     */
    public void refuser_demande_ami(Utilisateur demandeAmis){
        this.getAmis().remove(demandeAmis.id);
        demandeAmis.getAmis().remove(this.id);

        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        db.delete("Amis", "ID_User1 = '" + demandeAmis.getIdentifiant() + "' AND ID_User2 = '" + this.getIdentifiant() + "'",null);//TODO: c'est pas getID que tu voulais appeler?
        db.close(); // Closing database connection
    }


    /**
     * Contient les instances déjà existantes des utilisateurs afin d'éviter de créer deux instances
     * du même utilisateur.
     */
    public static SparseArray<Utilisateur> userSparseArray = new SparseArray<>();
    public static int userCount;

    /**
     * Utilisateur actuellement connecté à l'application. Correspond à null si aucun utilisateur
     * n'est connecté.
     */
    public static Utilisateur connectedUser = null;

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

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public int getId() {
        return id;
    }


    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public SparseArray<Utilisateur> getAmis() {
        return amis;
    }


    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public String getNom() {
        return nom;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public String getPrenom() {
        return prenom;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public String getPhoto() {
        return photo;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public Utilisateur getBestFriend() {
        return bestFriend;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public SparseArray<Poll> getPoll() {
        return poll;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public SparseArray<Utilisateur> getDemandeAmis() {
        return demandeAmis;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public void setPoll(SparseArray<Poll> poll) {
        this.poll = poll;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public String getMail() {
        return mail;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public void setAmis(Utilisateur ami) {
        this.amis.put(ami.id, ami);
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public void setDemandeAmis(SparseArray<Utilisateur> demandeAmis) {
        this.demandeAmis = demandeAmis;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public void setBestFriend(Utilisateur bestFriend) {
        this.bestFriend = bestFriend;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /*
    fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
     */
    public void setMotDePasse(String motDePasse) {
        if (this.motDePasse == null) {
            this.motDePasse = motDePasse;
        }
    }

    /*
    modifie la bdd ainsi que l'objet et retourne true si la modification a pu être faite
    Si false est retourne alors l'identifiant existe deja pour un autre utilisateur ou alors le mot de passe est faux
     */
    public boolean modifierIdentifiant(String nouveauIdentifiant, String motDePasse){
        if(this.motDePasse.equals(motDePasse)){
            Utilisateur user=Utilisateur.isUtilisateur(nouveauIdentifiant);
            if(user==null){
                this.identifiant=nouveauIdentifiant;
                SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put("Identifiant", "nouveauIdentifiant");
                // updating row
                db.update("Utilisateur", values, "ID_User1 = '" + this.getId(), null);
                db.close();
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    /*
    modifie le mot de passe de l'utilisateur en vérifiant si tout s'est bien passé ou pas
    Si false est retourné alors soit l'ancien mot de passe était faux soit le nouveau a mal été entré
    Il faudra afficher un toast dans ce cas
    Retourne true si ça c'est bien passé
    L'objet et la bdd sont mise à jour
     */
    public boolean modifierMotDePasse(String nouveauMotDePasse, String nouveauMotDePasse1, String ancienMotDePasse) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String arg=Integer.toString(this.getId());
        Cursor cursor = db.rawQuery("SELECT Password FROM Utilisateurs WHERE ID_User=" + arg,null);
        cursor.moveToFirst();
        String ancienMotDePasse1 = cursor.getString(0);
        cursor.close();
        if ((ancienMotDePasse == ancienMotDePasse1) && (nouveauMotDePasse == nouveauMotDePasse1)) {
            this.motDePasse = nouveauMotDePasse;
            ContentValues values = new ContentValues();
            values.put("Password", nouveauMotDePasse);
            db.update("Utilisateur", values, "ID_User1 = '" + this.getId(), null);
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
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
     * @return liste des amis d un utilisateur en allant les chercher dans la bdd
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
        Cursor cursor = db.rawQuery("SELECT * FROM Utilisateurs WHERE ID_User!=9",null );//TODO Simon: commande pour recuperer toute la ligne des utilisateurs ayant envoye une demande d ami a l utilisateur dont l id est 9 par exemple(voir quand l'id est 9 dans la deuxième collone et avoir la mention 'E' comme état
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
        Cursor cursor = db.rawQuery("SELECT * FROM Utilisateurs WHERE ID_User!=9",null );//TODO Simon: commande pour recuperer toute la ligne des Poll cree par l utilisateur dont l id vaut 9 par exemple
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

    /*
    recuperer dans la base de donnnées tout les utilisateurs et les stockes dans une arraylist
     */
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
                userCount++;
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

    /*
    retourne le plus petit Id libre dans la bdd pour créer un nouveel utilisateur
     */
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
        db.close();
        return uIdMAX+1;
    }

    /*
    verifie que le format fournit pour l'adresse mail est valide
     */
    public boolean verifFormatMail(String mail){
        int size=mail.length();
        boolean found1=false;
        boolean found2=false;
        for(int i=0;i<size;i++){
            if(mail.charAt(i)=='@'){
                if(found1){
                    return false;
                }
                else{
                    found1=true;
                    i++;
                }
            }
            if(mail.charAt(i)=='.'){
                if (found1 && found2){
                    return false;
                }
                if(found1){
                    found2=true;
                    i++;
                }
            }

        }
        return(found1 && found2);
    }

}

