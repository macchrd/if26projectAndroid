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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AfficheEnseignant extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    TextView tv_name;
    TextView tv_surname;
    TextView tv_type;
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
        setContentView(R.layout.affiche_enseignant);
        setTitle("Fiche de l'enseignant");

        bt_update = (Button) findViewById(R.id.ae_bt_update);
        bt_delete = (Button) findViewById(R.id.ae_bt_delete);
        bt_addPhoto = (Button) findViewById(R.id.ae_bt_addPhoto);
        tv_name = (TextView) findViewById(R.id.ae_et_name);
        tv_surname = (TextView) findViewById(R.id.ae_et_surname);
        tv_type = (TextView) findViewById(R.id.ae_et_type);
        imageView = (ImageView) findViewById(R.id.imageView);

        persistance = new BDPersistance(this, "enseignants.db", null, 1);

        Intent intent = getIntent();
        Enseignant e = (Enseignant)intent.getSerializableExtra("enseignant");
        Enseignant enseignant = persistance.getEnseignant(Integer.toString(e.getId()));
        row = enseignant.getId();
        Log.i("test", "onCreate: "+enseignant);
        tv_surname.setText(enseignant.getNom());
        tv_name.setText(enseignant.getPrenom());
        tv_type.setText(enseignant.getType());
        Log.i("Affiche enseignant","///////////////////////////////////////"+enseignant.getPhoto());
        if (enseignant.getPhoto() != null){
            byte[] decodedString = Base64.decode(enseignant.getPhoto(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);
        }

        bt_update.setOnClickListener(new View.OnClickListener() {

            public void onClick(View gestionEnseignants) {
                Enseignant enseignant = new Enseignant(row,tv_surname.getText().toString(),tv_name.getText().toString(),tv_type.getText().toString());
                if(encodedImage != null){
                    Log.i("image","/////////////////////////////////////////////////////////////////"+encodedImage);
                    enseignant.setPhoto(encodedImage);
                }
                persistance.updateEnseignant(enseignant);
                AfficheEnseignant.this.finish();
            }
        } );

        bt_delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View gestionEnseignants) {
                Enseignant enseignant = new Enseignant(row,tv_surname.getText().toString(),tv_name.getText().toString(),tv_type.getText().toString());
                persistance.delEnseignant(enseignant);
                AfficheEnseignant.this.finish();
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
                    Toast.makeText(AfficheEnseignant.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }

            }else {
                Toast.makeText(AfficheEnseignant.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
            }
        }
    }
}
