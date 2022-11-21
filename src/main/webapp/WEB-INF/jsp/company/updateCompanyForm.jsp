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
        <h5>Update company</h5>
             <div style ="margin-top:10px;margin-left:10px; max-width: 30%;display:flex;flex-direction:column;">
                  <form action="/companies" method="post">
                    <div style ="display:flex;justify-content: space-between;">
                         <label for="companyId"> Company ID:</label>
                         <input style ="margin-top:5px;margin-bottom:5px;" type="text" id="companyId" name="companyId">
                    </div>
                    <div style ="display:flex;justify-content: space-between;">
                         <label for="name"> Company name:</label>
                         <input style ="margin-top:5px;margin-bottom:5px;" type="text" id="name" name="name">
                    </div>
                    <div style ="display:flex;justify-content: space-between;">
                         <label for="country">Country:</label>
                         <input style ="margin-top:5px;margin-bottom:5px;" type="text" id="country" name="country">
                    </div>
                    <div style ="display:flex;justify-content: space-between;">
                         <label for="contactPerson">Contact person:</label>
                         <input style ="margin-top:5px;margin-bottom:5px;" type="text" id="contactPerson" name="contactPerson">
                    </div>
                    <div style ="display:flex;justify-content: space-between;">
                          <label for="email">Email:</label>
                          <input style ="margin-top:5px;margin-bottom:5px;" type="text" id="email" name="email">
                    </div>
                         <button type="submit" name="command" value="update">Update</button>
                  </form>
             </div>
        <div style ="margin-top:10px;margin-left:10px;">
            <c:if test="${not empty result}">
                  ${result}
            </c:if>
        </div>
    </body>
</html>