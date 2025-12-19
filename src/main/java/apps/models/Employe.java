package apps.models;

import java.time.LocalDate;
import java.util.Arrays;

public class Employe {
    private Integer  [] id;
    private String nom;
    private Double taille;
    private LocalDate dateNaissance;
    private boolean estMarie;
    private Departement departement;
    private Type type;

    public Employe (Integer [] id , String nom , Double taille  , LocalDate dateNaissance  , boolean estMarie , Departement departement , Type type ) {
        setDateNaissance(dateNaissance);
        setDepartement(departement);
        setEstMarie(estMarie);
        setTaille(taille);
        setType(type);
        setId(id);
        setNom(nom);
        
    }


    @Override
    public String toString() {

        return "Employe{" +
                "id=" +  Arrays.asList(id)  +
                ", nom='" + nom + '\'' +
                ", taille=" + taille +
                ", dateNaissance=" + dateNaissance +
                ", estMarie=" + estMarie +
                ", type= " + type +
                ", departement=" + departement +
                '}';
    }

    public Employe (){

    }
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
    public Departement getDepartement() {
        return departement;
    }
    public Integer[] getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public Double getTaille() {
        return taille;
    }
    public Type getType() {
        return type;
    }
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
    public void setEstMarie(boolean estMarie) {
        this.estMarie = estMarie;
    }
    public void setId(Integer[] id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setTaille(Double taille) {
        this.taille = taille;
    }
    public void setType(Type type) {
        this.type = type;
    }
}
