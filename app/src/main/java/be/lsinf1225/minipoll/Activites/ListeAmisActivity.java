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

import be.lsinf1225.minipoll.Classes.MiniPollApp;
import be.lsinf1225.minipoll.Classes.OnSwipeTouchListener;
import be.lsinf1225.minipoll.Classes.Utilisateur;
import be.lsinf1225.minipoll.R;

public class ListeAmisActivity extends AppCompatActivity {

    private ImageView profilPic;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private Button button1;
    private Button button2;
    private int countFriend = 0;
    private Utilisateur currentFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_d_amis);

        profilPic = (ImageView) findViewById(R.id.imageView3);
        text1 = (TextView) findViewById(R.id.textView15);
        text2 = (TextView) findViewById(R.id.textView16);
        text3 = (TextView) findViewById(R.id.textView4);
        button1 = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.button);

        final SparseArray<Utilisateur> friends = Utilisateur.connectedUser.getListeAmis();

        int nbreUtilisateur = friends.size();
        if (nbreUtilisateur == 1) {
            MiniPollApp.notifyLong(R.string.no_friend);
        } else {

            currentFriend = friends.get(countFriend);
            if (currentFriend.getPhoto().equals("Image par defaut")) {
                profilPic.setImageResource(R.mipmap.default_picture);
            } else {
                Bitmap bitmap;
                Uri data = Uri.parse(currentFriend.getPhoto());
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                    profilPic.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {

                    e.printStackTrace();
                }
            }
            text1.setText(currentFriend.getNom() + " " + currentFriend.getPrenom());
            text2.setText(currentFriend.getIdentifiant());
            text3.setText(currentFriend.getMail());

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilisateur.connectedUser.setBestFriend(currentFriend);
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilisateur.connectedUser.supprimer_amis(currentFriend);
                }
            });

            findViewById(android.R.id.content).setOnTouchListener(new OnSwipeTouchListener(ListeAmisActivity.this) {

                public void onSwipeRight() {
                    countFriend--;
                    if (countFriend < 0) {
                        countFriend++;
                    } else {
                        currentFriend = friends.get(countFriend);
                        if (currentFriend.getPhoto().equals("Image par defaut")) {
                            profilPic.setImageResource(R.mipmap.default_picture);
                        } else {
                            Bitmap bitmap;
                            Uri data = Uri.parse(currentFriend.getPhoto());
                            try {
                                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                                profilPic.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {

                                e.printStackTrace();
                            }
                        }
                        text1.setText(currentFriend.getNom() + " " + currentFriend.getPrenom());
                        text2.setText(currentFriend.getIdentifiant());
                        text3.setText(currentFriend.getMail());
                    }

                }

                public void onSwipeLeft() {
                    countFriend++;

                    if (countFriend > friends.size() - 1) {
                        countFriend--;
                    } else {
                        currentFriend = friends.get(countFriend);
                        if (currentFriend.getPhoto().equals("Image par defaut")) {
                            profilPic.setImageResource(R.mipmap.default_picture);
                        } else {
                            Bitmap bitmap;
                            Uri data = Uri.parse(currentFriend.getPhoto());
                            try {
                                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
                                profilPic.setImageBitmap(bitmap);
                            } catch (FileNotFoundException e) {

                                e.printStackTrace();
                            }
                        }
                        text1.setText(currentFriend.getNom() + " " + currentFriend.getPrenom());
                        text2.setText(currentFriend.getIdentifiant());
                        text3.setText(currentFriend.getMail());
                    }


                }

            });

        }
    }
}
