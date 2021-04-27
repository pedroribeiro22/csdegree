<%--
  Created by IntelliJ IDEA.
  User: jfc
  Date: 2019-04-22
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="style/starter-template.css" rel="stylesheet" />
    <style>
        body {
            padding-top: 50px;
        }

        .starter-template {
            padding: 40px 15px;
            text-align: center;
        }

        header {
            color: black;
            background-color: #eeee;
            padding: 5px 10px 5px 10px;
        }

        nav {
            background-color: rgba(211, 211, 202, 0.933);
        }

        footer {
            background-color: rgba(211, 211, 202, 0.933);
        }
    </style>

    <title>Template</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <header>
                <h2>App name</h2>
            </header>
        </div>
    </div>
    <div class="row" style="margin-top: 5px">
        <div class="col">
            <nav class="nav" style="justify-content: space-between">
                <a
                        class="nav-link disabled"
                        href=""
                        tabindex="-1"
                        aria-disabled="true"
                ><c:out value="${username}"></c:out></a
                >
                <span style="display: flex">
              <a class="nav-link" href="">All games</a>
              <a class="nav-link" href="">My games</a>
              <a class="nav-link" href="">Add game</a>
            </span>
            </nav>
        </div>
    </div>
    <div class="row">
        <c:choose>
            <c:when test="${requestScope.page=='all_games'}">
                <jsp:include page="ListGames.jsp"/>
            </c:when>
            <c:when test="${requestScope.page=='filtered_games'}">
                <jsp:include page="FilteredGames.jsp"/>
            </c:when>
        </c:choose>
        <div class="col-md-4">
            <form style="padding: 10px;">
                <div class="form-group" style="width: 90%">
                    <label for="year_input">Year</label>
                    <input type="year" class="form-control" id="year_input" placeholder="Year">
                </div>
                <div class="form-group" style="width: 90%">
                    <label for="platform_input">Platform</label>
                    <input type="text" class="form-control" id="platform_input" placeholder="Platform">
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col text-center">
            <footer style="text-align:center">
                <nav class="nav justify-content-center">
                    <a
                            class="nav-link disabled text-center"
                            href=""
                            tabindex="-1"
                            aria-disabled="true"
                    >Footer</a
                    >
                </nav>
            </footer>
        </div>
    </div>
</div>
<!-- /.container -->

<!-- Bootstrap core JavaScript -->
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>