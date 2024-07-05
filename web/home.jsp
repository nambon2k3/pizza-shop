<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Room List</title>
        <!-- Include Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .pagination {
                justify-content: center;
                margin-top: 20px;
            }
            .card {
                margin-bottom: 20px;
            }
        </style>

    </head>
    <body>

        <jsp:include page="header.jsp"/>
        <!-- Banner Section -->
        <section class="banner">
            <div class="container" style="color: white; background-color: rgba(0, 0, 0, .5); padding: 80px 0">
                <h1>Welcome to Our Website</h1>
                <p class="lead">Explore our pizzas and services.</p>
                <!-- Add more content or actions as needed -->
            </div>
        </section>
        <div class="container">
            <h1 class="my-4">Pizza List</h1>
            <c:if test="${isSuccess ne null && isSuccess}">
                <div class="alert alert-success alert-dismissible fade show mt-2" role="alert" id="mess">
                    <strong>Add order success!</strong> You should check in tab order.
                </div>
            </c:if>
            <c:if test="${isSuccess ne null && !isSuccess}">
                <div class="alert alert-danger alert-dismissible fade show mt-2" role="alert" id="mess">
                    <strong>Add order failed!</strong> You should check your network.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <!-- Filter Form -->
            <form action="home" method="get">
                <div class="form-row">
                    <div class="col-md-3 mb-3">
                        <label for="minPrice">Search</label>
                        <input type="text" class="form-control" id="minPrice" name="search" placeholder="Search..." value="${param.minPrice}">
                    </div>
                    <div class="col-md-2 mb-3 ">
                        <label>&nbsp;</label><br>
                        <button type="submit" class="btn btn-primary btn-block">Search</button>
                    </div>
                </div>
            </form>

            <!-- Room List -->
            <div class="row">
                
                <c:forEach var="product" items="${products}">
                    <div class="col-lg-4 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <img class="card-img-top" src="${product.productImage}" alt="..." />
                                <h5 class="card-title">${product.productName}</h5>
                                <p class="card-text">An amazing pizza for a new day!</p>
                                <p class="card-text"><strong>Price:</strong> $${product.unitPrice}</p>
                            </div>
                            <div class="d-flex justify-content-center p-3">
                                <a class="btn btn-primary" href="add-pizza?productID=${product.productID}">Add Pizza</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="footer.html"/>
        <!-- Include Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
