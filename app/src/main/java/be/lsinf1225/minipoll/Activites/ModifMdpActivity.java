package be.lsinf1225.minipoll.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import be.lsinf1225.minipoll.Classes.MiniPollApp;
import be.lsinf1225.minipoll.Classes.Utilisateur;
import be.lsinf1225.minipoll.R;

public class ModifMdpActivity extends AppCompatActivity {


    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_mdp);

        edit1 = (EditText) findViewById(R.id.editText1);
        edit2 = (EditText) findViewById(R.id.editText2);
        edit3 = (EditText) findViewById(R.id.editText3);

        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mdpCheck();
            }
        });


    }

    public void mdpCheck()
    {
        String currentMdp = edit1.getText().toString();
        String newMdp = edit2.getText().toString();
        String confNewMdp = edit3.getText().toString();

        if(!currentMdp.equals(Utilisateur.connectedUser.getMotDePasse()))
        {
            MiniPollApp.notifyShort(R.string.incorrect_current_password);
        }
        else if(!newMdp.equals(confNewMdp))
        {
            MiniPollApp.notifyShort(R.string.diff_password);
        }
        else
        {
            Utilisateur.connectedUser.setMotDePasse(newMdp);
            finishActivity(2);
        }
    }
}

