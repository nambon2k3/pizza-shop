<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Room Detail</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <!-- Custom styles -->
        <style>
            .thumbnail-images img {
                cursor: pointer;
                max-height: 100px;
            }
            .main-image {
                width: 100%;
                max-height: 400px;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <c:if test="${isSuccess ne null && isSuccess}">
            <div class="alert alert-success alert-dismissible fade show mt-2" role="alert" id="mess">
                <strong>Update order success!</strong> You should check in on some of those fields below.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" onclick="document.getElementById('mess').style.display = 'none'"></button>
            </div>
        </c:if>
        <c:if test="${isSuccess ne null && !isSuccess}">
            <div class="alert alert-danger alert-dismissible fade show mt-2" role="alert" id="mess">
                <strong>Update order failed!</strong> You should check your network.
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <!-- Main Content Section -->
        <div class="container mt-4">
            <h1 class="my-4">${room.roomNumber}</h1>
            <div class="row">
                <div class="container mt-5">
                    <h2> Current Order</h2>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Customer ID</th>
                                <th>Order Date</th>
                                <th>Required Date</th>
                                <th>Shipped Date</th>   
                                <th>Freight</th>
                                <th>Ship Address</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%-- Iterate over the list of orders from the servlet --%>
                            <c:forEach var="order" items="${orders}">
                                <tr>
                                    <td>${order.orderID}</td>
                                    <td>${order.customerID}</td>
                                    <td>${order.orderDate}</td>
                                    <td>${order.requiredDate}</td>
                                    <td>${order.shippedDate}</td>
                                    <td>${order.freight}</td>
                                    <td>${order.shipAddress}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

        <!-- Bootstrap JS (Optional for some components) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-5zvFH0C2F/pDnlzn/+OPOZsOL28NY2be6t/jrA6WWpSj8bc46l07Fg0S5oJPr5fZ" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+YT3EIQCE4NCptea7G4lB9Bq4Q+qwuSAIf2" crossorigin="anonymous"></script>
        <script>
                    // JavaScript to handle thumbnail click and update main image
                    $(document).ready(function () {
                        $('.thumbnail-images img').click(function () {
                            var mainImageSrc = $(this).attr('src');
                            $('#mainImage').attr('src', mainImageSrc);
                        });
                    });
        </script>
    </body>
</html>
