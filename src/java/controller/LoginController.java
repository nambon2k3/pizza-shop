/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Product;

/**
 *
 * @author Legion
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Retrieve user from database based on username
        Account account = new AccountDAO().getUserByCredentials(username, password);
        
        List<Product> listProduct = new ArrayList<>();
        
        if (account != null ) {
            request.getSession().setAttribute("account", account);
            request.getSession().setAttribute("order", listProduct);
            if (account.getType().equalsIgnoreCase("user")) {
                response.sendRedirect("home");

            } else {
                response.sendRedirect("manage-product");
            }

        } else {
            // Authentication failed, redirect back to login page with appropriate error message
            if (account == null) {
                // Username not found
                request.setAttribute("error", "username");
            } else {
                // Incorrect password
                request.setAttribute("error", "password");
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }


}
