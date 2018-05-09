package be.lsinf1225.minipoll.Classes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.util.ArrayList;

/**
 * Created by marti on 02-05-18.
 */

public class Sondage_Pour_Accord extends Poll {

    //variables

    private SparseArray<Utilisateur> participants;
    private SparseArray<Reponse_Utilisateur> listeRep;
    private Question question;
    private int nombreRep;

    /**
     * Contient les instances déjà existantes des sondages pour accord afin d'éviter de créer deux instances identiques.
     */
    public static SparseArray<Sondage_Pour_Accord> spaSparseArray = new SparseArray<>();

    //constructeur

    public Sondage_Pour_Accord(int id,Utilisateur createur,String titre, SparseArray<Utilisateur> participants, Question question, int nombreRep){
        super.id=id;
        super.createur=createur;
        super.titre=titre;
        this.participants=participants;
        this.listeRep=null;
        this.question=question;
        this.nombreRep=nombreRep;
        spaSparseArray.put(id,this);
    }

    //getteur et setteur

    @Override
    public void setTitre(String titre) {
        super.setTitre(titre);
    }

    @Override
    public void setCreateur(Utilisateur createur) {
        super.setCreateur(createur);
    }

    @Override
    public String getTitre() {
        return super.getTitre();
    }

    @Override
    public Utilisateur getCreateur() {
        return super.getCreateur();
    }

    public SparseArray<Reponse_Utilisateur> getListeRep() {
        return listeRep;
    }

    public SparseArray<Utilisateur> getParticipants() {
        return participants;
    }

    public int getNombreRep() {
        return nombreRep;
    }

    public Question getQuestion() {
        return question;
    }

    public void setListeRep(SparseArray<Reponse_Utilisateur> listeRep) {
        this.listeRep = listeRep;
    }

    public void setNombreRep(int nombreRep) {
        this.nombreRep = nombreRep;
    }

    public void setParticipants(SparseArray<Utilisateur> participants) {
        this.participants = participants;
    }

    public void addupInDb(Sondage_Pour_Accord spa){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        int size=spa.getParticipants().size();
        for(int i=0;i<size;i++){
            Utilisateur user = spa.getParticipants().valueAt(i);
            ContentValues values = new ContentValues();
            values.put("ID_User",user.getId());
            values.put("ID_Poll",spa.getId());
            values.put("A_repondu","F");
            db.insert("UtilisateurPoll",null,values);
        }
        db.close();
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     *
     * @return liste des participants a un sondage pour accord
     */
    public SparseArray<Utilisateur> getListeParticipants(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Utilisateurs WHERE ID_User!=9",null );//TODO Simon: commande pour recuperer toute la ligne des utilisateurs participants au poll dont idp est 4 par exemple(idem que pour questionnaire)
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
     * @return question d un sondage pour accord depuis la bdd
     */
    public Question getQuestiondb(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Utilisateurs WHERE ID_User!=9",null );//TODO Simon: commande pour recuperer toute la ligne de la question d un sondage pour accord dont l id est 4 par exemple
        cursor.moveToFirst();
        Question q=null;
        while (!cursor.isAfterLast()){
            int idq = cursor.getInt(0);
            int numordre = cursor.getInt(1);
            String format= cursor.getString(2);
            String enonce = cursor.getString(3);
            int nombreDeProp = cursor.getInt(5);
            q = Question.quesSparseArray.get(idq);
            if(q==null){
                q=new Question(idq,null,-2);
            }

            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        this.question=q;
        return q;
    }


    /**
     *
     * @return liste des reponses a un sondage pour accord
     */
    public SparseArray<Reponse_Utilisateur> getListRepUti(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Utilisateurs WHERE ID_User!=9",null );//TODO Simon: commande pour recuperer toutes les réponses (et donc leur valeur associées) de chaque utilisateur a chaque question du sondage pour accord dont l'id est 4 par exemple
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
