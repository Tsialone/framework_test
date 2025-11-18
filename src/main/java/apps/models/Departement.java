package apps.models;

public class Departement {
    private Integer id;
    private String nom;   

    public Departement (){

    }
    public Departement (String nom , Integer id){
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
