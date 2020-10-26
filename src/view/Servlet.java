package view;

import domain.db.ProductDB;
import domain.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        request.setAttribute("naam",request.getParameter("naam"));
        return "verwijderBevestig.jsp";
    }

    private String zoek(HttpServletRequest request) {
        String naamFP = request.getParameter("naam");

        String destination = "nietGevonden.jsp";
        if(x.findProduct(naamFP)!= null){
            destination = "gevonden.jsp";
            request.setAttribute("product",x);
            request.setAttribute("naam",naamFP);
        }
        return destination;
    }


    private String overzicht(HttpServletRequest request) {
        request.setAttribute("producten", x.getProducts());
        return "Overzicht.jsp";
    }


    private String voegToe(HttpServletRequest request) {
        //indien leeg
        String destination = "voegToe.jsp";

        //else

        String naam = request.getParameter("naam");
        String beschrijving = request.getParameter("beschrijving");
        int calorieen = Integer.parseInt(request.getParameter("calorieen"));
        String eenheid = request.getParameter("eenheid") ;
        int gram=Integer.parseInt(request.getParameter("gram"));

        Product product = new Product(naam,beschrijving,calorieen,eenheid,gram);
        x.addProduct(product);
        destination = overzicht(request);

        return destination;
    }

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
