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
@WebServlet(name="RemovePizzaController", urlPatterns={"/remove-pizza"})
public class RemovePizzaController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Product> listProduct = (List<Product>) request.getSession().getAttribute("order");
        Product product = new ProductDAO().getProductByID(Integer.parseInt(request.getParameter("productID")));
        for (Product p : listProduct) {
            if(p.getProductID() == product.getProductID()) {
                listProduct.remove(p);
            }
        }
        request.getSession().setAttribute("order", listProduct);
        response.sendRedirect("home?isSuccess=true");
    } 


}
