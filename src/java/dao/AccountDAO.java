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
import model.Account;
import model.Customer;
/**
 *
 * @author Legion
 */
public class AccountDAO extends DBContext{

    Connection connection;

    public AccountDAO() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            System.out.println("Connect Failed");
        }
    }
    
    public Account getUserByCredentials(String userName, String password) {
        Account account = null;
        try {
            String sql = "SELECT * FROM Account WHERE UserName = ? AND Password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int accountID = resultSet.getInt("AccountID");
                String fullName = resultSet.getString("FullName");
                String type = resultSet.getString("Type");

                account = new Account(accountID, userName, password, fullName, type);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("getUserByCredentials: " + e.getMessage());
        }

        return account;
    }
    
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT CustomerID, Password, ContactName, Address, Phone FROM Customers";
        
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                int customerId = resultSet.getInt("CustomerID");
                String password = resultSet.getString("Password");
                String contactName = resultSet.getString("ContactName");
                String address = resultSet.getString("Address");
                String phone = resultSet.getString("Phone");
                
                Customer customer = new Customer(customerId, password, contactName, address, phone);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("getAllCustomers: " + e.getMessage());
        }
        
        return customers;
    }
    
    // Method to add a new customer
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customers (Password, ContactName, Address, Phone) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getPassword());
            statement.setString(2, customer.getContactName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhone());
            
            statement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("addCustomer: " + e.getMessage());
        }
        
    }

    // Method to update an existing customer
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE Customers SET Password = ?, ContactName = ?, Address = ?, Phone = ? WHERE CustomerID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getPassword());
            statement.setString(2, customer.getContactName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhone());
            statement.setInt(5, customer.getCustomerID());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateCustomer: " + e.getMessage());
        }
    }

    // Method to delete a customer by ID
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM Customers WHERE CustomerID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted == 0) {
                throw new SQLException("Failed to delete customer with ID: " + customerId);
            }
        } catch (SQLException e) {
            System.out.println("deleteCustomer: " + e.getMessage());
        }
    }

    
}
