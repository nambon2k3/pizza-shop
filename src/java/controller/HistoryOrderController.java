/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;
import model.Order;

/**
 *
 * @author Legion
 */
@WebServlet(name="HistoryOrderController", urlPatterns={"/history-order"})
public class HistoryOrderController extends HttpServlet {
   
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        List<Order> customerOrders = new OrderDAO().getOrdersByCustomerId(account.getAccountID());
        request.setAttribute("orders", customerOrders);
        request.getRequestDispatcher("history-order.jsp").forward(request, response);
    } 

    

}
