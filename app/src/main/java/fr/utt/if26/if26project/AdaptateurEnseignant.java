package fr.utt.if26.if26project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class AdaptateurEnseignant extends ArrayAdapter<Enseignant> {

        ArrayList<Enseignant> enseignants;
        Context contexte;
        int ressource;

        public AdaptateurEnseignant(Context context, int resource, ArrayList<Enseignant> data) {
            super(context, resource, data);
            this.enseignants = data;
            this.contexte = context;
            this.ressource = resource;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ((Activity) contexte).getLayoutInflater();
            View v = inflater.inflate(ressource, parent, false);

            Enseignant enseignant = enseignants.get(position);

            TextView tv_sigle = (TextView) v.findViewById(R.id.enseignant_nom);
            tv_sigle.setText(enseignant.getNom());

            TextView tv_categorie = (TextView) v.findViewById(R.id.enseignant_prenom);
            tv_categorie.setText(enseignant.getPrenom());

            TextView tv_parcours = (TextView) v.findViewById(R.id.enseignant_type);
            tv_parcours.setText(enseignant.getType());

            TextView rowid = (TextView) v.findViewById(R.id.ROWID);
            rowid.setText(Integer.toString(enseignant.getId()));

            return v;

            }
            }