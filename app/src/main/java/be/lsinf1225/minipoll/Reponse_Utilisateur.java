package be.lsinf1225.minipoll;

/**
 * Created by Simon Kellen on 03-05-18.
 */

public class Reponse_Utilisateur {

    //Variables

    public static int idReponse = 1;
    public int id;
    public Utilisateur participants;
    public Poll poll;
    public int[][] tableauRep;

    //Constructeur

    public Reponse_Utilisateur(Utilisateur participants, Poll poll, int[][] tableauRep){
        this.participants = participants;
        this.poll = poll;
        this.tableauRep = tableauRep;
        this.id = idReponse;
        idReponse++;
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

    public static int getIdReponse() {
        return idReponse;
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
