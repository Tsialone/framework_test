package apps.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import annotations.ControllerAnnotation;
import annotations.JsonAnnotation;
import annotations.RequestParam;
import annotations.UrlAnnotation;
import annotations.UrlGetAnnotation;
import annotations.UrlPostAnnotation;
import apps.models.Departement;
import apps.models.Employe;
import apps.models.Utilisateur;
import utils.FileUtil;
import utils.KeyValueUtil;
import views.ModelView;

@ControllerAnnotation(name = "test1")
public class Test1 {

    public Test1() {

    }

    @UrlPostAnnotation(value = "/upload")
    public String upload(HashMap<String , byte []>  fileUpload  ) {
        // Employe [] emp = { employe };
        // StringBuilder coco = new StringBuilder();

        // if (employes != null ) {
        // for (Employe employe2 : employes) {
        // coco.append(employe2.toString());

        // }
        // }
        System.out.println("cacacacaca: " + fileUpload);
        StringBuilder coco = new StringBuilder();
        coco.append(FileUtil.printFileMap(fileUpload));
        FileUtil.saveFilesByMap(fileUpload);
        // coco.append(Arrays.asList(test));
        // coco.append();

        return coco.toString();
    }

    @UrlGetAnnotation(value = "/object")
    @JsonAnnotation(value = "api")
    public String createEmploye(
            Integer[] test) {
        // Employe [] emp = { employe };
        // StringBuilder coco = new StringBuilder();

        // if (employes != null ) {
        // for (Employe employe2 : employes) {
        // coco.append(employe2.toString());

        // }
        // }
        StringBuilder coco = new StringBuilder();
        coco.append(Arrays.asList(test));

        return coco.toString();
    }

    @UrlGetAnnotation(value = "/dept-create")
    @JsonAnnotation(value = "api")
    public Employe[] createDept(
            Departement departement[],
            Employe employe[],
            // Employe [] employe
            String[] roro

    ) {
        StringBuilder coco = new StringBuilder();

        if (departement != null) {
            for (Departement dept : departement) {
                if (dept != null) {
                    coco.append(dept.toString());
                }
            }
        }
        if (employe != null) {
            for (Employe emp : employe) {
                if (emp != null) {
                    coco.append(emp.toString());
                }
            }
        }
        if (roro != null) {
            coco.append(Arrays.asList(roro));
        }
        return employe;
    }

    // 3BIS et 6 et 6BIS
    @UrlPostAnnotation(value = "/hello")
    public String helloWorld(
            HashMap<String, Object> map,
            Integer id,
            String lolo,
            @RequestParam(name = "toto") Double prix,
            LocalDateTime date) {
        StringBuilder coco = new StringBuilder();

        coco.append("\n\nValeur hors Map:")
                .append("\nid: ").append(id)
                .append("\nlolo: ").append(lolo)
                .append("\nprix: ").append(prix)
                .append("\ndate: ").append(date);

        coco.append("\n\n").append(KeyValueUtil.outMap(map));

        return coco.toString();
    }

    // @UrlAnnotation(value = "/hello")
    @UrlGetAnnotation(value = "/hello")
    public String helloCreate(Integer id, String lolo, @RequestParam(name = "toto") Double prix, LocalDateTime date) {
        String coco = "\n\nid: " + id + "\nlolo: " + lolo + "\nprix: " + prix + "\ndate: " + date;

        return coco;
    }

    @UrlAnnotation(value = "/create")
    public String testPost(Integer idtest, String nomtest) {
        String coco = "\nid: " + idtest + "\nnom: " + nomtest;
        return coco;
    }

    // 6triers
    // /bendo/1/notes/1
    @UrlAnnotation(value = "/bendo/{identification}/notes/{value}")
    public String bendo(@RequestParam(name = "identification") Integer id, Double value) {
        String coco = "\n\nid: " + id + "\nnote: " + value;

        return coco;
    }

    // /test/1/
    @UrlAnnotation(value = "/test/{idUtilisateur}/{date}")
    @JsonAnnotation(value = "api")
    public ModelView test(@RequestParam(name = "idUtilisateur") Integer user, LocalDate date) {
        ModelView mv = new ModelView();
        List<Departement> departements = Arrays.asList(
                new Departement("Informatique", 1),
                new Departement("Mathematiques", 2),
                new Departement("Physique", 3),
                new Departement("Chimie", 4));

        Utilisateur utilisateur = new Utilisateur(user, "Benja", "Be", "b@gmail", date);

        mv.putData("departements", departements);
        mv.putData("utilisateur", utilisateur);

        mv.setView("test.jsp");
        return mv;
    }
}
