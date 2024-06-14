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
        <link rel="stylesheet" href="css/userProfile.css">
    </head>

    <body>
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
        <div class="text-center" style="margin-top: 40px">
            <h4>
                Order information
            </h4>
        </div>  
        <c:set var="orderDetail" value="${requestScope.ORDER_DETAIL}"/>
        <div class="orderDetail">
            <div class="form-div">
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
                               disabled
                               <c:if test="${orderDetail.status eq '1'}">
                                   value="0"
                               </c:if>
                               class="form-control profile-input" 
                               <c:if test="${orderDetail.status eq '2'}">
                                   value="${orderDetail.freight}"
                               </c:if>
                               >
                    </div>
                </div>

                <div class="row">
                    <label class="col-sm-4 col-form-label ">Ship Address</label>
                    <div class="col-sm-8">
                        <input name="shipAddress" type="text"  
                               disabled
                               <c:if test="${orderDetail.status eq '1'}">
                                   value="N/A"
                               </c:if>
                               class="form-control profile-input" 
                               <c:if test="${orderDetail.status ne '1'}">
                                   value="${orderDetail.shipAddress}"
                               </c:if>  
                               required=""
                               >
                    </div>
                </div>
                <div class="row">
                    <label class="col-sm-4 col-form-label ">Status</label>
                    <div class="col-sm-8">
                        <c:if test="${orderDetail.status eq '1'}">
                            <button id="myButton" disabled class="btn btn-warning">Waiting</button>
                        </c:if>
                        <c:if test="${orderDetail.status eq '2'}">
                            <button id="myButton" disabled class="btn btn-success">Finish</button>
                        </c:if>                     
                    </div>
                </div>

            </div>


            <div class="">
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
                                <td>${orderDetails.unitPrice}</td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div style="margin: 0 auto; padding: 0 30px auto; text-align: center">
            <a href="DispatchController?btnAction=UserOrdersHistory" class="btn btn-primary" type="submit" name="btnAction" value="ShowAllBook">
                Orders History
            </a>
        </div>
    </body>