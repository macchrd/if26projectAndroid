package fr.utt.if26.if26project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class AdaptateurEtudiant extends ArrayAdapter<Etudiant> {

        ArrayList<Etudiant> etudiants;
        Context contexte;
        int ressource;

        public AdaptateurEtudiant(Context context, int resource, ArrayList<Etudiant> data) {
            super(context, resource, data);
            this.etudiants = data;
            this.contexte = context;
            this.ressource = resource;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ((Activity) contexte).getLayoutInflater();
            View v = inflater.inflate(ressource, parent, false);

            Etudiant etudiant = etudiants.get(position);

            TextView tv_nom = (TextView) v.findViewById(R.id.etudiant_nom);
            tv_nom.setText(etudiant.getNom());

            TextView tv_prenom = (TextView) v.findViewById(R.id.etudiant_prenom);
            tv_prenom.setText(etudiant.getPrenom());

            TextView tv_niveau = (TextView) v.findViewById(R.id.m_categorie);
            tv_niveau.setText(etudiant.getNiveau());

            TextView tv_filiere = (TextView) v.findViewById(R.id.etudiant_filiere);
            tv_filiere.setText(etudiant.getFiliere());

            TextView rowid = (TextView) v.findViewById(R.id.ROWID);
            rowid.setText(Integer.toString(etudiant.getId()));

            return v;

            }
            }