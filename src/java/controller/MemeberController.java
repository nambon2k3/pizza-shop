/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author Legion
 */
@WebServlet(name = "MemeberController", urlPatterns = {"/memeber"})
public class MemeberController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        deleteCustomer(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                insertCustomer(request, response);
                break;
            case "update":
                updateCustomer(request, response);
                break;

        }
    }
    
     private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        new AccountDAO().deleteCustomer(id);
        response.sendRedirect("manage-member?isSuccess=true");
    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String password = request.getParameter("password");
        String contactName = request.getParameter("contactName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        Customer newCustomer = new Customer(1, password, contactName, address, phone);
        new AccountDAO().addCustomer(newCustomer);
        response.sendRedirect("manage-member?isSuccess=true");
    }
    
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("password");
        String contactName = request.getParameter("contactName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        Customer customer = new Customer(id, password, contactName, address, phone);
        new AccountDAO().updateCustomer(customer);
        response.sendRedirect("manage-member?isSuccess=true");
    }

}
