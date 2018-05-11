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

        final SparseArray<Utilisateur> existingUsers = Utilisateur.userSparseArray;
        int nbreUtilisateur = existingUsers.size();
        if(nbreUtilisateur == 1)
        {
            MiniPollApp.notifyLong(R.string.one_user);
        }
        else
        {
            final Utilisateur firstOne;
            if(Utilisateur.connectedUser.getId() == count)
            {
                count++;
            }
            firstOne = existingUsers.get(count);
            if(firstOne.getPhoto().equals("Image par defaut"))
            {
                profilPic.setImageResource(R.mipmap.default_picture);
            }
            else
                {
                Bitmap bitmap;
                Uri data = Uri.parse(firstOne.getPhoto());
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                    profilPic.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {

                    e.printStackTrace();
                }
            }
            text1.setText(firstOne.getNom() +" "+ firstOne.getPrenom());
            text2.setText(firstOne.getIdentifiant());
            text3.setText(firstOne.getMail());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Utilisateur.connectedUser.demande_ami(firstOne);
                }
            });

            findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(AjoutAmiActivity.this) {

                public void onSwipeRight()
                {
                    count--;
                    Utilisateur previous;
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
                        previous = existingUsers.get(count);
                        if (previous.getPhoto().equals("Image par defaut")) {
                            profilPic.setImageResource(R.mipmap.default_picture);
                        } else {
                            Bitmap bitmap;
                            Uri data = Uri.parse(previous.getPhoto());
                            try {
                                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                                profilPic.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {

                                e.printStackTrace();
                            }
                        }
                        text1.setText(previous.getNom() + " " + previous.getPrenom());
                        text2.setText(previous.getIdentifiant());
                        text3.setText(previous.getMail());
                    }

                }

                public void onSwipeLeft()
                {
                    count++;
                    Utilisateur next;
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
                        next = existingUsers.get(count);
                        if (next.getPhoto().equals("Image par defaut")) {
                            profilPic.setImageResource(R.mipmap.default_picture);
                        } else {
                            Bitmap bitmap;
                            Uri data = Uri.parse(next.getPhoto());
                            try {
                                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                                profilPic.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {

                                e.printStackTrace();
                            }
                        }
                        text1.setText(next.getNom() + " " + next.getPrenom());
                        text2.setText(next.getIdentifiant());
                        text3.setText(next.getMail());
                    }


                }

            });

        }


    }
}
