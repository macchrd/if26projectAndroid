package fr.utt.if26.if26project;

import java.util.ArrayList;

public interface PersistanceInterface {
        // ajoute un module dans la base (faire attention à sigle qui est clé primaire)
        public void addEnseignant(Enseignant m);

        // ajoute l'ensemble des modules du ListeModule via la méthode getModules()
        public void initData();

        // supprime un module dans la base (faire attention à sigle qui est clé primaire)
        public void delEnseignant(Enseignant m);

        // mise à jour un module dans la base (faire attention à sigle qui est clé primaire)
        public void updateEnseignant(Enseignant m);

        // recherche d'un module dans la base via la clé primaire (sigle)
        public Enseignant getEnseignant(String key);

        // retourne le nombre de module
        public int countEnseignant();

        // retourne l'ensemble des modules de la base
        public ArrayList<Enseignant> getAllEnseignants();
    }

