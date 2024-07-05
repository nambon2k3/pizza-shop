/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderDetail;
/**
 *
 * @author Legion
 */
public class OrderDAO extends  DBContext{
    
    Connection connection;

    public OrderDAO() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            System.out.println("Connect Failed");
        }
    }
    
    public void createOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO OrderDetails (OrderID, ProductID, UnitPrice, Quantity) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, orderDetail.getOrderID());
            statement.setInt(2, orderDetail.getProductID());
            statement.setDouble(3, orderDetail.getUnitPrice());
            statement.setInt(4, orderDetail.getQuantity());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("createOrderDetail: " + e.getMessage());
        }
    }
    
    

    public int createOrder(Order order) {
        int orderId = 0; // Initialize orderId
        
        // Your SQL query to insert new order
        String sql = "INSERT INTO Orders (CustomerID, OrderDate, RequiredDate, ShippedDate, Freight, ShipAddress) " +
                     "VALUES (?, getDate(), getDate(), null, ?, ?)";
        
        // Try-with-resources to automatically close resources
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            // Set parameters for the insert statement
            pstmt.setInt(1, order.getCustomerID());
            pstmt.setDouble(2, order.getFreight());
            pstmt.setString(3, order.getShipAddress());
            
            // Execute the insert statement
            int rowsAffected = pstmt.executeUpdate();
            
            // Retrieve the generated keys (order ID)
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1); // Retrieve the generated order ID
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("createOrder: " + ex.getMessage());
        }
        
        return orderId; // Return the generated order ID
    }
    
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();

        // SQL query to retrieve orders for a specific customer ID
        String sql = "SELECT OrderID, CustomerID, OrderDate, RequiredDate, ShippedDate, Freight, ShipAddress " +
                     "FROM Orders " +
                     "WHERE CustomerID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set the customer ID parameter for the query
            pstmt.setInt(1, customerId);

            // Execute the query
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Retrieve order details from the result set
                    int orderId = rs.getInt("OrderID");
                    Date orderDate = rs.getDate("OrderDate");
                    Date requiredDate = rs.getDate("RequiredDate");
                    Date shippedDate = rs.getDate("ShippedDate");
                    double freight = rs.getDouble("Freight");
                    String shipAddress = rs.getString("ShipAddress");

                    // Create an Order object
                    Order order = new Order(orderId, customerId, orderDate, requiredDate, shippedDate, freight, shipAddress);

                    // Add the order to the list
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exceptions as needed
        }

        return orders;
    }
    
    
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();

        // SQL query to retrieve orders for a specific customer ID
        String sql = "SELECT OrderID, CustomerID, OrderDate, RequiredDate, ShippedDate, Freight, ShipAddress " +
                     "FROM Orders ";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Execute the query
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Retrieve order details from the result set
                    int orderId = rs.getInt("OrderID");
                    Date orderDate = rs.getDate("OrderDate");
                    Date requiredDate = rs.getDate("RequiredDate");
                    Date shippedDate = rs.getDate("ShippedDate");
                    double freight = rs.getDouble("Freight");
                    String shipAddress = rs.getString("ShipAddress");

                    // Create an Order object
                    Order order = new Order(orderId, rs.getInt("CustomerID"), orderDate, requiredDate, shippedDate, freight, shipAddress);

                    // Add the order to the list
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exceptions as needed
        }

        return orders;
    }
    
    public void deleteOrderByID(int orderId) {
        String sql = "DELETE FROM Orders WHERE OrderID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            
            statement.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println("deleteOrderByID: " + ex.getMessage());
        }

    }
}
