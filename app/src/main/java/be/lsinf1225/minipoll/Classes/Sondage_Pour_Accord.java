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

    public Sondage_Pour_Accord(int id,Utilisateur createur,String titre, SparseArray<Utilisateur> participants, Question question, int nombreRep,String etat){
        super.id=id;
        super.etat=etat;
        super.createur=createur;
        super.titre=titre;
        this.participants=participants;
        this.listeRep=null;
        this.question=question;
        this.nombreRep=nombreRep;
        spaSparseArray.put(id,this);
    }

    //getteur et setteur

    /*
      fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
    */
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

    public void setQuestion(Question question) {
        this.question = question;
    }

    /*
    ajoute une ligne dans la table utilisateur reponse lorsque celui ci à été invité à participer à un poll
     */
    public static void addupInDb(Sondage_Pour_Accord spa){
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



    /**
     *
     * @return liste des participants a un sondage pour accord en allant la chercher dans la bdd
     */
    public SparseArray<Utilisateur> getListeParticipants(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String arg = Integer.toString(this.getId());
        Cursor cursor = db.rawQuery("SELECT \"ID_User\", \"Prenom\", \"Nom\", \"Password\", \"Pic\", \"Mail\", \"Identifiant\" FROM Utilisateurs UT INNER JOIN UtilisateurPoll PO on UT.ID_User = PO.ID_User WHERE PO.ID_Poll =" + arg,null );
        SparseArray<Utilisateur> part = new SparseArray<>();
        while (!cursor.isAfterLast()){
            int idu = cursor.getInt(0);
            String prenom = cursor.getString(1);
            String nom = cursor.getString(2);
            String password = cursor.getString(3);
            String photo = cursor.getString(4);
            String mail = cursor.getString(5);
            String identifiant = cursor.getString(6);
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
        String arg = Integer.toString(this.getId());
        Cursor cursor = db.rawQuery("SELECT \"ID_Question\", \"Numero_Ordre\", \"Format_Reponse\", \"Enonce\", \"ID_Poll\", \"Nbr_Choix\" from Questions Q INNER JOIN Poll P on Q.ID_Poll = P.ID_Poll WHERE (P.Type = \"A\") AND P.ID_Poll =" + arg,null );
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
     * @return liste des reponses a un sondage pour accord depuis la bdd
     */
    public SparseArray<Reponse_Utilisateur> getListRepUti(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String arg = Integer.toString(this.getId());
        Cursor cursor = db.rawQuery("SELECT URep.ID_User, URep.ID_Reponse, URep.Score FROM UtilisateurRéponse URep, Poll, Questions, QuestionReponse WHERE Poll.Type = 'A' AND Poll.ID_Poll =" + arg + " AND Poll.ID_Poll = Questions.ID_Poll AND Questions.ID_Question = QuestionReponse.ID_Question AND QuestionReponse.ID_Reponse = URep.ID_Reponse",null );
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
