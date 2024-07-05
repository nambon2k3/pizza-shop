/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;

/**
 *
 * @author Legion
 */
@WebServlet(name = "AddPizzaController", urlPatterns = {"/add-pizza"})
public class AddPizzaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> listProduct = (List<Product>) request.getSession().getAttribute("order");
        Product product = new ProductDAO().getProductByID(Integer.parseInt(request.getParameter("productID")));
        product.setQuantityPerUnit(1);
        boolean isExisted = false;
        for (Product p : listProduct) {
            if(p.getProductID() == product.getProductID()) {
                isExisted=true;
                p.setQuantityPerUnit(p.getQuantityPerUnit() + 1);
            }
        }
        if(!isExisted) listProduct.add(product);
        
        request.getSession().setAttribute("order", listProduct);
        response.sendRedirect("home?isSuccess=true");
    }
}
