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

public class GestionModule extends AppCompatActivity {

    EditText sigle;
    EditText credit;
    //EditText type;
    EditText rowid;
    Spinner sp_categorie;
    Spinner sp_parcours;
    ListView maListe;
    public AdaptateurModule adapteur;
    BDPersistance persistance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gestion_module);
        setTitle("Gestion des modules");

        Button bt_surname = (Button) findViewById(R.id.bt_surname);
        Button bt_add = (Button) findViewById(R.id.bt_add);
        sp_categorie = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorie_module, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp_categorie.setAdapter(adapter);
        sp_parcours = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.parcours_module, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_parcours.setAdapter(adapter2);
        sigle = findViewById(R.id.et_sigle);
        credit = findViewById(R.id.et_credit);
        //type = findViewById(R.id.et_type);
        //type.setEnabled(false);

        ListeModule listeModule =  new ListeModule();
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
                addModule();
            }
        } );

        maListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(GestionModule.this, AfficheModule.class);
                Module e = (Module) maListe.getItemAtPosition(position);
                Log.i("///////////////////////","////////////////////////////////////"+e);
                intent1.putExtra("module",e);
                startActivity(intent1);
            }
        });

    }

    private void filterSearch(){

        String sig = sigle.getText().toString();
        String cred = credit.getText().toString();
        //String t = type.getText().toString();
        String cat = sp_categorie.getSelectedItem().toString();
        String parc = sp_parcours.getSelectedItem().toString();
        if(parc.equals("Tous parcours")){
            parc="";
        }
        if(cat.equals("Toutes catégories")){
            cat="";
        }
        String[] key = new String[]{sig,parc,cat,cred};

        ListView maListe = (ListView) findViewById(R.id.listview);
        BDPersistance persistance = new BDPersistance(this, "enseignants.db", null, 1);
        //Enseignant enseignant = persistance.getEnseignant("Thierry");
        //ArrayList<Enseignant> enseignants = new ArrayList<>();
        //enseignants.add(enseignant);
        ArrayList<Module> modules = persistance.filterSearchModule(key);
        AdaptateurModule adapteur = new AdaptateurModule(this, R.layout.module, modules);
        maListe.setAdapter(adapteur);
    }

    private void addModule(){

        String sig = sigle.getText().toString();
        String cred = credit.getText().toString();
        //String t = type.getText().toString();
        String cat = sp_categorie.getSelectedItem().toString();
        String parc = sp_parcours.getSelectedItem().toString();
        Log.i("Catégorie",cat);
        Log.i("Parcours",parc);

        if(!sig.equals("") && !cred.equals("")){
            if(!cat.equals("Toutes catégories") && !parc.equals("Tous parcours")){
                sigle.getText().clear();
                credit.getText().clear();
                sp_parcours.setSelection(0,true);
                sp_categorie.setSelection(0,true);
                //type.getText().clear();
                ListView maListe = (ListView) findViewById(R.id.listview);

                Module e = new Module(-1,sig,parc,cat,Integer.valueOf(cred));

                BDPersistance persistance = new BDPersistance(this, "enseignants.db", null, 1);
                persistance.addModule(e);

                ArrayList<Module> modules = persistance.getAllModules();
                AdaptateurModule adapteur = new AdaptateurModule(this, R.layout.module, modules);
                maListe.setAdapter(adapteur);
            }else{
                Toast.makeText(GestionModule.this, "Vous devez choisir un parcours et une catégorie",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(GestionModule.this, "Vous devez rentrer un sigle, un nombre de crédit, et choisir un parcours et une catégorie",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onStart(){
        super.onStart();
        ArrayList<Module> modules = persistance.getAllModules();
        adapteur = new AdaptateurModule(this, R.layout.module, modules);
        maListe.setAdapter(adapteur);
    }
}
