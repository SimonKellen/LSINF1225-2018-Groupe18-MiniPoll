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
    private int count = 0;
    private Utilisateur current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_ami);

        profilPic = (ImageView) findViewById(R.id.imageView3);
        text1 = (TextView) findViewById(R.id.textView15);
        text2 = (TextView) findViewById(R.id.textView16);
        text3 = (TextView) findViewById(R.id.textView4);
        button = (Button) findViewById(R.id.button2);

        final SparseArray<Utilisateur> existingUsers = Utilisateur.userSparseArray;
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
            current = existingUsers.get(count);
            if(current.getPhoto().equals("Image par defaut"))
            {
                profilPic.setImageResource(R.mipmap.default_picture);
            }
            else
                {
                Bitmap bitmap;
                Uri data = Uri.parse(current.getPhoto());
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                    profilPic.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {

                    e.printStackTrace();
                }
            }
            text1.setText(current.getNom() +" "+ current.getPrenom());
            text2.setText(current.getIdentifiant());
            text3.setText(current.getMail());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilisateur.connectedUser.demande_ami(current);
                    MiniPollApp.notifyShort(R.string.friend_request);
                }
            });

            findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(AjoutAmiActivity.this) {

                public void onSwipeRight()
                {
                    count--;
                    if(Utilisateur.connectedUser.getId() == count)
                    {
                        count--;
                    }
                    if(count<0)
                    {
                        if(Utilisateur.connectedUser.getId() == 0)
                        {
                            count =count+2;
                        }
                        else {
                            count++;
                        }
                    }
                    else {
                        current = existingUsers.get(count);
                        if (current.getPhoto().equals("Image par defaut")) {
                            profilPic.setImageResource(R.mipmap.default_picture);
                        } else {
                            Bitmap bitmap;
                            Uri data = Uri.parse(current.getPhoto());
                            try {
                                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                                profilPic.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {

                                e.printStackTrace();
                            }
                        }
                        text1.setText(current.getNom() + " " + current.getPrenom());
                        text2.setText(current.getIdentifiant());
                        text3.setText(current.getMail());
                    }

                }

                public void onSwipeLeft()
                {
                    count++;
                    if(Utilisateur.connectedUser.getId() == count)
                    {
                        count++;
                    }
                    if(count>existingUsers.size()-1)
                    {
                        if(Utilisateur.connectedUser.getId() == existingUsers.size()-1)
                        {
                            count = count - 2;
                        }
                        else {
                            count--;
                        }
                    }
                    else {
                        current = existingUsers.get(count);
                        if (current.getPhoto().equals("Image par defaut")) {
                            profilPic.setImageResource(R.mipmap.default_picture);
                        } else {
                            Bitmap bitmap;
                            Uri data = Uri.parse(current.getPhoto());
                            try {
                                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                                profilPic.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {

                                e.printStackTrace();
                            }
                        }
                        text1.setText(current.getNom() + " " + current.getPrenom());
                        text2.setText(current.getIdentifiant());
                        text3.setText(current.getMail());
                    }


                }

            });

        }


    }
}
