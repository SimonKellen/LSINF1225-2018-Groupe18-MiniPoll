package be.lsinf1225.minipoll.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import be.lsinf1225.minipoll.Classes.MiniPollApp;
import be.lsinf1225.minipoll.Classes.Utilisateur;
import be.lsinf1225.minipoll.R;

public class ModifIdActivity extends AppCompatActivity {

    private EditText edit1;
    private EditText edit2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_id);

        edit1 = (EditText) findViewById(R.id.editText1);
        edit2 = (EditText) findViewById(R.id.editText2);

        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                idChange();
            }
        });


    }

    public void idChange()
    {
        String newId = edit1.getText().toString();
        String mdp = edit2.getText().toString();

        if((newId.length()==0) || (mdp.length()==0))
        {
            MiniPollApp.notifyShort(R.string.incomplete_field);
        }
        else if(Utilisateur.isUtilisateur(newId) != null)
        {
            MiniPollApp.notifyShort(R.string.unavailable_id);
        }
        else if(!mdp.equals(Utilisateur.connectedUser.getMotDePasse()))
        {
            MiniPollApp.notifyShort(R.string.incorrect_password);
        }
        else
        {
            Intent intent = new Intent(this,ProfilActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this,ProfilActivity.class);
        startActivity(intent);
        finish();
    }
}