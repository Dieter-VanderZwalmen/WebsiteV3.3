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
    <jsp:param name="activeItem" value="Overzicht"/>
</jsp:include>

<main>
    <h2>overzicht van alle producten</h2>
    <article class="container_table">

        <table>
            <thead id="head_tr">
            <tr>
                <td>Naam</td>
                <td>Beschrijving</td>
                <td>Calorieen</td>
                <td>Gram</td>
                <td>Percentage</td>

                <td>
                    <div class="dropdown">
                        <a class="fa fa-filter" aria-hidden="true"></a>
                        <div class="dropdown-content">
                            <a href="ProductInfo?command=setCookieLocatie&cookieLocatie=Beide">Beide</a>
                            <a href="ProductInfo?command=setCookieLocatie&cookieLocatie=Betekom">Betekom</a>
                            <a href="ProductInfo?command=setCookieLocatie&cookieLocatie=Rotselaar">Rotselaar</a>


                        </div>
                    </div>
                </td>


            </tr>
            </thead>
            <tbody>

            <c:forEach var="x" items="${producten}">
                <tr>
                    <td>${x.naam}</td>
                    <td>${x.beschrijving}</td>
                    <td>${x.calorieen} ${x.eenheid}</td>
                    <td>${x.gram}</td>
                    <td>${x.getProcent(x.getCalorieen())}%</td>
                    <td><a href="ProductInfo?command=update&naam=${x.naam}" class="fa fa-refresh"></a></td>
                    <td><a href="ProductInfo?command=verwijder&naam=${x.naam}" class="fa fa-trash"></a></td>
                    <!--vuilbakjes voor delete--->
                    <td><a href="ProductInfo?command=verlanglijst&naam=${x.naam}" class="fa fa-heart"
                           aria-hidden="true"></a></td> <!-- hartje voor verlang lijst-->
                </tr>


            </c:forEach>

            </tbody>
        </table>
        <div class="teller"> <p>${teller}</p> </div>
    </article>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="" value=""/>
</jsp:include>
