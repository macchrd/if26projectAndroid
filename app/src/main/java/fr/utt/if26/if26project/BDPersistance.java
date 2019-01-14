package fr.utt.if26.if26project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class BDPersistance extends SQLiteOpenHelper implements PersistanceInterface {

    public static final int DATABASE_VERSION = 1004;
    public static final String DATABASE_NAME = "enseignant.db"; // nom du fichier pour la base
    private static final String TABLE_ENSEIGNANT = "enseignant"; // nom de la table
    private static final String TABLE_ETUDIANT = "etudiant";
    private static final String TABLE_MODULE = "module";

    private static final String ATTRIBUT_NOM = "nom"; // liste des attributs
    private static final String ATTRIBUT_PRENOM = "prenom";
    private static final String ATTRIBUT_TYPE = "type";
    private static final String ATTRIBUT_PHOTO = "photo";
    private static final String ATTRIBUT_FILIERE = "filiere";
    private static final String ATTRIBUT_NIVEAU = "niveau";
    private  static final String ATTRIBUT_SIGLE = "sigle";
    private  static final String ATTRIBUT_PARCOURS = "parcours";
    private  static final String ATTRIBUT_CATEGORIE = "categorie";
    private  static final String ATTRIBUT_CREDIT = "credit";


    final String table_enseignant_create =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ENSEIGNANT + "(" +
                    ATTRIBUT_NOM + " TEXT," +
                    ATTRIBUT_PRENOM + " TEXT, " +
                    ATTRIBUT_TYPE + " TEXT, " +
                    ATTRIBUT_PHOTO+ " TEXT DEFAULT NULL "+
                    ")";

    final String table_etudiant_create =
            "CREATE TABLE " + TABLE_ETUDIANT + "(" +
                    ATTRIBUT_NOM + " TEXT," +
                    ATTRIBUT_PRENOM + " TEXT, " +
                    ATTRIBUT_NIVEAU + " TEXT, " +
                    ATTRIBUT_FILIERE + " TEXT, " +
                    ATTRIBUT_PHOTO+ " TEXT DEFAULT NULL "+
                    ")";

    final String table_module_create =
            "CREATE TABLE IF NOT EXISTS " + TABLE_MODULE + "(" +
                    ATTRIBUT_SIGLE + " TEXT," +
                    ATTRIBUT_PARCOURS + " TEXT, " +
                    ATTRIBUT_CATEGORIE + " TEXT, " +
                    ATTRIBUT_CREDIT+ " INTEGER "+
                    ")";


    public BDPersistance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void addEnseignant(Enseignant m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ATTRIBUT_NOM, m.getNom());
        values.put(ATTRIBUT_PRENOM, m.getPrenom());
        values.put(ATTRIBUT_TYPE, m.getType());

        db.insert(TABLE_ENSEIGNANT, null, values);
        db.close();
    }

    public void addEtudiant(Etudiant m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ATTRIBUT_NOM, m.getNom());
        values.put(ATTRIBUT_PRENOM, m.getPrenom());
        values.put(ATTRIBUT_NIVEAU, m.getNiveau());
        values.put(ATTRIBUT_FILIERE, m.getFiliere());

        db.insert(TABLE_ETUDIANT, null, values);
        db.close();
    }

    public void addModule(Module m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ATTRIBUT_SIGLE, m.getSigle());
        values.put(ATTRIBUT_CATEGORIE, m.getCategorie());
        values.put(ATTRIBUT_PARCOURS, m.getParcours());
        values.put(ATTRIBUT_CREDIT, m.getCredit());

        db.insert(TABLE_MODULE, null, values);
        db.close();
    }

    @Override
    public void initData() {

        ListeEnseignants c = new ListeEnseignants();
        ArrayList<Enseignant> l = c.getEnseignants() ;

        for(Enseignant enseignant : l){
            addEnseignant(enseignant);
        }

        ListeEtudiants cEtu = new ListeEtudiants();
        ArrayList<Etudiant> lEtu = cEtu.getEtudiants() ;

        for(Etudiant etudiant : lEtu){
            addEtudiant(etudiant);
        }

        ListeModule cModule = new ListeModule();
        ArrayList<Module> lModule = cModule.getModules();

        for(Module module : lModule){
            addModule(module);
        }
    }

    @Override
    public void delEnseignant(Enseignant m) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ENSEIGNANT,"ROWID="+m.getId(),null);
        db.close();
    }

    public void delEtudiant(Etudiant m) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ETUDIANT,"ROWID="+m.getId(),null);
        db.close();
    }

    public void delModule(Module m) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MODULE,"ROWID="+m.getId(),null);
        db.close();
    }

    @Override
    public void updateEnseignant(Enseignant m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_NOM, m.getNom());
        values.put(ATTRIBUT_PRENOM, m.getPrenom());
        values.put(ATTRIBUT_TYPE, m.getType());
        Log.i("m.getPhoto()","///////////////////////////////////////////////////"+m.getPhoto());
        if(m.getPhoto() != null){
            values.put(ATTRIBUT_PHOTO,m.getPhoto());
        }
        db.update(TABLE_ENSEIGNANT,values,"ROWID="+m.getId(),null);
        db.close();
    }

    public void updateEtudiant(Etudiant m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_NOM, m.getNom());
        values.put(ATTRIBUT_PRENOM, m.getPrenom());
        values.put(ATTRIBUT_NIVEAU, m.getNiveau());
        if(!m.getFiliere().equals("Toutes filières")){
            values.put(ATTRIBUT_FILIERE, m.getFiliere());
        }
        Log.i("m.getPhoto()","///////////////////////////////////////////////////"+m.getPhoto());
        if(m.getPhoto() != null){
            values.put(ATTRIBUT_PHOTO,m.getPhoto());
        }
        db.update(TABLE_ETUDIANT,values,"ROWID="+m.getId(),null);
        db.close();
    }

    public void updateModule(Module m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_SIGLE, m.getSigle());
        values.put(ATTRIBUT_CATEGORIE, m.getCategorie());
        values.put(ATTRIBUT_PARCOURS, m.getParcours());
        values.put(ATTRIBUT_CREDIT, m.getCredit());

        db.update(TABLE_MODULE,values,"ROWID="+m.getId(),null);
        db.close();
    }

    @Override
    public Enseignant getEnseignant(String key) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_ENSEIGNANT, new String[] { ATTRIBUT_NOM, ATTRIBUT_PRENOM, ATTRIBUT_TYPE, "ROWID",ATTRIBUT_PHOTO},"ROWID=?",
                new String[] { String.valueOf(key) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Enseignant enseignant = new Enseignant(cursor.getInt(3),cursor.getString(0),cursor.getString(1),cursor.getString(2));
        if (cursor.getString(4) != null) {
            enseignant.setPhoto(cursor.getString(4));
        }
        return enseignant;
    }

    public Etudiant getEtudiant(String key) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_ETUDIANT, new String[] { ATTRIBUT_NOM, ATTRIBUT_PRENOM, ATTRIBUT_NIVEAU, "ROWID",ATTRIBUT_PHOTO, ATTRIBUT_FILIERE},"ROWID=?",
                new String[] { String.valueOf(key) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Etudiant etudiant = new Etudiant(cursor.getInt(3),cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(5));
        if (cursor.getString(4) != null) {
            etudiant.setPhoto(cursor.getString(4));
        }
        return etudiant;
    }

    public Module getModule(String key) {
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_MODULE, new String[] { ATTRIBUT_SIGLE, ATTRIBUT_PARCOURS, ATTRIBUT_CATEGORIE, "ROWID",ATTRIBUT_CREDIT},"ROWID=?",
                new String[] { String.valueOf(key) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Module module = new Module(cursor.getInt(3),cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(4));

        return module;
    }

    public ArrayList<Enseignant> filterSearchEnseignant(String[] key) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Enseignant> liste = new ArrayList<Enseignant>();

        if(key[0].matches("")){
            key[0] = "%";
        }else {
            key[0] += "%";
        }
        if(key[1].matches("")){
            key[1] = "%";
        }else {
            key[1] += "%";
        }
        if(key[2].matches("")){
            key[2] = "%";
        }else {
            key[2] += "%";
        }
        Log.i("n", key[0]);
        Log.i("p", key[1]);
        Log.i("t", key[2]);

        Cursor cursor = db.query(TABLE_ENSEIGNANT, new String[] {ATTRIBUT_NOM, ATTRIBUT_PRENOM, ATTRIBUT_TYPE, "ROWID"}, ATTRIBUT_NOM + " LIKE ? AND "+ATTRIBUT_PRENOM+ " LIKE ? AND "+ATTRIBUT_TYPE+" LIKE ?",
                key, null, null, null, null);

        if(cursor.moveToFirst()){
            do {
                Enseignant enseignant = new Enseignant();
                enseignant.setNom(cursor.getString(0));
                enseignant.setPrenom(cursor.getString(1));
                enseignant.setType(cursor.getString(2));
                enseignant.setId(cursor.getInt(3));

                Log.i("getAllEnseignants",enseignant.toString());
                liste.add(enseignant);
            }while (cursor.moveToNext());
        }else {
            Enseignant enseignant = new Enseignant(0,"No record found","","");
            liste.add(enseignant);
        }
        db.close();
        return liste;
    }

    public ArrayList<Etudiant> filterSearchEtudiant(String[] key) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Etudiant> liste = new ArrayList<Etudiant>();

        if(key[0].matches("")){
            key[0] = "%";
        }else {
            key[0] += "%";
        }
        if(key[1].matches("")){
            key[1] = "%";
        }else {
            key[1] += "%";
        }
        if(key[2].matches("")){
            key[2] = "%";
        }else {
            key[2] += "%";
        }
        if(key[3].matches("")){
            key[3] = "%";
        }else {
            key[3] += "%";
        }
        Log.i("n", key[0]);
        Log.i("p", key[1]);
        Log.i("t", key[2]);
        Log.i("f", key[3]);

        Cursor cursor = db.query(TABLE_ETUDIANT, new String[] {ATTRIBUT_NOM, ATTRIBUT_PRENOM, ATTRIBUT_NIVEAU, "ROWID",ATTRIBUT_FILIERE}, ATTRIBUT_NOM + " LIKE ? AND "+ATTRIBUT_PRENOM+ " LIKE ? AND "+ATTRIBUT_NIVEAU+" LIKE ? AND "+ATTRIBUT_FILIERE+" LIKE ?",
                key, null, null, null, null);

        if(cursor.moveToFirst()){
            do {
                Etudiant etudiant = new Etudiant();
                etudiant.setNom(cursor.getString(0));
                etudiant.setPrenom(cursor.getString(1));
                etudiant.setNiveau(cursor.getString(2));
                etudiant.setId(cursor.getInt(3));
                etudiant.setFiliere(cursor.getString(4));
                Log.i("filterSearchEtudiant",etudiant.toString());
                liste.add(etudiant);
            }while (cursor.moveToNext());
        }else {
            Etudiant etudiant = new Etudiant(0,"No record found","","","");
            liste.add(etudiant);
        }
        db.close();
        return liste;
    }

    public ArrayList<Module> filterSearchModule(String[] key) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Module> liste = new ArrayList<Module>();

        if(key[0].matches("")){
            key[0] = "%";
        }else {
            key[0] += "%";
        }
        if(key[1].matches("")){
            key[1] = "%";
        }else {
            key[1] += "%";
        }
        if(key[2].matches("")){
            key[2] = "%";
        }else {
            key[2] += "%";
        }
        if(key[3].matches("")){
            key[3] = "%";
        }else {
            key[3] += "%";
        }
        Log.i("sigle", key[0]);
        Log.i("parcours", key[1]);
        Log.i("catégorie", key[2]);
        Log.i("credit",key[3]);

        Cursor cursor = db.query(TABLE_MODULE, new String[] {ATTRIBUT_SIGLE, ATTRIBUT_PARCOURS, ATTRIBUT_CATEGORIE, "ROWID", ATTRIBUT_CREDIT}, ATTRIBUT_SIGLE + " LIKE ? AND "+ATTRIBUT_PARCOURS+ " LIKE ? AND "+ATTRIBUT_CATEGORIE+" LIKE ? AND "+ATTRIBUT_CREDIT+" LIKE ?",
                key, null, null, null, null);

        if(cursor.moveToFirst()){
            do {
                Module module = new Module();
                module.setSigle(cursor.getString(0));
                module.setParcours(cursor.getString(1));
                module.setCategorie(cursor.getString(2));
                module.setId(cursor.getInt(3));
                module.setCredit(cursor.getInt(4));

                Log.i("getAllModules",module.toString());
                liste.add(module);
            }while (cursor.moveToNext());
        }else {
            Module module = new Module(0,"No record found","","",0);
            liste.add(module);
        }
        db.close();
        return liste;
    }




    @Override
    public int countEnseignant() {
        return 0;
    }

    @Override
    public ArrayList<Enseignant> getAllEnseignants() {
        ArrayList<Enseignant> liste = new ArrayList<Enseignant>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_ENSEIGNANT, new String[] { ATTRIBUT_NOM, ATTRIBUT_PRENOM, ATTRIBUT_TYPE, "ROWID"},null,
                null, null, null, null, null);


        if(cursor.moveToFirst()){
            do {
                Enseignant enseignant = new Enseignant();
                enseignant.setId(cursor.getInt(3));
                enseignant.setNom(cursor.getString(0));
                enseignant.setPrenom(cursor.getString(1));
                enseignant.setType(cursor.getString(2));

                Log.i("getAllEnseignants",enseignant.toString());
                liste.add(enseignant);
            }while (cursor.moveToNext());
        }
        db.close();
        return liste;
    }

    public ArrayList<Etudiant> getAllEtudiants() {
        ArrayList<Etudiant> liste = new ArrayList<Etudiant>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_ETUDIANT, new String[] { ATTRIBUT_NOM, ATTRIBUT_PRENOM, ATTRIBUT_NIVEAU, "ROWID", ATTRIBUT_FILIERE},null,
                null, null, null, null, null);


        if(cursor.moveToFirst()){
            do {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(cursor.getInt(3));
                etudiant.setNom(cursor.getString(0));
                etudiant.setPrenom(cursor.getString(1));
                etudiant.setNiveau(cursor.getString(2));
                etudiant.setFiliere(cursor.getString(4));

                Log.i("getAllEtudiants",etudiant.toString());
                liste.add(etudiant);
            }while (cursor.moveToNext());
        }
        db.close();
        return liste;
    }

    public ArrayList<Module> getAllModules() {
        ArrayList<Module> liste = new ArrayList<Module>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_MODULE, new String[] { ATTRIBUT_SIGLE, ATTRIBUT_PARCOURS, ATTRIBUT_CATEGORIE, "ROWID", ATTRIBUT_CREDIT},null,
                null, null, null, null, null);


        if(cursor.moveToFirst()){
            do {
                Module module = new Module();
                module.setId(cursor.getInt(3));
                module.setSigle(cursor.getString(0));
                module.setParcours(cursor.getString(1));
                module.setCategorie(cursor.getString(2));
                module.setCredit(cursor.getInt(4));

                Log.i("getAllModules",module.toString());
                liste.add(module);
            }while (cursor.moveToNext());
        }
        db.close();
        return liste;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(table_enseignant_create);
        sqLiteDatabase.execSQL(table_etudiant_create);
        sqLiteDatabase.execSQL(table_module_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ENSEIGNANT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ETUDIANT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MODULE);
        onCreate(sqLiteDatabase);
    }
}
