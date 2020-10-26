<%@ page import="domain.db.ProductDB" %>
<%@ page import="domain.model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@include file="headheader.jsp"%>


<% ProductDB product = (ProductDB) request.getAttribute("product"); %>
<% String naam = (String) request.getAttribute("naam");%>
<body>
<article class="text_centraal">
    <p>Je vroeg naar volgende gegevens:
        <%=product.findProduct(naam)%>
    </p>
</article>
<%@include file="footer.jsp"%>
</body>
</html>