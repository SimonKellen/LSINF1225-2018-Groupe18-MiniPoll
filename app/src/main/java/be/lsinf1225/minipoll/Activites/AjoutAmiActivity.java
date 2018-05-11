package be.lsinf1225.minipoll.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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

        imageView.setOnTouchListener(new OnSwipeTouchListener(AjoutAmiActivity.this) {
           
            public void onSwipeRight() 
            {   
                
            }
            public void onSwipeLeft() 
            {
               
            }
            
        });

    }
}
