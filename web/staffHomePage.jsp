<%@page import="dto.ProductDTO"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Admin Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/adminListBook.css">
    </head>

    <body class="text-center">
        <div class="head sticky-top">
            <div style="width: 100px">
                <a href="DispatchController?btnAction=ShowAllBook">
                    <img src="image/logo.png" alt="" class="image" style="width: 100%; height: 100%">  
                </a>
            </div>
            <div class="admin">
                <label class="link"><svg xmlns="http://www.w3.org/2000/svg" width="22" height="30" fill="currentColor"
                                         class="bi bi-person-lines-fill" viewBox="0 0 16 16">
                    <path
                        d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z" />
                    </svg>
                    Admin</label>
                <a class="link" href="DispatchController?btnAction=LogOut">Log out</a>
            </div>
            <a class="link" href="DispatchController?btnAction=StaffShowUsers">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-list-ul"
                     viewBox="0 0 16 16">
                <path fill-rule="evenodd"
                      d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm-3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                </svg>
                User management
            </a>
            <a class="link" href="DispatchController?btnAction=StaffShowOrders">
                
                Order management
            </a>
        </div>

        <%-- --%>
        <form class="search" action="DispatchController">
            <input type="text" class="form-control" name="searchValue" value="${param.searchValue}" placeholder="Search Name Book"/>
            <button class="btn btn-primary" type="submit" name="btnAction" value="StaffSearchProduct" placeholder="Search Name Book">Search</button>
        </form>
        <c:set var="listProduct" value="${requestScope.LIST_PRODUCT}"/>
        <c:set var="listCategory" value="${requestScope.LIST_CATEGORY}"/>
        <c:if test="${empty listProduct}">
            <h5 style="margin: 20px">
                This search does not has result            
            </h5>
            <a href="DispatchController?btnAction=StaffShowAllProduct" class="btn btn-primary" type="submit" name="btnAction" value="StaffShowAllProduct">
                List All Book
            </a>
        </c:if>
        <c:set var="msg" value="${requestScope.MSG}"/>
        <c:set var="msgError" value="${requestScope.MSG_ERROR}"/>
        <c:if test="${not empty msg}">
            <h5 style="margin: 20px" class="text-success">
                ${msg}         
            </h5>
        </c:if>
        <c:if test="${not empty msgError}">
            <h5 style="margin: 20px" class="text-danger">
                ${msgError}         
            </h5>
        </c:if>
        <c:if test="${not empty listProduct}">
            <div class="book-list">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="productDTO" items="${listProduct}" varStatus="count">
                        <form action="DispatchController">
                            <tr>
                                <td>
                                    ${productDTO.productId}
                                </td>
                                <td>
                                    <input class="form-control text-center" required type="text" name="txtProductName" value="${productDTO.productName}"/>
                                </td>
                                <td>
                                    <input class="form-control text-center" required style="width: 80px; margin: auto"  type="text" pattern="[0-9]+" name="txtQuantity" value="${productDTO.quantity}"/>
                                </td>
                                <td>
                                    <input class="form-control text-center" required style="width: 80px; margin: auto"  type="number" pattern="[0-9]+" step="any" name="txtPrice" value="${productDTO.price}"/>
                                </td>
                                <td>
                                    <input class="form-control text-center" required type="text" name="txtBookName" value="${productDTO.description}"/>
                                </td>
                                
                                <td>
                                    <select name="category" class="form-control text-center btn " aria-label="Default select example">
                                        <c:forEach var="categoryDTO" items="${listCategory}" varStatus="count">
                                            <option class="btn btn-success" <c:if test="${productDTO.category.categoryId eq categoryDTO.categoryId}">
                                                    selected
                                                </c:if>
                                                value="${categoryDTO.categoryId}">
                                                ${categoryDTO.categoryName}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                                
                                <td>
                                    <select name="status" class="form-control text-center btn <c:if test="${productDTO.status eq 'false'}">
                                            btn-danger
                                        </c:if>
                                        <c:if test="${productDTO.status eq 'true'}">
                                            btn-success
                                        </c:if>" aria-label="Default select example">
                                        
                                        <option class="btn btn-success" <c:if test="${productDTO.status eq 'true'}">
                                                selected
                                            </c:if>
                                            value="true">
                                            Available
                                        </option>
                                        
                                        <option class="btn btn-danger" <c:if test="${productDTO.status eq 'false'}">
                                                selected
                                            </c:if>
                                            value="false">
                                            Unavailable
                                        </option>
                                    </select>
                                </td>
                                <td>
                                    <input type="hidden" name="searchValue" value="${requestScope.searchValue}"/>
                                    <input type="hidden" name="txtProductId" value="${productDTO.productId}"/>
                                    <input class="btn btn-outline-primary" type="submit" name="btnAction" value="UpdateProduct" />      
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div style="margin: 20px 0 50px">
                <a class="btn btn-outline-primary" href="addNewProduct.html">Add New Product</a>
            </div>
        </c:if>



    </body>