package be.lsinf1225.minipoll.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import be.lsinf1225.minipoll.R;


public class MenuPrincipalActivity extends AppCompatActivity {

    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

    public void openAmiActivity(){
        Intent intent = new Intent(this, GestionAmisActivity.class);
        startActivity(intent);
    }
}
