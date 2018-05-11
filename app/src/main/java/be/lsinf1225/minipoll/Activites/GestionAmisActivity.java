package be.lsinf1225.minipoll.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import be.lsinf1225.minipoll.R;

public class GestionAmisActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_d_amis);

        button1 = (Button) findViewById(R.id.gestion_d_amis1);
        button1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openAjoutAmiActivity();
            }
        });

        button1 = (Button) findViewById(R.id.gestion_d_amis2);
        button1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openListeAmisActivity();
            }
        });
    }

    public void openAjoutAmiActivity()
    {
        Intent intent = new Intent(this, AjoutAmiActivity.class);
        startActivity(intent);
    }

    public void openListeAmisActivity(){
        Intent intent = new Intent(this, ListeAmisActivity.class);
        startActivity(intent);
    }
}
