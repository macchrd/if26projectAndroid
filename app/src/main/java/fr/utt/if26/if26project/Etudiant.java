package fr.utt.if26.if26project;

import java.io.Serializable;

public class Etudiant implements Serializable {

    private int id;
    private String nom;
    private String prenom;
    private String niveau;
    private String filiere;
    private String photo;

    public Etudiant(int id, String nom, String prenom, String niveau, String filiere) {
        this.id = id;
        this.nom = nom;
        this.prenom= prenom;
        this.niveau = niveau;
        this.filiere = filiere;
        this.photo = null;
    }

    public Etudiant(){};

    public void setFiliere(String filiere){
        this.filiere = filiere;
    }
    public String getFiliere() {
        return filiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void affiche() {
        System.out.println(toString());
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", niveau='" + niveau + '\'' +
                ", filiere='" + filiere + '\''+
                '}';
    }
}
