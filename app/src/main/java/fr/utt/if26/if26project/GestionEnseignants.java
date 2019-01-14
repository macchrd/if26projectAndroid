package fr.utt.if26.if26project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class GestionEnseignants extends AppCompatActivity {

    EditText nom;
    EditText prenom;
    EditText type;
    EditText rowid;
    Spinner sp;
    ListView maListe;
    public AdaptateurEnseignant adapteur;
    BDPersistance persistance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_enseignants);
        setTitle("Gestion des enseignants");

        Button bt_surname = (Button) findViewById(R.id.bt_surname);
        Button bt_add = (Button) findViewById(R.id.bt_add);
        sp = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_enseignants, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp.setAdapter(adapter);
        nom = findViewById(R.id.et_surname);
        prenom = findViewById(R.id.et_name);
        //type = findViewById(R.id.et_type);
        //type.setEnabled(false);

        ListeEnseignants listeEnseignants =  new ListeEnseignants();
        maListe = (ListView) findViewById(R.id.listview);
        persistance = new BDPersistance(this, "enseignants.db", null, 1);
        SQLiteDatabase db = persistance.getWritableDatabase();
        //persistance.onUpgrade(db,1,1000);
        //persistance.initData();
        //Enseignant enseignant = persistance.getEnseignant("Thierry");
        //ArrayList<Enseignant> enseignants = new ArrayList<>();
        //enseignants.add(enseignant);



        bt_surname.setOnClickListener(new View.OnClickListener() {

            public void onClick(View gestionEnseignants) {
                filterSearch();
            }
        } );

        bt_add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View gestionEnseignants) {
                addEnseignant();
            }
        } );

        maListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(GestionEnseignants.this, AfficheEnseignant.class);
                Enseignant e = (Enseignant)maListe.getItemAtPosition(position);
                Log.i("///////////////////////","////////////////////////////////////"+e);
                intent1.putExtra("enseignant",e);
                startActivity(intent1);
            }
        });

        }

        private void filterSearch(){

            String n = nom.getText().toString();
            String p = prenom.getText().toString();
            //String t = type.getText().toString();
            String t = sp.getSelectedItem().toString();
            if(t.equals("Tous types")){
                t="";
            }
            String[] key = new String[]{n,p,t};

            ListView maListe = (ListView) findViewById(R.id.listview);
            BDPersistance persistance = new BDPersistance(this, "enseignants.db", null, 1);
            //Enseignant enseignant = persistance.getEnseignant("Thierry");
            //ArrayList<Enseignant> enseignants = new ArrayList<>();
            //enseignants.add(enseignant);
            ArrayList<Enseignant> enseignants = persistance.filterSearchEnseignant(key);
            AdaptateurEnseignant adapteur = new AdaptateurEnseignant(this, R.layout.enseignant, enseignants);
            maListe.setAdapter(adapteur);
        }

        private void addEnseignant(){

            nom = findViewById(R.id.et_surname);
            prenom = findViewById(R.id.et_name);
            //type = findViewById(R.id.et_type);
            String n = nom.getText().toString();
            String p = prenom.getText().toString();
            //String t = type.getText().toString();
            String t = sp.getSelectedItem().toString();
            Log.i("///////////////////////",Boolean.toString(!t.equals("Tout Types")));
            if(!n.equals("") && !p.equals("")){
                if(!t.equals("Tous types")){
                    nom.getText().clear();
                    prenom.getText().clear();
                    //type.getText().clear();
                    ListView maListe = (ListView) findViewById(R.id.listview);

                    Enseignant e = new Enseignant(-1,n,p,t);

                    BDPersistance persistance = new BDPersistance(this, "enseignants.db", null, 1);
                    persistance.addEnseignant(e);

                    ArrayList<Enseignant> enseignants = persistance.getAllEnseignants();
                    AdaptateurEnseignant adapteur = new AdaptateurEnseignant(this, R.layout.enseignant, enseignants);
                    maListe.setAdapter(adapteur);
                }else{
                    Toast.makeText(GestionEnseignants.this, "Vous devez choisir un type",Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(GestionEnseignants.this, "Vous devez rentrer un nom un prénom, et choisir un type",Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onStart(){
            super.onStart();
            ArrayList<Enseignant> enseignants = persistance.getAllEnseignants();
            adapteur = new AdaptateurEnseignant(this, R.layout.enseignant, enseignants);
            maListe.setAdapter(adapteur);
        }
}
