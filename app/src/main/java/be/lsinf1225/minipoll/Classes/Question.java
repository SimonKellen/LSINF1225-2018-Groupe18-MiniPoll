package be.lsinf1225.minipoll.Classes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

/**
 * Created by Simon Kellen on 03-05-18.
 */

public class Question {

    //Variables

    private final int id;
    private String enonce;
    private String[] reponse;
    private int[] idreponse;
    private int bonne_rep; //Prends la valeur (-1) si un sondage n'a pas de bonne réponse (ex. sondage pour accord)

    /**
     * Contient les instances déjà existantes des sondages pour choix afin d'éviter de créer deux instances identiques.
     */
    public static SparseArray<Question> quesSparseArray = new SparseArray<>();

    //Constructeur

    public Question(int id,String[] reponse, int bonne_rep){
        this.reponse = reponse;
        this.bonne_rep = bonne_rep;
        this.id=id;
        quesSparseArray.put(id,this);
    }

    public void addReponseInDb(Question question){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        int size=question.getReponse().length;
        int index=question.getLowestReponsesIdAvailable();
        int returnvalue[]= new int[size];
        String tab[] = question.getReponse();
        for(int i=0;i<size;i++){
            returnvalue[i]=index+i;
            ContentValues values = new ContentValues();
            values.put("ID_Reponse",index+i);
            values.put("Valeur",tab[i]);
            db.insert("Reponses",null,values);
        }
        db.close();
        question.idreponse=returnvalue;
    }

    public void addqrInDb(Question question){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        int index[] = question.idreponse;
        int size=index.length;
        for(int i=0;i<size;i++){
            ContentValues values = new ContentValues();
            values.put("ID_Question",question.getId());
            values.put("ID_Reponse",index[i]);
            if(question.bonne_rep==-1){
                values.put("Bonne_reponse","NULL");
            }
            if(question.bonne_rep==i){
                values.put("Bonne_reponse","V");
            }
            else{
                values.put("Bonne_reponse","F");
            }
            db.insert("QuestionReponse",null,values);
        }
        db.close();
    }

    //Méthodes

    public int getLowestQuestionIdAvailable(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(ID_Question) FROM Questions ",null );
        cursor.moveToFirst();
        int uIdMAX=0;
        while (!cursor.isAfterLast()) {
            uIdMAX = cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        return uIdMAX+1;
    }

    public int getLowestReponsesIdAvailable(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT MAX(ID_Reponse) FROM Reponses ",null );
        cursor.moveToFirst();
        int uIdMAX=0;
        while (!cursor.isAfterLast()) {
            uIdMAX = cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        return uIdMAX+1;
    }


    public int getId() {
        return id;
    }

    public int getBonne_rep() {
        return bonne_rep;
    }

    public String[] getReponse() {
        return reponse;
    }

    public void setBonne_rep(int bonne_rep) {
        this.bonne_rep = bonne_rep;
    }

    public void setReponse(String[] reponse) {
        this.reponse = reponse;
    }

    public int[] getIdreponse() {
        return idreponse;
    }

    public void setIdreponse(int[] idreponse) {
        this.idreponse = idreponse;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }
}
