<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <jsp:param name="activeItem" value="index"/>
</jsp:include>
<main class="main_desktop">
    <article class="container container_one">
        <img id="schema" src="img/croissant.png" alt="hart">
    </article>

    <article class="container container_two">
        <h2>Brood & Banket</h2>
        <p>Hier vind je al het lekkere wat Van Praet te bieden heeft.Waar ambacht en vernieuwing hand in hand gaan.
            In onze bakkerijen in Rotselaar en Betekom bieden we je een royale keuze aan vers brood en gebak,
            telkens bereid volgens eigen recept en met aandacht voor intoleranties.
        </p>
    </article>
</main>
<!--fout ligt bij de laatste include-->
<jsp:include page="footer.jsp">
    <jsp:param name="" value=""/>
</jsp:include>