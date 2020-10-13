<%@include file="headheader.jsp"%>
<!--Product(naam,beschrijving,calorieen,eenheid,gram);-->
<main>
    <h1>Maak Product</h1>
    <form method="Post" action="ProductInfo" novalidate>
    <article class = "container_form">
        <p>voer gegevens in</p>
        <article id = "form">
            <div id="column_one">



                        <p>
                            <label class="control-label" for="naam">Naam:</label>
                            <input id="naam" name="naam" type="text" value="" required>
                        </p>

                        <p>
                            <label class="control-label" for="beschrijving">Beschrijving:</label>
                            <input id="beschrijving" name="beschrijving" type="text" value="" required>
                        </p>
            </div>
            <div id="column_two">
                        <p>
                            <label class="control-label" for="calorieen">Calorie&euml;n:</label>
                            <input id="calorieen" name="calorieen" type="number" value="" required>
                        </p>
                        <p>
                            <label class="control-label" for="eenheid">Eenheid:</label>
                            <input id="eenheid" name="eenheid" type="text" value="per stuk" required>
                        </p>
                        <p>
                            <label class="control-label" for="gram">Gram:</label>
                            <input id="gram" name="gram" type="number" value="" required>
                        </p>
                        <p>
                            <label for="vind">&nbsp;</label>
                            <input id="vind" type="submit" value="Voeg product toe">
                        </p>
            </div>
    </form>
    </article>
    </article>
</main>
<%@include file="footer.jsp"%>
</body>
</html>
