package be.lsinf1225.minipoll.Classes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.util.ArrayList;

/**
 * Created by marti on 02-05-18.
 */

public class Questionnaire extends Poll {

    //variables

    private SparseArray<Utilisateur> participants;
    private SparseArray<Question> questions;
    private SparseArray<Reponse_Utilisateur> listeRep;

    /**
     * Contient les instances déjà existantes des sondages pour choix afin d'éviter de créer deux instances identiques.
     */
    public static SparseArray<Questionnaire> quesSparseArray = new SparseArray<>();


    //constructeur

    public Questionnaire(int idp, Utilisateur createur, String titre, SparseArray<Utilisateur> participants, SparseArray<Question> questions) {
        super.id=idp;
        super.createur = createur;
        super.titre = titre;
        this.participants = participants;
        this.questions = questions;
        this.listeRep = null;
        quesSparseArray.put(idp,this);
    }

    //getteur et setteur


    public void setParticipants(SparseArray<Utilisateur> participants) {
        this.participants = participants;
    }

    public void setListeRep(SparseArray<Reponse_Utilisateur> listeRep) {
        this.listeRep = listeRep;
    }

    public SparseArray<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(SparseArray<Question> questions) {
        this.questions = questions;
    }


    public SparseArray<Utilisateur> getParticipants() {
        return participants;
    }

    public SparseArray<Reponse_Utilisateur> getListeRep() {
        return listeRep;
    }

    /**
     *
     * @return liste des participants a un questionnaire
     */
    public SparseArray<Utilisateur> getListeParticipants(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Utilisateurs WHERE ID_User!=9",null );//TODO Simon: commande pour recuperer toute la ligne des utilisateurs participants au poll dont idp est 4 par exemple
        cursor.moveToFirst();
        SparseArray<Utilisateur> part = new SparseArray<>();
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
            part.put(idu,user);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        this.participants=part;
        return part;
    }

    /**
     *
     * @return liste des amis d un utilisateur
     */
    //public SparseArray<Utilisateur> getListQuestion(){
      //  SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        //Cursor cursor = db.rawQuery("SELECT * FROM Utilisateurs WHERE ID_User!=9",null );//TODO Simon: commande pour recuperer toute la ligne des questions appartenant a un poll dont l'idp est 4 par exemple
        //cursor.moveToFirst();
        //SparseArray<Question> ques = new SparseArray<>();
        //while (!cursor.isAfterLast()){
          //  int idq = cursor.getInt(0);
           // int numordre = cursor.getInt(1);
            //String format= cursor.getString(2);
            //String enonce = cursor.getString(3);
            //int nombreDeProp = cursor.getInt(5);
            //Question q = Question.quesSparseArray.get(idq);
            //if(q==null){
              //  q=new Question(idq,null,-2);
            //}
            //users.put(idu,user);
            //cursor.moveToNext();
        //}
        //cursor.close();
        //db.close();
        //this.amis=users;
        //return users;
    //}
}

