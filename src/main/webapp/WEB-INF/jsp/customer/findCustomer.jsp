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
        <h5>Find customer</h5>
        <div style ="margin-top:10px;margin-left:10px;">
            <form action="/customers" method="get">
                   <label for="customerId"> Customer ID: <label>
                   <input type="text" id="customerId" name="customerId">
                   <button type="submit" name="command" value="find">Find</button>
                   <button type="submit" name="command" value="findAll">Find all customers</button>
            </form>
        </div>
        <div style ="margin-top:10px;margin-left:10px;">
           <c:if test="${not empty customer}">
            <table style ="border: 2px solid black">
                 <thead style ="border: 2px solid black">
                      <tr>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Customer ID</td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Customer name</td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Country</td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Contact person</td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;">Email</td>

                      </tr>
                 </thead>
                 <tbody style ="border: 2px solid black">
                      <tr>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${customer.customerId}"/></td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${customer.name}"/></td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${customer.country}"/></td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${customer.contactPerson}"/></td>
                            <td style="text-align: center;border: 2px solid black; padding-left: 5px; padding-right: 5px;"><c:out value="${customer.email}"/></td>
                       </tr>
                 </tbody>
            </table>
            </c:if>
            <c:if test="${empty customer}">
                 <p>There is no customer with such ID</p>
            </c:if>
        </div>
    </body>
</html>