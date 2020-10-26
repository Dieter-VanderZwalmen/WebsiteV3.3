<%@include file="headheader.jsp"%>
    <main>
        <h2>Wil je zeker <%=request.getParameter("naam")%> verwijderen?</h2>
        <article>
            <form id="zoek" action="ProductInfo?command=verwijderBevestig&naam=<%= request.getParameter("naam")%>" method="POST">
                <input class="verstuur" type="submit" value="Zeker">
                <a class="verstuur toch_niet" href="ProductInfo?command=overzicht" >Terug </a>
            </form>
        </article>
    </main>
<%@include file="footer.jsp"%>
</body>
</html>
