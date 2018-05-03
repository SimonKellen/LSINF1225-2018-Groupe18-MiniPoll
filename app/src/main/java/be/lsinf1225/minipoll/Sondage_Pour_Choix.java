package be.lsinf1225.minipoll;

import Poll;

/**
 * Created by Simon Kellen on 03-05-18.
 */

public class Sondage_Pour_Choix extends Poll {

    // Variables

    public Utilisateur participants;
    public Question question;
    public Reponse_Utilisateur listeRep;

    //Constructeur

    public Sondage_Pour_Choix(String titre, Utilisateur createur, Utilisateur participants, Question question){
        super.titre = titre;
        super.createur = createur;
        this.participants = participants;
        this.question = question;
        this.listeRep = null;
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
