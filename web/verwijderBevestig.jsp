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
<main>
    <h2>Wil je zeker ${param.naam} verwijderen?</h2>

    <article>
        <form id="zoek" action="ProductInfo?command=verwijderBevestig&naam=${param.naam}" method="POST">
            <input class="verstuur" type="submit" value="Zeker">
            <a class="verstuur toch_niet" href="ProductInfo?command=overzicht">Terug </a>
        </form>
    </article>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="" value=""/>
</jsp:include>
