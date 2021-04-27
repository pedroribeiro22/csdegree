<%--
  Created by IntelliJ IDEA.
  User: jfc
  Date: 2019-04-22
  Time: 18:09
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
    <div class="row justify-content-md-center" style="padding: 10px">
        <div class="col-md-2">
            <button type="button" class="btn btn-light"><a href="/GMSBaseIJ_war_exploded/ListGames?page=${currpage - 1}"><span>Previous</span></a></button>
        </div>
        <div class="col-md-2">
            <button type="button" class="btn btn-dark"><a href="/GMSBaseIJ_war_exploded/ListGames?page=${currpage + 1}"><span>Next</span></a></button>
        </div>
    </div>
</div>