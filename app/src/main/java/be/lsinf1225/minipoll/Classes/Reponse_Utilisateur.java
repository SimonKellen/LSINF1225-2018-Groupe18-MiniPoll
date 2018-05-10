package be.lsinf1225.minipoll.Classes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

/**
 * Created by Simon Kellen on 03-05-18.
 */

public class Reponse_Utilisateur {

    //Variables

    private final int id;
    private Utilisateur participants;
    private Poll poll;
    private int[][] tableauRep;

    /**
     * Contient les instances déjà existantes des reponse utilisateur  afin d'éviter de créer deux instances identiques.
     */
    public static SparseArray<Reponse_Utilisateur> ruSparseArray = new SparseArray<>();

    //Constructeur

    public Reponse_Utilisateur(int id,Utilisateur participants, Poll poll, int[][] tableauRep){
        this.participants = participants;
        this.id=id;
        this.poll = poll;
        this.tableauRep = tableauRep;
        ruSparseArray.put(id,this);
    }

    //Méthodes

    /*
    retourne le plus petit Id libre dans la bdd pour créer un nouvelle reponseUtilisateur
     */
    public int getLowestRUIdAvailable(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(ID_User) FROM UtilisateurPoll where A_repondu='V' ",null );
        cursor.moveToFirst();
        int uIdMAX=0;
        while (!cursor.isAfterLast()) {
            uIdMAX = cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        return uIdMAX+1;
    }

    /*
      fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
    */
    public void setParticipants(Utilisateur participants) {
        this.participants = participants;
    }

    public Utilisateur getParticipants() {
        return participants;
    }

    public int getId() {
        return id;
    }

    public int[][] getTableauRep() {
        return tableauRep;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public void setTableauRep(int[][] tableauRep) {
        this.tableauRep = tableauRep;
    }

}
