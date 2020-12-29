<%@ page import="domain.db.ProductDB" %>
<%@ page import="domain.model.Product" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Van Praet</title>
    <link rel="stylesheet" href="opmaak/stijl.css">
    <link rel="icon" href="img/logo.png">


</head>

<jsp:include page="headheader.jsp">
    <jsp:param name="" value=""/>
</jsp:include>


<!--ProductDB product = (ProductDB) request.getAttribute("product"); -->

<!--String naam = (String) request.getAttribute("naam");-->
<main>
    <article class="text_centraal">
        <p>Je vroeg naar volgende gegevens:
            ${productNaarString}

        </p>
    </article>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="" value=""/>
</jsp:include>