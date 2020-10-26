<%@ page import="domain.model.Product" %>
<%@ page import="domain.db.ProductDB" %>
<%@ page import="java.util.ArrayList" %>
<%@include file="headheader.jsp"%>

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


            <% ArrayList<Product> producten = (ArrayList<Product>) request.getAttribute("producten");
                for (Product x: producten) {%>

            <tr>
                <td><%=x.getNaam()%></td>
                <td><%=x.getBeschrijving()%></td>
                <td><%=x.getCalorieen() +" " + x.getEenheid()%></td>
                <td><%=x.getGram()%></td>
                <td><%=x.getProcent(x.getCalorieen())%>%</td>
                <td><a href="ProductInfo?command=verwijder&naam=<%=x.getNaam()%>" class="fa fa-trash"></a></td>

            </tr>
            <%}%>
            <p id = "totaal"><%=producten.size()%></p>

            </tbody>
        </table>
    </article>
</main>
<%@include file="footer.jsp"%>
</body>
</html>
