package be.lsinf1225.minipoll.Classes;

//import Poll;

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

    public Sondage_Pour_Choix(int id, String titre, Utilisateur createur, Utilisateur participants, Question question){
        super.id=id;
        super.titre = titre;
        super.createur = createur;
        this.participants = participants;
        this.question = question;
        this.listeRep = null;
        spcSparseArray.put(id,this);
    }

    //Méthodes


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


    }
