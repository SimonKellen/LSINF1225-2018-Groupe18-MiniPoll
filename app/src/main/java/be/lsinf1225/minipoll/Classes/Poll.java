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

    //getteur et setteur

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

    public void addLineInDb(Poll poll){
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

    public int getLowestPollIdAvailable(){
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
