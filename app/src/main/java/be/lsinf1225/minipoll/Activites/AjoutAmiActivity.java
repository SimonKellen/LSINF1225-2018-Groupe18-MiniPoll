package be.lsinf1225.minipoll.Activites;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import be.lsinf1225.minipoll.Classes.MiniPollApp;
import be.lsinf1225.minipoll.Classes.OnSwipeTouchListener;
import be.lsinf1225.minipoll.Classes.Utilisateur;
import be.lsinf1225.minipoll.R;

public class AjoutAmiActivity extends AppCompatActivity {

    private ImageView profilPic;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private Button button;
    private static int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_ami);

        profilPic = (ImageView) findViewById(R.id.imageView3);
        text1 = (TextView) findViewById(R.id.textView15);
        text2 = (TextView) findViewById(R.id.textView16);
        text3 = (TextView) findViewById(R.id.textView4);
        button = (Button) findViewById(R.id.button2);

        SparseArray<Utilisateur> existingUsers = Utilisateur.userSparseArray;
        int nbreUtilisateur = existingUsers.size();
        if(nbreUtilisateur == 1)
        {
            MiniPollApp.notifyLong(R.string.one_user);
        }
        else
        {
            if(Utilisateur.connectedUser.getId() == count)
            {
                count++;
            }
            Utilisateur currentOne = existingUsers.get(count);
            if(currentOne.getPhoto().equals("Image par defaut"))
            {
                profilPic.setImageResource(R.mipmap.default_picture);
            }
            else
                {
                Bitmap bitmap;
                Uri data = Uri.parse(Utilisateur.connectedUser.getPhoto());
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                    profilPic.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {

                    e.printStackTrace();
                }
            }
            text1.setText(currentOne.getNom() +" "+ currentOne.getPrenom());
            text2.setText(currentOne.getIdentifiant());
            text3.setText(currentOne.getMail());
        }


        findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(AjoutAmiActivity.this) {
           
            public void onSwipeRight() 
            {
                MiniPollApp.notifyShort(R.string.swipe_right);
            }

            public void onSwipeLeft() 
            {
               MiniPollApp.notifyShort(R.string.swipe_left);
               count++;


            }
            
        });

    }
}
