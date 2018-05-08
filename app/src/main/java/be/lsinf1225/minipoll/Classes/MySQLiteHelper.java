package be.lsinf1225.minipoll.Classes;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Classe utilitaire qui va gérer la connexion, la création et la mise à jour de la base de données.
 * <p>
 * Cette classe va s'occuper de gérer la base de données. Elle s'occupera d'en créer une nouvelle
 * lors du premier lancement de l'application. Ensuite, en cas d'évolution de version de la base de
 * données (par exemple lors d'une amélioration de votre application), elle mettra à jour celle-ci
 * de manière adéquate.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    /**
     * Nom du fichier sql contenant les instructions de création de la base de données. Le fichier
     * doit être placé dans le dossier assets/
     */
    private static final String DATABASE_SQL_FILENAME = "database.sql";
    /**
     * Nom du fichier de la base de données.
     */
    private static final String DATABASE_NAME = "minipoll_database.sqlite";

    /**
     * Version de la base de données (à incrémenter en cas de modification de celle-ci afin que la
     * méthode onUpgrade soit appelée).
     *
     * @note Le numéro de version doit changer de manière monotone.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Instance de notre classe afin de pouvoir y accéder facilement depuis n'importe quel objet.
     */
    private static MySQLiteHelper instance;

    /**
     * Constructeur. Instancie l'utilitaire de gestion de la base de données.
     *
     * @param context Contexte de l'application.
     */
    private MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        instance = this;
    }

    /**
     * Fournit une instance de notre MySQLiteHelper.
     *
     * @return MySQLiteHelper
     */
    public static MySQLiteHelper get() {
        if (instance == null) {
            return new MySQLiteHelper(MiniPollApp.getContext());
        }
        return instance;
    }

    /**
     * Méthode d'initialisation appelée lors de la création de la base de données.
     *
     * @param db Base de données à initialiser
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        initDatabase(db);
    }

    /**
     * Méthode de mise à jour lors du changement de version de la base de données.
     *
     * @param db         Base de données à mettre à jour.
     * @param oldVersion Numéro de l'ancienne version.
     * @param newVersion Numéro de la nouvelle version.
     *
     * @pre La base de données est dans la version oldVersion.
     * @post La base de données a été mise à jour vers la version newVersion.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
         * @note : Ici on se contente juste de supprimer toutes les données et de les re-créer par
         * après. Dans une vraie application en production (par ex. sur le Play Store), il faudra
         * faire en sorte que les données enregistrées par l'utilisateur ne soient pas complètement
         * effacées lorsqu'on veut mettre à jour la structure de la base de données.
         */
        deleteDatabase(db);
        onCreate(db);
    }

    /**
     * Crée les tables de la base de données et les remplit.
     *
     * @param db Base de données à initialiser.
     *
     * @note À l'avenir on peut imaginer aller chercher les requêtes à effectuer dans un fichier
     * local (dans le dossier assets) ou sur internet (sur un server distant), au lieu de les
     * encoder en dur ici. (En fait c’est une mauvaise pratique de les encoder en dur comme on a
     * fait ici, mais on a voulu simplifier le code pour des raisons didactiques.) Vous trouverez en
     * commentaires dans cette méthode le code permettant de charger la base de données depuis un
     * fichier SQL placé dans le dossier assets/.
     * @post Les tables nécessaires à l'application sont créées et les données initiales y sont
     * enregistrées.
     */
    private void initDatabase(SQLiteDatabase db) {
        try {
            // Ouverture du fichier sql.
            Scanner scan = new Scanner(MiniPollApp.getContext().getAssets().open(DATABASE_SQL_FILENAME));
            scan.useDelimiter(Pattern.compile(";"));
            while (scan.hasNext()) {
                String sqlQuery = scan.next();
                /*
                 * @note : Pour des raisons de facilité, on ne prend en charge ici que les fichiers
                 * contenant une instruction par ligne. Si des instructions SQL se trouvent sur deux
                 * lignes, cela produira des erreurs (car l'instruction sera coupée)
                 */
                if (!sqlQuery.trim().isEmpty() && !sqlQuery.trim().startsWith("--")) {
                    Log.d("MySQL query", sqlQuery);
                    db.execSQL(sqlQuery);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erreur de lecture du fichier " + DATABASE_SQL_FILENAME + " : " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur SQL lors de la création de la base de données." +
                    "Vérifiez que chaque instruction SQL est au plus sur une ligne." +
                    "L'erreur est : " + e.getMessage(), e);
        }
    }

    /**
     * Supprime toutes les tables dans la base de données.
     *
     * @param db Base de données.
     *
     * @post Les tables de la base de données passées en argument sont effacées.
     */
    private void deleteDatabase(SQLiteDatabase db) {
        Cursor c = db.query("sqlite_master", new String[]{"name"}, "type = 'table' AND name NOT LIKE '%sqlite_%'", null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            db.execSQL("DROP TABLE IF EXISTS " + c.getString(0));
            c.moveToNext();
        }
    }

}
