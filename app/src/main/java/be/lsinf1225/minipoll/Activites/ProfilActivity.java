package be.lsinf1225.minipoll.Activites;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

import be.lsinf1225.minipoll.Classes.MiniPollApp;
import be.lsinf1225.minipoll.Classes.Utilisateur;
import be.lsinf1225.minipoll.R;

public class ProfilActivity extends AppCompatActivity {
    private ImageView picture;
    private TextView edit1;
    private EditText edit2;
    private EditText edit3;
    private EditText edit4;
    private ImageButton imButton2;
    private ImageButton imButton3;
    private ImageButton imButton4;
    private ImageButton imButton5;
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);


        picture = (ImageView) findViewById(R.id.imageView);
        edit1 = (TextView) findViewById(R.id.textView9);
        edit2 = (EditText) findViewById(R.id.textView10);
        edit3 = (EditText) findViewById(R.id.textView11);
        edit4 = (EditText) findViewById(R.id.textView12);
        imButton5 = (ImageButton) findViewById(R.id.imageButton5);
        imButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imButton4 = (ImageButton) findViewById(R.id.imageButton4);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        if(Utilisateur.connectedUser.getPhoto().equals(R.string.default_picture))
        {
            picture.setImageResource(R.drawable.ic_launcher_foreground);
        }
        else {
            Bitmap bitmap;
            Uri data = Uri.parse(Utilisateur.connectedUser.getPhoto());
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                picture.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }
        }
        edit1.setText(Utilisateur.connectedUser.getIdentifiant());
        edit2.setText(Utilisateur.connectedUser.getPrenom());
        edit3.setText(Utilisateur.connectedUser.getNom());
        edit4.setText(Utilisateur.connectedUser.getMail());


        imButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String prenom = edit2.getText().toString();
                Utilisateur.connectedUser.setPrenom(prenom);
                edit2.setText(prenom);
                MiniPollApp.notifyShort(R.string.modif);
            }
        });

        imButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String nom = edit3.getText().toString();
                Utilisateur.connectedUser.setNom(nom);
                edit2.setText(nom);
                MiniPollApp.notifyShort(R.string.modif);
            }
        });

        imButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String mail = edit4.getText().toString();
                if(!Utilisateur.verifFormatMail(mail))
                {
                    MiniPollApp.notifyShort(R.string.wrong_mail_format);
                }
                else
                {
                    Utilisateur.connectedUser.setMail(mail);
                    edit4.setText(mail);
                    MiniPollApp.notifyShort(R.string.modif);

                }
            }
        });

        imButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startModifId();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                changeMdp();
            }
        });




    }
    public void startModifId()
    {
        Intent intent = new Intent(this,ModifIdActivity.class);
        startActivity(intent);
        finish();
    }
    public void changeMdp()
    {
        Intent intent = new Intent(this, ModifMdpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            String newPhoto = (targetUri.toString());
            Utilisateur.connectedUser.setPhoto(newPhoto);
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                picture.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }
        }


    }

}