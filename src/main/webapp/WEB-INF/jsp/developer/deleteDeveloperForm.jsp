<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>ProjectManagementSystem</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <c:import url="${contextPath}/WEB-INF/jsp/navbar.jsp"/>
        <h5>Delete developer</h5>
        <div style ="margin-top:10px;margin-left:10px;">
            <form action="/developers" method="post">
                   <label for="devId"> Developer ID: <label>
                   <input type="text" id="devId" name="devId">
                   <button type="submit" name="command" value="delete">Delete</button>
            </form>
        </div>
        <c:if test="${not empty result}">
            ${result}
        </c:if>
    </body>
</html>