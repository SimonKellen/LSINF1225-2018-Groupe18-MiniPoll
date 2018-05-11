package be.lsinf1225.minipoll.Activites;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

import be.lsinf1225.minipoll.Classes.MiniPollApp;
import be.lsinf1225.minipoll.Classes.MySQLiteHelper;
import be.lsinf1225.minipoll.Classes.Utilisateur;
import be.lsinf1225.minipoll.R;

public class CreationProfilActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private ImageView profilPicture;
    private TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_profil);

        Bundle b = getIntent().getExtras();
        final String identifiant = b.getString("username");
        final String password = b.getString("password");


        button1 = (Button) findViewById(R.id.creation_profil1);
        button2 = (Button) findViewById(R.id.creation_profil2);
        button3 = (Button) findViewById(R.id.creation_profil3);
        profilPicture = (ImageView)findViewById(R.id.imageView);
        edit1 = (EditText) findViewById(R.id.EditText1);
        edit2 = (EditText) findViewById(R.id.EditText2);
        edit3 = (EditText) findViewById(R.id.EditText3);
        text1 = (TextView) findViewById(R.id.textView7) ;
        profilPicture.setImageResource(R.drawable.ic_launcher_foreground);
        text1.setText(R.string.default_picture);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setPhotoToDefault();
            }
        });

        button3.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                openMenuActivity(password,identifiant);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            text1.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                profilPicture.setImageBitmap(bitmap);
            } catch (FileNotFoundException e)
            {

                e.printStackTrace();
            }
        }
    }


    public void setPhotoToDefault()
    {
        text1.setText(R.string.default_picture);
        profilPicture.setImageResource(R.mipmap.default_picture);
    }

    public void openMenuActivity(String password, String username)
    {
        String nom = edit1.getText().toString();
        String prenom = edit2.getText().toString();
        String mail = edit3.getText().toString();
        String photo = text1.getText().toString();

        if((nom.length()==0) || (prenom.length()==0))
        {
            MiniPollApp.notifyShort(R.string.incomplete_field);
        }
        else if(!Utilisateur.verifFormatMail(mail))
        {
            MiniPollApp.notifyShort(R.string.wrong_mail_format);
        }
        else
        {
            Utilisateur.connectedUser = new Utilisateur(Utilisateur.userSparseArray.size(), password, nom, prenom, username, photo, mail,Utilisateur.userSparseArray.size());
            Utilisateur.connectedUser.addUtilisateurInDb();

            Intent intent = new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
