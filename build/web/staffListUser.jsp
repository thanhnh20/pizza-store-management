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
        <link rel="stylesheet" href="css/adminListUser.css">
    </head>

    <body class="text-center">
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
            <a class="link" href="DispatchController?btnAction=StaffShowOrders">

                Order management
            </a>
        </div>
        <form class="search" action="DispatchController">
            <input name="txtSearchValue" value="${param.txtSearchValue}" type="text" class="form-control" type="search" placeholder="Username">
            <button name="btnAction" value="SearchUser" class="btn btn-primary">Search</button>
        </form>
        <c:set var="msg" value="${requestScope.MSG}"/>
        <c:if test="${not empty msg}">
            <h5 style="margin: 20px" class="text-success">
                ${msg}         
            </h5>
        </c:if>
        <c:set var="listUser" value="${requestScope.LIST_USER}"/>
        <c:if test="${empty listUser}">
            <h5 style="margin-top: 25px">
                This search does not has result            
            </h5>
            <a href="DispatchController?btnAction=ShowListUser" class="btn btn-primary" type="submit" name="btnAction" value="ShowAllBook">
                List All User
            </a>
        </c:if>
        <c:if test="${not empty listUser}">
            <div class="user-list" >
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>NO.</th>
                            <th>User ID</th>
                            <th>Username</th>
                            <th>Full Name</th>
                            <th>Address</th>
                            <th>Phone number</th>
                            <th>Status</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listUser}" var="user" varStatus="count">
                        <form action="DispatchController">
                            <tr>
                                <td>${count.count}</td>
                                <td>${user.userID}</td>
                                <td>${user.account.username}</td>
                                <td>${user.account.fullName}</td>
                                <td>
                                    <c:if test="${empty user.address}">
                                        N/A
                                    </c:if>
                                    <c:if test="${not empty user.address}">
                                        ${user.address}
                                    </c:if>       
                                </td>
                                <td>
                                    <c:if test="${empty user.phone}">
                                        N/A
                                    </c:if>
                                    <c:if test="${not empty user.phone}">
                                        ${user.phone}
                                    </c:if>
                                </td>
                                <td>
                                    <select name="status" class="form-control text-center btn <c:if test="${user.account.status eq 'false'}">
                                            btn-danger
                                        </c:if>
                                        <c:if test="${user.account.status eq 'true'}">
                                            btn-success
                                        </c:if>" aria-label="Default select example">

                                        <option class="btn btn-success" <c:if test="${user.account.status eq 'true'}">
                                                selected
                                            </c:if>
                                            value="true">
                                            Active
                                        </option>

                                        <option class="btn btn-danger" <c:if test="${user.account.status eq 'false'}">
                                                selected
                                            </c:if>
                                            value="false">
                                            Inactive
                                        </option>
                                    </select>
                                </td>
                                <td>
                                    <input type="hidden" name="txtUserId" value="${user.account.accountId}"/>
                                    <input class="btn btn-danger" type="submit" name="btnAction" value="Delete User">
                                </td>
                            </tr>
                        </form>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </c:if>

    </body>