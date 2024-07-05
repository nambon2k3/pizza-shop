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
                    <h2>Member Management</h2>
                    <c:if test="${isSuccess ne null && isSuccess}">
                        <div class="alert alert-success alert-dismissible fade show mt-2" role="alert" id="mess">
                            <strong>Update member success!</strong> You should check in on some of those fields below.
                        </div>
                    </c:if>
                    <c:if test="${isSuccess ne null && !isSuccess}">
                        <div class="alert alert-danger alert-dismissible fade show mt-2" role="alert" id="mess">
                            <strong>Update member failed!</strong> You should check your network.
                        </div>
                    </c:if>

                    <div class="d-flex justify-content-end mb-3">
                        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addCustomerModal">
                            Add Customer
                        </button>
                    </div>
                    <!-- Room List -->
                    <table id="table" class="table table-striped">
                        <thead>
                            <tr>
                                <th>CustomerID</th>
                                <th>Password</th>
                                <th>ContactName</th>
                                <th>Address</th>
                                <th>Phone</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="customer" items="${listCustomer}">
                                <tr>
                                    <td>${customer.customerID}</td>
                                    <td>${customer.password}</td>
                                    <td>${customer.contactName}</td>
                                    <td>${customer.address}</td>
                                    <td>${customer.phone}</td>
                                    <td>
                                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editCustomerModal-${customer.customerID}">
                                            Edit
                                        </button>
                                        <a href="memeber?id=${customer.customerID}" class="btn btn-danger">Delete</a>
                                    </td>
                                </tr>
                            <div class="modal fade" id="editCustomerModal-${customer.customerID}" tabindex="-1" role="dialog" aria-labelledby="editCustomerModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="editCustomerModalLabel">Edit Customer</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="memeber" method="post">
                                                <input type="hidden" name="id" value="${customer.customerID}">
                                                <input type="hidden" name="action" value="update">
                                                <div class="form-group">
                                                    <label>Password</label>
                                                    <input type="text" name="password" value="${customer.password}" class="form-control">
                                                </div>
                                                <div class="form-group">
                                                    <label>Contact Name</label>
                                                    <input type="text" name="contactName" value="${customer.contactName}" class="form-control">
                                                </div>
                                                <div class="form-group">
                                                    <label>Address</label>
                                                    <input type="text" name="address" value="${customer.address}" class="form-control">
                                                </div>
                                                <div class="form-group">
                                                    <label>Phone</label>
                                                    <input type="text" name="phone" value="${customer.phone}" class="form-control">
                                                </div>
                                                <button type="submit" class="btn btn-primary">Save changes</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        !-- Add Customer Modal -->
        <div class="modal fade" id="addCustomerModal" tabindex="-1" role="dialog" aria-labelledby="addCustomerModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addCustomerModalLabel">Add Customer</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="memeber" method="post">
                             <input type="hidden" name="action" value="add">
                            <div class="form-group">
                                <label>Password</label>
                                <input type="text" name="password" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Contact Name</label>
                                <input type="text" name="contactName" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <input type="text" name="address" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <input type="text" name="phone" class="form-control">
                            </div>
                            <button type="submit" class="btn btn-primary">Add Customer</button>
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
