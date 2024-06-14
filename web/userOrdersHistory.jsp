<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="css/userProfile.css">
    </head>

    <body class="text-center">
        <c:set var="user" value="${sessionScope.USER_ROLE}" />
        <div class="head sticky-top">
            <div style="width: 100px">
                <a href="DispatchController?btnAction=UserHomePage">
                    <img src="image/logo.png" alt="" class="image" style="width: 100%; height: 100%">  
                </a>
            </div>
            <div class="user">
                <a class="link" href="DispatchController?btnAction=UserProfile"><svg xmlns="http://www.w3.org/2000/svg" width="22" height="30" fill="currentColor"
                                                                                     class="bi bi-person-lines-fill" viewBox="0 0 16 16">
                    <path
                        d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z" />
                    </svg>
                    ${user.fullName}</a>
                <a class="link" href="DispatchController?btnAction=LogOut">Log out</a>
            </div>
            <a class="link" href="DispatchController?btnAction=UserShowCart">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="30" fill="currentColor" class="bi bi-cart3"
                     viewBox="0 0 16 16">
                <path
                    d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
                </svg>
                Cart
            </a>
        </div>
        <form class="search" action="DispatchController">
            <input name="txtSearchValue" value="${param.txtSearchValue}" type="text" class="form-control" type="search" placeholder="Order Id">
            <button name="btnAction" value="SearchOrder" class="btn btn-primary">Search</button>
        </form>
        <c:set var="msg" value="${requestScope.MSG}"/>
        <c:if test="${not empty msg}">
            <h5 style="margin: 20px" class="text-success">
                ${msg}         
            </h5>
        </c:if>
        <c:set var="listOrder" value="${requestScope.LIST_ORDER}"/>
        <c:if test="${empty listOrder}">
            <h5 style="margin-top: 25px">
                This search does not has result            
            </h5>
            <a href="DispatchController?btnAction=StaffShowOrders" class="btn btn-primary" type="submit" name="btnAction" value="ShowAllBook">
                List All Order
            </a>
        </c:if>
        <c:if test="${not empty listOrder}">
            <div class="user-list" >
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>NO.</th>
                            <th>Order ID</th>
                            <th>Username</th>
                            <th>Order Date</th>
                            <th>Shipped Date</th>
                            <th>Amount</th>
                            <th>Freight</th>
                            <th>Ship Address</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listOrder}" var="order" varStatus="count">
                        <form action="DispatchController">
                            <tr>
                                <td>${count.count}</td>
                                <td>${order.orderId}</td>
                                <td>${order.customer.account.username}</td>
                                <td>${order.orderDate}</td>
                                <td>
                                    <c:if test="${empty order.shippedDate}">
                                        N/A
                                    </c:if>
                                    <c:if test="${not empty order.shippedDate}">
                                        ${order.shippedDate}
                                    </c:if>
                                </td>
                                <td>${order.amount}$</td>
                                <td>
                                    <c:if test="${order.freight eq '0' and order.status eq '1'}">
                                        N/A
                                    </c:if>
                                    <c:if test="${order.status eq '2'}">
                                        ${order.freight}$
                                    </c:if>    
                                </td>
                                <td>
                                    <c:if test="${empty order.shipAddress}">
                                        N/A
                                    </c:if>
                                    <c:if test="${not empty order.shipAddress}">
                                        ${order.shipAddress}
                                    </c:if>   
                                </td>
                                <td>
                                    <c:if test="${order.status eq '1'}">
                                        <button id="myButton" disabled class="btn btn-warning">Waiting</button>
                                    </c:if>
                                    <c:if test="${order.status eq '2'}">
                                        <button id="myButton" disabled class="btn btn-success">Finish</button>
                                    </c:if> 
                                </td>
                                <td>
                                    <input type="hidden" name="txtOrderID" value="${order.orderId}"/>
                                    <input class="btn btn-outline-primary" type="submit" name="btnAction" value="Detail"/> 
                                </td>
                            </tr>
                        </form>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </c:if>

    </body>