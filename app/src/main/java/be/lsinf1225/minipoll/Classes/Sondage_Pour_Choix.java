package be.lsinf1225.minipoll.Classes;

//import Poll;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

/**
 * Created by Simon Kellen on 03-05-18.
 */

public class Sondage_Pour_Choix extends Poll {


    // Variables

    private Utilisateur participants;
    private Question question;
    private Reponse_Utilisateur listeRep;

    /**
     * Contient les instances déjà existantes des sondages pour choix afin d'éviter de créer deux instances identiques.
     */
    public static SparseArray<Sondage_Pour_Choix> spcSparseArray = new SparseArray<>();

    //Constructeur

    public Sondage_Pour_Choix(int id, String titre, Utilisateur createur, Utilisateur participants, Question question) {
        super.id = id;
        super.titre = titre;
        super.createur = createur;
        this.participants = participants;
        this.question = question;
        this.listeRep = null;
        spcSparseArray.put(id, this);
    }

    //Méthodes

    /*
      fonction uniquement à utiliser sur les objets pour les manipuler. Pas de lien avec la bdd
    */
    public Question getQuestion() {
        return question;
    }

    public Reponse_Utilisateur getListeRep() {
        return listeRep;
    }

    public Utilisateur getParticipants() {
        return participants;
    }

    public void setListeRep(Reponse_Utilisateur listeRep) {
        this.listeRep = listeRep;
    }

    public void setParticipants(Utilisateur participants) {
        this.participants = participants;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }



    /*
    ajoute une ligne dans la table utilisateur reponse lorsque celui ci à été invité à participer à un poll
     */
    public static void addupInDb(Sondage_Pour_Choix spc){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
            Utilisateur user = spc.getParticipants();
            ContentValues values = new ContentValues();
            values.put("ID_User",user.getId());
            values.put("ID_Poll",spc.getId());
            values.put("A_repondu","F");
            db.insert("UtilisateurPoll",null,values);
        db.close();
    }

    /**
     * @return participant a un sondage pour choix en allant les chercher dans la bdd
     */
    public Utilisateur getParticipantdb() {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT \"ID_User\", \"Prenom\", \"Nom\", \"Password\", \"BestFriend\", \"Pic\", \"Mail\" FROM Utilisateurs UT INNER JOIN UtilisateurPoll PO on UT.ID_User = PO.ID_User WHERE PO.ID_Poll = 4", null);//TODO Martin remplacer le 4 (Simon: commande pour recuperer toute la ligne de l utilisateur participant au poll dont idp est 4 par exemple(idem que pour questionnaire))
        cursor.moveToFirst();
        Utilisateur part = null;
        while (!cursor.isAfterLast()) {
            int idu = cursor.getInt(0);
            String prenom = cursor.getString(1);
            String nom = cursor.getString(2);
            String password = cursor.getString(3);
            String photo = cursor.getString(5);
            String mail = cursor.getString(6);
            String identifiant = cursor.getString(7);
            part = Utilisateur.userSparseArray.get(idu);
            if (part == null) {
                part = new Utilisateur(idu, password, nom, prenom, identifiant, photo, mail);
            }
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        this.participants = part;
        return part;
    }

    /**
     * @return question d un sondage pour choix depuis la bdd
     */
    public Question getQuestiondb() {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT \"ID_Question\", \"Numero_Ordre\", \"Format_Reponse\", \"Enonce\", \"ID_Poll\", \"Nbr_Choix\" from Questions Q INNER JOIN Poll P on Q.ID_Poll = P.ID_Poll WHERE (P.Type = \"S\") AND P.ID_Poll = 3", null);//TODO Martin remplacer le 4 (Simon: commande pour recuperer toute la ligne de la question d un sondage pour choix dont l id est 4 par exemple)
        cursor.moveToFirst();
        Question q = null;
        while (!cursor.isAfterLast()) {
            int idq = cursor.getInt(0);
            int numordre = cursor.getInt(1);
            String format = cursor.getString(2);
            String enonce = cursor.getString(3);
            int nombreDeProp = cursor.getInt(5);
            q = Question.quesSparseArray.get(idq);
            if (q == null) {
                q = new Question(idq, null, -2);
            }

            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        this.question = q;
        return q;
    }

    /**
     *
     * @return la reponse a un sondage pour accord
     */
    public Reponse_Utilisateur getRepUti(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT URep.ID_User, URep.ID_Reponse, URep.Score FROM UtilisateurRéponse URep, Poll, Questions, QuestionReponse WHERE Poll.Type = 'S' AND Poll.ID_Poll = 2 AND Poll.ID_Poll = Questions.ID_Poll AND Questions.ID_Question = QuestionReponse.ID_Question AND QuestionReponse.ID_Reponse = URep.ID_Reponse",null );//TODO Martin remplacer le 2 (Simon: commande pour recuperer la réponse (et donc la valeur associée) de chaque utilisateur a chaque question du sondage pour choix dont l'id est 4 par exemple)
        cursor.moveToFirst();
        Reponse_Utilisateur ru=null;
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
