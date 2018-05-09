package be.lsinf1225.minipoll.Activites;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import be.lsinf1225.minipoll.Classes.MiniPollApp;
import be.lsinf1225.minipoll.Classes.Utilisateur;
import be.lsinf1225.minipoll.R;

public class ConnexionActivity extends AppCompatActivity
{
    private Button button1;
    private Button button2;
    private EditText edit1;
    private EditText edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        edit1 =  (EditText) findViewById(R.id.editText1);
        edit2 =  (EditText) findViewById(R.id.editText2);

        button1 = (Button) findViewById(R.id.connexion1);
        button2 = (Button) findViewById(R.id.connexion2);

        //ArrayList<Utilisateur> users = Utilisateur.getUtilisateurs();

        button1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                connexionCheck();
            }
        });

        button2.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openCreationCompteActivity();
            }
        });



    }

    public void connexionCheck()
    {
        String identifiant = edit1.getText().toString();
        String motdepasse = edit2.getText().toString();
        if((identifiant.length() == 0) || (motdepasse.length() == 0))
        {
            MiniPollApp.notifyShort(R.string.incomplete_field);
        }
        else
        {
            MiniPollApp.notifyShort(R.string.test);

            Utilisateur personne = Utilisateur.isUtilisateur(identifiant);
            if (personne != null)
            {
                if (personne.login(motdepasse))
                {

                    Intent intent = new Intent(this, MenuPrincipalActivity.class);
                    startActivity(intent);
                }
                else
                {
                    MiniPollApp.notifyShort(R.string.incorrect_password);
                }
            }
            else
            {
                MiniPollApp.notifyShort(R.string.incorrect_login);
            }
        }

    }



    public void openCreationCompteActivity(){
        Intent intent = new Intent(this, CreationCompteActivity.class);
        startActivity(intent);
    }

}
