package be.lsinf1225.minipoll.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.lsinf1225.minipoll.Classes.Utilisateur;
import be.lsinf1225.minipoll.R;

public class AccepterDemande extends AppCompatActivity {

    private TextView text;
    private Button button1;
    private Button button2;
    private int count = 0;
    private SparseArray<Utilisateur> demandeAmis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accepter_demande);

        text = (TextView) findViewById(R.id.textView31);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        demandeAmis = Utilisateur.connectedUser.getDemandeAmisdb();

        text.setText("Vous avez reçu une demande d'ami de la part de "+demandeAmis.get(count).toString() + ".");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refuser(demandeAmis.get(count));

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accepter(demandeAmis.get(count));

            }
        });


    }

    public void accepter(Utilisateur demandeAmi){
        Utilisateur.connectedUser.accepter_demande_ami(demandeAmi);
        count++;
        if(count>demandeAmis.size()-1)
        {
            Intent intent = new Intent(this, GestionAmisActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            text.setText(R.string.friend_request2+demandeAmis.get(count).toString());
        }

    }

    public void refuser(Utilisateur demandeAmi){
        Utilisateur.connectedUser.refuser_demande_ami(demandeAmi);
        count++;
        if(count>demandeAmis.size()-1)
        {
            Intent intent = new Intent(this, GestionAmisActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            text.setText(R.string.friend_request2+demandeAmis.get(count).toString());
        }
    }
}
