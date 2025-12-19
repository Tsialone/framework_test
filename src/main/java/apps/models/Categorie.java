package apps.models;

public class Categorie {
    private Integer id;
    private String libelle;
    private Type type;

    public Categorie(Integer id, String libelle) {
        setId(id);
        setLibelle(libelle);
    }

    public Categorie() {

    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", type=" + type +
                ", libelle='" + libelle + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

}
