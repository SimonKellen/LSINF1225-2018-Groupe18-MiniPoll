package be.lsinf1225.minipoll.Classes;

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
}

