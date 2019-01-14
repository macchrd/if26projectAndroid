package fr.utt.if26.if26project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        Button gestionEnseignants = (Button) findViewById(R.id.gestionEnseignants);
        Button gestionUE = (Button) findViewById(R.id.gestionUE);
        Button gestionSemestre = (Button) findViewById(R.id.gestionSemestre);
        Button repartitionEtudiants = (Button) findViewById(R.id.repartitionEtudiants);

        BDPersistance persistance = new BDPersistance(this,"",null,0);
        //persistance.initData();
        gestionEnseignants.setOnClickListener(new View.OnClickListener() {

            public void onClick(View gestionEnseignants) {

                Intent intent1 = new Intent(MainActivity.this, GestionEnseignants.class);

                startActivity(intent1);
            }
        } );

        gestionUE.setOnClickListener(new View.OnClickListener() {

            public void onClick(View gestionUE) {

                Intent intent = new Intent(MainActivity.this, GestionModule.class);

                startActivity(intent);
            }
        } );

        repartitionEtudiants.setOnClickListener(new View.OnClickListener() {

            public void onClick(View repartitionEtudiants) {

                Intent intent1 = new Intent(MainActivity.this, RepartitionEtudiants.class);

                startActivity(intent1);
            }
        } );

        gestionSemestre.setOnClickListener(new View.OnClickListener() {

            public void onClick(View gestionSemestre) {

                Intent intent1 = new Intent(MainActivity.this, GestionEtudiants.class);

                startActivity(intent1);
            }
        } );
    }
}
