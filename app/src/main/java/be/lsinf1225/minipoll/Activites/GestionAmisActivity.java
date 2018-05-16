package be.lsinf1225.minipoll.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import be.lsinf1225.minipoll.Classes.MiniPollApp;
import be.lsinf1225.minipoll.Classes.Utilisateur;
import be.lsinf1225.minipoll.R;

public class GestionAmisActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private boolean answer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_d_amis);

        button1 = (Button) findViewById(R.id.gestion_d_amis1);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openAjoutAmiActivity();
            }
        });

        button1 = (Button) findViewById(R.id.gestion_d_amis2);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openListeAmisActivity();
            }
        });


        SparseArray<Utilisateur> demandeAmis = Utilisateur.connectedUser.getDemandeAmisdb();


        if (demandeAmis.size() != 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            builder.setTitle("Demande d'ami");
            builder.setMessage("Vous avez une ou plusieurs demandes d'ami en attente. Voulez vous y répondre maintenant ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            answer = true;
                            openAddFriendActivity();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            answer = false;
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    public void openAddFriendActivity()
    {
        Intent intent = new Intent(this, AccepterDemande.class);
        startActivity(intent);
        finish();

    }

    public void openAjoutAmiActivity() {
        Intent intent = new Intent(this, AjoutAmiActivity.class);
        startActivity(intent);
    }

    public void openListeAmisActivity() {
        Intent intent = new Intent(this, ListeAmisActivity.class);
        startActivity(intent);
    }

    public void onBackPressed()
    {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
        finish();
    }
}

