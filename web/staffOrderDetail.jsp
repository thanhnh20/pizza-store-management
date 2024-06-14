<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

    <head>
        <title>Profile Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="css/staffOrderDetail.css">
    </head>

    <body>
        <c:set var="user" value="${sessionScope.USER_ROLE}" />

        <div class="head sticky-top">
            <div style="width: 100px">
                <a href="DispatchController?btnAction=StaffShowAllProduct">
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
            <a class="link" href="DispatchController?btnAction=StaffShowAllProduct">
                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-book-fill"
                     viewBox="0 0 16 16">
                <path
                    d="M8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z" />
                </svg>
                Product Management
            </a>
            <a class="link" href="DispatchController?btnAction=StaffShowUsers">
                User management
            </a>
        </div>
        <div class="text-center" style="margin-top: 40px">
            <h4>
                Order information
            </h4>
        </div>
        <c:set var="msg" value="${requestScope.MSG}"/>
        <div class="text-center" style="margin-top: 40px">
            <c:if test="${not empty msg}">
                <h6 class="text-success">
                    ${msg}
                </h6>
            </c:if>
        </div>    
        <c:set var="orderDetail" value="${requestScope.ORDER_DETAIL}"/>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <div class="orderDetail">
            <div class="form-div">
                <form class="form" action="DispatchController">
                    <div class="row">
                        <label class="col-sm-4 col-form-label ">Order ID</label>
                        <div class="col-sm-8">
                            <input name="orderId" type="text" disabled class="form-control profile-input" value="${orderDetail.orderId}">
                        </div>
                    </div>

                    <div class="row">
                        <label class="col-sm-4 col-form-label ">Username</label>
                        <div class="col-sm-8">
                            <input name="username" type="text" disabled class="form-control profile-input" value="${orderDetail.username}">
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-sm-4 col-form-label ">Full Name</label>
                        <div class="col-sm-8">
                            <input name="fullName" type="text" disabled class="form-control profile-input" value="${orderDetail.fullName}">
                        </div>
                    </div>

                    <div class="row">
                        <label class="col-sm-4 col-form-label ">Number Phone</label>
                        <div class="col-sm-8">
                            <input name="numberPhone" type="text" disabled class="form-control profile-input" value="${orderDetail.phoneNumber}">
                        </div>
                    </div>

                    <div class="row">
                        <label class="col-sm-4 col-form-label ">Order Date</label>
                        <div class="col-sm-8">
                            <input name="orderDate" type="text" disabled class="form-control profile-input" value="${orderDetail.orderDate}">
                        </div>
                    </div>



                    <div class="row">
                        <label class="col-sm-4 col-form-label ">Shipped Date</label>
                        <div class="col-sm-8">
                            <input name="shippedDate" 
                                   disabled
                                   <c:if test="${empty orderDetail.shippedDate}">
                                       value="N/A"
                                   </c:if>
                                   type="datetime" class="form-control profile-input" 
                                   <c:if test="${not  empty orderDetail.shippedDate}">
                                       value="${orderDetail.shippedDate}"
                                   </c:if>  
                                   >
                        </div>
                    </div>

                    <div class="row">
                        <label class="col-sm-4 col-form-label ">Amount</label>
                        <div class="col-sm-8">
                            <input name="amount" type="text" disabled class="form-control profile-input" value="${orderDetail.amount}">
                        </div>
                    </div>

                    <div class="row">
                        <label class="col-sm-4 col-form-label ">Freight</label>
                        <div class="col-sm-8">
                            <input name="freight" type="number"  
                                   <c:if test="${orderDetail.status eq '2'}">
                                       disabled
                                   </c:if>
                                   class="form-control profile-input" value="${orderDetail.freight}"
                                   >
                        </div>
                    </div>

                    <div class="row">
                        <label class="col-sm-4 col-form-label ">Ship Address</label>
                        <div class="col-sm-8">
                            <input name="shipAddress" type="text"  
                                   <c:if test="${orderDetail.status eq '1'}">
                                       disabled
                                       value="N/A"
                                   </c:if>
                                   class="form-control profile-input" 
                                   <c:if test="${orderDetail.status eq '2'}">
                                       value="${orderDetail.shipAddress}"
                                   </c:if>  
                                   required=""
                                   >
                        </div>
                    </div>

                    <c:if test="${orderDetail.status ne '2'}">
                        <div class="row">
                            <input type="hidden" name="txtOrderId" value="${orderDetail.orderId}"/>
                            <button name="btnAction" value="StaffFinishOrder" class="btn btn-success w-25 ">
                                Finish
                            </button>
                        </div>
                    </c:if>
                </form>
            </div>


            <div class="user-list">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>NO.</th>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Quantity</th>
                            <th>Unit Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${orderDetail.orderDetails}" var="orderDetails" varStatus="count">
                        <form action="DispatchController">
                            <tr>
                                <td>${count.count}</td>
                                <td>${orderDetails.product.productId}</td>
                                <td>${orderDetails.product.productName}</td>
                                <td>${orderDetails.quantity}</td>
                                <td>${orderDetails.unitPrice}$</td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div style="margin: 0 auto; padding: 0 30px auto; text-align: center">
            <a href="DispatchController?btnAction=StaffShowOrders" class="btn btn-primary" type="submit" name="btnAction" value="ShowAllBook">
                List All Order
            </a>
        </div>
    </body>