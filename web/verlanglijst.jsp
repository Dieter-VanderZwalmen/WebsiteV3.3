
<jsp:include page="headheader.jsp">
    <jsp:param name="" value =""/>
</jsp:include>
<main>
    <h2>verlanglijst</h2>
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
                            <a href="ProductInfo?command=setCookieLocatie&cookieLocatie=Betekom">Betekom</a>
                            <a href="ProductInfo?command=setCookieLocatie&cookieLocatie=Rotselaar">Rotselaar</a>
                            <a href="ProductInfo?command=setCookieLocatie&cookieLocatie=Beide">Beide</a>
                        </div>
                    </div>
                </td>

            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>naam</td>
                    <td>beschrijving}</td>
                    <td>x.calorieenx.eenheid}</td>
                    <td>x.gram}</td>
                    <td>x.getProcent(x.getCalorieen())}%</td>
                    <td><a href="ProductInfo?command=verwijder&naam=naam" class="fa fa-trash"></a></td><!--vuilbakjes voor delete--->
                </tr>


            <p id = "totaal">5</p>

            </tbody>
        </table>
    </article>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="" value =""/>
</jsp:include>
