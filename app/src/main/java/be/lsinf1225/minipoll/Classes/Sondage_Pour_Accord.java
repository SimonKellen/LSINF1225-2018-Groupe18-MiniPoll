package be.lsinf1225.minipoll.Classes;

import java.util.ArrayList;

/**
 * Created by marti on 02-05-18.
 */

public class Sondage_Pour_Accord extends Poll {

    //variables

    public ArrayList<Utilisateur> participants;
    public ArrayList<Reponse_Utilisateur> listeRep;
    public Question question;
    public int nombreRep; //a verifier si c est bien ca pcq je suis pas sur d avoir compris

    //constructeur

    public Sondage_Pour_Accord(Utilisateur createur,String titre, ArrayList<Utilisateur> participants, Question question, int nombreRep){
        super.createur=createur;
        super.titre=titre;
        this.participants=participants;
        this.listeRep=null;
        this.question=question;
        this.nombreRep=nombreRep;
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

    public ArrayList<Reponse_Utilisateur> getListeRep() {
        return listeRep;
    }

    public ArrayList<Utilisateur> getParticipants() {
        return participants;
    }

    public int getNombreRep() {
        return nombreRep;
    }

    public Question getQuestion() {
        return question;
    }

    public void setListeRep(ArrayList<Reponse_Utilisateur> listeRep) {
        this.listeRep = listeRep;
    }

    public void setNombreRep(int nombreRep) {
        this.nombreRep = nombreRep;
    }

    public void setParticipants(ArrayList<Utilisateur> participants) {
        this.participants = participants;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }


    //


}
