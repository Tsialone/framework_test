package apps.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import annotations.ControllerAnnotation;
import annotations.RequestParam;
import annotations.UrlAnnotation;
import annotations.UrlGetAnnotation;
import annotations.UrlPostAnnotation;
import apps.models.Departement;
import apps.models.Utilisateur;
import views.ModelView;

@ControllerAnnotation(name = "test1")
public class Test1 {

    public Test1() {

    }

    // 3BIS et 6 et 6BIS
    @UrlAnnotation(value = "/hello")
    @UrlGetAnnotation
    public String helloWorld(Integer id , String lolo ,   @RequestParam (name =   "toto")  Double prix , LocalDateTime date) {
        String coco = "\n\nid: " + id  +  "\nlolo: " + lolo + "\nprix: " + prix + "\ndate: " + date;
        
        return coco;
    }

    @UrlAnnotation(value = "/hello")
    @UrlPostAnnotation
    public String helloCreate(Integer id , String lolo ,   @RequestParam (name =   "toto")  Double prix , LocalDateTime date) {
        String coco = "\nid: " + id  +  "\nlolo: " + lolo + "\nprix: " + prix + "\ndate: " + date;
        
        return coco;
    }


    @UrlAnnotation(value = "/create")
    @UrlGetAnnotation
    public String testPost(Integer idtest, String nomtest) {
        String coco = "\nid: " + idtest  +  "\nnom: " + nomtest ;
        return coco;
    }

    //6triers
    // /bendo/1/notes/1
    @UrlAnnotation(value = "/bendo/{identification}/notes/{value}")
    public String bendo(@RequestParam (name = "identification") Integer id , Double value  ) {
        String coco = "\n\nid: " + id  +  "\nnote: " + value ;
        
        return coco;
    }


    // /test/1/
    @UrlAnnotation(value = "/test/{idUtilisateur}/{date}")
    public ModelView test(@RequestParam (name = "idUtilisateur") Integer user , LocalDate date) {
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
