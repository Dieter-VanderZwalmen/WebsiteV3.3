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
    <jsp:param name="activeItem" value="zoek"/>
</jsp:include>
<main>

    <article class="contaier_form">
        <article class="text_centraal2">
            <h2>Zoek Product</h2>
        </article>
        <form method="Get" action="ProductInfo" novalidate>
            <p>voer gegevens in</p>
            <p>
                <label class="control-label" for="naam">Naam:</label>
                <input id="naam" name="naam" type="text" value="" required>
            </p>
            <p>
                <label for="zoek">&nbsp;</label>
                <input id="zoek" type="submit" value="Vind product">
                <input type="hidden" name="command" value="zoek">
            </p>
        </form>
    </article>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="" value=""/>
</jsp:include>