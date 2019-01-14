package fr.utt.if26.if26project;

import java.util.ArrayList;
import java.util.Iterator;

public class ListeEtudiants {
    private ArrayList<Etudiant> etudiants;

    public ListeEtudiants() {
        etudiants = new ArrayList();
        init();
    }

    public void ajoute(Etudiant m) {
        etudiants.add(m);
    }

    public void init() {
        ajoute(new Etudiant(1,"Samy", "Deblin", "ISI1",""));
        ajoute(new Etudiant(2,"Mael", "Tost", "ISI4","MPL"));
        ajoute(new Etudiant(3,"Kevin", "Teronal", "ISI5","MRI"));
        ajoute(new Etudiant(4,"Pol", "Saponi", "ISI8","MSI"));
        ajoute(new Etudiant(5,"Astrid", "Palomer", "ISI2",""));
    }

    public ArrayList<String> getNoms() {
        ArrayList res = new ArrayList();
        Iterator iterateur = etudiants.iterator();
        while (iterateur.hasNext()) {
            Etudiant m = (Etudiant) (iterateur.next());
            res.add(m.getNom());
        }
        return res;
    }


    public ArrayList<Etudiant> getEtudiants() {
        System.out.println(etudiants.toString());
        return etudiants;
    }
}
