package be.lsinf1225.minipoll;

/**
 * Created by Simon Kellen on 03-05-18.
 */

public class Question {

    //Variables

    public static int idQuestion=1;
    public int id;
    public String[] reponse;
    public int bonne_rep; //Prends la valeur (-1) si un sondage n'a pas de bonne réponse (ex. sondage pour accord)

    //Constructeur

    public Question(String[] reponse, int bonne_rep){
        this.reponse = reponse;
        this.bonne_rep = bonne_rep;
        this.id=idQuestion;
        idQuestion++;
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
