<%@include file="headheader.jsp"%>
        <main>
            <article class = "contaier_form">
                <h1>Zoek Product</h1>
                <form method="Get" action="ProductInfo" novalidate>
                    <p>voer gegevens in</p>
                    <p>
                        <label class="control-label" for="naam">Naam:</label>
                        <input id="naam" name="naam" type="text" value="" required>
                    </p>
                    <p>
                        <label for="zoek">&nbsp;</label>
                        <input id="zoek" type="submit" value="Vind product">
                    </p>
                </form>
            </article>
        </main>
<%@include file="footer.jsp"%>
    </body>
</html>
