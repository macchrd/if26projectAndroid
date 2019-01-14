package fr.utt.if26.if26project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AfficheEtudiant extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    TextView tv_name;
    TextView tv_surname;
    //TextView tv_type;
    Spinner sp_niveau;
    Spinner sp_filiere;
    int row;
    BDPersistance persistance;
    Button bt_update;
    Button bt_delete;
    Button bt_addPhoto;
    ImageView imageView;
    Uri imageUri;
    String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affiche_etudiant);
        setTitle("Fiche de l'etudiant");

        bt_update = (Button) findViewById(R.id.ae_bt_update);
        bt_delete = (Button) findViewById(R.id.ae_bt_delete);
        bt_addPhoto = (Button) findViewById(R.id.ae_bt_addPhoto);
        tv_name = (TextView) findViewById(R.id.ae_et_name);
        tv_surname = (TextView) findViewById(R.id.ae_et_surname);
        imageView = (ImageView) findViewById(R.id.imageView);
        sp_niveau = (Spinner) findViewById(R.id.spinner4);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.niveau_etudiant, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sp_niveau.setAdapter(adapter);
        sp_filiere = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.filiere_etudiant, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_filiere.setAdapter(adapter2);

        persistance = new BDPersistance(this, "enseignants.db", null, 1);

        Intent intent = getIntent();
        Etudiant e = (Etudiant) intent.getSerializableExtra("etudiant");
        Etudiant etudiant = persistance.getEtudiant(Integer.toString(e.getId()));
        row = etudiant.getId();
        Log.i("sp_filiere.getCount()", "onCreate: "+sp_filiere.getCount());
        tv_surname.setText(etudiant.getNom());
        tv_name.setText(etudiant.getPrenom());
        for(int i = 0; i<sp_niveau.getCount();i++){
            if(sp_niveau.getItemAtPosition(i).toString().equals(etudiant.getNiveau())){
                sp_niveau.setSelection(i,true);
            }
        }
        for(int i = 0; i<sp_filiere.getCount();i++){
            Log.i("getItemAtPosition(i)", "onCreate: "+sp_filiere.getItemAtPosition(i).toString());
            if(sp_filiere.getItemAtPosition(i).toString().equals(etudiant.getFiliere())){
                sp_filiere.setSelection(i,true);
            }
        }
        Log.i("Affiche enseignant","///////////////////////////////////////"+etudiant.getPhoto());
        if (etudiant.getPhoto() != null){
            byte[] decodedString = Base64.decode(etudiant.getPhoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);
        }

        bt_update.setOnClickListener(new View.OnClickListener() {

            public void onClick(View gestionEnseignants) {
                Etudiant etudiant1 = new Etudiant(row,tv_surname.getText().toString(),tv_name.getText().toString(),sp_niveau.getSelectedItem().toString(),sp_filiere.getSelectedItem().toString());
                if(encodedImage != null){
                    Log.i("image","/////////////////////////////////////////////////////////////////"+encodedImage);
                    etudiant1.setPhoto(encodedImage);
                }
                persistance.updateEtudiant(etudiant1);
                AfficheEtudiant.this.finish();
            }
        } );

        bt_delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View gestionEnseignants) {
                Etudiant etudiant1 = new Etudiant(row,tv_surname.getText().toString(),tv_name.getText().toString(),sp_niveau.getSelectedItem().toString(),"");
                persistance.delEtudiant(etudiant1);
                AfficheEtudiant.this.finish();
            }
        } );

        bt_addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent){
        if(requestCode == PICK_IMAGE){
            if(resultCode == RESULT_OK){
                try {
                    imageUri = resultIntent.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imageView.setImageBitmap(selectedImage);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                    byte[] byteArrayImage = baos.toByteArray();
                    encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(AfficheEtudiant.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }

            }else {
                Toast.makeText(AfficheEtudiant.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
            }
        }
    }
}
