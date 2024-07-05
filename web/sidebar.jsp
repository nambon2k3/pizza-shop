<%-- 
    Document   : sidebar
    Created on : Jul 4, 2024, 5:30:15 PM
    Author     : Legion
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .sidebar {
                width: 200px;
                position: fixed;
                top: 0;
                left: 0;
                height: 100%;
                background-color: #f8f9fa;
                padding-top: 20px;
            }
            .sidebar .nav-link {
                color: #333;
            }
        </style>
    </head>
    <body>
        <!-- sidebar.jsp -->
        <div class="sidebar">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link" href="manage-product">Product Management</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="manage-member">Member management</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="order">Order management</a>
                </li>
            </ul>
        </div>  
    </body>
</html>
