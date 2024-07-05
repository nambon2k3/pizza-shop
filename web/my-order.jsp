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
                        <tr>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Supplier ID</th>
                            <th>Category ID</th>
                            <th>Quantity Per Unit</th>
                            <th>Unit Price</th>
                            <th>Product Image</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach var="product" items="${sessionScope.order}">
                            <tr>
                                <td>${product.productID}</td>
                                <td>${product.productName}</td>
                                <td>${product.supplier.companyName}</td>
                                <td>${product.category.categoryName}</td>
                                <td>${product.quantityPerUnit}</td>
                                <td>${product.unitPrice}</td>
                                <td><img src="${product.productImage}" alt="${product.productName}" width="100"></td>
                                <td>
                                    <a href="remove-pizza?productID=${product.productID}" class="btn btn-danger">Remove</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${sessionScope.order.size() eq 0}">
                            <tr>
                                <td colspan="8" style="text-align: center">No product added</td>
                            </tr>
                        </c:if>
                    </table>
                    <c:if test="${sessionScope.order.size() ne 0}">
                        <div class="d-flex justify-content-end">
                            <form action="create-order">
                                <div class="form-group">
                                    <label>Address: </label>
                                    <input type="text" name="address" class="form-control">
                                </div>
                                <button class="btn btn-success">Create Order</button>
                            </form>
                        </div>
                    </c:if>

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
