package apps.models;

import java.util.Arrays;

public class Departement {
    private Integer id;
    private String nom;
    private Employe[] employe;

    public Departement() {

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Departement{");
        builder.append("id=").append(id);
        builder.append(", nom=").append(nom);
        builder.append(", employe=");

        if (employe != null) {
            builder.append(Arrays.asList(employe));
        } else {
            builder.append("[]");
        }

        builder.append('}');

        return builder.toString();
    }

    // public Employe[] getEmployes() {
    // return employes;
    // }
    // public void setEmployes(Employe[] employes) {
    // this.employes = employes;
    // }

    public Departement(String nom, Integer id) {
        setId(id);
        setNom(nom);
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
