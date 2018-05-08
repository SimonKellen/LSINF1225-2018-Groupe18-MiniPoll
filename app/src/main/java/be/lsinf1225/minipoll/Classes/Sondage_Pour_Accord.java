package be.lsinf1225.minipoll.Classes;

import android.util.SparseArray;

import java.util.ArrayList;

/**
 * Created by marti on 02-05-18.
 */

public class Sondage_Pour_Accord extends Poll {

    //variables

    private ArrayList<Utilisateur> participants;
    private ArrayList<Reponse_Utilisateur> listeRep;
    private Question question;
    private int nombreRep; //a verifier si c est bien ca pcq je suis pas sur d avoir compris

    /**
     * Contient les instances déjà existantes des sondages pour accord afin d'éviter de créer deux instances identiques.
     */
    public static SparseArray<Sondage_Pour_Accord> spaSparseArray = new SparseArray<>();

    //constructeur

    public Sondage_Pour_Accord(int id,Utilisateur createur,String titre, ArrayList<Utilisateur> participants, Question question, int nombreRep){
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
