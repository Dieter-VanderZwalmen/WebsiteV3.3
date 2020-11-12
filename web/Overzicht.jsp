<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.model.Product" %>
<%@ page import="domain.db.ProductDB" %>
<%@ page import="java.util.ArrayList" %>
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
            </tr>
            </thead>
            <tbody>

            <!-- deze lijn moet nog weg maar weet nog niet hoe-->
            <c:forEach var="x" items="${producten}">
                <tr>
                    <td>${x.naam}</td>
                    <td>${x.beschrijving}</td>
                    <td>${x.calorieen} ${x.eenheid}</td>
                    <td>${x.gram}</td>
                    <td>${x.getProcent(x.getCalorieen())}%</td>
                    <td><a href="ProductInfo?command=verwijder&naam=${x.naam}" class="fa fa-trash"></a></td>

                </tr>


            </c:forEach>
            <p id = "totaal">${teller}</p>

            <!--                producten.getAantalProducten werkt niet ?
                                maar moet wel gebruikt worden? -->

            </tbody>
        </table>
    </article>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="" value =""/>
</jsp:include>
