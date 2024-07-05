<!-- manager.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <jsp:include page="navigation.jsp" />
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <jsp:include page="sidebar.jsp" />
                </div>
                <div class="col-md-10">
                    <h2>Product Management</h2>
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

                    <div class="d-flex justify-content-end mb-3">
                        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addProductModal">
                            Add new
                        </button>
                    </div>
                    <!-- Room List -->
                    <table id="table" class="table table-striped">
                        <thead>
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
                        </thead>
                        <tbody>
                            <%-- Iterate over the list of products from the servlet --%>
                            <c:forEach var="product" items="${products}">
                                <tr>
                                    <td>${product.productID}</td>
                                    <td>${product.productName}</td>
                                    <td>${product.supplier.companyName}</td>
                                    <td>${product.category.categoryName}</td>
                                    <td>${product.quantityPerUnit}</td>
                                    <td>${product.unitPrice}</td>
                                    <td><img src="${product.productImage}" style="width: 50px; height: 50px" alt="Product Image"></td>
                                    <td>
                                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#updateProductModal${product.productID}">
                                            Update
                                        </button>
                                        <!-- Update Product Modal -->
                                        <div class="modal fade" id="updateProductModal${product.productID}" tabindex="-1" role="dialog" aria-labelledby="updateProductModalLabel${product.productID}" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="updateProductModalLabel${product.productID}">Update Product</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form id="updateProductForm${product.productID}" action="product" method="post">
                                                            <input type="hidden" id="updateProductId${product.productID}" name="productId" value="${product.productID}">
                                                            <input type="hidden" name="action" value="update">
                                                            <div class="form-group">
                                                                <label for="updateProductName${product.productID}">Product Name</label>
                                                                <input type="text" class="form-control" id="updateProductName${product.productID}" name="productName" value="${product.productName}" required>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="supplierId">Supplier</label>
                                                                <select class="form-control" id="supplierId" name="supplierId" required>
                                                                    <option value="">Select Supplier</option>
                                                                    <%-- Populate options from servlet or use JSTL loop --%>
                                                                    <c:forEach var="supplier" items="${suppliers}">
                                                                        <option value="${supplier.supplierID}" ${supplier.supplierID == product.supplierID ? 'selected' : ''}>${supplier.companyName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>

                                                            <!-- Category Dropdown -->
                                                            <div class="form-group">
                                                                <label for="categoryId">Category</label>
                                                                <select class="form-control" id="categoryId" name="categoryId" required>
                                                                    <option value="">Select Category</option>
                                                                    <%-- Populate options from servlet or use JSTL loop --%>
                                                                    <c:forEach var="category" items="${categories}">
                                                                        <option value="${category.categoryID}" ${category.categoryID == product.categoryID ? 'selected' : ''}>${category.categoryName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="updateQuantityPerUnit${product.productID}">Quantity Per Unit</label>
                                                                <input type="number" class="form-control" id="updateQuantityPerUnit${product.productID}" name="quantityPerUnit" value="${product.quantityPerUnit}" required>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="updateUnitPrice${product.productID}">Unit Price</label>
                                                                <input type="number" step="0.01" class="form-control" id="updateUnitPrice${product.productID}" name="unitPrice" value="${product.unitPrice}" required>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="updateProductImage${product.productID}">Product Image URL</label>
                                                                <input type="text" class="form-control" id="updateProductImage${product.productID}" name="productImage" value="${product.productImage}">
                                                            </div>
                                                            <button type="submit" class="btn btn-primary">Update Product</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="product?productId=${product.productID}" class="btn btn-danger">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="addProductModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addProductModalLabel">Add New Product</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="addProduct" action="product" method="post">
                            <input type="hidden" name="action" value="add">
                            <div class="form-group">
                                <label for="updateProductName${product.productID}">Product Name</label>
                                <input type="text" class="form-control" id="updateProductName${product.productID}" name="productName"  required>
                            </div>
                            <div class="form-group">
                                <label for="supplierId">Supplier</label>
                                <select class="form-control" id="supplierId" name="supplierId" required>
                                    <option value="">Select Supplier</option>
                                    <%-- Populate options from servlet or use JSTL loop --%>
                                    <c:forEach var="supplier" items="${suppliers}">
                                        <option value="${supplier.supplierID}" ${supplier.supplierID == product.supplierID ? 'selected' : ''}>${supplier.companyName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Category Dropdown -->
                            <div class="form-group">
                                <label for="categoryId">Category</label>
                                <select class="form-control" id="categoryId" name="categoryId" required>
                                    <option value="">Select Category</option>
                                    <%-- Populate options from servlet or use JSTL loop --%>
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.categoryID}" ${category.categoryID == product.categoryID ? 'selected' : ''}>${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="updateQuantityPerUnit${product.productID}">Quantity Per Unit</label>
                                <input type="number" class="form-control" id="updateQuantityPerUnit${product.productID}" name="quantityPerUnit"  required>
                            </div>
                            <div class="form-group">
                                <label for="updateUnitPrice${product.productID}">Unit Price</label>
                                <input type="number" step="0.01" class="form-control" id="updateUnitPrice${product.productID}" name="unitPrice"  required>
                            </div>
                            <div class="form-group">
                                <label for="updateProductImage${product.productID}">Product Image URL</label>
                                <input type="text" class="form-control" id="updateProductImage${product.productID}" name="productImage" required>
                            </div>

                            <button type="submit" class="btn btn-primary">Add Product</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>

        <script>
                                $(document).ready(function () {
                                    $('#table').DataTable({
                                        "pageLength": 5,
                                        "lengthChange": false
                                    });
                                });
        </script>
    </body>
</html>
