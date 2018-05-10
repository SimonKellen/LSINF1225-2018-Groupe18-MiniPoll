package be.lsinf1225.minipoll.Activites;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.FileNotFoundException;

import be.lsinf1225.minipoll.Classes.Utilisateur;
import be.lsinf1225.minipoll.R;

public class ProfilActivity extends AppCompatActivity {
    private ImageView picture;
    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private EditText edit4;
    private ImageButton imButton1;
    private ImageButton imButton2;
    private ImageButton imButton3;
    private ImageButton imButton4;
    private ImageButton imButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);


        picture = (ImageView) findViewById(R.id.imageView);
        edit1 = (EditText) findViewById(R.id.editText1);
        edit2 = (EditText) findViewById(R.id.editText2);
        edit3 = (EditText) findViewById(R.id.editText3);
        edit4 = (EditText) findViewById(R.id.editText4);
        imButton5 = (ImageButton) findViewById(R.id.imageButton5);
        imButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imButton4 = (ImageButton) findViewById(R.id.imageButton4);

        Bitmap bitmap;
        Uri data = Uri.parse(Utilisateur.connectedUser.getPhoto());
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
            picture.setImageBitmap(bitmap);
        } catch (FileNotFoundException e)
        {

            e.printStackTrace();
        }
        edit1.setText(Utilisateur.connectedUser.getIdentifiant());
        edit1.setText(Utilisateur.connectedUser.getPrenom());
        edit1.setText(Utilisateur.connectedUser.getNom());
        edit1.setText(Utilisateur.connectedUser.getMail());


    }
}