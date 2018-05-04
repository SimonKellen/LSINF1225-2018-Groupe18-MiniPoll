package be.lsinf1225.minipoll.Classes;

import be.lsinf1225.minipoll.Classes.Utilisateur;

/**
 * Created by marti on 02-05-18.
 */

public abstract class Poll {

    //variables

    public Utilisateur createur;
    public String titre;

    //getteur et setteur


    public Utilisateur getCreateur() {
        return this.createur;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setCreateur(Utilisateur createur) {
        this.createur = createur;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
