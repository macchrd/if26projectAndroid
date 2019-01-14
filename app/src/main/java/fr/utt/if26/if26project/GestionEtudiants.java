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

public class GestionEtudiants extends AppCompatActivity {

    EditText nom;
    EditText prenom;
    //EditText type;
    EditText rowid;
    Spinner sp_niveau;
    Spinner sp_filiere;
    ListView maListe;
    public AdaptateurEtudiant adapteur;
    BDPersistance persistance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_etudiant);
        setTitle("Gestion des étudiants");

        Button bt_surname = (Button) findViewById(R.id.bt_surname);
        Button bt_add = (Button) findViewById(R.id.bt_add);
        sp_niveau = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.niveau_etudiant, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp_niveau.setAdapter(adapter);
        sp_filiere = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.filiere_etudiant, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_filiere.setAdapter(adapter2);
        nom = findViewById(R.id.et_surname);
        prenom = findViewById(R.id.et_name);
        //type = findViewById(R.id.et_type);
        //type.setEnabled(false);

        ListeEtudiants listeEtudiants =  new ListeEtudiants();
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
                addEtudiant();
            }
        } );

        maListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(GestionEtudiants.this, AfficheEtudiant.class);
                Etudiant e = (Etudiant) maListe.getItemAtPosition(position);
                Log.i("///////////////////////","////////////////////////////////////"+e);
                intent1.putExtra("etudiant",e);
                startActivity(intent1);
            }
        });

    }

    private void filterSearch(){

        String n = nom.getText().toString();
        String p = prenom.getText().toString();
        //String t = type.getText().toString();
        String t = sp_niveau.getSelectedItem().toString();
        String f = sp_filiere.getSelectedItem().toString();
        if(t.equals("Tous niveaux")){
            t="";
        }
        if(f.equals("Toutes filières")){
            f="";
        }
        String[] key = new String[]{n,p,t,f};

        ListView maListe = (ListView) findViewById(R.id.listview);
        BDPersistance persistance = new BDPersistance(this, "enseignants.db", null, 1);
        //Enseignant enseignant = persistance.getEnseignant("Thierry");
        //ArrayList<Enseignant> enseignants = new ArrayList<>();
        //enseignants.add(enseignant);
        ArrayList<Etudiant> etudiants = persistance.filterSearchEtudiant(key);
        AdaptateurEtudiant adapteur = new AdaptateurEtudiant(this, R.layout.etudiant, etudiants);
        maListe.setAdapter(adapteur);
    }

    private void addEtudiant(){

        nom = findViewById(R.id.et_surname);
        prenom = findViewById(R.id.et_name);
        //type = findViewById(R.id.et_type);
        String n = nom.getText().toString();
        String p = prenom.getText().toString();
        //String t = type.getText().toString();
        String t = sp_niveau.getSelectedItem().toString();
        String f = sp_filiere.getSelectedItem().toString();
        if(f.equals("Toutes filières")){
            f = "";
        };
        if(!n.equals("") && !p.equals("")){
            if(!t.equals("Tous niveaux")){
                nom.getText().clear();
                prenom.getText().clear();
                sp_filiere.setSelection(0,true);
                sp_niveau.setSelection(0,true);
                //type.getText().clear();
                ListView maListe = (ListView) findViewById(R.id.listview);

                Etudiant e = new Etudiant(-1,n,p,t,f);

                BDPersistance persistance = new BDPersistance(this, "enseignants.db", null, 1);
                persistance.addEtudiant(e);

                ArrayList<Etudiant> etudiants = persistance.getAllEtudiants();
                AdaptateurEtudiant adapteur = new AdaptateurEtudiant(this, R.layout.etudiant, etudiants);
                maListe.setAdapter(adapteur);
            }else{
                Toast.makeText(GestionEtudiants.this, "Vous devez choisir un niveau",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(GestionEtudiants.this, "Vous devez rentrer un nom un prénom, et choisir un niveau",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onStart(){
        super.onStart();
        ArrayList<Etudiant> etudiants = persistance.getAllEtudiants();
        adapteur = new AdaptateurEtudiant(this, R.layout.etudiant, etudiants);
        maListe.setAdapter(adapteur);
    }
}
