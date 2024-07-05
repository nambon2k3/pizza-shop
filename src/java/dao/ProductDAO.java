/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;
import model.Supplier;
/**
 *
 * @author Legion
 */
public class ProductDAO extends DBContext{
    Connection connection;

    public ProductDAO() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            System.out.println("Connect Failed");
        }
    }
    
    // Method to add a new product
    public void addProduct(Product product) {
        String sql = "INSERT INTO Products (ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getSupplierID());
            statement.setInt(3, product.getCategoryID());
            statement.setInt(4, product.getQuantityPerUnit());
            statement.setDouble(5, product.getUnitPrice());
            statement.setString(6, product.getProductImage());
            
            statement.executeUpdate();
        }  catch (SQLException e) {
            System.out.println("addProduct: " + e.getMessage());
        }
    }

    // Method to update an existing product
    public void updateProduct(Product product){
        String sql = "UPDATE Products SET ProductName = ?, SupplierID = ?, CategoryID = ?, QuantityPerUnit = ?, " +
                     "UnitPrice = ?, ProductImage = ? WHERE ProductID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getSupplierID());
            statement.setInt(3, product.getCategoryID());
            statement.setInt(4, product.getQuantityPerUnit());
            statement.setDouble(5, product.getUnitPrice());
            statement.setString(6, product.getProductImage());
            statement.setInt(7, product.getProductID());
            
            statement.executeUpdate();
        }catch (SQLException e) {
            System.out.println("updateProduct: " + e.getMessage());
        }
    }
    
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted == 0) {
                throw new SQLException("Failed to delete product with ID: " + productId);
            }
        } catch (SQLException e) {
            System.out.println("deleteProduct: " + e.getMessage());
        }
    }
    
    public Category getCategoryByID(int categoryID) {
        Category category = null;
        String sql = "SELECT CategoryID, CategoryName, Description FROM Categories WHERE CategoryID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryID);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String categoryName = resultSet.getString("CategoryName");
                    String description = resultSet.getString("Description");
                    
                    category = new Category(categoryID, categoryName, description);
                }
            }
        } catch (SQLException e) {
            System.out.println("getCategoryByID: " + e.getMessage());
        }
        
        return category;
    }
    
    public Supplier getSupplierByID(int supplierID) {
        Supplier supplier = null;
        String sql = "SELECT SupplierID, CompanyName, Address, Phone FROM Suppliers WHERE SupplierID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, supplierID);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String companyName = resultSet.getString("CompanyName");
                    String address = resultSet.getString("Address");
                    String phone = resultSet.getString("Phone");
                    
                    supplier = new Supplier(supplierID, companyName, address, phone);
                }
            }
        } catch (SQLException e) {
            System.out.println("getSupplierByID: " + e.getMessage());
        }
        
        return supplier;
    }
    
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT CategoryID, CategoryName, Description FROM Categories";
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                int categoryID = resultSet.getInt("CategoryID");
                String categoryName = resultSet.getString("CategoryName");
                String description = resultSet.getString("Description");
                
                Category category = new Category(categoryID, categoryName, description);
                categories.add(category);
            }
        }catch (SQLException e) {
            System.out.println("getAllCategories: " + e.getMessage());
        }

        
        return categories;
    }
    
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT SupplierID, CompanyName, Address, Phone FROM Suppliers";
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                int supplierID = resultSet.getInt("SupplierID");
                String companyName = resultSet.getString("CompanyName");
                String address = resultSet.getString("Address");
                String phone = resultSet.getString("Phone");
                
                Supplier supplier = new Supplier(supplierID, companyName, address, phone);
                suppliers.add(supplier);
            }
        }catch (SQLException e) {
            System.out.println("getAllSuppliers: " + e.getMessage());
        }
        
        return suppliers;
    }
    
    
    
    public Product getProductByID(int productID) {
        Product product = null;

        try {
            String sql = "SELECT * FROM Products WHERE ProductID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, productID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String productName = resultSet.getString("ProductName");
                int supplierID = resultSet.getInt("SupplierID");
                int categoryID = resultSet.getInt("CategoryID");
                int quantityPerUnit = resultSet.getInt("QuantityPerUnit");
                double unitPrice = resultSet.getDouble("UnitPrice");
                String productImage = resultSet.getString("ProductImage");

                product = new Product(productID, productName, supplierID, categoryID, quantityPerUnit, unitPrice, productImage);
            }
        } catch (SQLException e) {
            System.out.println("getProductByID: " + e.getMessage());
        }

        return product;
    }
    
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try  {
            String sql = "SELECT * FROM Products";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                int supplierID = resultSet.getInt("SupplierID");
                int categoryID = resultSet.getInt("CategoryID");
                int quantityPerUnit = resultSet.getInt("QuantityPerUnit");
                double unitPrice = resultSet.getDouble("UnitPrice");
                String productImage = resultSet.getString("ProductImage");

                Product product = new Product(productID, productName, supplierID, categoryID, quantityPerUnit, unitPrice, productImage);
                products.add(product);
            }

        } catch (SQLException e) {
            System.out.println("getAllProducts: " + e.getMessage());
        }

        return products;
    }
}
