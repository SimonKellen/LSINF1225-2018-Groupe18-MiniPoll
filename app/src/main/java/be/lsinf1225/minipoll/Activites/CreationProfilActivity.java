package be.lsinf1225.minipoll.Activites;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import be.lsinf1225.minipoll.Classes.MySQLiteHelper;
import be.lsinf1225.minipoll.R;

public class CreationProfilActivity extends AppCompatActivity {

    private Button button1;
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_profil);

        Bundle b = getIntent().getExtras();
        String username = b.getString("username");
        String password = b.getString("password");
        String nom = edit1.getText().toString();
        String prenom = edit2.getText().toString();
        String mail = edit3.getText().toString();


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
        U
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_User", "countUser+1");
        values.put
        // updating row
        db.update("Ami", values, null, null);
        db.close();
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

}
