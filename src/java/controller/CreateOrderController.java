/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.OrderDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author Legion
 */
@WebServlet(name="CreateOrderController", urlPatterns={"/create-order"})
public class CreateOrderController extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Product> listProduct = (List<Product>) request.getSession().getAttribute("order");
        Account account = (Account) request.getSession().getAttribute("Account");
        double feight = 0;
        for (Product product : listProduct) {
            feight += product.getUnitPrice() * product.getQuantityPerUnit();
        }
        int orderID = new OrderDAO().createOrder(new Order(1, account.getAccountID(), feight, request.getParameter("address")));
        for (Product product : listProduct) {
            new OrderDAO().createOrderDetail(new OrderDetail(orderID, product.getProductID(), product.getUnitPrice(), product.getQuantityPerUnit()));
        }
        listProduct.clear();
        request.getSession().setAttribute("order", listProduct);
        response.sendRedirect("my-order.jsp");
    } 

}
