<body>
<header>
    <article id="header_img_logo">
        <img id="logo" src="img/logo.png" alt="logo">
    </article>
    <nav>
        <ul>

            <li class="unlist" ${param.active eq "index" ? "id = activeItem" : ""}>
                <a href="index.jsp">Home</a></li>

            <li class="unlist" ${param.active eq "voegToe" ? "id = activeItem" : ""}>
                <a href="productForm.jsp">Voeg toe</a></li>

            <li class="unlist" ${param.active eq "Overzicht" ? "id = activeItem" : ""}>
                <a href="ProductInfo?command=overview">Overzicht</a></li>

            <li class="unlist" ${param.active eq "zoek" ? "id = activeItem" : ""}>
                <a href="zoek.jsp">Zoek</a></li>

            <li class="unlist" ${param.active eq "zoek" ? "id = activeItem" : ""}>
                <a href="ProductInfo?command=overviewV" class="fa fa-heart" aria-hidden="true"></a></li>


        </ul>
    </nav>
</header>
