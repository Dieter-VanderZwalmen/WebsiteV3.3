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


}
