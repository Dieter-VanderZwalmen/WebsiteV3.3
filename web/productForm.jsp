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
    <jsp:param name="" value=""/>
</jsp:include>
<!--Product(naam,beschrijving,calorieen,eenheid,gram);-->
<main>
    <h1>Maak Product</h1>
    <form method="Post" action="ProductInfo?command=voegToe" novalidate>
        <article class="container_form">
            <article class="text_centraal2">
                <p>voer gegevens in</p>
            </article>
            <!--request.setAtttribute(errors) ophalen-->
            <c:if test="${not empty errors}">
                <c:forEach var="error" items="${errors}">
                    <ul>
                        <li>${error}</li>
                    </ul>
                </c:forEach>
            </c:if>


            <article id="form">
                <div id="column_one">


                    <p>
                        <label class="control-label" for="naam">Naam:</label>
                        <input id="naam" name="naam" type="text" value="${naamVorigeWaarde}" required>
                    </p>

                    <p>
                        <label class="control-label" for="beschrijving">Beschrijving:</label>
                        <input id="beschrijving" name="beschrijving" type="text" value="${beschrijvingVorigeWaarde}"
                               required>
                    </p>
                    <p>
                        <label class="control-label" for="calorieen">Calorie&euml;n:</label>
                        <input id="calorieen" name="calorieen" type="number" value="${calorieeenVorigeWaarde}" required>
                    </p>
                </div>
                <div id="column_two">

                    <p>
                        <label class="control-label" for="eenheid">Eenheid:</label>
                        <input id="eenheid" name="eenheid" type="text" value=
                        <c:choose>
                        <c:when test="${not empty eenheidVorigeWaarde}">
                                "${eenheidVorigeWaarde}"
                        </c:when>
                        <c:otherwise>
                            "Per stuk"
                        </c:otherwise>
                        </c:choose>
                        required>
                    </p>
                    <p>
                        <label class="control-label" for="gram">Gram:</label>
                        <input id="gram" name="gram" type="number" value="${gramVorigeWaarde}" required>
                    </p>
                    <p>
                        <label class="control-label" for="locatie">Locatie:</label>
                        <select id="locatie" name="locatie">
                            <option value="Betekom">Betekom</option>
                            <option value="Rotselaar">Rotselaar</option>
                            <option value="Beide">Beide</option>
                        </select>
                    </p>
                    <article class="knop_centraal">
                        <p>
                            <label for="vind">&nbsp;</label>
                            <input id="vind" type="submit" value="Voeg product toe">
                        </p>
                    </article>
                </div>

            </article>
        </article>
    </form>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="" value=""/>
</jsp:include>
