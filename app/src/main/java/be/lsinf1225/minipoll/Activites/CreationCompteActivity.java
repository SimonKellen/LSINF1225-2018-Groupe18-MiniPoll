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

public class CreationCompteActivity extends AppCompatActivity {

    private Button button1;
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_compte);

        button1 = (Button) findViewById(R.id.creation_compte1);
        edit1 = (EditText) findViewById(R.id.textView2);
        edit2 = (EditText) findViewById(R.id.editText3);
        edit3 = (EditText) findViewById(R.id.editText4);

        button1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openCreationProfilActivity();
            }
        });

    }

    public void openCreationProfilActivity()
    {
        String username = edit1.getText().toString();
        String password = edit2.getText().toString();
        String confPassword = edit3.getText().toString();

        if((username.length() == 0) || (password.length() == 0) || (confPassword.length() == 0))
        {
            MiniPollApp.notifyShort(R.string.incomplete_field);
        }
        else if(Utilisateur.isUtilisateur(username) != null)
        {
            MiniPollApp.notifyShort(R.string.unavailable_id);
        }
        else if(!(password.equals(confPassword)))
        {
            MiniPollApp.notifyShort(R.string.diff_password);
        }
        else
        {

            Intent intent = new Intent(this, CreationProfilActivity.class);
            Bundle b = new Bundle();
            b.putString("username",username);
            b.putString("password",password);
            intent.putExtras(b);
            startActivity(intent);
        }

    }

}
