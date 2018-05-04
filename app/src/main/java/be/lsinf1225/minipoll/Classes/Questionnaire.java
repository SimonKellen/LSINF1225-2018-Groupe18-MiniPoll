package be.lsinf1225.minipoll.Classes;

import java.util.ArrayList;

/**
 * Created by marti on 02-05-18.
 */

public class Questionnaire extends Poll {

    //variables

    ArrayList<Utilisateur> participants;
    ArrayList<Question> questions;
    ArrayList<Reponse_Utilisateur> listeRep;

    //constructeur

    public Questionnaire(Utilisateur createur,String titre,ArrayList<Utilisateur> participants,ArrayList<Question> questions){
        super.createur=createur;
        super.titre=titre;
        this.participants=participants;
        this.questions=questions;
        this.listeRep=null;
    }

    //getteur et setteur


    public void setParticipants(ArrayList<Utilisateur> participants) {
        this.participants = participants;
    }

    public void setListeRep(ArrayList<Reponse_Utilisateur> listeRep) {
        this.listeRep = listeRep;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    @Override
    public void setCreateur(Utilisateur createur) {
        super.setCreateur(createur);
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public void setTitre(String titre) {
        super.setTitre(titre);
    }

    public ArrayList<Utilisateur> getParticipants() {
        return participants;
    }

    public ArrayList<Reponse_Utilisateur> getListeRep() {
        return listeRep;
    }

    @Override
    public Utilisateur getCreateur() {
        return super.getCreateur();
    }

    @Override
    public String getTitre() {
        return super.getTitre();
    }
}
