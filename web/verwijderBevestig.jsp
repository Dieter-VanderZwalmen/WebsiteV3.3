<jsp:include page="headheader.jsp">
    <jsp:param name="" value =""/>
</jsp:include>
    <main>
        <h2>Wil je zeker ${param.naam} verwijderen?</h2>

        <article>
            <form id="zoek" action="ProductInfo?command=verwijderBevestig&naam=${param.naam}" method="POST">
                                                                                request.getParameter("naam")
                <input class="verstuur" type="submit" value="Zeker">
                <a class="verstuur toch_niet" href="ProductInfo?command=overzicht" >Terug </a>
            </form>
        </article>
    </main>
<jsp:include page="footer.jsp">
    <jsp:param name="" value =""/>
</jsp:include>
