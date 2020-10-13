<%@include file="headheader.jsp"%>
        <main>
            <h2>voeg een product toe</h2>
            <article class="container_form">
                <form method="post" action="ProductInfo?command=voegToe">
                    <p class="vul_in">Vul onderstaande alle gegevens in:</p>
                    <article id="form">
                        <div id="column_one">
                            <!--voorbeeld pas aan naar nodige informatie-->
                            <label for="naam">Naam:</label><br>
                            <input type="text" id="naam" name="naam" ><br>
                            <label for="beschrijving">Beschrijving:</label><br>
                            <input type="text" id="beschrijving" name="beschrijving"><br>

                        </div>
                        <div id="column_two">
                            <label for="calorieen">Calorieen:</label><br>
                            <input type="number" id="calorieen" name="calorieen"><br>
                            <label for="gram">Gram:</label><br>
                            <input type="number" id="gram" name="gram"><br>
                            <input class="verstuur" type="submit" value="VERSTUUR">
                        </div>
                    </article>
                </form>
            </article>
        </main>
<%@include file="footer.jsp"%>
    </body>
</html>
