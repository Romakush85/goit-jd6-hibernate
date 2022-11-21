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
        <h5>Find company</h5>
        <div style ="margin-top:10px;margin-left:10px;">
            <form action="/companies" method="get">
                   <label for="companyId"> Company ID: <label>
                   <input type="text" id="companyId" name="companyId">
                   <button type="submit" name="command" value="find">Find</button>
                   <button type="submit" name="command" value="findAll">Find all companies</button>
            </form>
        </div>
    </body>
</html>