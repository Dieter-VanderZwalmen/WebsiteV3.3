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
    <article class="text_centraal">
        <p>Het product met de naam ${param.naam} is niet gevonden</p>

    </article>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="" value=""/>
</jsp:include>