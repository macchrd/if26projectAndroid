package fr.utt.if26.if26project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class AfficheModule extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    TextView tv_sigle;
    TextView tv_credit;
    //TextView tv_type;
    Spinner sp_parcours;
    Spinner sp_categorie;
    int row;
    BDPersistance persistance;
    Button bt_update;
    Button bt_delete;
    Button bt_addPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affiche_module);
        setTitle("Fiche du module");

        bt_update = (Button) findViewById(R.id.ae_bt_update);
        bt_delete = (Button) findViewById(R.id.ae_bt_delete);
        tv_sigle = (TextView) findViewById(R.id.am_et_sigle);
        tv_credit = (TextView) findViewById(R.id.am_et_credit);
        sp_parcours = (Spinner) findViewById(R.id.spinner3);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.parcours_module, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp_parcours.setAdapter(adapter);
        sp_categorie = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.categorie_module, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_categorie.setAdapter(adapter2);

        persistance = new BDPersistance(this, "enseignants.db", null, 1);

        Intent intent = getIntent();
        Module e = (Module) intent.getSerializableExtra("module");
        Module module = persistance.getModule(Integer.toString(e.getId()));
        row = module.getId();
        Log.i("sp_categorie.getCount()", "onCreate: "+sp_categorie.getCount());
        tv_sigle.setText(module.getSigle());
        tv_credit.setText(Integer.toString(module.getCredit()));
        for(int i = 0; i<sp_categorie.getCount();i++){
            if(sp_categorie.getItemAtPosition(i).toString().equals(module.getCategorie())){
                sp_categorie.setSelection(i,true);
            }
        }
        for(int i = 0; i<sp_parcours.getCount();i++){
            Log.i("getItemAtPosition(i)", "onCreate: "+sp_parcours.getItemAtPosition(i).toString());
            if(sp_parcours.getItemAtPosition(i).toString().equals(module.getParcours())){
                sp_parcours.setSelection(i,true);
            }
        }

        bt_update.setOnClickListener(new View.OnClickListener() {

            public void onClick(View gestionEnseignants) {
                Module module1 = new Module(row,tv_sigle.getText().toString(),sp_parcours.getSelectedItem().toString(),sp_categorie.getSelectedItem().toString(),Integer.valueOf(tv_credit.getText().toString()));
                persistance.updateModule(module1);
                AfficheModule.this.finish();
            }
        } );

        bt_delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View gestionEnseignants) {
                Module module1 = new Module(row,tv_sigle.getText().toString(),sp_parcours.getSelectedItem().toString(),sp_categorie.getSelectedItem().toString(),Integer.valueOf(tv_credit.getText().toString()));
                persistance.delModule(module1);
                AfficheModule.this.finish();
            }
        } );
    }
}