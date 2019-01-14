package fr.utt.if26.if26project;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RepartitionEtudiants extends AppCompatActivity {

    private TextView stats_enseignant;
    private TextView stats_etudiant;
    private TextView stats_module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistiques);
        setTitle("Statistques");

        BDPersistance persistance = new BDPersistance(this, "enseignants.db", null, 1);
        SQLiteDatabase db = persistance.getWritableDatabase();

        stats_enseignant = (TextView) findViewById(R.id.stats_enseignants);
        stats_module = (TextView) findViewById(R.id.stats_ues);
        stats_etudiant = (TextView) findViewById(R.id.stats_etudiants);

        stats_enseignant.setText("Il y a "+persistance.countEnseignant().get(0)+" enseignants dans le département dont "
                                + persistance.countEnseignant().get(1)+" professeurs, "
                                + persistance.countEnseignant().get(2)+" maitres de conférences et "
                                + persistance.countEnseignant().get(3)+" contractuels.");
        stats_module.setText("Il y a "+persistance.countModule().get(0)+" modules dans le département dont "
                                + persistance.countModule().get(1)+"  CS et "
                                + persistance.countModule().get(2)+" TM.");
        stats_etudiant.setText("Il y a "+persistance.countEtudiant().get(0)+" etudiants dans le département dont "
                                + persistance.countEtudiant().get(1)+" en filière MRI, "
                                + persistance.countEtudiant().get(2)+" en filière MSI, "
                                + persistance.countEtudiant().get(3)+" en filière MPL.");
    }
}
