package view.controller;

import domain.db.ProductDB;
import domain.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ProductInfo")
public class Servlet extends HttpServlet {
    private ProductDB x = new ProductDB();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proccesRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proccesRequest(request, response);
    }


    private void proccesRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String destination = "index.jsp";
        String command = request.getParameter("command");
        if (command == null)
            command= "";
        switch (command) {
            case "verwijder":
                destination = verwijder(request);
                break;
            case "verwijderBevestig":
                destination = verwijderBevestig(request);
                break;
            case "overview":
                destination = overzicht(request);
                break;
            case "voegToe":
                destination = voegToe(request);
                break;
            case "zoek":
                destination = zoek(request);
                break;
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String verwijderBevestig(HttpServletRequest request) {
            String naam= request.getParameter("naam");

            x.removeProduct(x.findProduct(naam));
            return overzicht(request);
        }
    private String verwijder(HttpServletRequest request) {
        //request.setAttribute("naam",request.getParameter("naam"));
        return "verwijderBevestig.jsp";
    }

    private String zoek(HttpServletRequest request) {
        String naamFP = request.getParameter("naam");

        String destination = "nietGevonden.jsp";
        if(x.findProduct(naamFP)!= null){
            try{

                Product eenProduct = x.findProduct(naamFP);
                String productNaarString = eenProduct.toString();

                destination = "gevonden.jsp";
                request.setAttribute("product",x);
                //request.setAttribute("naamFP",naamFP);
                request.setAttribute("productNaarString",productNaarString);
            }catch(NullPointerException e){ //Nullpointer execption toevoegen wanneer je zoekt naar een product die niet bestaat?
                return destination;         // juiste implementatie? Hoe werkt Try catch juist? waarvoor gaan we e gebruiken naast e.getmessage()
            }
        }
        return destination;
    }


    private String overzicht(HttpServletRequest request) {
        int teller = x.getAantalProducten();
        request.setAttribute("teller",teller);

        request.setAttribute("producten", x.getProducts());
        return "Overzicht.jsp";
    }

//###########################################################################################
    private String voegToe(HttpServletRequest request) {
        ArrayList<String> errors = new ArrayList<>();//schrijf alle errors hierinweg
        Product product = new Product();//nieuw instantie v product
        //setters
        setNaam(product,request,errors);
        setBeschrijving(product,request,errors);
        setCalorieeen(product,request,errors);
        setEenheid(product,request,errors);
        setGram(product,request,errors);


        if(errors.size() == 0){
            //indien de meegegeven waardes niet juist zijn?
            try{
                x.addProduct(product);
                return overzicht(request);//overzicht(request,response) wordt als voorbeeld oplossing gegeven is de "response" nodig?
            }catch(IllegalArgumentException exc){
                errors.add(exc.getMessage());
            }
        }
            request.setAttribute("errors",errors);
            return "productForm.jsp";

    }

    private void setNaam(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String naam = request.getParameter("naam");

        try{
            product.setNaam(naam);
            request.setAttribute("naamVorigeWaarde",naam);//bewaren indien nodig
        }catch (IllegalArgumentException ecx){
            errors.add(ecx.getMessage());
        }
    }

    private void setBeschrijving(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String beschrijving = request.getParameter("beschrijving");
        try{
            product.setBeschrijving(beschrijving);
            request.setAttribute("beschrijvingVorigeWaarde",beschrijving);
        }catch(IllegalArgumentException ecx){
            errors.add(ecx.getMessage());
        }

    }
    private void setCalorieeen(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String calorieeen = request.getParameter("calorieen");
        try{
            product.setCalorieen(Integer.parseInt(calorieeen));
            request.setAttribute("calorieeenVorigeWaarde",calorieeen);
        }catch(NumberFormatException exc){
            errors.add("Calorieen moet een getal boven 0 zijn.");
        }catch(IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }
    private void setEenheid(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String eenheid = request.getParameter("eenheid");
        try{
            product.setEenheid(eenheid);
            request.setAttribute("eenheidVorigeWaarde",eenheid);
        }catch(IllegalArgumentException ecx){
            errors.add(ecx.getMessage());
        }
    }
    private void setGram(Product product, HttpServletRequest request, ArrayList<String> errors) {
        String gram = request.getParameter("gram");
        try{
            product.setCalorieen(Integer.parseInt(gram));
            request.setAttribute("gramVorigeWaarde",gram);
        }catch(NumberFormatException ecx){
            errors.add("Gram moet een getal boven 0 zijn.");
        }catch(IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
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
