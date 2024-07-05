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
import model.Category;
import model.Product;
import model.Supplier;

/**
 *
 * @author Legion
 */
@WebServlet(name = "MangeProductController", urlPatterns = {"/manage-product"})
public class MangeProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = new ProductDAO().getAllProducts();
        List<Supplier> suppliers = new ProductDAO().getAllSuppliers();
        List<Category> categories = new ProductDAO().getAllCategories();

        request.setAttribute("suppliers", suppliers);
        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        request.setAttribute("isSuccess", request.getParameter("isSuccess"));
        request.getRequestDispatcher("manage-product.jsp").forward(request, response);
    }

}
