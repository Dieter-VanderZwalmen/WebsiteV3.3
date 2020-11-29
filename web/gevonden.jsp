<%@ page import="domain.db.ProductDB" %>
<%@ page import="domain.model.Product" %>
<%@ page import="java.util.ArrayList" %>
<jsp:include page="headheader.jsp">
        <jsp:param name="" value =""/>
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
    <jsp:param name="" value =""/>
</jsp:include>