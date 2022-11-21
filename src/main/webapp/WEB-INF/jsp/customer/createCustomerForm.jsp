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
     <h5>Create customer</h5>
        <div style ="margin-top:10px;margin-left:10px; max-width: 30%;display:flex;flex-direction:column;">
                  <form action="/customers" method="post">
                    <div style ="display:flex;justify-content: space-between;">
                         <label for="name"> Customer name:</label>
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
                   <button type="submit" name="command" value="create">Create</button>
                  </form>
             </div>
    </body>
</html>