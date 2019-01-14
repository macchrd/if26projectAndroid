package fr.utt.if26.if26project;

import java.util.ArrayList;
import java.util.Iterator;

public class ListeEnseignants {
    private ArrayList<Enseignant> enseignants;

    public ListeEnseignants() {
        enseignants = new ArrayList();
        init();
    }

    public void ajoute(Enseignant m) {
        enseignants.add(m);
    }

    public void init() {
        ajoute(new Enseignant(1,"Michel", "Debois", "Contractuel"));
        ajoute(new Enseignant(2,"Philippe", "Trust", "Maitre de conférence"));
        ajoute(new Enseignant(3,"Alain", "Ternational", "Contractuel"));
        ajoute(new Enseignant(4,"Lucas", "Slop", "Professeur"));
        ajoute(new Enseignant(5,"Tony", "Parker", "Contractuel"));
    }

    public ArrayList<String> getNoms() {
        ArrayList res = new ArrayList();
        Iterator iterateur = enseignants.iterator();
        while (iterateur.hasNext()) {
            Enseignant m = (Enseignant) (iterateur.next());
            res.add(m.getNom());
        }
        return res;
    }


    public ArrayList<Enseignant> getEnseignants() {
        System.out.println(enseignants.toString());
        return enseignants;
    }
}
