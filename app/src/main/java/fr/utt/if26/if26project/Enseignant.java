package fr.utt.if26.if26project;

import android.net.Uri;

import java.io.Serializable;

public class Enseignant implements Serializable {

    private int id;
    private String nom;
    private String prenom;
    private String type;
    private String photo;

    public Enseignant(int id, String nom, String prenom, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom= prenom;
        this.type = type;
        this.photo = null;
    }

    public Enseignant(){};

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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
