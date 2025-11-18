package apps.controllers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import annotations.ControllerAnnotation;
import annotations.UrlAnnotation;
import apps.models.Departement;
import apps.models.Utilisateur;
import views.ModelView;

@ControllerAnnotation(name = "test1")
public class Test1 {

    public Test1() {

    }

    @UrlAnnotation(value = "/hello")
    public String helloWorld() {
        return "hello";
    }

    @UrlAnnotation(value = "/test")
    public ModelView test() {
        ModelView mv = new ModelView();
        List<Departement> departements = Arrays.asList(
                new Departement("Informatique", 1),
                new Departement("Mathematiques", 2),
                new Departement("Physique", 3),
                new Departement("Chimie", 4));

        Utilisateur utilisateur = new Utilisateur(1, "Benja", "Be", "b@gmail", LocalDate.now());

        mv.putData("departements", departements);
        mv.putData("utilisateur", utilisateur);


        mv.setView("test.jsp");
        return mv;
    }
}
