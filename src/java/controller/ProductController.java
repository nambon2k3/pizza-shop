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
import model.Product;

/**
 *
 * @author Legion
 */
@WebServlet(name = "ProductController", urlPatterns = {"/product"})
public class ProductController extends HttpServlet {

    private ProductDAO productDAO; // Assuming you have ProductDAO initialized

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        deleteProduct(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
        }
    }


    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form data
        String productName = request.getParameter("productName");
        int supplierId = Integer.parseInt(request.getParameter("supplierId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int quantityPerUnit = Integer.parseInt(request.getParameter("quantityPerUnit"));
        double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
        String productImage = request.getParameter("productImage");

        // Create Product object
        Product newProduct = new Product(1, productName, supplierId, categoryId, quantityPerUnit, unitPrice, productImage);

        // Insert product into database
        productDAO.addProduct(newProduct);

        // Redirect to product listing page
        response.sendRedirect("manage-product?isSuccess=true");

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form data
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        int supplierId = Integer.parseInt(request.getParameter("supplierId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int quantityPerUnit = Integer.parseInt(request.getParameter("quantityPerUnit"));
        double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
        String productImage = request.getParameter("productImage");

        // Create Product object
        Product updatedProduct = new Product(productId, productName, supplierId, categoryId, quantityPerUnit, unitPrice, productImage);

        // Update product in database
        productDAO.updateProduct(updatedProduct);

        // Redirect to product listing page
        response.sendRedirect("manage-product?isSuccess=true");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));

        // Delete product from database
        productDAO.deleteProduct(productId);

        // Redirect to product listing page
        response.sendRedirect("manage-product?isSuccess=true");
    }

}
