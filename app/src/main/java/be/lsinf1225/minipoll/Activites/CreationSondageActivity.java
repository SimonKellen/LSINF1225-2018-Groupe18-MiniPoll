package be.lsinf1225.minipoll.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import be.lsinf1225.minipoll.R;

public class CreationSondageActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_de_creation);

        button1 = (Button) findViewById(R.id.menu_de_creation1);
        button2 = (Button) findViewById(R.id.menu_de_creation2);
        button3 = (Button) findViewById(R.id.menu_de_creation3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}