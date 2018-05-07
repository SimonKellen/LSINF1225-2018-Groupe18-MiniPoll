package be.lsinf1225.minipoll.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import be.lsinf1225.minipoll.R;

public class CreationProfilActivity extends AppCompatActivity {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_profil);

        button1 = (Button) findViewById(R.id.creation_profil3);
        button1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                openMenuActivity();
            }
        });

    }

    public void openMenuActivity(){
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

}
