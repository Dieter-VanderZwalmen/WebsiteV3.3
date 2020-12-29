package view.controller;

import com.sun.net.httpserver.HttpsConfigurator;
import domain.db.ProductDB;
import domain.model.Product;
import jdk.swing.interop.SwingInterOpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ProductInfo")
public class Servlet extends HttpServlet {
    private final ProductDB x = new ProductDB();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proccesRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proccesRequest(request, response);
    }


    private void proccesRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination;
        String command = request.getParameter("command");
        if (command == null)
            command = "";
        switch (command) {
            case "verwijder":
                destination = verwijder();
                break;
            case "verwijderVerlanglijst":
                destination = verwijderVerlanglijst(request);
                break;
            case "verwijderBevestig":
                destination = verwijderBevestig(request, response);
                break;
            case "overview":
                destination = overzicht(request, response);//locatie == Cookie locatie standaard als beide ingesteld
                break;
            case "voegToe":
                destination = voegToe(request, response);
                break;
            case "zoek":
                destination = zoek(request);
                break;
            case "setCookieLocatie":
                destination = setCookieLocatie(request, response);
                break;

            case "verlanglijst":
                destination = voegToeVerlanglijst(request, response);
                break;
            case "update":
                destination = Update(request);
                break;
            case "geupdate":
                destination = geupdate(request, response);
                break;

            case "setCookieLocatieV":
                destination = setVerlanglijstCookieLocatie(request, response);
                break;

            case "overviewV":
                destination = overzichtV(request, response);
                break;

            default:
                destination = "index.jsp";

        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String overzichtV(HttpServletRequest request, HttpServletResponse response) {
        Cookie verlanglijstCookie = getCookieWithKey(request, "verlanglijstCookie");
        String c = verlanglijstCookie == null ? "beide" : verlanglijstCookie.getValue();
        return overzichtV(request, response, c);
    }

    private String overzichtV(HttpServletRequest request, HttpServletResponse response, String verlanglijstCookie) {
        HttpSession session = request.getSession();
        List<Product> lijst = (List<Product>) session.getAttribute("lijst");
        ArrayList<Product> lijstLocatie = new ArrayList<>();
        if (!(lijst == null || lijst.isEmpty())) {
            for (Product l : lijst) {
                if (l.getLocatie().equalsIgnoreCase(verlanglijstCookie) || l.getLocatie().equalsIgnoreCase("beide")) {
                    lijstLocatie.add(l);
                }
            }
        }
        request.setAttribute("lijst", lijstLocatie);
        return "verlanglijst.jsp";
    }

    private String setVerlanglijstCookieLocatie(HttpServletRequest request, HttpServletResponse response) {
        Cookie verlanglijstCookie = new Cookie("verlanglijstCookie", request.getParameter("verlanglijstCookieLocatie"));
        response.addCookie(verlanglijstCookie);
        return overzichtV(request, response, request.getParameter("cookieLocatie"));
    }

    /*#######################SESSIONS */
    private String voegToeVerlanglijst(HttpServletRequest request, HttpServletResponse reponse) {


        Product product = x.findProduct(request.getParameter("naam"));//geeft null terug indien niet gevonden

        HttpSession session = request.getSession(); //altijd nieuwe session aanmaken kan voor problemen zorgen of haalt het elke keer de zelfde session boven waardoor het niet uitmaakt?

        List<Product> verlanglijst = (List<Product>) session.getAttribute("lijst");
        if (verlanglijst == null) {
            verlanglijst = new ArrayList<>();
        }
        if (!verlanglijst.contains(product)) {
            verlanglijst.add(product);
        }
        session.setAttribute("lijst", verlanglijst);
        return overzicht(request, reponse);
        //return "verlanglijst.jsp";// kan allebei
    }


    private String verwijderVerlanglijst(HttpServletRequest request) {
        HttpSession session = request.getSession();

        ArrayList<Product> verlanglijst = (ArrayList<Product>) session.getAttribute("lijst");

        if (verlanglijst != null) {
            verlanglijst.remove(x.findProduct(request.getParameter("naam")));
            session.setAttribute("lijst", verlanglijst);
            return "verlanglijst.jsp";
        }
        return "debug.jsp"; //pagina om fout opsporing te doen indien je op debug.jsp komt is er iets mis
    }

    /*#######################*/

    private String verwijderBevestig(HttpServletRequest request, HttpServletResponse reponse) {
        String naam = request.getParameter("naam");

        x.removeProduct(x.findProduct(naam));
        return overzicht(request, reponse);
    }

    private String Update(HttpServletRequest request) {
        Product product = x.findProduct(request.getParameter("naam"));
        request.setAttribute("product", product);
        return "update.jsp";
    }

    private String geupdate(HttpServletRequest request, HttpServletResponse reponse) {//veel dubbele code niet perfect
        ArrayList<String> errors = new ArrayList<>();//schrijf alle errors hierinweg
        Product product = x.findProduct(request.getParameter("naam"));//nieuw instantie v product
        //setters
        setBeschrijving(product, request, errors);
        setCalorieeen(product, request, errors);
        setEenheid(product, request, errors);
        setGram(product, request, errors);
        setLocatie(product, request, errors);


        if (errors.size() == 0) {
            //indien de meegegeven waardes niet juist zijn
            try {
                x.updateProduct(product, x.findProduct(request.getParameter("naam")));
                return overzicht(request, reponse);
            } catch (IllegalArgumentException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "update.jsp";

    }

    private String verwijder() {
        //request.setAttribute("naam",request.getParameter("naam"));
        return "verwijderBevestig.jsp";
    }

    private String zoek(HttpServletRequest request) {
        String naamFP = request.getParameter("naam");

        String destination = "nietGevonden.jsp";
        if (x.findProduct(naamFP) != null) {
            try {

                Product eenProduct = x.findProduct(naamFP);
                String productNaarString = eenProduct.toString();

                destination = "gevonden.jsp";
                request.setAttribute("product", x);
                //request.setAttribute("naamFP",naamFP);
                request.setAttribute("productNaarString", productNaarString);
            } catch (NullPointerException e) { //Nullpointer execption toevoegen wanneer je zoekt naar een product die niet bestaat?
                return destination;         // juiste implementatie? Hoe werkt Try catch juist? waarvoor gaan we e gebruiken naast e.getmessage()
            }
        }
        return destination;
    }


    private String overzicht(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookieWithKey(request, "Locatie") != null ? getCookieWithKey(request, "Locatie") : new Cookie("Locatie", "Beide");
        String c = getCookieWithKey(request, "Locatie") == null ? "beide" : cookie.getValue();
        return overzicht(request, response, c);
    }

    private String overzicht(HttpServletRequest request, HttpServletResponse response, String cookieLocatie) {
        request.setAttribute("teller", x.getAantalProducten(cookieLocatie));
        request.setAttribute("producten", x.getProductsLocatie(cookieLocatie));
        return "Overzicht.jsp";
    }

    private String setCookieLocatie(HttpServletRequest request, HttpServletResponse response) {

        Cookie locatieCookie = new Cookie("Locatie", request.getParameter("cookieLocatie"));
        response.addCookie(locatieCookie);
        return overzicht(request, response, request.getParameter("cookieLocatie"));

    }


    //###########################################################################################
    private String voegToe(HttpServletRequest request, HttpServletResponse reponse) {
        ArrayList<String> errors = new ArrayList<>();//schrijf alle errors hierinweg
        Product product = new Product();//nieuw instantie v product
        //setters
        setNaam(product, request, errors);
        setBeschrijving(product, request, errors);
        setCalorieeen(product, request, errors);
        setEenheid(product, request, errors);
        setGram(product, request, errors);
        setLocatie(product, request, errors);


        if (errors.size() == 0) {
            //indien de meegegeven waardes niet juist zijn
            try {

                if (x.addProduct(product)) {
                    x.addProduct(product);
                    return overzicht(request, reponse);
                } else {
                    errors.add("Dit product bestaat al");
                }
            } catch (IllegalArgumentException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "productForm.jsp";

    }

    private void setNaam(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String naam = request.getParameter("naam");

        try {
            product.setNaam(naam);
            request.setAttribute("naamVorigeWaarde", naam);//bewaren indien nodig
        } catch (IllegalArgumentException ecx) {
            errors.add(ecx.getMessage());
        }
    }

    private void setBeschrijving(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String beschrijving = request.getParameter("beschrijving");
        try {
            product.setBeschrijving(beschrijving);
            request.setAttribute("beschrijvingVorigeWaarde", beschrijving);
        } catch (IllegalArgumentException ecx) {
            errors.add(ecx.getMessage());
        }

    }

    private void setCalorieeen(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String calorieeen = request.getParameter("calorieen");
        try {
            product.setCalorieen(Integer.parseInt(calorieeen));
            request.setAttribute("calorieeenVorigeWaarde", calorieeen);
        } catch (NumberFormatException exc) {
            errors.add("Calorieen moet een getal boven 0 zijn.ServletError");
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setEenheid(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String eenheid = request.getParameter("eenheid");
        try {
            product.setEenheid(eenheid);
            request.setAttribute("eenheidVorigeWaarde", eenheid);
        } catch (IllegalArgumentException ecx) {
            errors.add(ecx.getMessage());
        }
    }

    private void setGram(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String gram = request.getParameter("gram");
        try {
            product.setGram(Integer.parseInt(gram));
            request.setAttribute("gramVorigeWaarde", gram);
        } catch (NumberFormatException ecx) {
            errors.add("Gram moet een getal boven 0 zijn.");
        } catch (IllegalArgumentException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setLocatie(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String locatie = request.getParameter("locatie");

        try {
            product.setLocatie(locatie);
            request.setAttribute("locatieVorigeWaarde", locatie);
        } catch (NullPointerException exc) {//indien niets?
            errors.add(exc.getMessage());
        }

    }


    //overloop al je cookies
    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies
        ) {
            if (cookie.getName().equals(key))
                return cookie;
        }
        return null;
    }


//###########################################################################################
    /*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String naam = request.getParameter("naam");
        String beschrijving = request.getParameter("beschrijving");
        int calorieen = Integer.parseInt(request.getParameter("calorieen"));
        String eenheid = request.getParameter("eenheid") ;
        int gram=Integer.parseInt(request.getParameter("gram"));
        Product product = new Product(naam,beschrijving,calorieen,eenheid,gram);
        x.addProduct(product);

        request.setAttribute("producten",x.getProducts());

        String destination = "Overzicht.jsp";
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String naamFP = request.getParameter("naam");

        String destination = "nietGevonden.jsp";
        if(x.findProduct(naamFP)!= null){
            destination = "gevonden.jsp";
            request.setAttribute("product",x);
        }
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request,response);

    }
    */

}
