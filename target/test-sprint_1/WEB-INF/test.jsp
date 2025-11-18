<%@ page import="java.util.List" %>
<%@ page import="apps.models.Departement" %>
<%@ page import="apps.models.Utilisateur" %>
<%@ page import="views.ModelView" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%
    ModelView mv = (ModelView) request.getAttribute("modelView");

    List<Departement> departements = (List<Departement>) mv.getDataByKey("departements");

    Utilisateur utilisateur = (Utilisateur) mv.getDataByKey("utilisateur");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste Departements et Utilisateur</title>
</head>
<body>

<h1>Utilisateur</h1>
<p>
    <strong>ID:</strong> <%= utilisateur.getId() %><br>
    <strong>Nom:</strong> <%= utilisateur.getNom() %><br>
    <strong>Prenoms:</strong> <%= utilisateur.getPrenoms() %><br>
    <strong>Email:</strong> <%= utilisateur.getEmail() %><br>
    <strong>Date:</strong> <%= utilisateur.getDate().format(formatter) %>
</p>

<h1>Liste des Departements</h1>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Nom</th>
    </tr>

    <% for(Departement d : departements) { %>
        <tr>
            <td><%= d.getId() %></td>
            <td><%= d.getNom() %></td>
        </tr>
    <% } %>
</table>

</body>
</html>
