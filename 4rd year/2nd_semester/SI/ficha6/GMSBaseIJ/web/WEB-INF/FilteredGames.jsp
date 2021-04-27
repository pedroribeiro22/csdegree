<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 4/27/21
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-8">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Game</th>
            <th scope="col">Year</th>
            <th scope="col">Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="g" items="${requestScope.games}">
            <tr>
                <td>Game ${g.id}</td>
                <td>${g.year}</td>
                <td>${g.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
