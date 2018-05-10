package be.lsinf1225.minipoll.Classes;

import android.content.ContentValues;
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

    public Questionnaire(int idp, Utilisateur createur, String titre, SparseArray<Utilisateur> participants, SparseArray<Question> questions, String etat) {
        super.id=idp;
        super.createur = createur;
        super.titre = titre;
        super.etat=etat;
        super.type="Q";
        this.participants = participants;
        this.questions = questions;
        this.listeRep = null;
        quesSparseArray.put(idp,this);
    }

    //Les methodes

    /*
    ajoute une ligne dans la table utilisateur reponse lorsque celui ci à été invité à participer à un poll
     */
    public void addupInDb(Questionnaire questionnaire){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        int size=questionnaire.getParticipants().size();
        for(int i=0;i<size;i++){
            Utilisateur user = questionnaire.getParticipants().valueAt(i);
            ContentValues values = new ContentValues();
            values.put("ID_User",user.getId());
            values.put("ID_Poll",questionnaire.getId());
            values.put("A_repondu","F");
            db.insert("UtilisateurPoll",null,values);
        }
        db.close();
    }

    /*
      fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
    */

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
     * @return liste des participants a un questionnaire depuis la bdd
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
     * @return liste des questions d un questionnaire depuis la bdd
     */
    public SparseArray<Question> getListQuestion(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Utilisateurs WHERE ID_User!=9",null );//TODO Simon: commande pour recuperer toute la ligne des questions appartenant a un poll dont l'idp est 4 par exemple
        cursor.moveToFirst();
        SparseArray<Question> ques = new SparseArray<>();
        while (!cursor.isAfterLast()){
            int idq = cursor.getInt(0);
            int numordre = cursor.getInt(1);
            String format= cursor.getString(2);
            String enonce = cursor.getString(3);
            int nombreDeProp = cursor.getInt(5);
            Question q = Question.quesSparseArray.get(idq);
            if(q==null){
                q=new Question(idq,null,-2);
            }
            ques.put(idq,q);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        this.questions=ques;
        return ques;
    }

    /**
     *
     * @return liste des reponses a un questionnaire depuis la bdd
     */
    public SparseArray<Reponse_Utilisateur> getListRepUti(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Utilisateurs WHERE ID_User!=9",null );//TODO Simon: commande pour recuperer toutes les réponses (et donc leur valeur associées) de chaque utilisateur a chaque question du questionnaire dont l'id est 4 par exemple
        cursor.moveToFirst();
        SparseArray<Reponse_Utilisateur> ru = new SparseArray<>();
        while (!cursor.isAfterLast()){
            // TODO a creer en fonction de ce que renvoit la commande sql que simon fait
            //int idq = cursor.getInt(0);
            //int numordre = cursor.getInt(1);
            //String format= cursor.getString(2);
            //String enonce = cursor.getString(3);
            //int nombreDeProp = cursor.getInt(5);
            //Question q = Question.quesSparseArray.get(idq);
            //if(q==null){
              //  q=new Question(idq,null,-2);
            //}
            //ques.put(idq,q);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        this.listeRep=ru;
        return ru;
    }






}

