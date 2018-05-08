package be.lsinf1225.minipoll.Classes;

import android.util.SparseArray;

/**
 * Created by Simon Kellen on 03-05-18.
 */

public class Reponse_Utilisateur {

    //Variables

    private final int id;
    private Utilisateur participants;
    private Poll poll;
    private int[][] tableauRep;

    /**
     * Contient les instances déjà existantes des reponse utilisateur  afin d'éviter de créer deux instances identiques.
     */
    public static SparseArray<Reponse_Utilisateur> ruSparseArray = new SparseArray<>();

    //Constructeur

    public Reponse_Utilisateur(int id,Utilisateur participants, Poll poll, int[][] tableauRep){
        this.participants = participants;
        this.id=id;
        this.poll = poll;
        this.tableauRep = tableauRep;
        ruSparseArray.put(id,this);
    }

    //Méthodes


    public void setParticipants(Utilisateur participants) {
        this.participants = participants;
    }

    public Utilisateur getParticipants() {
        return participants;
    }

    public int getId() {
        return id;
    }

    public int[][] getTableauRep() {
        return tableauRep;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public void setTableauRep(int[][] tableauRep) {
        this.tableauRep = tableauRep;
    }

}
