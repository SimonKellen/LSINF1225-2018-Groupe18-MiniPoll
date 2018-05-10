package be.lsinf1225.minipoll.Classes;

//import be.lsinf1225.minipoll.Classes.Utilisateur;

//Created by marti on 02-05-18.


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class Poll {

    //variables
    //protected pour que les sous classes y ai acces
    protected int id;
    protected Utilisateur createur;
    protected String titre;
    protected String etat;
    protected String type;

    //Methodes

    /*
      fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
    */
    public Utilisateur getCreateur() {
        return this.createur;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setCreateur(Utilisateur createur) {
        this.createur = createur;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public String getEtat() {
        return etat;
    }

    public String getType() {
        return type;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*
    ajoute un poll dans la base de données
     */
    public static void addPollInDb(Poll poll){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_Poll",poll.getId());
        values.put("ID_User",poll.getCreateur().getId());
        values.put("Etat",poll.getEtat());
        values.put("Titre",poll.getTitre());
        values.put("Type",poll.getType());
        db.insert("Poll",null,values);
        db.close();
    }

    /*
    empêche les utilisateurs d'envoyer d'autres votes
     */
    public void stopPoll(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("Etat", "F");
        db.update("Poll", values, "ID_Poll = '" + this.getId(), null);
        db.close();
    }
    /*
    supprime un poll
     */
    public void deletePoll(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String idp = Integer.toString(this.getId());
        db.delete("Poll", "Id_Poll=" + idp , null);
    }



    /*
    ajoute une question dans la bdd
     */
    public static void addQuestionsInDb(Poll poll,int nombredeProp[],String format[],Question questions[]){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        int size = questions.length;

        for(int i=0;i<size;i++){
            ContentValues values = new ContentValues();
            values.put("ID_Question",questions[i].getId());
            values.put("Numero_Ordre",i+1);
            values.put("Format_Reponse",format[i]);
            values.put("Enonce",questions[i].getEnonce());
            values.put("ID_Poll",poll.getId());
            values.put("Nombres_de_Propositions",nombredeProp[i]);
            db.insert("Questions",null,values);
        }
        db.close();
    }



    /*
    retourne le plus petit Id libre dans la bdd pour créer un nouveau poll
     */
    public static int getLowestPollIdAvailable(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(ID_Poll) FROM Poll ",null );
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
