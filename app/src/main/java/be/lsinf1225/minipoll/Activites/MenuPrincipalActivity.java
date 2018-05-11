package be.lsinf1225.minipoll.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import be.lsinf1225.minipoll.Classes.Utilisateur;
import be.lsinf1225.minipoll.R;


public class MenuPrincipalActivity extends AppCompatActivity
{


    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        button1 = (Button) findViewById(R.id.menu_principal6);
        button1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openAmiActivity();
            }
        });

        button2 = (Button) findViewById(R.id.menu_principal4);
        button2.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openCreationActivity();
            }
        });

        button3 = (Button) findViewById(R.id.menu_principal1);
        button3.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openMenuAccordActivity();
            }
        });

        button4 = (Button) findViewById(R.id.menu_principal3);
        button4.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openMenuChoixActivity();
            }
        });

        button5 = (Button) findViewById(R.id.menu_principal2);
        button5.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openMenuQuestionnaireActivity();
            }
        });

        button6 = (Button) findViewById(R.id.menu_principal5);
        button6.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openProfilActivity();
            }
        });

        SparseArray<Utilisateur> demandeAmis = Utilisateur.connectedUser.getDemandeAmisdb();
        for(int i=0; i<demandeAmis.size(); i++)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            final Utilisateur  newAmi = demandeAmis.get(i);
            builder.setTitle("Demande d'ami");
            builder.setMessage("Accepter la demande en ami de " + newAmi.toString()+" ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Utilisateur.connectedUser.accepter_demande_ami(newAmi);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Utilisateur.connectedUser.refuser_demande_ami(newAmi);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    public void openAmiActivity(){
        Intent intent = new Intent(this, GestionAmisActivity.class);
        startActivity(intent);
    }

    public void openCreationActivity(){
        Intent intent = new Intent(this, CreationSondageActivity.class);
        startActivity(intent);
    }

    public void openMenuAccordActivity(){
        Intent intent = new Intent(this, MenuAccordActivity.class);
        startActivity(intent);
    }

    public void openMenuChoixActivity(){
        Intent intent = new Intent(this, MenuChoixActivity.class);
        startActivity(intent);
    }

    public void openMenuQuestionnaireActivity(){
        Intent intent = new Intent(this, MenuQuestionnaireActivity.class);
        startActivity(intent);
    }

    public void openProfilActivity(){
        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
    }
}
