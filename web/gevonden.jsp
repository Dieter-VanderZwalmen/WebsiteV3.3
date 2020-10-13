<%@ page import="domain.db.ProductDB" %>
<%@ page import="domain.model.Product" %>
<%@include file="headheader.jsp"%>
<%
    ProductDB x = new ProductDB();
    Product product = x.findProduct(request.getParameter("naam"));

%>
<body style="min-height: 100vh !important;">
<p>Je vroeg naar volgende gegevens:
    <%=product.toString()%>
</p>
<%@include file="footer.jsp"%>
</body>
</html>