package be.lsinf1225.minipoll.Classes;

import android.util.SparseArray;

/**
 * Created by Simon Kellen on 03-05-18.
 */

public class Question {

    //Variables

    private final int id;
    private String[] reponse;
    private int bonne_rep; //Prends la valeur (-1) si un sondage n'a pas de bonne réponse (ex. sondage pour accord)

    /**
     * Contient les instances déjà existantes des sondages pour choix afin d'éviter de créer deux instances identiques.
     */
    public static SparseArray<Question> quesSparseArray = new SparseArray<>();

    //Constructeur

    public Question(int id,String[] reponse, int bonne_rep){
        this.reponse = reponse;
        this.bonne_rep = bonne_rep;
        this.id=id;
        quesSparseArray.put(id,this);
    }

    //Méthodes


    public int getBonne_rep() {
        return bonne_rep;
    }

    public String[] getReponse() {
        return reponse;
    }

    public void setBonne_rep(int bonne_rep) {
        this.bonne_rep = bonne_rep;
    }

    public void setReponse(String[] reponse) {
        this.reponse = reponse;
    }
}
