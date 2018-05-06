package be.lsinf1225.minipoll.Activites;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import be.lsinf1225.minipoll.R;

public class ConnexionActivity extends AppCompatActivity
{
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        button1 = (Button) findViewById(R.id.connexion1);
        button1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openMenuActivity();
            }
        });

        button2 = (Button) findViewById(R.id.connexion2);
        button2.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openCreationCompteActivity();
            }
        });
    }

    public void openMenuActivity(){
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

    public void openCreationCompteActivity(){
        Intent intent = new Intent(this, CreationCompteActivity.class);
        startActivity(intent);
    }

}
