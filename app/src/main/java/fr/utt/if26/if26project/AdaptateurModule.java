package fr.utt.if26.if26project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class AdaptateurModule extends ArrayAdapter<Module> {

        ArrayList<Module> modules;
        Context contexte;
        int ressource;

        public AdaptateurModule(Context context, int resource, ArrayList<Module> data) {
            super(context, resource, data);
            this.modules = data;
            this.contexte = context;
            this.ressource = resource;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ((Activity) contexte).getLayoutInflater();
            View v = inflater.inflate(ressource, parent, false);

            Module module = modules.get(position);

            TextView tv_sigle = (TextView) v.findViewById(R.id.m_sigle);
            tv_sigle.setText(module.getSigle());

            TextView tv_parcours = (TextView) v.findViewById(R.id.m_parcours);
            tv_parcours.setText(module.getParcours());

            TextView tv_categorie = (TextView) v.findViewById(R.id.m_categorie);
            tv_categorie.setText(module.getCategorie());

            TextView tv_credit = (TextView) v.findViewById(R.id.m_credit);
            tv_credit.setText(Integer.toString(module.getCredit()));

            TextView rowid = (TextView) v.findViewById(R.id.ROWID);
            rowid.setText(Integer.toString(module.getId()));

            return v;

            }
            }